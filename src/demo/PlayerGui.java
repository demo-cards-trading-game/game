package demo;
import demo.HandGui;
import demo.DeckGui;
import demo.CardGui;
import javax.swing.JPanel;

public class PlayerGui extends JPanel {

	HandGui mano;
	DeckGui deck;
	
	public PlayerGui(int x , int y) {
	
		setOpaque(false);
		setLayout(null);
		setBounds(x,y, 780, 552);
		mano = new HandGui(0,0); 
		mano.setLocation(0, 130);
		add(mano);
	}

}
