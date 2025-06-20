package com.cli_taskmanager.thread;

import com.cli_taskmanager.core.SimpleTask;
import com.cli_taskmanager.core.Task;

import java.time.LocalDate;
import java.util.List;

public class DeadlineMonitor extends Thread {
    private final List<Task> tasks;
    private final int checkIntervalSeconds;
    private volatile boolean running = true;

    public DeadlineMonitor(List<Task> tasks, int checkIntervalSeconds) {
        this.tasks = List.copyOf(tasks);
        this.checkIntervalSeconds = checkIntervalSeconds;
    }

    public void startMonitoring() {
        this.start();
    }

    public void stopMonitoring() {
        running = false;
        this.interrupt();
    }

    @Override
    public void run() {
        while (running) {
            checkDeadlines();
            try {
                Thread.sleep(checkIntervalSeconds * 1000L);
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    private void checkDeadlines() {
        for (Task task : tasks) {
            if (task instanceof SimpleTask st && !st.isCompleted() && st.getDueDate().isBefore(LocalDate.now().plusDays(1))) {
                System.out.println("[⚠️] Task due to expire: " + st.getName());
            }
        }
    }
}
