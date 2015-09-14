package demo;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import demo.Hand;
import demo.Card;
import demo.CardGui;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.event.MouseAdapter;
public  class HandGui extends JPanel implements MouseListener{
	private JPanel[]  handgui  = new JPanel[5];
	private Card[]  cards  = new Card[5];
	private int current;
	
	public HandGui(int posx,int posy) {
		
		current=0;
		setOpaque(false);
		setBounds(posx,posy, 620, 206);
		setLayout(null);
		addMouseListener(this);
		
		
	
		
	
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
	 public void draw(Card a)
	 {
		 
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
		      x.addMouseListener(this);
		      handgui[current]=x;
		      add(handgui[current]);
		      current=current+1;
		      
			
			 repaint();
		 
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
	 
	 public void mousePressed(MouseEvent e) {
	
	    }
	  public void mouseReleased(MouseEvent e) {
		
	    }
	  public void mouseExited(MouseEvent e) {
		  if(e.getSource()==handgui[0])
			{
				handgui[0].setBounds(0, 20, 124, 186);
			}
			else if(e.getSource()==handgui[1])
			{
				handgui[1].setBounds(124, 20, 124, 186);
			}
			else if(e.getSource()==handgui[2])
			{
				handgui[2].setBounds(248, 20, 124, 186);
			}
			else if(e.getSource()==handgui[3])
			{
				handgui[3].setBounds(372, 20, 124, 186);
			}else if(e.getSource()==handgui[4])
			{
				handgui[4].setBounds(496, 20, 124, 186);
			}
	    }
	  
	  public void mouseClicked(MouseEvent e) 
	  {
		  if(e.getButton() == MouseEvent.BUTTON1)
		    {
		     if(e.getClickCount()==2)
		     {
		    	 System.out.println("se jugara la carta");
		     }
		    }	    
		    else if(e.getButton() == MouseEvent.BUTTON3)
		    {
		    	if(e.getSource()==handgui[0])
				{
					discard(1);
				}
				else if(e.getSource()==handgui[1])
				{
					discard(2);
				}
				else if(e.getSource()==handgui[2])
				{
					discard(3);
				}
				else if(e.getSource()==handgui[3])
				{
					discard(4);
				}else if(e.getSource()==handgui[4])
				{
					discard(5);
				}
		    }
		  
	   }
	
	    public void mouseMoved(MouseEvent e) {
	       
	     }

	     public void mouseDragged(MouseEvent e) {
	       
	     }
		 public void mouseEntered(MouseEvent e) 
			{
				if(e.getSource()==handgui[0])
				{
					handgui[0].setBounds(0, 0, 124, 186);
				}
				else if(e.getSource()==handgui[1])
				{
					handgui[1].setBounds(124, 0, 124, 186);
				}
				else if(e.getSource()==handgui[2])
				{
					handgui[2].setBounds(248, 0, 124, 186);
				}
				else if(e.getSource()==handgui[3])
				{
					handgui[3].setBounds(372, 0, 124, 186);
				}else if(e.getSource()==handgui[4])
				{
					handgui[4].setBounds(496, 0, 124, 186);
				}
			}
		
		 
	 }	 
	
	 
	 

