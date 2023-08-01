package com.example;
import java.util.ArrayList;
import java.util.List;

public class LibraryViewModel {
    private List<BookViewModel> Library;

    public LibraryViewModel(List<Book> originalBooks){
        this.Library = new ArrayList<>();
        BookViewModel book;
        for (Book originalBook : originalBooks) {
            book = new BookViewModel(originalBook);
            this.Library.add(book);
        }
    }
}
