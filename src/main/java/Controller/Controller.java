package Controller;

import DBUtil.DatabaseControl;
import Domain.Client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Controller {

    private final DatabaseControl db = new DatabaseControl();

    public void startController(){
        try(Scanner scanner = new Scanner(System.in)){
            boolean isRunning = true;
            while (isRunning) {
                showMenu();
                String selected = scanner.nextLine();
                switch (selected) {
                    case "1" -> db.showAllDB();
                    case "2" -> {
                        Client client = createNewClientByTypingValues(scanner);
                        db.createRowInDB(client);
                        System.out.println("New client in DB created!");
                    }
                    case "3" -> {
                        System.out.println("Enter id:");
                        int id = Integer.parseInt(scanner.nextLine());
                        if (db.checkIfIdIsPresent(id)) {
                            db.showRowById(id);
                        } else System.out.println("ID Not Found.");
                    }
                    case "4" -> {
                        System.out.println("Enter id:");
                        int id = Integer.parseInt(scanner.nextLine());
                        if (db.checkIfIdIsPresent(id)) {
                            System.out.println("Found. This is a row to change:");
                            db.showRowById(id);
                            Client client = createNewClientByTypingValues(scanner);
                            db.updateRowInDB(id, client);
                        }
                        else System.out.println("ID Not Found.");
                    }
                    case "5" -> {
                        System.out.println("Enter id:");
                        int id = Integer.parseInt(scanner.nextLine());
                        if (db.checkIfIdIsPresent(id)) {
                            db.deleteRowById(id);}
                        else{
                            System.out.println("ID Not Found.");
                        }
                    }
                    default -> isRunning = false;
                }
            }
        } catch (ParseException e) {
            System.out.println("Date WRONG!");
        }
    }

    private Client createNewClientByTypingValues(Scanner scanner) throws ParseException {
        System.out.println("Enter: Name, Country, City, Date(yyyy-MM-dd)");
        return new Client(scanner.nextLine(),
                                scanner.nextLine(),
                                scanner.nextLine(),
                                new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));
    }

    private void showMenu(){
        System.out.println();
        System.out.println("1 - Show all DB");
        System.out.println("---------------------");
        System.out.println("2 - Create row in DB");
        System.out.println("3 - Read row by id");
        System.out.println("4 - Update row by id");
        System.out.println("5 - Delete row by id");
        System.out.println("---------------------");
        System.out.println("6+ - EXIT");
        System.out.println("Enter number below:");
    }
}
