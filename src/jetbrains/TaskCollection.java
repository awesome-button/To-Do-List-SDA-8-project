package jetbrains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TaskCollection {
        ArrayList<Task> tasks;

        public TaskCollection() {
            this.tasks = new ArrayList<>();
        }

        public Task getTask(int index) {
            return this.tasks.get(index);
        }

        public void addTask(Task task) {
            this.tasks.add(task);
        }

        public boolean removeTask(Task task) {
            return this.tasks.remove(task);
        }

        public String listTasks() {
            String list = "";

            for (int i = 0; i < this.tasks.size(); i++) {
                Task currentTask = this.tasks.get(i);
                list += String.valueOf(i+1) + ": " + currentTask.getTitle() + ", due on " + currentTask.getDueDate() + "\n";
            }

            return list;
        }

        public void sortByName() {
            Collections.sort(tasks, new SorterByName());
        }

        public void sortByDate() {
            Collections.sort(tasks, new SorterByDate());
        }




}
