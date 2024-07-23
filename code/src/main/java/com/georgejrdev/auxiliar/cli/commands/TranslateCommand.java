package com.georgejrdev.auxiliar.cli.commands;

import com.georgejrdev.auxiliar.cli.interfaces.Command;
import com.georgejrdev.auxiliar.processing.interfaces.Help;
import com.georgejrdev.auxiliar.processing.interfaces.Translate;


public class TranslateCommand implements Command{
    
    private Translate translator;
    private Help help;


    public TranslateCommand(Translate translator, Help help){
        this.translator = translator;
        this.help = help;
    }


    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            this.help.showCorrectSintax("-t");
            return;
        }

        System.out.println(translator.translate(args[1], args[2]));
    }
}