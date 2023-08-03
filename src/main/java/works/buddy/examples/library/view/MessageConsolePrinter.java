package works.buddy.examples.library.view;

import static works.buddy.examples.library.utility.PropertiesReader.getProperties;

public class MessageConsolePrinter {

    public static void printMessage(String key) {
        System.out.println(getProperties("messages.properties").getProperty(key));
    }
}
