package com.epam.laboratory.entities.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName(value = "book")
public class Book {

    @JsonProperty("bookTitle")
    private String title;

    @JsonProperty("author")
    private List<Author> authorList;

    @JsonProperty("pagesAmount")
    private int pagesAmount;

    @JsonProperty("ISBN")
    private String ISBN;

    @JsonProperty("issueYear")
    private int issueYear;

    public Book() {
    }

    public Book(String title, List<Author> author, int pagesAmount, String ISBN, int issueYear) {
        this.title = title;
        this.authorList = author;
        this.pagesAmount = pagesAmount;
        this.ISBN = ISBN;
        this.issueYear = issueYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public int getPagesAmount() {
        return pagesAmount;
    }

    public void setPagesAmount(int pagesAmount) {
        this.pagesAmount = pagesAmount;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + authorList +
                ", pagesAmount=" + pagesAmount +
                ", ISBN='" + ISBN + '\'' +
                ", issueYear=" + issueYear +
                '}';
    }
}
