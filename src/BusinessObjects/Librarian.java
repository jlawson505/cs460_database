package BusinessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


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
	
	
	public boolean showUsers(){
		if(!isConnected) return false;
		
		try 
		{
			preparedStatement = connect.prepareStatement("SELECT * FROM users");
			preparedStatement.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}// end showUsers

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
			connect.close();
			preparedStatement.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}// closeConnection
}
