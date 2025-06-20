package com.cli_taskmanager.persistence;

import com.cli_taskmanager.core.Task;
import com.cli_taskmanager.exceptions.TaskManagerException;

import java.nio.file.Path;
import java.util.List;

public interface FileStoreManager {
    void saveTasks(List<Task> tasks, Path filePath) throws TaskManagerException;

    List<Task> loadTasks(Path filePath) throws TaskManagerException;
}
