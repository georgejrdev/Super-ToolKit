package com.georgejrdev;

import org.junit.Before;
import org.junit.Test;

import com.georgejrdev.auxiliar.processing.MarkdownImpl;
import com.georgejrdev.auxiliar.processing.interfaces.Markdown;

public class TestMarkdown {
    
    private Markdown markdown;

    @Before
    public void setUp(){
        markdown = new MarkdownImpl("./README.md");
    }

    @Test
    public void testMarkdown() {
        markdown.parseHtml(false);
    }

    @Test
    public void testMarkdownWithHotReload() {
        markdown.parseHtml(true);
    }
}
