
package com.example;
        import com.example.controller.BookController;
        import com.example.controller.BookStore;
        import com.example.data.InitialData;

        import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) {
        BookController bookController = new BookController();
        bookController.begin();

    }
}