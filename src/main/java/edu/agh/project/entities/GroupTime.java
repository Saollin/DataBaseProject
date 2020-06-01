package edu.agh.project.entities;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Time;

@Embeddable
public class GroupTime {

    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    private Time hourStart;
    private Time hourEnd;

    public GroupTime() {}

    public GroupTime(DayOfWeek day, Time hourStart, Time hourEnd) {
        this.day = day;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
    }

    @Override
    public String toString() {
        return day.getName() + ": " + hourStart + "-" + hourEnd;
    }
}
