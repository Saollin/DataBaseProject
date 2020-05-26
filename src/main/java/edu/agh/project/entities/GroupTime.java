package edu.agh.project.entities;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Time;

@Embeddable
public class GroupTime {

    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek day;
    private Time hourStart;
    private Time hourEnd;

}
