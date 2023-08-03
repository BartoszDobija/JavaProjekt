package works.buddy.examples.library.view;

import java.util.Properties;

import static works.buddy.examples.library.utility.PropertiesReader.getProperties;

public class MessageConsolePrinter {
    public static void printMessage(String key){
        Properties messages = getProperties("messages.properties");
        String message = messages.getProperty(key);
        System.out.println( message );
    }
}
