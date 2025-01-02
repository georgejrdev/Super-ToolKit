package com.georgejrdev.utils.files;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Type;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.georgejrdev.utils.helper.AppLogger;

public class ManipulateJsonFile {
    
    private String path;
    private boolean fileExist;
    private List<Map<String, Object>> content;
    private static final Logger logger = AppLogger.getLogger();
    private boolean isIdString;

    public ManipulateJsonFile(String path, boolean isIdString) {
        this.path = path;
        this.fileExist = false;
        this.content = new ArrayList<>();
        this.isIdString = isIdString;
    }
    
    public void createNewJsonFile() {
        logger.info("Creating new json file at path: " + this.path);
        File file = new File(this.path);

        if (!file.exists()) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String emptyJsonArray = gson.toJson(new Object[]{});

            try (FileWriter writer = new FileWriter(this.path)) {
                writer.write(emptyJsonArray);
                this.fileExist = true;
                logger.info("Json file created at path: " + this.path);

            } catch (IOException e) {
                logger.severe("Error creating new json file at path: " + this.path + " - " + e.getMessage());
            }

        } else {
            this.fileExist = true;
        }
    }

    public void addItemInJsonFile(Object id, String content) {
        if (!this.fileExist) {
            createNewJsonFile();
        }
        
        List<Map<String, Object>> currentContent = getContentJsonFile();

        if (!isIdString) {
            int maxId = 0;
            for (Map<String, Object> item : currentContent) { 
                int currentId = (int) item.get("id"); 
                if (currentId > maxId) {
                    maxId = currentId; 
                } 
            }
            id = maxId + 1;
        }

        Map<String, Object> newItem = new HashMap<>();
        newItem.put("id", id);
        newItem.put("content", content);
        newItem.put("state", true);

        currentContent.add(newItem);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(this.path)) {
            gson.toJson(currentContent, writer);
            logger.info("Item " + content + " added to json file at path: " + this.path);

        } catch (IOException e) {
            e.printStackTrace();
            logger.severe("Error adding " + content + " to json file at path: " + this.path + " - " + e.getMessage());
        }
    }

    public void updateItemInJsonFile(Object id, boolean newStatus) {
        List<Map<String, Object>> currentContent = getContentJsonFile();

        boolean found = false;

        for (Map<String, Object> item : currentContent) {
            if (item.get("id").equals(id)) {
                item.put("state", newStatus);
                found = true;
                break;
            }
        }

        if (found) {
            writeContentToFile(currentContent);
            logger.info("Item with ID " + id + " updated. New status: " + newStatus);
        } else {
            System.out.println("Item with ID " + id + " not found.");
            logger.severe("Item with ID " + id + " not found.");
        }
    }

    public void deleteItemInJsonFile(Object id) {
        List<Map<String, Object>> currentContent = getContentJsonFile();

        boolean found = false;

        for (Map<String, Object> item : currentContent) {
            if (item.get("id").equals(id)) {
                currentContent.remove(item);
                found = true;
                break;
            }
        }

        if (found) {
            writeContentToFile(currentContent);
            logger.info("Item with ID " + id + " deleted.");
        } else {
            System.out.println("Item with ID " + id + " not found.");
            logger.severe("Item with ID " + id + " not found.");
        }
    }

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

            logger.info("Json file read at path: " + this.path);

        } catch (IOException e) {
            e.printStackTrace();
            logger.severe("Error reading json file at path: " + this.path + " - " + e.getMessage());
        }

        if (this.content == null) {
            this.content = new ArrayList<>();
        }

        return this.content;
    }

    private void writeContentToFile(List<Map<String, Object>> content) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(this.path)) {
            gson.toJson(content, writer);
            logger.info("Json file updated at path: " + this.path + " with content: " + content);
        } catch (IOException e) {
            e.printStackTrace();
            logger.severe("Error updating json file at path: " + this.path + " - " + e.getMessage());
        }
    }
}