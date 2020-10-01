package jetbrains;

import java.util.ArrayList;

public class Project {
    String title;
    ArrayList<Task> tasks;

    public Project(String title) {
        this.title = title;
        this.tasks = new ArrayList<>();
    }

    public String getTitle() {
        return this.title;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public String listTasks() {
        String list = "";

        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            list += String.valueOf(i+1) + ": " + currentTask.getTitle() + ", due on " + currentTask.getDueDate() + "\n";
        }

        return list;
    }

}
