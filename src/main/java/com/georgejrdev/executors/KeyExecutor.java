package com.georgejrdev.executors;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.georgejrdev.utils.files.ManipulateJsonFile;
import com.georgejrdev.utils.helper.AppLogger;

import static com.georgejrdev.DefaultValues.*;

public class KeyExecutor {

    private ManipulateJsonFile manipulateFile;
    private static final Logger logger = AppLogger.getLogger();

    public KeyExecutor() {
        File dir = new File(DIR_SAVE);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        this.manipulateFile = new ManipulateJsonFile(DIR_SAVE + File.separator + "KeySave.json", true); 
    }

    public void createNewKey(String id, String content) {
        this.manipulateFile.addItemInJsonFile(id, content);
        showKeys();
        logger.info("New key created - Name: " + id + " Content: " + content);
    }

    public void deleteKey(String id) {
        this.manipulateFile.deleteItemInJsonFile(id);
        showKeys();
        logger.info("Key deleted - Name: " + id);
    }

    public void showKeys() {
        this.manipulateFile.createNewJsonFile();
        
        List<Map<String, Object>> content = orderKeys(this.manipulateFile.getContentJsonFile());

        for (Map<String, Object> item : content) {
            String itemId = (String) item.get("id");
            String itemContent = (String) item.get("content");
            
            System.out.printf(ANSI_BRIGHT_CYAN + "Name: %s - " + ANSI_RESET + "%s%n", itemId, itemContent);
        }
    }

    public void showKey(String id) {
        this.manipulateFile.createNewJsonFile();
        
        List<Map<String, Object>> content = this.manipulateFile.getContentJsonFile();

        for (Map<String, Object> item : content) {
            String itemId = (String) item.get("id");
            String itemContent = (String) item.get("content");

            if (itemId.equals(id)) {
                System.out.printf(ANSI_BRIGHT_CYAN + "Name: %s - " + ANSI_RESET + "%s%n", itemId, itemContent);
                return;
            }
        }

        System.out.println("Key with Name " + id + " not found.");
        logger.severe("Key with Name " + id + " not found.");
    }

    private List<Map<String, Object>> orderKeys(List<Map<String, Object>> content) {
        List<Map<String, Object>> orderContent = new ArrayList<>();

        for (Map<String, Object> item : content) {
            orderContent.add(item); 

        }

        return orderContent;
    }
}