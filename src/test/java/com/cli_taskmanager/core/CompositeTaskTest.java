package com.cli_taskmanager.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompositeTaskTest {
    private CompositeTask composite;
    private SimpleTask task1;
    private SimpleTask task2;

    @BeforeEach
    void setUp() {
        composite = new CompositeTask("Composite Title", "Description");
        task1 = new SimpleTask("Task 1", "Description", LocalDate.now());
        task2 = new SimpleTask("Task 2", "Description", LocalDate.now());

        composite.addSubTask(task1);
        composite.addSubTask(task2);
    }

    @Test
    void testProgressWithSubTasks() {
        assertEquals(0.0, composite.getProgress(), 0.01);

        task1.execute();
        assertEquals(0.5, composite.getProgress(), 0.01);

        task2.execute();
        assertEquals(1.0, composite.getProgress(), 0.01);
    }

    @Test
    void testAddAndRetrieveSubTasks() {
        assertTrue(composite.getSubTasks().contains(task1));
        assertTrue(composite.getSubTasks().contains(task2));
        assertEquals(2, composite.getSubTasks().size());
    }
}
