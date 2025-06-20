package com.cli_taskmanager.utils;

import com.cli_taskmanager.core.SimpleTask;
import com.cli_taskmanager.core.Task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskUtilsTest {
    private Task task1;
    private Task task2;
    private List<Task> tasks;

    @BeforeEach
    void setUp() {
        task1 = new SimpleTask("Task 1", "Description", LocalDate.now());
        task2 = new SimpleTask("Task 2", "Description", LocalDate.now());
        task1.execute();

        tasks = List.of(task1, task2);
    }

    @Test
    void testGetIncompleteTasks() {
        List<Task> result = TaskUtils.getIncompleteTasks(tasks);
        assertEquals(1, result.size());
        assertEquals("Task 2", result.get(0).getName());
    }

    @Test
    void testGetTasksSortByName() {
        List<Task> result = TaskUtils.getTasksSortedByName(tasks);
        assertEquals("Task 1", result.get(0).getName());
        assertEquals("Task 2", result.get(1).getName());
    }

    @Test
    void testCountCompletedTasks() {
        long count = TaskUtils.countCompletedTasks(tasks);
        assertEquals(1, count);
    }

    @Test
    void testGroupByCompletion() {
        Map<Boolean, List<Task>> group = TaskUtils.groupByCompletion(tasks);
        assertEquals(1, group.get(true).size());
        assertEquals(1, group.get(false).size());
    }
}
