package com.cli_taskmanager.builder;

import com.cli_taskmanager.core.Project;
import com.cli_taskmanager.core.ProjectImplementation;
import com.cli_taskmanager.core.Task;

import java.util.ArrayList;
import java.util.List;

public class ProjectBuilder {
    private String name;
    private String description;
    private final List<Task> tasks = new ArrayList<>();

    public ProjectBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProjectBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProjectBuilder addTask(Task task) {
        tasks.add(task);
        return this;
    }

    public Project build() {
        return new ProjectImplementation(name, description, tasks);
    }
}
