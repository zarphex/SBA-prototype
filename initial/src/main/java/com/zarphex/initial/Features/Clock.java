package com.zarphex.initial.Features;
import java.time.*;

/**
 * The Clock feature showing the current local time and date.
 */
public class Clock {
    private boolean isTwentyFourHour;
    private LocalTime currentTime;

    /***
     * The default constructor.
     */
    public Clock() {
        this.isTwentyFourHour = false;
        this.currentTime = LocalTime.now();
    }

    public void changeMode() {
        isTwentyFourHour = !isTwentyFourHour;
    }

    public void setTime(LocalTime time) {
        currentTime = time;
    }
}
