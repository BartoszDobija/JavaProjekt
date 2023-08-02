package com.example.view;
import java.util.Map;
import java.util.HashMap;
public class MessageConsolePrinter {
    private Map<String, String> messages;
    public MessageConsolePrinter(){
        this.messages = new HashMap<>();
        this.messages.put("welcome", "welcome to library service! \nplease select action: \n1: list books in library \n2: add new book\n3: find book by id");
        this.messages.put("showLibrary", "the library contains:");
        this.messages.put("addBook", "enter book data:");
        this.messages.put("error", "wrong operation [insert number number corresponding with the action]");
        this.messages.put("end", "do you wish to continue? 1: yes");
    }

    public void printMessage(String name){
        String message = messages.get(name);
        System.out.println( message );
    }
}
