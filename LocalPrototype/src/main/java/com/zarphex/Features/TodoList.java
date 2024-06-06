package com.zarphex.Features;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Properties;

public class TodoList extends Feature {
    private JLabel TITLE;
    private ArrayList<Task> taskList;
    private ArrayList<String> categoryList;
    private Priority priority;

    public TodoList(Properties props) {
        super(props);

        TITLE = new JLabel(props.getProperty("TodoListTitle"));
        TITLE.setFont(new Font(getFont(), Font.BOLD, 38));
        TITLE.setForeground(new Color(250, 249,246));

        createGUI(props);
        addArrowComponents();
    }

    @Override
    public void createGUI(Properties props) {
        super.createGUI(props);
        getPanel().add(TITLE);
    }

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
