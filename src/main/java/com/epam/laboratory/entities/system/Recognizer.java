package com.epam.laboratory.entities.system;

import com.epam.laboratory.entities.library.Author;
import com.epam.laboratory.entities.library.Book;
import com.epam.laboratory.entities.library.Bookmark;
import com.epam.laboratory.entities.user.User;
import com.epam.laboratory.util.BookFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recognizer {

    private final Scanner scanner = new Scanner(System.in);

    public List<Book> recognizeBookData() {

        System.out.println("Enter books amount:");
        String booksAmountString = scanner.nextLine();
        int booksAmount = Integer.parseInt(booksAmountString);

        List<Book> bookList = new ArrayList<>();

        for (int i = 0; i < booksAmount; i++) {

            System.out.println("Enter title:");
            String title = scanner.nextLine();

            System.out.println("Enter authors list comma separated:");
            String authors = scanner.nextLine();

            System.out.println("Enter pages amount:");
            String pagesAmountString = scanner.nextLine();
            int pagesAmount = Integer.parseInt(pagesAmountString);

            System.out.println("Enter ISBN:");
            String isbn = scanner.nextLine();

            System.out.println("Enter issue year:");
            String issueYearString = scanner.nextLine();
            int issueYear = Integer.parseInt(issueYearString);

            bookList.add(new Book(title.trim(), recognizeAuthors(authors), pagesAmount, isbn.trim(), issueYear));
        }

        return bookList;
    }

    public List<Book> recognizeSimplifiedBookData() {

        System.out.println("Enter title:");
        String title = scanner.nextLine();

        System.out.println("Enter pages amount:");
        String pagesAmountString = scanner.nextLine();
        int pagesAmount = Integer.parseInt(pagesAmountString);

        System.out.println("Enter issue year:");
        String issueYearString = scanner.nextLine();
        int issueYear = Integer.parseInt(issueYearString);

        return new BookFinder().getBooksByTitleAndPagesAndYear(title, pagesAmount, issueYear);
    }

    public List<Author> recognizeAuthors(String authorsString) {
        authorsString = authorsString.replaceAll(" ", "");
        authorsString += ',';
        List<Author> authorList = new ArrayList<>();
        int commaCount = 0;
        for (int i = 0; i < authorsString.length(); i++) {
            if (authorsString.charAt(i) == ',') {
                commaCount++;
            }
        }
        for (int i = 0; i < commaCount; i++) {
            String authorSubstring = authorsString.substring(0, authorsString.indexOf(','));
            authorList.add(new Author(authorSubstring));
            authorsString = authorsString.replace(authorSubstring, "").replaceFirst(",", "");
        }
        return authorList;
    }

    public Bookmark recognizeExistBookmark(User user){
        System.out.println("Enter book isbn:");
        String isbn = scanner.nextLine();

        System.out.println("Enter bookmarks name:");
        String bookmarksNames = scanner.nextLine();

        return new UserManager().getBookmarkByName(user, isbn, bookmarksNames);
    }

    public Bookmark recognizeNewBookmark(){
        System.out.println("Enter book isbn:");
        String isbn = scanner.nextLine();

        System.out.println("Enter page number:");
        String pageNumber = scanner.nextLine();

        System.out.println("Enter bookmarks name:");
        String bookmarksName = scanner.nextLine();

        return new Bookmark(bookmarksName, isbn, Integer.parseInt(pageNumber));
    }

}
