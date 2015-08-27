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
	private int current;
	public HandGui(int posx,int posy) {
		setBackground(Color.BLACK);
		current=0;
		
		setBounds(posx,posy, 780, 252);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.BLACK);
		add(panel_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.BLACK);
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
			 
			 CardGui x= new CardGui(a);
		      handgui[current]=x;
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
