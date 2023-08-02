package com.example;

import java.util.List;
import java.util.Scanner;

public class BookStore {

    BookStore() {
        // wprowadzić Dependency Injection
        InitialData testData = new InitialData();
        books = testData.sampleBooks();
    }

    List<Book> books;

    void addBook() {
        // przenieść to do Book
        Scanner scanner = new Scanner(System.in);
        Book book = new Book();
        System.out.println("Adding new book");
        book.id = books.get(books.size() - 1).id + 1;
        System.out.println("Author name:");
        book.author = scanner.nextLine();
        System.out.println("Book title:");
        book.title = scanner.nextLine();
        System.out.println("Book genre:");
        book.genre = scanner.nextLine();
        System.out.println("Release date:");
        book.releaseDate = scanner.nextInt();
        books.add(book);
    }

/*
    void editBook(Book book)
    {
        int toChange;
        toChange = Books.indexOf(book);
        Books.set(toChange, book);
    }
*/

    Book showBook(int id) {
        return books.get(id);
    }

    Book deleteBook(int id) {
        return books.remove(id);
    }
}
