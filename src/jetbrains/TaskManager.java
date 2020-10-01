package jetbrains;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    ProjectCollection collection = new ProjectCollection();
    Scanner scanner;

    public TaskManager() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("Enter command:");

        while (true) {
            String command = scanner.nextLine();
            if (command == "list projects") {

            }
        }



    }


}
