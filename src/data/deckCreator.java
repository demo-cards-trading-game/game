package data;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.border.LineBorder;

public class deckCreator extends JInternalFrame {

	
	public deckCreator(int x , int y) {
		getContentPane().setLayout(null);
		setBounds(x,y,300,400);
		JList list = new JList();
		list.setBounds(10, 11, 270, 224);
		getContentPane().add(list);
		setClosable(true);
		setIconifiable(false);
		setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		

	}
}
