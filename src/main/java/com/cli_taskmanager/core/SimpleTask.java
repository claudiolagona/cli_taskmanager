package com.cli_taskmanager.core;

import java.time.LocalDate;
import java.util.UUID;

public class SimpleTask extends AbstractTask {
    private final String id;
    private final String name;
    private final String description;
    private final LocalDate dueDate;
    private boolean completed = false;

    public SimpleTask(String name, String description, LocalDate dueDate, String id, boolean completed) {
        if (id != null) {
            this.id = id;
        } else {
            this.id = UUID.randomUUID().toString();
        }
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public SimpleTask(String name, String description, LocalDate dueDate) {
        this(name, description, dueDate, UUID.randomUUID().toString(), false);
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
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public double getProgress() {
        return completed ? 1.0 : 0.0;
    }

    @Override
    public void execute() {
        this.completed = true;
        notifyObservers("Task " + this.getName() + "completed");
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
