package jetbrains;
import java.time.LocalDate;
import java.util.Comparator;

public class Task {
    String title, project;
    LocalDate dueDate;
    boolean isDone;

    public Task(String title, LocalDate dueDate, String project) {
        this.title = title;
        this.dueDate = dueDate;
        this.project = project;
        this.isDone = false;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public String getProject() {
        return this.project;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(LocalDate date) {
        this.dueDate = date;
    }

    public void setProject(String project) {
        this.project = project;
    }

    //the task should also be automatically removed from the list
    public boolean markDone() {
        this.isDone = true;
        return this.isDone; //side effect? how to handle it in a different way?
    }

    public void formatDate() {

    }

    public String toString() {
        return this.title + ", due date: " + this.dueDate
                + ", project: " + this.project;
    }

}
