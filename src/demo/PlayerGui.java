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
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.SwingConstants;
import javax.swing.JSlider;
import java.awt.Canvas;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextPane;

public class PlayerGui extends JPanel {
	DeckGui deck;
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
	private JPanel pene;
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
	public PlayerGui(int x , int y) {
		setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(204, 153, 0), new Color(153, 102, 102)));
		setBackground(UIManager.getColor("Button.disabledShadow"));
		deck = new DeckGui(0,0);
		deck.setSize(250, 343);
		hand= new HandGui (0,0);
		hand.setLocation(169, 414);
		deck.setLocation(760, 246);
		setOpaque(false);
		setLayout(null);
		setBounds(x,y, 1020, 600);
		this.add(deck);
		this.add(hand);
		
		JPanel Verde=new JPanel();
		Verde.setBackground(Color.GREEN);
		
		
		
		/*******************************************/
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 102, 0));
		panel.setForeground(new Color(0, 102, 0));
		panel.setBounds(170, 380, 97, 35);
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
		panel_1.setBounds(293, 380, 100, 35);
		add(panel_1);
		
		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setForeground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_2.setBackground(new Color(102, 153, 204));
		panel_2.setBounds(420, 380, 88, 35);
		add(panel_2);
		
		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setForeground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_3.setBackground(new Color(102, 153, 153));
		panel_3.setBounds(536, 380, 100, 35);
		add(panel_3);
		
		panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setForeground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_4.setBackground(new Color(204, 0, 102));
		panel_4.setBounds(663, 380, 97, 35);
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
		field.setBounds(284, 198, 114, 154);
		add(field);
		
		flecha = new JPanel();
		try {
		    flecha.add(new JLabel(new ImageIcon(ImageIO.read(new File("flecha.png")))));
	     } 
	   catch (IOException e) {
	      e.printStackTrace();
	     }
		flecha.setOpaque(false);
		flecha.setBounds(580, 152, 51, 29);
		add(flecha);
		
		panel_5 = new JPanel();
		panel_5.setBounds(171, 152, 51, 35);
		panel_6 = new JPanel();
		panel_6.setBounds(258, 152, 51, 29);
		panel_7 = new JPanel();
		panel_7.setBounds(335, 152, 51, 29);
		panel_8 = new JPanel();
		panel_8.setBounds(420, 152, 51, 29);
		panel_9 = new JPanel();
		panel_9.setBounds(498, 152, 51, 29);
		
		try {
		  
			
		
		    
		    panel_5.add(new JLabel(new ImageIcon(ImageIO.read(new File("hex.png")))));
		    panel_6.add(new JLabel(new ImageIcon(ImageIO.read(new File("hex.png")))));
		    panel_7.add(new JLabel(new ImageIcon(ImageIO.read(new File("hex.png")))));
		    panel_8.add(new JLabel(new ImageIcon(ImageIO.read(new File("hex.png")))));
		    panel_9.add(new JLabel(new ImageIcon(ImageIO.read(new File("hex.png")))));
		    
	     } 
	   catch (IOException e) {
	      e.printStackTrace();
	     }
		panel_5.setOpaque(false);
		panel_6.setOpaque(false);
		panel_7.setOpaque(false);
		panel_8.setOpaque(false);
		panel_9.setOpaque(false);
		add(panel_5);
		add(panel_6);
		add(panel_7);
		add(panel_8);
		add(panel_9);
		
		panel_10 = new JPanel();
		panel_10.setBorder(new LineBorder(new Color(102, 0, 0), 4));
		panel_10.setBackground(new Color(204, 153, 51));
		panel_10.setBounds(408, 198, 110, 154);
		add(panel_10);
		
		panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(102, 0, 0), 4));
		panel_11.setBackground(new Color(204, 153, 51));
		panel_11.setBounds(528, 198, 108, 154);
		add(panel_11);
		
		panel_12 = new JPanel();
		panel_12.setBorder(new LineBorder(new Color(102, 0, 0), 4));
		panel_12.setBackground(new Color(204, 153, 51));
		panel_12.setBounds(640, 198, 110, 154);
		add(panel_12);
		
		panel_13 = new JPanel();
		panel_13.setBounds(143, 198, 124, 154);
		add(panel_13);
		panel_13.setBorder(new LineBorder(new Color(102, 0, 0), 4));
		panel_13.setBackground(new Color(204, 153, 51));
		
	
	
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
}
	

	

