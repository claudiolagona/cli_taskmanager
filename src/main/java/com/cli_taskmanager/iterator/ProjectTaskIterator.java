package com.cli_taskmanager.iterator;

import com.cli_taskmanager.core.Task;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ProjectTaskIterator implements Iterator<Task> {
    private final List<Task> tasks;
    private int index = 0;

    public ProjectTaskIterator(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean hasNext() {
        return index < tasks.size();
    }

    @Override
    public Task next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more task in this project");
        }
        return tasks.get(index++);
    }
}
