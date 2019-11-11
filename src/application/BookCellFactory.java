package application;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class BookCellFactory implements Callback<ListView<Book>,ListCell<Book>> {
	
	public ListCell<Book> call (ListView<Book> listview) {
		return new BookCell();
	}
	
}
