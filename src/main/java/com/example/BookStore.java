package com.example;

import com.sun.org.apache.xml.internal.security.Init;

import java.util.List;

public class BookStore {
    BookStore()
    {
        InitialData testData = new InitialData();
        books = testData.sampleBooks();
    }
    List<Book> books;
    void addBook()
    {
        Book book = new Book();
        System.out.println("Adding new book");
        book.id = books.get(books.size()-1).id + 1;
        System.out.println("Author name:");
        book.author = System.console().readLine();
        System.out.println("Book title:");
        book.title = System.console().readLine();
        System.out.println("Book genre:");
        book.title = System.console().readLine();
        System.out.println("Release date:");
        book.releaseDate = Integer.parseInt(System.console().readLine());
    }

/*
    void editBook(Book book)
    {
        int toChange;
        toChange = Books.indexOf(book);
        Books.set(toChange, book);
    }
*/

    Book showBook(int id)
    {
        return books.get(id);
    }

    Book deleteBook(int id)
    {
        return books.remove(id);
    }
}
