package com.georgejrdev.auxiliar.processing.interfaces;


public interface ToDo {
    void createTask(String content);
    void checkTask(int index);
    void uncheckTask(int index);
    void deleteTask(int index);    
}