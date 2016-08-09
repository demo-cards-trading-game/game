package demo;
import extra.RoundedPanel;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AiDeck extends JPanel {

	public deck Deck;
	public JLabel btnNewButton;
	public JLabel btnNewButton_1;
	public RoundedPanel panel;
	public JTextField textField;

	public AiDeck(int x , int y) throws IOException {
		setBackground(new Color(255, 165, 0));
		setBounds(x, y, 250, 340);
		setOpaque(false);
		setLayout(null);

		this.Deck = new deck();
		Deck.load("resources/siren.in");

		textField = new JTextField("Cards left "+ Deck.cardsLeft(), 10);
		textField.setEditable(false);
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		textField.setBackground(new Color(139, 69, 19));
		textField.setBounds(160, 320, 80, 20);
		textField.setLocation(50, 62);
		add(textField);

		btnNewButton = new JLabel(new ImageIcon("draw4.png"));
		btnNewButton.setBounds(175, 269, 46, 40);
		btnNewButton.setSize(43, 43);
		btnNewButton.setLocation(20, 188);
		add(btnNewButton);

		btnNewButton_1 = new JLabel(new ImageIcon("fallen1.png"));
		btnNewButton_1.setBounds(55, 84, 236, 160);
		add(btnNewButton_1);

		repaint();
	}
}