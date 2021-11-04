package com.epam.laboratory.workObjects;

import com.epam.laboratory.systemObjects.workWithData.BookFinder;
import com.epam.laboratory.workObjects.library.Author;
import com.epam.laboratory.workObjects.library.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recognizer {

    private final Scanner SCANNER = new Scanner(System.in);

    public List<Book> recognizeBookData() {

        System.out.println("Enter books amount:");
        String booksAmountString = SCANNER.nextLine();
        int booksAmount = Integer.parseInt(booksAmountString);

        List<Book> bookList = new ArrayList<>();

        for (int i = 0; i < booksAmount; i++) {

            System.out.println("Enter title:");
            String title = SCANNER.nextLine();

            System.out.println("Enter authors list comma separated:");
            String authors = SCANNER.nextLine();

            System.out.println("Enter pages amount:");
            String pagesAmountString = SCANNER.nextLine();
            int pagesAmount = Integer.parseInt(pagesAmountString);

            System.out.println("Enter ISBN:");
            String isbn = SCANNER.nextLine();

            System.out.println("Enter issue year:");
            String issueYearString = SCANNER.nextLine();
            int issueYear = Integer.parseInt(issueYearString);

            bookList.add(new Book(title.trim(), recognizeAuthors(authors), pagesAmount, isbn.trim(), issueYear));
        }

        return bookList;
    }

    public List<Book> recognizeSimplifiedBookData() {

        System.out.println("Enter title:");
        String title = SCANNER.nextLine();

        System.out.println("Enter pages amount:");
        String pagesAmountString = SCANNER.nextLine();
        int pagesAmount = Integer.parseInt(pagesAmountString);

        System.out.println("Enter issue year:");
        String issueYearString = SCANNER.nextLine();
        int issueYear = Integer.parseInt(issueYearString);

        return new BookFinder().findBooksByTitleAndPagesAndYear(title, pagesAmount, issueYear);
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
            System.out.println(authorsString);
            String authorSubstring = authorsString.substring(0, authorsString.indexOf(','));
            authorList.add(new Author(authorSubstring));
            authorsString = authorsString.replace(authorSubstring, "").replaceFirst(",", "");
        }
        return authorList;
    }
}
