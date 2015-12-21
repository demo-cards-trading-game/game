package demo;

import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Color;

public class fightpane extends JLayeredPane{//en este panel se muestran las dos cartas que pelearan

	/**
	 * Create the panel.
	 */
	JTextPane txtpnVs;
	BigCard a1 ;
	BigCard a2;
	public fightpane( ) 
	{
		setLayout(null);
		this.setBounds(200,150,700,400);
		this.setOpaque(false);
	
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
		int winner;
		a2=p2;
		a1=p1;
		
		if (p1.getcard().GetHp() > p2.getcard().GetHp())//gana la carta 1
		{
			add(p1);
			add(p2);
		} else //gana la carta 2
		{
			add(p2);
			add(p1);
		}
		
		p1.setLocation(60,15);
		p2.setLocation(360,15);
		add(txtpnVs);
		setVisible(true);
		Thread t = new Thread(new Runnable(){
			
			public void start() {
				this.start();
			}

			public void run() {
				int i = 0;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();

				}

				while (60 + i <= 360 - i) {
				a1.setLocation(60 + i, 15);
					a2.setLocation(360 - i, 15);

					i++;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();

					}

				}
			
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();

				}
				
				
				setVisible(false);
				Remove();
			}
		

		});
		t.start();
	}
	
	public void Remove()
	{
		removeAll();
		
		
	}
	
}
