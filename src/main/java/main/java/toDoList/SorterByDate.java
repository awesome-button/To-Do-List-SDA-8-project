package main.java.toDoList;

import java.util.Comparator;

/**
 * This class is used for sorting tasks by date
 */
public class SorterByDate implements Comparator<Task> {

    /**
     * Compares two task objects based on their due date. Returns 1 if objects are given in the correct order,
     * -1 if the objects are given in the wrong order, and 0 if the due dates of the two task objects
     * are identical.
     * @param t1
     * @param t2
     * @return
     */
    public int compare(Task t1, Task t2) {
        return t1.getDueDate().compareTo(t2.dueDate);
    }
}
