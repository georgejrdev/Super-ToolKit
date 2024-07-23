package com.georgejrdev.auxiliar.processing.interfaces;

import java.util.List;
import java.util.Map;


public interface ManipulateJsonFile {
    void createNewJsonFile();
    void addItemInJsonFile(String content); 
    void updateItemInJsonFile(int id, boolean newStatus);
    void deleteItemInJsonFile(int id);
    List<Map<String, Object>> getContentJsonFile();
}
