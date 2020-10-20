package main.java.toDoList;
import java.io.*;

public class FileManager {

    public void createFile(String name) {
        File newFile = new File(name);
    }

    public void saveChanges(TaskCollection collection, String name) {
        try {
            FileOutputStream file = new FileOutputStream(name);
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

    public TaskCollection openSavedTasks(String name) {

        TaskCollection collection = new TaskCollection();

        try {
            FileInputStream file = new FileInputStream(name);
            ObjectInputStream stream = new ObjectInputStream(file);

            collection = (TaskCollection) stream.readObject();

            stream.close();
            file.close();
        }
        catch(EOFException  e)
        {}
        catch(IOException e) {
            System.out.println(e);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("problems inside the file " + e);
        }
        return collection;
    }
}
