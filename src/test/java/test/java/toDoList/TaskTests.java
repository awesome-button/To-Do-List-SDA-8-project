package test.java.toDoList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import main.java.toDoList.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TaskTests {

    @Test
    void createTask() {
        Task task = new Task("buy a guide", LocalDate.parse("2020-12-05"), "travel");
        assertTrue(true);
    }

    @Test
    void getTitle() {
        Task task = new Task("buy a guide", LocalDate.parse("2020-12-05"), "travel");
        String expected = "buy a guide";
        assertEquals(expected, task.getTitle());
    }

    @Test
    void setTitle() {
        Task task = new Task("buy a guide", LocalDate.parse("2020-12-05"), "travel");
        task.setTitle("plan the route");
        String expected = "plan the route";
        assertEquals(expected, task.getTitle());
    }

    @Test
    void getDueDate() {
        Task task = new Task("buy a guide", LocalDate.parse("2020-12-05"), "travel");
        LocalDate expected = LocalDate.parse("2020-12-05");
        assertEquals(expected, task.getDueDate());
    }

    @Test
    void setDueDate() {
        Task task = new Task("buy a guide", LocalDate.parse("2020-12-05"), "travel");
        task.setDueDate(LocalDate.parse("2021-01-10"));
        LocalDate expected = LocalDate.parse("2021-01-10");
        assertEquals(expected, task.getDueDate());
    }

    @Test
    void getProject() {
        Task task = new Task("buy a guide", LocalDate.parse("2020-12-05"), "travel");
        String expected = "travel";
        assertEquals(expected, task.getProject());
    }

    @Test
    void setProject() {
        Task task = new Task("buy a guide", LocalDate.parse("2020-12-05"), "travel");
        task.setProject("decorate house");
        String expected = "decorate house";
        assertEquals(expected, task.getProject());
    }

    @Test
    void checkIfTaskIsDone() {
        Task task = new Task("buy a guide", LocalDate.parse("2020-12-05"), "travel");
        assertEquals(false, task.isDone());
    }

    @Test
    void markAsDone() {
        Task task = new Task("buy a guide", LocalDate.parse("2020-12-05"), "travel");
        task.markDone();
        assertEquals(true, task.isDone());
    }

    @Test
    void printTaskInfo() {
        Task task = new Task("buy a guide", LocalDate.parse("2020-12-05"), "travel");
        String expected = "buy a guide, due date: 2020-12-05, project: travel";
        assertEquals(expected, task.toString());
    }

}
