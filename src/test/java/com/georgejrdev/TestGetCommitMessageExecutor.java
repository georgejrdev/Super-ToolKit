package com.georgejrdev;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.georgejrdev.executors.GetCommitMessageExecutor;


public class TestGetCommitMessageExecutor {

    @Test
    public void testGetCommitMessageSuccess() throws Exception {
        String description = "Add PNG to JPEG conversion class with unit tests";
        
        boolean result = GetCommitMessageExecutor.getCommitMessage(description);
        assertEquals(true, result);
    }
}