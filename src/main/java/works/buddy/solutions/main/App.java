package works.buddy.solutions.main;

import works.buddy.solutions.InMemoryProblemResolver;
import works.buddy.solutions.ProblemResolver;
import works.buddy.solutions.model.Problem;
import works.buddy.solutions.model.Solution;

import java.util.Collection;

public class App {

    public static void main(String[] args) {
        ProblemResolver problemResolver = new InMemoryProblemResolver();
        Problem problem = problemResolver.reportProblem("Ala ma kota", "Kot zdycha");
        Integer problemId = problem.getId();
        problemResolver.addSolution(problemId, "Jedzenie", "Trzeba karmić kota");
        problemResolver.addSolution(problemId, "Odchody", "Trzeba sprzątać mu kuwetę");
        Collection<Solution> solutions = problemResolver.getSolutions(problemId);
        for (Solution solution : solutions) {
            System.out.println(solution);
        }
    }
}
