package com.epam.laboratory.systemObjects.workWithData;


import com.epam.laboratory.systemObjects.workWithData.parsers.DataLoader;
import com.epam.laboratory.workObjects.globalObjects.GlobalObject;
import com.epam.laboratory.workObjects.globalObjects.Library;
import com.epam.laboratory.workObjects.library.Author;
import com.epam.laboratory.workObjects.library.Book;

import java.util.ArrayList;
import java.util.List;

// ADD & REMOVE BOOKS, AUTHORS
public class Librarian {

    private final static DataLoader DATA_LOADER = new DataLoader();
    private static Library library;

    static {
        library = (Library) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToLibraryJsonFile);
    }

    private void updateLibraryFile(GlobalObject globalObject) {
        DATA_LOADER.updateFile(ConfigurationDataUsage.pathToLibraryJsonFile, globalObject);
    }


    // ADDING FUNCTIONALITY

    public void addNewBooksInLibrary(List<Book> bookList) {
        library.addObjectsToList(bookList);
        updateLibraryFile(library);
    }

    public void addNewBooksInLibraryFromFile(String pathToFile) {
        library.addObjectsToList(DATA_LOADER.getDataFromFile(pathToFile).getList());
        updateLibraryFile(library);
    }

    public void addNewAuthors(List<Author> authorsList) {
        library.addObjectsToList(authorsList);
        updateLibraryFile(library);
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
        updateLibraryFile(library);
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
        updateLibraryFile(library);
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
        updateLibraryFile(library);
    }
}
