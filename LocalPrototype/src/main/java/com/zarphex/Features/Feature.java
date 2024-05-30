package com.zarphex.Features;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * The abstract class for all features.
 */
public abstract class Feature {
    private final JButton LEFT_ARROW, RIGHT_ARROW;
    private final JPanel PANEL;

    /**
     * Default constructor.
     * @param props: Properties file.
     */
    public Feature(Properties props) {
        PANEL = new JPanel();
        LEFT_ARROW = new JButton(new ImageIcon(props.getProperty("leftArrowImage")));
        RIGHT_ARROW = new JButton(new ImageIcon(props.getProperty("rightArrowImage")));
    }

    /**
     * Skeleton method for drawing the GUI.
     */
    public void createGUI(Properties props) {
        // Fix layout and background of the panel.
        PANEL.setLayout(new MigLayout("insets 0, al center center, debug"));
        PANEL.setBackground(new Color(Integer.parseInt(props.getProperty("creamBackgroundR")),
                Integer.parseInt(props.getProperty("creamBackgroundG")),
                Integer.parseInt(props.getProperty("creamBackgroundB"))));
    }

    public void addArrowComponents() {
        PANEL.add(LEFT_ARROW, "al left bottom");
        PANEL.add(RIGHT_ARROW, "al right bottom, push, wrap");
    }
    /**
     * Getter for the main Panel.
     * @return the panel.
     */
    public JPanel getPanel() {
        return PANEL;
    }

    /**
     * Getter for the left arrow button.
     * @return the left arrow button.
     */
    public JButton getLEFT_ARROW() {
        return LEFT_ARROW;
    }

    /**
     * Getter for the right arrow button.
     * @return the right arrow button.
     */
    public JButton getRIGHT_ARROW() {
        return RIGHT_ARROW;
    }
}