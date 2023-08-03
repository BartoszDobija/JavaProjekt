package works.buddy.examples.library.utility;

import java.util.Scanner;

import static works.buddy.examples.library.utility.ConsoleClearLine.clearScanner;

public class ConsoleInputValidation {

    public static int inputNumber(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                clearScanner(scanner);
                return number;
            }
            System.out.println("Please input number");
            scanner.nextLine();
        }
    }
}
