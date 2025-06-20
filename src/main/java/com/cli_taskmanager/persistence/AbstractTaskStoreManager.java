package com.cli_taskmanager.persistence;

import com.cli_taskmanager.core.Task;
import com.cli_taskmanager.exceptions.TaskManagerException;

import java.nio.file.Path;
import java.util.List;

public abstract class AbstractTaskStoreManager implements FileStoreManager {
    @Override
    public final void saveTasks(List<Task> tasks, Path filePath) throws TaskManagerException {
        preProcess(tasks);
        doSave(tasks, filePath);
        postProcess(tasks);
    }

    @Override
    public final List<Task> loadTasks(Path filePath) throws TaskManagerException {
        preLoad(filePath);
        List<Task> tasks = doLoad(filePath);
        postLoad(tasks);
        return tasks;
    }

    protected void preProcess(List<Task> tasks) {}
    protected void postProcess(List<Task> tasks) {}
    protected void preLoad(Path filePath) {}
    protected void postLoad(List<Task> tasks) {}

    protected abstract void doSave(List<Task> tasks, Path filePath) throws TaskManagerException;
    protected abstract List<Task> doLoad(Path filePath) throws TaskManagerException;
}
