package com.cli_taskmanager.exceptions;

public class TaskManagerException extends Exception {
    public TaskManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskManagerException(String message) {
        super(message);
    }
}
