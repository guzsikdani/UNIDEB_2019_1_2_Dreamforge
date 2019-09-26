package dao;

import util.jpa.GenericJpaDao;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO extends GenericJpaDao {

    public UserDAO() { super(User.class); }

    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        List<User> result = query.getResultList();
        return !result.isEmpty() ? result : null;
    }
}
