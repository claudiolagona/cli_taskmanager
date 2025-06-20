package com.cli_taskmanager.builder;

import com.cli_taskmanager.core.CompositeTask;
import com.cli_taskmanager.core.Task;

import java.util.ArrayList;
import java.util.List;

public class CompositeTaskBuilder {
    private String name;
    private String description;
    private final List<Task> subTasks = new ArrayList<>();

    public CompositeTaskBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CompositeTaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CompositeTaskBuilder addSubTask(Task task) {
        this.subTasks.add(task);
        return this;
    }

    public CompositeTask build() {
        CompositeTask composite = new CompositeTask(name, description);
        subTasks.forEach(composite::addSubTask);
        return composite;
    }
}
