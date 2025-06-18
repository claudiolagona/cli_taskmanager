package com.cli_taskmanager.storage;

import java.io.IOException;

public interface StorageService {
    void saveTasks(String filePath) throws IOException;

    void loadTasks(String filePath) throws IOException;
}
