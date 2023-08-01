package works.buddy.solutions;

import works.buddy.solutions.model.Problem;
import works.buddy.solutions.model.Solution;

import java.util.Collection;

public interface ProblemResolver {

    Problem reportProblem(String title, String description);

    void addSolution(Integer problemId, String solutionTitle, String solutionDescription);

    Collection<Solution> getSolutions(Integer problemId);
}
