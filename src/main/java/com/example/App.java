package com.example;

import com.example.controller.BookController;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        BookController bookController = new BookController();
        boolean running = true;
        while (running) {
            running = bookController.begin();
        }
    }
}