package com.georgejrdev.executors;

import com.georgejrdev.auxiliar.file.ManipulateFile;
import com.georgejrdev.auxiliar.reload.HotReload;
import com.georgejrdev.auxiliar.reload.Parser;
import com.georgejrdev.utils.parser.MarkdownParser;


public class ParseExecutor {
    
    private final ManipulateFile manipulateFile;
    private final Parser parser;
    
    private final int HTTP_PORT;
    private final int WEB_SOCKET_PORT;

    private String directoryPath;
    private String srcFilePath;
    private String destFilePath;
    private String htmlFileName;

    public ParseExecutor(String srcFilePath){
        this.manipulateFile = new ManipulateFile();
        this.HTTP_PORT = 8080;
        this.WEB_SOCKET_PORT = 8081;
        this.parser = new MarkdownParser(this.WEB_SOCKET_PORT);
        this.srcFilePath = srcFilePath;
        this.directoryPath = getDirectory();
        this.htmlFileName = "index.html";
        this.destFilePath = this.directoryPath + this.htmlFileName;

        if (!this.manipulateFile.fileExist(this.destFilePath)){
            createFileIfNotExist();
        }
    }


    public void parseToHtml(){
        String htmlContent = this.parser.parse(this.srcFilePath);
        this.manipulateFile.writeFile(this.destFilePath, htmlContent);
    }


    public void parseToHtmlWithHotReload(){
        HotReload hotReload = new HotReload(this.srcFilePath,this.destFilePath,HTTP_PORT,WEB_SOCKET_PORT,this.parser);
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println(" ---> Server is shutting down...");
        }));

        hotReload.start();
    }


    private void createFileIfNotExist(){
        String initialContent = "<h1>Init Reload</h1> \n <p>update the file to load its content</p> \n <script> document.addEventListener('DOMContentLoaded', (event) => { var ws = new WebSocket('ws://localhost:"+this.WEB_SOCKET_PORT+"/reload'); ws.onmessage = function(event) { if (event.data === 'reload') { window.location.reload(); } }; }); </script>";
        manipulateFile.createFile(this.directoryPath, htmlFileName, initialContent);
    }


    private String getDirectory(){
        int lastSeparatorIndex = this.srcFilePath.lastIndexOf('/');
        
        if (lastSeparatorIndex == -1) {
            return "";
        }
        return this.srcFilePath.substring(0, lastSeparatorIndex + 1);    
    }
}
