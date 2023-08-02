package com.example.view;

import java.util.Scanner;

public class BookFindDialog {

    private Scanner scanner;

    public BookFindDialog(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getBookId() {
        return scanner.nextInt();
    }
}
