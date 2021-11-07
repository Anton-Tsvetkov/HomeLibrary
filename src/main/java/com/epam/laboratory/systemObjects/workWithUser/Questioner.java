package com.epam.laboratory.systemObjects.workWithUser;

import com.epam.laboratory.workObjects.Recognizer;
import com.epam.laboratory.workObjects.library.Author;
import com.epam.laboratory.workObjects.user.User;
import com.epam.laboratory.workObjects.user.UserRights;
import com.epam.laboratory.workObjects.user.UserStatus;

import java.util.List;
import java.util.Scanner;

public class Questioner {

    private final Facade FACADE = new Facade();
    private final Scanner SCANNER = new Scanner(System.in);
    private User activeUser;
    private final Recognizer RECOGNIZER = new Recognizer();

    public void askUserTasks() throws Throwable {

        loginProcess();

        String answer = "";
        if(activeUser.getUserStatus().equals(UserStatus.LOCKED)){
            System.out.println("Account \"" + activeUser.getUsername() + "\" was LOCKED");
            answer = "logout";
        }
        while (!answer.equals("logout")){
            demonstrateProgramFunctionality();
            answer = SCANNER.nextLine();
            switch (answer) {
                case "1":
                    askShowingFunctionality();
                    break;
                case "2":
                    askAddingFunctionality();
                    break;
                case "3":
                    askRemovingFunctionality();
                    break;
                case "4":
                    askSearchFunctionality();
                    break;
                case "5":
                    if (activeUser.getUserRights().equals(UserRights.ADMIN)) {
                        askAdminProgramFunctional();
                    } else {
                        System.out.println("You have no power here, " + activeUser.getUsername());
                    }
                    break;
                case "logout":
                    break;
                default:
                    System.out.println("Not found " + answer + " functionality");
            }
        }

    }

    private void demonstrateProgramFunctionality() {
        System.out.println("1.Showing functions\n" +
                "2.Adding functions\n" +
                "3.Remove functions\n" +
                "4.Search functions");
        if (activeUser.getUserRights().equals(UserRights.ADMIN)) {
            System.out.println("5.Work with users functions");
        }
        System.out.println("\nlogout");
    }

    private void loginProcess() throws Throwable {
        System.out.println("LOGIN PROCESS");

        System.out.println("Enter username: ");
        String userName = SCANNER.nextLine();
        System.out.println("Enter password: ");
        String password = SCANNER.nextLine();

        activeUser = FACADE.loginUser(userName, password);

        System.out.println("Welcome back " + activeUser.getUsername());
    }

    private void askShowingFunctionality() {
        System.out.println("1.Show library books\n" +
                "2.Show books with my bookmarks\n");
        String answer = SCANNER.nextLine();
        switch (answer) {
            case "1":
                System.out.println(FACADE.getBooks());
                break;
            case "2":
                System.out.println(FACADE.getBooksWithUsersBookmarks(activeUser));
                break;
            default:
                System.out.println("Not found " + answer + " functionality");
        }

    }

    private void askAddingFunctionality() {
        System.out.println("1.Add new books in library\n" + // as objects
                "2.Add new authors\n" +
                "3.Add new books in library from CSV or JSON file\n" + // from csv/json files
                "4.Add new bookmarks\n");
        String answer = SCANNER.nextLine();
        switch (answer) {
            case "1":
                FACADE.addNewBooksInLibrary(RECOGNIZER.recognizeBookData());
                break;
            case "2":
                System.out.println("Enter authors list comma separated:");
                FACADE.addNewAuthors(RECOGNIZER.recognizeAuthors(SCANNER.nextLine()));
                break;
            case "3":
                System.out.println("Enter path to file:");
                FACADE.addNewBooksInLibraryFromFile(SCANNER.nextLine());
                break;
            case "4":
                System.out.println("Enter book title:");
                String bookTitle = SCANNER.nextLine();

                System.out.println("Enter book's page number for bookmark:");
                String bookPageNumberString = SCANNER.nextLine();

                System.out.println("Enter bookmark name:");
                String bookmarkName = SCANNER.nextLine();

                FACADE.addNewBookmarks(activeUser, bookmarkName, bookTitle, Integer.parseInt(bookPageNumberString));
                break;
            default:
                System.out.println("Not found " + answer + " functionality");
        }
    }

    private void askRemovingFunctionality() {
        System.out.println("1.Remove books from library\n" +
                "2.Remove authors (with books by these authors)\n" +
                "3.Remove bookmark\n");
        String answer = SCANNER.nextLine();
        switch (answer) {
            case "1":
                FACADE.removeBooksFromLibrary(RECOGNIZER.recognizeSimplifiedBookData());
                break;
            case "2":
                System.out.println("Enter authors list comma separated: ");
                List<Author> authors = RECOGNIZER.recognizeAuthors(SCANNER.nextLine());
                FACADE.removeBooksFromLibraryByAuthors(authors);
                FACADE.removeAuthorsFromLibrary(authors);
                break;
            case "3":
                FACADE.removeBookmark(activeUser, RECOGNIZER.recognizeBookmark(activeUser));
                break;
            default:
                System.out.println("Not found " + answer + " functionality");
        }
    }

    private void askSearchFunctionality() {
        System.out.println("1.Search books by title (or part of it)\n" +
                "2.Search books by author\n" +
                "3.Search books by ISBN\n" +
                "4.Search books by issue year diapason\n" +
                "5.Search books by year, pages amount and part of book title");
        String answer = SCANNER.nextLine();
        switch (answer) {
            case "1":
                System.out.println("Enter book title or part of it");
                System.out.println(FACADE.getBooksByTitle(SCANNER.nextLine()));
                break;
            case "2":
                System.out.println("Enter author name or part of it");
                System.out.println(FACADE.getBooksByAuthor(SCANNER.nextLine()));
                break;
            case "3":
                System.out.println("Enter ISBN index");
                System.out.println(FACADE.getBooksByISBN(SCANNER.nextLine()));
                break;
            case "4":
                System.out.println("Enter lower bound of issue years diapason...");
                String issueYearFromString = SCANNER.nextLine();
                System.out.println("... and upper bound");
                String issueYearToString = SCANNER.nextLine();
                System.out.println(FACADE.getBooksByIssueYear(Integer.parseInt(issueYearFromString), Integer.parseInt(issueYearToString)));
                break;
            case "5":
                System.out.println("Enter book title: ");
                String bookTitle = SCANNER.nextLine();

                System.out.println("Enter pages amount: ");
                String pagesAmountString = SCANNER.nextLine();
                int pagesAmount = Integer.parseInt(pagesAmountString);

                System.out.println("Enter issue year: ");
                String issueYearString = SCANNER.nextLine();
                int issueYear = Integer.parseInt(issueYearString);

                System.out.println(FACADE.getBooksByParametersGroup(bookTitle, pagesAmount, issueYear));
                break;
            default:
                System.out.println("Not found " + answer + " functionality");
        }
    }

    // WORK IN PROGRESS
    private void askAdminProgramFunctional() {
        System.out.println("1.Add new user\n" +
                "2.Show user's logs\n" +
                "3.Block user\n" +
                "4.Unblock user\n");
        String answer = SCANNER.nextLine();
        switch (answer) {
            case "1":
                System.out.println("Enter new user username:");
                String username = SCANNER.nextLine();

                System.out.println("Enter new user password:");
                String password = SCANNER.nextLine();
                FACADE.registerUser(username, password);
                break;
            case "2":
                break;
            case "3":
                System.out.println("Enter username you want to block:");
                FACADE.blockUser(SCANNER.nextLine());
                break;
            case "4":
                System.out.println("Enter username you want to unblock:");
                FACADE.unlockUser(SCANNER.nextLine());
                break;
            default:
                System.out.println("Not found " + answer + " functionality");
        }
    }
}
