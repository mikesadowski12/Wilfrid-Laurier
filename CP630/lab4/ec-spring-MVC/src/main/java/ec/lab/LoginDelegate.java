package ec.lab;

import java.sql.SQLException;


public class LoginDelegate {
	
	private String USERNAME = "admin";
	private String PASSWORD = "admin";
	
	public boolean validete(String username, String password) throws SQLException {
		return USERNAME.equals(username) && PASSWORD.equals(password);
	}
}
