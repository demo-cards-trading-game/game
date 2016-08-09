package demo;
import utils.GeneralConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Barriers extends JPanel implements GeneralConstants{
	public static  JLabel[]  barriers   = new JLabel[5];
	public static  Card[]  cards   = new Card[5];
	public int boundAxisX[] = {96,194,290,386,486};

	public Barriers(int x ,int y, int barrierPos) throws IOException {
		setLayout(null);
		setOpaque(false);
		setBounds(x,y,600,50);

		String fileName;
		if(barrierPos == BARRIER_PLAYER_POSITION){
			fileName = "barrier.png";
		}else{
			fileName = "barrierai.png";
		}

		for (int i=0; i<5;i++){
			JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(fileName))));
			label.setBounds(boundAxisX[i], 0, 80, 25);
			add(label);
			barriers[i]= label;
		}
	}

	public int findWhereSetCard(){
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

	public void addCard(Card card){
		int i = findWhereSetCard();
		cards[i]=card;
		barriers[i].setVisible(true);
	}

	public void removeCard(int pos){
		barriers[pos].setVisible(false);
		cards[pos]=null;
	}

	public Card getCardPos(int pos){
		return cards[pos];
	}

	public int getCardsLenght(){
		int cont =0;
		for (int i=0; i<5; i++){
			if(cards[i]!=null){
				i++;
			}
		}
		return cont;
	}

	public int findwhich(){
		int which=-1,i=0;
		while(i<5 && which==-1){
			if(barriers[i].isVisible()){
				which=i;
			}
			i++;
		}
		return(which);
	}
}