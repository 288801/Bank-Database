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
import service.MainService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainService service = new MainService();

        System.out.println("Please enter the command. To view a list of possible commands, enter '--help'");
        while(true){
            String command = scanner.nextLine();
            String response = service.processRequest(command);
            if(response == null){
                break;
            }else{
                System.out.println(response);
            }
        }
    }
}
