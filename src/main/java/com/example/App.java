
package com.example;
        import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args ) {
        Scanner scanner = new Scanner(System.in);
        String[] messages = {
                "welcome to library service! \nplease select action: \n1: list books in library \n2: add new book",
                "the library contains:",
                "enter book data: ",
                "wrong operation [insert number number corresponding with the action]"
        };
        System.out.println( messages[0] );
        int action = scanner.nextInt();
        switch (action){
            case 1:
                System.out.println( messages[1] );
                break;
            case 2:
                System.out.println( messages[2] + "title" ); //etc.
                break;
            default:
                System.out.println( messages[3] );
                break;
        }
    }
}