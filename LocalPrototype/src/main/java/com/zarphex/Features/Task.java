package com.zarphex.Features;

public class Task {
    public String taskDescription;
    public boolean isCompleted;
    public String category;
    public Task parentTask;

    public Task(String description, String category, Task parentTask) {
        this.taskDescription = description;
        this.category = category;
        this.parentTask = parentTask;
        this.isCompleted = false;
    }
    public Task(String description) {
        this.taskDescription = description;
        this.isCompleted = false;
    }
}
