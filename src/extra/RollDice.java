package extra;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class RollDice extends JPanel {
	public RollDicePanel pane;
	 public JButton btnPlay ;
	 public JLabel label;
	 
 public RollDice() throws IOException {
	 
	 
	 this.setBounds(0,0,1024,768);
	 this.setLayout(null);
	 this.setOpaque(false);
	  pane=new RollDicePanel();
	  pane.dice2.setLocation(544, 51);
	  pane.dice1.setLocation(21, 51);
	  pane.rollButton.setBounds(180, 306, 440, 52);
	  pane.rollButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
     pane.setBounds(148,258,788,499);
	 this.add(pane);
	 
	 btnPlay = new JButton("Play");
	 btnPlay.setFont(new Font("Showcard Gothic", Font.BOLD, 14));
	 btnPlay.setBounds(268, 369, 264, 41);
	 pane.add(btnPlay);
	 
	 label = new JLabel("");
	 label.setForeground(Color.WHITE);
	 label.setHorizontalAlignment(SwingConstants.CENTER);
	 label.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
	 label.setBounds(236, 320, 507, 41);
	 pane.add(label);
	 btnPlay.setVisible(false);
	 label.setVisible(false);
     this.setVisible(true);
 }
}