package extra;

import java.awt.*;
import javax.swing.*;

public class RollDice extends JPanel {
	public RollDicePanel pane;
	 public JButton btnPlay ;
 public RollDice() {
	 this.setBounds(0,0,1024,768);
	 this.setLayout(null);
	 this.setOpaque(false);
	  pane=new RollDicePanel();
	  pane.rollButton.setBounds(71, 316, 440, 52);
	  pane.rollButton.setFont(new Font("Showcard Gothic", Font.PLAIN, 24));
     pane.setBounds(200,300,800,500);
	 this.add(pane);
	 
	 btnPlay = new JButton("Play");
	 btnPlay.setFont(new Font("Showcard Gothic", Font.BOLD, 14));
	 btnPlay.setBounds(161, 379, 264, 41);
	 pane.add(btnPlay);
	 btnPlay.setVisible(false);
     this.setVisible(true);
 }
}