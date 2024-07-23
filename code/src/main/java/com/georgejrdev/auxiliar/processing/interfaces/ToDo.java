package com.georgejrdev.auxiliar.processing.interfaces;


public interface ToDo {
    void createNewTask(String content);
    void checkTask(int idTask, boolean newState);
    void deleteTask(int id);
    void showTasks();
}
