package com.cli_taskmanager.persistence;

import com.cli_taskmanager.core.SimpleTask;
import com.cli_taskmanager.core.Task;
import com.cli_taskmanager.exceptions.TaskManagerException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonFileStoreManager extends AbstractTaskStoreManager {
    private final Gson gson;

    public JsonFileStoreManager() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfText,
                        context) -> LocalDate.parse(json.getAsString()))
                .setPrettyPrinting().create();
    }

    @Override
    protected void doSave(List<Task> tasks, Path filePath) throws TaskManagerException {
        List<SimpleTask> simpleTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof SimpleTask) {
                simpleTasks.add((SimpleTask) task);
            }
        }

        try {
            String json = gson.toJson(simpleTasks);
            Files.writeString(filePath, json);
        } catch (IOException e) {
            throw new TaskManagerException("Error while saving tasks on file: " + filePath, e);
        }
        
    }

    @Override
    protected List<Task> doLoad(Path filePath) throws TaskManagerException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        try {
            String json = Files.readString(filePath);
            Type taskListType = new TypeToken<List<SimpleTask>>() {}.getType();
            return gson.fromJson(json, taskListType);
        } catch (IOException | JsonSyntaxException e) {
            throw new TaskManagerException("Error while loading tasks from file: " + filePath, e);
        }
    }

    @Override
    protected void postLoad(List<Task> tasks) {
        System.out.println("Uploaded " + tasks.size() + " files");
    }
}
