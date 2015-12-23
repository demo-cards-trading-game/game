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
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.Point;
public  class HandGui extends JPanel //implements MouseListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CardGui[]  handgui  = new CardGui[5];
	Card[]  cards  = new Card[5];
	public int current;
	int curX = -1, curY = -1;
    boolean dragging = false;
    int sX = -1, sY = -1;

	public HandGui(int posx,int posy) {
		
		current=0;
		setOpaque(false);
		setBounds(posx,posy, 620, 206);
		setLayout(null);
	
		
		
	
		
	
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
			 
			 CardGui x = null;
			 cards[current]=a;
		      
		      switch(current)
		      {
		      case 0:x= new CardGui(a,0,20);
		    	  break;
		      case 1:x= new CardGui(a,124,20);
		    	  break;
		      case 2:x= new CardGui(a,248,20);
		    	  break;
		      case 3:x= new CardGui(a,372,20);
		    	  break;
		      case 4:x= new CardGui(a,496,20);
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
			 	case 0:handgui[i].setBounds(0, 20, 124, 186);
			 		break;
			 	case 1:handgui[i].setBounds(124, 20, 124, 186);
			 		break;
			 	case 2:handgui[i].setBounds(248, 20, 124, 186);
			 		break;
			 	case 3:handgui[i].setBounds(372, 20, 124, 186);
			 		break;
			 	case 4:handgui[i].setBounds(496, 20, 124, 186);
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
		}
	 	 
	
	 
	 

