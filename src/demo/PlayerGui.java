package demo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

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
		field = new fieldGui(226,430);
		hand.setLocation(230,610 );
		powers=new Drained(15,350,name);
	
		barriers =new Barriers(179,490);
		
		/************a�ade atributos***********/
		add(powers);
		this.add(hand);
		this.add(field);
		//add(barriers);
		pdeck = new DeckGui(0,0);
		pdeck.setSize(250, 343);
		pdeck.setLocation(740, 425);
		this.add(pdeck);
		
		try {
			pdeck.addhero(pdeck.Deck.lista.Data.Consultar(8));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pdeck.panel.addMouseListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==1)
		{
			if(e.getSource()==pdeck.panel)
			{
				pdeck.menu.setVisible(true);
			}
		}
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
