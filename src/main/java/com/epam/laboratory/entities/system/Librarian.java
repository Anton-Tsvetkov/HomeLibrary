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

    public void addNewBooksInLibrary(List<Book> bookList) {
        library.addObjectsToList(bookList);
        updateLibraryFile();
    }

    public void addNewBooksInLibraryFromFile(String pathToFile) {
        library.addObjectsToList(dataLoader.getDataFromFile(pathToFile).getList());
        updateLibraryFile();
    }

    public void addNewAuthors(List<Author> authorsList) {
        library.addObjectsToList(authorsList);
        updateLibraryFile();
    }


    // REMOVING FUNCTIONALITY

    public void removeBooksFromLibrary(List<Book> bookListForRemove) {
        List<Book> removingList = new ArrayList<>();
        for (Book removingBook : bookListForRemove) {
            for (Book book : library.getList()) {
                if (book.getTitle().equals(removingBook.getTitle())
                        && book.getPagesAmount() == removingBook.getPagesAmount()
                        && book.getIssueYear() == removingBook.getIssueYear()) {
                    removingList.add(book);
                }
            }
        }
        List<Book> newList = library.getList();
        newList.removeAll(removingList);
        library.setList(newList);
        updateLibraryFile();
    }

    public void removeAuthorsFromLibrary(List<Author> authorsForRemove) {
        List<Author> removingAuthorList = new ArrayList<>();
        for (Author removingAuthor : authorsForRemove) {
            for (Author author : library.getAuthorsList()) {
                if (author.getName().equals(removingAuthor.getName())) {
                    removingAuthorList.add(author);
                }
            }
        }
        List<Author> authors = library.getAuthorsList();
        authors.removeAll(removingAuthorList);
        library.setList(authors);
        updateLibraryFile();
    }

    public void removeBooksFromLibraryByAuthors(List<Author> authorsForRemove) {
        List<Book> removingBookList = new ArrayList<>();
        for (Author removingAuthor : authorsForRemove) {
            for (Book book : library.getList()) {
                for (Author bookAuthor : book.getAuthorList()) {
                    if (bookAuthor.getName().equals(removingAuthor.getName())) {
                        removingBookList.add(book);
                    }
                }
            }
        }
        List<Book> books = library.getList();
        books.removeAll(removingBookList);
        library.setList(books);
        updateLibraryFile();
    }
}
