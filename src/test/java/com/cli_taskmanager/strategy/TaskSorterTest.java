package com.cli_taskmanager.strategy;

import com.cli_taskmanager.core.SimpleTask;
import com.cli_taskmanager.core.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class TaskSorterTest {
    private List<Task> tasks;
    
    @BeforeEach
    void setUp() {
        tasks = new ArrayList<>();
        tasks.add(new SimpleTask("B. Scrivere", "Documentazione", LocalDate.of(2025, 6, 28)));
        tasks.add(new SimpleTask("A. Leggere", "Design Patterns", LocalDate.of(2025, 6, 20)));
        tasks.add(new SimpleTask("C. Testare", "JUnit", LocalDate.of(2025, 6, 25)));
    }

    @Test
    void testSortByName() {
        TaskSorter sorter = new TaskSorter();
        sorter.setStrategy(new NameSortStrategy());
        List<Task> sorted = sorter.sort(tasks);

        assertEquals("A. Leggere", sorted.get(0).getName());
        assertEquals("B. Scrivere", sorted.get(1).getName());
        assertEquals("C. Testare", sorted.get(2).getName());
    }

    @Test
    void testSortByDueDate() {
        TaskSorter sorter = new TaskSorter();
        sorter.setStrategy(new DueDateSortStrategy());
        List<Task> sorted = sorter.sort(tasks);

        assertEquals("A. Leggere", sorted.get(0).getName());
        assertEquals("C. Testare", sorted.get(1).getName());
        assertEquals("B. Scrivere", sorted.get(2).getName());
    }

    @Test
    void testSortByCompletion() {
        tasks.get(1).execute();

        TaskSorter sorter = new TaskSorter();
        sorter.setStrategy(new CompletionSortStrategy());
        List<Task> sorted = sorter.sort(tasks);

        assertFalse(sorted.get(0).isCompleted());
        assertFalse(sorted.get(1).isCompleted());
        assertTrue(sorted.get(2).isCompleted());
        assertEquals("A. Leggere", sorted.get(2).getName());
    }
}
