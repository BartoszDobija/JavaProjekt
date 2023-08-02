package works.buddy.solutions.main;

import works.buddy.solutions.InMemoryProblemResolver;
import works.buddy.solutions.ProblemResolver;
import works.buddy.solutions.util.SolutionUtils;

public class App {

    public static void main(String[] args) {
        ProblemResolver problemResolver = new InMemoryProblemResolver(SolutionUtils.readFromFile("/solutions"));
        problemResolver.getSolutions(2).forEach(System.out::println);
    }
}
