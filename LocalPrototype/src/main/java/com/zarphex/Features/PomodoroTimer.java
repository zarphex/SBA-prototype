package com.zarphex.Features;

import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.swing.*;

/**
 * The Pomodoro Timer feature class for measuring durations of work and break.
 */
public class PomodoroTimer extends TimerMeasurer {
    private static final String WORK_TEXT = "workText";
    private static final String SHORT_BREAK_TEXT = "shortText";
    private static final String LONG_BREAK_TEXT = "longText";
    private static final String SKIP_BUTTON_TEXT = "skipLabel";

    private final JLabel MODE_LABEL;
    private final JButton SKIP_TIMER;

    private LocalTime shortBreakDuration, longBreakDuration, workDuration;
    private int cyclesBeforeLongBreak, currentCycle;
    private PomodoroStatus currentStatus;

    /**
     * The default constructor.
     * @param props: Properties file.
     */
    public PomodoroTimer(Properties props) {
        super(props);
        currentCycle = 1;

        MODE_LABEL = new JLabel();
        MODE_LABEL.setFont(new Font(getFont(), Font.ITALIC, 16));
        MODE_LABEL.setForeground(new Color(250, 249,246));

        SKIP_TIMER = new JButton(props.getProperty(SKIP_BUTTON_TEXT));
        formatButton(SKIP_TIMER);

        workDuration = LocalTime.of(0, Integer.parseInt(props.getProperty("defaultWork")));
        shortBreakDuration = LocalTime.of(0, Integer.parseInt(props.getProperty("defaultShort")));
        longBreakDuration = LocalTime.of(0, Integer.parseInt(props.getProperty("defaultLong")));
        cyclesBeforeLongBreak = Integer.parseInt(props.getProperty("defaultCycle"));

        createGUI(props);
        initialiseTimer(props);
        addLabels(props);
        getPanel().add(SKIP_TIMER, "al center, span, wrap");
        addArrowComponents();
    }

    /**
     * Create the pomodoro timer.
     * @param props: Properties file.
     */
    @Override
    public void initialiseTimer(Properties props) {
        super.initialiseTimer(props);
        setTimer(props, PomodoroStatus.WORK, workDuration);

        SKIP_TIMER.addActionListener(e -> nextTimer(props));

        getPanel().add(MODE_LABEL, "al center, span, wrap");
    }

    /**
     * Start the respective timer.
     * @param props: Properties file.
     */
    public void updateTimer(Properties props) {
        if (currentStatus.equals(PomodoroStatus.WORK)) {
            startWorkTimer(props);
        } else {
            startBreakTimer(props);
        }
    }

    /**
     * Start the focus timer.
     * @param props: Properties file.
     */
    public void startWorkTimer(Properties props) {
        if (!getCurrentTimer().equals(LocalTime.MIN)) {
            setCurrentTimer(getCurrentTimer().minusSeconds(1));
            drawTimer(props);
        } else if (currentCycle != cyclesBeforeLongBreak) {
            currentCycle++;
            setTimer(props, PomodoroStatus.SHORT_BREAK, shortBreakDuration);
        } else {
            currentCycle = 1;
            setTimer(props, PomodoroStatus.LONG_BREAK, longBreakDuration);
        }
    }

    /**
     * Start the break timer.
     * @param props: Properties file.
     */
    public void startBreakTimer(Properties props) {
        if (!getCurrentTimer().equals(LocalTime.MIN)) {
            setCurrentTimer(getCurrentTimer().minusSeconds(1));
            drawTimer(props);
        } else {
            setTimer(props, PomodoroStatus.WORK, workDuration);
        }
    }

    /**
     * Display the current timer.
     * @param props: Properties file.
     */
    public void drawTimer(Properties props) {
        String modeText = switch (currentStatus) {
            case WORK -> props.getProperty(WORK_TEXT);
            case SHORT_BREAK -> props.getProperty(SHORT_BREAK_TEXT);
            case LONG_BREAK -> props.getProperty(LONG_BREAK_TEXT);
        };
        MODE_LABEL.setText(modeText);
        getTimerLabel().setText(getCurrentTimer().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

    /**
     * Set the new timer value.
     * @param props: Properties file.
     * @param newStatus: The next status of the timer.
     * @param time: The time to be set.
     */
    public void setTimer(Properties props, PomodoroStatus newStatus, LocalTime time) {
        currentStatus = newStatus;
        setSetTimer(time);
        setCurrentTimer(getSetTimer());
        drawTimer(props);
    }

    /**
     * Cycle to the new timer stage.
     * @param props: Properties file.
     */
    public void nextTimer(Properties props) {
        // Skip button action listener.
        getTimerControl().stop();
        adjustLabelColour(timerStatus.revert);
        getTimerLabel().setEditable(true);

        if (currentStatus.equals(PomodoroStatus.WORK) && currentCycle == cyclesBeforeLongBreak) {
            currentCycle = 1;
            setTimer(props, PomodoroStatus.LONG_BREAK, longBreakDuration);
        } else if (currentStatus.equals(PomodoroStatus.WORK)) {
            currentCycle++;
            setTimer(props, PomodoroStatus.SHORT_BREAK, shortBreakDuration);
        } else {
            setTimer(props, PomodoroStatus.WORK, workDuration);
        }
    }

    /**
     * Enumeration options for the status of the timer.
     */
    private enum PomodoroStatus {
        WORK,
        SHORT_BREAK,
        LONG_BREAK
    }
}
