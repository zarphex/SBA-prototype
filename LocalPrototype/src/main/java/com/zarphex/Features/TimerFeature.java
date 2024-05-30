package com.zarphex.Features;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * The timer feature class with countdown capabilities in hours, minutes and seconds.
 */
public class TimerFeature extends TimerMeasurer {
    /**
     * Default constructor.
     * @param props: Properties file.
     */
    public TimerFeature(Properties props) {
        super(props);
        makeTimer(props);
        createGUI(props);
        addLabels(props);
        addArrowComponents();
    }

    /**
     * Create the timer with button functioning.
     */
    public void makeTimer(Properties props) {
        super.makeTimer(props);
        // Set the timer to start counting down from.
        setSetTimer(LocalTime.of(0, 0, 10));
        setCurrentTimer(getSetTimer());
        drawTimer(props);
    }

    /**
     * Update the display of the timer.
     */
    public void updateTimer(Properties props) {
        // Count down but stop when timer reaches zero.
        if (getCurrentTimer() != LocalTime.MIN) {
            setCurrentTimer(getCurrentTimer().minusSeconds(1));
            drawTimer(props);
        } else {
            getTimerControl().stop();
        }
    }

    /**
     * Draw the timer display.
     */
    public void drawTimer(Properties props) {
        getTimerLabel().setText(getCurrentTimer().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

}
