package com.zarphex.initial.Features.Calendar;
import java.time.*;

public class TimeBlock {
    private String name;
    private ColoursTimeBlock colour;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private int repeatInterval;
    private boolean reminderOn;
    private Duration durationBeforeReminder;
}
