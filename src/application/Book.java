package application;

public class Book {
	
	String title;
	String authorFirstName;
	String authorLastName;
	int yearPublished;
	int numAvailable;
	int checkedOutBy;
	
	public Book(String title, String authorFirstName, String authorLastName, int yearPublished) {
		this.title = title;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.yearPublished = yearPublished;
	}
	
	public Book(String title, String authorFirstName, String authorLastName, int yearPublished, int numAvailable) {
		this.title = title;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.yearPublished = yearPublished;
		this.numAvailable = numAvailable;
	}
	
	public String getTitle() {
		return title;
	}

}
