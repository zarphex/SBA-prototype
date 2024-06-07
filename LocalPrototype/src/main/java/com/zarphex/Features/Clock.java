package com.zarphex.Features;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.swing.Timer;

/**
 * The Clock feature class showing the current local date and time.
 */
public class Clock extends Feature {
    private final JButton FORMAT_CHANGE_BUTTON;
    private JLabel CLOCK_LABEL;
    private JLabel DATE_LABEL;
    private boolean isTwentyFourHour;

    /**
     * The default constructor.
     * @param props: Properties file.
     */
    public Clock(Properties props) {
        super(props);
        isTwentyFourHour = false;

        getFeaturePanel().setPreferredSize(new Dimension(600, 200));
        ImageIcon imageicon = new ImageIcon(props.getProperty("settingCogImage"));
        Image settingCogImage = imageicon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        FORMAT_CHANGE_BUTTON = new JButton(new ImageIcon(settingCogImage));
        FORMAT_CHANGE_BUTTON.setBackground(null);
        FORMAT_CHANGE_BUTTON.setBorder(null);

        createGUI(props);
    }

    /**
     * Create the GUI with relevant displays.
     * @param props: Properties file.
     */
    @Override
    public void createGUI(Properties props) {
        super.createGUI(props);
        makeClock();

        // Add components to the panel.
        getBACKGROUND_PANEL().add(FORMAT_CHANGE_BUTTON, "al right, gap 20, span, wrap");
        getFeaturePanel().setBackground(null);
        getFeaturePanel().add(CLOCK_LABEL, "pos 0.5al 0.5al, span, wrap");
        getFeaturePanel().add(DATE_LABEL, "pos 0.5al 0.9al, span, wrap");
        getBACKGROUND_PANEL().add(getFeaturePanel(), "al center, span, wrap");

        addArrowComponents();
    }

    /**
     * Make the clock with its label and current time.
     */
    public void makeClock() {
        // Instantiate time and date labels.
        CLOCK_LABEL = new JLabel();
        DATE_LABEL = new JLabel();

        // Format components.
        CLOCK_LABEL.setFont(new Font(getFont(), Font.BOLD, 60));
        CLOCK_LABEL.setForeground(getCREAM_COLOR());
        DATE_LABEL.setFont(new Font(getFont(), Font.BOLD, 18));
        DATE_LABEL.setForeground(getCREAM_COLOR());
        FORMAT_CHANGE_BUTTON.addActionListener(e -> changeMode());

        // Create and start the timer.
        Timer timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    /**
     * Update the time with the correct format.
     */
    public void updateTime() {
        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();

        // Format display of time and date.
        String timeDisplay = currentTime.format(isTwentyFourHour
                ? DateTimeFormatter.ofPattern("HH:mm:ss")
                : DateTimeFormatter.ofPattern("hh:mm:ss a"));
        String dateDisplay = currentDate.format(DateTimeFormatter.ofPattern("EEE, dd LLL yyyy"));

        CLOCK_LABEL.setText(timeDisplay);
        DATE_LABEL.setText(dateDisplay);
    }

    /**
     * Flip the mode to either twelve hour or twenty-four hour time.
     */
    public void changeMode() {
        isTwentyFourHour = !isTwentyFourHour;
        updateTime(); // Adjust the display of the time with the new format.
    }
}
