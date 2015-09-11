package demo;

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.util.Random;

public class field extends JPanel {

	/**
	 * Create the panel.
	 */
	private JPanel[]  p1  = new JPanel[5];
	private JPanel[]  p2  = new JPanel[5];
	private Card[]  cards1  = new Card[5];
	private Card[]  cards2  = new Card[5];
	private int current;
	public field(int x,int y) {
		setBounds(new Rectangle(x, y, 580, 370));
		setLayout(null);
		current=0;
		JPanel panel = new JPanel();
		panel.setBounds(10, 214, 100, 145);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(131, 214, 100, 145);
		add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(241, 214, 100, 145);
		add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(351, 214, 100, 145);
		add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(470, 214, 100, 145);
		add(panel_4);
		
		p1[0]=panel_1;
		p1[1]=panel_2;
		p1[2]=panel_3;
		p1[3]=panel_4;
		p1[2]=panel;
		setOpaque(false);

	}
	
	 void addall()
	 {
		 int i;
		 for (i=0;i<=4;i++)
		 {
			 add(p1[i]);
			 
		 }
		 
	 }
	 public void draw(Card a)
	 {
		 
		 
			 SmallCard x= new SmallCard(a,0,0);
		      p1[current].add(x);
		      cards1[current]=a;
		      if (current<4)
		      current=current+1;
		   
			 repaint();
		 
	  }
}
