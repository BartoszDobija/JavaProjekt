package works.buddy.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import works.buddy.library.model.Book;

import java.util.Collection;

@Repository
@Primary
public class HibernateBookDAO implements BookDAO {

    @Autowired
    private final SessionFactory sessionFactory;

    public HibernateBookDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Collection<Book> findAll() {
        return getCurrentSession().createQuery("from books").list();
    }

    @Override
    public void save(Book book) {
        getCurrentSession().save(book);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Book find(Integer id) throws NotFoundException {
        return getCurrentSession().get(Book.class, id);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        getCurrentSession().delete(find(id));
    }

    @Override
    public void update(Book book) throws NotFoundException {
        getCurrentSession().merge(book);
    }
}
