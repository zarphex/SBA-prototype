package com.zarphex.Features;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class TimerFeature extends Feature {
    private final JLabel TIMER_LABEL;
    private final JButton START_TIMER, PAUSE_TIMER, RESET_TIMER;
    private LocalTime setTimer, currentTimer;
    private Timer timerControl;

    public TimerFeature(Properties props) {
        super(props);

        TIMER_LABEL = new JLabel();
        START_TIMER = new JButton("Start");
        PAUSE_TIMER = new JButton("Pause");
        RESET_TIMER = new JButton("Reset");
        createGUI();
    }

    @Override
    public void createGUI() {
        super.createGUI();

        TIMER_LABEL.setFont(new Font("Arial", Font.PLAIN, 24));

        makeClock();

        // Add elements to the panel.
        getPanel().add(TIMER_LABEL, "al center, span, wrap");
        getPanel().add(START_TIMER, "al center, span, wrap");
        getPanel().add(PAUSE_TIMER, "al center, span, wrap");
        getPanel().add(RESET_TIMER, "al center top, span, push, wrap");
    }

    public void makeClock() {
        setTimer = LocalTime.of(0, 0, 10);
        currentTimer = setTimer;
        drawTimer();

        timerControl = new Timer(1000, e -> updateTime());

        START_TIMER.addActionListener(e -> timerControl.start());
        PAUSE_TIMER.addActionListener(e -> timerControl.stop());
        RESET_TIMER.addActionListener(e -> {
            timerControl.stop();
            currentTimer = setTimer;
            drawTimer();
        });
    }

    public void updateTime() {
        if (currentTimer != LocalTime.MIN) {
            currentTimer = currentTimer.minusSeconds(1);
            drawTimer();
        } else {
            timerControl.stop();
        }

    }

    public void drawTimer() {
        String timerDisplay = currentTimer.format(DateTimeFormatter.ISO_LOCAL_TIME);
        TIMER_LABEL.setText(timerDisplay);
    }
}
