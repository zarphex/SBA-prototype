package com.zarphex.Features;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Properties;

public abstract class TimerMeasurer extends Feature {
    private final JLabel timerLabel;
    private final JButton START_TIMER, PAUSE_TIMER, RESET_TIMER;
    private LocalTime setTimer, currentTimer;
    private Timer timerControl;

    public TimerMeasurer(Properties props) {
        super(props);

        // Instantiate all components.
        timerLabel = new JLabel();
        START_TIMER = new JButton(props.getProperty("startLabel"));
        PAUSE_TIMER = new JButton(props.getProperty("pauseLabel"));
        RESET_TIMER = new JButton(props.getProperty("resetLabel"));
    }

    @Override
    public void createGUI(Properties props) {
        super.createGUI(props);

        timerLabel.setFont(new Font("Arial", Font.PLAIN, 24));
    }

    public void addLabels(Properties props) {
        // Add labels to the panel.
        getPanel().add(timerLabel, "al center bottom, span, push, wrap");
        getPanel().add(START_TIMER, "al center, span, wrap");
        getPanel().add(PAUSE_TIMER, "al center, span, wrap");
        getPanel().add(RESET_TIMER, "al center, span, wrap");
    }

    public void makeTimer(Properties props) {
        // Call to update the timer every second.
        setTimerControl(new Timer(1000, e -> updateTimer(props)));

        // Button action listeners.
        getSTART_TIMER().addActionListener(e -> getTimerControl().start());
        getPAUSE_TIMER().addActionListener(e -> getTimerControl().stop());
        getRESET_TIMER().addActionListener(e -> {
            getTimerControl().stop();
            setCurrentTimer(getSetTimer());
            drawTimer(props);
        });
    }

    public abstract void updateTimer(Properties props);

    public abstract void drawTimer(Properties props);

    // Getters
    public JLabel getTimerLabel() {
        return timerLabel;
    }

    public JButton getSTART_TIMER() {
        return START_TIMER;
    }

    public JButton getPAUSE_TIMER() {
        return PAUSE_TIMER;
    }

    public JButton getRESET_TIMER() {
        return RESET_TIMER;
    }

    public LocalTime getSetTimer() {
        return setTimer;
    }

    public LocalTime getCurrentTimer() {
        return currentTimer;
    }

    public Timer getTimerControl() {
        return timerControl;
    }

    // Setters
    public void setSetTimer(LocalTime setTimer) {
        this.setTimer = setTimer;
    }

    public void setCurrentTimer(LocalTime currentTimer) {
        this.currentTimer = currentTimer;
    }

    public void setTimerControl(Timer timerControl) {
        this.timerControl = timerControl;
    }
}
