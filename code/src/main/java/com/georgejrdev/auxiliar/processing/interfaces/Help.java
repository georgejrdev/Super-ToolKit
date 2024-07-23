package com.georgejrdev.auxiliar.processing.interfaces;


public interface Help {
    void showCommands();
    void showCorrectSintax(String command);
    String getCommandSyntax(String command);
}
