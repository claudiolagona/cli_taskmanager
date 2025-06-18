package com.cli_taskmanager.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskTest {
    private Task mockTask1;
    private Task mockTask2;
    private CompositeTask composite;

    @BeforeEach
    void setUp() {
        mockTask1 = mock(Task.class);
        mockTask2 = mock(Task.class);
        composite = new CompositeTask("Mock Composite", "Group of mocked tasks");
    }

    @Test
    void testCompositeTaskProgress() {
        when(mockTask1.getProgress()).thenReturn(0.0);
        when(mockTask2.getProgress()).thenReturn(0.0);

        composite.addSubTask(mockTask1);
        composite.addSubTask(mockTask2);

        assertEquals(0.0, composite.getProgress(), 0.01);

        when(mockTask1.getProgress()).thenReturn(1.0);
        when(mockTask2.getProgress()).thenReturn(0.0);
        assertEquals(0.5, composite.getProgress(), 0.01);

        when(mockTask2.getProgress()).thenReturn(1.0);
        assertEquals(1.0, composite.getProgress(), 0.01);
    }

    @Test
    void testCompositeIsCompleted() {
        when(mockTask1.isCompleted()).thenReturn(true);
        when(mockTask2.isCompleted()).thenReturn(true);

        composite.addSubTask(mockTask1);
        composite.addSubTask(mockTask2);

        assertTrue(composite.isCompleted());

        when(mockTask2.isCompleted()).thenReturn(false);
        assertFalse(composite.isCompleted());
    }

    @Test
    void testCompositeExecuteDelegatesToSubTasks() {
        composite.addSubTask(mockTask1);
        composite.addSubTask(mockTask2);

        composite.execute();

        verify(mockTask1).execute();
        verify(mockTask2).execute();
    }
}
