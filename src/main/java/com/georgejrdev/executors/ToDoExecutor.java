package com.georgejrdev.executors;

import java.util.List;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.georgejrdev.utils.files.ManipulateJsonFile;
import com.georgejrdev.utils.helper.AppLogger;

import static com.georgejrdev.DefaultValues.*;

public class ToDoExecutor {
    
    private ManipulateJsonFile manipulateFile;
    private static final Logger logger = AppLogger.getLogger();

    public ToDoExecutor(){
        File dir = new File(DIR_SAVE);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        this.manipulateFile = new ManipulateJsonFile(DIR_SAVE+File.separator+"ToDoSave.json", false);
    }

    public void createNewTask(String content){
        this.manipulateFile.addItemInJsonFile(null, content);
        showTasks();
        logger.info("New task created - Content: "+content);
    }

    public void checkTask(int id, boolean newState){
        this.manipulateFile.updateItemInJsonFile(id, newState);
        showTasks();
        logger.info("Task checked - ID: "+id + " New state: "+newState);
    }

    public void showTasks(){
        this.manipulateFile.createNewJsonFile();
            
        List<Map<String,Object>> content = orderTasks(this.manipulateFile.getContentJsonFile());

        for(Map<String,Object> item : content) {
            int itemId = (int) item.get("id");
            boolean itemState = (boolean) item.get("state");
            String itemContent = (String) item.get("content");
            
            if (itemState){
                System.out.printf(ANSI_YELLOW+"ID: %d [ ] -"+ ANSI_RESET +" %s%n",itemId,itemContent);
            } else {
                System.out.printf(ANSI_BRIGHT_RED+"ID: %d ["+ANSI_RESET+ANSI_RED+"x"+ANSI_RESET+ANSI_BRIGHT_RED+"] -"+ ANSI_RESET +" %s%n",itemId,itemContent);
            }
        }
    }

    public void deleteTask(int id){
        this.manipulateFile.deleteItemInJsonFile(id);
        showTasks();
        logger.info("Task deleted - ID: "+id);
    }

    private List<Map<String,Object>> orderTasks(List<Map<String,Object>> content){
        List<Map<String,Object>> orderContent = new ArrayList<>();

        for(Map<String,Object> item : content) {
            boolean state = (boolean) item.get("state");

            if (state == false){
                orderContent.add(item);

            } else {
                orderContent.add(0,item);
            }
        }

        return orderContent;
    }
}