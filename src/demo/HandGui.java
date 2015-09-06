package demo;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import demo.Hand;
import demo.Card;
import demo.CardGui;
import java.awt.Color;
import java.util.Random;
public class HandGui extends JPanel {
	private JPanel[]  handgui  = new JPanel[5];
	private Card[]  cards  = new Card[5];
	private int current;
	
	public HandGui(int posx,int posy) {
		
		current=0;
		setOpaque(false);
		setBounds(posx,posy, 620, 186);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		add(panel_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setOpaque(false);
		add(panel_5);
		
		handgui[0] = panel;
		handgui[1] = panel_1;
		handgui[2] = panel_2;
		handgui[3] = panel_3;
		handgui[4] = panel_5;
		addall();
		
	
	}
	
	 public void discard(int pos)
	    {
	     int i;
	     
	     
	      for (i=pos;i<current;i++)
	      {
	       
	      handgui[i-1]=handgui[i];
	      cards[i-1]=cards[i];
	      }

	      current=current-1;
	      addall();
	      repaint();
	    }
	 
	 public void draw(Card a)
	 {
		 
		 if(current==5)
		 {
			 Random randomGenerator = new Random();
		 	int randomInt = randomGenerator.nextInt(4);
			 discard(randomInt+1);
		  
		 }
			 
			 CardGui x= new CardGui(a,0,0);
		      handgui[current]=x;
		      cards[current]=a;
		      current=current+1;
		     removeAll();
			 addall();
			 repaint();
		 
	  }
	 
	 void addall()
	 {
		 int i;
		 for (i=0;i<=4;i++)
		 {
			 add(handgui[i]);
			 
		 }
		 
	 }
	 
	 
	 
}
