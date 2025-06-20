package com.cli_taskmanager.builder;

import com.cli_taskmanager.core.Project;
import com.cli_taskmanager.core.SimpleTask;
import com.cli_taskmanager.core.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectBuilderTest {
    @Test
    void testBuildProject() {
        Task task1 = new SimpleTask("Task 1", "Description", LocalDate.now());
        Task task2 = new SimpleTask("Task 2", "Description", LocalDate.now());

        Project project = new ProjectBuilder()
                .setName("Project")
                .setDescription("Description")
                .addTask(task1)
                .addTask(task2)
                .build();

        assertEquals("Project", project.getName());
        assertEquals("Description", project.getDescription());
        assertEquals(2, project.getTasks().size());

        task1.execute();
        task2.execute();

        assertTrue(project.isCompleted());
        assertEquals(1, project.getProgress());
    }
}
