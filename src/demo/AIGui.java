package demo;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AIGui extends JPanel {

	/**
	 * Create the panel.
	 */
	public fieldGui aifield;
	public AiDeck aideck;
	public AiHand aihand;
	public AIGui()
	{
		setBounds(0,0,1024,600);
		this.setLayout(null);
		aifield=new fieldGui(220,110);
	
		aideck=new AiDeck(10,10);
		aihand=new AiHand(278,-10);
		add(aihand);
		add(aideck);
		add (aifield);
		setOpaque(false);
		
		for (int i=1;i<=5;i++)
		{
			aihand.draw(aideck.Deck.extraerR());
			
		}
	}

}
