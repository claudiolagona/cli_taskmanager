package com.cli_taskmanager.manager;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.NoSuchElementException;
import java.util.Optional;

import com.cli_taskmanager.core.Task;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    private TaskManager manager;

    @BeforeEach
    void setUp() {
        manager = TaskManagerImplementation.getInstance();
        manager.clearAllTasks();
    }

    @Test
    void testAddAndGetTask() {
        Task mockTask = Mockito.mock(Task.class);
        Mockito.when(mockTask.getId()).thenReturn("task1");

        manager.addTask(mockTask);

        Optional<Task> retrieved = manager.getTaskById("task1");
        assertTrue(retrieved.isPresent());
        assertEquals("task1", retrieved.get().getId());
    }

    @Test
    void testExecuteTask() {
        Task task = Mockito.mock(Task.class);
        Mockito.when(task.getId()).thenReturn("task3");

        manager.addTask(task);
        manager.executeTask("task3");

        Mockito.verify(task).execute();
    }

    @Test
    void testRemoveTask() {
        Task mockTask = Mockito.mock(Task.class);
        Mockito.when(mockTask.getId()).thenReturn("task2");

        manager.addTask(mockTask);
        manager.removeTask("task2");

        assertFalse(manager.getTaskById("task2").isPresent());
    }

    @Test
    void testClearAllTasks() {
        Task task1 = Mockito.mock(Task.class);
        Task task2 = Mockito.mock(Task.class);
        Mockito.when(task1.getId()).thenReturn("task1");
        Mockito.when(task2.getId()).thenReturn("task2");

        manager.addTask(task1);
        manager.addTask(task2);

        manager.clearAllTasks();

        assertEquals(0, manager.getAllTasks().size());
    }

    @Test
    void testExecuteNonExistingTaskThrowException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            manager.executeTask("nonexisting");
        });

        assertTrue(exception.getMessage().contains("nonexisting"));
    }
}
