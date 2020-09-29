import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;

public class App extends javax.swing.JFrame{
	
	JPanel Main_Panel,p1,p2,p3;
	
	final static boolean RIGHT_TO_LEFT = false;
	final static boolean shouldFill = true; 
    final static boolean shouldWeightX = true;
	//JFrame Main_Frame;
	/*
	public App(){
		//Main_Frame = new JFrame("Movie Reviewer");
		Main_Panel = new JPanel();
		Main_Panel.setLayout(new GridBagLayout());
		Main_Panel.setVisible(true);
		
		p1 = new JPanel();	//this will hold the options buttons
		p2 = new JPanel();	//this will hold the search functionality
		p3 = new JPanel();	//this will hold the list of movie reviews		(may be a Panel of Panels)
		
		bNew = new JButton("New");
		bNew.setBounds(0,0,100,100);
		bOptions = new JButton("Options");
		bExport = new JButton("Export");
		
		setPreferredSize(new Dimension(1080,720));
		p1.add(bNew);
		p1.add(bOptions);
		p1.add(bExport);
		
		p1.setVisible(true);
		p1.setBackground(Color.black);
		p2.setVisible(true);
		p2.setBackground(Color.red);
		p3.setVisible(true);
		p1.setBackground(Color.blue);
		
		Main_Panel.add(p1);
		Main_Panel.add(p2);
		Main_Panel.add(p3);
		Main_Panel.setVisible(true);
		getContentPane().add(Main_Panel);
		pack();
	}
	*/
	
	
	public static void assemble_Frame(Container pane) {
		JButton bNew,bOptions,bExport,bSearch;
		JTextField tf_search;
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
		bNew = new JButton("New");
		constraint.gridx = 0;
		constraint.gridy = 0;
		pane.add(bNew,constraint);
		
		bOptions = new JButton("Options");
		constraint.gridx = 1;
		constraint.gridy = 0;
		pane.add(bOptions,constraint);
		
		bExport = new JButton("Export");
		constraint.gridx = 2;
		constraint.gridy = 0;
		pane.add(bExport,constraint);
		
		tf_search = new JTextField("Enter a Movie Title");
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.ipady = 20;
		constraint.weightx = 0.0;
		constraint.gridwidth = 2;
		constraint.gridx = 0;
		constraint.gridy = 1;		//request any extra vertical space
		pane.add(tf_search,constraint);
		
		bSearch = new JButton("Search");
		constraint.gridx = 2;
		constraint.gridy = 1;
		pane.add(bSearch,constraint);
	}
	
	private static void createFrame() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		JFrame Main_Frame = new JFrame("Move Reviewist");
		
		Main_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//when window is closed the program closes
		Main_Frame.pack();		//adds all the components and sets things before it makes the window visible
		assemble_Frame(Main_Frame.getContentPane());
		Main_Frame.setVisible(true);
	}
	/*
	public void actionPerformed(ActionEvent s) {
		String search_holder = tf_search.getText();
		System.out.print(search_holder);
	}
	*/
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() { 
			   
            public void run() { 
                createFrame(); 
            } 
        }); 
	}
	
}