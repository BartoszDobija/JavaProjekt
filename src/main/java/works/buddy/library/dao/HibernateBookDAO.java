package works.buddy.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import works.buddy.library.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Connection;
import java.util.Collection;

@Repository
@Primary
public class HibernateBookDAO implements BookDAO {

    @Autowired
    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    private final Connection connection;

    public HibernateBookDAO(EntityManager entityManager, Connection connection) {
        this.entityManager = entityManager;
        this.connection = connection;
    }

    @Override
    public Collection<Book> findAll() {
        return entityManager.createQuery("select * from books").getResultList();
    }

    @Override
    public void save(Book book) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(book);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book find(Integer id) throws NotFoundException {
        return entityManager.find(Book.class, id);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        entityManager.remove(find(id));
    }

    @Override
    public void update(Book book) throws NotFoundException {
        entityManager.merge(book);
    }
}
