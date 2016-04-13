package BusinessObjects;
import java.sql.ResultSet;

/**
 * 
 * @author zfalgout
 * 
 *	Has default implementations for non abstract methods, but these methods just
 *	return the default types. Designed so objects that shouldn't be allowed to access
 *	those methods will not be able to do anything with them, however, objects
 *	that can use those methods should still check the access level before access to
 *	database
 */
public abstract class DBOperations 
{	
	/**
	 * Handles connecting to the database
	 * @return should return true if connection succeeds, otherwise false
	 */
	public abstract boolean connectToDB();
	
	/**
	 * Handles checking out a book for the given uid
	 * @param uid user id of the person checking the book out
	 * @param ISBN of the book being checked out
	 * @return true if checkout succeeds, false otherwise
	 */
	public boolean checkout(int uid, int ISBN)
	{
		return false;
	}
	
	/**
	 * Handles checking in a book for the given uid
	 * Should check that the given ISBN is in the checkedOut table
	 * @param uid user id of the person checking in the book
	 * @param ISBN of the book being checked in
	 * @return true if checkin succeeds, false otherwise
	 */
	public boolean checkin(int uid, int ISBN)
	{
		return false;
	}
	
	/**
	 * Adds a user to the Users tables with the given information: uid, lastName, firstName, role
	 * Needs to check that the accessID has the authorization to add users to the Users table
	 * @param uid user id of the user being added
	 * @param lastName last name of the user
	 * @param firstName first name of the user
	 * @param role access level of the user
	 * @return true if succeeds, false otherwise
	 */
	public boolean addUser(int uid, String lastName, String firstName, String role) 
	{
		return false;
	}
	
	/**
	 * Looks up a user based off of uid
	 * @param uid user id of person being looked up
	 * @return ResultSet containing information of look up, or NULL if failed
	 */
	public ResultSet lookupUser(int uid) 
	{
		return null;
	}
	
	/**
	 * Looks up user(s) based off the user's name
	 * If look up is with lastName only, set firstName to empty string
	 * If look up is with firstName only, set lastName to empty string
	 * Otherwise searching using both lastName and firstName
	 * @param lastName of person being looked up
	 * @param firstName of person being looked up
	 * @return ResultSet containing information of look up, or NULL if failed
	 */
	public ResultSet lookupUser(String lastName, String firstName) 
	{
		return null;
	}
	
	/**
	 * Look up user(s) based off their role
	 * @param role level of access into the database
	 * @return ResultSet containing information of look up, or NULL if failed
	 */
	public ResultSet lookupUser(String role) 
	{
		return null;
	}
	
	/**
	 * Handles closing all connections to the database
	 */
	public abstract void closeConnection();

}
