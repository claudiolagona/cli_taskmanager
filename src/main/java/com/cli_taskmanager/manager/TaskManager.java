package com.cli_taskmanager.manager;

import java.util.List;
import java.util.Optional;

import com.cli_taskmanager.core.Task;

public interface TaskManager {
    void addTask(Task task);

    void removeTask(String id);

    Optional<Task> getTaskById(String id);

    List<Task> getAllTasks();

    void executeTask(String id);

    void clearAllTasks();
}
