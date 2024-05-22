package com.zarphex.initial.Features.Calendar;
import java.util.ArrayList;

public class CalendarFeature {
    private ArrayList<Event> eventList;
    private ArrayList<Reminder> reminderList;
    private ArrayList<TimeBlock> timeBlockList;
    private boolean reminderOn;
    private CalendarView view;

    public CalendarFeature() {}

    public void scheduleEvent(Event event) {}
    public void createReminder(Reminder reminder) {}
    public void scheduleTimeBlock(TimeBlock timeBlock) {}
    public void syncCalendar(CalendarFeature calendar) {}
    public void calendarImport(CalendarFeature calendar) {}
    public CalendarFeature calendarExport() {}
    public boolean calendarSync(CalendarFeature calendar) {}
    public String reminderUser(Reminder reminder) {}
}
