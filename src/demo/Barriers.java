package demo;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Barriers extends JPanel {
	public static  JLabel[]  barriers   = new JLabel[5];
	public static  Card[]  cards   = new Card[5];

	public Barriers(int x ,int y) throws IOException {
		removeall();
		setLayout(null);
		setOpaque(false);
		setBounds(x,y+10,600,50);

		// TODO: 21-06-2016 hacer abstraccion de la definicion de estos componentes junto con el barriers2.java 
		JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File("barrier.png"))));
		label.setBounds(96, 0, 80, 25);
		add(label);

		JLabel label_1 = new JLabel(new ImageIcon(ImageIO.read(new File("barrier.png"))));
		label_1.setBounds(194, 0, 80, 25);
		add(label_1);

		JLabel label_2 = new JLabel(new ImageIcon(ImageIO.read(new File("barrier.png"))));
		label_2.setBounds(290, 0, 80, 25);
		add(label_2);

		JLabel label_3;
		label_3 = new JLabel(new ImageIcon(ImageIO.read(new File("barrier.png"))));
		label_3.setBounds(386, 0, 80, 25);
		add(label_3);

		JLabel label_4 = new JLabel(new ImageIcon(ImageIO.read(new File("barrier.png"))));
		label_4.setBounds(486, 0, 80, 25);
		add(label_4);

		barriers[0]= label;
		barriers[1]= label_1;
		barriers[2]= label_2;
		barriers[3]= label_3;
		barriers[4]= label_4;
	}

	public int findwhere(){
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

	public void addbarrier(Card recieved){
		int i = findwhere();
		cards[i]=recieved;
		barriers[i].setVisible(true);
		repaint();
		
	}

	public void removebarrier(int pos){
		barriers[pos].setVisible(false);
		cards[pos]=null;
		repaint();
	}

	public void removeall(){
		for (int i=0;i<5;i++){
			cards[i]=null;
		}
	}
}