package com.epam.laboratory.entities.system;


import com.epam.laboratory.util.ConfigurationDataUsage;
import com.epam.laboratory.util.parsers.DataLoader;
import com.epam.laboratory.entities.global.Library;
import com.epam.laboratory.entities.library.Author;
import com.epam.laboratory.entities.library.Book;

import java.util.ArrayList;
import java.util.List;

// ADD & REMOVE BOOKS, AUTHORS
public class Librarian {

    private final DataLoader dataLoader = new DataLoader();
    private final Library library;
    private final ConfigurationDataUsage configurationDataUsage = new ConfigurationDataUsage();

    public Librarian() {
        library = (Library) dataLoader.getDataFromFile(configurationDataUsage.getPathToLibraryJsonFile());
    }

    private void updateLibraryFile() {
        dataLoader.updateFile(configurationDataUsage.getPathToLibraryJsonFile(), this.library);
    }


    // ADDING FUNCTIONALITY

    public void addNewBookInLibrary(Book book) {
        library.addObjectToList(book);
        updateLibraryFile();
    }

    public void addNewBooksInLibraryFromFile(String pathToFile) {
        library.addObjectToList(dataLoader.getDataFromFile(pathToFile).getList());
        updateLibraryFile();
    }

    public void addNewAuthor(Author author) {
        library.addObjectToList(author);
        updateLibraryFile();
    }


    // REMOVING FUNCTIONALITY

    public void removeBooksFromLibrary(List<Book> bookListForRemove) {
        List<Book> removingList = new ArrayList<>();
        for (Book removingBook : bookListForRemove) {
            for (Book book : library.getList()) {
                if (book.getBookName().equals(removingBook.getBookName())
                        && book.getPageCount() == removingBook.getPageCount()
                        && book.getReleaseYear() == removingBook.getReleaseYear()) {
                    removingList.add(book);
                }
            }
        }
        List<Book> newList = library.getList();
        newList.removeAll(removingList);
        library.setList(newList);
        updateLibraryFile();
    }

    public void removeAuthorFromLibrary(Author authorForRemove) {
        List<Author> removingAuthorList = new ArrayList<>();
        for (Author author : library.getAuthorsList()) {
            if (author.getName().equals(authorForRemove.getName())) {
                removingAuthorList.add(author);
            }
        }
        List<Author> authors = library.getAuthorsList();
        authors.removeAll(removingAuthorList);
        library.setList(authors);
        updateLibraryFile();
    }

    public void removeBooksFromLibraryByAuthor(Author authorForRemove) {
        List<Book> removingBookList = new ArrayList<>();
        for (Book book : library.getList()) {
            if (book.getAuthor().getName().equals(authorForRemove.getName())) {
                removingBookList.add(book);
            }
        }
        List<Book> books = library.getList();
        books.removeAll(removingBookList);
        library.setList(books);
        updateLibraryFile();
    }
}
