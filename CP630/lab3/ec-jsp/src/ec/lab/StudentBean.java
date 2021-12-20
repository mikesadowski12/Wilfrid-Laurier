package ec.lab;

import java.io.Serializable;

public class StudentBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String firstName = null;
	private String lastName = null;
	private int score = 0;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return this.score;
	}
}


