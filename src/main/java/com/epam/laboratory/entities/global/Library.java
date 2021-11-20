package com.epam.laboratory.entities.global;

import com.epam.laboratory.entities.library.Author;
import com.epam.laboratory.entities.library.Book;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonRootName(value = "catalog")
public class Library extends GlobalObject {

    @JsonProperty("catalog")
    private List<Book> catalog;

    @JsonProperty("authorList")
    private List<Author> authorList;

    @Override
    public List<Book> getList() {
        if (catalog == null) {
            catalog = new ArrayList<>();
        }
        return this.catalog;
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
            catalog.add((Book) object);
        } else {
            authorList.add((Author) object);
        }
    }

    @Override
    public void setList(List<?> objects) {
        if (objects.get(0) instanceof Book) {
            catalog = (List<Book>) objects;
        } else {
            authorList = (List<Author>) objects;
        }
    }

    @Override
    public String toString() {
        return "Library{" +
                "bookList=" + catalog +
                ", authorList=" + authorList +
                '}';
    }
}
