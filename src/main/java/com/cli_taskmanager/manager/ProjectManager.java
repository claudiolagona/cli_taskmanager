package com.cli_taskmanager.manager;

import com.cli_taskmanager.core.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectManager {
    void addProject(Project project);

    void removeProject(String id);

    Optional<Project> getProjectById(String id);

    List<Project> getAllProjects();

    void executeProject(String id);

    void clearAllProjects();
}
