package com.cli_taskmanager.thread;

import com.cli_taskmanager.core.SimpleTask;
import com.cli_taskmanager.core.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

class DeadlineMonitorTest {
    @Test
    void testMonitorDetectsExpiringTasks() throws InterruptedException {
        SimpleTask task = mock(SimpleTask.class);

        when(task.isCompleted()).thenReturn(false);
        when(task.getDueDate()).thenReturn(LocalDate.now());
        when(task.getName()).thenReturn("Task");

        List<Task> tasks = List.of(task);

        DeadlineMonitor monitor = new DeadlineMonitor(tasks, 1);

        monitor.startMonitoring();

        Thread.sleep(2100);

        monitor.stopMonitoring();
        monitor.join();

        verify(task, atLeastOnce()).isCompleted();
        verify(task, atLeastOnce()).getDueDate();
        verify(task, atLeastOnce()).getName();
    }
}
