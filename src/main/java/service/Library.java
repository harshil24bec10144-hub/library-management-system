package service;

import model.Book;
import model.Member;
import util.FileManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Library {

    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = FileManager.loadBooks();
        members = FileManager.loadMembers();
    }

    public void addBook(Book book) {

        if (findBook(book.getBookId()) != null) {
            System.out.println("Book ID already exists.");
            return;
        }

        books.add(book);
        saveData();

        System.out.println("Book added successfully.");
    }

    public void removeBook(String bookId) {

        Book book = findBook(bookId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is currently issued.");
            return;
        }

        books.remove(book);
        saveData();

        System.out.println("Book removed successfully.");
    }

    public void addMember(Member member) {

        if (findMember(member.getMemberId()) != null) {
            System.out.println("Member ID already exists.");
            return;
        }

        members.add(member);
        saveData();

        System.out.println("Member added successfully.");
    }

    public void searchBook(String keyword) {

        boolean found = false;

        keyword = keyword.toLowerCase();

        System.out.println();
        printBookHeader();

        for (Book book : books) {

            if (book.getBookId().toLowerCase().contains(keyword)
                    || book.getTitle().toLowerCase().contains(keyword)
                    || book.getAuthor().toLowerCase().contains(keyword)
                    || book.getCategory().toLowerCase().contains(keyword)) {

                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching books found.");
        }
    }

    public void displayBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        printBookHeader();

        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void displayMembers() {

        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }

        System.out.printf(
                "%-10s %-25s %-30s %-10s%n",
                "ID", "Name", "Email", "Books"
        );

        System.out.println("-".repeat(80));

        for (Member member : members) {
            System.out.println(member);
        }
    }

    public void issueBook(String bookId, String memberId) {

        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already issued.");
            return;
        }

        if (!member.canIssueMoreBooks()) {
            System.out.println("Member has reached the maximum limit of 3 books.");
            return;
        }

        book.issue();
        member.addBook(bookId);

        saveData();

        System.out.println("Book issued successfully.");
    }

    public void returnBook(String bookId, String memberId) {

        Book book = findBook(bookId);
        Member member = findMember(memberId);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("This book is not currently issued.");
            return;
        }

        if (!member.getIssuedBooks().contains(bookId)) {
            System.out.println("This member does not have this book.");
            return;
        }

        book.returnBook();
        member.removeBook(bookId);

        saveData();

        System.out.println("Book returned successfully.");
    }

    public void viewIssuedBooks() {

        boolean found = false;

        System.out.println();
        System.out.println("========== ISSUED BOOKS ==========");

        for (Member member : members) {

            for (String bookId : member.getIssuedBooks()) {

                Book book = findBook(bookId);

                if (book != null) {

                    System.out.println(
                            "Book: " + book.getTitle()
                                    + " | Member: " + member.getName()
                                    + " | Member ID: " + member.getMemberId()
                    );

                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No books are currently issued.");
        }
    }

    public void sortBooksByTitle() {

        books.sort(
                Comparator.comparing(
                        Book::getTitle,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        System.out.println("Books sorted by title.");
        displayBooks();
    }

    public void sortBooksByAuthor() {

        books.sort(
                Comparator.comparing(
                        Book::getAuthor,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        System.out.println("Books sorted by author.");
        displayBooks();
    }

    public Book findBook(String bookId) {

        for (Book book : books) {

            if (book.getBookId().equalsIgnoreCase(bookId)) {
                return book;
            }
        }

        return null;
    }

    public Member findMember(String memberId) {

        for (Member member : members) {

            if (member.getMemberId().equalsIgnoreCase(memberId)) {
                return member;
            }
        }

        return null;
    }

    public void saveData() {
        FileManager.saveBooks(books);
        FileManager.saveMembers(members);
    }

    // Library statistics
    public void showStatistics() {

        int totalBooks = books.size();
        int issuedBooks = 0;

        for (Book book : books) {
            if (!book.isAvailable()) {
                issuedBooks++;
            }
        }

        int availableBooks = totalBooks - issuedBooks;
        int totalMembers = members.size();

        System.out.println();
        System.out.println("========== LIBRARY STATISTICS ==========");
        System.out.println();
        System.out.println("Total Books       : " + totalBooks);
        System.out.println("Available Books   : " + availableBooks);
        System.out.println("Issued Books      : " + issuedBooks);
        System.out.println("Total Members     : " + totalMembers);
        System.out.println();
        System.out.println("=========================================");
    }

    private void printBookHeader() {

        System.out.println();

        System.out.printf(
                "%-8s %-30s %-25s %-15s %-10s%n",
                "ID",
                "Title",
                "Author",
                "Category",
                "Status"
        );

        System.out.println("-".repeat(95));
    }

    public int getBookCount() {
        return books.size();
    }

    public int getMemberCount() {
        return members.size();
    }
}