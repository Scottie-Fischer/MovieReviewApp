import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App implements ActionListener{
	JTextField tf_search;
	JPanel Main_Panel;
	JFrame Main_Frame;
	public App(){
		Main_Frame = new JFrame("Movie Reviewer");
		Main_Panel = new JPanel();
		Main_Panel.setLayout(new BoxLayout(Main_Panel, BoxLayout.Y_AXIS));
		Main_Panel.setVisible(true);
		
		JPanel p1 = new JPanel();	//this will hold the options buttons
		JPanel p2 = new JPanel();	//this will hold the search functionality
		JPanel p3 = new JPanel();	//this will hold the list of movie reviews		(may be a Panel of Panels)
		
		JButton bNew = new JButton("New");
		JButton bOptions = new JButton("Options");
		JButton bExport = new JButton("Export");
		
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
		Main_Frame.add(Main_Panel);
		Main_Frame.setSize(1080,720);
		Main_Frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent s) {
		String search_holder = tf_search.getText();
		System.out.print(search_holder);
		
	}
	
	public static void main(String[] args){
		new App();
	}
	
}