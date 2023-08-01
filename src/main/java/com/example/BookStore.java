package com.example;

import java.util.List;

public class BookStore {
    List<Book> Books;
    void addBook()
    {
        Book book = new Book();
        System.out.println("Adding new book");
        book.id = Books.get(Books.size()-1).id + 1;
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
        return Books.get(id);
    }

    Book deleteBook(int id)
    {
        return Books.remove(id);
    }
}
