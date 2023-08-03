package works.buddy.solutions.model;

import java.util.ArrayList;
import java.util.Collection;

public class Problem {

    private Integer id;

    private String title;

    private String description;

    private Collection<Solution> solutions;

    public Problem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public void addSolution(String solutionTitle, String solutionDescription) {
        if(solutions == null) {
            solutions = new ArrayList<>();
        }
        solutions.add(new Solution(solutions.size(), solutionTitle, solutionDescription));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(Collection<Solution> solutions) {
        this.solutions = solutions;
    }
}
