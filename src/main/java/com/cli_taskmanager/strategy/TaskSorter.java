package com.cli_taskmanager.strategy;

import java.util.List;

import com.cli_taskmanager.core.Task;

public class TaskSorter {
    private TaskSortStrategy strategy;

    public void setStrategy(TaskSortStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Task> sort(List<Task> tasks) {
        return strategy.sort(tasks);
    }
}
