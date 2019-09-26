package dao;

import model.Book;
import util.jpa.GenericJpaDao;
import javax.persistence.TypedQuery;
import java.util.List;

public class BookDAO extends GenericJpaDao {

    public BookDAO() { super(Book.class); }

    public List<Book> bookRead(String searchBy, String searchTerm) {
        if (searchBy.equals("id")) {
            TypedQuery<Book> query = entityManager.createQuery("select b from Book b where id = ?1", Book.class)
                    .setParameter(1, searchTerm);
            List<Book> result = query.getResultList();
            return !result.isEmpty() ? result : null;
        }

        else if (searchBy.equals("title")) {
            TypedQuery<Book> query = entityManager.createQuery("select b from Book b where title = ?1", Book.class)
                    .setParameter(1, searchTerm);
            List<Book> result = query.getResultList();
            return !result.isEmpty() ? result : null;
        }

        else if (searchBy.equals("author")) {
            TypedQuery<Book> query = entityManager.createQuery("select b from Book b where author = ?1", Book.class)
                    .setParameter(1, searchTerm);
            List<Book> result = query.getResultList();
            return !result.isEmpty() ? result : null;
        }

        else
            return null;
    }
}
