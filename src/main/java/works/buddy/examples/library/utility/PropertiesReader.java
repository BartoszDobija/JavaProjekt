package works.buddy.examples.library.utility;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    public static Properties getProperties(String name) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(loader.getResourceAsStream(name));
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
