package application;

import java.io.IOException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {
	
	public LoginController() {
		
	}
	
	public void login(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/LibraryScreen.fxml"));
		
		Node node = (Node) event.getSource();
		
		Stage stage = (Stage) node.getScene().getWindow();
		
		stage.setScene(new Scene(root));
		
	}
	
	public void newUser(Event event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/application/NewUserScreen.fxml"));
		
		Node node = (Node) event.getSource();
		
		Stage stage = (Stage) node.getScene().getWindow();
		
		stage.setScene(new Scene(root));
	}
}
