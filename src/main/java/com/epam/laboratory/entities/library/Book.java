package com.epam.laboratory.entities.library;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Book {

    @JsonProperty("bookName")
    private String bookName;

    @JsonProperty("releaseYear")
    private int releaseYear;

    @JsonProperty("pageCount")
    private int pageCount;

    @JsonProperty("ISBN")
    private String ISBN;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("author")
    private Author author;

    public Book() {
    }

    public Book(String bookName, int releaseYear, int pageCount, String ISBN, String publisher, Author author) {
        this.bookName = bookName;
        this.releaseYear = releaseYear;
        this.pageCount = pageCount;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", releaseYear=" + releaseYear +
                ", pageCount=" + pageCount +
                ", ISBN='" + ISBN + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author=" + author +
                '}';
    }
}
