package jetbrains;

public class Main {

    public static void main(String[] args) {
	    Task task1 = new Task("buy a map", "30102020", "Travel to Asia");
        Task task2 = new Task("book hotels", "30102020", "Travel to Asia");

        Project project = new Project("Travel to Asia");
        project.addTask(task1);
        project.addTask(task2);
        System.out.println(project.listTasks());
        project.removeTask(1);
        System.out.println(project.listTasks());
    }
}
