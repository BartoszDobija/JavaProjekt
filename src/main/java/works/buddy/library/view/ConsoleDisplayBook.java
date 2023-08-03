package works.buddy.library.view;

import works.buddy.library.model.Book;

public class ConsoleDisplayBook implements DisplayBook {

    @Override
    public void details(Book book) {
        System.out.println(book.getTitle() + " by " + book.getAuthor() + " [" + book.getReleaseYear() + "]");
    }

    @Override
    public void title(Book book) {
        System.out.println(book.getTitle());
    }
}
