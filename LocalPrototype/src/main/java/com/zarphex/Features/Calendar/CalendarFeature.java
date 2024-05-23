package com.zarphex.Features.Calendar;
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
    public CalendarFeature calendarExport() {
        return this;
    }
    public int calendarSync(CalendarFeature calendar) {
        for (Event events : calendar.eventList) {
            eventList.add(events);
        }
        for (Reminder reminder : calendar.reminderList) {
            reminderList.add(reminder);
        }
        for (TimeBlock timeblock : calendar.timeBlockList) {
            timeBlockList.add(timeblock);
        }
        return 1;
    }
}
