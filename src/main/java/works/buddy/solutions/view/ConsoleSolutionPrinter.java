package works.buddy.solutions.view;

import works.buddy.solutions.model.Solution;

public class ConsoleSolutionPrinter implements SolutionPrinter {

    @Override
    public void print(Solution solution) {
        System.out.println(solution);
    }
}
