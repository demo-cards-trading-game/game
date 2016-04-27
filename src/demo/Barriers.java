package demo;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Barriers extends JPanel {
	public static  JLabel[]  barriers   = new JLabel[5];
	public static  Card[]  cards   = new Card[5];

	public Barriers(int x ,int y) { // para de una vez conocer la ubicacion donde se pondra
		
		
		
		removeall();
		setLayout(null);
		setOpaque(false);
		setBounds(x,y+10,600,50);//se fija el tama�o y la posicion
		
		//barriers[0]=;

		

	

		



		try {
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
			barriers[4]= label_4;
			barriers[2]= label_2;
			barriers[1]= label_1;
			barriers[3]= label_3;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		
		
		
		
		
		
		
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
	void addbarrier(Card recieved)
	{
		int i = findwhere();
		cards[i]=recieved;
		barriers[i].setVisible(true);
		repaint();
		
	}
	void removebarrier(int pos)
	{
		barriers[pos].setVisible(false);
		cards[pos]=null;
		repaint();
	}
	void removeall()
	{
		
		
		
			for (int i=0;i<5;i++){
			cards[i]=null;
			}
		
	}

}
