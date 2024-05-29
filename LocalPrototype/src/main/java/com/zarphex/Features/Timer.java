package com.zarphex.Features;

import javax.swing.*;
import java.util.Properties;

public class Timer extends Feature {
    private final JLabel TIMER_LABEL;
    private final JButton START_TIMER, PAUSE_TIMER, RESET_TIMER;

    public Timer(Properties props) {
        super(props);

        TIMER_LABEL = new JLabel("a");
        START_TIMER = new JButton("Start");
        PAUSE_TIMER = new JButton("Pause");
        RESET_TIMER = new JButton("Reset");
        createGUI();
    }

    public void createGUI() {
        super.createGUI();

        // Add elements to the panel.
        getPanel().add(TIMER_LABEL, "al center, span, wrap");
        getPanel().add(START_TIMER, "al center, span, wrap");
        getPanel().add(PAUSE_TIMER, "al center, span, wrap");
        getPanel().add(RESET_TIMER, "al center, span, wrap");
    }
}
