package stats.ejb;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.jpa.model.User;

@Stateless
@LocalBean
public class UserEJBStateless implements UserEJBStatelessLocal {
	@PersistenceContext(unitName = "primary")
	private EntityManager entityManager;

	@Override
	public User getUserByName(String username) {
		Query query = entityManager.createQuery("select u from User u where u.name = :username");
		query.setParameter("username", username);
		
		try {
			User user = (User) query.getSingleResult();
			return user;
		} catch (EntityNotFoundException notFound) {
			return null;
		}
	}
	
	public ArrayList<User> getAllUsers() {
		Query query = entityManager.createQuery("select u from User u");		
		
		try {
			ArrayList<User> users = (ArrayList<User>) query.getResultList();
			return users;
		} catch (EntityNotFoundException notFound) {
			return null;
		}
	}

	@Override
	public void saveUser(User user) {
		entityManager.persist(user);
	}
}
