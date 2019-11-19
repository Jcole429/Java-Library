package application;

public class Book {
	
	int id;
	String title;
	String authorFirstName;
	String authorLastName;
	int yearPublished;
	int numAvailable;
	int checkedOutBy;
	
	public Book(int id,String title, String authorFirstName, String authorLastName, int yearPublished) {
		this.id = id;
		this.title = title;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.yearPublished = yearPublished;
	}
	
	public Book(int id,String title, String authorFirstName, String authorLastName, int yearPublished, int numAvailable) {
		this.id = id;
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
