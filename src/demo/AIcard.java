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
		setBounds(x,y,61,90);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(Color.BLACK);
		
		lblNewLabel.setBounds(0, 0, 65, 90);
		add(lblNewLabel);
		Current=c;
	}
	
	 Card GetCard()
	 {
		 return Current;
		 
	 }
}
