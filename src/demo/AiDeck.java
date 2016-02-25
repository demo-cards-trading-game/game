package demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import data.LoadData;
import extra.RoundedPanel;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AiDeck extends JPanel {

	Graphics2D g2d;
	public deck Deck;
	public LoadData loadData;
	private JTextField txtHero;
	private JTextField txtCharacter;
	private JTextField txtSection;
	public JLabel btnNewButton; 
	public JLabel btnNewButton_1;
	public JLabel btnNewButton_2;
	public JLabel lblTheFallen;
	public RoundedPanel panel;
	public JLabel lblDeck; 
	public JLabel lblForgotten ;
	public JTextField textField;
	public AiDeck(int x , int y) {
		
		setBackground(new Color(255, 165, 0));
		setBounds(x, y, 250, 340);
		setOpaque(false);
		setLayout(null);
		this.Deck = new deck();
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		textField.setText("Cards left "+ Deck.cardsLeft());
		textField.setBackground(new Color(139, 69, 19));
		textField.setBounds(160, 320, 80, 20);
		add(textField);
		textField.setColumns(10);
		btnNewButton = new JLabel();
//		btnNewButton.setBackground(new Color(139, 69, 19));
//		btnNewButton.setOpaque(false);
		btnNewButton.setIcon(new ImageIcon("draw4.png"));
		btnNewButton.setBounds(175, 269, 46, 40);
		add(btnNewButton);
		
		lblDeck = new JLabel("DECK");
		lblDeck.setForeground(new Color(50, 205, 50));
		lblDeck.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblDeck.setBounds(187, 244, 53, 14);
		add(lblDeck);
		lblTheFallen = new JLabel("The Fallen");
		lblTheFallen.setForeground(new Color(30, 144, 255));
		lblTheFallen.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblTheFallen.setBounds(160, 168, 80, 14);
		add(lblTheFallen);

		lblForgotten = new JLabel("Forgotten");
		lblForgotten.setBorder(null);
		lblForgotten.setForeground(new Color(153, 102, 255));
		lblForgotten.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblForgotten.setBounds(160, 99, 117, 14);
		add(lblForgotten);

		panel = new RoundedPanel();
		

		panel.setLayout(null);
		panel.setBackground(new Color(204, 153, 51));
		panel.setBounds(41, 168, 100, 145);
		add(panel);
//		Card c = new Card();
//		c.SetCardNumber(10);
//		c.SetClass("Siren");
//		c.SetCost(1);
//		c.SetDescription("hola");
//		c.SetHp(300);
//		c.SetMp(250);
//		c.SetId("SSD-05");
//		c.SetLimit(4);
//		c.SetName("Truce");
//		c.SetSource("Water");
//		c.SetType("Warrior");
//		c.SetSup(150);
//		for (int i = 0; i < 40; i++) {
//			Deck.insertar(c);
//		}
		 try {
			Deck.Load("resources/siren.in");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 btnNewButton_1 = new JLabel();
			
			btnNewButton_1.setIcon(new ImageIcon("fallen4.png"));
			
			btnNewButton_1.setBounds(175, 193, 46, 40);
			add(btnNewButton_1);
			btnNewButton_2 = new JLabel();
			
			
			btnNewButton_2.setIcon(new ImageIcon("forgotten4.png"));
		
			btnNewButton_2.setBounds(175, 117, 46, 40);
			add(btnNewButton_2);
			
			
		panel.setSize(53, 68);
		btnNewButton.setSize(43, 43);
		btnNewButton_2.setSize(43, 43);
		btnNewButton_1.setSize(43, 43);
	

		
		textField.setLocation(10, 242);
		lblTheFallen.setLocation(10, 11);
		lblForgotten.setLocation(10, 96);
		lblDeck.setLocation(20, 175);
		btnNewButton.setLocation(20, 188);
		btnNewButton_1.setLocation(20, 36);
		btnNewButton_2.setLocation(20, 121);
		panel.setLocation(116, 11);
		repaint();
		
	}
	 public void addhero(Card x) throws IOException
		{
			panel.add(new SmallCard(true,x));
			repaint();
			
		}
}
