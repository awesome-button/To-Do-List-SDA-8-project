package jetbrains;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInterface {

        TaskCollection collection;
        Scanner scanner;

    public UserInterface() {
        this.collection = new TaskCollection();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        printWelcome();
        openSavedTasks();
        //input for testing
//        this.collection.addTask(new Task("buy a guide", LocalDate.parse("2020-10-01"), "travel"));
//        this.collection.addTask(new Task("book train tickets", LocalDate.parse("2020-10-30"), "travel"));
//        this.collection.addTask(new Task("make a route", LocalDate.parse("2020-10-05"), "travel"));
//        this.collection.addTask(new Task("get some advice from Aline", LocalDate.parse("2020-10-09"), "travel"));

        while (true) {
            printCommands();
            System.out.println("Enter command:");

            int command = Integer.valueOf(scanner.nextLine());

            switch (command) {
                case 1:
                    sortTasks();
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
                    createFile();
                    saveChanges();
                    printByeMessage();
                    return;
            }


        }
    }

    public void createFile() {
        File newFile = new File("savedTasks.txt");
    }

    public void saveChanges() {
        try {
            FileWriter writer = new FileWriter("savedTasks.txt");
            writer.write(this.collection.getTasks());
            writer.close();
            System.out.println("wrote to the file");
        } catch(IOException e) {
            e.getMessage();
        }
    }

    public void openSavedTasks() {
        try {
            File file = new File("savedTasks.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();

                String title = "";
                String project= "";
                LocalDate date = null;

                Pattern titlePattern = Pattern.compile("([a-zA-Z\\s]+)");
                Matcher m1 = titlePattern.matcher(data);

                Pattern datePattern = Pattern.compile("([\\d-]+(?!:))");
                Matcher m2 = datePattern.matcher(data);

                Pattern projectPattern = Pattern.compile("(?<=project: ).*");
                Matcher m3 = projectPattern.matcher(data);

                if (m1.find()) {
                    title = m1.group().trim();
                }

                if (m2.find()) {
                    String d = m2.group();
                    date = LocalDate.parse(m2.group());
                }

                if (m3.find()) {
                    project = m3.group();
                }

                this.collection.addTask(new Task(title, date, project));

            }
            reader.close();
        } catch(IOException e) {
            e.getMessage();
        }
    }

    public void printWelcome() {
        System.out.println("Welcome to \"Get It Done!\"\n");
    }

    public void printByeMessage() {
        System.out.println("Your tasks have been saved.\nWelcome back whenever you need to Get It Done!");
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
        System.out.println(this.collection.getTasks());
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
        System.out.println(collection.getTasks());

        int taskIndex = Integer.valueOf(scanner.nextLine()) - 1;
        Task selectedTask = this.collection.getTask(taskIndex);

        System.out.println("What would you like to do?\n" +
                "Pick an option:\n" +
                "(1) Update the name\n" +
                "(2) Mark as done\n" +
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
}
