package main.java.toDoList;
import java.io.*;

/**
 * The class is responsible for opening tasks saved from the previous sessions and for saving tasks into a file
 * at the end of each session.
 */
public class FileManager {

    /**
     * Saves tasks to a file.
     * @param collection
     * @param name
     */
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

    /**
     * Opens tasks from a file saved there from a previous session.
     * @param name
     * @return
     */
    public TaskCollection openSavedTasks(String name) {

        TaskCollection collection = new TaskCollection();

        try {
            FileInputStream file = new FileInputStream(name);
            ObjectInputStream stream = new ObjectInputStream(file);

            collection = (TaskCollection) stream.readObject();

            stream.close();
            file.close();
        }
        catch(EOFException e)
        {}
        catch(IOException e) {
            System.out.println(e);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
        return collection;
    }
}
