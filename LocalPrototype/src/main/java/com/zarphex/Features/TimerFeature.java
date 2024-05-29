package com.zarphex.Features;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * The timer feature class with countdown capabilities in hours, minutes and seconds.
 */
public class TimerFeature extends Feature {
    private final JLabel TIMER_LABEL;
    private final JButton START_TIMER, PAUSE_TIMER, RESET_TIMER;
    private LocalTime setTimer, currentTimer;
    private Timer timerControl;

    /**
     * Default constructor.
     * @param props: Properties file.
     */
    public TimerFeature(Properties props) {
        super(props);

        // Instantiate all components.
        TIMER_LABEL = new JLabel();
        START_TIMER = new JButton("Start");
        PAUSE_TIMER = new JButton("Pause");
        RESET_TIMER = new JButton("Reset");

        createGUI();
    }

    /**
     * Implement the GUI.
     */
    @Override
    public void createGUI() {
        super.createGUI();

        TIMER_LABEL.setFont(new Font("Arial", Font.PLAIN, 24));

        makeTimer();

        // Add elements to the panel.
        getPanel().add(TIMER_LABEL, "al center, span, wrap");
        getPanel().add(START_TIMER, "al center, span, wrap");
        getPanel().add(PAUSE_TIMER, "al center, span, wrap");
        getPanel().add(RESET_TIMER, "al center top, span, push, wrap");
    }

    /**
     * Create the timer with button functioning.
     */
    public void makeTimer() {
        // Set the timer to start counting down from.
        setTimer = LocalTime.of(0, 0, 10);
        currentTimer = setTimer;
        drawTimer();

        // Call to update the timer every second.
        timerControl = new Timer(1000, e -> updateTime());

        // Button action listeners.
        START_TIMER.addActionListener(e -> timerControl.start());
        PAUSE_TIMER.addActionListener(e -> timerControl.stop());
        RESET_TIMER.addActionListener(e -> {
            timerControl.stop();
            currentTimer = setTimer;
            drawTimer();
        });
    }

    /**
     * Update the display of the timer.
     */
    public void updateTime() {
        // Count down but stop when timer reaches zero.
        if (currentTimer != LocalTime.MIN) {
            currentTimer = currentTimer.minusSeconds(1);
            drawTimer();
        } else {
            timerControl.stop();
        }

    }

    /**
     * Draw the timer display.
     */
    public void drawTimer() {
        TIMER_LABEL.setText(currentTimer.format(DateTimeFormatter.ISO_LOCAL_TIME));
    }
}
