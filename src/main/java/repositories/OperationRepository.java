package repositories;

import operations.OperationImpl;

import java.util.List;

public interface OperationRepository extends Repository<OperationImpl, Integer> {
    public List<OperationImpl> getAllAccountOperations(int id);
}
