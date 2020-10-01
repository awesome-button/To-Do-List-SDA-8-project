package jetbrains;
import java.util.HashMap;

public class Task {
    HashMap<String, String> taskDetails;
    boolean isDone;

    public Task(String title, String dueDate, String project) {
        this.taskDetails = new HashMap<>();
        this.taskDetails.put("title", title);
        this.taskDetails.put("dueDate", dueDate);
        this.taskDetails.put("project", project);
        this.isDone = false;
    }

    public String getTitle() {
        return this.taskDetails.get("title");
    }

    public String getDueDate() {
        return this.taskDetails.get("dueDate");
    }

    public String getProject() {
        return this.taskDetails.get("project");
    }

    public void markDone() {
        this.isDone = true;
    }

    public void formatDate() {

    }

    public String toString() {
        return "Task: " + this.taskDetails.get("title") + "\nDue date: " + this.taskDetails.get("dueDate")
                + "\nProject: " + this.taskDetails.get("project");
    }

}
