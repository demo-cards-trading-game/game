package demo;
import extra.RoundedPanel;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AiDeck extends JPanel {

	public deck Deck;
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
		btnNewButton.setIcon(new ImageIcon("draw4.png"));
		btnNewButton.setBounds(175, 269, 46, 40);
		add(btnNewButton);
		
		lblDeck = new JLabel("DECK");
		lblDeck.setForeground(new Color(50, 205, 50));
		lblDeck.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblDeck.setBounds(187, 244, 53, 14);
//		add(lblDeck);
		lblTheFallen = new JLabel("The Fallen");
		lblTheFallen.setForeground(new Color(30, 144, 255));
		lblTheFallen.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblTheFallen.setBounds(160, 168, 80, 14);
//		add(lblTheFallen);

		lblForgotten = new JLabel("Forgotten");
		lblForgotten.setBorder(null);
		lblForgotten.setForeground(new Color(153, 102, 255));
		lblForgotten.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblForgotten.setBounds(160, 99, 117, 14);
//		add(lblForgotten);

		panel = new RoundedPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(204, 153, 51));
		panel.setBounds(41, 168, 100, 145);
		//add(panel);

		try {
			Deck.Load("resources/siren.in");
		} catch (IOException e) {
			e.printStackTrace();
		}
		btnNewButton_1 = new JLabel();
		btnNewButton_1.setIcon(new ImageIcon("fallen1.png"));
		btnNewButton_1.setBounds(55, 84, 236, 160);
		add(btnNewButton_1);
		btnNewButton_2 = new JLabel();
		btnNewButton_2.setIcon(new ImageIcon("forgotten4.png"));
		btnNewButton_2.setBounds(175, 117, 46, 40);
//		add(btnNewButton_2);

		panel.setSize(53, 68);
		btnNewButton.setSize(43, 43);
//		btnNewButton_2.setSize(43, 43);
//		btnNewButton_1.setSize(43, 43);
		textField.setLocation(50, 62);
		lblTheFallen.setLocation(10, 11);
		lblForgotten.setLocation(10, 96);
		lblDeck.setLocation(20, 175);
		btnNewButton.setLocation(20, 188);
//		btnNewButton_1.setLocation(20, 36);
//		btnNewButton_2.setLocation(20, 121);
		panel.setLocation(116, 11);
		repaint();
	}
}