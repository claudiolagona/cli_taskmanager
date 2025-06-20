package com.cli_taskmanager.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.cli_taskmanager.iterator.CompositeTaskIterator;

public class CompositeTask implements Task, Iterable<Task> {
    private final String id;
    private final String name;
    private final String description;
    private final List<Task> subTasks = new ArrayList<>();

    public CompositeTask(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    public void addSubTask(Task task) {
        subTasks.add(task);
    }

    public void removeSubTask(Task task) {
        subTasks.remove(task);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isCompleted() {
        return subTasks.stream().allMatch(Task::isCompleted);
    }

    @Override
    public double getProgress() {
        if (subTasks.isEmpty())
            return 0.0;

        double total = 0;
        for (Task task : subTasks) {
            total += task.getProgress();
        }

        return total / subTasks.size();
    }

    @Override
    public void execute() {
        for (Task task : subTasks) {
            task.execute();
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return new CompositeTaskIterator(subTasks);
    }

    public List<Task> getSubTasks() {
        return new ArrayList<>(subTasks);
    }
}
