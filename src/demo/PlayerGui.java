package demo;
import utils.GeneralConstants;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class PlayerGui extends JLayeredPane implements ActionListener,MouseListener, GeneralConstants {
	public HandGui hand;
	public DeckGui pdeck;
	public Drained powers;
	public Barriers barriers;
	public FieldGui field;

	public PlayerGui(int x , int y, String name) throws IOException {
		setBounds(x,y, 1024, 768);
		setOpaque(false);
		setLayout(null);
		
		hand= new HandGui (0,0);
		hand.setLocation(235,605 );
		add(hand);

		field = new FieldGui(226,430);
		add(field);

		powers=new Drained(15,350,name);
		add(powers);

		barriers =new Barriers(179,590, BARRIER_PLAYER_POSITION);
		add(barriers);

		pdeck = new DeckGui(0,0);
		pdeck.setSize(250, 343);
		pdeck.setLocation(662, 425);
		pdeck.addhero(pdeck.Deck.list.Data.Consultar(8));
		pdeck.panel.addMouseListener(this);
		add(pdeck);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount()==1){
			if(e.getSource()==pdeck.panel){
				pdeck.menu.setVisible(true);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}
