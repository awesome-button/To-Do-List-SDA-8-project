package main.java.toDoList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Allows to maintain and manipulate a list of tasks.
 * The class implements Serializable interface for the program
 * to be able to write/read objects to/from streams
 */
public class TaskCollection implements Serializable {
        ArrayList<Task> tasks;

        /**Constructor function sets tasks field to a new ArrayList of tasks*/
        public TaskCollection() {
            this.tasks = new ArrayList<>();
        }

        /**
         *Returns size of a list of tasks
         * @return
         */
        public int getSize() {
            return this.tasks.size();
        }

        /**
         * Accepts an index and retrieves as task object from collection by this index
         * @param index
         * @return
         */
        public Task getTask(int index) {
            return this.tasks.get(index);
        }

        /**
         * Adds the task given as a parameter to collection
         * @param task
         * @return
         */
        public boolean addTask(Task task) {
            return this.tasks.add(task);
        }

        /**
         * Removes the task given as a parameter from collection
         * @param task
         * @return
         */
        public boolean removeTask(Task task) {
            return this.tasks.remove(task);
        }

        /**
         * Provides information on whether the collection is empty
         * @return
         */
        public boolean isEmpty() {
            return this.tasks.isEmpty();
        }

        /**
         * Provides a string representation of tasks present in collection
         * @return
         */
        public String getTasks() {
            String list = "";

            for (int i = 0; i < this.tasks.size(); i++) {
                Task currentTask = this.tasks.get(i);
                list += (i+1) + ": " + currentTask.toString() + "\n";
            }
            return list;
        }

        /**Sorts tasks by project using Stream*/
        public void sortByProject() {
            
            List<Task> list = this.tasks.stream()
                    .sorted((task1, task2) -> task1.getProject().compareTo(task2.getProject()))
                    .collect(Collectors.toList());
            this.tasks = new ArrayList<Task>(list);

        }

        /**Sorts task by date using Collections.sort and Comparator*/
        public void sortByDate() {
            Collections.sort(this.tasks, new SorterByDate());
        }

}
