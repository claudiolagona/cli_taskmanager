package com.cli_taskmanager.strategy;

import com.cli_taskmanager.core.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompletionSortStrategy implements TaskSortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::isCompleted))
                .collect(Collectors.toList());
    }
}
