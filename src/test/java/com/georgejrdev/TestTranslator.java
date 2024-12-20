package com.georgejrdev;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.georgejrdev.executors.TranslatorExecutor;
import com.georgejrdev.utils.ia.GeminiRequest;

public class TestTranslator {

    private TranslatorExecutor translatorExecutor;

    @Before
    public void setUp() {
        this.translatorExecutor = new TranslatorExecutor(new GeminiRequest());
    }

    @Test
    public void testGetCommitMessageSuccess() throws Exception {
        String text = "Add PNG to JPEG conversion class with unit tests";
        String language = "ptbr";
        
        boolean result = translatorExecutor.translate(text, language);
        assertEquals(true, result);
    }
}