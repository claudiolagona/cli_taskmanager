package com.cli_taskmanager.manager;

import com.cli_taskmanager.core.SimpleTask;
import com.cli_taskmanager.core.Task;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerPersistenceTest {
    private TaskManagerImplementation manager;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        manager = TaskManagerImplementation.getInstance();
        manager.clearAllTasks();

        tempFile = Files.createTempFile("tasks_test", ".json");

        setPrivatePathField(tempFile);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testSaveAndLoadTasks() throws IOException {
        Task task1 = new SimpleTask("Test 1", "Desc1", LocalDate.now());
        Task task2 = new SimpleTask("Test 2", "Desc2", LocalDate.now().plusDays(1));
        manager.addTask(task1);
        manager.addTask(task2);

        manager.saveTasksToFile();
        manager.clearAllTasks();

        assertEquals(0, manager.getAllTasks().size());

        manager.loadTasksFromFile();
        List<Task> loaded = manager.getAllTasks();

        assertEquals(2, loaded.size());
        assertTrue(loaded.stream().anyMatch(task -> task.getName().equals("Test 1")));
        assertTrue(loaded.stream().anyMatch(task -> task.getName().equals("Test 2")));
    }

    private void setPrivatePathField(Path path) {
        try {
            var field = TaskManagerImplementation.class.getDeclaredField("savePath");
            field.setAccessible(true);
            field.set(manager, path);
        } catch (Exception e) {
            fail("Failed to set custom savePath in TaskManagerImplementation: " + e.getMessage());
        }
    }
}
