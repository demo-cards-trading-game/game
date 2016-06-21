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
	public JLayeredPane panel;
	public JInternalFrame menu;
	public SmallCard Hero;
	public JButton Play,Preview;
	
	public DeckGui(int x,int y) throws IOException {
		this.Deck=new deck();
		Deck.Load("resources/siren.in");

		setBackground(new Color(255, 165, 0));
		setBounds(x, y, 250, 340);
		setOpaque(false);
		setLayout(null);

		btnNewButton = new JLabel(new ImageIcon("draw1.png"));
		btnNewButton.setBounds(134, 206, 236, 160);
		add(btnNewButton);

		textField = new JTextField("Cards Left "+ Deck.cardsLeft());
		textField.setEditable(false);
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 10));
		textField.setBackground(Color.BLACK);
		textField.setBounds(149, 320, 70, 20);
		textField.setVisible(false);
		textField.setColumns(10);
		add(textField);

		btnNewButton_1 = new JLabel(new ImageIcon("fallen1.png"));
		btnNewButton_1.setBounds(135, 90, 236, 160);
		add(btnNewButton_1);

		btnNewButton_2 = new JLabel(new ImageIcon("forgotten1.png"));
		btnNewButton_2.setBounds(135, -26, 236, 160);
		add(btnNewButton_2);

		panel= new JLayeredPane();
		panel.add(new RoundedPanel());
		panel.setLayout(null);
		panel.setBackground(new Color(204, 153, 0));
		panel.setBounds(50, 195, 75, 145);
		add(panel);
	}

	public void addhero(Card x) throws IOException{
		Hero=new SmallCard(x,0,0);	
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
		Preview.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 10));
		menu.getContentPane().add(Preview);

		menu.setClosable(true);
		menu.setBounds(0,0,100,145);
		menu.setBackground(Color.ORANGE);
		menu.setVisible(false);
		menu.setOpaque(true);
		panel.add(menu);
	}
}