package application;

public class User {
	
	String firstName;
	String lastName;
	String username;
	String password;
	String passwordConfirmation;
	boolean isAdmin;	
	
	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User(String firstName, String lastName, String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	public User(String firstName, String lastName, String username, String password, String passwordConfirmation) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
	}

}
