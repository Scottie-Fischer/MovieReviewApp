import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App implements ActionListener{
	JTextField tf_search;
	JPanel Main_Panel;
	
	public App(){
		
		tf_search = new JTextField("Search");
		tf_search.setBounds(5,35,295,35);
		tf_search.setSize(295,35);
		tf_search.addActionListener(this);
		
		JFrame f = new JFrame();
		
		//Setting Up the Panels
		JPanel Panel = new JPanel();
		JPanel Panel2 = new JPanel();
		Panel.setLayout(new BoxLayout(Panel, BoxLayout.X_AXIS));		//sets the Panel Layout to BoxLayout
		Panel2.setLayout(new BoxLayout(Panel2,BoxLayout.X_AXIS));
		
		//Creating the Buttons and Such
		JButton b_New = new JButton("New");
		b_New.setBounds(0,0,100,25);	//setting x axis, y axis, width, height
		
		JButton b_Option = new JButton("Options");
		b_Option.setBounds(100,0,100,25);
		
		JButton b_Export = new JButton("Export");
		b_Export.setBounds(200,0,100,25);
		
		JButton b_Search = new JButton("Search");
		b_Search.setBounds(305,35,100,35);
		b_Search.setSize(100,35);
		JButton b_new = new JButton("test");

		
		
		//Adding the Components to Panel
		Panel.add(b_New);
		Panel.add(b_Option);
		Panel.add(b_Export);
		//b_New.set
		Panel.setBackground(Color.white);
		Panel.setVisible(true);
		Panel.setSize(300,25);
		
		Panel2.add(tf_search);
		Panel2.add(b_Search);
		Panel2.setVisible(true);
		Panel2.setSize(720,100);
		Panel2.setBounds(0,25,720,100);
		Panel2.setBackground(Color.red);
		//Adding Panel to Frame
		f.add(Panel);
		f.add(Panel2);
		
		//Setting Frame Attributes
		f.setSize(1080,720);
		f.setLayout(null);
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent s) {
		String search_holder = tf_search.getText();
		System.out.print(search_holder);
		
	}
	
	public static void main(String[] args){
		new App();
	}
	
}