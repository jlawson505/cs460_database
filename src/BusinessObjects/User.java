package BusinessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author zfalgout
 *
 * Handles User operations when dealing with the database
 * Like connecting to the database, reading from database,
 * and making changes to the database.
 * When making changes to the database, a uid(user id) needs to be checked
 * to make sure that user is allowed to make changes to the database
 */

public class User extends DBOperations
{
	private Connection connect = null;
	//check if user is connected before read and writes to database
	private boolean isConnected = false;
	private String username, password;
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	/**
	 * Handles trying to connect to the database
	 * Will use the given user name and password for connections
	 * NOTE: for visitors a default user name and password would be needed
	 * to connect to database to allow reading from the database.
	 * @return true if connection is successful, otherwise false
	 */
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


	@Override
	public boolean checkout(int uid, int ISBN) {
		if(!isConnected) return false;
		return false;
	}

	@Override
	public boolean checkin(int uid, int ISBN) {
		if(!isConnected) return false;
		return false;
	}

	/**
	 * Handles closing the connections to the database, and all related
	 * database objects
	 * If closing fails, the stack trace is printed
	 */
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
