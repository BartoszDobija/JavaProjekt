package works.buddy.solutions.main;

import works.buddy.solutions.services.InMemoryProblemResolver;
import works.buddy.solutions.services.ProblemResolver;
import works.buddy.solutions.services.StartupDataInitializer;
import works.buddy.solutions.view.ConsoleSolutionPrinter;
import works.buddy.solutions.view.SolutionPrinter;

public class App {

    public static void main(String[] args) {
        ProblemResolver problemResolver = new InMemoryProblemResolver(StartupDataInitializer.readFromFile("/solutions"));
        SolutionPrinter printer = new ConsoleSolutionPrinter();
        problemResolver.getSolutions(1).forEach(printer::print);
    }
}
