package com.georgejrdev.auxiliar.cli.commands;

import com.georgejrdev.auxiliar.cli.interfaces.Command;
import com.georgejrdev.auxiliar.processing.interfaces.Help;
import com.georgejrdev.auxiliar.processing.interfaces.ToDo;


public class ToDoCommand implements Command{

    private ToDo toDo;
    private Help help;


    public ToDoCommand(ToDo toDo, Help help){
        this.toDo = toDo;
        this.help = help;
    }


    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            help.showCorrectSintax("-td");
            return;
        }

        switch (args[1]) {
            case "show":
                this.toDo.showTasks();
                break;

            case "add":
                if (args.length == 3) {
                    this.toDo.createNewTask(args[2]);
                    this.toDo.showTasks();
                    
                } else {
                    help.showCorrectSintax("add");
                }
                break;

            case "check":
                try {
                    int idCheck = Integer.parseInt(args[2]);
                    this.toDo.checkTask(idCheck, false);
                    this.toDo.showTasks();

                } catch (NumberFormatException e) {
                    help.showCorrectSintax("check");
                }
                break;

            case "uncheck":
                try {
                    int idUncheck = Integer.parseInt(args[2]);
                    this.toDo.checkTask(idUncheck, true);
                    this.toDo.showTasks();

                } catch (NumberFormatException e) {
                    help.showCorrectSintax("uncheck");
                }
                break;

            case "delete":
                try {
                    int idDelete = Integer.parseInt(args[2]);
                    this.toDo.deleteTask(idDelete);
                    this.toDo.showTasks();

                } catch (NumberFormatException e) {
                    help.showCorrectSintax("delete");
                }
                break;

            default:
                System.out.println(args[1] + " does not correspond to any function");
                help.showCommands();
                break;
        }
    }
}
