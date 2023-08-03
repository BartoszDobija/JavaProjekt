package works.buddy.solutions.services;

import works.buddy.solutions.dao.ProblemDAO;
import works.buddy.solutions.model.Problem;
import works.buddy.solutions.model.Solution;

import java.util.Collection;

/**
 * Taka klasa zawierala by logikę biznesową, bo w DAO klasach nie robimy żadnej logiki tylko stricte pobranie/zapis do bazy
*/
public class InMemoryProblemResolver implements ProblemResolver {

    private ProblemDAO problemDAO;

    public InMemoryProblemResolver(ProblemDAO problemDAO) {
        this.problemDAO = problemDAO;
    }

    @Override
    public Problem reportProblem(String title, String description) {
        Problem problem = new Problem(title, description);
        problemDAO.save(problem);
        return problem;
    }

    @Override
    public void addSolution(Integer problemId, String solutionTitle, String solutionDescription) {
        getProblem(problemId).addSolution(solutionTitle, solutionDescription);
    }

    @Override
    public Collection<Solution> getSolutions(Integer problemId) {
        return getProblem(problemId).getSolutions();
    }

    private Problem getProblem(Integer problemId) {
        return problemDAO.findById(problemId);
    }
}
