import BusinessObjects.User;

/**
 * Created by James Lawson on 4/8/2016.
 *
 * Gets the show started
 */
public class Main
{

  public static void main(String[] args)
  {
    new GUIFrame(new GUIPanel());
    User user = new User("zfalgout", "Upe4OPvb");
    if(!user.connectToDB())
    	{
    		System.err.println("Failed to connect to database");
    		System.exit(1);
    	}
    
    user.closeConnection();
    
  }
}
