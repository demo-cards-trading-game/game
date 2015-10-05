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
	public AIGui()
	{
		setBounds(0,0,1024,600);
		this.setLayout(null);
		aifield=new fieldGui(220,110);
		aifield.setLocation(228, 111);
		aideck=new AiDeck(10,10);
		add(aideck);
		add (aifield);
		setOpaque(false);
	}

}
