package com.example.view;
import java.util.Map;
import java.util.HashMap;
public class MessageConsolePrinter {
    private Map<String, String> messages;
    public MessageConsolePrinter(){
        this.messages = new HashMap<>();
        this.messages.put("welcome", """
                welcome to library service!\s
                please select action:\s
                1: list books in library\s
                2: add new book
                3: find book by id
                4: exit""");
        this.messages.put("showLibrary", "the library contains:");
        this.messages.put("addBook", "enter book data:");
        this.messages.put("findById", "enter id to find a book");
        this.messages.put("error", "wrong operation [insert number number corresponding with the action]");
        this.messages.put("end", "do you wish to continue? 1: yes");
    }

    public void printMessage(String name){
        String message = messages.get(name);
        System.out.println( message );
    }
}
