package demo;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.util.Random;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
public class fieldGui extends JPanel {
	public JButton btn;
	public JPanel panel2,panel,panel3,panel4,panel1,panel5; 
	public SmallCard[]  cards  = new SmallCard[5];

	public fieldGui(int posx,int posy) {
		
		setOpaque(false);
		setBounds(posx,posy, 544, 145);
		setLayout(null);
		
		 panel = new JPanel();
		panel.setOpaque(true);
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBorder(new CompoundBorder(new MatteBorder(0, 2, 1, 0, (Color) new Color(0, 191, 255)), new LineBorder(new Color(0, 0, 0), 3, true)));
		panel.setBackground(new Color(169, 169, 169));
		panel.setBounds(0, 0, 100, 145);
		add(panel);
	
		 panel2 = new JPanel();
		panel2.setOpaque(true);
		panel2.setLayout(null);
		panel2.setForeground(new Color(147, 112, 219));
		panel2.setBorder(new CompoundBorder(new MatteBorder(0, 2, 1, 0, (Color) new Color(0, 191, 255)), new LineBorder(new Color(0, 0, 0), 3, true)));
		panel2.setBackground(new Color(169, 169, 169));
		panel2.setBounds(110, 0, 100, 145);
		add(panel2);
		
		panel3 = new JPanel();
		panel3.setOpaque(true);
		panel3.setLayout(null);
		panel3.setForeground(new Color(192, 192, 192));
		panel3.setBorder(new CompoundBorder(new MatteBorder(0, 2, 1, 0, (Color) new Color(0, 191, 255)), new LineBorder(new Color(0, 0, 0), 3, true)));
		panel3.setBackground(new Color(169, 169, 169));
		panel3.setBounds(220, 0, 100, 145);
		add(panel3);
		
		panel4 = new JPanel();
		panel4.setOpaque(true);
		panel4.setLayout(null);
		panel4.setForeground(Color.WHITE);
		panel4.setBorder(new CompoundBorder(new MatteBorder(0, 2, 1, 0, (Color) new Color(0, 191, 255)), new LineBorder(new Color(0, 0, 0), 3, true)));
		panel4.setBackground(new Color(169, 169, 169));
		panel4.setBounds(330, 0, 100, 145);
		add(panel4);
		
		panel5 = new JPanel();
		panel5.setOpaque(true);
		panel5.setLayout(null);
		panel5.setForeground(Color.WHITE);
		panel5.setBorder(new CompoundBorder(new MatteBorder(0, 2, 1, 0, (Color) new Color(0, 191, 255)), new LineBorder(new Color(0, 0, 0), 3, true)));
		panel5.setBackground(new Color(169, 169, 169));
		panel5.setBounds(440, 0, 100, 145);
		add(panel5);
			
	}
	void poner(SmallCard x,int pos)
	{
		
		switch(pos)
		{
		
		case 0:
			if(panel.isShowing()){
			remove(panel);
			cards[0]=x;
				x.setBounds(0,0,100,145);
			
			}
			break;
		case 1:
			if(panel2.isShowing())
			{
			remove(panel2);
			cards[1]=x;
			x.setBounds(110,0,100,145);
					
			}
			break;
		case 2: 
			if(panel3.isShowing())
			{
			remove(panel3);
			cards[2]=x;
			x.setBounds(220,0,100,145);
			
			}
			break;
		case 3 :
			if(panel4.isShowing()){
			remove(panel4);
			cards[3]=x;
			x.setBounds(330,0,100,145);
			}
			break;
			
		case 4:
			if(panel5.isShowing()){
			remove(panel5);
			cards[4]=x;
			x.setBounds(440,0,100,145);
			}
			
			
			break;
			
		}
		
		
	
		add(cards[pos]);
		repaint();
	
	}
	 
	public void quitar(int pos)
	{
		if(cards[pos]!=null)
		{
		switch(pos)
		{
		case 0:
			remove(cards[0]);
		cards[0]=null;
			add(panel);
			
			
			break;
		case 1:
			if(cards[1].isShowing())
			{
			remove(cards[1]);
			cards[1]=null;
			add(panel2);
			}
			break;
		case 2: 
			if(cards[2].isShowing())
			{
			remove(cards[2]);
			cards[2]=null;
			add(panel3);
			}
			break;
		case 3 :
			if(cards[3].isShowing()){
			remove(cards[3]);
			cards[3]=null;
			add(panel4);
			}
			break;
			
		case 4:
			if(cards[4].isShowing()){
			remove(cards[4]);
			cards[4]=null;
			add(panel5);
			}
		
			break;
				
		}
		
		}
		repaint();
	}
	int findwhere()
	{
		int x=-1;
		int i=0;
		while(x==-1 && i<5)
		{
			if(cards[i]==null)
			{
				x=i;
			}
			i++;
			
		}
		
		return x;
	}
	int findwarrior()
	{
		int x=-1;
		int i=0;
		while(x==-1 && i<5)
		{
			if(cards[i]!=null)
			{
				if(cards[i].getcard().GetType()=="Warrior"){
				x=i;
				}
			}
			i++;
			
		}
		
		return x;
	}
	
	 
	 public int countcards()//cuenta las cartas en la mano
		{
			int cant=0;
			int i = 0;
			while(i<5)
			{
				if(cards[i]!=null)
					cant++;
				i++;
				
			}
			
			return cant;
		}
}
