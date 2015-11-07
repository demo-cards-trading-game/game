package demo;
import demo.HandGui;
import demo.Fallen.SimpleColorTableModel;
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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

	public Barriers barriers;
	public Drained powers;
	public static DeckGui deck;
	public Previewpane preview;
	public HandGui hand;
	public fieldGui field;
	public AIGui ai;
	int turn;
	int acampo=-1;
	int i=0;
	 private Fallen fallen ;
	private LoadData cartas;
	JInternalFrame pane; 
	private Phases phases;
	public JButton changePhase;
	private FileReader turno;
	private BufferedReader br;
	
	
	public int getPhaseActual(){
		return phases.actual;
	}
	
	public PlayerGui(int x , int y, String name) throws IOException {
		setBorder(null);
		preview= new Previewpane();
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


		field = new fieldGui(220,350);
		ai = new AIGui();


		field.addMouseListener(this);

		this.add(field);
		add(ai);

		/*******************************************/
		powers=new Drained(15,320);
		add(powers);

		deck = new DeckGui(0,0);
		deck.setSize(250, 343);
		deck.setLocation(770, 361);
		this.add(deck);
		this.add(preview);
		
		barriers =new Barriers(179,500);
		add(barriers);
		barriers.addMouseListener(this);
		for(int i=1;i<=5;i++)
		{
			int pos= hand.draw(deck.Deck.extraerR());
			//hand.handgui[pos-1].addMouseListener(this);					 //DE HAND A FIELD

			deck.textField.setText("cards left "+ deck.Deck.cardsLeft());
			deck.textField.repaint();

			repaint();

		}

		for (int i=0;i<5;i++)
			//barriers.barriers[i].addMouseListener(this);				//DE BARRIERS A HAND
		fallen=new Fallen();
		
		add(fallen);
		
		juego();
		
		JLabel a= new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
		a.setBounds(850, 80, 50, 110);
		a.setVisible(true);
		add(a);
		try{
			turno = new FileReader(new File("turno.txt"));
			br= new BufferedReader(turno);
			this.turn = Integer.parseInt(br.readLine());
			turno.close();
		}catch(Exception e2){ 
            e2.printStackTrace();
         }
	}
	
	//este sera nuestro manejador de juego, aca estara todas las condiciones y cosas de las phases
	public void juego()
	{
		/*this.changePhase = new JButton("cambiar");
		this.changePhase.setBounds(450, 80, 90, 20);
		this.changePhase.setVisible(true);
		this.add(this.changePhase);
		this.changePhase.addActionListener(this);*/
		this.phases.draw.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==deck.btnNewButton_1)
		{
				
				fallen.setVisible(true);
			moveToFront(fallen);

		}
		
		
		if((e.getSource()==changePhase)||(e.getSource()==phases.setup)||(e.getSource()==phases.draw)||(e.getSource()==phases.action)||(e.getSource()==phases.attack)||(e.getSource()==phases.end)){
			System.out.println(turn);
			
			if(phases.actual<4)
				phases.change(phases.actual+1);
			else
				phases.change(0);
			
			switch(phases.actual){
				//setup
				case 0:
					this.phases.setup.removeActionListener(this);
					this.phases.draw.addActionListener(this);
					
					//enable deck
					//disable barriers
					for (int i=0;i<5;i++)
						barriers.barriers[i].removeMouseListener(this);
					//disable hand
					for(int i=0;i<5;i++)
						hand.handgui[i].removeMouseListener(this);
					//disable field
					//disable battle phase
					//disable end turn
				break;
				//draw
				case 1:
					this.phases.draw.removeActionListener(this);
					this.phases.action.addActionListener(this);
					
					//disable deck: done
					//enable barriers
					for (int i=0;i<5;i++)
						barriers.barriers[i].addMouseListener(this);
					//disable hand
					for(int i=0;i<5;i++)
						hand.handgui[i].removeMouseListener(this);
					//disable field
					//disable battle phase
					//disable end turn
				break;
				//action
				case 2:
					this.phases.action.removeActionListener(this);
					this.phases.attack.addActionListener(this);
					
					//disable deck: done
					//disable barriers
					for (int i=0;i<5;i++)
						barriers.barriers[i].removeMouseListener(this);
					//enable hand
					for(int i=0;i<5;i++)
						hand.handgui[i].addMouseListener(this);
					//enable field
					//disable battle phase
					//disable end turn
				break;
				//attack
				case 3:
					this.phases.attack.removeActionListener(this);
					this.phases.end.addActionListener(this);
					
					//disable deck: done
					//disable barriers
					for (int i=0;i<5;i++)
						barriers.barriers[i].removeMouseListener(this);
					//disable hand
					for(int i=0;i<5;i++)
						hand.handgui[i].removeMouseListener(this);
					//enable field
					//enable battle phase
					//disable end turn
					
					
					
				break;
				//end turn
				case 4:
					this.phases.end.removeActionListener(this);
					this.phases.setup.addActionListener(this);
					
					//disable deck: done
					//disable barriers
					for (int i=0;i<5;i++)
						barriers.barriers[i].removeMouseListener(this);
					//disable hand
					for(int i=0;i<5;i++)
						hand.handgui[i].removeMouseListener(this);
					//disable field
					//disable battle phase
					//enable end turn
				break;
				
			}
			
			repaint();
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
		{if(e.getClickCount()==1)
		{

			if(e.getSource()==barriers.barriers[0])
			{
				int pos= hand.draw(barriers.cards[0]);
				hand.handgui[pos-1].addMouseListener(this);
				barriers.removebarrier(0);
				repaint();
			}
			if(e.getSource()==barriers.barriers[1])
			{
				int pos= hand.draw(barriers.cards[1]);
				hand.handgui[pos-1].addMouseListener(this);
				barriers.removebarrier(1);
				repaint();
			}
			if(e.getSource()==barriers.barriers[2])
			{
				int pos= hand.draw(barriers.cards[2]);
				hand.handgui[pos-1].addMouseListener(this);
				barriers.removebarrier(2);
				repaint();
			}
			if(e.getSource()==barriers.barriers[3])
			{
				int pos= hand.draw(barriers.cards[3]);
				hand.handgui[pos-1].addMouseListener(this);
				barriers.removebarrier(3);
				repaint();
			}
			if(e.getSource()==barriers.barriers[4])
			{
				int pos= hand.draw(barriers.cards[4]);
				hand.handgui[pos-1].addMouseListener(this);
				barriers.removebarrier(4);
				repaint();
			}
		}
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

					
					SmallCard carta;
					Reverse volteada;
					try {
						Random randomGenerator = new Random();
						int test=randomGenerator.nextInt(10);
						if(test % 2==0)
						{
							carta = new SmallCard(true,hand.handgui[acampo].getcard());
							volteada=new Reverse(true,hand.handgui[acampo].getcard());
						}else{
							carta = new SmallCard(false,hand.handgui[acampo].getcard());
							volteada=new Reverse(false,hand.handgui[acampo].getcard());
						}
						powers.undrain(hand.handgui[acampo].getcard().GetCost());
					
						repaint();
						carta.addMouseListener(this);
						volteada.addMouseListener(this);
						

						field.poner(carta,where);
						hand.discard(acampo+1);
						//ai.aifield.poner(volteada, where);
						repaint();

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
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),hand.cards[0]);
	
					hand.discard(1);
					
				}
				if(e.getSource()==hand.handgui[1])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),hand.cards[1]);
					
					hand.discard(2);
				}
				if(e.getSource()==hand.handgui[2])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),hand.cards[2]);
					hand.discard(3);
				
				}
				if(e.getSource()==hand.handgui[3])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),hand.cards[3]);
					hand.discard(4);
					
				}
				if(e.getSource()==hand.handgui[4])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),hand.cards[4]);
					hand.discard(5);
					
				}

				if(e.getSource()==field.cards[0])
				{
					powers.drain(field.cards[0].getcard().GetCost());
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),field.cards[0].getcard());
					field.quitar(0);
				}	
				if(e.getSource()==field.cards[1])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),field.cards[1].getcard());
					powers.drain(field.cards[1].getcard().GetCost());
					field.quitar(1);
				}
				if(e.getSource()==field.cards[2])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),field.cards[2].getcard());
					powers.drain(field.cards[2].getcard().GetCost());
					field.quitar(2);
				}
				if(e.getSource()==field.cards[3])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),field.cards[3].getcard());
					powers.drain(field.cards[3].getcard().GetCost());
					field.quitar(3);
					
				}if(e.getSource()==field.cards[4])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),field.cards[4].getcard());
					
					powers.drain(field.cards[4].getcard().GetCost());
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
			preview.Remove();
		}
		else if(e.getSource()==hand.handgui[1])
		{
			hand.handgui[1].setBounds(124, 20, 124, 186);
			preview.Remove();
		}
		else if(e.getSource()==hand.handgui[2])
		{
			hand.handgui[2].setBounds(248, 20, 124, 186);
			preview.Remove();
		}
		else if(e.getSource()==hand.handgui[3])
		{
			hand.handgui[3].setBounds(372, 20, 124, 186);
			preview.Remove();
		}else if(e.getSource()==hand.handgui[4])
		{
			hand.handgui[4].setBounds(496, 20, 124, 186);
			preview.Remove();
		}
		if(e.getSource()==field.cards[0])
		{
			
			preview.Remove();
		}
		if(e.getSource()==field.cards[1])
		{
			
			preview.Remove();
		}
		if(e.getSource()==field.cards[2])
		{
			
			preview.Remove();
		}
		if(e.getSource()==field.cards[3])
		{
			
			preview.Remove();
		}
		if(e.getSource()==field.cards[4])
		{
			
			preview.Remove();
		}
		if(e.getSource()==ai.aifield.cards[0])
		{
			preview.Remove();
			
		}
		if(e.getSource()==ai.aifield.cards[1]||e.getSource()==ai.aifield.cards[2]|| e.getSource()==ai.aifield.cards[3]||e.getSource()==ai.aifield.cards[4] )
		{
			preview.Remove();
			
		}
	}



	public void mouseMoved(MouseEvent e) {

	}


	public void mouseEntered(MouseEvent e) 
	
	{
		if(e.getSource()==hand.handgui[0])
		{
			hand.handgui[0].setBounds(0, 0, 124, 186);
			preview.addCard(new BigCard(hand.handgui[0].getcard(),0,0));
		}
		else if(e.getSource()==hand.handgui[1])
		{
			hand.handgui[1].setBounds(124, 0, 124, 186);
			preview.addCard(new BigCard(hand.handgui[1].getcard(),0,0));
		}
		else if(e.getSource()==hand.handgui[2])
		{
			hand.handgui[2].setBounds(248, 0, 124, 186);
			preview.addCard(new BigCard(hand.handgui[2].getcard(),0,0));
		}
		else if(e.getSource()==hand.handgui[3])
		{
			hand.handgui[3].setBounds(372, 0, 124, 186);
			preview.addCard(new BigCard(hand.handgui[0].getcard(),0,0));
		}else if(e.getSource()==hand.handgui[4])
		{
			hand.handgui[4].setBounds(496, 0, 124, 186);
			preview.addCard(new BigCard(hand.handgui[4].getcard(),0,0));
		}
		
		if(e.getSource()==field.cards[0])
		{
			
			preview.addCard(new BigCard(field.cards[0].getcard(),0,0));
		}
		if(e.getSource()==field.cards[1])
		{
			
			preview.addCard(new BigCard(field.cards[1].getcard(),0,0));
		}if(e.getSource()==field.cards[2])
		{
			
			preview.addCard(new BigCard(field.cards[2].getcard(),0,0));
		}if(e.getSource()==field.cards[3])
		{
			
			preview.addCard(new BigCard(field.cards[3].getcard(),0,0));
		}if(e.getSource()==field.cards[4])
		{
			
			preview.addCard(new BigCard(field.cards[4].getcard(),0,0));
		}
		if(e.getSource()==ai.aifield.cards[0])
		{
			preview.addCard(new BigCard(ai.aifield.cards[0].getcard(),0,0));
		
			
		}
		if(e.getSource()==ai.aifield.cards[1])
		{
			preview.addCard(new BigCard(ai.aifield.cards[1].getcard(),0,0));
			
			
		}
		if(e.getSource()==ai.aifield.cards[2])
		{
			preview.addCard(new BigCard(ai.aifield.cards[2].getcard(),0,0));
			
			
		}
		if(e.getSource()==ai.aifield.cards[3])
		{
			preview.addCard(new BigCard(ai.aifield.cards[3].getcard(),0,0));
			
			
		}
		if(e.getSource()==ai.aifield.cards[4])
		{
			preview.addCard(new BigCard(ai.aifield.cards[4].getcard(),0,0));
			
			
		}
	}
	

}
