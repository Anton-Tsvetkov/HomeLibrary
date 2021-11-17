package com.epam.laboratory.userCommunication;

import com.epam.laboratory.entities.system.Recognizer;
import com.epam.laboratory.util.UserActionsLogger;
import com.epam.laboratory.entities.library.Author;
import com.epam.laboratory.entities.user.User;
import com.epam.laboratory.entities.user.UserRights;
import com.epam.laboratory.entities.user.UserStatus;

import java.util.Scanner;

public class Questioner {

    private final Facade facade = new Facade();
    private final Scanner scanner = new Scanner(System.in);
    private User activeUser;
    private final Recognizer recognizer = new Recognizer();
    private final UserActionsLogger userActionsLogger = new UserActionsLogger();


    public void askUserTasks() throws Throwable {

        loginProcess();

        String answer = "";
        if (activeUser.getUserStatus().equals(UserStatus.LOCKED)) {
            System.out.println("Account \"" + activeUser.getUsername() + "\" was LOCKED");
            answer = "logout";
        }
        while (!answer.equals("logout")) {
            demonstrateProgramFunctionality();
            answer = scanner.nextLine();
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
                    userActionsLogger.logAction(activeUser.getUsername(), "Log out");
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
        String userName = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        activeUser = facade.loginUser(userName, password);
        userActionsLogger.logAction(activeUser.getUsername(), "Log in");

        System.out.println("Welcome back " + activeUser.getUsername());
    }

    private void askShowingFunctionality() {
        System.out.println("1.Show library books\n" +
                "2.Show books with my bookmarks\n");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                System.out.println(facade.getBooks());
                userActionsLogger.logAction(activeUser.getUsername(), "Viewing books");
                break;
            case "2":
                System.out.println(facade.getBooksWithUsersBookmarks(activeUser));
                userActionsLogger.logAction(activeUser.getUsername(), "Viewing books with users bookmarks");
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
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                facade.addNewBooksInLibrary(recognizer.recognizeBookData());
                userActionsLogger.logAction(activeUser.getUsername(), "Add new books in library");
                break;
            case "2":
                System.out.println("Enter author:");
                facade.addNewAuthor(recognizer.recognizeAuthor());
                userActionsLogger.logAction(activeUser.getUsername(), "Add new authors");
                break;
            case "3":
                System.out.println("Enter path to file:");
                facade.addNewBooksInLibraryFromFile(scanner.nextLine());
                userActionsLogger.logAction(activeUser.getUsername(), "Add new books in library from CSV or JSON file");
                break;
            case "4":
                facade.addNewBookmarks(activeUser, recognizer.recognizeNewBookmark());
                userActionsLogger.logAction(activeUser.getUsername(), "Add new bookmarks");
                break;
            default:
                System.out.println("Not found " + answer + " functionality");
        }
    }

    private void askRemovingFunctionality() {
        System.out.println("1.Remove books from library\n" +
                "2.Remove authors (with books by these authors)\n" +
                "3.Remove bookmark\n");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                facade.removeBooksFromLibrary(recognizer.recognizeSimplifiedBookData());
                userActionsLogger.logAction(activeUser.getUsername(), "Remove books from library");
                break;
            case "2":
                System.out.println("Enter authors list comma separated: ");
                Author author = recognizer.recognizeAuthor();
                facade.removeBooksFromLibraryByAuthor(author);
                facade.removeAuthorFromLibrary(author);
                userActionsLogger.logAction(activeUser.getUsername(), "Remove authors (with books by these authors)");
                break;
            case "3":
                facade.removeBookmark(activeUser, recognizer.recognizeExistBookmark(activeUser));
                userActionsLogger.logAction(activeUser.getUsername(), "Remove bookmark");
                break;
            default:
                System.out.println("Not found \"" + answer + "\" functionality");
        }
    }

    private void askSearchFunctionality() {
        System.out.println("1.Search books by title (or part of it)\n" +
                "2.Search books by author\n" +
                "3.Search books by ISBN\n" +
                "4.Search books by issue year diapason\n" +
                "5.Search books by year, pages amount and part of book title");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                System.out.println("Enter book title or part of it");
                System.out.println(facade.getBooksByTitle(scanner.nextLine()));
                userActionsLogger.logAction(activeUser.getUsername(), "Search books by title (or part of it)");
                break;
            case "2":
                System.out.println("Enter author name or part of it");
                System.out.println(facade.getBooksByAuthor(scanner.nextLine()));
                userActionsLogger.logAction(activeUser.getUsername(), "Search books by author");
                break;
            case "3":
                System.out.println("Enter ISBN index");
                System.out.println(facade.getBooksByISBN(scanner.nextLine()));
                userActionsLogger.logAction(activeUser.getUsername(), "Search books by ISBN");
                break;
            case "4":
                System.out.println("Enter lower bound of issue years diapason...");
                String issueYearFromString = scanner.nextLine();
                System.out.println("... and upper bound");
                String issueYearToString = scanner.nextLine();
                System.out.println(facade.getBooksByIssueYear(Integer.parseInt(issueYearFromString), Integer.parseInt(issueYearToString)));
                userActionsLogger.logAction(activeUser.getUsername(), "Search books by issue year diapason");
                break;
            case "5":
                System.out.println("Enter book title: ");
                String bookTitle = scanner.nextLine();

                System.out.println("Enter pages amount: ");
                String pagesAmountString = scanner.nextLine();
                int pagesAmount = Integer.parseInt(pagesAmountString);

                System.out.println("Enter issue year: ");
                String issueYearString = scanner.nextLine();
                int issueYear = Integer.parseInt(issueYearString);

                System.out.println(facade.getBooksByParametersGroup(bookTitle, pagesAmount, issueYear));
                userActionsLogger.logAction(activeUser.getUsername(), "Search books by year, pages amount and part of book title");
                break;
            default:
                System.out.println("Not found " + answer + " functionality");
        }
    }

    private void askAdminProgramFunctional() {
        System.out.println("1.Add new user\n" +
                "2.Show user's logs\n" +
                "3.Block user\n" +
                "4.Unblock user\n");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                System.out.println("Enter new user username:");
                String username = scanner.nextLine();

                System.out.println("Enter new user password:");
                String password = scanner.nextLine();
                facade.registerUser(username, password);
                userActionsLogger.logAction(activeUser.getUsername(), "Add new user");
                break;
            case "2":
                System.out.println("Enter username: ");
                System.out.println(facade.getUsersLogs(scanner.nextLine()));
                userActionsLogger.logAction(activeUser.getUsername(), "Show user's logs");
                break;
            case "3":
                System.out.println("Enter username you want to block:");
                facade.blockUser(scanner.nextLine());
                userActionsLogger.logAction(activeUser.getUsername(), "Block user");
                break;
            case "4":
                System.out.println("Enter username you want to unblock:");
                facade.unlockUser(scanner.nextLine());
                userActionsLogger.logAction(activeUser.getUsername(), "Unblock user");
                break;
            default:
                System.out.println("Not found " + answer + " functionality");
        }
    }
}
