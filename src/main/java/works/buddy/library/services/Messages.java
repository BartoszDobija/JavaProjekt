package works.buddy.library.services;

import java.util.Properties;

import static works.buddy.library.utils.PropertiesReader.getProperties;

public class Messages {

    private static Properties messages;

    public static void init(String fileName) {
        messages = getProperties(fileName);
    }

    public static String getMessage(String key) {
        return messages.getProperty(key);
    }
}
