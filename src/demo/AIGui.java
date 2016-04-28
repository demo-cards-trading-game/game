package demo;
import javax.swing.*;

public class AIGui extends JPanel {
	/**
	 * Create the panel.
	 */
	public fieldGui aifield;
	public AiDeck aideck;
	public AiHand aihand;
	public Barriers2 barriers;
	public Drained_2 aidra;
	public int whereInvoqued;
	public AIGui()
	{
		setBounds(0,0,1024,600);
		this.setLayout(null);
		aifield=new fieldGui(225,197);
		barriers=new Barriers2(180,140);
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

}