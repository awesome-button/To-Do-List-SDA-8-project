package main.java.toDoList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
        printCommands();

        boolean finished = false;

        while (!finished) {
            System.out.println("Press 0 for list of commands\n\nEnter command: ");
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
            case 0:
                printCommands();
                break;
            case 1:
                sortTasks();
                System.out.println(collection.getTasks());
                break;
            case 2:
                createTask();
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

        boolean validInput = false;

        while (!validInput) {
            System.out.println("Sort by task project(1) or due date(2)?");

            int answer = Integer.valueOf(scanner.nextLine());

            switch (answer) {
                case 1:
                    collection.sortByProject();
                    validInput = true;
                    break;
                case 2:
                    collection.sortByDate();
                    validInput = true;
                    break;
                default:
                    break;
            }

        }
    }

    public void createTask() {
        System.out.println("For which project do you want to create a task?"); // should not ask the question if there is only one project
        String project = scanner.nextLine();

        System.out.println("What is your task's title?");
        String title = scanner.nextLine();

        System.out.println("By when shall it be done? (date in format yyyy-mm-dd)"); // should handle exceptions, wrong format or a date before today

        //Throws an exception and notifies user that the date is in the wrong format
        try {
            LocalDate dueDate = LocalDate.parse(scanner.nextLine());
            Task task = new Task(title, dueDate, project);
            this.collection.addTask(task);
            System.out.println("\nThe task \'" + title + "\' has been created for" +
                    "the project \'" + project + "\' with due date on " + dueDate.toString() + "\n");

        } catch(DateTimeParseException e) {
            System.out.println("Error: please put the due date in the format yyyy-mm-dd.\n");
        }

    }

    public void editTask() {

        System.out.println("Which task would you like to edit?");
        System.out.println(this.collection.getTasks());
      
        boolean validTaskIndex = false;

        while (!validTaskIndex) {
            try {
                int taskIndex = Integer.valueOf(scanner.nextLine()) - 1;
                Task selectedTask = this.collection.getTask(taskIndex);
                validTaskIndex = true;

                System.out.println("What would you like to do?\n" +
                "Pick an option:\n" +
                "(1) Update the name\n" +
                "(2) Update the due date\n" +
                "(3) Update the project name\n" +
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
                    default:
                        printErrorMessage();
                        break;

            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nPlease choose the task from the list. You have only " + this.collection.getSize() +
                        " tasks on your list at the moment.");
            }

        }

    }

    public void printErrorMessage() {
        System.out.println("Something went wrong. Try it again\n");
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
                "Commands:\n" +
                "(1) Show task list(by project or date)\n" +
                "(2) Add new task\n" +
                "(3) Edit task(update, mark as done, remove)\n" +
                "(4) Save and quit\n");
    }

}
