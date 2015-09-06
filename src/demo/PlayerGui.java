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
import java.awt.geom.Ellipse2D;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

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


public class PlayerGui extends JPanel implements ActionListener {
	public DeckGui deck;
	HandGui hand;
	int turn;
	private JPanel[]  barriers   = new JPanel[5];
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblBarrier;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JPanel field;
	private JPanel flecha;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private LoadData cartas;
	private JInternalFrame pane; 
	
	public PlayerGui(int x , int y, String name) {
		setBorder(null);
		setBackground(UIManager.getColor("Button.disabledShadow"));
		hand= new HandGui (0,0);
		hand.setLocation(179, 530);
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
		
		
		
		panel_13 = new JPanel();
		panel_13.setBounds(179, 311, 114, 154);
		add(panel_13);
		panel_13.setBorder(new LineBorder(new Color(102, 0, 0), 4));
		panel_13.setBackground(new Color(204, 153, 51));
		
		
		this.add(hand);
		
		JPanel Verde=new JPanel();
		Verde.setBackground(Color.GREEN);
		
		
		
		/*******************************************/
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 0));
		panel.setForeground(new Color(0, 102, 0));
		panel.setBounds(179, 490, 97, 35);
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
		panel_1.setBounds(302, 490, 100, 35);
		add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_2.setBackground(new Color(102, 153, 204));
		panel_2.setBounds(428, 490, 88, 35);
		add(panel_2);
		
		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setForeground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_3.setBackground(new Color(102, 153, 153));
		panel_3.setBounds(553, 490, 100, 35);
		add(panel_3);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setForeground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_4.setBackground(new Color(204, 0, 102));
		panel_4.setBounds(676, 490, 97, 35);
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
		
		field = new JPanel();
		field.setBorder(new LineBorder(new Color(102, 0, 0), 4));
		field.setBackground(new Color(204, 153, 51));
		field.setBounds(303, 311, 114, 154);
		add(field);
		
		 flecha = new JPanel();
		 flecha.setBounds(507, 18, 75, 35);
		
		panel_5 = new JPanel();
		panel_5.setBounds(10, 18, 51, 35);
		panel_6 = new JPanel();
	    panel_6.setBounds(88, 18, 51, 35);
	    panel_7 = new JPanel();
		panel_7.setBounds(209, 18, 51, 35);
		panel_8 = new JPanel();
		panel_8.setBounds(307, 18, 51, 35);
		panel_9 = new JPanel();
		panel_9.setBounds(405, 18, 51, 35);
		
		try {
		    panel_5.add(new JLabel(new ImageIcon(ImageIO.read(new File("hex.png")))));
		    panel_6.add(new JLabel(new ImageIcon(ImageIO.read(new File("hex.png")))));
		    panel_7.add(new JLabel(new ImageIcon(ImageIO.read(new File("hex.png")))));
		    panel_8.add(new JLabel(new ImageIcon(ImageIO.read(new File("hex.png")))));
		    panel_9.add(new JLabel(new ImageIcon(ImageIO.read(new File("hex.png")))));
		    flecha.add(new JLabel(new ImageIcon(ImageIO.read(new File("flecha.png")))));
		    
	     } 
	   catch (IOException e) {
	      e.printStackTrace();
	     }
		
		panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(102, 0, 0), 4));
		panel_10.setBackground(new Color(204, 153, 51));
		panel_10.setBounds(425, 311, 110, 154);
		add(panel_10);
		
		panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(102, 0, 0), 4));
		panel_11.setBackground(new Color(204, 153, 51));
		panel_11.setBounds(545, 311, 108, 154);
		add(panel_11);
		
		panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(102, 0, 0), 4));
		panel_12.setBackground(new Color(204, 153, 51));
		panel_12.setBounds(663, 311, 110, 154);
		add(panel_12);
		
		deck = new DeckGui(0,0);
		deck.setSize(250, 343);
		deck.setLocation(770, 361);
		this.add(deck);
		
		JPanel turno = new JPanel();
		turno.setBounds(179, 236, 603, 64);
		add(turno);
		turno.setLayout(null);
		
		
		
		turno.add(panel_5);
		
			
		
		  
		  
		  panel_5.setOpaque(false);
		
		  turno.add(panel_6);
		  
		  panel_6.setOpaque(false);
		
		  turno.add(panel_7);
		  
		  panel_7.setOpaque(false);
		
		  turno.add(panel_8);
		 
		  panel_8.setOpaque(false);
		  
		  turno.add(panel_9);
		
		  
		  panel_9.setOpaque(false);
		  
		 
		  turno.add(flecha);
		 
		  flecha.setOpaque(false);
		  turno.setOpaque(false);
		  
		  deck.btnNewButton.addActionListener(this);
		  deck.btnNewButton_1.addActionListener(this);
		  
	
	}
	  public void actionPerformed(ActionEvent e) {
		  
		  if (e.getSource()==deck.btnNewButton)
		  {
			 
			 if(deck.Deck.cardsLeft()!= 0 )
			 {

			  hand.draw( deck.Deck.extraerR());
			 }
			 deck.textField.repaint();
			 deck.textField.setText("cards left "+ deck.Deck.cardsLeft());
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
	 
	public HandGui getHand() {
		return hand;
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
}
	

	

