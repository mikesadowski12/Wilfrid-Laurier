package ec.lab;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDao  {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private UserRepository repository;
    
//    public User save(User user) {
//        try {
//            entityManager.getTransaction().begin();
//            entityManager.persist(user);
//            entityManager.getTransaction().commit();
//            return user;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public void createUser(User user) {
//        entityManager.persist(user);
//    }
    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }
//    public User findById(Integer id) {
//        User user = entityManager.find(User.class, id);
//        return user != null ? user : null;
//    }
//    public User findByID(int id) {
//        User user = entityManager.createQuery("select u from User u where u.id = :userid",  User.class).setParameter("userid", id).getSingleResult();
//        return user != null ? user : null;
//    }
//    public User findByName(String name) {
//        List<User> users = entityManager.createNamedQuery("User.findByName", User.class)
//                .setParameter("name", name)
//                .getResultList();
//        if (users.size() == 0) return null;
//        else return users.get(0);
//    }
//    public void removeUserByID(Integer id) {
//        try {
//            entityManager.getTransaction().begin();
//            entityManager.createQuery("delete from User u where u.id = :userid").setParameter("userid", id).executeUpdate();
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void removeAll() {
//        try {
//            entityManager.getTransaction().begin();
//            entityManager.createQuery("delete from User u").executeUpdate();
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    
    
}
