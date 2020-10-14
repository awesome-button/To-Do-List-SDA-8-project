package main.java.toDoList;

import java.util.Comparator;

public class SorterByDate implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        return t1.getDueDate().compareTo(t2.dueDate);
    }
}
