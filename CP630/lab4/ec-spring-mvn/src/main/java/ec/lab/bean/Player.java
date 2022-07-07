package ec.lab.bean;

public class Player {
	private String firstName = null;
	private String lastName = null;
	private int score = 0;

	public Player() {
	}

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

	public void printName() {
		System.out.println("Name: " + this.firstName + " " + this.lastName);
	}

	public void printThrowException() {
		throw new IllegalArgumentException();
	}

	public void checkScore() {
		if (this.score < 0) {
			throw new IllegalArgumentException();
		}
	}

}
