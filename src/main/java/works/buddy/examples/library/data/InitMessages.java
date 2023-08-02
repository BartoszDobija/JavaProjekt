package works.buddy.examples.library.data;


import java.util.Map;
import java.util.HashMap;

import static works.buddy.examples.library.utility.FileReader.read;
public class InitMessages {
    public static Map<String, String> getMessages(){
        Map<String, String> messages = new HashMap<>();
        for (String line : read("/messages")) {
            String[] parts = line.split(";");
            messages.put(parts[0], parts[1]);
        }
        return messages;
    }
}
