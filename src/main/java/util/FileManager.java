package util;

import model.Book;
import model.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    private static final String BOOK_FILE = "data/books.csv";
    private static final String MEMBER_FILE = "data/members.csv";

    public static void saveBooks(List<Book> books) {
        createDataDirectory();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOK_FILE))) {

            writer.write("bookId,title,author,category,available");
            writer.newLine();

            for (Book book : books) {
                writer.write(
                        escape(book.getBookId()) + "," +
                        escape(book.getTitle()) + "," +
                        escape(book.getAuthor()) + "," +
                        escape(book.getCategory()) + "," +
                        book.isAvailable()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();

        File file = new File(BOOK_FILE);

        if (!file.exists()) {
            return books;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length >= 5) {
                    books.add(new Book(
                            data[0],
                            data[1],
                            data[2],
                            data[3],
                            Boolean.parseBoolean(data[4])
                    ));
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }

        return books;
    }

    public static void saveMembers(List<Member> members) {
        createDataDirectory();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEMBER_FILE))) {

            writer.write("memberId,name,email,issuedBooks");
            writer.newLine();

            for (Member member : members) {

                String issuedBooks = String.join(
                        ";",
                        member.getIssuedBooks()
                );

                writer.write(
                        escape(member.getMemberId()) + "," +
                        escape(member.getName()) + "," +
                        escape(member.getEmail()) + "," +
                        escape(issuedBooks)
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    public static List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();

        File file = new File(MEMBER_FILE);

        if (!file.exists()) {
            return members;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",", -1);

                if (data.length >= 4) {

                    List<String> issuedBooks = new ArrayList<>();

                    if (!data[3].isEmpty()) {
                        issuedBooks.addAll(
                                Arrays.asList(data[3].split(";"))
                        );
                    }

                    members.add(
                            new Member(
                                    data[0],
                                    data[1],
                                    data[2],
                                    issuedBooks
                            )
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading members: " + e.getMessage());
        }

        return members;
    }

    private static String escape(String value) {
        return value.replace(",", " ");
    }

    private static void createDataDirectory() {
        File directory = new File("data");

        if (!directory.exists()) {
            directory.mkdirs();
        }
    }
}