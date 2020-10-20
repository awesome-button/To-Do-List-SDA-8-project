package main.java.toDoList;
import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
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

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        return this.title + ", due date: " + this.dueDate
                + ", project: " + this.project;
    }

}
