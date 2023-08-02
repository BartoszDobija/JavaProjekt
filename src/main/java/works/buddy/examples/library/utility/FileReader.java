package works.buddy.examples.library.utility;

import works.buddy.solutions.util.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;

public class FileReader {
    public static Collection<String> read(String path) {
        try {
            return Files.readAllLines(Paths.get(Objects.requireNonNull(FileUtils.class.getResource(path)).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
