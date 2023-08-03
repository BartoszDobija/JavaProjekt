package works.buddy.examples.library.utility;

import java.util.Scanner;

public class ConsoleInputValidation {

    public static int inputNumber(Scanner scanner) {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Please input number");
            scanner.nextLine();
        }
    }
}
