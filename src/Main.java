import java.sql.ResultSet;
import java.sql.SQLException;

import BusinessObjects.Librarian;
import BusinessObjects.Patron;

/**
 * Created by James Lawson on 4/8/2016.
 *
 * Gets the show started
 */
public class Main
{

  public static void main(String[] args)
  {

    Patron patron = new Patron("zfalgout", "Upe4OPvb");
    if(!patron.connectToDB())
    	{
    		System.err.println("Failed to connect to database");
    		System.exit(1);
    	}
    if(!patron.checkout(0, 001)) System.err.println("checkout failed");
    if(!patron.checkin(0, 001)) System.err.println("checkin failed");
    
    new GUIFrame(new GUIPanel(patron));

//    patron.closeConnection();
    
    Librarian librarian = new Librarian("zfalgout", "Upe4OPvb");
    if(!librarian.connectToDB()) System.err.println("Failed to connect to database");
    if(!librarian.addUser(2, "Falgout", "Zachariah", "librarian")) System.err.println("addUser failed");
    ResultSet resultSet;
    if((resultSet = librarian.showUsers()) == null) System.err.println("Show users failed");
    else
    {
    	try {
    		while(resultSet.next())
    		{
    			for(int i = 1; i < resultSet.getMetaData().getColumnCount(); i++)
					System.out.println(resultSet.getObject(i));
    		}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    librarian.closeConnection();
    

    
  }
}
