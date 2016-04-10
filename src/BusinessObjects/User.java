package BusinessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class User 
{
	private Connection connect = null;
	private boolean isConnected = false;
	private String username, password;
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public boolean connectToDB()
	{
		try 
		{
			connect = DriverManager
			          .getConnection("jdbc:mysql://Tarandophobia.is-a-geek.org/bookworm"
			              + "?user="+username + "&password=" + password);
		} catch (SQLException e) 
		{
			return false;
		}
		return true;
	}
	
	public void closeConnection()
	{
		try 
		{
			connect.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
