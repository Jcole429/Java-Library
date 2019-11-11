package application;

public class User {
	
	String firstName;
	String lastName;
	boolean isAdmin;
	
	
	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User(String firstName, String lastName, boolean isAdmin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
	}

}
