package demo;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import demo.deck;
import java.awt.Font;
import javax.swing.JLabel;
public class DeckGui extends JPanel {
	private JTextField textField;

	public deck Deck;
	public DeckGui(int x,int y) {
		Deck=new deck();
		setBackground(new Color(255, 165, 0));
		setBounds(x, y, 250, 315);
		setOpaque(false);
		setLayout(null);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setBackground(new Color(139, 69, 19));
		btnNewButton.setOpaque(true);
		btnNewButton.setIcon(new ImageIcon("draw.JPG"));
		btnNewButton.setBounds(10, 189, 80, 115);
		add(btnNewButton);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		textField.setText("cards left "+ Deck.cardsLeft());
		textField.setBackground(new Color(139, 69, 19));
		textField.setBounds(10, 158, 80, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblDeck = new JLabel("DECK");
		lblDeck.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblDeck.setBounds(26, 134, 53, 14);
		add(lblDeck);
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setIcon(new ImageIcon("C:\\Documents and Settings\\Administrador\\git\\game\\Fallen.JPG"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(100, 227, 114, 77);
		add(btnNewButton_1);
		
		JLabel lblTheFallen = new JLabel("The Fallen");
		lblTheFallen.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblTheFallen.setBounds(122, 206, 80, 14);
		add(lblTheFallen);

	}
}
