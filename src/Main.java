//  Спроектировать и реализовать классы, эмулирующие базу
//  данных для операций над банковскими счетами – банковский счет,
//  владелец счета, история операций.
//  Должны быть доступны следующие операции
//        • добавление некоторой суммы на счет;
//        • снятие некоторой суммы со счета;
//        • передача суммы с одного счета на другой;
//        • открытие счета и закрытие счета;
//        • получение истории операций;
//        • регистрация клиента;
//        • получение баланса по счету;
//        • формирование сводной информации по всем счетам


import db.Database;
import operations.GetOperation;
import operations.PutOperation;
import operations.TransferOperation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database db = Database.getInstance();

        System.out.println("Please enter the command. To view a list of possible commands, enter '--help'");
        while(true){
            String command = scanner.nextLine();
            switch (command){
                case "--help":
                    System.out.println("--end - to stop the program \n" +
                            "--add - to add user \n" +
                            "--remove_id - to remove user by id \n" +
                            "--remove_name - to remove user by name \n" +
                            "--get_db - to print database \n" +
                            "--get_user_id - to get user by id \n" +
                            "--get_user_name - to get user by name \n" +
                            "--add_money - to add money \n" +
                            "--get_money - to get money from account \n" +
                            "--transfer - to transfer money from first account to second");
                    break;
                case "--end":
                    return;
                case "--add":
                    System.out.println("Please enter the name, surname and balance in format 'name surname balance'");
                    try {
                        String[] data = scanner.nextLine().split(" ");
                        db.addUser(data[0], data[1], Integer.parseInt(data[2]));
                        System.out.println("Operation completed");
                    }catch (Exception e){
                        System.out.println("The entered data is incorrect, please use the format 'name surname balance'");
                    }
                    break;
                case "--remove_id":
                    System.out.println("Please enter user id");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        db.removeById(id);
                        System.out.println("Operation completed");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "--remove_name":
                    System.out.println("Please enter user name and surname in format 'name surname'");
                    try {
                        String[] data = scanner.nextLine().split(" ");
                        db.removeByName(data[0], data[1]);
                        System.out.println("Operation completed");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "--get_db":
                    System.out.println(db.getDatabase());
                    break;
                case "--get_user_id":
                    System.out.println("Please enter user id");
                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(db.getUserById(id));
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "--get_user_name":
                    System.out.println("Please enter user name and surname in format 'name surname'");
                    try {
                        String[] data = scanner.nextLine().split(" ");
                        System.out.println(db.getUserByName(data[0], data[1]));
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "--add_money":
                    System.out.println("Please enter data in format 'name surname sum'");
                    try {
                        String[] data = scanner.nextLine().split(" ");
                        PutOperation operation = new PutOperation(Integer.parseInt(data[2]), data[0], data[1]);
                        operation.doOperation();
                        System.out.println("Operation completed");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "--get_money":
                    System.out.println("Please enter data in format 'name surname sum'");
                    try {
                        String[] data = scanner.nextLine().split(" ");
                        GetOperation operation = new GetOperation(Integer.parseInt(data[2]), data[0], data[1]);
                        operation.doOperation();
                        System.out.println("Operation completed");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "--transfer":
                    System.out.println("Please enter data in format 'name1 surname1 name2 surname2 sum'");
                    try {
                        String[] data = scanner.nextLine().split(" ");
                        TransferOperation operation = new TransferOperation(Integer.parseInt(data[4]), data[0], data[1], data[2], data[3]);
                        operation.doOperation();
                        System.out.println("Operation completed");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Input command is incorrect. To view a list of possible commands, enter '--help'");
            }
        }
    }
}
