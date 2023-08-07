package works.buddy.library.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private final File csvFile;

    private static final char DEFAULT_SEPARATOR = ',';

    public CsvReader(File csvFile) {
        this.csvFile = csvFile;
    }

    public List<String[]> getAllLines() throws Exception {
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

}
