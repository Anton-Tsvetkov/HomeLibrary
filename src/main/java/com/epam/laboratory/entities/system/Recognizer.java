package com.epam.laboratory.entities.system;

import com.epam.laboratory.entities.library.Author;
import com.epam.laboratory.entities.library.Book;
import com.epam.laboratory.entities.library.Bookmark;
import com.epam.laboratory.entities.user.User;
import com.epam.laboratory.userCommunication.Facade;
import com.epam.laboratory.util.BookFinder;

import java.util.Scanner;

public class Recognizer {

    private final Scanner scanner = new Scanner(System.in);

    public Book recognizeBookData() {
        System.out.println("BOOK DATA");
        System.out.println("Enter title:");
        String title = scanner.nextLine();

        System.out.println("Enter pages amount:");
        String pagesAmountString = scanner.nextLine();
        int pagesAmount = Integer.parseInt(pagesAmountString);

        System.out.println("Enter ISBN:");
        String isbn = scanner.nextLine();

        System.out.println("Enter issue year:");
        String issueYearString = scanner.nextLine();
        int issueYear = Integer.parseInt(issueYearString);

        System.out.println("Enter publisher:");
        String publisher = scanner.nextLine();

        return new Book(title.trim(), issueYear, pagesAmount, isbn.trim(), publisher.trim(), recognizeAuthor());
    }

    public Author recognizeAuthor() {
        System.out.println("AUTHOR DATA");
        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter second name:");
        String secondName = scanner.nextLine();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter date of birthday:");
        String dob = scanner.nextLine();

        return new Author(name, secondName, lastName, dob);

    }

    public Book recognizeSimplifiedBookData() {

        System.out.println("Enter title:");
        String title = scanner.nextLine();

        System.out.println("Enter pages amount:");
        String pagesAmountString = scanner.nextLine();
        int pagesAmount = Integer.parseInt(pagesAmountString);

        System.out.println("Enter issue year:");
        String issueYearString = scanner.nextLine();
        int issueYear = Integer.parseInt(issueYearString);

        return BookFinder.getBookByTitleAndPagesAndYear(new Facade().getBooks(), title, pagesAmount, issueYear);
    }

    public Bookmark recognizeExistBookmark(User user) {
        System.out.println("Enter book isbn:");
        String isbn = scanner.nextLine();

        System.out.println("Enter bookmarks name:");
        String bookmarksNames = scanner.nextLine();

        return new UserManager().getBookmarkByName(user, isbn, bookmarksNames);
    }

    public Bookmark recognizeNewBookmark() {
        System.out.println("Enter book isbn:");
        String isbn = scanner.nextLine();

        System.out.println("Enter page number:");
        String pageNumber = scanner.nextLine();

        System.out.println("Enter bookmarks name:");
        String bookmarksName = scanner.nextLine();

        return new Bookmark(bookmarksName, isbn, Integer.parseInt(pageNumber));
    }

}
