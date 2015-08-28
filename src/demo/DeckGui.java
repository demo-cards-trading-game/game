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
		setBounds(x, y, 250, 340);
		setOpaque(false);
		setLayout(null);
		
		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(new Color(139, 69, 19));
		btnNewButton.setOpaque(false);
		btnNewButton.setIcon(new ImageIcon("draw.JPG"));
		btnNewButton.setBounds(160, 235, 80, 77);
		add(btnNewButton);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		textField.setText("cards left "+ Deck.cardsLeft());
		textField.setBackground(new Color(139, 69, 19));
		textField.setBounds(160, 309, 80, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblDeck = new JLabel("DECK");
		lblDeck.setForeground(new Color(50, 205, 50));
		lblDeck.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblDeck.setBounds(170, 210, 53, 14);
		add(lblDeck);
		
		JButton btnNewButton_1 = new JButton();
		btnNewButton_1.setIcon(new ImageIcon("C:\\Documents and Settings\\Administrador\\git\\game\\Fallen.JPG"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(160, 122, 80, 77);
		add(btnNewButton_1);
		
		JLabel lblTheFallen = new JLabel("The Fallen");
		lblTheFallen.setForeground(new Color(30, 144, 255));
		lblTheFallen.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblTheFallen.setBounds(160, 108, 80, 14);
		add(lblTheFallen);
		
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setOpaque(true);
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setIcon(new ImageIcon("C:\\Documents and Settings\\Administrador\\git\\game\\Forgotten.JPG"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2.setBounds(160, 21, 80, 77);
		add(btnNewButton_2);
		
		JLabel lblForgotten = new JLabel("Forgotten");
		lblForgotten.setBorder(null);
		lblForgotten.setForeground(new Color(153, 102, 255));
		lblForgotten.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 12));
		lblForgotten.setBounds(158, 0, 117, 14);
		add(lblForgotten);

	}
}
