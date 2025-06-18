package com.cli_taskmanager.core;

import java.util.*;

public class ProjectImplementation implements Project {
    private final String id;
    private final String name;
    private final String description;
    private final List<Task> tasks;

    public ProjectImplementation(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.tasks = new ArrayList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public double getProgress() {
        if (tasks.isEmpty())
            return 0.0;
        return tasks.stream().mapToDouble(Task::getProgress).average().orElse(0.0);
    }

    @Override
    public boolean isCompleted() {
        return tasks.stream().allMatch(Task::isCompleted);
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void removeTask(String id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    @Override
    public void execute() {
        tasks.forEach(Task::execute);
    }

}
