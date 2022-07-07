package ec.jpa.repository;

import javax.persistence.EntityManager;
import ec.jpa.model.User;

import java.util.List;

public class UserRepository {
    private EntityManager entityManager;
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    public User save(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void createUser(User user) {
        entityManager.persist(user);
    }
    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }
    public User findById(Integer id) {
        User user = entityManager.find(User.class, id);
        return user != null ? user : null;
    }
    public User findByID(int id) {
        User user = entityManager.createQuery("select u from User u where u.id = :userid",  User.class).setParameter("userid", id).getSingleResult();
        return user != null ? user : null;
    }
    public User findByName(String name) {
        List<User> users = entityManager.createNamedQuery("User.findByName", User.class)
                .setParameter("name", name)
                .getResultList();
        if (users.size() == 0) return null;
        else return users.get(0);

    }
}

