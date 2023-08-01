package works.buddy.solutions;

import works.buddy.solutions.model.Problem;
import works.buddy.solutions.model.Solution;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryProblemResolver implements ProblemResolver {

    private Map<Integer, Problem> database = new HashMap<>();

    @Override
    public Problem reportProblem(String title, String description) {
        Integer id = getNextId();
        Problem problem = new Problem(id, title,description);
        database.put(id, problem);
        return problem;
    }

    private Integer getNextId() {
        return database.size();
    }

    @Override
    public void addSolution(Integer problemId, String solutionTitle, String solutionDescription) {
        findProblemById(problemId).addSolution(solutionTitle, solutionDescription);
    }

    private Problem findProblemById(Integer problemId) {
        return database.get(problemId);
    }

    @Override
    public Collection<Solution> getSolutions(Integer problemId) {
        return database.get(problemId).getSolutions();
    }
}
