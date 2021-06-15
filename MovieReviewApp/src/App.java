import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.DatabaseMetaData;

public class App extends javax.swing.JFrame{
	
	JPanel Main_Panel,p1,p2,p3;
	
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
		
		bNew = new JButton("New");
		constraint.gridx = 0;
		constraint.gridy = 0;
		pane.add(bNew,constraint);
		
		
		//Adding a button for option menu
		bOptions = new JButton("Options");
		constraint.gridx = 1;
		constraint.gridy = 0;
		pane.add(bOptions,constraint);
		
		//Adding Export option for exporting to a txt? file
		bExport = new JButton("Export");
		constraint.gridx = 2;
		constraint.gridy = 0;
		pane.add(bExport,constraint);
		
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
	public static void buildToolBar(JFrame frame){
		MenuBar toolBar = new MenuBar();
		
		//Main Menu Options
		Menu file = new Menu("File");
		
		Menu options = new Menu("Options");
				
		Menu export = new Menu("Export");
	
		//Sub Menus and Menu Buttons
		
		MenuItem lightMode = new MenuItem("Light Mode");
		lightMode.addActionListener ( new ActionListener()  
	    {  
	      public void actionPerformed( ActionEvent e )  
	      {  
	        frame.setBackground(Color.white); 
	        System.out.println("We light now");
	      }  
	    });
		MenuItem darkMode = new MenuItem("Dark Mode");
		darkMode.addActionListener ( new ActionListener()  
	    {  
	      public void actionPerformed( ActionEvent e )  
	      {  
	        frame.setBackground(Color.black.brighter());  
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
		
		file.add(bNew);
		
		options.add(lightMode);
		options.add(darkMode);
		
		toolBar.add(file);
		toolBar.add(options);
		toolBar.add(export);
		
		frame.setMenuBar(toolBar);
	
	}
	
	
	// Creating our Frame/Window-----------------------------------
	private static void createFrame() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		JFrame Main_Frame = new JFrame("Move Reviewist");
		
		buildToolBar(Main_Frame);
		
		Main_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//when window is closed the program closed
		Main_Frame.setUndecorated(true);
		Main_Frame.pack();											//adds all the components and sets things before it makes the window visible
		assemble_Frame(Main_Frame.getContentPane());
		
		Main_Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);			//Sets the Window to Full Screen when opened
		Main_Frame.setVisible(true);
	}
	
	/*
	public void actionPerformed(ActionEvent s) {
		String search_holder = tf_search.getText();
		System.out.print(search_holder);
	}
	*/
	public static void main(String[] args){
		Connection conn = null;
		String fileName = "movieDB";				//This is the file name to our DB
		
		//We try to connect to our DB which is set to 'fileName'
		/*
		try {
			String url = "jdbc:sqlite:C:/sqlite/JTP.db" + fileName;
			conn = DriverManager.getConnection(url);
			if(conn != null) {
				DatabaseMetaData meta = conn.getMetaData();
				 System.out.println("The driver name is " + meta.getDriverName());  
	             System.out.println("A new database has been created.");  
		}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		*/
		javax.swing.SwingUtilities.invokeLater(new Runnable() { 
			   
            public void run() { 
                createFrame(); 
            } 
        }); 
	}
	
}