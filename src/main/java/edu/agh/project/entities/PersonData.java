package edu.agh.project.entities;

import javax.persistence.Embeddable;

@Embeddable
public class PersonData {
    private String name;
    private String surname;

    public PersonData() {}

    public PersonData(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
