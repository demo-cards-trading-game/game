package demo;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;

import extra.RoundedPanel;

public class AIcard extends RoundedPanel {

	/** 
	 * Create the panel.
	 */
	private Card Current;
	public AIcard(Card c ,int x ,int y) {
		setForeground(Color.GREEN);
		setBackground(Color.BLACK);
		setBounds(x,y,65,90);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("back2.JPG"));
		lblNewLabel.setBackground(Color.BLACK);
		
		lblNewLabel.setBounds(0, 0, 65, 85);
		add(lblNewLabel);
		Current=c;
	}
	
	 Card GetCard()
	 {
		 return Current;
		 
	 }
}
