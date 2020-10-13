package main.java.toDoList;

import java.util.Comparator;

public class SorterByProject implements Comparator<Task> {
    public int compare(Task t1, Task t2)
    {
        return t1.getProject().compareTo(t2.getProject());
    }

}
