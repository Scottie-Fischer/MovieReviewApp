import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends javax.swing.JFrame{
	JTextField tf_search;
	JPanel Main_Panel,p1,p2,p3;
	JButton bNew,bOptions,bExport;
	//JFrame Main_Frame;
	
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
	
	public void actionPerformed(ActionEvent s) {
		String search_holder = tf_search.getText();
		System.out.print(search_holder);
		
	}
	
	public static void main(String[] args){
		new App().setVisible(true);
	}
	
}