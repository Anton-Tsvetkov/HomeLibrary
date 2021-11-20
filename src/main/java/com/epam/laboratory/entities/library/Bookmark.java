package com.epam.laboratory.entities.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "bookmark")
public class Bookmark {

    @JsonProperty("name")
    private String name;

    @JsonProperty("ISBN")
    private String ISBN;

    @JsonProperty("pageNumber")
    private int pageNumber;

    public Bookmark() {
    }

    public Bookmark(String name, String isbn, int pageNumber) {
        this.name = name;
        this.ISBN = isbn;
        this.pageNumber = pageNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "name='" + name + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
