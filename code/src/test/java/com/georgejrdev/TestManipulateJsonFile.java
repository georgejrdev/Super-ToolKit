package com.georgejrdev;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import java.util.Map;

import com.georgejrdev.core.ManipulateJsonFile;


public class TestManipulateJsonFile {

    private ManipulateJsonFile mJsonFile;

    @Before
    public void setUp(){
        mJsonFile = new ManipulateJsonFile("./test.json");
        mJsonFile.createNewJsonFile();
    }

    @Test
    public void testCreateNewJsonFile(){
        mJsonFile.createNewJsonFile();
    }

    @Test
    public void testAddItemInJsonFile(){
        mJsonFile.addItemInJsonFile("Hey");
    }

    @Test
    public void testUpdateItemInJsonFile(){
        mJsonFile.addItemInJsonFile("Hi");
        mJsonFile.updateItemInJsonFile(0, false);
    }

    @Test
    public void testGetContentJsonFile(){
        mJsonFile.addItemInJsonFile("Hello");
        mJsonFile.addItemInJsonFile("Hola");
        List<Map<String,Object>> content = mJsonFile.getContentJsonFile();

        assertNotNull(content);
        assertFalse(content.isEmpty());
    }
}