package demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
if(e.getSource()==powers.undrained[0])
			
		{
			
			
			if(powers.undrained[0].getY()==10)
			{
				powers.paying++;
				powers.undrained[0].setBounds(0,0,25,20);
				powers.label.setText(""+powers.paying);
			}else
			{
				powers.paying--;
				powers.undrained[0].setBounds(0,10,25,20);
				powers.label.setText(""+powers.paying);
			}
		}
		
		if(e.getSource()==powers.undrained[1])
		
		{
			
			
			if(powers.undrained[1].getY()==10)
			{
				powers.paying++;
				powers.label.setText(""+powers.paying);
				powers.undrained[1].setBounds(30,0,25,20);
			}else
			{
			
				powers.paying--;
				powers.label.setText(""+powers.paying);
				powers.undrained[1].setBounds(30,10,25,20);
			}
		}
		repaint();
		powers.panel.repaint();
		setVisible(true);
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
