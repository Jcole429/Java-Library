package application;

import javafx.scene.control.ListCell;

public class BookCell extends ListCell<Book> {
	
	public void updateItem(Book item, boolean empty) {
		if (empty) {
//			System.out.println("Empty Item");
		}
		else {
			super.updateItem(item, empty);
			this.setText(item.getTitle());
		}
	}
}
