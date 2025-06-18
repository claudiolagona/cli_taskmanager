package com.cli_taskmanager.factory;

import java.time.LocalDate;

import com.cli_taskmanager.core.*;

public class TaskFactoryImplementation implements TaskFactory {
    @Override
    public Task createTask(TaskType type, String name, String description, String id, boolean completed) {
        switch (type) {
            case SIMPLE:
                return new SimpleTask(name, description, LocalDate.now(), id, completed);
            case COMPOSITE:
                return new CompositeTask(name, description);
            default:
                throw new IllegalArgumentException("Task's tyoe not supported");
        }
    }

    @Override
    public Task createTask(TaskType type, String name, String description) {
        switch (type) {
            case SIMPLE:
                return new SimpleTask(name, description, LocalDate.now());
            case COMPOSITE:
                return new CompositeTask(name, description);
            default:
                throw new IllegalArgumentException("Task's type not supported");
        }
    }
}
