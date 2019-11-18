package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application implements EventHandler<ActionEvent> {
	
//	MainScreen mainScreen = new MainScreen();
//	LoginScreenView loginScreen = new LoginScreenView();
//	NewUserScreen newUserScreen = new NewUserScreen();
//	
//	Scene loginScene,mainScene,newUserScene;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
//		this.primaryStage = primaryStage;
		
//		try {
//			mainScene = mainScreen.mainScene(window);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		loginScene = loginScreen.loginScene(window,mainScene);
//		newUserScene = newUserScreen.newUserScene(window, loginScene);
//		LoginScreenModel loginScreenModel = new LoginScreenModel();
		
		
		
//		LoginScreenController loginScreenController = new LoginScreenController();
//		LoginScreenView loginScreenView = new LoginScreenView(loginScreenController);
		
		Parent root = FXMLLoader.load(getClass().getResource("/application/LoginScreen.fxml"));
		Scene scene = new Scene(root,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
//		primaryStage.setScene(loginScreenView.getScene());
		primaryStage.setScene(scene);
		primaryStage.setTitle("The Java Library");
		primaryStage.show();
	}
	
	@Override
	public void handle(ActionEvent event) {
		System.out.println("There was an event");
	}
}
