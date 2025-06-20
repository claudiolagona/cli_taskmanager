package com.cli_taskmanager.builder;

import com.cli_taskmanager.core.CompositeTask;
import com.cli_taskmanager.core.SimpleTask;
import com.cli_taskmanager.core.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CompositeTaskBuilderTest {
    @Test
    void testBuildCompositeTask() {
        Task task1 = new SimpleTask("Task 1", "Description", LocalDate.now());
        Task task2 = new SimpleTask("Task 2", "Description", LocalDate.now());

        CompositeTask composite = new CompositeTaskBuilder()
                .setName("Composite")
                .setDescription("Composite Description")
                .addSubTask(task1)
                .addSubTask(task2)
                .build();
        
        assertEquals("Composite", composite.getName());
        assertEquals(2, composite.getSubTasks().size());
        assertTrue(composite.getSubTasks().contains(task1));
        assertTrue(composite.getSubTasks().contains(task2));
    }
}
