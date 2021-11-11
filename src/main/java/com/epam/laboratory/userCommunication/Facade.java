package com.epam.laboratory.userCommunication;

import com.epam.laboratory.entities.system.UserDataInspector;
import com.epam.laboratory.entities.system.UserManager;
import com.epam.laboratory.exceptions.UserDataException;
import com.epam.laboratory.util.*;
import com.epam.laboratory.util.BookFinder;
import com.epam.laboratory.entities.system.Librarian;
import com.epam.laboratory.util.parsers.DataLoader;
import com.epam.laboratory.entities.library.Author;
import com.epam.laboratory.entities.library.Book;
import com.epam.laboratory.entities.library.Bookmark;
import com.epam.laboratory.entities.user.User;
import org.apache.log4j.Logger;

import java.util.List;

public class Facade {

    private final UserDataInspector userDataInspector = new UserDataInspector();
    private final UserManager userManager = new UserManager();
    private final DataLoader dataLoader = new DataLoader();
    private final Librarian librarian = new Librarian();
    private final BookFinder bookFinder = new BookFinder();


    public User loginUser(String username, String password) throws Throwable {
        if (userDataInspector.isUserDataCorrect(username, password)) {
            return new UserManager().getUserByUsername(username);
        } else {
            throw new UserDataException("").getCause();
            //LOGGER.error();
        }
    }

    public void registerUser(String username, String password) {

        if (userDataInspector.isUserExists(username)) {
        } else {
            userManager.registerNewUser(username, password);
        }

    }

    public void blockUser(String username) {
        userManager.blockUser(username);
    }

    public void unlockUser(String username) {
        userManager.unlockUser(username);
    }

    public static User getUserByUsername(String username) {
        User user = new User();
        try {
            return new UserManager().getUserByUsername(username);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<Book> getBooks() {
        return (List<Book>) dataLoader.getDataFromFile(new ConfigurationDataUsage().getPathToLibraryJsonFile()).getList();
    }

    public List<Book> getBooksWithUsersBookmarks(User user) {
        return bookFinder.getBooksWithUsersBookmarks(user);
    }

    public String getUsersLogs(String username){
        return userManager.getUsersLogs(username);
    }


    // ADDING FUNCTIONALITY

    public void addNewBooksInLibrary(List<Book> bookList) {
        librarian.addNewBooksInLibrary(bookList);
    }

    public void addNewBooksInLibraryFromFile(String pathToFile) {
        librarian.addNewBooksInLibraryFromFile(pathToFile);
    }

    public void addNewAuthors(List<Author> authorsList) {
        librarian.addNewAuthors(authorsList);
    }

    public void addNewBookmarks(User user, Bookmark bookmark) {
        dataLoader.addNewBookmarks(user, bookmark);
    }


    // REMOVING FUNCTIONALITY

    public void removeBooksFromLibrary(List<Book> bookList) {
        librarian.removeBooksFromLibrary(bookList);
    }

    public void removeAuthorsFromLibrary(List<Author> authorsList) {
        librarian.removeAuthorsFromLibrary(authorsList);
    }

    public void removeBooksFromLibraryByAuthors(List<Author> authorsList) {
        librarian.removeBooksFromLibraryByAuthors(authorsList);
    }

    public void removeBookmark(User user, Bookmark bookmark) {
        dataLoader.removeBookmark(user, bookmark);
    }


    // SEARCHING FUNCTIONALITY

    public List<Book> getBooksByTitle(String title) {
        return bookFinder.getBooksByTitle(title);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookFinder.getBooksByAuthor(author);
    }

    public List<Book> getBooksByISBN(String isbn) {
        return bookFinder.getBooksByISBN(isbn);
    }

    public List<Book> getBooksByIssueYear(int issueYearFrom, int issueYearTo) {
        return bookFinder.getBooksByIssueYear(issueYearFrom, issueYearTo);
    }

    public List<Book> getBooksByParametersGroup(String bookTitle, int pagesAmount, int issueYear) {
        return new BookFinder().getBooksByTitleAndPagesAndYear(bookTitle, pagesAmount, issueYear);

    }


}
