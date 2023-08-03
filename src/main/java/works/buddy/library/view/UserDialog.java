package works.buddy.library.view;

import works.buddy.library.model.Book;

public interface UserDialog {
    Book addBookDialog();
    Book pickBookDialog();
    Book removeBookDialog();
    Book editBookDialog();
    int menuDialog();
}
