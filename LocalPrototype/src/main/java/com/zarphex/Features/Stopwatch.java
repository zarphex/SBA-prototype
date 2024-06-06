package com.zarphex.Features;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * The stopwatch feature class with time tracking capabilities in hours, minutes and seconds.
 */
public class Stopwatch extends TimerMeasurer {
    private final JPanel lapsPanel;
    private final JButton LAP_STOPWATCH;

    /**
     * Default constructor for the stopwatch.
     * @param props: Properties file.
     */
    public Stopwatch(Properties props) {
        super(props);
        getTimerLabel().setEditable(false);

        // Instantiate the components.
        lapsPanel = new JPanel();
        lapsPanel.setBackground(null);
        lapsPanel.setLayout(new BoxLayout(lapsPanel, BoxLayout.Y_AXIS));
        adjustLabelColour(timerStatus.start);

        LAP_STOPWATCH = new JButton(props.getProperty("lapLabel"));
        formatButton(LAP_STOPWATCH);

        createGUI(props);
        initialiseTimer(props);
        addLabels(props);
        addArrowComponents();
    }

    /**
     * Create the stopwatch with all button functions.
     * @param props: Properties file.
     */
    @Override
    public void initialiseTimer(Properties props) {
        // Call to update the stopwatch every second.
        setTimerControl(new Timer(1000, e -> updateTimer(props)));

        // Button action listeners.
        getSTART_TIMER().addActionListener(e -> getTimerControl().start());
        getPAUSE_TIMER().addActionListener(e -> getTimerControl().stop());
        LAP_STOPWATCH.addActionListener(e -> {
            drawLap(getTimerLabel().getText());
        });
        getRESET_TIMER().addActionListener(e -> {
            getTimerControl().stop();
            setSetTimer(LocalTime.MIN);
            setCurrentTimer(getSetTimer());
            deleteLaps();
            drawTimer(props);
        });

        // Set default stopwatch value.
        setCurrentTimer(LocalTime.MIN);
        drawTimer(props);
    }

    /**
     * Add all components to the screen.
     * @param props: Properties file.
     */
    @Override
    public void addLabels(Properties props) {
        // Add labels to the panel.
        getPanel().add(getTimerLabel(), "al center bottom, span, push, wrap");
        getPanel().add(getSTART_TIMER(), "al center, split 4, span");
        getPanel().add(getPAUSE_TIMER(), "al center, span");
        getPanel().add(LAP_STOPWATCH, "al center, span");
        getPanel().add(getRESET_TIMER(), "al center, span, wrap");
        getPanel().add(lapsPanel, "pos 0.5al 55%");
    }

    /**
     * Add a second to the stopwatch and draw it.
     */
    public void updateTimer(Properties props) {
        setCurrentTimer(getCurrentTimer().plusSeconds(1));
        drawTimer(props);
    }

    /**
     * Create a new lap based on the current time.
     * @param lapTime: The current lap time.
     */
    public void drawLap(String lapTime) {
        // Create new lap label component and add to the panel.
        JLabel newLap = new JLabel();
        newLap.setText(lapTime);
        newLap.setFont(new Font(getFont(), Font.PLAIN, 12));
        lapsPanel.add(newLap);
        lapsPanel.revalidate();
        lapsPanel.repaint();
    }

    /**
     * Remove every lap recorded and redraw the entire panel.
     */
    public void deleteLaps() {
        lapsPanel.removeAll();
        lapsPanel.revalidate();
        lapsPanel.repaint();
    }

    /**
     * Display the stopwatch in the valid format.
     * @param props: Properties file.
     */
    public void drawTimer(Properties props) {
        getTimerLabel().setText(getCurrentTimer().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }
}
