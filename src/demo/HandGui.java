package demo;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
public  class HandGui extends JLayeredPane 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CardGui[]  handgui  = new CardGui[30];
	Card[]  cards  = new Card[30];
	public int current;
	public JPanel panel;
	public  int Factor_de_compresion=100;
	public HandGui(int posx,int posy) {
		
		current=0;
		setOpaque(false);
		setBounds(posx-15,posy, 560, 206);
		setLayout(null);
		panel = new JPanel();
		panel.setOpaque(false);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(30, 20, 500, 186);
		add(panel);
	}

	public int countcards()//cuenta las cartas en la mano
	{
		return current;
	}
	
	public void discard(int pos)
	{
		int i;

		for (i=pos;i<current;i++)
		{
			cards[i-1]=cards[i];
		}
		current=current-1;
		compactar();
		repaint();
	}

	void addall()
	{
		add(panel);
		
		for (int i=0;i<current;i++)
		{
			add(handgui[i]);
			moveToFront(handgui[i]);
		}
	}
	public int draw(Card a)
	{
		music();
		CardGui x;
		cards[current]=a;
			 
		if (current<5){
			x=new CardGui(a,current*100+30,20);
			handgui[current]=x;
			add(handgui[current],current);
			current=current+1;
		}else{
			current=current+1;
			compactar();
		}
		repaint();

		return(current);
	}
	public int draw(CardGui a)
	{
		music();
		CardGui x;
		cards[current]=a.getcard();
			 
		if (current<5){
			x=new CardGui(a.getcard(),current*100+30,20);
			handgui[current]=x;
			add(handgui[current],current);
			current=current+1;
		}else{
			current=current+1;
			compactar();
		}
		repaint();

		return(current);
	}
	 
	public void compactar()
	{
		removeAll();
		
		if(current<5)
		{
			Factor_de_compresion=100;
			for (int i=0;i<current;i++)
			{
				handgui[i]=new CardGui(cards[i],Factor_de_compresion*i+30,20);
			}
		}else
		{
			Factor_de_compresion=400/ (current-1);
			for (int i=0;i<current-1;i++)
			{
				handgui[i]=new CardGui(cards[i],Factor_de_compresion*i+30,20);
			}
			handgui[current-1]=new CardGui(cards[current-1],400+30,20);
		}
		addall();
	}

	public static void music()
	{
		String soundName = "burn.wav";
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();
	}
}