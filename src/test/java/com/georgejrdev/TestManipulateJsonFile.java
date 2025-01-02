package com.georgejrdev;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import java.util.Map;
import com.georgejrdev.utils.files.ManipulateJsonFile;

public class TestManipulateJsonFile {

    private ManipulateJsonFile mJsonFile;
    
    @Before
    public void setUp(){
        mJsonFile = new ManipulateJsonFile("./test.json", false);
        mJsonFile.createNewJsonFile();
    }

    @Test
    public void testCreateNewJsonFile(){
        mJsonFile.createNewJsonFile();
    }

    @Test
    public void testAddItemInJsonFile(){
        mJsonFile.addItemInJsonFile(null, "Hey");
    }

    @Test
    public void testUpdateItemInJsonFile(){
        mJsonFile.addItemInJsonFile(null, "Hi");
        
        List<Map<String,Object>> content = mJsonFile.getContentJsonFile();
        int id = ((content.size()) -1);
        
        mJsonFile.updateItemInJsonFile(id, false);
    }

    @Test
    public void testGetContentJsonFile(){
        mJsonFile.addItemInJsonFile(null, "Hello");
        mJsonFile.addItemInJsonFile(null, "Hola");

        List<Map<String,Object>> content = mJsonFile.getContentJsonFile();

        assertNotNull(content);
        assertFalse(content.isEmpty());
    }

    @Test
    public void testDeleteItemInJsonFile(){
        mJsonFile.addItemInJsonFile(null, "Hello");
        mJsonFile.addItemInJsonFile(null, "Hola");

        List<Map<String,Object>> content = mJsonFile.getContentJsonFile();
        int id = ((content.size()) -1);
 
        mJsonFile.deleteItemInJsonFile(id);
    }
}