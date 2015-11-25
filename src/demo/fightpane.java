package demo;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;

public class fightpane extends JInternalFrame {//en este panel se muestran las dos cartas que pelearan

	/**
	 * Create the panel.
	 */
	JTextPane txtpnVs;
	
	public fightpane( ) 
	{
		setLayout(null);
		this.setBounds(200,150,700,400);
		this.setOpaque(false);
		setClosable(true);
		txtpnVs = new JTextPane();
		txtpnVs.setForeground(Color.WHITE);
		txtpnVs.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 32));
		txtpnVs.setText("VS");
		txtpnVs.setBounds(300, 128, 78, 58);
		
		txtpnVs.setOpaque(false);
		setVisible(false);
		
	}
	
	public void addCards(BigCard p1 , BigCard p2)
	{
		add(p1);
		add(p2);
		p1.setLocation(60,15);
		p2.setLocation(360,15);
		add(txtpnVs);
		setVisible(true);
	}
	
	public void Remove()
	{
		removeAll();
		
		
	}
	
}
