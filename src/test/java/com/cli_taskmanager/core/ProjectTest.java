package com.cli_taskmanager.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectTest {
    private Task mockTask1;
    private Task mockTask2;
    private Project project;

    @BeforeEach
    void setUp() {
        mockTask1 = mock(Task.class);
        mockTask2 = mock(Task.class);
        when(mockTask1.getId()).thenReturn("1");
        when(mockTask2.getId()).thenReturn("2");

        project = new ProjectImplementation("Test Project", "Description Project");
    }

    @Test
    void testAddAndRemoveTasks() {
        project.addTask(mockTask1);
        project.addTask(mockTask2);

        assertEquals(2, project.getTasks().size());

        project.removeTask("1");
        assertEquals(1, project.getTasks().size());
    }

    @Test
    void testProgressCalculation() {
        when(mockTask1.getProgress()).thenReturn(1.0);
        when(mockTask2.getProgress()).thenReturn(0.0);

        project.addTask(mockTask1);
        project.addTask(mockTask2);

        assertEquals(0.5, project.getProgress(), 0.01);
    }

    @Test
    void testIsCompleted() {
        when(mockTask1.isCompleted()).thenReturn(true);
        when(mockTask2.isCompleted()).thenReturn(false);

        project.addTask(mockTask1);
        project.addTask(mockTask2);

        assertFalse(project.isCompleted());

        when(mockTask2.isCompleted()).thenReturn(true);
        assertTrue(project.isCompleted());
    }

    @Test
    void testExecuteCallsOnAllTasks() {
        project.addTask(mockTask1);
        project.addTask(mockTask2);

        project.execute();

        verify(mockTask1).execute();
        verify(mockTask2).execute();
    }
}
