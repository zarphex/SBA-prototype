package com.zarphex.initial.Features.Calendar;

import com.zarphex.initial.Features.Priority;

import java.time.*;

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
