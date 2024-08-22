package com.georgejrdev;

import com.georgejrdev.auxiliar.processing.MarkdownImpl;
import com.georgejrdev.auxiliar.processing.interfaces.Markdown;

public class Main {
    public static void main(String[] args){

        // Temporary example of how to start HotReload for markdown, will change from here after doing CLI interface
        // Before changing, create unit test for it
        // Remember to remove the reload script after closing
        
        String fileToWatch = "/home/george/Code/Projetos/Markdown-To-Html/README.md";
        Markdown markdown = new MarkdownImpl(fileToWatch);
        markdown.parseHtml(true);
    }
}