package demo;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public  class AiHand extends JPanel
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
	
	public void discard(int pos){
		int i;
	     
		if(pos>0){
			for (i=pos;i<current;i++){
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
	void addall(){
		for (int i=0;i<current;i++){
			add(handgui[i]);
		}
	}
	public int draw(Card a) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		music();
		if(current==5)
		{
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(4);
			discard(randomInt+1);
		}

		AIcard x = null;
		cards[current]=a;
		      
		switch(current){
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

	public static void music() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		String soundName = "burn.wav";
		AudioInputStream audioInputStream;
		audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());

		Clip clip;
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
	}

	public int countcards()
	{
		int cant=0;
		int i = 0;
		while(i<5){
			if(handgui[i]!=null)
				cant++;
			i++;
		}
		return cant;
	}
}