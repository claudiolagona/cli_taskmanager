package com.cli_taskmanager.iterator;

import com.cli_taskmanager.core.Task;

public interface TaskIterator {
    boolean hasNext();
    Task next();
}
