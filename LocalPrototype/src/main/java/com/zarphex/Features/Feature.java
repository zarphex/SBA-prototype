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
    private final String font;

    /**
     * Default constructor.
     * @param props: Properties file.
     */
    public Feature(Properties props){
        font = props.getProperty("fontFile");
        PANEL = new JPanel();

        LEFT_ARROW = new JButton(new ImageIcon(props.getProperty("leftArrowImage")));
        LEFT_ARROW.setBorderPainted(false);
        RIGHT_ARROW = new JButton(new ImageIcon(props.getProperty("rightArrowImage")));
        RIGHT_ARROW.setBorderPainted(false);
    }

    /**
     * Adjust the size and colours for the interactive button.
     * @param buttons: The buttons to format.
     */
    public void formatButton(JButton... buttons) {
        for (JButton button : buttons) {
            button.setPreferredSize(new Dimension(100, 40));
            button.setBackground(new Color(237, 234, 222));
            button.setForeground(new Color(128, 0 ,128));
        }
    }

    /**
     * Implement the GUI and display.
     * @param props: Properties file.
     */
    public void createGUI(Properties props) {
        // Fix layout and background of the panel.
        PANEL.setLayout(new MigLayout("al center bottom"));
        PANEL.setBackground(new Color(156,151,252));
        LEFT_ARROW.setForeground(new Color(156,151,252));
        RIGHT_ARROW.setForeground(new Color(156,151,252));
    }

    /**
     * Add the arrow buttons to the screen.
     */
    public void addArrowComponents() {
        PANEL.add(LEFT_ARROW, "al left bottom, push");
        PANEL.add(RIGHT_ARROW, "al right bottom, push, span, wrap");
    }

    // Getters
    public JPanel getPanel() {
        return PANEL;
    }

    public JButton getLEFT_ARROW() {
        return LEFT_ARROW;
    }

    public JButton getRIGHT_ARROW() {
        return RIGHT_ARROW;
    }

    public String getFont() {
        return font;
    }
}