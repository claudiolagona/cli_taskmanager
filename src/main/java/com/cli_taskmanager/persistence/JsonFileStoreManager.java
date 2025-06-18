package com.cli_taskmanager.persistence;

import com.cli_taskmanager.core.SimpleTask;
import com.cli_taskmanager.core.Task;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JsonFileStoreManager implements FileStoreManager {
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
    public void saveTasks(List<Task> tasks, Path filePath) throws IOException {
        List<SimpleTask> simpleTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof SimpleTask) {
                simpleTasks.add((SimpleTask) task);
            }
        }

        String json = gson.toJson(simpleTasks);
        Files.writeString(filePath, json);
    }

    @Override
    public List<Task> loadTasks(Path filePath) throws IOException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }

        String json = Files.readString(filePath);
        Type taskListType = new TypeToken<List<SimpleTask>>() {
        }.getType();
        return gson.fromJson(json, taskListType);
    }
}
