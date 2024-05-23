package com.zarphex.Features.Calendar;
import com.zarphex.Features.Priority;

import java.time.Duration;
import java.time.LocalDateTime;

public class Event {
    private String name;
    private ColoursEvent colour;
    private String description;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private String location;
    private Priority priority;
    private int repeatInterval;
    private boolean reminderOn;
    private Duration durationBeforeReminder;
}
