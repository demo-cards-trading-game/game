package demo;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import demo.Hand;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import demo.Card;
import demo.CardGui;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.Point;
public  class AiHand extends JPanel //implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AIcard[]  handgui  = new AIcard[5];
	Card[]  cards  = new Card[5];
	int current;
	int curX = -1, curY = -1;
    boolean dragging = false;
    int sX = -1, sY = -1;
	public AiHand(int posx,int posy) {
		
		current=0;
		setOpaque(false);
		setBounds(posx,posy, 500, 111);
		setLayout(null);

		
		
	
		
	
	}
	
	 public void discard(int pos)
	    {
	     int i;
	     
	     
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
		      case 1:x= new AIcard(a,90,20);
		    	  break;
		      case 2:x= new AIcard(a,180,20);
		    	  break;
		      case 3:x= new AIcard(a,270,20);
		    	  break;
		      case 4:x= new AIcard(a,360,20);
		    	  break;
		      }
		     // x.addMouseListener(this);
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
		 
		 
		 public int findwarrior()
		 {
			int which=-1;
			int i=0;
			int found=0;
			while (i<5&& found==0)
			{
				System.out.println(cards[i].GetType());
				if(cards[i].GetType()=="Warrior")
				{
					which=i;
					found=1;
					System.out.println("encontrowarrior");
				}
				i++;
			}
			return (which);
			 
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
					System.out.println("encontrodisruption");
					which=i;
					found=1;
				}
				i++;
			}
			return (which);
			 
		 }
		}
	 	 
	
	 
	 

