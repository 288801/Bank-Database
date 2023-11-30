package repositories.impl;

import db.ConnectionManager;
import operations.*;
import repositories.OperationRepository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationRepositoryImpl implements OperationRepository {

    private static ConnectionManager connectionManager;
    private static OperationRepositoryImpl instance;

    public static OperationRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new OperationRepositoryImpl();
            connectionManager = ConnectionManager.getInstance();
        }
        return instance;
    }

    @Override
    public List<OperationImpl> getAll() {
        try{
            List<OperationImpl> operations = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `operation`");
            while (!rs.isClosed() && rs.next()) {
                operations.add(getOperationFromResultSet(rs));
            }
            rs.close();
            return operations;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void add(OperationImpl operation) {
        try {
            String type = "";
            if(operation instanceof PutOperation){
                type = "PUT";
                connectionManager.executeUpdate("INSERT INTO `operation`(`date`," +
                        " `sender_id`, `sum`, `type`) VALUES ( CURRENT_DATE, " + operation.getAccountId() +
                        ", " + operation.getSum() + ", '" + type + "')");
            }else if(operation instanceof GetOperation){
                type = "GET";
                connectionManager.executeUpdate("INSERT INTO `operation`(`date`," +
                        " `sender_id`, `sum`, `type`) VALUES ( CURRENT_DATE, " + operation.getAccountId() +
                        ", " + operation.getSum() + ", '" + type + "')");
            }else{
                type = "TRANSFORM";
                connectionManager.executeUpdate("INSERT INTO `operation`(`date`," +
                        " `sender_id`, `recipient_id`, `sum`, `type`) VALUES ( CURRENT_DATE, " + operation.getAccountId() +
                        ", " + operation.getDestinationId() + ", " + operation.getSum() + ", '" + type + "')");
            }
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public OperationImpl getById(Integer id) {
        try {
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `operation` WHERE operation_id = " + id);
            rs.next();
            OperationImpl operation = getOperationFromResultSet(rs);
            rs.close();
            return operation;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void removeById(Integer id) {
        try {
            connectionManager.executeUpdate("DELETE FROM `operation` WHERE operation_id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Integer id, OperationImpl operation) {
        try {
            String type = "";
            if(operation instanceof PutOperation){
                type = "PUT";
            }else if(operation instanceof GetOperation){
                type = "GET";
            }else{
                type = "TRANSFORM";
            }
            connectionManager.executeUpdate("UPDATE `operation` SET date = '" + operation.getDate() + "', sender_id = "
                    + operation.getAccountId() + "', recipient_id = "
                    + operation.getDestinationId() + "', sum = "
                    + operation.getSum() + "', type = "
                    + type +  "' WHERE operation_id = " + id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private OperationImpl getOperationFromResultSet(ResultSet rs) {
        try {
            int id = rs.getInt(1);
            Date date = rs.getDate(2);
            int senderId = rs.getInt(3);
            int recipientId = rs.getInt(4);
            int sum = rs.getInt(5);
            String type = rs.getString(6);
            if(type.equals("GET")){
                return new GetOperation(id, sum, senderId, date);
            }else if(type.equals("PUT")){
                return new PutOperation(id, sum, senderId, date);
            }else{
                return new TransferOperation(id, sum, senderId, recipientId, date);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<OperationImpl> getAllAccountOperations(int id) {
        try{
            List<OperationImpl> operations = new ArrayList<>();
            ResultSet rs = connectionManager.executeSelect("SELECT * FROM `operation` WHERE sender_id = " + id
                    + " OR recipient_id = " + id);
            while (!rs.isClosed() && rs.next()) {
                operations.add(getOperationFromResultSet(rs));
            }
            rs.close();
            return operations;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
