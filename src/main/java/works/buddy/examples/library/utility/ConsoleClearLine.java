package works.buddy.examples.library.utility;

import java.util.Scanner;

public class ConsoleClearLine {
    public static void clearScanner(Scanner scanner){
        if(scanner.hasNextLine()){
            scanner.nextLine();
        }
    }
}
