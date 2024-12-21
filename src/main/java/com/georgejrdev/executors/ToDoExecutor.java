package com.georgejrdev.executors;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.georgejrdev.utils.files.ManipulateJsonFile;
import com.georgejrdev.utils.helper.AppLogger;

public class ToDoExecutor {
    
    private String pathSave;
    private String os;
    private ManipulateJsonFile manipulateFile;
    private static final Logger logger = AppLogger.getLogger();

    public ToDoExecutor(){
        setCorrectPath();
        this.manipulateFile = new ManipulateJsonFile(getPathSave());
    }

    public void createNewTask(String content){
        this.manipulateFile.addItemInJsonFile(content);
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
                System.out.printf("ID: %d [ ] - %s%n",itemId,itemContent);
            } else {
                System.out.printf("ID: %d [x] - %s%n",itemId,itemContent);
            }
        }
    }

    public void deleteTask(int id){
        this.manipulateFile.deleteItemInJsonFile(id);
        showTasks();
        logger.info("Task deleted - ID: "+id);
    }

    private void setCorrectPath(){
        setOs(System.getProperty("os.name"));

        if (getOs() != null && getOs().toLowerCase().contains("linux")){
                setPathSave(System.getenv("HOME") + "/Super-ToolKit/save/ToDoSave.json");;
                
        } else {
            String appData = System.getenv("APPDATA");
            setPathSave(appData + "\\Super-ToolKit\\save\\ToDoSave.json");
        }
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

    private String getPathSave(){
        return this.pathSave;
    }

    private void setPathSave(String pathSave){
        this.pathSave = pathSave;
    }

    private String getOs(){
        return this.os;
    }

    private void setOs(String os){
        this.os = os;
    }
}
