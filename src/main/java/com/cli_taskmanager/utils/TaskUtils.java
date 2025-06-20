package com.cli_taskmanager.utils;

import com.cli_taskmanager.core.SimpleTask;
import com.cli_taskmanager.core.Task;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskUtils {
    public static List<Task> getIncompleteTasks(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
    }

    public static List<Task> getTasksSortedByName(List<Task> tasks) {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getName))
                .collect(Collectors.toList());
    }

    public static long countCompletedTasks(List<Task> tasks) {
        return tasks.stream()
                .filter(Task::isCompleted)
                .count();
    }

    public static Map<Boolean, List<Task>> groupByCompletion(List<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(Task::isCompleted));
    }

    public static void printExpiredTasks(List<Task> tasks) {
        tasks.stream()
            .filter(task -> task instanceof SimpleTask)
            .map(task -> (SimpleTask) task)
            .filter(t -> !t.isCompleted() && t.getDueDate().isBefore(LocalDate.now()))
            .forEach(t -> System.out.println("Task expired: " + t.getName()));
    }
}
