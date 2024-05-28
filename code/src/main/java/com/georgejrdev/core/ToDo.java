package com.georgejrdev.core;

import java.util.List;
import java.util.Map;

import com.georgejrdev.core.interfaces.ToDoInterface;;


public class ToDo implements ToDoInterface{

    private String pathSave;
    private String os;
    private ManipulateJsonFile mJsonFile;

    public ToDo(){
        getCorrectPath();
        mJsonFile = new ManipulateJsonFile(this.pathSave);
    }


    @Override
    public void createNewTask(String content){
        mJsonFile.addItemInJsonFile(content);
    }

    @Override
    public void checkTask(int id, boolean newState){
        mJsonFile.updateItemInJsonFile(id, newState);
    }

    @Override
    public void showTasks(){
        mJsonFile.createNewJsonFile();
        
        List<Map<String,Object>> content = mJsonFile.getContentJsonFile();
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

    private void getCorrectPath(){
        this.os = System.getProperty("os.name");

        if (this.os != null && this.os.toLowerCase().contains("linux")){
                this.pathSave = System.getenv("HOME") + "/ToolKit-dev/save/ToDoSave.json";

        } else {
            String appData = System.getenv("APPDATA");
            this.pathSave = appData + "\\toolkit-dev\\save\\ToDoSave.json";
        }
    } 
}
