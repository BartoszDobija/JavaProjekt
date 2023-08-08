package works.buddy.library.data;

import works.buddy.library.model.Book;
import works.buddy.library.utils.CsvReader;

import java.util.Collection;

public class CsvInitialData implements InitialData {

    private final CsvReader csvReader;

    public CsvInitialData(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    @Override
    public Collection<Book> getBooks() {
        try {
            return csvReader.getAllLines().stream().map(object -> new Book(object[0], object[1], object[2], Integer.parseInt(object[3]))).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
