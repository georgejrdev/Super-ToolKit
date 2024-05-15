package com.georgejrdev;
import com.georgejrdev.core.Translate;;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("Parameters Required");
            return;
        }
        
        String choice = args[0];

        switch (choice) {

            case "-t":
                if (args.length != 3){
                    System.out.println("Invalid parameter(s). Example of correct syntax: tkd -t 'Hello' pt");
                    return;
                }

                Translate translator = new Translate();
                System.out.println(translator.translate(args[1],args[2]));
                break;
        
            default:
                System.out.println(choice+" does not correspond to any function");
                break;
        }
    }
}