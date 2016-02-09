package demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class PlayerGui extends JLayeredPane implements ActionListener,MouseListener {
	public HandGui hand;
	public DeckGui pdeck;
	public Drained powers;
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
		hand.setLocation(149, 510);
		powers=new Drained(15,350,name);
		barriers =new Barriers(179,500);
		
		/************añade atributos***********/
		add(powers);
		this.add(hand);
		this.add(field);
		add(barriers);
		pdeck = new DeckGui(0,0);
		pdeck.setSize(250, 343);
		pdeck.setLocation(770, 361);
		this.add(pdeck);
		pdeck.Deck.lista.Data.imprimir();
		try {
			pdeck.addhero(pdeck.Deck.lista.Data.Consultar(8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
