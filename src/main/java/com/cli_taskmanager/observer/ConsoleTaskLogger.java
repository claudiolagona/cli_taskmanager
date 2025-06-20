package com.cli_taskmanager.observer;

import com.cli_taskmanager.core.Task;

public class ConsoleTaskLogger implements TaskObserver {
    @Override
    public void onTaskUpdate(Task task, String message) {
        System.out.println("[NOTIFY] " + message + " | Task: " + task.getName());
    }
}
