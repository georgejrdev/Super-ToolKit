package com.georgejrdev;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.georgejrdev.executors.GetCommitMessageExecutor;
import com.georgejrdev.utils.ia.GeminiRequest;

public class TestGetCommitMessageExecutor {

    private GetCommitMessageExecutor getCommitMessageExecutor;

    @Before
    public void setUp() {
        this.getCommitMessageExecutor = new GetCommitMessageExecutor(new GeminiRequest());
    }

    @Test
    public void testGetCommitMessageSuccess() throws Exception {
        String description = "Add PNG to JPEG conversion class with unit tests";
        
        boolean result = getCommitMessageExecutor.getCommitMessage(description);
        assertEquals(true, result);
    }
}