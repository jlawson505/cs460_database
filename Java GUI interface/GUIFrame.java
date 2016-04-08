import javax.swing.*;

/**
 * Created by James Lawson on 4/8/2016.
 *
 * simple JFrame class
 */
public class GUIFrame extends JFrame
{
  public GUIFrame(GUIPanel window)
  {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setVisible(true);

    this.add(window);
    this.pack();
  }

}
