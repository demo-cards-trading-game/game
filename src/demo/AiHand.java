package demo;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
public  class AiHand extends JPanel //implements MouseListener
{
	private static final long serialVersionUID = 1L;
	public AIcard[]  handgui  = new AIcard[5];
	Card[]  cards  = new Card[5];
	int current;

	public AiHand(int posx,int posy) {
		current=0;
		setOpaque(false);
		setBounds(posx-10,posy, 500, 111);
		setLayout(null);
	}
	
	public void discard(int pos)
	{
		int i;
	     
		if(pos>0){
			for (i=pos;i<current;i++)
			{
				cards[i-1]=cards[i];
				handgui[i-1]=handgui[i];
			}
	   
			current=current-1;
			remove(current);
			compactar();
			removeAll();
			addall();
			repaint();
		}
	}
	void addall()
	{
		for (int i=0;i<current;i++)
		{
			add(handgui[i]);
		}
	}
	public int draw(Card a)
	{
		music();
		if(current==5)
		{
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(4);
			discard(randomInt+1);
		}
		AIcard x = null;
		cards[current]=a;
		      
		switch(current)
		{
			case 0:x= new AIcard(a,0,20);
				break;
			case 1:x= new AIcard(a,80,20);
				break;
			case 2:x= new AIcard(a,170,20);
				break;
			case 3:x= new AIcard(a,260,20);
				break;
			case 4:x= new AIcard(a,350,20);
				break;
		}
		handgui[current]=x;
		add(handgui[current]);
		current=current+1;
		repaint();
		return(current);
	}

	public void compactar()
	{
		for (int i=0;i<current;i++)
		{
			switch(i){
				case 0:handgui[i].setBounds(0, 20, 65, 90);
					break;
				case 1:handgui[i].setBounds(90, 20, 65, 90);
					break;
				case 2:handgui[i].setBounds(180, 20, 65, 90);
					break;
				case 3:handgui[i].setBounds(270, 20,65, 90);
					break;
				case 4:handgui[i].setBounds(360, 20, 65, 90);
					break;
			}
		}
	}

	public static void music()
	{
		String soundName = "burn.wav";
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		Clip clip = null;
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		clip.start();
	}
		 
	public int findwarrior()
	{
		int which=-1;
		int i=0;
		int found=0;
		while (i<5&& found==0)
		{
			
			if(cards[i].GetType()=="Warrior")
			{
				which=i;
				found=1;
			}
			i++;
		}
		return (which);
	}
		 
	public int countcards()//cuenta las cartas en la mano
	{
		int cant=0;
		int i = 0;
		while(i<5)
		{
			if(handgui[i]!=null)
				cant++;
			i++;
		}
		return cant;
	}
	public int finddisruption()
	{
		int which=-1;
		int i=0;
		int found=0;
		while (i<5&& found==0)
		{
			if(cards[i].GetType()=="Disruption")
			{
				which=i;
				found=1;
			}
			i++;
		}
		return (which);
	}
}