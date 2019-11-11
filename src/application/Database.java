package application;

import java.sql.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {
	
	private Connection conn;

	public Database() {
		
		String url = "jdbc:postgresql://localhost:5432/java_library";
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url);
			this.conn = conn;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ObservableList<Book> getBooks() throws SQLException {
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM books");
		
		ObservableList<Book> list = FXCollections.observableArrayList();
		
		while (rs.next())
		{
			list.add(new Book(
					rs.getString("title")
					,rs.getString("authorFirstName")
					,rs.getString("authorLastName")
					,rs.getInt("yearPublished")
					));
		}
		
		rs.close();
		st.close();
		
		return list;
	}
	
	public ObservableList<Book> getAvailableBooks() throws SQLException {
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM vw_available_books");
		
		ObservableList<Book> list = FXCollections.observableArrayList();
		
		while (rs.next()) {
			list.add(new Book(
					rs.getString("title")
					,rs.getString("authorFirstName")
					,rs.getString("authorLastName")
					,rs.getInt("yearPublished")
					,rs.getInt("numAvailable")
					));
		}
		
		rs.close();
		st.close();
		
		return list;
	}
	
	public ObservableList<Book> getAvailableBooks(String orderBy) throws SQLException {
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM vw_available_books order by " + orderBy);
		
		ObservableList<Book> list = FXCollections.observableArrayList();
		
		while (rs.next()) {
			list.add(new Book(
					rs.getString("title")
					,rs.getString("authorFirstName")
					,rs.getString("authorLastName")
					,rs.getInt("yearPublished")
					,rs.getInt("numAvailable")
					));
		}
		
		rs.close();
		st.close();
		
		return list;
	}
	
	
}
