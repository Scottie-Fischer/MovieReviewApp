import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;

import java.io.File;
import java.util.Hashtable;
import java.util.Properties;

//Test
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;

class App extends javax.swing.JFrame{
  

   JPanel main_panel,right_panel,left_panel;
   final static boolean RIGHT_TO_LEFT = false;
   final static boolean shouldFill = true;
   final static boolean shouldWeightX = true;   

   //This is the main function of the GUI
   public static void create_frame(){
      //This is the main frame window
      JFrame main_frame = new JFrame("Movie Reviewist");
      main_frame.setUndecorated(false);

      //Create Database Here
      //
      //
      //------------------------------
 
      //Set the Layout
      Container pane = main_frame.getContentPane();

      //Setting up the Panels--------------------------------
 
      JPanel search_panel = new JPanel();
      search_panel.setBackground(Color.blue);
      search_panel.setLayout(new GridBagLayout());

      JPanel panel_grid   = new JPanel();
      panel_grid.setBackground(Color.white);
      panel_grid.setLayout(new GridBagLayout());

      //This will fields how data entry
      JPanel right_panel  = new JPanel();
      right_panel.setBackground(Color.black);

      JPanel left_panel   = new JPanel();
      left_panel.setBackground(Color.green);
      left_panel.setLayout(new GridBagLayout());
      //-----------------------------------------------------

      //Setting the contraints for Panels--------------------
      GridBagConstraints panel_constraints = new GridBagConstraints();
      panel_constraints.insets = new Insets(5,2,2,2);

      panel_constraints.gridx   = 0;
      panel_constraints.gridy   = 0;
      panel_constraints.weightx = 0.25;
      panel_constraints.weighty = 0.25;
      panel_constraints.fill    = GridBagConstraints.BOTH;

      panel_grid.add(left_panel,panel_constraints);
      panel_constraints.gridx++;
      panel_constraints.weightx = 1;

      panel_grid.add(right_panel,panel_constraints);
      //---------------------------------------------------------------

      //Search Constraints
      GridBagConstraints search_constraints = new GridBagConstraints();
      search_constraints.insets = new Insets(7,5,7,5);
      search_constraints.gridx  = 0;
      search_constraints.gridy  = 0;
      search_constraints.ipadx  = 100;
      search_constraints.weightx = 0.95;
      search_constraints.fill = GridBagConstraints.HORIZONTAL;

      //Search Panel Elements==========================================
      
      //Search Text Field
      JTextField search_tf = new JTextField("");
      search_tf.addFocusListener(new FocusListener(){
         @Override
	 //Listens for when you select the text field. 
	 //Empties text when selected
         public void focusGained(FocusEvent e){
            if(search_tf.getText().trim().equals("Enter a Movie Title to Search")){
	       search_tf.setText("");	    
            }
	 }
	 @Override
	 //If the Text Box is Empty it refills it with default text
	 public void focusLost(FocusEvent e){
            if(search_tf.getText().trim().equals("")){
               search_tf.setText("Enter a Movie Title to Search");
	    }
	 }
      });
      search_panel.add(search_tf,search_constraints);
      
      //Search Button
      JButton search_button = new JButton("Search");

      //TODO: this is for debugging. Edit to look into DB by movie title
      search_button.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             System.out.println("Searching for:" + search_tf.getText());
	 }
      });
      search_constraints.gridx++;
      search_constraints.weightx = 0.05;
      search_panel.add(search_button,search_constraints);

      pane.add(search_panel,BorderLayout.NORTH);
      pane.add(panel_grid,BorderLayout.CENTER);

      Dimension min_size = new Dimension(400,400); //Sets minimum window size
      main_frame.setResizable(true);
      main_frame.setMinimumSize(min_size);

      //When Window is close the program exits
      main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      main_frame.setVisible(true); 

   }	   

   public static void main(String[] args){
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run(){
            create_frame();
	 }
      });
   }
}
