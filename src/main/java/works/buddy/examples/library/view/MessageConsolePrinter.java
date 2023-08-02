package works.buddy.examples.library.view;
import java.util.Map;
import java.util.HashMap;

import static works.buddy.examples.library.data.InitMessages.getMessages;

public class MessageConsolePrinter {
    private Map<String, String> messages;
    public MessageConsolePrinter(){
        this.messages = getMessages();

    }

    public void printMessage(String name){
        String message = messages.get(name);
        System.out.println( message );
    }
}
