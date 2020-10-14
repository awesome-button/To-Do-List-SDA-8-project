package test.java.toDoList;

import static org.junit.jupiter.api.Assertions.*;

import main.java.toDoList.FileManager;
import main.java.toDoList.Task;
import main.java.toDoList.TaskCollection;
import main.java.toDoList.UserInterface;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskCollectionTests {

    @Test
    void createCollection() {
        TaskCollection collection = new TaskCollection();
        assertTrue(true);
    }

    @Test
    void getTask() {
        TaskCollection collection = new TaskCollection();
        collection.addTask(new Task("water plants", LocalDate.parse("2020-12-31"), "plants"));
        collection.addTask(new Task("buy a flower", LocalDate.parse("2020-12-31"), "plants"));
        collection.addTask(new Task("buy pots", LocalDate.parse("2020-12-31"), "plants"));
        String expected = "buy pots, due date: 2020-12-31, project: plants";
        assertEquals(expected, collection.getTask(2).toString());
    }

    @Test
    void addTask() {
        TaskCollection collection = new TaskCollection();
        collection.addTask(new Task("buy pots", LocalDate.parse("2020-12-31"), "plants"));
        String expected = "1: buy pots, due date: 2020-12-31, project: plants\n";
        assertEquals(expected, collection.getTasks().toString());
    }

}


