package jetbrains;

public class Main {

    public static void main(String[] args) {
	    Task task = new Task("buy a map", "30102020", "Travel to Asia");
        System.out.println(task);
        System.out.println(task.getProject());
        System.out.println(task.getDueDate());
        System.out.println(task.getTitle());
    }
}
