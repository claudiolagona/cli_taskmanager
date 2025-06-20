package com.cli_taskmanager.strategy;

import com.cli_taskmanager.core.Task;
import com.cli_taskmanager.core.SimpleTask;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DueDateSortStrategy implements TaskSortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> task instanceof SimpleTask)
                .map(task -> (SimpleTask) task)
                .sorted(Comparator.comparing(SimpleTask::getDueDate))
                .collect(Collectors.toList());
    }
}
