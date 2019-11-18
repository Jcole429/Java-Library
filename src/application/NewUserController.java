package application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewUserController {
	
	@FXML
	public TextField firstNameTextField;
	
	@FXML
	public TextField lastNameTextField;
	
	@FXML
	public TextField usernameTextField;
	
	@FXML
	public TextField passwordTextField;
	
	@FXML
	public TextField passwordConfirmTextField;
	
	
	public NewUserController() {
		
	}
	
	public void login(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/LoginScreen.fxml"));
		
		Node node = (Node) event.getSource();
		
		Stage stage = (Stage) node.getScene().getWindow();
		
		stage.setScene(new Scene(root));
	}
	
	public void createNewUser() {
		Database db = new Database();
		User user = new User(firstNameTextField.getText(),lastNameTextField.getText(),usernameTextField.getText(),passwordTextField.getText(),passwordConfirmTextField.getText());
		if (user.firstName.equals("")||user.lastName.equals("")||user.username.equals("")||user.password.equals("")||user.passwordConfirmation.equals("")) {
			System.out.println("Must fill in all fields");
		} else if (!passwordTextField.getText().equals(passwordConfirmTextField.getText())) {
			System.out.println("Passwords do not match!");
		} else if (db.doesUserExist(usernameTextField.getText())) {
			System.out.println("User already exists");
		}
		else {
			boolean result = db.createNewUser(user.firstName,user.lastName,user.username,user.password);
			if (result) {
				System.out.println("User added");
			} else {
				System.out.println("User not added");
			}
		}
	}
	

}
