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

import java.util.List;

public class Facade {

    private final UserDataInspector userDataInspector = new UserDataInspector();
    private final UserManager userManager = new UserManager();
    private final DataLoader dataLoader = new DataLoader();
    private final Librarian librarian = new Librarian();


    public User loginUser(String username, String password) throws Throwable {
        if (userDataInspector.isUserDataCorrect(username, password)) {
            return new UserManager().getUserByUsername(username);
        } else {
            throw new UserDataException("").getCause();
            //LOGGER.error();
        }
    }

    public void registerUser(String username, String password) {
        if (!userDataInspector.isUserExists(username)) {
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
        return BookFinder.getBooksWithUsersBookmarks(getBooks(), user);
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

    public void addNewAuthor(Author author) {
        librarian.addNewAuthor(author);
    }

    public void addNewBookmarks(User user, Bookmark bookmark) {
        dataLoader.addNewBookmarks(user, bookmark);
    }


    // REMOVING FUNCTIONALITY

    public void removeBooksFromLibrary(List<Book> bookList) {
        librarian.removeBooksFromLibrary(bookList);
    }

    public void removeAuthorFromLibrary(Author author) {
        librarian.removeAuthorFromLibrary(author);
    }

    public void removeBooksFromLibraryByAuthor(Author author) {
        librarian.removeBooksFromLibraryByAuthor(author);
    }

    public void removeBookmark(User user, Bookmark bookmark) {
        dataLoader.removeBookmark(user, bookmark);
    }


    // SEARCHING FUNCTIONALITY

    public List<Book> getBooksByTitle(String title) {
        return BookFinder.getBooksByTitle(getBooks(), title);
    }

    public List<Book> getBooksByAuthor(String author) {
        return BookFinder.getBooksByAuthor(getBooks(), author);
    }

    public List<Book> getBooksByISBN(String isbn) {
        return BookFinder.getBooksByISBN(getBooks(), isbn);
    }

    public List<Book> getBooksByIssueYear(int issueYearFrom, int issueYearTo) {
        return BookFinder.getBooksByIssueYear(getBooks(), issueYearFrom, issueYearTo);
    }

    public List<Book> getBooksByParametersGroup(String bookTitle, int pagesAmount, int issueYear) {
        return BookFinder.getBooksByTitleAndPagesAndYear(getBooks(), bookTitle, pagesAmount, issueYear);

    }


}
