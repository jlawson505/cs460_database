package BusinessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

public class Patron extends DBOperations
{
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	
	//check if user is connected before read and writes to database
	private boolean isConnected = false;
	private String username, password;
	
	/**
	 * Sets up the user object
	 * @param username the login name for the database
	 * @param password the password for the database
	 */
	public Patron(String username, String password)
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
			isConnected = true;
		} catch (SQLException e) 
		{
			isConnected = false;
			return false;
		}
		return true;
	}


	@Override
	public boolean checkout(int uid,int ISBN) {
		if(!isConnected) return false;
		try 
		{
			preparedStatement = connect.prepareStatement("insert into CheckedOut values(" + uid + ", " + ISBN + ", '2016-05-10', 1)");
			preparedStatement.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean checkin(int uid, int ISBN)
	{
		if(!isConnected) return false;
		
		try 
		{
			preparedStatement = connect.prepareStatement("delete from CheckedOut where uid=" + uid + " and ISBN=" + ISBN);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
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
			if(connect != null) connect.close();
			if(preparedStatement != null) preparedStatement.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
