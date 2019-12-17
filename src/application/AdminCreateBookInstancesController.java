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
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

public class AdminCreateBookInstancesController implements Initializable {
	
	Database db = new Database();
	
	Book selectedBook;
	
	Book selectedBookInstance;
	
	@FXML
	public SplitPane splitPane;
	
	@FXML
	public ListView<Book> bookListView;
	
	@FXML
	public ListView<Book> bookListInstancesView;
	
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
		
		Image img = new Image("/application/wood_image.jpeg");
		BackgroundImage background_img = new BackgroundImage(img, null, null, null, null);
		splitPane.setBackground(new Background(background_img));
	}
	
	@FXML
	public void bookSelected(Event event) {
		this.selectedBook = bookListView.getSelectionModel().getSelectedItem();
		this.bookIdLabel.setText("Book ID: " + selectedBook.id);
		this.bookTitleLabel.setText("Title: " + selectedBook.getTitle());
		this.authorLabel.setText("Author: " + selectedBook.authorFirstName + " " + selectedBook.authorLastName);
		this.yearPublishedLabel.setText("Year published: " + selectedBook.yearPublished);

		updateBookInstances(this.selectedBook.getTitle());
	}
	
	@FXML
	public void bookInstanceSelected(Event event) {
		this.selectedBookInstance = bookListInstancesView.getSelectionModel().getSelectedItem();
		this.bookInstanceIdLabel.setText("Book Instance ID: " + selectedBookInstance.instance_id);
		if (selectedBookInstance.checkedOutBy == 0) {
			this.checkedOutByLabel.setText("Checked out by: None");
		}
		else {this.checkedOutByLabel.setText("Checked out by: " + selectedBookInstance.checkedOutBy);}
	}
	
	public void updateBooks() {
		try {
			bookListView.getItems().clear();
			bookListView.getItems().addAll(db.getBooks());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookListView.setCellFactory(new BookCellFactory());
	}
	
	public void updateBookInstances(String title) {
		try {
			System.out.println("Started");
			bookListInstancesView.getItems().clear();
			bookListInstancesView.getItems().addAll(db.getBookInstances(title));
			this.bookInstanceIdLabel.setText("Book Instance ID: ");
			this.checkedOutByLabel.setText("Checked out by: ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bookListInstancesView.setCellFactory(new BookCellFactory());
		System.out.println("Finished");
	}
	
	@FXML
	public void deleteBookInstance() throws SQLException {
		db.deleteBookInstance(selectedBookInstance);
		updateBookInstances(selectedBook.getTitle());
	}
	
	@FXML
	public void createBookInstance() throws SQLException {
		db.createBookInstance(selectedBook);
		updateBookInstances(selectedBook.getTitle());
	}
	
	public void loadLibraryScreen(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/LibraryScreen.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}

}
