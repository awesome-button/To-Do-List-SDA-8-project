package jetbrains;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {

    public void createFile() {
        File newFile = new File("savedTasks.txt");
    }

    public void saveChanges(TaskCollection collection) {
        try {
            FileWriter writer = new FileWriter("savedTasks.txt");
            writer.write(collection.getTasks());
            writer.close();
        } catch(IOException e) {
            e.getMessage();
        }
    }

    public void openSavedTasks(TaskCollection collection) {
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

                collection.addTask(new Task(title, date, project));

            }
            reader.close();
        } catch(IOException e) {
            e.getMessage();
        }
    }
}
