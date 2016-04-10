package demo;
import extra.RoundedPanel;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class DeckGui extends JPanel {
	public JTextField textField;
	public deck Deck;
	public JLabel btnNewButton;
	public JLabel btnNewButton_1;
	public JLabel btnNewButton_2;
	public JLabel lblTheFallen;
	public JLayeredPane panel;
	public JLabel lblDeck; 
	public JLabel lblForgotten ;
	public JInternalFrame menu;
	public SmallCard Hero;
	public JButton Play, Preview;
	
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
		
		btnNewButton = new JLabel("");
		btnNewButton.setIcon(new ImageIcon("draw1.png"));
		btnNewButton.setBounds(175, 269, 46, 40);
		add(btnNewButton);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 10));
		textField.setText("Cards Left "+ Deck.cardsLeft());
		textField.setBackground(Color.BLACK);
		textField.setBounds(149, 320, 91, 20);
		add(textField);
		textField.setColumns(10);
		
		lblDeck = new JLabel("Deck");
		lblDeck.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeck.setForeground(new Color(50, 205, 50));
		lblDeck.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 15));
		lblDeck.setBounds(168, 244, 53, 14);
		add(lblDeck);
		
		btnNewButton_1 = new JLabel();
		btnNewButton_1.setIcon(new ImageIcon("fallen1.png"));
		btnNewButton_1.setBounds(175, 193, 46, 40);
		add(btnNewButton_1);
		
		lblTheFallen = new JLabel("The Fallen");
		lblTheFallen.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheFallen.setForeground(new Color(30, 144, 255));
		lblTheFallen.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 15));
		lblTheFallen.setBounds(140, 168, 100, 14);
		add(lblTheFallen);
		
		btnNewButton_2 = new JLabel();
		btnNewButton_2.setIcon(new ImageIcon("forgotten1.png"));
		btnNewButton_2.setBounds(175, 117, 46, 40);
		add(btnNewButton_2);
		
		lblForgotten = new JLabel("Forgotten");
		lblForgotten.setForeground(new Color(0x8888ff));
		lblForgotten.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 15));
		lblForgotten.setForeground(new Color(153, 102, 255));
		lblForgotten.setBounds(149, 92, 117, 14);
		add(lblForgotten);
		
		panel= new JLayeredPane();
		panel.add(new RoundedPanel());
		panel.setLayout(null);
		panel.setBackground(new Color(204, 153, 0));
		panel.setBounds(39, 195, 100, 145);
		add(panel);
	}

	public void addhero(Card x) throws IOException
	{
		Hero=new SmallCard(false,x);	
		panel.add(Hero);
		menu = new JInternalFrame();
		menu.getContentPane().setLayout(null);
		repaint();
		Play = new JButton("Play");
		Play.setBounds(5, 11, 80, 23);
		Play.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 9));
		menu.getContentPane().add(Play);
		Preview = new JButton("Preview");
		Preview.setBounds(5, 59, 80, 23);
		menu.getContentPane().add(Preview);
		Preview.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 10));
		menu.setClosable(true);
		menu.setBounds(0,0,100,145);
		menu.setBackground(Color.ORANGE);
		menu.setVisible(false);
		panel.add(menu);
		menu.setOpaque(true);
	}
}