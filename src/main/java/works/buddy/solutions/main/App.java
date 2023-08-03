package works.buddy.solutions.main;

import works.buddy.solutions.dao.InMemoryProblemDAO;
import works.buddy.solutions.model.Problem;
import works.buddy.solutions.services.InMemoryProblemResolver;
import works.buddy.solutions.services.ProblemResolver;
import works.buddy.solutions.services.StartupDataInitializer;
import works.buddy.solutions.view.ConsoleSolutionPrinter;
import works.buddy.solutions.view.SolutionPrinter;

import java.util.Map;

public class App {

    public static void main(String[] args) {
        Map<Integer, Problem> sampleData = StartupDataInitializer.readFromFile("/solutions");
        ProblemResolver problemResolver = new InMemoryProblemResolver(new InMemoryProblemDAO(sampleData));
        SolutionPrinter printer = new ConsoleSolutionPrinter();
        problemResolver.getSolutions(1).forEach(printer::print);
    }
}
