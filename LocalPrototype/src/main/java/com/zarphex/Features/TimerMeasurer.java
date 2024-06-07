package com.zarphex.Features;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Properties;
/**
 * The abstract timer class for all classes using a counting function.
 */
public abstract class TimerMeasurer extends Feature {
    private final JFormattedTextField TIMER_LABEL;
    private final JButton START_TIMER, RESET_TIMER;
    private LocalTime setTimer, currentTimer;
    private Timer timerControl;
    private boolean isTimerActive;

    /**
     * The default constructor.
     * @param props: Properties file.
     */
    public TimerMeasurer(Properties props) {
        super(props);

        getFeaturePanel().setPreferredSize(new Dimension(450, 300));
        getFeaturePanel().setBackground(null);
        // Instantiate all components.
        TIMER_LABEL = new JFormattedTextField(createFormatter("##:##:##"));
        START_TIMER = new JButton(props.getProperty("startLabel"));
        RESET_TIMER = new JButton(props.getProperty("resetLabel"));

        TIMER_LABEL.setFont(new Font(getFont(), Font.BOLD, 60));
        adjustLabelColour(TimerStatus.revert);
        TIMER_LABEL.setText("00:00:00");
        TIMER_LABEL.setEditable(true);
        TIMER_LABEL.setBackground(getLIGHT_PURPLE_COLOR());
        TIMER_LABEL.setBorder(null);

        formatButton(START_TIMER, RESET_TIMER);
        isTimerActive = false;
    }

    /**
     * Display components on the panel.
     * @param props: Properties file.
     */
    public void addLabels(Properties props) {
        // Add labels to the panel.
        getFeaturePanel().add(TIMER_LABEL, "pos 0.5al 0.4al");
        getFeaturePanel().add(START_TIMER, "pos 0.3al 0.85al");
        getFeaturePanel().add(RESET_TIMER, "pos 0.7al 0.85al");
        getBACKGROUND_PANEL().add(getFeaturePanel());
    }

    /**
     * Initialise the timer.
     * @param props: Properties file.
     */
    public void initialiseTimer(Properties props) {
        // Call to update the timer every second.
        setTimerControl(new Timer(1000, e -> updateTimer(props)));

        // Button action listeners.
        getSTART_TIMER().addActionListener(e -> {
            if (isTimerActive) {
                getTimerControl().stop();
                START_TIMER.setText(props.getProperty("startLabel"));
            } else {
                // Format the current time string of the text field.
                String[] listOfValues = getTimerLabel().getText().split(":");
                LocalTime newTime = LocalTime.of(Integer.parseInt(listOfValues[0]),
                        Integer.parseInt(listOfValues[1]),
                        Integer.parseInt(listOfValues[2]));
                // Change the set timer and current timer to match.
                setSetTimer(newTime);
                setCurrentTimer(newTime);
                // Start the timer.
                if (getCurrentTimer() != LocalTime.MIN) {
                    adjustLabelColour(TimerStatus.start);
                    getTimerControl().start();
                    TIMER_LABEL.setEditable(false);
                    START_TIMER.setText(props.getProperty("pauseLabel"));
                }
            }
            setTimerStatus(!isTimerActive);
        });

        getRESET_TIMER().addActionListener(e -> {
            getTimerControl().stop();
            adjustLabelColour(TimerStatus.revert);
            setCurrentTimer(getSetTimer());
            drawTimer(props);
            TIMER_LABEL.setEditable(true);
            START_TIMER.setText(props.getProperty("startLabel"));
            setTimerStatus(false);
        });
    }

    /**
     * Styles the main timer label.
     * @param status: The mode to revert to.
     */
    public void adjustLabelColour(TimerStatus status) {
        if (status.equals(TimerStatus.start)) {
            TIMER_LABEL.setForeground(getCREAM_COLOR());
        } else if (status.equals(TimerStatus.revert)) {
            TIMER_LABEL.setForeground(Color.black);
        }
    }

    /**
     * The status of the timer label.
     */
    public enum TimerStatus {
        start,
        revert;
    }

    // Abstract methods.
    public abstract void updateTimer(Properties props);

    public abstract void drawTimer(Properties props);

    // Getters
    public JFormattedTextField getTimerLabel() {
        return TIMER_LABEL;
    }

    public JButton getSTART_TIMER() {
        return START_TIMER;
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

    public boolean getTimerStatus() {
        return isTimerActive;
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

    public void setTimerStatus(boolean status) {
        isTimerActive = status;
    }
}
