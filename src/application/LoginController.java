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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

public class LoginController implements Initializable{
	
	@FXML
	public AnchorPane mainAnchorPane;
	
	@FXML
	public TextField usernameTextField;
	
	@FXML
	public TextField passwordTextField;
	
	@FXML
	public Label errorLabel;
	
	
	public LoginController() {
		
	}
	
	public void initialize(URL location, ResourceBundle resources){
		
		Image img = new Image("/application/wood_image.jpeg");
		BackgroundImage background_img = new BackgroundImage(img, null, null, null, null);
		mainAnchorPane.setBackground(new Background(background_img));
		
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
