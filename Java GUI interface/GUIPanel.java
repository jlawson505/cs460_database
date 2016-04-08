import javax.swing.*;
import java.awt.*;

/**
 * Created by James Lawson on 4/8/2016.
 *
 * Simple panel, also where all components are stored
 * and action listeners are set up to send events to the
 * class that deals with DB interface
 */
public class GUIPanel extends JPanel
{

  private final int WIDTH = 500;
  private final int HEIGHT = 500;
  private final Dimension SIZE;
  private final int GRID_ROWS = 2;
  private final int GRID_COLS = 5;
  private final int GAP = 10;
  private final GridLayout layout;



  public GUIPanel()
  {
    SIZE = new Dimension(WIDTH,HEIGHT);
    this.setPreferredSize(SIZE);
    this.setBackground(Color.GRAY);

    layout = new GridLayout(GRID_ROWS,GRID_COLS,GAP,GAP);
    this.setLayout(layout);

    addComponents();
  }

  //initialize and add components
  private void addComponents()
  {
  }
}
