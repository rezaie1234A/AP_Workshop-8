import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CommandHandler commandHandler = new CommandHandler();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("1- Add");
            System.out.println("2- Remove");
            System.out.println("3- Notes");
            System.out.println("4- Export");
            String command = scanner.nextLine();
            if(command.equals("1")) {
                commandHandler.add();
            } else if (command.equals("2")) {
                commandHandler.remove();
            } else if (command.equals("3")) {
                commandHandler.showNotes();
            } else if (command.equals("4")) {
                commandHandler.export();
            } else {
                System.out.println("Invalid command");
            }
        }

    }

}