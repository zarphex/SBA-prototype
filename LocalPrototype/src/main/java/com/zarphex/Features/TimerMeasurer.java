package com.zarphex.Features;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Properties;
import javax.swing.text.MaskFormatter;
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

        // Instantiate all components.
        TIMER_LABEL = new JFormattedTextField(createFormatter("##:##:##"));
        START_TIMER = new JButton(props.getProperty("startLabel"));
        RESET_TIMER = new JButton(props.getProperty("resetLabel"));

        TIMER_LABEL.setFont(new Font(getFont(), Font.BOLD, 60));
        adjustLabelColour(TimerStatus.revert);
        TIMER_LABEL.setText("00:00:00");
        TIMER_LABEL.setEditable(true);

        formatButton(START_TIMER, RESET_TIMER);
        isTimerActive = false;
    }

    /**
     * Display components on the panel.
     * @param props: Properties file.
     */
    public void addLabels(Properties props) {
        // Add labels to the panel.
        getPanel().add(TIMER_LABEL, "al center bottom, span, push, wrap");
        getPanel().add(START_TIMER, "al center, split, span");
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

    /**
     * Styles the main timer label.
     * @param status: The mode to revert to.
     */
    public void adjustLabelColour(TimerStatus status) {
        if (status.equals(TimerStatus.start)) {
            TIMER_LABEL.setBackground(null);
            TIMER_LABEL.setForeground(new Color(250, 249,246));
        } else if (status.equals(TimerStatus.revert)) {
            TIMER_LABEL.setBackground(new Color(250, 249,246));
            TIMER_LABEL.setForeground(Color.black);
            TIMER_LABEL.setBorder(null);
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
