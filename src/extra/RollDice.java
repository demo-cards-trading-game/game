package extra;

import java.awt.*;
import javax.swing.*;

public class RollDice extends JPanel {
	public RollDicePanel pane;
	 public JButton btnPlay ;
	 public JLabel label;
	 
 public RollDice() {
	 
	 
	 this.setBounds(0,0,1024,768);
	 this.setLayout(null);
	 this.setOpaque(false);
	  pane=new RollDicePanel();
	  pane.rollButton.setBounds(71, 316, 440, 52);
	  pane.rollButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
     pane.setBounds(230,257,616,500);
	 this.add(pane);
	 
	 btnPlay = new JButton("Play");
	 btnPlay.setFont(new Font("Showcard Gothic", Font.BOLD, 14));
	 btnPlay.setBounds(161, 379, 264, 41);
	 pane.add(btnPlay);
	 
	 label = new JLabel("");
	 label.setForeground(Color.WHITE);
	 label.setHorizontalAlignment(SwingConstants.CENTER);
	 label.setFont(new Font("Showcard Gothic", Font.BOLD, 20));
	 label.setBounds(25, 316, 507, 41);
	 pane.add(label);
	 btnPlay.setVisible(false);
	 label.setVisible(false);
     this.setVisible(true);
 }
}