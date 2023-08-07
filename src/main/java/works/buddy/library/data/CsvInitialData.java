package works.buddy.library.data;

import works.buddy.library.model.Book;
import works.buddy.library.utils.CsvReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CsvInitialData implements InitialData {

    private final CsvReader csvReader;

    public CsvInitialData(CsvReader csvReader) {
        this.csvReader = csvReader;
    }

    @Override
    public Collection<Book> getBooks() {
        Collection<Book> result = new ArrayList<>();
        try {
            for (String[] object : csvReader.getAllLines()) {
                result.add(new Book(result.size(), object[0], object[1], object[2], Integer.parseInt(object[3])));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
