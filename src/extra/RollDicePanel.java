package extra;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

public class RollDicePanel extends JPanel {
 public Die _leftDie;  
 public Die _rightDie;
 public JButton rollButton;
 private IconGetter getter; 
 public JLabel text = new JLabel(""); //donde deberia ir el resultado
 RollDicePanel() {
	 this.setOpaque(false);
     //... Create the dice
    /* _leftDie  = new Die();
     _leftDie.setBounds(5, 51, 244, 244);
     _rightDie = new Die();
     _rightDie.setBounds(350, 51, 244, 244);
     */
     getter = new IconGetter();
	 JLabel dice1 = new JLabel(getter.getIcon("d1.png"));
	 dice1.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
	 dice1.setBounds(5, 51, 244, 244);
     JLabel dice2 = new JLabel(getter.getIcon("d1.png"));
     dice2.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
     dice2.setBounds(350, 51, 244, 244);
     rollButton = new JButton("Let them roll");
     rollButton.setBounds(71, 317, 440, 41);
     rollButton.setFont(new Font("Sansserif", Font.PLAIN, 24));
     
    
     
    
     setLayout(null);
     this.add(rollButton);
    // this.add(_leftDie);
    // this.add(_rightDie);
     this.add(dice1);
     this.add(dice2);
     rollButton.addActionListener(new ButtonListener(dice1, dice2, text));
     this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
 }
 
}
