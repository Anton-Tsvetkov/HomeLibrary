package com.epam.laboratory.systemObjects.workWithUser;

import com.epam.laboratory.exceptions.UserDataException;
import com.epam.laboratory.systemObjects.workWithData.BookFinder;
import com.epam.laboratory.systemObjects.workWithData.ConfigurationDataUsage;
import com.epam.laboratory.systemObjects.workWithData.Librarian;
import com.epam.laboratory.systemObjects.workWithData.loadData.DataLoader;
import com.epam.laboratory.workObjects.library.Author;
import com.epam.laboratory.workObjects.library.Book;
import com.epam.laboratory.workObjects.library.Bookmark;
import com.epam.laboratory.workObjects.user.User;
import com.epam.laboratory.workObjects.user.UserStatus;

import java.util.ArrayList;
import java.util.List;

public class Facade {

    private final UserDataInspector USER_DATA_INSPECTOR = new UserDataInspector();
    private final UserCreator USER_CREATOR = new UserCreator();
    private static final DataLoader DATA_LOADER = new DataLoader();
    private final Librarian LIBRARIAN = new Librarian();
    private final BookFinder BOOK_FINDER = new BookFinder();

    private void updateData(){

    }

    public User loginUser(String username, String password) throws Throwable {
        if (USER_DATA_INSPECTOR.isUserDataCorrect(username, password)) {
            return new DataLoader().getUserByUsername(username);
        } else {
            throw new UserDataException().fillInStackTrace();
        }
    }

    public String registerUser(String username, String password) {

        if (USER_DATA_INSPECTOR.isUserExists(username)) {
            return "Username \"" + username + "\" is already exists";
        } else {
            USER_CREATOR.registerNewUser(username, password);
            return "Welcome " + username;
        }

    }

    public void blockUser(String username){
        User user = getUserByUsername(username);
        user.setUserStatus(UserStatus.LOCKED);

    }

    public void unlockUser(String username){
        User user = getUserByUsername(username);
        user.setUserStatus(UserStatus.UNLOCKED);
    }

    public static User getUserByUsername(String username){
        List<User> userList = (List<User>) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToUserStoreJsonFile);
        for (User user : userList){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return new User();
    }

    public List<Book> getBooks() {
        return (List<Book>) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToLibraryJsonFile);
    }

    public List<Book> getBooksWithUsersBookmarks(User user) {
        List<Book> bookList = (List<Book>) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToLibraryJsonFile);
        List<Book> bookListWithUsersBookmarks = new ArrayList<>();
        List<Bookmark> usersBookmark = user.getBookmarkList();
        for (Book book : bookList) {
            for (Bookmark bookmark : usersBookmark) {
                if (book.getTitle().equals(bookmark.getBookTitle())){
                    bookListWithUsersBookmarks.add(book);
                }
            }
        }
        return bookListWithUsersBookmarks;
    }



    // ADDING FUNCTIONALITY

    public void addNewBooksInLibrary(List<Book> bookList){
        LIBRARIAN.addNewBooksInLibrary(bookList);
    }

    public void addNewBooksInLibraryFromFile(String pathToFile){
        LIBRARIAN.addNewBooksInLibraryFromFile(pathToFile);
    }

    public void addNewAuthors(List<Author> authorsList){
        LIBRARIAN.addNewAuthors(authorsList);
    }

    public void addNewBookmarks(User user, String bookmarkName, String bookTitle, int pageNumber){
        LIBRARIAN.addNewBookmarks(user, bookmarkName, bookTitle, pageNumber);
    }



    // REMOVING FUNCTIONALITY

    public void removeBooksFromLibrary(List<Book> bookList){
        LIBRARIAN.removeBooksFromLibrary(bookList);
    }
//
//    public void removeBooksFromLibraryByAuthors(List<Author> authorsList){
//        LIBRARIAN.removeBooksFromLibraryByAuthors(authorsList);
//    }

    public void removeBookmarks(List<Bookmark> bookmarkList){
        LIBRARIAN.removeBookmarks(bookmarkList);
    }



    // SEARCHING FUNCTIONALITY

    public List<Book> getBooksByTitle(String title){
        return BOOK_FINDER.getBooksByTitle(title);
    }

    public List<Book> getBooksByAuthor(String author){
        return BOOK_FINDER.getBooksByAuthor(author);
    }

    public List<Book> getBooksByISBN(String isbn){
        return BOOK_FINDER.getBooksByISBN(isbn);
    }

    public List<Book> getBooksByIssueYear(int issueYearFrom, int issueYearTo){
        return BOOK_FINDER.getBooksByIssueYear(issueYearFrom, issueYearTo);
    }

    public List<Book> getBooksByParametersGroup(String bookTitle, int pagesAmount, int issueYear){
        return new BookFinder().findBooksByTitleAndPagesAndYear(bookTitle, pagesAmount, issueYear);

    }




}
