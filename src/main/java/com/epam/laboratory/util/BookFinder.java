package com.epam.laboratory.util;

import com.epam.laboratory.entities.library.Author;
import com.epam.laboratory.entities.library.Book;
import com.epam.laboratory.entities.library.Bookmark;
import com.epam.laboratory.entities.user.User;

import java.util.ArrayList;
import java.util.List;

public class BookFinder {

    public static List<Book> getBooksByTitleAndPagesAndYear(List<Book> bookList, String title, int pagesAmount, int issueYear) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getTitle().contains(title)
                    || (book.getPagesAmount() == pagesAmount)
                    || (book.getIssueYear() == issueYear)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public static List<Book> getBooksByTitle(List<Book> bookList, String title) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getTitle().contains(title)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public static List<Book> getBooksByAuthor(List<Book> bookList, String author) {
        List<Book> foundBooks = new ArrayList<>();
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

    public static List<Book> getBooksByISBN(List<Book> bookList, String isbn) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getISBN().equals(isbn)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public static List<Book> getBooksByIssueYear(List<Book> bookList, int issueYearFrom, int issueYearTo) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getIssueYear() >= issueYearFrom
                    && book.getIssueYear() <= issueYearTo) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public static List<Book> getBooksWithUsersBookmarks(List<Book> bookList, User user){
        List<Book> bookListWithUsersBookmarks = new ArrayList<>();
        List<Bookmark> usersBookmark = user.getBookmarkList();
        for (Book book : bookList) {
            for (Bookmark bookmark : usersBookmark) {
                if (book.getISBN().equals(bookmark.getISBN())){
                    bookListWithUsersBookmarks.add(book);
                }
            }
        }
        return bookListWithUsersBookmarks;
    }

}
