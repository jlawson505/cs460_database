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

    new GUIFrame(new GUIPanel(patron));

    
  }
}
