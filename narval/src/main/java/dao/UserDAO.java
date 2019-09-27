package dao;

import util.jpa.GenericJpaDao;
import model.User;

import javax.persistence.TypedQuery;
import java.util.List;

public class UserDAO extends GenericJpaDao {

    public UserDAO() { super(User.class); }

    public List<String> getAllUsers() {
        TypedQuery<String> query = entityManager.createQuery("select u.id || ':' || u.name from User u", String.class);
        List<String> result = query.getResultList();
        return !result.isEmpty() ? result : null;
    }

    public User getUserById(String userId) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id = " + userId, User.class);
        User result = query.getResultList().get(0);
        return result;
    }
}
