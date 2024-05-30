package com.zarphex.Features;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Properties;

/**
 * The abstract timer class for all classes using a counting function.
 */
public abstract class TimerMeasurer extends Feature {
    private final JLabel TIMER_LABEL;
    private final JButton START_TIMER, PAUSE_TIMER, RESET_TIMER;
    private LocalTime setTimer, currentTimer;
    private Timer timerControl;

    /**
     * The default constructor.
     * @param props: Properties file.
     */
    public TimerMeasurer(Properties props) {
        super(props);

        // Instantiate all components.
        TIMER_LABEL = new JLabel();
        START_TIMER = new JButton(props.getProperty("startLabel"));
        PAUSE_TIMER = new JButton(props.getProperty("pauseLabel"));
        RESET_TIMER = new JButton(props.getProperty("resetLabel"));

        TIMER_LABEL.setFont(new Font(getFont(), Font.BOLD, 60));
        TIMER_LABEL.setForeground(new Color(250, 249,246));

        formatButton(START_TIMER);
        formatButton(PAUSE_TIMER);
        formatButton(RESET_TIMER);
    }

    /**
     * Display components on the panel.
     * @param props: Properties file.
     */
    public void addLabels(Properties props) {
        // Add labels to the panel.
        getPanel().add(TIMER_LABEL, "al center bottom, span, push, wrap");
        getPanel().add(START_TIMER, "al center, split, span");
        getPanel().add(PAUSE_TIMER, "al center, split, span");
        getPanel().add(RESET_TIMER, "al center, split, span, wrap");
    }

    /**
     * Initialise the timer.
     * @param props: Properties file.
     */
    public void initialiseTimer(Properties props) {
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

    // Abstract methods.
    public abstract void updateTimer(Properties props);

    public abstract void drawTimer(Properties props);

    // Getters
    public JLabel getTimerLabel() {
        return TIMER_LABEL;
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
