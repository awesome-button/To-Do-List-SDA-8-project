package main.java.toDoList;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInterface {

        TaskCollection collection;
        Scanner scanner;
        FileManager fileManager;

    /**
     * Constructor method where a new collection of tasks and a scanner are initialized
     */
    public UserInterface() {
        this.collection = new TaskCollection();
        this.scanner = new Scanner(System.in);
        this.fileManager = new FileManager();
    }

    /**
     * Start the program by opening tasks saved from previous sessions, printing a welcome
     * message and asking user to choose and enter a command.
     */
    public void start() {
        fileManager.openSavedTasks(this.collection);
        printWelcome();
        boolean finished = false;

        while (!finished) {
            printCommands();
            System.out.println("Enter command:");
            int command = Integer.valueOf(scanner.nextLine());
            finished = processCommand(command);
        }
    }

    /**
     * Accept input command from user and continue with execution based on the command
     * @param command
     */
    public boolean processCommand(int command) {
        boolean wantToQuit = false;

        switch (command) {
            case 1:
                sortTasks();
                System.out.println(this.collection.getTasks());
                break;
            case 2:
                createTask();
                System.out.println(this.collection.getTasks());
                break;
            case 3:
                editTask();
                break;
            case 4:
                fileManager.createFile();
                fileManager.saveChanges(this.collection);
                printByeMessage();
                wantToQuit = true;
                break;
        }
        return wantToQuit;
    }

    public void sortTasks() {
        System.out.println("Sort by task project(1) or due date(2)?");

        int answer = Integer.valueOf(scanner.nextLine());

        switch (answer) {
            case 1:
                collection.sortByProject();
                break;
            case 2:
                collection.sortByDate();
                break;
        }
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

        System.out.println("Which task would you like to edit?");
        System.out.println(this.collection.getTasks());
        int taskIndex = Integer.valueOf(scanner.nextLine()) - 1;
        Task selectedTask = this.collection.getTask(taskIndex);

        System.out.println("What would you like to do?\n" +
                "Pick an option:\n" +
                "(1) Update the name\n" +
                "(2) Update the due date" +
                "(3) Update the project name" +
                "(4) Mark as done\n" +
                "(5) Remove");
        int action = Integer.valueOf(scanner.nextLine());

        switch (action) {
            case 1:
                System.out.println("Write a new name for your task: ");
                String newTitle = scanner.nextLine();
                selectedTask.setTitle(newTitle);
                break;
            case 2:
                System.out.println("Write a new due date for your task in the format yyyy-mm-dd: ");
                LocalDate newDate = LocalDate.parse(scanner.nextLine());
                selectedTask.setDueDate(newDate);
                break;
            case 3:
                System.out.println("Write a new name for the project your task belongs to: ");
                String newProject = scanner.nextLine();
                selectedTask.setProject(newProject);
                break;
            case 4:
                if (selectedTask.markDone()) {
                    this.collection.removeTask(selectedTask);
                    System.out.println("The task (" + (taskIndex+1) + ") has been marked as done");
                } else {
                    printErrorMessage();
                }
                break;
            case 5:
                if (this.collection.removeTask(selectedTask)) {
                    System.out.println("Task (" + (taskIndex + 1) + ") has been removed from your list\n");
                } else {
                    printErrorMessage();
                }
                break;
        }

    }

    public void printErrorMessage() {
        System.out.println("Something went wrong. Try it again");
    }

    public void printByeMessage() {
        System.out.println("Your tasks have been saved.\nWelcome back whenever you need to Get It Done!");
    }



    public void printWelcome() {
        if (this.collection.isEmpty()) {
            System.out.println("Welcome to \"Get It Done!\"\n");
        } else {
            System.out.println("Welcome back to \"Get It Done!\"\n");
            this.collection.getTasks();
        }

    }

    public void printCommands() {
        System.out.println(
                "Pick an option:\n" +
                "(1) Show task list(by project or date)\n" +
                "(2) Add new task\n" +
                "(3) Edit task(update, mark as done, remove)\n" +
                "(4) Save and quit\n");
    }

}
