package dao;

import com.google.inject.persist.Transactional;
import model.Book;
import util.SearchBy;
import util.jpa.GenericJpaDao;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookDAO extends GenericJpaDao {

    public BookDAO() { super(Book.class); }

    public List<Book> bookRead(SearchBy sb , String searchTerm) {

        if (sb.equals(SearchBy.ID)) {
            TypedQuery<Book> query = entityManager.createQuery("select b from Book b where id = ?1", Book.class)
                    .setParameter(1, searchTerm);
            List<Book> result = query.getResultList();
            return !result.isEmpty() ? result : null;
        } else if (sb.equals(SearchBy.TITLE)) {
            TypedQuery<Book> query = entityManager.createQuery("select b from Book b where title = ?1", Book.class)
                    .setParameter(1, searchTerm);
            List<Book> result = query.getResultList();
            return !result.isEmpty() ? result : null;
        } else if (sb.equals(SearchBy.AUTHOR)) {
            TypedQuery<Book> query = entityManager.createQuery("select b from Book b where author = ?1", Book.class)
                    .setParameter(1, searchTerm);
            List<Book> result = query.getResultList();
            return !result.isEmpty() ? result : null;
        } else
            return null;
    }

    @Transactional
    public void updateAvailabilityToFalse(String bookId) {
        Book book = bookRead(SearchBy.ID, bookId).get(0);
        book.setAvailable(false);
    }

    @Transactional
    public void updateAvailabilityToTrue(String bookId) {
        Book book = bookRead(SearchBy.ID, bookId).get(0);
        book.setAvailable(true);
    }
}
