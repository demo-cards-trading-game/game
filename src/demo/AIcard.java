package demo;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AIcard extends JPanel {

	/** 
	 * Create the panel.
	 */
	private Card Current;
	public AIcard(Card c ,int x ,int y) {
		setBackground(Color.BLACK);
		setBounds(x,y,100,97);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setIcon(new ImageIcon("C:\\Documents and Settings\\Administrador\\Escritorio\\game\\game\\back2.JPG"));
		lblNewLabel.setBounds(0, 0, 100, 97);
		add(lblNewLabel);
		Current=c;
	}
	
	 Card GetCard()
	 {
		 return Current;
		 
	 }
}
