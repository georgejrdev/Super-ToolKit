package com.georgejrdev;

import com.georgejrdev.core.Help;
import com.georgejrdev.core.Translate;
import com.georgejrdev.core.ToDo;


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
            

            case "-c":
                System.out.println("This function is still under development");
                break;


            case "-td":

                ToDo td = new ToDo();

                if (args.length < 2){
                    help.showCorrectSintax("-td");
                    break;
                }

                switch (args[1]) {
                    case "show":
                        td.showTasks();
                        break;

                    case "add":
                        if (args.length == 3){
                            td.createNewTask(args[2]);
                            td.showTasks();
                        } else {
                            help.showCorrectSintax("add");
                        }
                        break;
                
                    case "check":
                        try{
                            int idCheck = Integer.parseInt(args[2]);
                            td.checkTask(idCheck, false);
                            td.showTasks();
                            
                        } catch (NumberFormatException e) {
                            help.showCorrectSintax(args[1]);

                        } catch (Exception e) {
                            help.showCorrectSintax("check");
                        }
                       
                        break;

                    case "uncheck":
                        try{
                            int idUncheck = Integer.parseInt(args[2]);
                            td.checkTask(idUncheck, true);
                            td.showTasks();

                        } catch (NumberFormatException e){
                            help.showCorrectSintax(args[1]);

                        } catch (Exception e) {
                            help.showCorrectSintax("uncheck");
                        }
                        break;
                        
                    default:
                        System.out.println(args[1]+" does not correspond to any function");
                        help.showCommands();
                        break;
                }
                break;


            default:
                System.out.println(choice+" does not correspond to any function");
                help.showCommands();
                break;
        }
    }
}