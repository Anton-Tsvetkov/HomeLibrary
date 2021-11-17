package com.epam.laboratory.entities.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "author")
public class Author {

    @JsonProperty("name")
    private String name;

    @JsonProperty("secondName")
    private String secondName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("dob")
    private String dob;

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, String secondName, String lastName, String dob) {
        this.name = name;
        this.secondName = secondName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
