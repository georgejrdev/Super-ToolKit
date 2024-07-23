package com.georgejrdev.auxiliar.cli;

import java.util.HashMap;
import java.util.Map;

import com.georgejrdev.auxiliar.cli.interfaces.Command;

import com.georgejrdev.auxiliar.cli.commands.HelpCommand;
import com.georgejrdev.auxiliar.cli.commands.TranslateCommand;
import com.georgejrdev.auxiliar.cli.commands.ToDoCommand;

import com.georgejrdev.auxiliar.processing.interfaces.Request;
import com.georgejrdev.auxiliar.processing.interfaces.Help;
import com.georgejrdev.auxiliar.processing.interfaces.Translate;
import com.georgejrdev.auxiliar.processing.interfaces.ToDo;

import com.georgejrdev.auxiliar.processing.RequestImpl;
import com.georgejrdev.auxiliar.processing.HelpImpl;
import com.georgejrdev.auxiliar.processing.TranslateImpl;
import com.georgejrdev.auxiliar.processing.ToDoImpl;


public class CommandFactory {

    private final Map<String, Command> commands = new HashMap<>();


    public CommandFactory() {
        Request request = new RequestImpl();
        Help help = new HelpImpl();
        Translate translator = new TranslateImpl(request);
        ToDo toDo = new ToDoImpl();

        commands.put("-h", new HelpCommand(help));
        commands.put("-t", new TranslateCommand(translator, help));
        commands.put("-td", new ToDoCommand(toDo, help));
    }


    public Command getCommand(String key) {
        return commands.get(key);
    }
}