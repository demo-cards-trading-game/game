package demo;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class PlayerGui extends JLayeredPane {
	public HandGui hand;
	public DeckGui pdeck;
	public Drained_2 powers;
	public Barriers barriers;
	public fieldGui field;
	/**
	 * Create the panel.
	 */
	public PlayerGui(int x , int y, String name) 
	{
		/************graficos del panel*****************/
		setBounds(x,y, 1024, 768);
		setOpaque(false);
		setLayout(null);
		
		/*inicializacion de los atributos*/
		hand= new HandGui (0,0);
		field = new fieldGui(220,350);
		hand.setLocation(179, 510);
		powers=new Drained_2(15,570,name);
		pdeck = new DeckGui(0,0);
		pdeck.setSize(250, 343);
		pdeck.setLocation(770, 361);
		barriers =new Barriers(179,500);
		
		/************añade atributos***********/
		this.add(pdeck);
		add(powers);
		this.add(hand);
		this.add(field);
		add(barriers);
		
		
	}

	
	
}
