package application;

public class User {
	
	int id;
	String firstName;
	String lastName;
	String username;
	boolean isAdmin;	
	
//	public User(String firstName, String lastName) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//	}
	
	public User(int id,String firstName, String lastName, String username) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
	}
}
