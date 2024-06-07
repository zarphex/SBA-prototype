package com.zarphex.Features;

import com.zarphex.RoundedPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.util.Properties;

/**
 * The abstract class for all features.
 */
public abstract class Feature {
    private final JButton LEFT_ARROW, RIGHT_ARROW;
    private final JPanel BACKGROUND_PANEL;
    private RoundedPanel featurePanel;
    private final String font;
    private final Color DARK_PURPLE_COLOR, LIGHT_PURPLE_COLOR, CREAM_COLOR;

    /**
     * Default constructor.
     * @param props: Properties file.
     */
    public Feature(Properties props){
        font = props.getProperty("fontFile");
        DARK_PURPLE_COLOR = getBackgroundRGB(props.getProperty("darkPurpleRGB"));
        LIGHT_PURPLE_COLOR = getBackgroundRGB(props.getProperty("lightPurpleRGB"));
        CREAM_COLOR = getBackgroundRGB(props.getProperty("creamRGB"));

        BACKGROUND_PANEL = new JPanel();
        featurePanel = new RoundedPanel(new MigLayout("al 50% 50%"),
                Integer.parseInt(props.getProperty("innerPanelCornerRadius")), LIGHT_PURPLE_COLOR);

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
            button.setBackground(CREAM_COLOR);
            button.setForeground(DARK_PURPLE_COLOR);
        }
    }

    /**
     * Implement the GUI and display.
     * @param props: Properties file.
     */
    public void createGUI(Properties props) {
        // Fix layout and background of the panel.
        BACKGROUND_PANEL.setLayout(new MigLayout("al 50% 50%"));
        BACKGROUND_PANEL.setBackground(DARK_PURPLE_COLOR);
        LEFT_ARROW.setForeground(DARK_PURPLE_COLOR);
        RIGHT_ARROW.setForeground(DARK_PURPLE_COLOR);
    }

    /**
     * Add the arrow buttons to the screen.
     */
    public void addArrowComponents() {
        BACKGROUND_PANEL.add(LEFT_ARROW, "pos 0al 1al");
        BACKGROUND_PANEL.add(RIGHT_ARROW, "pos 1al 1al");
    }

    public Color getBackgroundRGB(String backgroundPropertyValue) {
        String[] stringRGB = backgroundPropertyValue.split(",");
        int[] intRGB = new int[3];
        for (int i = 0; i < 3; i++) {
            intRGB[i] = Integer.parseInt(stringRGB[i]);
        }
        return new Color(intRGB[0], intRGB[1], intRGB[2]);
    }

    /**
     * Create a new formatter for the text field.
     * @param s: The string to take from the text field.
     * @return The formatter.
     */
    public MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
            formatter.setPlaceholderCharacter('0');
        } catch (java.text.ParseException exception){
            System.err.println("Formatter is bad: " + exception.getMessage());
            System.exit(-1);
        }
        return formatter;
    }

    public Color getDARK_PURPLE_COLOR() {
        return DARK_PURPLE_COLOR;
    }

    public Color getLIGHT_PURPLE_COLOR() {
        return LIGHT_PURPLE_COLOR;
    }

    public Color getCREAM_COLOR() {
        return CREAM_COLOR;
    }

    // Getters
    public JPanel getBACKGROUND_PANEL() {
        return BACKGROUND_PANEL;
    }

    public RoundedPanel getFeaturePanel() {return featurePanel;}

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