import db.Accounts;
import db.Operations;
import db.Users;
import models.Role;
import services.*;

import java.util.Scanner;

public class App {

    private Scanner scanner = new Scanner(System.in);
    AccountDatabaseService accountDb = AccountDatabaseService.getInstance();
    UserDatabaseService userDb = UserDatabaseService.getInstance();
    OperationDatabaseService operationDb = OperationDatabaseService.getInstance();
    DatabaseService db = DatabaseService.getInstance();
    private RoleService service;

    public void run(){
        userDb.addAdmin("Egor", "Rogachev", "admin", "admin");
        System.out.println("Please print start to start the program");
        String input = scanner.nextLine();
        if(input.equals("start")){
            System.out.println("Application is running!");
            System.out.println("Print 'end' if you want to turn off the application");
            while(true){
                System.out.println("Please print 'sign up' to create an account and 'sign in' if you already have account or print 'end' to stop the program");
                input = scanner.nextLine();
                if(input.equals("end")){
                    break;
                }else if(input.equals("sign up")){
                    signUp();
                }else if(input.equals("sign in")){
                    signIn();
                }else{
                    System.out.println("The entered data is incorrect, please print 'sign in' or 'sign up");
                }
            }
        }
        System.out.println("Application is turned off!");
    }

    private void signIn(){
        System.out.println("Please print your email and password in format 'email' 'password");
        String[] input = scanner.nextLine().split(" ");
        String email = input[0];
        String password = input[1];
        try {
            Distributor distributor = new Distributor();
            Role role = distributor.checkRole(email, password);
            startInteracting(email, role);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void signUp(){
        try{
            System.out.println("Please print information in format 'name' 'surname' 'email' 'password'");
            String[] input = scanner.nextLine().split(" ");
            String name = input[0];
            String surname = input[1];
            String email = input[2];
            String password = input[3];
            if(userDb.checkUser(email)){
                System.out.println("This user is already exist");
                return;
            }
            userDb.addUser(name, surname, email, password);
            System.out.println("You have successfully registered!");
        }catch (Exception e){
            System.out.println("Input data is incorrect, try again!");
        }
    }

    private void startInteracting(String email, Role role){
        if(role == Role.USER){
            service = new UserService();
        }else if(role == Role.ADMIN){
            service = new AdminService();
        }

        System.out.println("Print 'sign out' if you want to change the account");
        while(true){
            String request = scanner.nextLine();
            if(request.equals("sign out")){
                service = null;
                return;
            }
            try {
                System.out.println(service.processRequest(email, request));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
