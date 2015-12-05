package demo;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import data.LoadData;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import demo.deck;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.JTextPane;
public class DeckGui extends JPanel {
	public JTextField textField;

	public deck Deck;
	public LoadData loadData;
	private JTextField txtHero;
	private JTextField txtCharacter;
	private JTextField txtSection;
	public JButton btnNewButton; 
	public JButton btnNewButton_1;
	public JButton btnNewButton_2;
	public JLabel lblTheFallen;
	public JPanel panel;
	public JLabel lblDeck; 
	public JLabel lblForgotten ;
	public void addhero(Card x) throws IOException
	{
		panel.add(new SmallCard(true,x));
		repaint();
		
	}
	public DeckGui(int x,int y) {
		this.Deck=new deck();
		 
		 try {
			Deck.Load("resources/siren.in");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			

		 
		setBackground(new Color(255, 165, 0));
		setBounds(x, y, 250, 340);
		setOpaque(false);
		setLayout(null);
		
		btnNewButton = new JButton();
		btnNewButton.setBackground(new Color(139, 69, 19));
		btnNewButton.setOpaque(false);
		btnNewButton.setIcon(new ImageIcon("draw.JPG"));
		btnNewButton.setBounds(175, 269, 46, 40);
		add(btnNewButton);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		textField.setText("Cards left "+ Deck.cardsLeft());
		textField.setBackground(new Color(139, 69, 19));
		textField.setBounds(160, 320, 80, 20);
		add(textField);
		textField.setColumns(10);
		
	 lblDeck = new JLabel("DECK");
		lblDeck.setForeground(new Color(50, 205, 50));
		lblDeck.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblDeck.setBounds(187, 244, 53, 14);
		add(lblDeck);
		
		btnNewButton_1 = new JButton();
		
		btnNewButton_1.setIcon(new ImageIcon("Fallen.JPG"));
		
		btnNewButton_1.setBounds(175, 193, 46, 40);
		add(btnNewButton_1);
		
	    lblTheFallen = new JLabel("The Fallen");
		lblTheFallen.setForeground(new Color(30, 144, 255));
		lblTheFallen.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblTheFallen.setBounds(160, 168, 80, 14);
		add(lblTheFallen);
		
		btnNewButton_2 = new JButton();
	
		
		btnNewButton_2.setIcon(new ImageIcon("Forgotten.JPG"));
	
		btnNewButton_2.setBounds(175, 117, 46, 40);
		add(btnNewButton_2);
		
	  lblForgotten = new JLabel("Forgotten");
		lblForgotten.setBorder(null);
		lblForgotten.setForeground(new Color(153, 102, 255));
		lblForgotten.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblForgotten.setBounds(160, 99, 117, 14);
		add(lblForgotten);
		
		 panel= new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		
		
		panel.setLayout(null);
		panel.setBackground(new Color(204, 153, 51));
		panel.setBounds(41, 168, 100, 145);
		add(panel);
	
		
		
		
	
	}
	
}
