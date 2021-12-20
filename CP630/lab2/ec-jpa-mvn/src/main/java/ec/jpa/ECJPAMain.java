package ec.jpa;

import ec.jpa.model.User;
import ec.jpa.repository.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class ECJPAMain {

    public static void main(String[] args) throws IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ec-jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserRepository userrepository = new UserRepository(entityManager);

        User user = new User("admin");
        User savedUser = userrepository.save(user);
        System.out.println("Saved User: " + savedUser.getName());
        
        User findUser = userrepository.findById(savedUser.getId());
        System.out.println("Find user by id:");
        if ( findUser != null) System.out.println(findUser.toString());
       
        findUser = userrepository.findByID(savedUser.getId());
        System.out.println("Find user by ID (query):");
        if ( findUser != null) System.out.println(findUser.toString());
        
        findUser = userrepository.findByName("guest");
        System.out.println("Find user by name:");
        if ( findUser != null) System.out.println(findUser.toString());
        
        List<User> users = userrepository.findAll();
        System.out.println("Users:");
        users.forEach(System.out::println);
       
        entityManager.close();
        entityManagerFactory.close();
    }
}