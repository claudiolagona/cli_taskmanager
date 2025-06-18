package com.cli_taskmanager.manager;

import com.cli_taskmanager.core.Project;

import java.util.*;

public class ProjectManagerImplementation implements ProjectManager {
    private final Map<String, Project> projects = new HashMap<>();

    private static ProjectManagerImplementation instance;

    private ProjectManagerImplementation() {
    }

    public static synchronized ProjectManagerImplementation getInstance() {
        if (instance == null) {
            instance = new ProjectManagerImplementation();
        }
        return instance;
    }

    @Override
    public void addProject(Project project) {
        projects.put(project.getId(), project);
    }

    @Override
    public void removeProject(String id) {
        projects.remove(id);
    }

    @Override
    public Optional<Project> getProjectById(String id) {
        return Optional.ofNullable(projects.get(id));
    }

    @Override
    public List<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    @Override
    public void executeProject(String id) {
        Project project = projects.get(id);
        if (project != null) {
            project.execute();
        } else {
            throw new NoSuchElementException("Project with ID '" + id + "' not found");
        }
    }

    @Override
    public void clearAllProjects() {
        projects.clear();
    }
}
