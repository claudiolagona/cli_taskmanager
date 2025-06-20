package com.cli_taskmanager.core;

import com.cli_taskmanager.observer.TaskObserver;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

class SimpleTaskTest {
    @Test
    void testObserverCalledBySimpleTask() {
        TaskObserver observer = mock(TaskObserver.class);

        SimpleTask task = new SimpleTask("Name", "Description", LocalDate.now());
        task.addObserver(observer);

        task.execute();

        verify(observer, times(1)).onTaskUpdate(eq(task), contains("completed"));
    }
}
