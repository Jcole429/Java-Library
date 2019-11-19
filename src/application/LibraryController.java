package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LibraryController implements Initializable{

	Database db = new Database();
	
	public Book selectedBook;
	
	@FXML
	public ListView<Book> availableBooksListView;
	
	@FXML
	public Label selectedBookTitleLabel;
	
	@FXML
	public Label selectedBookAuthorLabel;
	
	@FXML
	public Label selectedBookPublishedYearLabel;
	
	@FXML
	public Label selectedBookNumAvailableLabel;
	
	@FXML
	public Label currentUserUsername;
	
	@FXML
	public Label currentUserFullName;
	
	@FXML
	public Button checkoutButton;
	
	@FXML
	public Button checkInButton;
	
	public LibraryController() throws SQLException {
		
	}
	
	
	public void initialize(URL location, ResourceBundle resources){
		try {
			availableBooksListView.getItems().addAll(db.getAvailableBooks("title"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		availableBooksListView.setCellFactory(new BookCellFactory());
		
		SessionController sessionController = new SessionController();
		
		this.currentUserUsername.setText("Username: " + sessionController.currentUser().username);
		this.currentUserFullName.setText("Name: " + sessionController.currentUser().firstName + " " + sessionController.currentUser().lastName);
	}
	
	@FXML
	public void bookSelected(Event event) {
		this.selectedBook = availableBooksListView.getSelectionModel().getSelectedItem();
		this.selectedBookTitleLabel.setText("Title: " + this.selectedBook.title);
		this.selectedBookAuthorLabel.setText("Author: " + this.selectedBook.authorFirstName + " " + this.selectedBook.authorLastName);
		this.selectedBookPublishedYearLabel.setText("Year Published: " + this.selectedBook.yearPublished);
		this.selectedBookNumAvailableLabel.setText("# Available: " + this.selectedBook.numAvailable);
	}
	
	@FXML
	public void checkoutBook(Event event) throws SQLException {
		Database db = new Database();
		SessionController sessionController = new SessionController();
		db.checkoutBook(selectedBook, sessionController.currentUser());
	}
}
