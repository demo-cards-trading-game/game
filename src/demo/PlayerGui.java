package demo;
import demo.HandGui;
import demo.DeckGui;
import demo.CardGui;
import javax.swing.JPanel;

public class PlayerGui extends JPanel {
	DeckGui deck;
	HandGui hand;
	public PlayerGui(int x , int y) {
		deck = new DeckGui(0,0);
		deck.setSize(250, 343);
		hand= new HandGui (0,0);
		hand.setLocation(0, 366);
		deck.setLocation(550, 234);
		setOpaque(false);
		setLayout(null);
		setBounds(x,y, 829, 600);
		this.add(deck);
		this.add(hand);
	}

}
