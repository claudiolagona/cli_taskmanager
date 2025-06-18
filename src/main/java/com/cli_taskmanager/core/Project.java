package com.cli_taskmanager.core;

import java.util.List;

public interface Project {
    String getId();

    String getName();

    String getDescription();

    List<Task> getTasks();

    double getProgress();

    boolean isCompleted();

    void addTask(Task task);

    void removeTask(String id);

    void execute();
}
