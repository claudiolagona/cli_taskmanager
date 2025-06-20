package com.cli_taskmanager.manager;

import com.cli_taskmanager.core.Task;
import com.cli_taskmanager.exceptions.TaskManagerException;
import com.cli_taskmanager.persistence.FileStoreManager;
import com.cli_taskmanager.persistence.JsonFileStoreManager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TaskManagerImplementation implements TaskManager {
    private final Map<String, Task> tasks = new HashMap<>();
    private static TaskManagerImplementation instance;

    private final FileStoreManager fileStoreManager;
    private final Path savePath;

    private TaskManagerImplementation() {
        this.fileStoreManager = new JsonFileStoreManager();
        this.savePath = Paths.get("tasks.json");
    }

    public static synchronized TaskManagerImplementation getInstance() {
        if (instance == null) {
            instance = new TaskManagerImplementation();
        }
        return instance;
    }

    @Override
    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void removeTask(String id) {
        tasks.remove(id);
    }

    @Override
    public Optional<Task> getTaskById(String id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void executeTask(String id) {
        Task task = tasks.get(id);
        if (task != null) {
            task.execute();
        } else {
            throw new NoSuchElementException("Task with ID" + id + "not found");
        }
    }

    @Override
    public void clearAllTasks() {
        tasks.clear();
    }

    public void saveTasksToFile() throws TaskManagerException {
        fileStoreManager.saveTasks(getAllTasks(), savePath);
    }

    public void loadTasksFromFile() throws TaskManagerException {
        List<Task> loadedTasks = fileStoreManager.loadTasks(savePath);
        tasks.clear();
        for (Task task : loadedTasks) {
            tasks.put(task.getId(), task);
        }
    }
}
