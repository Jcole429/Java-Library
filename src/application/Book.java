package application;

public class Book {
	
	int id;
	int instance_id;
	String title;
	String authorFirstName;
	String authorLastName;
	int yearPublished;
	int numAvailable;
	int checkedOutBy;
	int numCopies;
	
	public Book(int id,String title, String authorFirstName, String authorLastName, int yearPublished) {
		this.id = id;
		this.title = title;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.yearPublished = yearPublished;
	}
	
	public Book(String title, String authorFirstName, String authorLastName, int yearPublished) {
		this.title = title;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.yearPublished = yearPublished;
	}
	
	public Book(int id,int instance_id,String title, String authorFirstName, String authorLastName, int yearPublished, int checkedOutBy) {
		this.id = id;
		this.instance_id = instance_id;
		this.title = title;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.yearPublished = yearPublished;
		this.checkedOutBy = checkedOutBy;
	}
	
	public Book(int id,String title, String authorFirstName, String authorLastName, int yearPublished, int numAvailable, int numCopies) {
		this.id = id;
		this.title = title;
		this.authorFirstName = authorFirstName;
		this.authorLastName = authorLastName;
		this.yearPublished = yearPublished;
		this.numAvailable = numAvailable;
		this.numCopies = numCopies;
	}
	
	public String getTitle() {
		return title;
	}

}
