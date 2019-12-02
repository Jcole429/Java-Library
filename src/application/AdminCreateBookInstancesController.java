package application;

import java.io.IOException;
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

public class AdminCreateBookInstancesController implements Initializable {
	
	Database db = new Database();
	
	Book selectedBook;
	
	@FXML
	public ListView<Book> bookListView;
	
	@FXML
	public Label bookInstanceIdLabel;
	
	@FXML
	public Label bookIdLabel;
	
	@FXML
	public Label bookTitleLabel;
	
	@FXML
	public Label authorLabel;
	
	@FXML
	public Label checkedOutByLabel;
	
	@FXML
	public Label yearPublishedLabel;
	
	@FXML
	public Button createBookInstanceButton;
	
	@FXML
	public Button deleteBookInstanceButton;
	
	public AdminCreateBookInstancesController() {
		
	}
	
	public void initialize(URL location, ResourceBundle resources){
		SessionController sessionController = new SessionController();
		updateBooks();
	}
	
	@FXML
	public void bookSelected(Event event) {
		this.selectedBook = bookListView.getSelectionModel().getSelectedItem();
		this.bookInstanceIdLabel.setText("Book Instance ID: " + selectedBook.instance_id);
		this.bookIdLabel.setText("Book ID: " + selectedBook.id);
		this.bookTitleLabel.setText("Title: " + selectedBook.getTitle());
		this.authorLabel.setText("Author: " + selectedBook.authorFirstName + " " + selectedBook.authorLastName);
		this.yearPublishedLabel.setText("Year published: " + selectedBook.yearPublished);
		if (selectedBook.checkedOutBy == 0) {
			this.checkedOutByLabel.setText("Checked out by: ");
		}
		else {this.checkedOutByLabel.setText("Checked out by: " + selectedBook.checkedOutBy);}
	}
	
	public void updateBooks() {
		try {
			System.out.println("Started");
			bookListView.getItems().clear();
			bookListView.getItems().addAll(db.getBookInstances("title"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookListView.setCellFactory(new BookCellFactory());
		System.out.println("Finished");
	}
	
	@FXML
	public void deleteBookInstance() throws SQLException {
		db.deleteBookInstance(selectedBook);
		updateBooks();
	}
	
	@FXML
	public void createBookInstance() throws SQLException {
		db.createBookInstance(selectedBook);
		updateBooks();
	}
	
	public void loadLibraryScreen(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/LibraryScreen.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

}
