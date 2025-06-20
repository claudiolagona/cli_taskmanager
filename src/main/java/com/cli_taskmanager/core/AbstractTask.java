package com.cli_taskmanager.core;

import com.cli_taskmanager.observer.TaskObserver;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTask implements Task {
    private final List<TaskObserver> observers = new ArrayList<>();

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    protected void notifyObservers(String message) {
        for (TaskObserver observer : observers) {
            observer.onTaskUpdate(this, message);
        }
    }
}
