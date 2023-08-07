package works.buddy.library.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.nio.file.Path;
import java.util.List;

public class CsvReader {

    private final Path filePath;

    public CsvReader(Path filePath) {
        this.filePath = filePath;
    }

    public List<String[]> getAllLines() throws Exception {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(String.valueOf(filePath))).build()) {
            return reader.readAll();
        }
    }
}
