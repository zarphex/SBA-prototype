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
    private final JLabel modeLabel;
    private final int DEFAULT_WORK_TIMER,
                DEFAULT_SHORT_BREAK,
                DEFAULT_LONG_BREAK,
                DEFAULT_CYCLE_INTERVAL;
    private LocalTime shortBreakLength, longBreakLength, workLength;
    private int numberCyclesInterval, currentCycle;
    private PomodoroStatus status;

    /**
     * The default constructor.
     * @param props: Properties file.
     */
    public PomodoroTimer(Properties props) {
        super(props);
        status = PomodoroStatus.work;
        currentCycle = 1;

        // Instantiate all components.
        modeLabel = new JLabel();
        modeLabel.setFont(new Font("Arial", Font.ITALIC, 16));

        DEFAULT_WORK_TIMER = Integer.parseInt(props.getProperty("defaultWork"));
        DEFAULT_SHORT_BREAK = Integer.parseInt(props.getProperty("defaultShort"));
        DEFAULT_LONG_BREAK = Integer.parseInt(props.getProperty("defaultLong"));
        DEFAULT_CYCLE_INTERVAL = Integer.parseInt(props.getProperty("defaultCycle"));

        shortBreakLength = LocalTime.of(0, 0, 3);
        longBreakLength = LocalTime.of(0, DEFAULT_LONG_BREAK);
        workLength = LocalTime.of(0, 0, 4);
        numberCyclesInterval = DEFAULT_CYCLE_INTERVAL;

        createGUI(props);
        makeTimer(props);
        addLabels(props);
        addArrowComponents();
    }

    /**
     * Create the pomodoro timer.
     */
    public void makeTimer(Properties props) {
        super.makeTimer(props);
        setSetTimer(workLength);
        setCurrentTimer(getSetTimer());
        drawTimer(props);
        getPanel().add(modeLabel, "al center, span, wrap");
    }

    public void updateTimer(Properties props) {
        if (status.equals(PomodoroStatus.work)) {
            startWorkTimer(props);
        } else {
            startBreakTimer(props);
        }
    }

    public void startWorkTimer(Properties props) {
        if (!getCurrentTimer().equals(LocalTime.MIN)) {
            setCurrentTimer(getCurrentTimer().minusSeconds(1));
            drawTimer(props);
        } else if (currentCycle != numberCyclesInterval) {
            currentCycle++;
            status = PomodoroStatus.shortBreak;
            setSetTimer(shortBreakLength);
            setCurrentTimer(getSetTimer());
            drawTimer(props);
        } else {
            currentCycle = 1;
            status = PomodoroStatus.longBreak;
            setSetTimer(longBreakLength);
            setCurrentTimer(getSetTimer());
            drawTimer(props);
        }
    }

    public void startBreakTimer(Properties props) {
        if (!getCurrentTimer().equals(LocalTime.MIN)) {
            setCurrentTimer(getCurrentTimer().minusSeconds(1));
            drawTimer(props);
        } else {
            status = PomodoroStatus.work;
            setSetTimer(workLength);
            setCurrentTimer(getSetTimer());
            drawTimer(props);
        }
    }

    public void drawTimer(Properties props) {
        if (status.equals(PomodoroStatus.work)) {
            modeLabel.setText(props.getProperty("workText"));
        } else if (status.equals(PomodoroStatus.shortBreak)) {
            modeLabel.setText(props.getProperty("shortText"));
        } else {
            modeLabel.setText(props.getProperty("longText"));
        }
        getTimerLabel().setText(getCurrentTimer().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }
}
