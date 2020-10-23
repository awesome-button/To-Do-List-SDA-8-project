package main.java.toDoList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * UserInterface class is responsible for providing text UI and for
 * accepting and processing input from user
 */
public class UserInterface {

        TaskCollection collection;
        TaskCollection logCompletedTasks;
        Scanner scanner;
        FileManager fileManager;

    /**
     * Constructor method where a new collection of tasks, a log of completed tasks,
     * a scanner as well as a file manager are initialized
     */
    public UserInterface() {
        this.collection = new TaskCollection();
        this.logCompletedTasks = new TaskCollection();
        this.scanner = new Scanner(System.in);
        this.fileManager = new FileManager();
    }

    /**
     * Start the program by opening tasks saved from previous sessions, printing a welcome
     * message and a list of commands, and asking user to choose and enter a command.
     */
    public void start() {
        this.collection = fileManager.openSavedTasks("savedTasks.txt");
        this.logCompletedTasks = fileManager.openSavedTasks("logCompletedTasks.txt");
        printWelcome();
        printCommands();
        acceptCommand();
    }

    /**Prints a welcoming message*/
    public void printWelcome() {
        if (this.collection.isEmpty()) {
            System.out.println("Welcome to \"Get It Done!\"\n");
        } else {
            System.out.println("Welcome back to \"Get It Done!\"\n");
            this.collection.getTasks();
        }
    }

    /**Prints a list of commands.*/
    public void printCommands() {
        System.out.println(
                "Commands:\n" +
                        "(1) Show task list(by project or date)\n" +
                        "(2) Add new task\n" +
                        "(3) Edit task(update, mark as done, remove)\n" +
                        "(4) View log of completed tasks\n" +
                        "(5) Save and quit\n");
    }

    /**Accepts a command from user*/
    public void acceptCommand() {
        //the method will stop accepting commands only when boolean 'finished' is set to true
        boolean finished = false;

        while (!finished) {
            System.out.println("Press 0 for list of commands\n\nEnter command: ");
            try {
                //boolean 'finished' is changing its value depending on the return value
                //of processCommand(command) menthod
                int command = Integer.valueOf(scanner.nextLine());
                finished = processCommand(command);
            }
            catch(IllegalArgumentException e) {
                printInexistingCommandMessage();
            }
        }
    }

    /**
     * Processes command given as an argument and proceeds with execution based on command
     * @param command
     */
    public boolean processCommand(int command) {
        //boolean wantToQuit will be set to true only when command 5 is selected by user
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
        //returning the boolean from this method will inform the method acceptCommand()
        //if user wants to exit the program
        return wantToQuit;
    }

    /**
     * Accepts a list of tasks and displays them
     * @param list
     */
    public void displayTasks(TaskCollection list) {
        if (list.isEmpty()) {
            System.out.println("There are no tasks on this list yet");
        } else {
            sortTasks(list);
            System.out.println(list.getTasks());
        }
    }

    /**
     * Accepts a list of tasks and sorts them by date or project, as selected by user.
     * @param list
     */
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
                    //handles the case when user gives number as command out of
                    //existing commands range
                    default: printInexistingCommandMessage();
                }
                //handle the case when user gives a non-number as command
            } catch(IllegalArgumentException e) {
                printInexistingCommandMessage();
            }
        }
    }

    /**
     * Accepts data about task from user and creates a task based on that data
     */
    public void createTask() {

        String project = getProjectName();
        String title = getTaskName();
        LocalDate dueDate = getDueDate();

        Task task = new Task(title, dueDate, project);

        if (this.collection.addTask(task)) {
            System.out.println("\nThe task \'" + title + "\' has been created for " +
                    "the project \'" + project + "\' with due date on " + dueDate.toString() + "\n");
        }
    }

    /**
     * Gets user input for project name
     * @return
     */
    public String getProjectName() {
        String project = "";

        while (stringEmpty(project)) {
            System.out.println("Write project name:"); // should not ask the question if there is only one project
            project = scanner.nextLine();

            if (stringEmpty(project)) printEmptyFieldMessage();
        }
        return project;
    }

    /**
     * Gets user input for task title
     * @return
     */
    public String getTaskName() {
        String title = "";

        while(stringEmpty(title)) {
            System.out.println("What is your task's title?");
            title = scanner.nextLine();

            if (stringEmpty(title)) printEmptyFieldMessage();
        }
        return title;
    }

    /**
     * Gets user input for due date
     * @return
     */
    public LocalDate getDueDate() {
        LocalDate dueDate = null;

        while (dueDate == null) {
            System.out.println("By when shall it be done? (date in format yyyy-mm-dd)");

            try {
                LocalDate date = LocalDate.parse(scanner.nextLine());
                LocalDate today = LocalDate.now();

                //checks if the due date is set for not earlier than today
                if (date.compareTo(today) == -1) {
                    System.out.println("The due date cannot be set before " + today);
                } else {
                    dueDate = date;
                }

            } catch(DateTimeParseException e) {
                printWrongDateFormatMessage();
            }
        }
        return dueDate;
    }

    /**Checks if a string is empty*/
    private boolean stringEmpty(String name) {
        return name.equals("");
    }

    /**
     * Edits a task with the help of two helper methods: selectTaskToEdit()
     * and processEditCommand()
     */
    public void editTask() {

        if (this.collection.isEmpty()) {
            System.out.println("There are no tasks on your list yet");
            return;
        }

        int taskIndex = selectTaskToEdit();
        Task selectedTask = this.collection.getTask(taskIndex);

        processEditCommand(selectedTask, taskIndex);
    }

    /**
     * Asks user for the number of the task he/she wants to edit and
     * returns an index of that task in the TaskCollection
     * @return
     */
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

    /**
     * Edits task's title/due date/project title or removes the selected task
     * or marks it as done. The operation is chosen based on user's input.
     * @param selectedTask
     * @param taskIndex
     */
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
                        String newTitle = getTaskName();
                        selectedTask.setTitle(newTitle);
                        System.out.println("The name for your task has been updated to \'" + newTitle + "\'");
                        validCommand = true;
                        break;
                    case 2:
                        LocalDate newDate = getDueDate();
                        selectedTask.setDueDate(newDate);
                        validCommand = true;
                        System.out.println("The due date for your task has been updated to \'" + newDate.toString() + "\'");
                        break;
                    case 3:
                        String newProject = getProjectName();
                        selectedTask.setProject(newProject);
                        System.out.println("The name for your task's project has been updated to \'" + newProject + "\'");
                        validCommand = true;
                        break;
                    case 4:
                        selectedTask.markDone();
                        if (this.collection.removeTask(selectedTask) && this.logCompletedTasks.addTask(selectedTask)) {
                            System.out.println("The task (" + (taskIndex + 1) + ") has been marked as done." +
                                    "You can still view it in the log");
                            validCommand = true;
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
                        validCommand = true;
                        break;
                    case -1:
                        printCommands();
                        acceptCommand();
                    //handles the case when user gives number as command out of
                    //existing commands range
                    default:
                        printInexistingCommandMessage();
                }
            //handle the case when user gives a non-number as command
            } catch (IllegalArgumentException e) {
                printInexistingCommandMessage();
            }
        }
    }

    /**Saves changes for the current tasks as well as for the log of completed tasks*/
    public void saveChanges() {
        fileManager.saveChanges(this.collection, "savedTasks.txt");
        fileManager.saveChanges(this.logCompletedTasks, "logCompletedTasks.txt");
    }

    /**Prints a message saying the field cannot be empty*/
    public void printEmptyFieldMessage() {
        System.out.println("The field cannot be empty");
    }

    /**Prints error message*/
    public void printErrorMessage() {
        System.out.println("Something went wrong. Try it again\n");
    }

    /**Prints inexisting command message*/
    public void printInexistingCommandMessage() {
        System.out.println("Oops! The command you entered does not exist.\n");
    }

    /**Prints error message for the wrong date formatting*/
    public void printWrongDateFormatMessage() {
        System.out.println("Error: please put the due date in the format yyyy-mm-dd.\n");
    }

    /**Prints bye message*/
    public void printByeMessage() {
        System.out.println("Your tasks have been saved.\nWelcome back whenever you need to Get It Done!");
    }
}