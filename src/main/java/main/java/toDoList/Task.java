package main.java.toDoList;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Task instances can be created from Task class
 * The class implements Serializable interface for the program
 * to be able to write/read objects to/from streams
 */
public class Task implements Serializable {
    String title, project;
    LocalDate dueDate;
    boolean isDone;

    /**Constructor function defines that each instance of Task class
     * will have a title, a due date, a project and a status of completeness*/
    public Task(String title, LocalDate dueDate, String project) {
        this.title = title;
        this.dueDate = dueDate;
        this.project = project;
        this.isDone = false;
    }

    /**
     * Returns task title as a string
     * @return
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns task due date as a local date
     * @return
     */
    public LocalDate getDueDate() {
        return this.dueDate;
    }

    /**
     * Returns project title as a string
     * @return
     */
    public String getProject() {
        return this.project;
    }

    /**
     * Returns boolean providing info about task completeness
     * @return
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**Sets task title to a new one passed as a parameter*/
    public void setTitle(String title) {
        this.title = title;
    }
    /**Sets task due date to a new one passed as a parameter*/
    public void setDueDate(LocalDate date) {
        this.dueDate = date;
    }
    /**Sets project title to a new one passed as a parameter*/
    public void setProject(String project) {
        this.project = project;
    }

    /**Marks task as done*/
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of a task
     * @return
     */
    public String toString() {
        return this.title + ", due date: " + this.dueDate
                + ", project: " + this.project;
    }

}
