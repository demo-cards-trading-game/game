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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextPane;
public class DeckGui extends JPanel {
	private JTextField textField;

	public deck Deck;
	private JTextField txtHero;
	private JTextField txtCharacter;
	private JTextField txtSection;
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
		textField.setBounds(160, 320, 80, 20);
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
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
			}
		});
		panel.setBackground(new Color(204, 153, 51));
		panel.setBounds(41, 168, 109, 172);
		add(panel);
		panel.setLayout(null);
		
		txtHero = new JTextField();
		txtHero.setOpaque(false);
		txtHero.setForeground(new Color(255, 255, 255));
		txtHero.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		txtHero.setEditable(false);
		txtHero.setText("Hero");
		txtHero.setBounds(10, 53, 86, 20);
		panel.add(txtHero);
		
		txtHero.setBorder(null);
		txtHero.setColumns(10);
		
		txtCharacter = new JTextField();
		txtCharacter.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		txtCharacter.setForeground(new Color(255, 255, 255));
		txtCharacter.setEditable(false);
		txtCharacter.setText("character");
		txtCharacter.setColumns(10);
		txtCharacter.setBounds(10, 79, 86, 20);
		txtCharacter.setOpaque(false);
		txtCharacter.setBorder(null);
		panel.add(txtCharacter);
		
		txtSection = new JTextField();
		txtSection.setOpaque(false);
		txtSection.setBorder(null);
		txtSection.setForeground(new Color(255, 255, 255));
		txtSection.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		txtSection.setEditable(false);
		txtSection.setText("Section");
		txtSection.setColumns(10);
		txtSection.setBounds(10, 107, 86, 20);
		panel.add(txtSection);

	}
}
