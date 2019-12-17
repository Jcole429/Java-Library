package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

public class NewUserController implements Initializable {
	
	@FXML
	public AnchorPane mainAnchorPane;
	
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
	
	@FXML
	public Label errorLabel;
	
	public NewUserController() {
		
	}
	
	public void initialize(URL location, ResourceBundle resources){
		
		Image img = new Image("/application/wood_image.jpeg");
		BackgroundImage background_img = new BackgroundImage(img, null, null, null, null);
		mainAnchorPane.setBackground(new Background(background_img));
		
	}
	
	public void login(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/LoginScreen.fxml"));
		
		Node node = (Node) event.getSource();
		
		Stage stage = (Stage) node.getScene().getWindow();
		
		stage.setScene(new Scene(root));
	}
	
	public void createNewUser() {
		Database db = new Database();
		if (firstNameTextField.getText().equals("")||lastNameTextField.getText().equals("")||usernameTextField.getText().equals("")||passwordTextField.getText().equals("")||passwordConfirmTextField.getText().equals("")) {
			errorLabel.setText("Must fill in all fields");
		} else if (!passwordTextField.getText().equals(passwordConfirmTextField.getText())) {
			errorLabel.setText("Passwords do not match!");
		} else if (db.doesUserExist(usernameTextField.getText())) {
			errorLabel.setText("Username already exists!");
		}
		else {
			boolean result = db.createNewUser(firstNameTextField.getText(),lastNameTextField.getText(),usernameTextField.getText(),passwordTextField.getText());
			if (result) {
				System.out.println("User added");
				errorLabel.setText("User created!");
			} else {
				System.out.println("User not added");
			}
		}
	}
	

}
