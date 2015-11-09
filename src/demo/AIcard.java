package demo;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class AIcard extends JPanel {

	/** 
	 * Create the panel.
	 */
	private Card Current;
	public AIcard(Card c ,int x ,int y) {
		setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		setBackground(Color.BLACK);
		setBounds(x,y,65,90);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("back2.JPG"));
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
