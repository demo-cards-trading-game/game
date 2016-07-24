package demo;
import extra.RoundedPanel;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class fieldGui extends JPanel {
	public RoundedPanel panel2,panel,panel3,panel4,panel5;
	public SmallCard[]  cards  = new SmallCard[5];

	public fieldGui(int posx,int posy) {
		setOpaque(false);
		setBounds(posx,posy, 544, 166);
		setLayout(null);
		
		panel = new RoundedPanel();
		panel.setLayout(null);
		panel.setForeground(Color.GREEN);
		panel.setBackground(new Color(169, 169, 169));
		panel.setBounds(52, 0, 75, 145);
		panel.arcs=new Dimension(10,10);
		panel.shady=false;
		add(panel);
	
		panel2 = new RoundedPanel();
		panel2.setLayout(null);
		panel2.setForeground(new Color(147, 112, 219));
		panel2.setBackground(new Color(169, 169, 169));
		panel2.setBounds(149, 0, 75, 145);
		panel2.arcs=new Dimension(10,10);
		panel2.shady=false;
		add(panel2);
		
		panel3 = new RoundedPanel();
		panel3.setLayout(null);
		panel3.setForeground(Color.ORANGE);
		panel3.setBackground(new Color(169, 169, 169));
		panel3.setBounds(246, 0, 75, 145);
		panel3.arcs=new Dimension(10,10);
		panel3.shady=false;
		add(panel3);

		panel4 = new RoundedPanel();
		panel4.setLayout(null);
		panel4.setForeground(Color.MAGENTA);
		panel4.setBackground(new Color(169, 169, 169));
		panel4.setBounds(343, 0, 75, 145);
		panel4.arcs=new Dimension(10,10);
		panel4.shady=false;
		add(panel4);

		panel5 = new RoundedPanel();
		panel5.setLayout(null);
		panel5.setForeground(Color.CYAN);
		panel5.setBackground(new Color(169, 169, 169));
		panel5.setBounds(440, 0, 75, 145);
		panel5.arcs=new Dimension(10,10);
		panel5.shady=false;
		add(panel5);

	}

	void poner(SmallCard x,int pos){
		switch(pos){
			case 0:
				if(panel.isShowing()){
					remove(panel);
					cards[0]=x;
					x.setBounds(52, 0, 83, 145);
				}
				break;
			case 1:
				if(panel2.isShowing()){
					remove(panel2);
					cards[1]=x;
					x.setBounds(149, 0, 83, 145);
				}
				break;
			case 2:
				if(panel3.isShowing()){
					remove(panel3);
					cards[2]=x;
					x.setBounds(246, 0, 83, 145);
				}
				break;
			case 3 :
				if(panel4.isShowing()){
					remove(panel4);
					cards[3]=x;
					x.setBounds(343, 0, 83, 145);
				}
				break;
			case 4:
				if(panel5.isShowing()){
					remove(panel5);
					cards[4]=x;
					x.setBounds(440, 0, 83, 145);
				}
				break;
		}
		add(cards[pos]);
		repaint();
	}
	 
	public void quitar(int pos){
		if(cards[pos]!=null){
			switch(pos){
				case 0:
					remove(cards[0]);
					cards[0]=null;
					add(panel);
					break;
				case 1:
					if(cards[1].isShowing()){
						remove(cards[1]);
						cards[1]=null;
						add(panel2);
					}
					break;
				case 2:
					if(cards[2].isShowing()){
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

	int findwhere(){
		int x=-1;
		int i=0;
		while(x==-1 && i<5){
			if(cards[i]==null){
				x=i;
			}
			i++;
		}
		return x;
	}

	int findwarrior(){
		int x=-1;
		int i=0;
		while(x==-1 && i<5){
			if(cards[i]!=null){
				if(Objects.equals(cards[i].getcard().getType(), "Warrior")){
					x=i;
				}
			}
			i++;
		}
		return x;
	}
	 
	public int countcards(){
		int cant=0;
		int i = 0;
		while(i<5){
			if(cards[i]!=null)
				cant++;
			i++;
		}
		return cant;
	}
}