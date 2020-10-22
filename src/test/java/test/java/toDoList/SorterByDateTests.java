package test.java.toDoList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import main.java.toDoList.SorterByDate;
import main.java.toDoList.Task;
import java.time.LocalDate;

public class SorterByDateTests {

    @Test
    void sortTwoTasksPlacedInWrongOrder() {
        SorterByDate sorter = new SorterByDate();

        Task task1 = new Task("buy a guide", LocalDate.parse("2020-12-30"), "travel");
        Task task2 = new Task("cook dinner", LocalDate.parse("2021-12-25"), "Christmas");

        int expected = -1;

        assertEquals(expected, sorter.compare(task1, task2));
    }

    @Test
    void sortTwoTasksPlacedInCorrectOrder() {
        SorterByDate sorter = new SorterByDate();

        Task task1 = new Task("cook dinner", LocalDate.parse("2021-12-25"), "Christmas");
        Task task2 = new Task("buy a guide", LocalDate.parse("2020-12-30"), "travel");

        int expected = 1;

        assertEquals(expected, sorter.compare(task1, task2));
    }

    @Test
    void sortTwoTasksWithTheSameDate() {
        SorterByDate sorter = new SorterByDate();

        Task task1 = new Task("cook dinner", LocalDate.parse("2020-12-25"), "Christmas");
        Task task2 = new Task("buy a guide", LocalDate.parse("2020-12-25"), "travel");

        int expected = 0;

        assertEquals(expected, sorter.compare(task1, task2));
    }

}
