package demo;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class AIGui extends JPanel {

	public FieldGui aifield;
	public AiDeck aideck;
	public AiHand aihand;
	public Barriers2 barriers;
	public Drained_2 aidra;
	public int whereInvoqued;

	public AIGui() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		setBounds(0,0,1024,600);
		setLayout(null);
		setOpaque(false);

		aifield=new FieldGui(225,197);
		add(aifield);

		barriers=new Barriers2(180,140);
		add(barriers);

		aideck=new AiDeck(-70,10);
		add(aideck);

		aihand=new AiHand(278,-10);
		add(aihand);

		aidra=new Drained_2(860,0,"AI PLAYER");
		add(aidra);

		repaint();

		for (int i=1;i<=5;i++){
			aihand.draw(aideck.Deck.extraerR());
			barriers.addbarrier(aideck.Deck.extraerR());
		}
	}
}