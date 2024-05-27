package com.georgejrdev;

import com.georgejrdev.core.Help;
import com.georgejrdev.core.Translate;


public class Main {

    public static void main(String[] args) {
        
        Help help = new Help();

        if (args.length == 0){
            System.out.println("Parameters Required");
            help.showCommands();
            return;
        }
        
        String choice = args[0];

        switch (choice) {

            case "-h":
                help.showCommands();
                break;


            case "-t":
                if (args.length != 3){
                    help.showCorrectSintax(choice);
                    return;
                }

                Translate translator = new Translate();
                System.out.println(translator.translate(args[1],args[2]));
                break;
        

            default:
                System.out.println(choice+" does not correspond to any function");
                help.showCommands();
                break;
        }
    }
}