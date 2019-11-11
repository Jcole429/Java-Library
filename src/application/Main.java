package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends Application implements EventHandler<ActionEvent> {
	
	Button button;
	Database db = new Database();
	
	Book selectedBook;
	User currentUser;
	
	Label selectedBookTitleLabel = new Label();
	Label selectedBookAuthorLabel = new Label();
	Label selectedBookYearPublishedLabel = new Label();
	Label selectedBookNumAvailableLabel = new Label();
	
	Label currentUserNameLabel = new Label();
	Label currentUserIsAdminLabel = new Label();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			button = new Button("Click me");
//			button.setOnAction(this);
			
			Label pageTitle = new Label("Welcome to the library");
//			pageTitle.setTextAlignment(TextAlignment.RIGHT);
			
			HBox titleBox = new HBox(pageTitle);
			titleBox.setAlignment(Pos.CENTER);
			
			// Setup main BorderPane
			BorderPane mainPane = new BorderPane();
			mainPane.setLeft(bookBrowser());
			mainPane.setRight(userScreen());
			mainPane.setCenter(centerScreen());
			mainPane.setTop(titleBox);
	
			
			// Setup main scene with the BorderPane
			Scene scene = new Scene(mainPane,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// Add the main scene to the stage
			primaryStage.setScene(scene);
			primaryStage.setTitle("Public Library");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public VBox bookBrowser() throws SQLException {
		Label title = new Label("Available Books");
		VBox titleVBox = new VBox(title);
		titleVBox.setAlignment(Pos.CENTER);
		
		ListView<Book> list = new ListView<Book>();
		list.getItems().addAll(db.getAvailableBooks("title"));
		list.setCellFactory(new BookCellFactory());
		
		list.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> updateSelectedBook(newValue));
		
		VBox outerVBox = new VBox(titleVBox,list);
		outerVBox.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: black");
		outerVBox.setPrefWidth(300);
		return outerVBox;
	}
	
	public VBox centerScreen() {
		Label title = new Label("Selected Book");
		VBox titleVBox = new VBox(title);
		titleVBox.setAlignment(Pos.CENTER);
		
		selectedBookTitleLabel.setText("Title: ");
		selectedBookAuthorLabel.setText("Author: ");
		selectedBookYearPublishedLabel.setText("Year Published: ");
		selectedBookNumAvailableLabel.setText("Number Available: ");
		VBox bookDetailsVBox = new VBox(
				selectedBookTitleLabel
				,selectedBookAuthorLabel
				,selectedBookYearPublishedLabel
				,selectedBookNumAvailableLabel);
//		bookDetailsVBox.setPrefWidth(50);
		VBox outerVBox = new VBox(titleVBox,bookDetailsVBox);
//		outerVBox.setPrefWidth(50);
		outerVBox.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: black");
		return outerVBox;
	}
	
	public VBox userScreen() {
		Label title = new Label("Current User");
		VBox titleVBox = new VBox(title);
//		titleVBox.setPrefWidth(50);
		titleVBox.setAlignment(Pos.CENTER);
		
		currentUserNameLabel.setText("Name: ");
		currentUserIsAdminLabel.setText("Is Admin?: ");
		VBox currentUserDetailsVBox = new VBox(
				currentUserNameLabel
				,currentUserIsAdminLabel);
//		bookDetailsVBox.setPrefWidth(50);
		VBox outerVBox = new VBox(titleVBox,currentUserDetailsVBox);
		outerVBox.setPrefWidth(200);
//		outerVBox.setAlignment(Pos.TOP_LEFT);
		outerVBox.setStyle("-fx-border-style: solid;"
                + "-fx-border-width: 1;"
                + "-fx-border-color: black");
		return outerVBox;
	}
	
	public void updateSelectedBook(Book newBook) {
		selectedBookTitleLabel.setText("Title: " + newBook.title);
		selectedBookAuthorLabel.setText("Author: " + newBook.authorFirstName + " " + newBook.authorLastName);
		selectedBookYearPublishedLabel.setText("Year Published: " + newBook.yearPublished);
		selectedBookNumAvailableLabel.setText("Number Available: " + newBook.numAvailable);
	}
	
	@Override
	public void handle(ActionEvent event) {
		if (event.getSource() == button) {
			System.out.println("It worked");
		}
	}
}
