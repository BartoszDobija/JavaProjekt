package works.buddy.solutions.util;

import works.buddy.solutions.model.Problem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SolutionUtils {

    private static final String SEPARATOR = ",";

    private SolutionUtils() {
    }

    public static Map<Integer, Problem> readFromFile(String path) {
        Map<Integer, Problem> database = new HashMap<>();
        Problem lastProblem = null;
        for (String line : getLines(path)) {
            String[] parts = line.split(SEPARATOR);
            if (isSolution(line)) {
                validateSolutionIsAfterProblem(lastProblem, line);
                lastProblem.addSolution(parts[0], parts[1]);
            } else {
                lastProblem = new Problem(database.size() + 1, parts[0], parts[1]);
                database.put(lastProblem.getId(), lastProblem);
            }
        }
        return database;
    }

    private static void validateSolutionIsAfterProblem(Problem lastProblem, String line) {
        if (lastProblem == null) {
            throw new RuntimeException("Invalid line: " + line);
        }
    }

    private static boolean isSolution(String line) {
        return line.startsWith("-");
    }

    private static List<String> getLines(String path) {
        try {
            return Files.readAllLines(Paths.get(Objects.requireNonNull(SolutionUtils.class.getResource(path)).getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
