package com.cli_taskmanager.core;

public interface Task {
    String getId();

    String getName();

    String getDescription();

    boolean isCompleted();

    double getProgress();

    void execute();
}
