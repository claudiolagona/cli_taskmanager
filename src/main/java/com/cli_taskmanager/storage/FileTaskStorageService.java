package com.cli_taskmanager.storage;

import com.cli_taskmanager.core.*;
import com.cli_taskmanager.manager.TaskManager;
import com.cli_taskmanager.manager.TaskManagerImplementation;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class FileTaskStorageService implements StorageService {
    private final TaskManager manager = TaskManagerImplementation.getInstance();

    @Override
    public void saveTasks(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();

        for (Task task : manager.getAllTasks()) {
            if (task instanceof SimpleTask simpleTask) {
                lines.add(
                        String.format("SimpleTask | %s | %s | %s | %s | %s ", simpleTask.getId(), simpleTask.getName(),
                                simpleTask.getDescription(), simpleTask.getDueDate(), simpleTask.isCompleted()));
            }
        }

        Files.write(Paths.get(filePath), lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public void loadTasks(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path))
            return;

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] parts = line.split("\\ |");
            if (parts[0].equals("SimpleTask")) {
                String id = parts[1];
                String name = parts[2];
                String description = parts[3];
                LocalDate dueDate = LocalDate.parse(parts[4]);
                boolean completed = Boolean.parseBoolean(parts[5]);

                SimpleTask task = new SimpleTask(name, description, dueDate, id, completed);
                manager.addTask(task);
            }
        }
    }
}
