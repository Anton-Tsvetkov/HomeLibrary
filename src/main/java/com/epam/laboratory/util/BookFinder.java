package com.epam.laboratory.util;

import com.epam.laboratory.entities.library.Author;
import com.epam.laboratory.entities.library.Book;
import com.epam.laboratory.entities.library.Bookmark;
import com.epam.laboratory.entities.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookFinder {

    public static Book getBookByTitleAndPagesAndYear(List<Book> bookList, String title, int pagesAmount, int issueYear) {
        return (Book) bookList
                .stream()
                .filter(book -> book.getBookName().contains(title)
                        && book.getPageCount() == pagesAmount
                        && book.getReleaseYear() == issueYear);
    }

    public static List<Book> getBooksByTitle(List<Book> bookList, String title) {
        return bookList.stream().filter(book -> book.getBookName().contains(title)).collect(Collectors.toList());
    }

    public static List<Book> getBooksByAuthor(List<Book> bookList, String authorName) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getAuthor().getName().contains(authorName)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public static List<Book> getBooksByISBN(List<Book> bookList, String isbn) {
        return bookList.stream().filter(book -> book.getISBN().contains(isbn)).collect(Collectors.toList());
    }

    public static List<Book> getBooksByIssueYear(List<Book> bookList, int issueYearFrom, int issueYearTo) {
        return bookList.stream().filter(book -> book.getReleaseYear() >= issueYearFrom && book.getReleaseYear() <= issueYearTo).collect(Collectors.toList());
    }

    public static List<Book> getBooksWithUsersBookmarks(List<Book> bookList, User user) {
        List<Book> bookListWithUsersBookmarks = new ArrayList<>();
        for (Book book : bookList) {
            for (Bookmark bookmark : user.getBookmarkList()) {
                if (book.getISBN().equals(bookmark.getISBN())) {
                    bookListWithUsersBookmarks.add(book);
                }
            }
        }
        return bookListWithUsersBookmarks;
    }

}
