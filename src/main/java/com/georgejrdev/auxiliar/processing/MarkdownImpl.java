package com.georgejrdev.auxiliar.processing;

import com.georgejrdev.auxiliar.file.ManipulateFile;
import com.georgejrdev.auxiliar.processing.interfaces.Markdown;
import com.georgejrdev.auxiliar.reload.HotReload;
import com.georgejrdev.auxiliar.utils.ParseMarkdown;


public class MarkdownImpl implements Markdown {

    private String srcFilePath;
    private String srcDirectory;
    private String destFilePath;
    private ManipulateFile manipulateFile;

    public MarkdownImpl(String srcFilePath){
        this.srcFilePath = srcFilePath;
        this.srcDirectory = getDirectory();
        this.manipulateFile = new ManipulateFile();
    }
    
    
    @Override
    public void parseHtml(boolean hotReload) {
        
        String htmlFile = "index.html";
        this.destFilePath = this.srcDirectory + htmlFile;


        if (!manipulateFile.fileExist(this.destFilePath)) {
            manipulateFile.createFile(this.srcDirectory, htmlFile, "<h1>Init Reload</h1> \n <p>update the file to load its content</p> \n <script> document.addEventListener('DOMContentLoaded', (event) => { var ws = new WebSocket('ws://localhost:8081/reload'); ws.onmessage = function(event) { if (event.data === 'reload') { window.location.reload(); } }; }); </script>");
        }

        if (hotReload){
            activeHotReload();
        
        } else {
            parser();
        }
    }


    private void activeHotReload(){
        ParseMarkdown parseMarkdown = new ParseMarkdown();
        HotReload hotReload = new HotReload(this.srcFilePath,this.destFilePath,parseMarkdown);
        hotReload.start();
    }


    private void parser(){
        String htmlContent = new ParseMarkdown().parse(this.srcFilePath);
        this.manipulateFile.writeFile(this.destFilePath, htmlContent);
    }


    private String getDirectory(){
        int lastSeparatorIndex = this.srcFilePath.lastIndexOf('/');
        
        if (lastSeparatorIndex == -1) {
            return "";
        }

        return this.srcFilePath.substring(0, lastSeparatorIndex + 1);    
    }
}