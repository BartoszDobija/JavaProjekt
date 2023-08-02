package works.buddy.solutions.services;

import works.buddy.solutions.model.Problem;

import java.util.HashMap;
import java.util.Map;

import static works.buddy.solutions.util.FileUtils.readClasspathResource;

public class StartupDataInitializer {

    private static final String SEPARATOR = ",";

    private StartupDataInitializer() {
    }

    public static Map<Integer, Problem> readFromFile(String path) {
        Map<Integer, Problem> database = new HashMap<>();
        Problem lastProblem = null;
        for (String line : readClasspathResource(path)) {
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
}
