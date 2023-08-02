
package com.example;
        import com.example.controller.BookStore;

        import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) {
        BookStore bookStore = new BookStore();
        //Library library;
        Scanner scanner = new Scanner(System.in);
        String[] messages = {
                "welcome to library service! \nplease select action: \n1: list books in library \n2: add new book",
                "the library contains:",
                "enter book data: ",
                "wrong operation [insert number number corresponding with the action]",
                "do you wish to continue? 1: yes"
        };
        boolean exit = false;
        while(!exit){
            System.out.println( messages[0] );
            int action = scanner.nextInt();
            switch (action) {
                case 1 -> {
                    System.out.println(messages[1]);
                    //library = new Library(bookStore.books);
                    //library.displayLibrary();
                }
                case 2 -> bookStore.addBook();
                default -> System.out.println(messages[3]);
            }
            System.out.println( messages[4] );
            int decision = scanner.nextInt();
            exit = decision != 1;
        }
    }
}