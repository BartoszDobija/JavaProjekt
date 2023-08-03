package works.buddy.examples.library.view;

import works.buddy.examples.library.utility.ConsoleInputValidation;

import java.util.Scanner;

public class BookFindDialog {
    public static int getBookId(Scanner scanner) {
        return ConsoleInputValidation.inputNumber(scanner);
    }
}
