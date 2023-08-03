package works.buddy.solutions.dao;

import works.buddy.solutions.model.Problem;

public interface ProblemDAO {

    void save(Problem problem);

    Problem findById(Integer id);
}
