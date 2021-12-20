package ec.lab;

import java.util.ArrayList;
import java.util.List;

public class UserData {
	public List<User> getUserList() {
		List<User> listUser = new ArrayList<>();
		listUser.add(new User("admin", 1));
		listUser.add(new User("me", 2));
		listUser.add(new User("guest", 3));
		return listUser;
	}
}
