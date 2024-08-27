package com.georgejrdev.utils.parser;

import com.georgejrdev.auxiliar.file.ManipulateFile;
import com.georgejrdev.auxiliar.reload.Parser;


public class MarkdownParser implements Parser{

    private int webSocketPort;
    private ManipulateFile manipulateFile;

    public MarkdownParser(int webSocketPort) {
        this.webSocketPort = webSocketPort;
        this.manipulateFile = new ManipulateFile();      
    }

    
    public String parse(String pathFile) {  
        
        String content = this.manipulateFile.getFileContent(pathFile);

        content = convertHeaders(content);
        content = convertBold(content);
        content = convertItalic(content);
        content = convertLists(content);
        content = convertLinks(content);
        content = addJavaScript(content);

        return content;
    }
    
    
    private String addJavaScript(String content) {
        String script = "<script> document.addEventListener('DOMContentLoaded', (event) => { var ws = new WebSocket('ws://localhost:" + this.webSocketPort + "/reload'); ws.onmessage = function(event) { if (event.data === 'reload') { window.location.reload(); } }; }); </script>";
        content = content + "\n" + script;
        return content;
    }
    

    private String convertHeaders(String markdown) {
        markdown = markdown.replaceAll("(?m)^# (.+)$", "<h1>$1</h1>");
        markdown = markdown.replaceAll("(?m)^## (.+)$", "<h2>$1</h2>");
        markdown = markdown.replaceAll("(?m)^### (.+)$", "<h3>$1</h3>");
        return markdown;
    }


    private String convertBold(String markdown) {
        markdown = markdown.replaceAll("\\*\\*(.+?)\\*\\*", "<strong>$1</strong>");
        markdown = markdown.replaceAll("__(.+?)__", "<strong>$1</strong>");
        return markdown;
    }


    private String convertItalic(String markdown) {
        markdown = markdown.replaceAll("\\*(.+?)\\*", "<em>$1</em>");
        markdown = markdown.replaceAll("_(.+?)_", "<em>$1</em>");
        return markdown;
    }


    private String convertLists(String markdown) {
        markdown = markdown.replaceAll("(?m)^\\* (.+)$", "<li>$1</li>");
        markdown = markdown.replaceAll("(?m)^- (.+)$", "<li>$1</li>");
        markdown = markdown.replaceAll("(?s)(<li>.+?</li>)", "<ul>$1</ul>");
        markdown = markdown.replaceAll("</ul><ul>", "");
        return markdown;
    }

    
    private String convertLinks(String markdown) {
        markdown = markdown.replaceAll("\\[(.+?)\\]\\((.+?)\\)", "<a href=\"$2\">$1</a>");
        return markdown;
    }

}