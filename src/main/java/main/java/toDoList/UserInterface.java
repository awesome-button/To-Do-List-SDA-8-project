package main.java.toDoList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserInterface {

        TaskCollection collection;
        TaskCollection logCompletedTasks;
        Scanner scanner;
        FileManager fileManager;

    /**
     * Constructor method where a new collection of tasks and a scanner are initialized
     */
    public UserInterface() {
        this.collection = new TaskCollection();
        this.logCompletedTasks = new TaskCollection();
        this.scanner = new Scanner(System.in);
        this.fileManager = new FileManager();
    }

    /**
     * Start the program by opening tasks saved from previous sessions, printing a welcome
     * message and asking user to choose and enter a command.
     */
    public void start() {
        this.collection = fileManager.openSavedTasks("savedTasks.txt");
        this.logCompletedTasks = fileManager.openSavedTasks("logCompletedTasks.txt");
        printWelcome();
        printCommands();
        acceptCommand();
    }

    public void acceptCommand() {
        boolean finished = false;

        while (!finished) {
            System.out.println("Press 0 for list of commands\n\nEnter command: ");
            try {
                int command = Integer.valueOf(scanner.nextLine());
                finished = processCommand(command);
            }
            catch(IllegalArgumentException e) {
                printInexistingCommandMessage();
            }
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
                displayTasks(this.collection);
                break;
            case 2:
                createTask();
                break;
            case 3:
                editTask();
                break;
            case 4:
                displayTasks(this.logCompletedTasks);
                break;
            case 5:
                saveChanges();
                printByeMessage();
                wantToQuit = true;
                break;
            default:
                printInexistingCommandMessage();
                break;
        }
        return wantToQuit;
    }

    public void displayTasks(TaskCollection list) {
        if (list.isEmpty()) {
            System.out.println("There are no tasks on this list yet");
        } else {
            sortTasks(list);
            System.out.println(list.getTasks());
        }
    }

    public void sortTasks(TaskCollection list) {

        boolean validInput = false;

        while (!validInput) {
            System.out.println("Sort by task project(1) or due date(2)?\n" +
                    "(type -1 to go to the previous command menu)");
            try {
                int answer = Integer.valueOf(scanner.nextLine());
                switch (answer) {
                    case 1:
                        list.sortByProject();
                        validInput = true;
                        break;
                    case 2:
                        list.sortByDate();
                        validInput = true;
                        break;
                    case -1:
                        printCommands();
                        acceptCommand();
                        break;
                    default: printInexistingCommandMessage();
                }
            } catch(IllegalArgumentException e) {
                printInexistingCommandMessage();
            }
        }
    }

    public void createTask() {
        System.out.println("For which project do you want to create a task?"); // should not ask the question if there is only one project
        String project = scanner.nextLine();

        System.out.println("What is your task's title?");
        String title = scanner.nextLine();

        System.out.println("By when shall it be done? (date in format yyyy-mm-dd)"); // should handle exceptions, wrong format or a date before today

        try {
            LocalDate dueDate = LocalDate.parse(scanner.nextLine());
            Task task = new Task(title, dueDate, project);

            if (this.collection.addTask(task)) {
            System.out.println("\nThe task \'" + title + "\' has been created for " +
                    "the project \'" + project + "\' with due date on " + dueDate.toString() + "\n");
            }

        } catch(DateTimeParseException e) {
            printWrongDateFormatMessage();
        }

    }

    public void editTask() {

        if (this.collection.isEmpty()) {
            System.out.println("There are no tasks on your list yet");
            return;
        }

        int taskIndex = selectTaskToEdit();
        Task selectedTask = this.collection.getTask(taskIndex);

        processEditCommand(selectedTask, taskIndex);

    }

    public int selectTaskToEdit() {

        while (true) {
            System.out.println("Which task would you like to edit?\n");
            System.out.println(this.collection.getTasks());

            try {
                int taskIndex = Integer.valueOf(scanner.nextLine()) - 1;
                this.collection.getTask(taskIndex);
                return taskIndex;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nPlease choose the task from the list. You have only " + this.collection.getSize() +
                        " task(s) on your list at the moment.\n");
            } catch (IllegalArgumentException e) {
                System.out.println("Please choose a task from the list.\n");
            }
        }
    }

    public void processEditCommand(Task selectedTask, int taskIndex) {
        boolean validCommand = false;

        while (!validCommand) {
            System.out.println("What would you like to do?\n" +
                    "Pick an option:\n" +
                    "(1) Update the name\n" +
                    "(2) Update the due date\n" +
                    "(3) Update the project name\n" +
                    "(4) Mark as done\n" +
                    "(5) Remove\n" +
                    "(-1) go to the main command menu");
            try {
                int action = Integer.valueOf(scanner.nextLine());

                switch (action) {
                    case 1:
                        System.out.println("Write a new name for your task: ");
                        String newTitle = scanner.nextLine();
                        selectedTask.setTitle(newTitle);
                        validCommand = true;
                        break;
                    case 2:
                        System.out.println("Write a new due date for your task in the format yyyy-mm-dd: ");
                        LocalDate newDate = LocalDate.parse(scanner.nextLine());
                        selectedTask.setDueDate(newDate);
                        validCommand = true;
                        break;
                    case 3:
                        System.out.println("Write a new name for the project your task belongs to: ");
                        String newProject = scanner.nextLine();
                        selectedTask.setProject(newProject);
                        validCommand = true;
                        break;
                    case 4:
                        selectedTask.markDone();
                        this.collection.removeTask(selectedTask);
                        this.logCompletedTasks.addTask(selectedTask);
                        System.out.println("The task (" + (taskIndex + 1) + ") has been marked as done." +
                                "You can still view it in the log");
                        validCommand = true;
                        break;
                    case 5:
                        if (this.collection.removeTask(selectedTask)) {
                            System.out.println("Task (" + (taskIndex + 1) + ") has been removed from your list\n");
                        } else {
                            printErrorMessage();
                        }
                        validCommand = true;
                        break;
                    case -1:
                        printCommands();
                        acceptCommand();
                    default:
                        printInexistingCommandMessage();
                }

            } catch (IllegalArgumentException e) {
                printInexistingCommandMessage();
            }
        }
    }

    public void saveChanges() {
        fileManager.createFile("savedTasks.txt");
        fileManager.saveChanges(this.collection, "savedTasks.txt");
        fileManager.createFile("logCompletedTasks.txt");
        fileManager.saveChanges(this.logCompletedTasks, "logCompletedTasks.txt");
    }

    public void printErrorMessage() {
        System.out.println("Something went wrong. Try it again\n");
    }

    public void printInexistingCommandMessage() {
        System.out.println("Oops! The command you entered does not exist.\n");
    }

    public void printWrongDateFormatMessage() {
        System.out.println("Error: please put the due date in the format yyyy-mm-dd.\n");
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
                "(4) View log of completed tasks\n" +
                "(5) Save and quit\n");
    }

}

//1: make a list of guests, due date: 2020-12-31, project: Ney Year party
//        2: buy a poster, due date: 2020-12-31, project: house decoration
//        3: learn Gradle, due date: 2020-10-28, project: programming
//        4: buy a guide, due date: 2020-12-23, project: travel
//        5: get some advice from Aline, due date: 2020-10-09, project: travel
//        6: book train tickets, due date: 2020-10-30, project: travel
