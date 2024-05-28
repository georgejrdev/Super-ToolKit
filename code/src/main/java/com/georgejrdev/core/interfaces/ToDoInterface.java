package com.georgejrdev.core.interfaces;

public interface ToDoInterface {
    void createNewTask(String content);
    void checkTask(int idTask, boolean newState);
    void deleteTask(int id);
    void showTasks();
}
