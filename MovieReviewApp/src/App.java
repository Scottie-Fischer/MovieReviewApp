import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.io.File;

public class App extends javax.swing.JFrame{
	
	JPanel Main_Panel,right_panel,left_panel;
	
	final static boolean RIGHT_TO_LEFT = false;
	final static boolean shouldFill = true; 
    final static boolean shouldWeightX = true;
	
	
	public static void assemble_Frame(Container pane) {
		JButton bNew,bOptions,bExport,bSearch;
		JTextField tf_search;
		
		//Setting out layout--------------------------------------------------------
		if(RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}
		
		pane.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		if(shouldFill) {
			constraint.fill = GridBagConstraints.HORIZONTAL;
		}
		if(shouldWeightX) {
			constraint.weightx = 0.5;
		}
		//--------------------------------------------------------------------------
		//Buttons
		//Adding a text box for search function
		tf_search = new JTextField("Enter a Movie Title");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.ipady = 20;
		constraint.weightx = 0.0;
		constraint.gridwidth = 2;
		constraint.gridx = 0;
		constraint.gridy = 1;										//request any extra vertical space
		pane.add(tf_search,constraint);
		
		//Adding a search button
		bSearch = new JButton("Search");
		constraint.gridx = 2;
		constraint.gridy = 1;
		pane.add(bSearch,constraint);
	}
	
	//We add buttons to our tool bar here-------------------------
	public static void buildToolBar(JFrame frame,JPanel right_panel, JPanel left_panel,JPanel search_panel, JPanel grid_panel){
		MenuBar toolBar = new MenuBar();
		
		//Main Menu Options
		Menu file = new Menu("File");
		
		Menu options = new Menu("Options");
				
		Menu export = new Menu("Export");
	
		//Sub Menus and Menu Buttons
		MenuItem fullscreen = new MenuItem("Fullscreen");
		fullscreen.addActionListener ( new ActionListener(){
			public void actionPerformed(ActionEvent e ){
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
		});
		
		MenuItem lightMode = new MenuItem("Light Mode");
		lightMode.addActionListener ( new ActionListener()  
	    {  
	      public void actionPerformed( ActionEvent e )  
	      {  
	        frame.setBackground(new Color(155,155,155));
	        right_panel.setBackground(new Color(155,155,155));
	        left_panel.setBackground(new Color(155,155,155));
	        search_panel.setBackground(new Color(155,155,155));
	        grid_panel.setBackground(new Color(200,200,200));
	        System.out.println("Currently is: " + right_panel.getBackground());
	        System.out.println("We light now");
	      }  
	    });
		MenuItem darkMode = new MenuItem("Dark Mode");
		darkMode.addActionListener ( new ActionListener()  
	    {  
	      public void actionPerformed( ActionEvent e )  
	      {  
	        frame.setBackground(new Color(62,62,62));  
	        right_panel.setBackground(new Color(62,62,62));
	        left_panel.setBackground(new Color(62,62,62));
	        search_panel.setBackground(new Color(62,62,62));
	        grid_panel.setBackground(new Color(32,32,32));
	        System.out.println("We dark now");
	      }  
	    });
		
		MenuItem bNew = new MenuItem("New");
		bNew.addActionListener ( new ActionListener()  
	    {  
	      public void actionPerformed( ActionEvent e )  
	      {  
	        frame.setBackground(Color.white); 
	        System.out.println("Make New Module");
	      }  
	    });
		
		//Adding our buttons and sub menus
		file.add(bNew);
		
		options.add(fullscreen);
		options.add(lightMode);
		options.add(darkMode);
		
		//Adding the menus to the tool bar
		toolBar.add(file);
		toolBar.add(options);
		toolBar.add(export);
		
		//Adding the menu bar to the frame
		frame.setMenuBar(toolBar);
	
	}
	
	public static void buildPanels(JPanel encompassing_panel) {
		
	}
	// Creating our Frame/Window-----------------------------------
	private static void createFrame() {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		
		//This is the main frame of the window
		JFrame main_frame = new JFrame("Movie Reviewst");
		main_frame.setUndecorated(false);
		
		
		//Lets set up the Layout - right now we are choosing BorderLayout
		Container pane = main_frame.getContentPane();
		
		
		//Lets set up the panels-----------------------------------
		JPanel search_panel = new JPanel();
		search_panel.setBackground(Color.blue);
		search_panel.setLayout(new GridBagLayout());
		
		JPanel panel_grid = new JPanel();
		panel_grid.setBackground(Color.white);
		panel_grid.setLayout(new GridBagLayout());
		
		JPanel right_panel = new JPanel();
		right_panel.setBackground(Color.black);
		
		JPanel left_panel = new JPanel();
		left_panel.setBackground(Color.green);
		
		//Setting up constraints for our two panels-----------------
		GridBagConstraints panel_constraints = new GridBagConstraints();
		panel_constraints.insets = new Insets(5,2,2,2);
		
		panel_constraints.gridx = 0;
		panel_constraints.gridy = 0;
		panel_constraints.weightx = 0.25;
		panel_constraints.weighty = 0.25;
		panel_constraints.fill = GridBagConstraints.BOTH;
		
		panel_grid.add(left_panel,panel_constraints);
		
		panel_constraints.gridx++;
		panel_constraints.weightx = 1;
		panel_grid.add(right_panel,panel_constraints);
		
		//---------------------------------------------------------
		
		GridBagConstraints search_constraints = new GridBagConstraints();
		search_constraints.insets = new Insets(7,5,7,5);	//(top, side, bottom, side)
		search_constraints.gridx = 0;
		search_constraints.gridy = 0;
		search_constraints.ipadx = 100;
		search_constraints.weightx = 0.95;
		search_constraints.fill = GridBagConstraints.HORIZONTAL;
		
		//Search Panel Elements------------------------------------
		JTextField tf_search = new JTextField("Enter a Movie Title");
		search_panel.add(tf_search,search_constraints);
		
		JButton b_search = new JButton("Search");
		b_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Searching for:" + tf_search.getText());
			}
		});

		search_constraints.gridx++;
		search_constraints.weightx = 0.05;
		search_panel.add(b_search,search_constraints);
		
		
		//Building our Tool Bar------------------------------------
		buildToolBar(main_frame,right_panel,left_panel,search_panel,panel_grid);
		
		
		//Adding all the components to the Main Frame--------------
		pane.add(search_panel,BorderLayout.NORTH);
		pane.add(panel_grid,BorderLayout.CENTER);

		Dimension min_size = new Dimension(400,400);
		main_frame.setResizable(true);
		main_frame.setMinimumSize(min_size);
		
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//when window is closed the program closed
		main_frame.pack();											//adds all the components and sets things before it makes the window visible
		//assemble_Frame(Main_Frame.getContentPane());
		
		main_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);			//Sets the Window to Full Screen when opened
		main_frame.setVisible(true);
		
		
		String pathway = selectFilePath(main_frame);
		databaseSetup(pathway);
	}
	
	
	public void actionPerformed(ActionEvent s) {
		int e = s.ACTION_FIRST;
		System.out.println(e);
	}
	
	
	public static String selectFilePath(JFrame main_frame) {
		String path = "C:/sqlite/db/reviewst.db";
		
		JFileChooser file_selector = new JFileChooser();
		file_selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		file_selector.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = file_selector.showOpenDialog(main_frame);
		if(result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = file_selector.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		}
		
		return path;
	}
	
	public static void databaseSetup(String path) {
		//Connect to DataBase------------------------------------
		
				//Connection conn = null;
				
				String url = "jdbc:sqlite:" + path;
				
				try(Connection conn = DriverManager.getConnection(url)) {
					if (conn != null) {
		                DatabaseMetaData meta = conn.getMetaData();
		                System.out.println("The driver name is " + meta.getDriverName());
		                System.out.println("A new database has been created.");
		            }
				}catch(SQLException e){
					System.out.println(e.getMessage());
					System.exit(1);
				}
				
				//-------------------------------------------------------
	}
	
	public static void main(String[] args){
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() { 
			   
            public void run() { 
                createFrame(); 
            } 
        });
	}
	
}