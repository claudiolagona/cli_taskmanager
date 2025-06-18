package com.cli_taskmanager.persistence;

import com.cli_taskmanager.core.SimpleTask;
import com.cli_taskmanager.core.Task;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JsonFileStoreManagerTest {
    private JsonFileStoreManager storeManager;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        storeManager = new JsonFileStoreManager();
        tempFile = Files.createTempFile("tasks", ".json");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testSaveAndLoadSimpleTasks() throws IOException {
        SimpleTask task1 = new SimpleTask("Task 1", "Descrizione 1", LocalDate.of(2025, 6, 17));
        SimpleTask task2 = new SimpleTask("Task 2", "Descrizione 2", LocalDate.of(2025, 6, 18));
        task2.execute();

        List<Task> taskToSave = List.of(task1, task2);
        storeManager.saveTasks(taskToSave, tempFile);

        List<Task> loadedTasks = storeManager.loadTasks(tempFile);

        assertEquals(2, loadedTasks.size());
        assertEquals("Task 1", loadedTasks.get(0).getName());
        assertFalse(loadedTasks.get(0).isCompleted());

        assertEquals("Task 2", loadedTasks.get(1).getName());
        assertTrue(loadedTasks.get(1).isCompleted());
    }

    @Test
    void testLoadFromNonExistentFile() throws IOException {
        Path nonExistentPath = Paths.get("tasks.json");
        List<Task> tasks = storeManager.loadTasks(nonExistentPath);
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }
}
