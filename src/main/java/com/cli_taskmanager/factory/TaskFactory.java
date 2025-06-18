package com.cli_taskmanager.factory;

import com.cli_taskmanager.core.Task;

public interface TaskFactory {
    Task createTask(TaskType type, String name, String description, String id, boolean completed);

    Task createTask(TaskType type, String name, String description);
}
