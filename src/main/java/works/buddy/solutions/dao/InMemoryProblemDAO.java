package works.buddy.solutions.dao;

import works.buddy.solutions.model.Problem;

import java.util.Map;

public class InMemoryProblemDAO implements ProblemDAO {

    private Map<Integer, Problem> database;

    public InMemoryProblemDAO(Map<Integer, Problem> database) {
        this.database = database;
    }

    @Override
    public void save(Problem problem) {
        Integer id = getNextId();
        problem.setId(id);
        database.put(id, problem);
    }

    private Integer getNextId() {
        return database.size() + 1;
    }

    @Override
    public Problem findById(Integer id) {
        return database.get(id);
    }
}
