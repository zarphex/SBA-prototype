package com.zarphex.initial.Features;
import com.zarphex.initial.Features.Calendar.CalendarFeature;

import java.time.*;

public class DeepFocusTracker {
    private Duration focusDurationTotal;
    private Duration sessionDuration;
    private FocusView currentView;
    private FocusDisplay currentDisplay;
    private CalendarFeature calendarLink;
    private PomodoroTimer pomodoroLink;

    public DeepFocusTracker() {}

    public void syncFocusDuration(int duration) {}
    public void addFocusSession(int duration) {}
}
