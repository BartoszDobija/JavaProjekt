package com.example;

import java.util.List;

public class BookStore {
    List<Book> Books;
    void AddBook(Book book)
    {
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
}
