package demo;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FieldGui extends JPanel {
	public SmallCard[] cards = new SmallCard[5];
	public JPanel panels[];
	public int boundAxisX[] = {52,149,246,343,440};
	public JLabel swords[];
	public JLabel tarjets[];

	public FieldGui(int x, int y) {
		setBounds(x,y, 544, 166);
		setOpaque(false);
		setLayout(null);

		panels = new JPanel[5];
		swords = new JLabel[5];
		tarjets = new JLabel[5];

		for(int i = 0; i<5; i++){
			panels[i] = new JPanel();
			panels[i].setBounds(boundAxisX[i], 0, 75, 145);
			panels[i].setOpaque(false);
			add(panels[i]);
			try {
				swords[i]=setLabel("sword.png", new Rectangle(boundAxisX[i], 0, 75, 145));
				add(swords[i]);
				tarjets[i]=setLabel("redTarget1.png", new Rectangle(boundAxisX[i], 0, 75, 145));
				add(tarjets[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public JLabel setLabel(String image, Rectangle rectangle) throws IOException {
		JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(image))));
		label.setBounds(rectangle);
		label.setVisible(false);
		add(label);

		return label;
	}

	public JLabel getSword(int pos){
		return swords[pos];
	}

	public JLabel getTarjet(int pos){
		return tarjets[pos];
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
			if(cards[i]!=null && Objects.equals(cards[i].getcard().getType(), "Warrior")){
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