package works.buddy.library.dao;

import org.apache.commons.lang3.NotImplementedException;
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
        return sessionFactory.openSession();
    }

    @Override
    public Collection<Book> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public void save(Book book) {
        try {
            Session session = getCurrentSession();
            session.getTransaction().begin();
            session.persist(book);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book find(Integer id) throws NotFoundException {
        return getCurrentSession().find(Book.class, id);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        getCurrentSession().remove(find(id));
    }

    @Override
    public void update(Book book) throws NotFoundException {
        getCurrentSession().merge(book);
    }
}
