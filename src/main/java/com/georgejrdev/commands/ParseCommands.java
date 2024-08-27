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
        final String FILE_TO_WATCH = args[args.length - 1];

        String fileToUpdate = getPathFileTopUpdate(FILE_TO_WATCH);
        
        if (!manipulateFile.fileExist(fileToUpdate+"/index.html")){
            manipulateFile.createFile(fileToUpdate, "index.html", "vai se foder");
        }
        
        if (OPTION == null){
            String content = parser.parse(FILE_TO_WATCH);
            manipulateFile.writeFile(fileToUpdate+"/index.html", content);
            return;
        }

        // HotReload hotReload = new HotReload(FILE_TO_WATCH, FILE_TO_UPDATE, HTTP_PORT, WEB_SOCKET_PORT, parser);

    }


    private String getPathFileTopUpdate(String path) {
        Path filePath = Paths.get(path);
        Path directory = filePath.getParent();    
        return directory.toString();
    }
}
