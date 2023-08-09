package works.buddy.library.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import works.buddy.library.model.Book;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

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
        return getCurrentSession().createQuery("from Book", Book.class).getResultList();
    }

    @Override
    public void save(Book book) {
        try {
            dbTransactionVoid(getCurrentSession(), o -> o.persist(book));
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public Book find(Integer id) throws NotFoundException {
        try {
            Book book = getCurrentSession().find(Book.class, id);
            if (book == null) {
                throw new NotFoundException();
            }
            return book;
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        try {
            dbTransactionVoid(getCurrentSession(), o -> o.delete(find(id)));
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public void update(Book book) throws NotFoundException {
        try {
            dbTransaction(getCurrentSession(), o -> o.merge(book));
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    private void dbTransaction(Session session, Function<Session, Object> func) {
        session.getTransaction().begin();
        func.apply(session);
        session.getTransaction().commit();
    }

    private void dbTransactionVoid(Session session, Consumer<Session> func) {
        session.getTransaction().begin();
        func.accept(session);
        session.getTransaction().commit();
    }
}
