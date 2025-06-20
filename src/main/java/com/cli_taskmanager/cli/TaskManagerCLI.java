package com.cli_taskmanager.cli;

import com.cli_taskmanager.builder.ProjectBuilder;
import com.cli_taskmanager.core.*;
import com.cli_taskmanager.persistence.JsonFileStoreManager;
import com.cli_taskmanager.utils.TaskUtils;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class TaskManagerCLI {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Task> allTasks = new ArrayList<>();
    private final List<Project> allProjects = new ArrayList<>();
    private final JsonFileStoreManager storeManager = new JsonFileStoreManager();
    private final Path savePath = Path.of("allTasks.json");

    public void start() {
        System.out.println("Welcome to CLI Task Manager");
        boolean running = true;

        while (running) {
            printMenu();
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> createSimpleTask();
                case "2" -> createProject();
                case "3" -> showTasks();
                case "4" -> showProjects();
                case "5" -> saveTasks();
                case "6" -> loadTasks();
                case "7" -> sortAndFilterMenu();
                case "0" -> {
                    System.out.println("Bye!");
                    running = false;
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    public void printMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("\n1. Create a simple Task");
        System.out.println("\n2. Create a Project");
        System.out.println("\n3. Show all Tasks");
        System.out.println("\n4. Show all Projects");
        System.out.println("\n5. Save all Tasks on a File");
        System.out.println("\n6. Load Tasks from a File");
        System.out.println("\n7. Order / Filter Tasks");
        System.out.println("\n0. Exit");
        System.out.println("\n> Select: ");
    }

    private void createSimpleTask() {
        System.out.println("Name of the Task: ");
        String name = scanner.nextLine();
        System.out.println("Description of the Task: ");
        String description = scanner.nextLine();
        System.out.println("Due date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(scanner.nextLine());

        SimpleTask task = new SimpleTask(name, description, dueDate);
        allTasks.add(task);
        System.out.println("Task successfully created!");
    }

    private void createProject() {
        System.out.println("Name of the Project: ");
        String name = scanner.nextLine();
        System.out.println("Description of the Project: ");
        String description = scanner.nextLine();

        ProjectBuilder builder = new ProjectBuilder().setName(name).setDescription(description);
        while (true) {
            System.out.println("Do you want to add a task to the Project? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            if (!choice.equals("s")) break;

            createSimpleTask();
            Task last = allTasks.get(allTasks.size() - 1);
            builder.addTask(last);
        }

        allProjects.add(builder.build());
        System.out.println("Project successfully created!");
    }

    private void showTasks() {
        if (allTasks.isEmpty()) {
            System.out.println("No tasks available!");
            return;
        }
        System.out.println("Tasks list: ");
        allTasks.forEach(task -> System.out.println("\n- " + task.getName() + " | Is completed: " + task.isCompleted()));
    }

    private void showProjects() {
        if (allProjects.isEmpty()) {
            System.out.println("No projects available!");
            return;
        }
        System.out.println("Projects list: ");
        allProjects.forEach(project -> System.out.println("\n- " + project.getName() + " | Progress: " + project.getProgress() * 100 + "%"));
    }

    private void saveTasks() {
        try {
            storeManager.saveTasks(allTasks, savePath);
            System.out.println("Tasks saved successfully!");
        } catch (Exception e) {
            System.out.println("Error while saving Tasks: " + e.getMessage());
        }
    }

    private void loadTasks() {
        try {
            List<Task> loadedTasks = storeManager.loadTasks(savePath);
            allTasks.clear();
            allTasks.addAll(loadedTasks);
            System.out.println("Tasks loaded successfully!");
        } catch (Exception e) {
            System.out.println("Error while loading Tasks: " + e.getMessage());
        }
    }

    private void sortAndFilterMenu() {
        System.out.println("1. Load uncompleted Tasks");
        System.out.println("2. Load tasks ordered by name");
        System.out.println("3. Load expired tasks");
        System.out.println("> Select: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> TaskUtils.getIncompleteTasks(allTasks).forEach(task -> System.out.println(task.getName()));
            case "2" -> TaskUtils.getTasksSortedByName(allTasks).forEach(task -> System.out.println(task.getName()));
            case "3" -> TaskUtils.printExpiredTasks(allTasks);
            default -> System.out.println("Invalid option!");
        }
    }
}
