package com.epam.laboratory.entities.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "author")
public class Author {

    @JsonProperty("name")
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                '}';
    }
}
