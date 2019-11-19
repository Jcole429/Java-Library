package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	public TextField usernameTextField;
	
	@FXML
	public TextField passwordTextField;
	
	@FXML
	public Label errorLabel;
	
	
	public LoginController() {
		
	}
	
	public void login(Event event) throws IOException, SQLException {
		Database db = new Database();
		if (db.isLoginSuccessful(usernameTextField.getText(), passwordTextField.getText())) {
			SessionController sessionController = new SessionController();
			sessionController.createSession(db.getUser(usernameTextField.getText()));
			Parent root = FXMLLoader.load(getClass().getResource("/application/LibraryScreen.fxml"));
			
			Node node = (Node) event.getSource();
			
			Stage stage = (Stage) node.getScene().getWindow();
			
			stage.setScene(new Scene(root));
		} else {
			errorLabel.setText("Login Unsuccessful");
		}
		
	}
	
	public void newUser(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewUserScreen.fxml"));
		
		Node node = (Node) event.getSource();
		
		Stage stage = (Stage) node.getScene().getWindow();
		
		stage.setScene(new Scene(root));
	}
}
