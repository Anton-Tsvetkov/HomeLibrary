package com.epam.laboratory.systemObjects.workWithUser;

import com.epam.laboratory.exceptions.UserDataException;
import com.epam.laboratory.systemObjects.workWithData.BookFinder;
import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.systemObjects.workWithData.Librarian;
import com.epam.laboratory.systemObjects.workWithData.parsers.DataLoader;
import com.epam.laboratory.workObjects.library.Author;
import com.epam.laboratory.workObjects.library.Book;
import com.epam.laboratory.workObjects.library.Bookmark;
import com.epam.laboratory.workObjects.user.User;
import com.epam.laboratory.workObjects.user.UserStatus;
import org.apache.log4j.Logger;

import java.util.List;

public class Facade {

    private final UserDataInspector USER_DATA_INSPECTOR = new UserDataInspector();
    private final UserManager USER_MANAGER = new UserManager();
    private final DataLoader DATA_LOADER = new DataLoader();
    private final Librarian LIBRARIAN = new Librarian();
    private final BookFinder BOOK_FINDER = new BookFinder();
    private final Logger LOGGER = Logger.getLogger(Facade.class);

    private void updateData() {

    }

    public User loginUser(String username, String password) throws Throwable {
        if (USER_DATA_INSPECTOR.isUserDataCorrect(username, password)) {
            return new UserManager().getUserByUsername(username);
        } else {
            throw new UserDataException("").getCause();
            //LOGGER.error();
        }
    }

    public String registerUser(String username, String password) {

        if (USER_DATA_INSPECTOR.isUserExists(username)) {
            return "Username \"" + username + "\" is already exists";
        } else {
            USER_MANAGER.registerNewUser(username, password);
            return "Welcome " + username;
        }

    }

    public void blockUser(String username) {
        User user = getUserByUsername(username);
        user.setUserStatus(UserStatus.LOCKED);

    }

    public void unlockUser(String username) {
        User user = getUserByUsername(username);
        user.setUserStatus(UserStatus.UNLOCKED);
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
        return (List<Book>) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToLibraryJsonFile).getList();
    }

    public List<Book> getBooksWithUsersBookmarks(User user) {
        return BOOK_FINDER.getBooksWithUsersBookmarks(user);
    }


    // ADDING FUNCTIONALITY

    public void addNewBooksInLibrary(List<Book> bookList) {
        LIBRARIAN.addNewBooksInLibrary(bookList);
    }

    public void addNewBooksInLibraryFromFile(String pathToFile) {
        LIBRARIAN.addNewBooksInLibraryFromFile(pathToFile);
    }

    public void addNewAuthors(List<Author> authorsList) {
        LIBRARIAN.addNewAuthors(authorsList);
    }

    public void addNewBookmarks(User user, Bookmark bookmark) {
        DATA_LOADER.addNewBookmarks(user, bookmark);
    }


    // REMOVING FUNCTIONALITY

    public void removeBooksFromLibrary(List<Book> bookList) {
        LIBRARIAN.removeBooksFromLibrary(bookList);
    }

    public void removeAuthorsFromLibrary(List<Author> authorsList) {
        LIBRARIAN.removeAuthorsFromLibrary(authorsList);
    }

    public void removeBooksFromLibraryByAuthors(List<Author> authorsList) {
        LIBRARIAN.removeBooksFromLibraryByAuthors(authorsList);
    }

    public void removeBookmark(User user, Bookmark bookmark) {
        DATA_LOADER.removeBookmark(user, bookmark);
    }


    // SEARCHING FUNCTIONALITY

    public List<Book> getBooksByTitle(String title) {
        return BOOK_FINDER.getBooksByTitle(title);
    }

    public List<Book> getBooksByAuthor(String author) {
        return BOOK_FINDER.getBooksByAuthor(author);
    }

    public List<Book> getBooksByISBN(String isbn) {
        return BOOK_FINDER.getBooksByISBN(isbn);
    }

    public List<Book> getBooksByIssueYear(int issueYearFrom, int issueYearTo) {
        return BOOK_FINDER.getBooksByIssueYear(issueYearFrom, issueYearTo);
    }

    public List<Book> getBooksByParametersGroup(String bookTitle, int pagesAmount, int issueYear) {
        return new BookFinder().getBooksByTitleAndPagesAndYear(bookTitle, pagesAmount, issueYear);

    }


}
