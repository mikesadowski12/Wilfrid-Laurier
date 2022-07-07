package ec.lab;

public class User {
	private String name;
	private int role;

	public User() {
		super();
	}

	public User(String name, int role) {
		super();
		this.name = name;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
