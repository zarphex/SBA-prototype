package com.zarphex.Features;

import java.util.ArrayList;

public class TodoList {
    private ArrayList<Task> taskList;
    private ArrayList<String> categoryList;
    private Priority priority;

    public TodoList() {}

    public void createTask(Task task) {
        taskList.add(task);
    }
    public void createCategory(String categoryName) {
        categoryList.add(categoryName);
    }
    public void markAsCompleted(Task task) {
        for (Task listItem : taskList) {
            if (listItem.equals(task)) {
                listItem.isCompleted = true;
            }
        }
    }
    public void removeTask(Task task) {
        taskList.remove(task);
    }
}
