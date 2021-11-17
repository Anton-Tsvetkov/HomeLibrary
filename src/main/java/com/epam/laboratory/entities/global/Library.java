package com.epam.laboratory.entities.global;

import com.epam.laboratory.entities.library.Author;
import com.epam.laboratory.entities.library.Book;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonRootName(value = "catalog")
public class Library extends GlobalObject {

    private List<Book> bookList = new ArrayList<>();
    private List<Author> authorList = new ArrayList<>();

    @Override
    public List<Book> getList() {
        if (bookList == null) {
            bookList = new ArrayList<>();
        }
        return this.bookList;
    }

    public List<Author> getAuthorsList() {
        if (authorList == null) {
            authorList = new ArrayList<>();
        }
        return this.authorList;
    }

    @Override
    public void addObjectToList(Object object) {
        if (object instanceof Book) {
            List<Book> objectList = new ArrayList<>((Collection<? extends Book>) object);
            bookList.addAll(objectList);
        } else {
            List<Author> objectList = new ArrayList<>((Collection<? extends Author>) object);
            authorList.addAll(objectList);
        }
    }

    @Override
    public void setList(List<?> objects) {
        if (objects.get(0) instanceof Book) {
            bookList = (List<Book>) objects;
        } else {
            authorList = (List<Author>) objects;
        }
    }

    @Override
    public String toString() {
        return "Library{" +
                "bookList=" + bookList +
                ", authorList=" + authorList +
                '}';
    }
}
