package com.zarphex.Features;

import javax.swing.*;
import java.time.LocalTime;
import java.util.Properties;
/**
 * The Clock feature showing the current local time and date.
 */
public class Clock {
    private final JLabel CREAM_BACKGROUND;
    private boolean isTwentyFourHour;
    private LocalTime currentTime;

    /***
     * The default constructor.
     */
    public Clock(JFrame frame, Properties props) {
        this.isTwentyFourHour = false;
        this.currentTime = LocalTime.now();

        CREAM_BACKGROUND = new JLabel(new ImageIcon(props.getProperty("creamBackgroundImage")));
        frame.add(CREAM_BACKGROUND);
    }

    public void changeMode() {
        isTwentyFourHour = !isTwentyFourHour;
    }

    public void setTime(LocalTime time) {
        currentTime = time;
    }
}
