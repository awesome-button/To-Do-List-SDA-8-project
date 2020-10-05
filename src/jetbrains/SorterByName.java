package jetbrains;

import java.util.Comparator;

public class SorterByName implements Comparator<Task> {
    public int compare(Task t1, Task t2)
    {
        return t1.getTitle().compareTo(t2.getTitle());
    }

}
