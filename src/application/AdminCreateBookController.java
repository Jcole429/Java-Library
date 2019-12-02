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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminCreateBookController implements Initializable {
	
	@FXML
	public Button createBookButton;
	
	@FXML
	public Button cancelButton;
	
	@FXML
	public TextField titleTextField;
	
	@FXML
	public TextField authorFirstNameTextField;
	
	@FXML
	public TextField authorLastNameTextField;
	
	@FXML
	public TextField yearPublishedTextField;
	
	@FXML
	public Label errorLabel;

	public AdminCreateBookController() {
		
	}
	
	public void initialize(URL location, ResourceBundle resources){
		SessionController sessionController = new SessionController();
		
	}
	
	@FXML
	public void cancel(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/LibraryScreen.fxml"));
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		stage.setScene(new Scene(root));
	}
	
	@FXML
	public void createBook(Event event) throws SQLException {
		
		String title = titleTextField.getText();
		String firstName = authorFirstNameTextField.getText();
		String lastName = authorLastNameTextField.getText();
		int yearPublished = -1;
		if (yearPublishedTextField.getText().length() > 0) {
			yearPublished = Integer.parseInt(yearPublishedTextField.getText());	
		}
		
		if (title.equals("") || firstName.equals("") || lastName.equals("") || yearPublished == -1) {
			errorLabel.setText("Complete all fields");
		} else {
			Database db = new Database();
			
			Book newBook = new Book(title,firstName,lastName,yearPublished);
			
			db.createBook(newBook);
			
			errorLabel.setText("Book created");
		}
	}
}
