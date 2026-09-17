import model.Book;
import model.Member;
import service.Library;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("       LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==========================================");
        System.out.println("Books loaded   : " + library.getBookCount());
        System.out.println("Members loaded : " + library.getMemberCount());

        while (true) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    removeBook();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    library.displayBooks();
                    break;

                case 5:
                    addMember();
                    break;

                case 6:
                    library.displayMembers();
                    break;

                case 7:
                    issueBook();
                    break;

                case 8:
                    returnBook();
                    break;

                case 9:
                    library.viewIssuedBooks();
                    break;

                case 10:
                    library.sortBooksByTitle();
                    break;

                case 11:
                    library.sortBooksByAuthor();
                    break;

                case 12:
                    library.saveData();
                    System.out.println("Data saved successfully.");
                    break;

                case 13:
                    library.showStatistics();
                    break;

                case 0:
                    library.saveData();
                    System.out.println("Data saved.");
                    System.out.println("Thank you for using the Library Management System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {

        System.out.println();
        System.out.println("--------------- MENU ----------------");
        System.out.println("1.  Add Book");
        System.out.println("2.  Remove Book");
        System.out.println("3.  Search Book");
        System.out.println("4.  Display All Books");
        System.out.println("5.  Add Member");
        System.out.println("6.  Display Members");
        System.out.println("7.  Issue Book");
        System.out.println("8.  Return Book");
        System.out.println("9.  View Issued Books");
        System.out.println("10. Sort Books by Title");
        System.out.println("11. Sort Books by Author");
        System.out.println("12. Save Data");
        System.out.println("13. Library Statistics");
        System.out.println("0.  Exit");
        System.out.println("-------------------------------------");
    }

    private static void addBook() {

        System.out.println();
        System.out.println("========== ADD BOOK ==========");

        String id = readText("Book ID: ");
        String title = readText("Title: ");
        String author = readText("Author: ");
        String category = readText("Category: ");

        Book book = new Book(id, title, author, category);

        library.addBook(book);
    }

    private static void removeBook() {

        System.out.println();
        System.out.println("========== REMOVE BOOK ==========");

        String id = readText("Enter Book ID: ");

        library.removeBook(id);
    }

    private static void searchBook() {

        System.out.println();
        System.out.println("========== SEARCH BOOK ==========");

        String keyword = readText("Enter title, author, category or ID: ");

        library.searchBook(keyword);
    }

    private static void addMember() {

        System.out.println();
        System.out.println("========== ADD MEMBER ==========");

        String id = readText("Member ID: ");
        String name = readText("Name: ");
        String email = readText("Email: ");

        Member member = new Member(id, name, email);

        library.addMember(member);
    }

    private static void issueBook() {

        System.out.println();
        System.out.println("========== ISSUE BOOK ==========");

        String bookId = readText("Book ID: ");
        String memberId = readText("Member ID: ");

        library.issueBook(bookId, memberId);
    }

    private static void returnBook() {

        System.out.println();
        System.out.println("========== RETURN BOOK ==========");

        String bookId = readText("Book ID: ");
        String memberId = readText("Member ID: ");

        library.returnBook(bookId, memberId);
    }

    private static String readText(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                String input = scanner.nextLine().trim();

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }
}