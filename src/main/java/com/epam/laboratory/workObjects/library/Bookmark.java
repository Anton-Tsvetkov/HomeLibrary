package com.epam.laboratory.workObjects.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "bookmark")
public class Bookmark {

    @JsonProperty("name")
    private String name;

    @JsonProperty("bookTitle")
    private String bookTitle;

    @JsonProperty("pageNumber")
    private int pageNumber;

    public Bookmark() {
    }

    public Bookmark(String name, String bookTitle, int pageNumber) {
        this.name = name;
        this.bookTitle = bookTitle;
        this.pageNumber = pageNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
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
                ", bookTitle='" + bookTitle + '\'' +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
