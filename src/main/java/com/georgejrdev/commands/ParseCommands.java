package com.georgejrdev.commands;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.georgejrdev.auxiliar.file.ManipulateFile;
import com.georgejrdev.auxiliar.reload.HotReload;
import com.georgejrdev.auxiliar.reload.Parser;
import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.parser.MarkdownParser;
import com.georgejrdev.utils.validations.OptionsValidation;


public class ParseCommands implements Commands{
    
    private final Helper helper;
    private final OptionsValidation optionsValidation;
    private final int HTTP_PORT = 8080;
    private final int WEB_SOCKET_PORT = 8081;
    private final Parser parser;
    private final ManipulateFile manipulateFile;
    private String srcFilePath; 
    private String destFilePath;
    private String directoryPath;

    public ParseCommands(Helper helper){
        this.helper = helper;
        this.optionsValidation = new OptionsValidation();
        this.parser = new MarkdownParser();
        this.manipulateFile = new ManipulateFile();
    }


    public void run(String[] args){
        
        try {
            this.optionsValidation.expectedQuantityOfParameters(args, 2, 3);
            
            if (args.length == 3){
                this.optionsValidation.optionIsAvailable(args[0],args[1]);
            }

            this.optionsValidation.parameterIsAvailable(args[args.length - 1]);
        } 

        catch (UnexpectedNumberOfParameters e){
            System.out.println("Unexpected number of parameters");
            helper.listCommands(args[0]);
            return;
        }

        catch (InvalidOptionCommand e){
            helper.invalidOption(args[0], args[1]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println("Argument " + args[args.length - 1] + " is not valid");
            helper.listCommands(args[0]);
            return;
        }

        final String OPTION = (args.length == 3) ? args[1] : null;

        this.srcFilePath = args[args.length - 1];
        this.directoryPath = getDirectory();
        
        String htmlFile = "index.html";
        this.destFilePath = directoryPath + htmlFile;
        
        if (!manipulateFile.fileExist(this.destFilePath)){
            manipulateFile.createFile(this.directoryPath, htmlFile, "<h1>Init Reload</h1> \n <p>update the file to load its content</p> \n <script> document.addEventListener('DOMContentLoaded', (event) => { var ws = new WebSocket('ws://localhost:8081/reload'); ws.onmessage = function(event) { if (event.data === 'reload') { window.location.reload(); } }; }); </script>");
        }

        if (OPTION == null){
            parseContent();
        } else {
            activeHotReload();
        }

        System.out.println("VAI SE FODER -------------------------------------------------------------------");
    }


    private void activeHotReload(){
        MarkdownParser parseMarkdown = new MarkdownParser();
        HotReload hotReload = new HotReload(this.srcFilePath,this.destFilePath,HTTP_PORT,WEB_SOCKET_PORT,parseMarkdown);
        hotReload.start();
    }


    private void parseContent(){
        String htmlContent = new MarkdownParser().parse(this.srcFilePath);
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
