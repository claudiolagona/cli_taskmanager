package com.cli_taskmanager.iterator;

import com.cli_taskmanager.core.Task;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CompositeTaskIterator implements Iterator<Task> {
    private final List<Task> tasks;
    private int index = 0;

    public CompositeTaskIterator(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean hasNext() {
        return index < tasks.size();
    }

    @Override
    public Task next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more subtask in this task");
        }
        return tasks.get(index++);
    }
}
