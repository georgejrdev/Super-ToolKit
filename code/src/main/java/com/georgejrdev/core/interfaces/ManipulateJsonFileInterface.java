package com.georgejrdev.core.interfaces;

import java.util.List;
import java.util.Map;


public interface ManipulateJsonFileInterface {
    void createNewJsonFile();
    void addItemInJsonFile(String content); 
    void updateItemInJsonFile(int id, boolean newStatus);
    List<Map<String, Object>> getContentJsonFile();
}
