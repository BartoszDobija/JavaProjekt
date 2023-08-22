package works.buddy.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.model.Book;

import java.util.Collection;

@Repository
@Primary
@Transactional
public class HibernateBookDAO implements BookDAO {

    @Autowired
    protected SessionFactory sessionFactory;

    public HibernateBookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Collection<Book> findAll() {
        return getCurrentSession().createQuery("from Book", Book.class).getResultList();
    }

    @Override
    public void save(Book book) {
        getCurrentSession().save(book);
    }

    @Override
    public Book find(Integer id) {
        return getCurrentSession().find(Book.class, id);
    }

    @Override
    public void delete(Book book) {
        getCurrentSession().delete(book);
    }

    @Override
    public void update(Book book) {
        getCurrentSession().clear();
        getCurrentSession().update(book);
    }
}
