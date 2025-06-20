package com.cli_taskmanager.strategy;

import com.cli_taskmanager.core.Task;
import java.util.List;

public interface TaskSortStrategy {
    List<Task> sort(List<Task> tasks);
}
