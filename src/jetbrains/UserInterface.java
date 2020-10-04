package jetbrains;
import java.time.LocalDate;
import java.util.Scanner;

    public class UserInterface {

        TaskCollection collection;
        Scanner scanner;

    public UserInterface() {
        this.collection = new TaskCollection();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        printWelcome();

        //inout for testing
        this.collection.addTask(new Task("buy a guide", LocalDate.parse("2020-10-30"), "travel"));
        this.collection.addTask(new Task("book train tickets", LocalDate.parse("2020-10-30"), "travel"));
        this.collection.addTask(new Task("make a route", LocalDate.parse("2020-10-30"), "travel"));
        this.collection.addTask(new Task("get some advice from Aline", LocalDate.parse("2020-10-30"), "travel"));

        while (true) {
            printCommands();
            System.out.println("Enter command:");

            int command = Integer.valueOf(scanner.nextLine());

            switch (command) {
                case 1:
                    printTasks();
                    break;
                case 2:
                    createTask();
                    printTasks();
                    break;
                case 3:
                    editTask();
                    break;
                case 4:
                    return;
            }
        }
    }

    public void printWelcome() {
    System.out.println("Welcome to \"Get It Done!\"\n");
    }

    public void printCommands() {
        System.out.println(
                "Pick an option:\n" +
                "(1) Show task list(by date or project)\n" +
                "(2) Add new task\n" +
                "(3) Edit task(update, mark as done, remove)\n" +
                "(4) Save and quit\n");
    }

    public void printTasks() {
        System.out.println("The following tasks are now on your list:");
        System.out.println(this.collection.listTasks());
    }

    public void createTask() {
        System.out.println("For which project do you want to create a task?"); // should not ask the question if there is only one project
        String project = scanner.nextLine();

        System.out.println("What is your task's title?");
        String title = scanner.nextLine();

        System.out.println("By when shall it be done? (date in format yyyy-mm-dd)"); // should handle exceptions, wrong format or a date before today
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        Task task = new Task(title, dueDate, project);
        this.collection.addTask(task);
    }

    public void editTask() {
        //add a case for when there are no tasks on the list

        System.out.println("which task would you like to edit?");
        System.out.println(collection.listTasks());

        int taskIndex = Integer.valueOf(scanner.nextLine()) - 1;
        Task selectedTask = this.collection.getTask(taskIndex);

        System.out.println("What would you like to do?\n" +
                "Pick an option:\n" +
                "(1) Update the name\n" +
                "(2) Mark as done" +
                "(3) Remove");
        int action = Integer.valueOf(scanner.nextLine());

        if (action == 1) {
            System.out.println("Write a new name for your task: ");
            String newTitle = scanner.nextLine();
            selectedTask.setTitle(newTitle);
        }

        if (action == 2) {
            if (selectedTask.markDone()) {
                System.out.println("The task (" + (taskIndex+1) + ") has been marked as done");
            } else {
                System.out.println("Something went wrong. Try it again"); //repeating code, exception?
            }
        }

        if (action == 3) {
            if (this.collection.removeTask(selectedTask)) {
                System.out.println("Task (" + (taskIndex + 1) + ") has been removed from your list\n");
                printTasks();
            } else {
                System.out.println("Something went wrong. Try it again"); // repeating code, exception?
            }
        }
    }


}
