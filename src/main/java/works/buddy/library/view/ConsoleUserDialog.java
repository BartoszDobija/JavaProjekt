package works.buddy.library.view;

import works.buddy.library.model.Book;

public class ConsoleUserDialog implements UserDialog{

    @Override
    public Book addBookDialog() {
        return null;
    }

    @Override
    public int findBookDialog() {
        return 0;
    }

    @Override
    public int removeBookDialog() {
        return 0;
    }

    @Override
    public Book editBookDialog() {
        return null;
    }

    @Override
    public int menuDialog() {
        return 0;
    }
}
