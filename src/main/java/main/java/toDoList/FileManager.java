package main.java.toDoList;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {

    public void createFile() {
        File newFile = new File("savedTasks.txt");
    }

    public void saveChanges(TaskCollection collection) {
        try {
            FileOutputStream file = new FileOutputStream("savedTasks.txt");
            ObjectOutputStream output = new ObjectOutputStream(file);

            // writes objects to output stream
            output.writeObject(collection);

            output.close();
            file.close();
        }
        catch(IOException e)
        {
            System.out.println("File could not be found " +  e);
        }
    }

    public TaskCollection openSavedTasks() {

        TaskCollection collection = new TaskCollection();

        try {
            FileInputStream file = new FileInputStream("savedTasks.txt");
            ObjectInputStream stream = new ObjectInputStream(file);

            collection = (TaskCollection) stream.readObject();

            stream.close();
            file.close();
        }
        catch(IOException  e)
        {
            System.out.println("File is not found. " +  e);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("problems inside the file " + e);
        }
        return collection;
    }
//        try {
//            File file = new File("savedTasks.txt");
//            Scanner reader = new Scanner(file);
//
//            while (reader.hasNextLine()) {
//                String data = reader.nextLine();
//
//                String title = "";
//                String project= "";
//                LocalDate date = null;
//
//                Pattern titlePattern = Pattern.compile("([a-zA-Z\\s]+)");
//                Matcher m1 = titlePattern.matcher(data);
//
//                Pattern datePattern = Pattern.compile("([\\d-]+(?!:))");
//                Matcher m2 = datePattern.matcher(data);
//
//                Pattern projectPattern = Pattern.compile("(?<=project: ).*");
//                Matcher m3 = projectPattern.matcher(data);
//
//                if (m1.find()) {
//                    title = m1.group().trim();
//                }
//
//                if (m2.find()) {
//                    String d = m2.group();
//                    date = LocalDate.parse(m2.group());
//                }
//
//                if (m3.find()) {
//                    project = m3.group();
//                }
//
//                collection.addTask(new Task(title, date, project));
//
//            }
//            reader.close();
//        } catch(IOException e) {
//            e.getMessage();
//        }
//    }
}
