import BusinessObjects.DBOperations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

  private final DBOperations DB_OPERATIONS;



  public GUIPanel(DBOperations operations)
  {
    DB_OPERATIONS = operations;

    SIZE = new Dimension(WIDTH,HEIGHT);
    this.setPreferredSize(SIZE);
    this.setBackground(Color.GRAY);
    this.setLayout(null);

    addComponents();
  }

  //initialize and add components
  private void addComponents()
  {
    JTextField UID = new JTextField("UID",20);
    UID.setLocation(50, 20);
    UID.setSize(100,30);

    JTextField ISBN = new JTextField("ISBN",20);
    ISBN.setLocation(200,20);
    ISBN.setSize(100, 30);

    JButton checkOut = new JButton("check out");
    checkOut.setBounds(50, 70, 100, 50);

    checkOut.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                   int isbnNumber = Integer.parseInt(ISBN.getText());
                                   int uidNumber = Integer.parseInt(UID.getText());

                                   DB_OPERATIONS.checkout(uidNumber, isbnNumber);
                                 }
                               }
    );

    JButton checkIn = new JButton("check in");
    checkOut.setBounds(200,70,100,50);

    checkOut.addActionListener(new ActionListener() {
                                 @Override
                                 public void actionPerformed(ActionEvent e) {
                                   int isbnNumber = Integer.parseInt(ISBN.getText());
                                   int uidNumber = Integer.parseInt(UID.getText());

                                   DB_OPERATIONS.checkin(uidNumber,isbnNumber);
                                 }
                               }
    );

    this.add(UID);
    this.add(ISBN);
    this.add(checkOut);
    this.add(checkIn);
  }
}
