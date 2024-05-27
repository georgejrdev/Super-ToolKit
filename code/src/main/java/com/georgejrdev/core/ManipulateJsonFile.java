package com.georgejrdev.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

import com.georgejrdev.core.interfaces.ManipulateJsonFileInterface;


public class ManipulateJsonFile implements ManipulateJsonFileInterface {
    
    private String path;
    private boolean fileExist;
    private List<Map<String, Object>> content;

    public ManipulateJsonFile(String path) {
        this.path = path;
        this.fileExist = false;
        this.content = new ArrayList<>();
    }

    
    @Override
    public void createNewJsonFile() {
        File file = new File(this.path);

        if (!file.exists()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String emptyJsonArray = gson.toJson(new Object[]{});

            try (FileWriter writer = new FileWriter(this.path)) {
                writer.write(emptyJsonArray);
                this.fileExist = true;

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            this.fileExist = true;
        }
    }

    @Override
    public void addItemInJsonFile(String content) {
        if (!this.fileExist) {
            createNewJsonFile();
        }
        
        List<Map<String, Object>> currentContent = getContentJsonFile();

        Map<String, Object> newItem = new HashMap<>();
        newItem.put("id", currentContent.size());
        newItem.put("content", content);
        newItem.put("state", true);

        currentContent.add(newItem);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(this.path)) {
            gson.toJson(currentContent, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateItemInJsonFile(int id, boolean newStatus) {
        List<Map<String, Object>> currentContent = getContentJsonFile();

        boolean found = false;
        for (Map<String, Object> item : currentContent) {
            if (item.get("id") instanceof Integer && (Integer) item.get("id") == id) {
                item.put("state", newStatus);
                found = true;
                break;
            }
        }

        if (found) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter(this.path)) {
                gson.toJson(currentContent, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Item with ID " + id + " not found.");
        }
    }

    @Override
    public List<Map<String, Object>> getContentJsonFile() {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Map<String, Object>>>(){}.getType();

        try (FileReader reader = new FileReader(this.path)) {
            this.content = gson.fromJson(reader, listType);

            if (this.content != null) {
                for (Map<String, Object> item : this.content) {
                    Object idObj = item.get("id");
                    if (idObj instanceof Double) {
                        item.put("id", ((Double) idObj).intValue());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (this.content == null) {
            this.content = new ArrayList<>();
        }

        return this.content;
    }
}