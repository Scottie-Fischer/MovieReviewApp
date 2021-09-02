import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
//

public class App extends javax.swing.JFrame{
	
	JPanel Main_Panel,right_panel,left_panel;
	
	final static boolean RIGHT_TO_LEFT = false;
	final static boolean shouldFill = true; 
    final static boolean shouldWeightX = true;
	
	/*
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
		tf_search = new JTextField("");
		//tf_search.setToolTipText("Enter a Movie Title");
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
	*/
	//We add buttons to our tool bar here-------------------------
	public static void buildToolBar(JFrame frame,JPanel right_panel, JPanel left_panel,JPanel search_panel, JPanel grid_panel, JButton b_search,String url){
		MenuBar toolBar = new MenuBar();
		
		//Main Menu Options
		Menu file = new Menu("File");
		
		Menu options = new Menu("Options");
				
		Menu export = new Menu("Export");
		
		//--------------------------------
		JTextField title = new JTextField("Title");
		
		String genreList[] = {"Action", "Adventure","Comedy","Drama","Fantasy","Horror","Mystery","Pyschological","Romance","Scifi","Thriller","Western"};
		JList genres = new JList(genreList);
		JComboBox comboBox = new JComboBox(genreList);
		
		JSlider rating = new JSlider(JSlider.HORIZONTAL,0,20,0);
		
		JTextArea review = new JTextArea("Write Your Review Here");
		
		JTextField director = new JTextField("Enter this Film's Director Here");
		
		JTextField holder = new JTextField("temp");
		//--------------------------------
		
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
	        b_search.setBackground(new Color(184,201,230));
	        System.out.println("Currently is: " + right_panel.getBackground());
	        System.out.println("We light now");
	        
			//Light Mode
			director.setBackground(new Color(205,205,205));
			review.setBackground(new Color(205,205,205));
			comboBox.setBackground(new Color(205,205,205));
			rating.setBackground(new Color(205,205,205));
			holder.setBackground(new Color(205,205,205));
			title.setBackground(new Color(205,205,205));
	        
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
	        b_search.setBackground(new Color(238,238,238));
	        b_search.setForeground(new Color(51,51,51));
	        
	        
	      //Dark Mode
			director.setBackground(new Color(165,165,165));
			review.setBackground(new Color(165,165,165));
			comboBox.setBackground(new Color(165,165,165));
			rating.setBackground(new Color(165,165,165));
			holder.setBackground(new Color(165,165,165));
			title.setBackground(new Color(165,165,165));
	        //Setting Components
	        //director.setBackground(new Color(175,175,175));
	        
	        System.out.println("We dark now");
	        
	      }  
	    });
		
		
		MenuItem bNew = new MenuItem("New");
		bNew.addActionListener ( new ActionListener()  
	    {  
	      public void actionPerformed( ActionEvent e )  
	      {  
	    	//TODO: create a new panel(?) and add it to left_panel
			//TODO: add an entry into the DB for a new entry
	    	
			createNewEntry(right_panel,title,rating,review,director,comboBox,holder);
	    	
	        System.out.println("Make New Module");
	      }  
	    });
		MenuItem bSave = new MenuItem("Save");
		bSave.addActionListener ( new ActionListener()  
	    {  
	      public void actionPerformed( ActionEvent e )  
	      {  
	    	//TODO:create functionality to insert or update the review to the DB
	    	int ID = 0;
	    	String title_data = title.getText();
	    	Double rating_data = (rating.getValue()/2.0);
	    	String dir_data = director.getText();
	    	
	    	String sql_string = "INSERT INTO REVIEWS (ID,title,rating,director) " +
	    						"VALUES (?,?,?,?)";
	    	
	    	try{
	    		System.out.println("Attempting to establish connection: " + url);
	    		
	    		String URL = "jdbc:sqlite:" + url + "/MovieReviewer.db"; 
	    		Connection con = DriverManager.getConnection(URL);
	    		con.setAutoCommit(false);
	    		
	    		System.out.println("Connection Established.");
	    		
	    		PreparedStatement sql_exe = con.prepareStatement(sql_string);
	    		sql_exe.setInt(1,ID);
	    		sql_exe.setString(2,title_data);
	    		sql_exe.setDouble(3,rating_data);
	    		sql_exe.setString(4,dir_data);
	    		sql_exe.executeUpdate();
	    		con.commit();
	    	}catch(Exception ex){
	    		System.out.println("Error saving value to DB: " + ex.getMessage());
	    		System.exit(1);
	    	}
	        
	      }  
	    });
		
		//Adding our buttons and sub menus
		file.add(bNew);
		file.add(bSave);
		
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
	
	//Function that create the components for a new review
	public static void createNewEntry(JPanel parent_panel, JTextField title, JSlider rating, JTextArea review, JTextField director, JComboBox comboBox,JTextField holder) {
		//
		//JTextfield title
		//JSlider rating
		//JTextArea review
		//JTextfield 
		//
		parent_panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(2,2,2,2);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.75;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		
		title.setPreferredSize(new Dimension(150,35));
		title.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (title.getText().trim().equals("Title")){
					title.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(title.getText().trim().equals("")) {
					title.setText("Title");
				}
			}
		});
		title.setSize(200,50);
		parent_panel.add(title,gbc);
		
		gbc.weightx = 0.25;
		gbc.gridx = 1;
		
		//Custom Labels for the Rating Slider
		Hashtable labelTable = new Hashtable();
		labelTable.put(0,new JLabel("0"));
		labelTable.put(5,new JLabel("2.5"));	
		labelTable.put(10,new JLabel("5"));
		labelTable.put(15,new JLabel("7.5"));	
		labelTable.put(20,new JLabel("10"));
		
		
		
		rating.setPreferredSize(new Dimension(50,40));
		rating.setLabelTable(labelTable);
		rating.setMajorTickSpacing(2);
		rating.setMinorTickSpacing(1);
		rating.setPaintTicks(true);
		rating.setPaintLabels(true);
		parent_panel.add(rating,gbc);
		
		
		//review.setHorizontalAlignment(JTextField.LEFT);
		//review.setAlignmentY(0);
		review.setLineWrap(true);
		review.setPreferredSize(new Dimension(200,500));
		review.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (review.getText().trim().equals("Write Your Review Here")){
					review.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(review.getText().trim().equals("")) {
					review.setText("Write Your Review Here");
				}
			}
		});
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 5;
		gbc.gridy = 1;
		parent_panel.add(review,gbc);
		
		//Create the Director Text Field
		
		director.setPreferredSize(new Dimension(50,35));
		director.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (director.getText().trim().equals("Enter this Film's Director Here")) {
					director.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if(director.getText().trim().equals("")) {
					director.setText("Enter this Film's Director Here");
				}
			
			}
		});
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridy = 6;
		parent_panel.add(director,gbc);
		
		//Create the Genre List----------------------------------------------------------
		comboBox.setPreferredSize(new Dimension(50,35));
		comboBox.setLightWeightPopupEnabled(true);
		gbc.gridx = 1;
		parent_panel.add(comboBox,gbc);
		
		System.out.println("Default color:" + director.getBackground().toString());
		
		//Testing------------------------------------------------------------------------
		
		holder.setPreferredSize(new Dimension(200,50));
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		
		parent_panel.add(holder,gbc);
		//--------------------------------------------------------------------------------
		parent_panel.validate();
	}
	
	public static void buildPanels(JPanel encompassing_panel) {
		
	}
	// Creating our Frame/Window-----------------------------------
	private static void createFrame() {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		
		//This is the main frame of the window
		JFrame main_frame = new JFrame("Movie Reviewer");
		main_frame.setUndecorated(false);
		
		//Then we want to do the database
		String pathway = selectFilePath(main_frame);
		databaseSetup(pathway);
		
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
		JTextField tf_search = new JTextField("");
		//tf_search.setToolTipText("Enter A Movie Title to Search For");
		tf_search.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e){
				if (tf_search.getText().trim().equals("Enter a Movie Title to Search")){
					tf_search.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(tf_search.getText().trim().equals("")){
					tf_search.setText("Enter a Movie Title to Search");
				}
			}
		});
		search_panel.add(tf_search,search_constraints);
		
		JButton b_search = new JButton("Search");
		
		System.out.println(b_search.getForeground().toString());
		System.out.println(b_search.getBackground().toString());
		b_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Searching for:" + tf_search.getText());
			}
		});

		search_constraints.gridx++;
		search_constraints.weightx = 0.05;
		search_panel.add(b_search,search_constraints);
		
		
		//Building our Tool Bar------------------------------------
		buildToolBar(main_frame,right_panel,left_panel,search_panel,panel_grid,b_search,pathway);
		
		
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
		
	}
	
	
	public void actionPerformed(ActionEvent s) {
		int e = s.ACTION_FIRST;
		System.out.println(e);
	}
	
	
	//This is the function used to select file path for the DB
	//Function takes in the main_frame as argument
	//Function returns string: "path"
	public static String selectFilePath(JFrame main_frame) {
		String path = "C:/sqlite/db";			//Default path
		
		JFileChooser file_selector = new JFileChooser();
		file_selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		file_selector.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = file_selector.showOpenDialog(main_frame);
		if(result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = file_selector.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			path = selectedFile.getAbsolutePath();
		}
		
		return path;
	}
	
	public static void databaseSetup(String path) {
		//Connect to DataBase------------------------------------
		System.out.println("File path selected: " + path);
		String url = "jdbc:sqlite:" + path + "/MovieReviewer.db";
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(url);
			if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A connection has been made and a new database has been created.");
            }
		}catch(SQLException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		//-------------------------------------------------------
		//Creating Table if it doesn't exist---------------------
		String sql = "CREATE TABLE IF NOT EXISTS REVIEWS"
				+ "(id integer PRIMARY KEY,"
				+ "	title text NOT NULL,"
				+ " rating double NOT NULL,"
				+ " director text NOT NULL"
				+ ");";
		try{
			//conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}catch(Exception e) {
					System.out.println(e.getMessage());
					System.exit(1);
		}
		
		String sql_string = "INSERT INTO REVIEWS (id,title,rating,director) VALUES(?,?,?,?)";
		
		try{
			conn.setAutoCommit(false);
			PreparedStatement sql_exe = conn.prepareStatement(sql_string);
			sql_exe.setInt(1,1);
			sql_exe.setString(2,"Machester");
			sql_exe.setDouble(3,7.0);
			sql_exe.setString(4,"Casey");
			sql_exe.executeUpdate();
			conn.commit();
			conn.close();
			System.out.println();
		}catch(Exception ee){
			System.out.println("Error adding first:" + ee.getMessage());
			ee.printStackTrace();
		}
		/*
		finally {
			try {
				if(conn!=null) {
					conn.close();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}*/
	}
	
	public static void main(String[] args){
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() { 
			   
            public void run() { 
                createFrame(); 
            } 
        });
	}
}

class JSearchButton extends JButton{
	private Color color1, color2;
	
	public JSearchButton(String text, Color color1, Color color2) {
		super(text);
		this.color1 = color1;
		this.color2 = color2;
		setOpaque(false);
	}
	 public void paintComponent(Graphics g)
	    {
	        Graphics2D g2 = (Graphics2D)g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                            RenderingHints.VALUE_ANTIALIAS_ON);
	        int width = getWidth();
	        int height = getHeight();
	        Paint gradient = new GradientPaint(0, 0, color1, width, height, color2);
	        g2.setPaint(gradient);
	        g2.fillRect(0, 0, width, height);
	        super.paintComponent(g);
	    }
}