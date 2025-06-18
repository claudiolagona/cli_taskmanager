package com.cli_taskmanager.factory;

import org.junit.jupiter.api.Test;

import com.cli_taskmanager.core.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskFactoryTest {
    private final TaskFactory factory = new TaskFactoryImplementation();

    @Test
    void testCreateSimpleTask() {
        Task task = factory.createTask(TaskType.SIMPLE, "Read", "Read a book");
        assertTrue(task instanceof SimpleTask);
        assertEquals("Read", task.getName());
    }

    @Test
    void testCreateCompositeTask() {
        Task task = factory.createTask(TaskType.COMPOSITE, "Project", "Main Project");
        assertTrue(task instanceof CompositeTask);
    }

    @Test
    void testInvalidTypeThrowsException() {
        Exception exception = assertThrows(NullPointerException.class,
                () -> factory.createTask(null, "X", "Y"));
        assertTrue(exception.getMessage().contains("null"));
    }
}
