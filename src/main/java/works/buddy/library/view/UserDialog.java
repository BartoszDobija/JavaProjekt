package works.buddy.library.view;

import works.buddy.library.model.Book;

public interface UserDialog {
    Book addBookDialog();
    int findBookDialog();
    int removeBookDialog();
    Book editBookDialog();
    int menuDialog();
    void acceptDialog(String key);
}
