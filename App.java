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

/*
private static final int LIGHT_LIGHT = 155;
private static final int LIGHT_DARK  = 205;
private static final int DARK_LIGHT  = 62;
private static final int DARK_DARK   = 165;
*/

/*
class settingsClass{
   
   private Color light = LIGHT_LIGHT;
   private Color dark = light_dark;

   
   //public Settings(){}
   public static void update_color(Color light, Color dark){
      light = light;
      dark = dark;
   }
   public static Color get_color_light(){return light;}
   public static Color get_color_dark(){return dark;}

	
}
*/
class App extends javax.swing.JFrame{
   public static final Color LIGHT_DARK = new Color(155,155,155);
   public static final Color LIGHT_LIGHT = new Color(205,205,205);
   public static final Color DARK_LIGHT = new Color(62,62,62);
   public static final Color DARK_DARK = new Color(165,165,165);



   JPanel main_panel,right_panel,left_panel;
   final static boolean RIGHT_TO_LEFT = false;
   final static boolean shouldFill = true;
   final static boolean shouldWeightX = true;   
   
   public static boolean entry_flag = false;


//============This is the Model section of GUi=========================
   public static void build_db(String path){


   }


   public static void build_panels(JPanel panel_grid, JPanel right_panel, JPanel left_panel){
      panel_grid.setBackground(LIGHT_DARK);
      panel_grid.setLayout(new GridBagLayout());
      
      right_panel.setBackground(LIGHT_LIGHT);

      //This holds DB fields
      //JPanel left_panel   = new JPanel();
      left_panel.setBackground(LIGHT_LIGHT);
      left_panel.setLayout(new GridBagLayout());

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
   }

   public static void build_toolbar(){
      
   
   }

   //This builds the search bar
   //Returns the JPanel element of the search bar that holds Button and Text Field
   public static JPanel build_searchbar(JButton search_button, JTextField search_tf){
      JPanel search_panel = new JPanel();
      search_panel.setBackground(LIGHT_LIGHT);
      search_panel.setLayout(new GridBagLayout());
      
      //Search Constraints
      GridBagConstraints search_constraints = new GridBagConstraints();
      search_constraints.insets = new Insets(7,5,7,5);
      search_constraints.gridx  = 0;
      search_constraints.gridy  = 0;
      search_constraints.ipadx  = 100;
      search_constraints.weightx = 0.95;
      search_constraints.fill = GridBagConstraints.HORIZONTAL;

      //Search Panel Elements==========================================

      //Search Text Field Action Listener
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
      //===============================================================
      //Search Button--------------------------------------------------

      //TODO: this is for debugging. Edit to look into DB by movie title
      search_button.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
             System.out.println("Searching for:" + search_tf.getText());
         }
      });
      search_constraints.gridx++;
      search_constraints.weightx = 0.05;
      search_panel.add(search_button,search_constraints);
      //--------------------------------------------------------------


      return search_panel;
   }

//===========This is the controller section of GUI=========================
//This section holds the elements for all the elements that changes the model
//This is responsible for user input and output

   public static void fullscreen(JFrame frame, MenuItem fullscreen){
      fullscreen.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
	    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	 }
      });
   } 

   public static void dark_mode(){

   }
   
   public static void light_mode(){

   }
   public static void create_new_entry(MenuItem new_entry, JComboBox genres,
		                       JSlider rating,JTextArea review,
				       JTextField director,JTextField title,
				       JPanel right_panel
				       ){
	   
      new_entry.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            if(entry_flag){
               System.out.println("Refreshing Panels");
               
            }else{
               entry_flag = true;
               //IF we have not yet created the elements
	       //We will add them to the right_panel
	       
            }
         }
      });

   }
//=============This is the view sections of the GUI===================
   public static void create_frame(){
      //This is the main frame window
      JFrame main_frame = new JFrame("Movie Reviewist");
      main_frame.setUndecorated(false);

      //settingsClass Settings;

      //Create Database Here
      //
      //
      //------------------------------
 
      //Set the Layout--------------------------------------
      Container pane = main_frame.getContentPane();

      //Setting up the Panels--------------------------------
 
      //Holds the search bars
      JTextField search_tf = new JTextField("");
      JButton search_button = new JButton("Search");
      JPanel search_panel = build_searchbar(search_button,search_tf);

      //Holds the other panels
      JPanel grid_panel   = new JPanel();
      
      //This holds the fields for data entry
      JPanel right_panel  = new JPanel();

      //This holds DB fields
      JPanel left_panel   = new JPanel();
   
      build_panels(grid_panel,right_panel,left_panel);
      //-----------------------------------------------------
      MenuBar toolbar = new MenuBar();
      Menu menu_file = new Menu("File");
      Menu menu_options = new Menu("Options");
      Menu menu_export = new Menu("Export");
      
      MenuItem fullscreen = new MenuItem("Fullscreen");
      fullscreen(main_frame,fullscreen);
      
      MenuItem light_mode = new MenuItem("Light Mode");
      light_mode.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
	    main_frame.setBackground(LIGHT_DARK);
	    right_panel.setBackground(LIGHT_DARK);
	    left_panel.setBackground(LIGHT_DARK);
	    search_panel.setBackground(LIGHT_DARK);
	    grid_panel.setBackground(new Color(200,200,200));
	 }
      });
      
      MenuItem dark_mode = new MenuItem("Dark Mode");
      dark_mode.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
	    main_frame.setBackground(DARK_DARK);
            right_panel.setBackground(DARK_DARK);
            left_panel.setBackground(DARK_DARK);
            search_panel.setBackground(DARK_DARK);
            grid_panel.setBackground(new Color(32,32,32));
	 }
      });
      
      MenuItem new_entry = new MenuItem("New");
      
      JTextField title = new JTextField("Title");
      String genreList[] = {"Action","Adventure","Comedy","Drama",
	                    "Fantasy","Horror","Mystery","Pyschological",
		            "Romance","Scifi","Thriller","Wester"}
      JList genresL = new JList(genreList);
      JComboBoc genres = new JComboBox(genreL);
      JSlider rating = new JSlider(JSlider.HORIZONTAL,0,20,0);
      JTextArea review = new JTextArea("Write Your Review Here");
      JTextField director = new JTextField("Enter the Director's Name Here");
      
      create_new_entry(new_entry,genres,rating,review,director,title,right_panel);
      /*
      new_entry.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e){
            if(entry_set){
               System.out.println("Refreshing Panels");
	       refresh_entry();
	    }else{
               entry_set = true;
	       
	    }
	 }
      });
      */
      menu_options.add(fullscreen);
      menu_options.add(light_mode);
      menu_options.add(dark_mode);

      toolbar.add(menu_file);
      toolbar.add(menu_options);
      toolbar.add(menu_export);

      main_frame.setMenuBar(toolbar);
      //--------------------------------------------------------------

      pane.add(search_panel,BorderLayout.NORTH);
      pane.add(grid_panel,BorderLayout.CENTER);

      Dimension min_size = new Dimension(400,400); //Sets minimum window size
      main_frame.setResizable(true);
      main_frame.setMinimumSize(min_size);

      //When Window is close the program exits
      main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      main_frame.setVisible(true); 

   }	   

   public static void main(String[] args){
      
      //public static final int LIGHT_LIGHT = 155;
      //public static final int LIGHT_DARK  = 205;
      //public static final int DARK_LIGHT  = 62;
      //public static final int DARK_DARK   = 165;

      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run(){
            create_frame();
	 }
      });
   }
}
