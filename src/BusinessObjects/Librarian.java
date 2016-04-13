package BusinessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Librarian extends DBOperations {
	
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	
	//check if user is connected before read and writes to database
	private boolean isConnected = false;
	private String username, password;
	
	
	public Librarian( String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public ResultSet showUsers(){
		if(!isConnected) return null;
		ResultSet resultSet = null;
		try 
		{
			preparedStatement = connect.prepareStatement("SELECT * FROM Users");
			resultSet = preparedStatement.executeQuery();
			
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		return resultSet;
	}// end showUsers
	
	@Override
	public boolean addUser(int uid, String lastName, String firstName, String role ){
		if(!isConnected) return false;
		
		try {
			preparedStatement = connect.prepareStatement("INSERT INTO Users VALUES ("
					+ uid + ", '" + lastName + "', '" + firstName  + "', false, '" + role + "')");
			preparedStatement.executeUpdate();
			isConnected = true;
			
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean connectToDB() {
		try 
		{
			connect = DriverManager
			          .getConnection("jdbc:mysql://Tarandophobia.is-a-geek.org/bookworm"
			              + "?user="+username + "&password=" + password);
			isConnected = true;
		} catch (SQLException e) 
		{
			isConnected = false;
			return false;
		}
		return true;
	}// end connectToDB

	@Override
	public void closeConnection() {
		try 
		{
			if(connect != null) connect.close();
			if(preparedStatement != null) preparedStatement.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}// closeConnection
}
