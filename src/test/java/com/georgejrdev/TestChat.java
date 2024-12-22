package com.georgejrdev;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.georgejrdev.executors.ChatExecutor;
import com.georgejrdev.utils.ia.GeminiRequest;

public class TestChat {

    private ChatExecutor chatExecutor;

    @Before
    public void setUp() {
        this.chatExecutor = new ChatExecutor(new GeminiRequest());
    }

    @Test
    public void testGetCommitMessageSuccess() throws Exception {
        String message = "Eae, como vai?";
        
        boolean result = chatExecutor.getIAResponse(message);
        assertEquals(true, result);
    }
}