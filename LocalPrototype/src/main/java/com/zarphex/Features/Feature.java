package com.zarphex.Features;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public abstract class Feature {
    private final JButton LEFT_ARROW, RIGHT_ARROW;
    private final JPanel PANEL;

    public Feature(Properties props) {
        PANEL = new JPanel();
        LEFT_ARROW = new JButton(new ImageIcon(props.getProperty("leftArrowImage")));
        RIGHT_ARROW = new JButton(new ImageIcon(props.getProperty("rightArrowImage")));
    }

    public void createGUI() {
        // Fix layout and background of the panel.
        PANEL.setLayout(new MigLayout("insets 0, al center center"));
        PANEL.setBackground(new Color(0xFAEED1));

        PANEL.add(LEFT_ARROW, "al left top");
        PANEL.add(RIGHT_ARROW, "al right top, push, wrap");
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