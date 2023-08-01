package com.example;

import java.util.List;

public class BookStore {
    List<Book> Books;
    void AddBook()
    {
        Book book = new Book();
        System.out.println("Adding new book");
        System.out.println("Author name:");
        book.Author = System.console().readLine();
        System.out.println("Book title:");
        book.Title = System.console().readLine();
        System.out.println("Book genre:");
        book.Title = System.console().readLine();
        System.out.println("Release date:");
        book.ReleaseDate = Integer.parseInt(System.console().readLine());
        Books.add(book);
    }

/*
    void EditBook(Book book)
    {
        int toChange;
        toChange = Books.indexOf(book);
        Books.set(toChange, book);
    }
*/

    Book ShowBook(int id)
    {
        return Books.get(id);
    }

    Book DeleteBook(int id)
    {
        return Books.remove(id);
    }
}
