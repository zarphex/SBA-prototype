package com.zarphex.Features;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public abstract class Feature {
    private final ImageIcon LEFT_ARROW_ICON, RIGHT_ARROW_ICON;
    private final JButton LEFT_ARROW, RIGHT_ARROW;
    private final JPanel PANEL;

    public Feature(Properties props) {
        PANEL = new JPanel();
        LEFT_ARROW_ICON = new ImageIcon(props.getProperty("leftArrowImage"));
        LEFT_ARROW = new JButton(LEFT_ARROW_ICON);
        RIGHT_ARROW_ICON = new ImageIcon(props.getProperty("rightArrowImage"));
        RIGHT_ARROW = new JButton(RIGHT_ARROW_ICON);

        // Add action listeners to the buttons.
        LEFT_ARROW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        RIGHT_ARROW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getPanel() {
        return PANEL;
    }

    public JButton getLEFT_ARROW() {
        return LEFT_ARROW;
    }

    public JButton getRIGHT_ARROW() {
        return RIGHT_ARROW;
    }
}
