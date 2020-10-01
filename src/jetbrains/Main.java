package jetbrains;

public class Main {

    public static void main(String[] args) {

        ProjectCollection collection = new ProjectCollection();
        Project project1 = new Project("Travel to Asia");
        Project project2 = new Project("Learn programming");

	    Task task1 = new Task("buy a map", "30102020", "Travel to Asia");
        Task task2 = new Task("book hotels", "30102020", "Travel to Asia");

        project1.addTask(task1);
        project2.addTask(task2);

        collection.addProject(project1);
        collection.addProject(project2);

        System.out.println(collection.listProjects());

    }
}
