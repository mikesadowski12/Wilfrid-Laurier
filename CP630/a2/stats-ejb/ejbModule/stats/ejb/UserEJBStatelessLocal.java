package stats.ejb;

import ec.jpa.model.User;

import javax.ejb.Local;

@Local
public interface UserEJBStatelessLocal {
    User getUserByName(String username);
    void saveUser(User user);
}
