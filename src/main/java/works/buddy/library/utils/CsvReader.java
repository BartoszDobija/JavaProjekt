package works.buddy.library.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.nio.file.Path;
import java.util.Collection;

public class CsvReader {

    private CsvReader() {
    }

    public static Collection<String[]> readCSV(Path filePath) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(String.valueOf(filePath))).build()) {
            return reader.readAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
