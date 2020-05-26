package edu.agh.project.entities;

public enum DayOfWeek {
    MONDAY("Poniedziałek"),
    TUESDAY("Wtorek"),
    WEDNESDAY("Środa"),
    THURSDAY("Czwartek"),
    FRIDAY("Piątek"),
    SATURDAY("Sobota"),
    SUNDAY("Niedziela");

    private String name;

    DayOfWeek(String name) {
        this.name = name;
    }
}
