package model;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private List<String> issuedBooks;

    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.issuedBooks = new ArrayList<>();
    }

    public Member(String memberId, String name, String email, List<String> issuedBooks) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.issuedBooks = new ArrayList<>(issuedBooks);
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getIssuedBooks() {
        return issuedBooks;
    }

    public boolean canIssueMoreBooks() {
        return issuedBooks.size() < 3;
    }

    public void addBook(String bookId) {
        issuedBooks.add(bookId);
    }

    public void removeBook(String bookId) {
        issuedBooks.remove(bookId);
    }

    @Override
    public String toString() {
        return String.format(
                "%-10s %-25s %-30s %-10d",
                memberId,
                name,
                email,
                issuedBooks.size()
        );
    }
}