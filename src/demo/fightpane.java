package demo;
import javax.swing.*;
import java.awt.*;

public class fightpane extends JLayeredPane{
	JTextPane txtpnVs;
	BigCard a1 ;
	BigCard a2;

	public fightpane() {
		setLayout(null);
		setVisible(false);
		this.setBounds(200,150,700,400);
		this.setOpaque(false);

		txtpnVs = new JTextPane();
		txtpnVs.setForeground(Color.WHITE);
		txtpnVs.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 32));
		txtpnVs.setText("VS");
		txtpnVs.setBounds(300, 128, 78, 58);
		txtpnVs.setOpaque(false);
	}
	
	public void addCards(BigCard p1 , BigCard p2) throws InterruptedException{
		a2=p2;
		a1=p1;
		
		if (p1.getcard().GetHp() > p2.getcard().GetHp()){
			add(p1);
			add(p2);
		} else{
			add(p2);
			add(p1);
		}
		
		p1.setLocation(60,15);
		p2.setLocation(360,15);
		add(txtpnVs);
		setVisible(true);
		Thread t = new Thread(() -> {
			int i = 0;

			timeThread(1000);

			while (60 + i <= 360 - i) {
				a1.setLocation(60 + i, 15);
				a2.setLocation(360 - i, 15);
				i++;
				timeThread(10);
			}

			timeThread(1000);
			setVisible(false);
			Remove();
		});
		t.start();
	}

	public void timeThread(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void Remove()
	{
		removeAll();
	}
}