package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LibraryController implements Initializable{

	Database db = new Database();
	
	public Book selectedBook;
	
	public Book userSelectedBook;
	
	@FXML
	public ListView<Book> availableBooksListView;
	
	@FXML
	public ListView<Book> checkedOutBooksListView;
	
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
	
	@FXML
	public Button logoutButton;
	
	@FXML
	public Button adminCreateBookButton;
	
	@FXML
	public Button adminCreateBookInstanceButton;
	
	@FXML
	public TextField searchBoxTextField;
	
	@FXML BorderPane mainBorderPane;
	
	
	public LibraryController() throws SQLException {
		
	}
	
	
	public void initialize(URL location, ResourceBundle resources){
		SessionController sessionController = new SessionController();
		this.currentUserUsername.setText("Username: " + sessionController.currentUser().username);
		this.currentUserFullName.setText("Name: " + sessionController.currentUser().firstName + " " + sessionController.currentUser().lastName);
		updateAvailableBooksListView();
		updateCheckedOutBooksListView();

		
		if(sessionController.currentUser().isAdmin) {
			adminCreateBookButton.setVisible(true);
			adminCreateBookInstanceButton.setVisible(true);
		} else {
			adminCreateBookButton.setVisible(false);
			adminCreateBookInstanceButton.setVisible(false);
		}
		
		Image img = new Image("/application/wood_image.jpeg");
		BackgroundImage background_img = new BackgroundImage(img, null, null, null, null);
		mainBorderPane.setBackground(new Background(background_img));
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
	public void userBookSelected(Event event) {
		this.userSelectedBook = checkedOutBooksListView.getSelectionModel().getSelectedItem();
	}
	
	@FXML
	public void checkoutBook(Event event) throws SQLException {
		Database db = new Database();
		SessionController sessionController = new SessionController();
		System.out.println("Checking out book id " + selectedBook.id + " titled " + selectedBook.title);
		db.checkoutBook(selectedBook, sessionController.currentUser());
		updateAvailableBooksListView();
		updateCheckedOutBooksListView();
		resetSelectedBook();
	}
	
	@FXML
	public void checkInBook(Event event) throws SQLException {
		Database db = new Database();
		db.checkInBook(userSelectedBook);
		updateAvailableBooksListView();
		updateCheckedOutBooksListView();
		this.userSelectedBook = null;
	}
	
	@FXML
	public void refreshListViews(Event event) {
		updateAvailableBooksListView();
		updateCheckedOutBooksListView();
	}
	
	@FXML
	public void logout(Event event) throws IOException {
		SessionController sessionController = new SessionController();
		sessionController.destroySession();
		Parent root = FXMLLoader.load(getClass().getResource("/application/LoginScreen.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
	
	public void updateAvailableBooksListView() {
		try {
			availableBooksListView.getItems().clear();
			availableBooksListView.getItems().addAll(db.getAvailableBooks("title"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		availableBooksListView.setCellFactory(new BookCellFactory());
	}
	
	public void updateAvailableBooksListViewWithSearch() {
		try {
			ObservableList<Book> list = db.getAvailableBooks("title");
			
			availableBooksListView.getItems().clear();
			
			for (int i=0; i < list.size(); i++) {
				if (list.get(i).getTitle().toLowerCase().contains(searchBoxTextField.getText().toLowerCase())) {
					availableBooksListView.getItems().add(list.get(i));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		availableBooksListView.setCellFactory(new BookCellFactory());
	}
	
	public void updateCheckedOutBooksListView() {
		SessionController sessionController = new SessionController();
		try {
			checkedOutBooksListView.getItems().clear();
			checkedOutBooksListView.getItems().addAll(db.getCheckedOutBooks(sessionController.currentUser()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkedOutBooksListView.setCellFactory(new BookCellFactory());
	}
	
	public void resetSelectedBook() {
		this.selectedBook = null;
		this.selectedBookTitleLabel.setText("Title: ");
		this.selectedBookAuthorLabel.setText("Author: ");
		this.selectedBookPublishedYearLabel.setText("Year Published: ");
		this.selectedBookNumAvailableLabel.setText("# Available: ");
	}
	
	public void loadAdminCreateBookScreen(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/AdminCreateBookScreen.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
	
	public void loadAdminCreateBookInstancesScreen(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/AdminCreateBookInstancesScreen.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
}
