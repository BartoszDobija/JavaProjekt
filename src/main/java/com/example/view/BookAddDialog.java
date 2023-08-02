package com.example.view;

import com.example.model.Book;

import java.util.Scanner;

public class BookAddDialog {

    Book createBook() {
        Book book = new Book();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adding new book");
        System.out.println("Author name:");
        book.setAuthor(scanner.nextLine());
        System.out.println("Book title:");
        book.setTitle(scanner.nextLine());
        System.out.println("Book genre:");
        book.setGenre(scanner.nextLine());
        System.out.println("Release date:");
        book.setReleaseDate(scanner.nextInt());
        return book;
    }

}
