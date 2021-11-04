package com.epam.laboratory.systemObjects.workWithData;


import com.epam.laboratory.systemObjects.workWithData.loadData.DataLoader;
import com.epam.laboratory.systemObjects.workWithUser.Facade;
import com.epam.laboratory.workObjects.library.Author;
import com.epam.laboratory.workObjects.library.Book;
import com.epam.laboratory.workObjects.library.Bookmark;
import com.epam.laboratory.workObjects.user.User;

import java.util.ArrayList;
import java.util.List;

// ADD & REMOVE BOOKS, AUTHORS
public class Librarian {

    private final static DataLoader DATA_LOADER = new DataLoader();
    private static List<Book> bookList;

    static {
        bookList = (List<Book>) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToLibraryJsonFile);
    }

    private void updateLibraryFile(List<?> list) {
        DATA_LOADER.addNewObjectsInFile(ConfigurationDataUsage.pathToLibraryJsonFile, list);
    }


    // ADDING FUNCTIONALITY

    public void addNewBooksInLibrary(List<Book> bookList) {
        updateLibraryFile(bookList);
    }

    public void addNewBooksInLibraryFromFile(String pathToFile) {
        List<Book> bookList = (List<Book>) DATA_LOADER.getDataFromFile(pathToFile);
        Librarian.bookList.addAll(bookList);
        updateLibraryFile(Librarian.bookList);
    }

    public void addNewAuthors(List<Author> authorsList){
        DATA_LOADER.addNewObjectsInFile(ConfigurationDataUsage.pathToLibraryJsonFile, authorsList);
        updateLibraryFile(authorsList);
    }

    public void addNewBookmarks(User user, String bookmarkName, String bookTitle, int pageNumber) {
        Bookmark bookmark = new Bookmark(bookmarkName, bookTitle, pageNumber);
        List<Bookmark> bookmarkList = user.getBookmarkList();
        bookmarkList.add(bookmark);
        user.setBookmarkList(bookmarkList);
    }


    // REMOVING FUNCTIONALITY

    public void removeBooksFromLibrary(List<Book> bookListForRemove) {

        List<Book> removingList = new ArrayList<>();

        for (Book removingBook : bookListForRemove) {
            for (Book book : bookList) {
                if (book.getTitle().equals(removingBook.getTitle())
                        && book.getPagesAmount() == removingBook.getPagesAmount()
                        && book.getIssueYear() == removingBook.getIssueYear()) {
                    removingList.add(book);
                }
            }
        }

        bookList.removeAll(removingList);

        updateLibraryFile(bookList);
    }

//    public void removeBooksFromLibraryByAuthors(List<Author> authorsList){
//        // go to dataLoader? with authorsList -> remove from file authors and their books
//
//        updateLibraryFile();
//    }

    public void removeBookmarks(List<Bookmark> bookmarkList) {
        // go to dataLoader with bookmarkList -> rewrite data in library file
    }

}
