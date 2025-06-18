package com.cli_taskmanager;

import java.time.LocalDate;

import com.cli_taskmanager.core.CompositeTask;
import com.cli_taskmanager.core.SimpleTask;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        CompositeTask project = new CompositeTask("Sviluppo App", "Task composti");

        SimpleTask task1 = new SimpleTask("Analisi", "Raccogliere requisiti", LocalDate.now());
        SimpleTask task2 = new SimpleTask("Design", "Progettare l'architettura", LocalDate.now());

        project.addSubTask(task1);
        project.addSubTask(task2);

        project.execute();
    }
}
