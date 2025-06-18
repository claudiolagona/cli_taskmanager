package com.cli_taskmanager.persistence;

import com.cli_taskmanager.core.Task;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileStoreManager {
    void saveTasks(List<Task> tasks, Path filePath) throws IOException;

    List<Task> loadTasks(Path filePath) throws IOException;
}
