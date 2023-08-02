package com.example.controller;

import com.example.data.InitialData;
import com.example.model.Book;

import java.util.List;
import java.util.Scanner;

public class BookStore {

    public BookStore(List<Book> books) {
        this.books = books;
    }

    public List<Book> books;

    public void addBook(Book book) {
        book.setId(getNextId());
        books.add(book);
    }

    private int getNextId() {
        return books.get(books.size() - 1).getId() + 1;
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
