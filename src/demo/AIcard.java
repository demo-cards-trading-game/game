package demo;
import extra.RoundedPanel;
import javax.swing.*;
import java.awt.*;

public class AIcard extends RoundedPanel {

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
