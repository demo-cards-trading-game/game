package demo;
import demo.HandGui;
import demo.DeckGui;
import demo.CardGui;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import data.LoadData;

import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.SwingConstants;
import javax.swing.JSlider;
import java.awt.Canvas;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextPane;
import java.awt.Rectangle;
import javax.swing.JLayeredPane;


public class PlayerGui extends JLayeredPane implements ActionListener, MouseListener {
	
	public static  JPanel[]  barriers   = new JPanel[5];
	public static DeckGui deck;
	public HandGui hand;
	public fieldGui field;
	int turn;
	int acampo=-1;
	int i=0;
	
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblBarrier;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private LoadData cartas;
	private JInternalFrame pane; 
	private Phases phases;
	
	public PlayerGui(int x , int y, String name) throws IOException {
	setBorder(null);

		setBackground(UIManager.getColor("Button.disabledShadow"));
		hand= new HandGui (0,0);
		hand.setLocation(179, 510);
		hand.addMouseListener(this);
		setOpaque(false);
		setLayout(null);
		setBounds(x,y, 1024, 768);
		
		JLabel name_1 = new JLabel("Player : "+ name);
		add(name_1);
		name_1.setForeground(new Color(255, 248, 220));
		name_1.setBackground(Color.WHITE);
		name_1.setHorizontalAlignment(SwingConstants.CENTER);
		name_1.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		name_1.setBounds(780, 450, 176, 64);
		
		
		pane = new JInternalFrame("THE FALLEN");
		
		phases=new Phases(200,290);
		add(phases);
		this.add(hand);
		
	
		field = new fieldGui(200,350);
		
		
		
		field.addMouseListener(this);
		this.add(field);
		
		
		/*******************************************/
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 0));
		panel.setForeground(new Color(0, 102, 0));
		panel.setBounds(179, 505, 97, 35);
		barriers[0]=panel;
		
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel.setLayout(null);
		add(panel);
		
		lblBarrier = new JLabel("Barrier");
		lblBarrier.setHorizontalAlignment(SwingConstants.CENTER);
		lblBarrier.setForeground(new Color(255, 255, 255));
		lblBarrier.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		lblBarrier.setLabelFor(panel);
		lblBarrier.setBounds(10, 0, 77, 25);
		panel.add(lblBarrier);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_1.setBackground(new Color(204, 204, 204));
		panel_1.setBounds(305, 505, 100, 35);
		add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_2.setBackground(new Color(102, 153, 204));
		panel_2.setBounds(432, 505, 88, 35);
		add(panel_2);
		
		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setForeground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_3.setBackground(new Color(102, 153, 153));
		panel_3.setBounds(547, 505, 100, 35);
		add(panel_3);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setForeground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_4.setBackground(new Color(204, 0, 102));
		panel_4.setBounds(673, 505, 97, 35);
		add(panel_4);
		barriers[1]=panel_1;
		
		label = new JLabel("Barrier");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label.setBounds(10, 0, 80, 25);
		panel_1.add(label);
		barriers[2]=panel_2;
		
		label_1 = new JLabel("Barrier");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label_1.setBounds(10, 0, 68, 25);
		panel_2.add(label_1);
		barriers[3]=panel_3;
		
		label_2 = new JLabel("Barrier");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label_2.setBounds(10, 0, 80, 25);
		panel_3.add(label_2);
		barriers[4]=panel_4;
		
		label_3 = new JLabel("Barrier");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		label_3.setBounds(10, 0, 77, 25);
		panel_4.add(label_3);
		

		deck = new DeckGui(0,0);
		deck.setSize(250, 343);
		deck.setLocation(770, 361);
		 
		this.add(deck);
		
		deck.btnNewButton.addActionListener(this);
		deck.btnNewButton_1.addActionListener(this);
		for(int i=1;i<=5;i++)
		{
			 int pos= hand.draw(deck.Deck.extraerR());
			 hand.handgui[pos-1].addMouseListener(this);
			 
			 deck.textField.setText("cards left "+ deck.Deck.cardsLeft());
			 deck.textField.repaint();
			
			  repaint();
			
		}
		
	}
	  public void actionPerformed(ActionEvent e) {
		  
		  if (e.getSource()==deck.btnNewButton)
		  {
			 
			 if(deck.Deck.cardsLeft()!= 0 )
			 {

			 int pos= hand.draw(deck.Deck.extraerR());
			 hand.handgui[pos-1].addMouseListener(this);
			 }
			 deck.textField.setText("cards left "+ deck.Deck.cardsLeft());
			 deck.textField.repaint();
			
			  repaint();
			  
			  
		  }
		  if (e.getSource()==deck.btnNewButton_1)
		  {
			  	if(!pane.isVisible())
			  	{
			  	pane = new JInternalFrame("THE FALLEN");
				pane.setBounds(86, 53, 817, 436);
				pane.setClosable(true);
				pane.setIconifiable(false);
				pane.setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
				//pane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pane.setVisible(true);
				add(pane);
				moveToFront(pane);
				
			  	}
				
				
				
				
		  }
		  
}
	void addall()
	 {
		 int i;
		 for (i=0;i<=4;i++)
		 {
			 add(barriers[i]);
			 
		 }
		 
	 }
	 

	
	public class CirclePanel extends JPanel {

	    /**
		 * 
		 */
	

		@Override
	    protected void paintComponent(Graphics g) {
	        g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
	    }
	}
	public DeckGui getDeck() {
		return deck;
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		int where;
		if(e.getButton() == MouseEvent.BUTTON1)
		    {
		     if(e.getClickCount()==2)
		     {
		    	 if(e.getSource()==hand.handgui[0])
					{
						acampo=0;
					}
		    	 else if(e.getSource()==hand.handgui[1])
					{
						acampo=1;
					}
					else if(e.getSource()==hand.handgui[2])
					{
						acampo=2;
					}
					else if(e.getSource()==hand.handgui[3])
					{
						acampo=3;
					}else if(e.getSource()==hand.handgui[4])
					{
						acampo=4;
					}
		    	 
		    	 
		    	 
		    	
		    	 if(acampo!=-1)
		    	 {
		    		 where=field.findwhere();
		    		if(where!=-1)
		    		{
		    	
		    		hand.discard(acampo+1);
		    	 	SmallCard pene;
					try {
						Random randomGenerator = new Random();
						int test=randomGenerator.nextInt(10);
						if(test % 2==0)
						{
						pene = new SmallCard(true,hand.handgui[acampo].getcard());
						
						}else
						pene = new SmallCard(false,hand.handgui[acampo].getcard());
					 	
						pene.addMouseListener(this);
						if(phases.actual<5)
							phases.change(phases.actual+1);
						else
							phases.change(0);
			    	 	
					 	
			    	 
					 	
					 		field.poner(pene,where);
					 
					 
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	
		    	 acampo=-1;
		    		 
		    		 }
		    	 }
		     }
		    }	    
		    else if(e.getButton() == MouseEvent.BUTTON3)
		    {
		    	if(e.getClickCount()==1)
		    	{
		    		
		    	
		    		if(e.getSource()==hand.handgui[0])
		    		{
		    			hand.discard(1);
		    		}
		    		if(e.getSource()==hand.handgui[1])
		    		{
		    			hand.discard(2);
		    		}
		    		if(e.getSource()==hand.handgui[2])
		    		{
		    			hand.discard(3);
		    		}
		    		if(e.getSource()==hand.handgui[3])
		    		{
		    			hand.discard(4);
		    		}
		    		if(e.getSource()==hand.handgui[4])
		    		{
		    			hand.discard(5);
		    		}
		    	
		    		if(e.getSource()==field.cards[0])
		    		{
		    			field.quitar(0);
		    		}	
		    		if(e.getSource()==field.cards[1])
		    		{
		    			field.quitar(1);
		    		}
		    		if(e.getSource()==field.cards[2])
		    		{
		    			field.quitar(2);
		    		}
		    		if(e.getSource()==field.cards[3])
		    		{
		    			field.quitar(3);
		    		}if(e.getSource()==field.cards[4])
		    		{
					field.quitar(4);
		    		}	
		    	
		    	
		    	}
		    }
		}
	
	 public void mousePressed(MouseEvent e)
	 {
		
	 }
	  public void mouseReleased(MouseEvent e) {
		  
	    }
	  
	  public void mouseDragged(MouseEvent e)
	     {
		
	     }
	  
	  
	  public void mouseExited(MouseEvent e) {
		  if(e.getSource()==hand.handgui[0])
			{
				hand.handgui[0].setBounds(0, 20, 124, 186);
			}
			else if(e.getSource()==hand.handgui[1])
			{
				hand.handgui[1].setBounds(124, 20, 124, 186);
			}
			else if(e.getSource()==hand.handgui[2])
			{
				hand.handgui[2].setBounds(248, 20, 124, 186);
			}
			else if(e.getSource()==hand.handgui[3])
			{
				hand.handgui[3].setBounds(372, 20, 124, 186);
			}else if(e.getSource()==hand.handgui[4])
			{
				hand.handgui[4].setBounds(496, 20, 124, 186);
			}
	    }
	  
	  
	
	    public void mouseMoved(MouseEvent e) {
	       
	     }

	     
		 public void mouseEntered(MouseEvent e) 
			{
				if(e.getSource()==hand.handgui[0])
				{
					hand.handgui[0].setBounds(0, 0, 124, 186);
				}
				else if(e.getSource()==hand.handgui[1])
				{
					hand.handgui[1].setBounds(124, 0, 124, 186);
				}
				else if(e.getSource()==hand.handgui[2])
				{
					hand.handgui[2].setBounds(248, 0, 124, 186);
				}
				else if(e.getSource()==hand.handgui[3])
				{
					hand.handgui[3].setBounds(372, 0, 124, 186);
				}else if(e.getSource()==hand.handgui[4])
				{
					hand.handgui[4].setBounds(496, 0, 124, 186);
				}
			}
		 
		 
		 
}

	

