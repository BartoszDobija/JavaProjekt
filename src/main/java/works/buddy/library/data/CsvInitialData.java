package works.buddy.library.data;

import works.buddy.library.model.Book;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CsvInitialData implements InitialData {

    private final File csvFile;

    private static final char DEFAULT_SEPARATOR = ',';

    public CsvInitialData(File csvFile) {
        this.csvFile = csvFile;
    }

    private List<String[]> readFile() throws Exception {
        List<String[]> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] csvLine = parseLine(line);
                result.add(csvLine);
            }
        }
        return result;
    }

    private String[] parseLine(String line) {
        List<String> result = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == DEFAULT_SEPARATOR) {
                result.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            } else {
                stringBuilder.append(c);
            }
        }
        result.add(stringBuilder.toString());
        return result.toArray(String[]::new);
    }

    @Override
    public Collection<Book> getBooks() {
        Collection<Book> result = new ArrayList<>();
        try {
            List<String[]> booksData = readFile();
            for (String[] object : booksData) {
                result.add(new Book(result.size(), object[0], object[1], object[2], Integer.parseInt(object[3])));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
