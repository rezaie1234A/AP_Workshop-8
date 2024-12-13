import java.io.*;
import java.util.Scanner;

public class CommandHandler {
    Scanner scanner = new Scanner(System.in);

    public void add () {
        Note note= new Note();
        System.out.print("Enter Name: ");
        note.setTitle( scanner.nextLine());
        System.out.print("Enter Contents: ");
        note.setContent(takeMultiLineInput());
        note.setDate(String.valueOf(System.currentTimeMillis()));

        File file = new File("assets/" + note.getTitle() +".ser");
        if (file.exists()) {
            System.out.println("File already exists!");
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream)) {
            out.writeObject(note);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove () {
        System.out.println("Enter a title to remove:");
        Note note = new Note();
        note.setTitle(scanner.nextLine());
        File file = new File("assets/" + note.getTitle() +".ser");
        if (file.exists()) {
            file.delete();
        } else {
            System.out.println("Note does not exist!");
        }
    }

    public void showNotes () {
        Note note = new Note();
        System.out.println("Show Title");
        File file = new File("assets/");
        File [] fileNames = file.listFiles();
        for (File fileName : fileNames) {
            System.out.println(fileName.getName());
        }
        System.out.println("choose a title:");
        note.setTitle(scanner.nextLine());
        System.out.println("Show Contents:");
        File filename = new File("assets/" + note.getTitle() +".ser");
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            note = (Note) inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(note.getContent());
    }

    public void export () {
        Note note = new Note();

        System.out.println("Show Title");
        File file = new File("assets/");
        File [] fileNames = file.listFiles();
        for (File fileName : fileNames) {
            System.out.println(fileName.getName());
        }
        System.out.println("choose a note to export:");
        note.setTitle(scanner.nextLine());
        System.out.println("Show Contents:");
        File filename = new File("assets/" + note.getTitle() +".ser");
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             ObjectInputStream inputStream = new ObjectInputStream(fileInputStream)) {
            note = (Note) inputStream.readObject();
            FileWriter writer = new FileWriter("assets/" + note.getTitle() +".txt");
            writer.write(note.getContent());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String takeMultiLineInput () {
        StringBuilder input = new StringBuilder();
        String line = "";
        while (!(line = scanner.nextLine()).equals("#")) {
            input.append(line).append("\n");
        }
        return input.toString();
    }

}