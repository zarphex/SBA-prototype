package com.zarphex.Features;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

/**
 * The stopwatch feature class with time tracking capabilities in hours, minutes and seconds.
 */
public class Stopwatch extends Feature {
    private final JLabel STOPWATCH_LABEL;
    private final JButton START_STOPWATCH, PAUSE_STOPWATCH, LAP_STOPWATCH, RESET_STOPWATCH;
    private LocalTime currentTime;
    private Timer stopwatchControl;
    private ArrayList<JLabel> stopwatchLapsLabel;

    /**
     * Default constructor for the stopwatch.
     * @param props: Properties file.
     */
    public Stopwatch(Properties props) {
        super(props);

        // Instantiate the components.
        STOPWATCH_LABEL = new JLabel();
        stopwatchLapsLabel = new ArrayList<>();
        START_STOPWATCH = new JButton("start");
        PAUSE_STOPWATCH = new JButton("pause");
        LAP_STOPWATCH = new JButton("lap");
        RESET_STOPWATCH = new JButton("reset");

        createGUI();
    }

    /**
     * Draw the GUI.
     */
    @Override
    public void createGUI() {
        super.createGUI();

        STOPWATCH_LABEL.setFont(new Font("Arial", Font.PLAIN, 24));

        makeStopwatch();

        // Add elements to the panel.
        getPanel().add(STOPWATCH_LABEL, "al center, span, wrap");
        getPanel().add(START_STOPWATCH, "al center, span, wrap");
        getPanel().add(PAUSE_STOPWATCH, "al center, span, wrap");
        getPanel().add(LAP_STOPWATCH, "al center, span, wrap");
        getPanel().add(RESET_STOPWATCH, "al center top, span, push, wrap");
    }

    /**
     * Create the stopwatch with all button functions.
     */
    public void makeStopwatch() {
        // Set default stopwatch value.
        currentTime = LocalTime.MIN;
        drawStopwatch();

        // Call to update the stopwatch every second.
        stopwatchControl = new Timer(1000, e -> updateStopwatch());

        // Button functions.
        START_STOPWATCH.addActionListener(e -> stopwatchControl.start());
        PAUSE_STOPWATCH.addActionListener(e -> stopwatchControl.stop());
        LAP_STOPWATCH.addActionListener(e -> drawLap(currentTime));
        RESET_STOPWATCH.addActionListener(e -> {
            stopwatchControl.stop();
            currentTime = LocalTime.MIN;
            drawStopwatch();
            deleteLaps();
        });
    }

    /**
     * Add a second to the stopwatch and draw it.
     */
    public void updateStopwatch() {
        currentTime = currentTime.plusSeconds(1);
        drawStopwatch();
    }

    /**
     * Display the stopwatch in the valid format.
     */
    public void drawStopwatch() {
        STOPWATCH_LABEL.setText(currentTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    /**
     * Create a new lap based on the current time.
     * @param time: The current time.
     */
    public void drawLap(LocalTime time) {
        // Create new Label component.
        JLabel newLap = new JLabel();
        newLap.setFont(new Font("Arial", Font.PLAIN, 12));
        newLap.setText(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        // Add the label to the list of labels.
        stopwatchLapsLabel.add(newLap);

        // Draw every label.
        for (JLabel lap : stopwatchLapsLabel) {
            getPanel().add(lap, "al center, top, span, wrap");
        }
    }

    /**
     * Remove every lap recorded and redraw the entire panel.
     */
    public void deleteLaps() {
        for (JLabel lap : stopwatchLapsLabel) {
            getPanel().remove(lap);
            getPanel().revalidate();
            getPanel().repaint();
        }
        stopwatchLapsLabel.clear();
    }
}
