package works.buddy.solutions.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Objects;

public class FileUtils {

    private FileUtils() {
    }

    public static Collection<String> readClasspathResource(String path) {
        try {
            return Files.readAllLines(Paths.get(Objects.requireNonNull(FileUtils.class.getResource(path)).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
