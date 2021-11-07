package com.epam.laboratory.workObjects.globalObjects;

import com.epam.laboratory.workObjects.library.Author;
import com.epam.laboratory.workObjects.library.Book;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonRootName(value = "Library")
public class Library extends GlobalObject {

    protected List<Book> bookList = new ArrayList<>();
    protected List<Author> authorList = new ArrayList<>();

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
    public void addObjectsToList(List<?> objects) {
        if (objects.get(0) instanceof Book) {
            List<Book> objectList = new ArrayList<>((Collection<? extends Book>) objects);
            bookList.addAll(objectList);
        } else {
            List<Author> objectList = new ArrayList<>((Collection<? extends Author>) objects);
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
                '}';
    }
}
