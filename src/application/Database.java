package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Database {
	
	private Connection conn;

	public Database() {
		
		String url = "jdbc:postgresql://localhost:5432/java_library";
		
		Properties props = new Properties();
		String db_url = "jdbc:postgresql://ec2-54-225-115-177.compute-1.amazonaws.com:5432/d42rfdpu706cgu";
		props.setProperty("user", "ebyevyrayojzsu");
		props.setProperty("password", "2a1082e27d87e03da1447d5b6e32e14908afb625a431af9e23f6da5423466d22");
		
		try {
			Class.forName("org.postgresql.Driver");
//			Connection conn = DriverManager.getConnection(url);
			Connection conn = DriverManager.getConnection(db_url, props);
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
	
	public boolean createNewUser(String firstName,String lastName,String username,String password) {
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(
					"INSERT INTO USERS (first_name,last_name,username,password_hash)"
					+ "VALUES ('"+firstName+"','"+lastName+"','"+username.toLowerCase()+"',crypt('"+password+"',gen_salt('bf')));");
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean doesUserExist(String username) {
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT username FROM users where username = '" + username.toLowerCase() + "';");
			if (rs.next() == false) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean isLoginSuccessful(String username, String password) throws SQLException {
		Statement st;
		st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT username FROM users WHERE username = '" + username.toLowerCase() + "';");
		if (rs.next() == false) {
			System.out.println("User does not exist");
			return false;
		}
		rs = st.executeQuery("SELECT username FROM users WHERE username = '" + username.toLowerCase() + "' and password_hash = crypt('" + password+ "',password_hash);");
//		System.out.println(rs.next());
		if (rs.next() == false) {
			System.out.println("Incorrect password");
			return false;
		}
		System.out.println("Successful login");
		return true;
	}
	
	public User getUser(String username) {
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT username,first_name,last_name FROM users WHERE username='" + username.toLowerCase() + "';");
			
			User user;
			
			while (rs.next()) {
				user = new User(rs.getString("first_name"),rs.getString("last_name"),rs.getString("username"));
				return user;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
