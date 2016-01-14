package demo;
import javax.swing.JPanel;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;

import demo.Hand;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import demo.Card;
import demo.CardGui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.awt.RenderingHints;

import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
public  class HandGui extends JLayeredPane 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CardGui[]  handgui  = new CardGui[30];
	Card[]  cards  = new Card[30];
	public int current;
	int curX = -1, curY = -1;
    boolean dragging = false;
    int sX = -1, sY = -1;
    public JPanel panel;
	public HandGui(int posx,int posy) {
		
		current=0;
		setOpaque(false);
		setBounds(posx,posy, 620, 206);
		setLayout(null);
		
	    panel = new JPanel();
	    panel.setBorder(new CompoundBorder(new MatteBorder(0, 2, 1, 0, (Color) new Color(0, 191, 255)), new LineBorder(new Color(47, 79, 79), 4, true)));
		panel.setBackground(new Color(34, 139, 34));
		panel.setBounds(0, 20, 620, 186);
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
	      handgui[i-1]=handgui[i];
	      }
	   
	      current=current-1;
	      if(current>=5){
	    	  
	    	  handgui[0]=new CardGui(cards[0],0,20);
	    	  handgui[1]=new CardGui(cards[1],124,20);
	    	  handgui[2]=new CardGui(cards[2],248,20);
	    	  handgui[3]=new CardGui(cards[3],372,20);
	    	  handgui[4]=new CardGui(cards[4],496,20);
	    	 
	    	  compactar();
		   
	    	  
	      }
	      else{
	    	  remove(current);
		      compactar();
		
		        
	      }
	      
	      repaint();
	      
	      System.out.println("current: "+current);
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
	
			 
			 CardGui x = null;
			 cards[current]=a;
			 
			if (current<5){
			 x=new CardGui(a,current*124,20);
			
			 handgui[current]=x;
			 add(handgui[current],current);
		      current=current+1;
			}else{
			     current=current+1;
				compactar();
				
			}
		  
		  
		
		      repaint();
		      
		      System.out.println("current: "+current);
			return(current);
		 }
	 public void compactar()
	 {
		 removeAll();
		 int Factor_de_compresion;
		 if(current<5)
		 {
			 Factor_de_compresion=124;
			 for (int i=0;i<current;i++)
			 {
				handgui[i]=new CardGui(cards[i],Factor_de_compresion*i,20);
				 
				
			 }
		 }else 
		 { 
			 Factor_de_compresion=496/ (current-1);
			 for (int i=0;i<current-1;i++)
			 {
				handgui[i]=new CardGui(cards[i],Factor_de_compresion*i,20);
				 
				
			 }
			 handgui[current-1]=new CardGui(cards[current-1],498,20);
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
	 	 
	
	 
	 

