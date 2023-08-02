package works.buddy.examples.library;

import works.buddy.examples.library.controller.BookController;

public class App {

    public static void main(String[] args) {
        BookController bookController = new BookController();
        boolean running = true;
        while (running) {
            running = bookController.begin();
        }
    }
}