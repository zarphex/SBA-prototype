package com.zarphex.Features;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class TodoList extends Feature {
    private JPanel listPanel;
    private JTextField title, description;
    private HashMap<JCheckBox, JTextField> mapOfTodoItems;
    private ArrayList<Task> taskList;
    private ArrayList<String> categoryList;
    private Priority priority;

    public TodoList(Properties props) {
        super(props);
        mapOfTodoItems = new HashMap<>();
        listPanel = new JPanel();
        listPanel.setBackground(null);
        listPanel.setLayout(new MigLayout("al center top"));
        createNewListItem();
        title = new JTextField(props.getProperty("TodoListTitle"));
        title.setFont(new Font(getFont(), Font.BOLD, 38));
        title.setPreferredSize(new Dimension(600, 40));
        title.setForeground(new Color(250, 249,246));
        title.setBackground(null);
        title.setBorder(null);
        description = new JTextField(props.getProperty("TodoListDescription"));
        description.setFont(new Font(getFont(), Font.PLAIN, 20));
        description.setPreferredSize(new Dimension(600, 40));
        description.setForeground(Color.black);
        description.setBackground(null);
        description.setBorder(null);

        createGUI(props);
        getBACKGROUND_PANEL().add(title, "al center, span, wrap");
        getBACKGROUND_PANEL().add(description, "al center, span,, wrap");
        getBACKGROUND_PANEL().add(listPanel, "al center top, span, push, wrap");
        addArrowComponents();
    }

    @Override
    public void createGUI(Properties props) {
        super.createGUI(props);
    }

    public void createNewListItem() {
        JCheckBox newCheckBox = new JCheckBox();
        JTextField newTextField = new JTextField();
        newCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newCheckBox.isSelected()) {
                    completeTaskItem(newCheckBox);
                }
            }
        });
        newTextField.setPreferredSize(new Dimension(600, 40));
        newTextField.setHorizontalAlignment(JTextField.LEFT);
        newTextField.setAutoscrolls(true);
        newTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == (KeyEvent.VK_ENTER)) {
                    newTextField.setBorder(null);
                    newTextField.setBackground(null);
                    newTextField.setEditable(false);
                    createNewListItem();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        mapOfTodoItems.put(newCheckBox, newTextField);
        listPanel.add(newCheckBox, "al center, split 2");
        listPanel.add(newTextField, "al center, wrap");
        listPanel.revalidate();
        listPanel.repaint();
    }

    public void completeTaskItem(JCheckBox checkBox) {
        listPanel.remove(checkBox);
        listPanel.remove(mapOfTodoItems.get(checkBox));
        mapOfTodoItems.remove(checkBox);
        if (mapOfTodoItems.isEmpty()) {
            createNewListItem();
        } else {
            listPanel.revalidate();
            listPanel.repaint();
        }
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
