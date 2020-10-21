package main.java.toDoList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TaskCollection implements Serializable {
        ArrayList<Task> tasks;

        public TaskCollection() {
            this.tasks = new ArrayList<>();
        }

        public TaskCollection(ArrayList<Task> tasks) {
        this.tasks = tasks;
        }


        public int getSize() {
            return this.tasks.size();
        }

        public Task getTask(int index) {
            return this.tasks.get(index);
        }

        public boolean addTask(Task task) {
            return this.tasks.add(task);
        }

        public boolean removeTask(Task task) {
            return this.tasks.remove(task);
        }

        public boolean isEmpty() {
            return this.tasks.isEmpty();
        }

        public String getTasks() {
            String list = "";

            for (int i = 0; i < this.tasks.size(); i++) {
                Task currentTask = this.tasks.get(i);
                list += (i+1) + ": " + currentTask.toString() + "\n";
            }

            return list;
        }

        public void sortByProject() {
            Collections.sort(tasks, new SorterByProject());
        }

        public void sortByDate() {
            Collections.sort(tasks, new SorterByDate());
        }




}
