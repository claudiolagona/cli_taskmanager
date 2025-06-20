package com.cli_taskmanager.observer;

import com.cli_taskmanager.core.Task;

public interface TaskObserver {
    void onTaskUpdate(Task task, String message);
}
