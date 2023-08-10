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
        try {
            getCurrentSession().persist(book);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public Book find(Integer id) throws NotFoundException {
        Book book = getCurrentSession().find(Book.class, id);
        if (book == null) {
            throw new NotFoundException();
        }
        return book;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        try {
            getCurrentSession().delete(find(id));
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public void update(Book book) throws NotFoundException {
        find(book.getId());
        try {
            getCurrentSession().clear();
            getCurrentSession().update(book);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}
