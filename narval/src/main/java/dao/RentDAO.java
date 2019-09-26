package dao;

import com.google.inject.persist.Transactional;
import model.Rent;
import util.jpa.GenericJpaDao;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class RentDAO extends GenericJpaDao {

    public RentDAO() {
        super(Rent.class);
    }

    public List<Rent> getAllRents() {
        TypedQuery<Rent> query = entityManager.createQuery("select r from Rent r", Rent.class);
        List<Rent> result = query.getResultList();
        return !result.isEmpty() ? result : null;
    }

    @Transactional
    public void updateRent(Rent rent) {
        if (rent != null) {
            Date currentDate = new Date(System.currentTimeMillis());
            rent.setEndDate(currentDate);
        }
    }

    public List<String> getRentsWithoutEndDate() {
        TypedQuery<String> query = entityManager.createQuery("select r.id || ' : ' || b.title || ' : ' || u.name " +
                "|| ' : ' || r.deadline from Rent r, User u, Book b where r.bookId = b.id and r.userId = u.id and r.endDate is null",
                String.class);
        List<String> result = query.getResultList();
        return !result.isEmpty() ? result : null;
    }
}
