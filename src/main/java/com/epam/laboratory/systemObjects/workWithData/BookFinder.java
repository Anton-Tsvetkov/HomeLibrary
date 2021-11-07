package com.epam.laboratory.systemObjects.workWithData;

import com.epam.laboratory.systemObjects.workWithData.parsers.DataLoader;
import com.epam.laboratory.systemObjects.workWithUser.Facade;
import com.epam.laboratory.workObjects.library.Author;
import com.epam.laboratory.workObjects.library.Book;
import com.epam.laboratory.workObjects.library.Bookmark;
import com.epam.laboratory.workObjects.user.User;

import java.util.ArrayList;
import java.util.List;

public class BookFinder {

    private final DataLoader DATA_LOADER = new DataLoader();



    public List<Book> getBooksByTitleAndPagesAndYear(String title, int pagesAmount, int issueYear) {
        List<Book> foundBooks = new ArrayList<>();

        List<Book> bookList = new Facade().getBooks();

        for (Book book : bookList) {
            if (book.getTitle().contains(title)
                    || (book.getPagesAmount() == pagesAmount)
                    || (book.getIssueYear() == issueYear)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> getBooksByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();

        List<Book> bookList = new Facade().getBooks();

        for (Book book : bookList) {
            if (book.getTitle().contains(title)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> getBooksByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();

        List<Book> bookList = new Facade().getBooks();

        for (Book book : bookList) {
            List<Author> authorsList = book.getAuthorList();
            for (Author author1 : authorsList) {
                if (author1.getName().contains(author)) {
                    foundBooks.add(book);
                }
            }
        }
        return foundBooks;
    }

    public List<Book> getBooksByISBN(String isbn) {
        List<Book> foundBooks = new ArrayList<>();

        List<Book> bookList = new Facade().getBooks();

        for (Book book : bookList) {
            if (book.getISBN().equals(isbn)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> getBooksByIssueYear(int issueYearFrom, int issueYearTo) {
        List<Book> foundBooks = new ArrayList<>();

        List<Book> bookList = new Facade().getBooks();

        for (Book book : bookList) {
            if (book.getIssueYear() >= issueYearFrom
                    && book.getIssueYear() <= issueYearTo) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public List<Book> getBooksWithUsersBookmarks(User user){
        List<Book> bookList = (List<Book>) DATA_LOADER.getDataFromFile(ConfigurationDataUsage.pathToLibraryJsonFile).getList();
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

}
