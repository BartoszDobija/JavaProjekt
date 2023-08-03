package works.buddy.library.service;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static works.buddy.library.utils.PropertiesReader.getProperties;

public class Messages {
    private static Properties messages;

    private void init(){
        messages = getProperties("messages.properties");
    }
    public static String getMessage(String key){
        return messages.getProperty("key");
    }
}
