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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class LibraryController implements Initializable{

	Database db = new Database();
	
	@FXML
	public ListView<Book> listView;
	
	@FXML
	public Book selectedBook;
	
	@FXML
	public Label selectedBookTitleLabel;
	
	@FXML
	public Label selectedBookAuthorLabel;
	
	@FXML
	public Label selectedBookPublishedYearLabel;
	
	@FXML
	public Label selectedBookNumAvailableLabel;
	
	public LibraryController() throws SQLException {
		System.out.println("In Library Controller");
	}
	
	
	public void initialize(URL location, ResourceBundle resources){
		try {
			listView.getItems().addAll(db.getAvailableBooks("title"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listView.setCellFactory(new BookCellFactory());
	}
	
	@FXML
	public void bookSelected(Event event) {
		this.selectedBook = listView.getSelectionModel().getSelectedItem();
		this.selectedBookTitleLabel.setText("Title: " + this.selectedBook.title);
		this.selectedBookAuthorLabel.setText("Author: " + this.selectedBook.authorFirstName + " " + this.selectedBook.authorLastName);
		this.selectedBookPublishedYearLabel.setText("Year Published: " + this.selectedBook.yearPublished);
		this.selectedBookNumAvailableLabel.setText("# Available: " + this.selectedBook.numAvailable);
	}
	
}
