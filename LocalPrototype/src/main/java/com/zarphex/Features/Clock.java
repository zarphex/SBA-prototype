package com.zarphex.Features;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.awt.Color;
/**
 * The Clock feature showing the current local time and date.
 */
public class Clock {
    private final ImageIcon LEFT_ARROW_ICON, RIGHT_ARROW_ICON;
    private final JLabel LEFT_ARROW, RIGHT_ARROW;
    private final JFrame FRAME;
    private final JPanel PANEL;
    private boolean isTwentyFourHour;
    private String currentTime;
    private String currentDate;

    /***
     * The default constructor.
     */
    public Clock(Properties props) {
        this.isTwentyFourHour = false;
        this.currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("K:mm:ss a"));
        this.currentDate = LocalDate.now().toString();

        // Instantiate all entities.
        FRAME = new JFrame();
        PANEL = new JPanel();
        LEFT_ARROW_ICON = new ImageIcon(props.getProperty("leftArrowImage"));
        LEFT_ARROW = new JLabel(LEFT_ARROW_ICON);
        RIGHT_ARROW_ICON = new ImageIcon(props.getProperty("rightArrowImage"));
        RIGHT_ARROW = new JLabel(RIGHT_ARROW_ICON);

        createGUI(props);
    }

    public void createGUI(Properties props) {
        // Update the frame.
        FRAME.setSize(Integer.parseInt(props.getProperty("windowLength")),
                Integer.parseInt(props.getProperty("windowHeight")));
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Update the panel.
        PANEL.setLayout(new MigLayout("al center center, gapy 20, debug"));
        PANEL.setBackground(new Color(0xFAEED1));
        PANEL.add(new JLabel(currentTime), "center, span");
        PANEL.add(new JLabel(currentDate), "center, span");
        PANEL.add(LEFT_ARROW);
        PANEL.add(RIGHT_ARROW, "wrap");
        setTime(LocalTime.now());
        setDate(LocalDate.now());

        FRAME.add(PANEL);
        FRAME.setVisible(true);
    }

    public void changeMode() {
        isTwentyFourHour = !isTwentyFourHour;
    }

    public void setTime(LocalTime time) {
        currentTime = time.format(DateTimeFormatter.ofPattern("K:m:s a"));
    }

    public void setDate(LocalDate date) {
        currentDate = date.toString();
    }

    public JFrame getFrame() {
        return FRAME;
    }
}
