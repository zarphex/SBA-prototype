package com.zarphex.Features;

import com.zarphex.Features.Calendar.CalendarFeature;
import java.time.Duration;

public class DeepFocusTracker {
    private Duration focusDurationTotal;
    private Duration sessionDurationMinutes;
    private FocusView currentView;
    private FocusDisplay currentDisplay;
    private CalendarFeature calendarLink;
    private PomodoroTimer pomodoroLink;

    public DeepFocusTracker() {
        this.focusDurationTotal = Duration.ZERO;
        this.currentView = FocusView.Week;
        this.currentDisplay = FocusDisplay.Heatmap;
    }

    public void syncFocusDuration(int minutes) {
        focusDurationTotal = focusDurationTotal.plus(Duration.ofMinutes(minutes));
    }
}
