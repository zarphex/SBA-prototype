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
        initialiseTimer(props);
        createGUI(props);
        addLabels(props);
        addArrowComponents();
    }

    /**
     * Initialise timer.
     * @param props: Properties file.
     */
    public void initialiseTimer(Properties props) {
        super.initialiseTimer(props);
        // Set the timer to start counting down from.
        setSetTimer(LocalTime.of(0, 0, 10));
        setCurrentTimer(getSetTimer());
        drawTimer(props);
    }

    /**
     * Update the timer with the next time.
     * @param props: Properties file.
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
     * @param props: Properties file.
     */
    public void drawTimer(Properties props) {
        getTimerLabel().setText(getCurrentTimer().format(DateTimeFormatter.ISO_LOCAL_TIME));
    }

}
