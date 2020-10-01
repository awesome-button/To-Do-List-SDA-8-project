package jetbrains;

import java.util.ArrayList;

public class ProjectCollection {
    ArrayList<Project> projects;

    public ProjectCollection() {
        this.projects = new ArrayList<>();
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public String listProjects() {
        String list = "";

        for (int i = 0; i < this.projects.size(); i++) {
            Project currentProject = this.projects.get(i);
            list += String.valueOf(i+1) + ": " + currentProject.getTitle() + "\n";
        }

        return list;
    }

}
