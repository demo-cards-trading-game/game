package demo;
import extra.RoundedPanel;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class FieldGui extends JPanel {
	public SmallCard[]  cards  = new SmallCard[5];
	public JPanel panels[];
	public int boundAxisX[] = {52,149,246,343,440};

	public FieldGui(int x, int y) {
		setBounds(x,y, 544, 166);
		setOpaque(false);
		setLayout(null);

		panels = new JPanel[5];

		for(int i = 0; i<5; i++){
			panels[i] = new JPanel();
			panels[i].setBounds(boundAxisX[i], 0, 75, 145);
			panels[i].setOpaque(false);
			add(panels[i]);
		}
	}

	public RoundedPanel setRoundedPanel(Color color, Rectangle rectangle){
		RoundedPanel roundedPanel = new RoundedPanel();
		roundedPanel.setLayout(null);
		roundedPanel.setForeground(color);
		roundedPanel.setBackground(new Color(169,169,169));
		roundedPanel.setBounds(rectangle);
		roundedPanel.arcs = new Dimension(10,10);
		roundedPanel.shady = false;
		return roundedPanel;
	}

	void addCard(SmallCard card, int pos){
		for(int i=0; i<5; i++){
			if(pos==i && panels[i].isShowing()){
				remove(panels[i]);
				cards[i] = card;
				card.setBounds(boundAxisX[i],0,83,145);
			}
		}
		add(cards[pos]);
	}
	 
	public void removeCard(int pos){
		if(cards[pos]!=null){
			for(int i=0; i<5; i++){
				if(pos==i){
					remove(cards[i]);
					cards[i]=null;
					add(panels[i]);
				}
			}
		}
	}

	int findwhereSetCard(){
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

	int findWarrior(){
		int x=-1;
		int i=0;
		while(x==-1 && i<5){
			if(cards[i]!=null && Objects.equals(cards[i].getCard().getType(), "Warrior")){
				x=i;
			}
			i++;
		}
		return x;
	}
	 
	public int getCardsLength(){
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