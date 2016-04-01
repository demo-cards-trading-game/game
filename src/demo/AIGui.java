package demo;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AIGui extends JPanel {

	/**
	 * Create the panel.
	 */
	public fieldGui aifield;
	public AiDeck aideck;
	public AiHand aihand;
	public int warriorPlayed;
	public Barriers2 barriers;
	public Drained_2 aidra;

	public int where, whereInvoqued;
	public AIGui()
	{
		setBounds(0,0,1024,600);
		this.setLayout(null);
		aifield=new fieldGui(220,150);
		barriers=new Barriers2(220,100);
		aideck=new AiDeck(10,10);
		aihand=new AiHand(278,-10);
	
		add(aihand);
		add(aideck);
		add (aifield);
		add(barriers);
		setOpaque(false);
		aidra=new Drained_2(860,0,"AI PLAYER");//mover al ai 
		add(aidra);
		repaint();
		for (int i=1;i<=5;i++)
		{
			aihand.draw(aideck.Deck.extraerR());
			barriers.addbarrier(aideck.Deck.extraerR());
		}
	}

	void aiPlay(int pos) throws IOException
	{
		whereInvoqued=-1;
		where = aifield.findwhere();// busca en donde poner la carta el ai
		if(where!=-1)
		{
			SmallCard carta;
		
			carta = new Reverse(false,aihand.handgui[pos].GetCard());
			if(aidra.currentundrained - aihand.handgui[pos].GetCard().GetCost()>0){
				aifield.poner(carta, where);
				aihand.discard(pos);
				whereInvoqued=where;
			}
			repaint();
		}
	}
	
	void smartPlay() throws IOException
	{
		
		int which ;
		which=aihand.findwarrior();
		
		if(which!=-1)
		{
		
			aiPlay(which);
			
		}else{
			
			which=aihand.finddisruption();
			if(which!=-1)
			{
				
				aiPlay(which);
			}else
			{
			
				aiPlay(0);
			}
			
		}
		
	}
	
}
