package extra;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RollDicePanel extends JPanel implements ActionListener{
	public JButton rollButton;
	public JLabel text = new JLabel("");
	public JLabel dice1;
	public JLabel dice2;
	private Random rg = new Random();
	public int count=25;
 
	RollDicePanel() throws IOException {
		this.setOpaque(false);
		setLayout(null);
		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

		dice1 = new JLabel(new ImageIcon(ImageIO.read(new File("d1.png"))));
		dice1.setBounds(-66, 51, 244, 244);
		this.add(dice1);

		dice2 = new JLabel(new ImageIcon(ImageIO.read(new File("d1.png"))));
		dice2.setBounds(542, 51, 244, 244);
		this.add(dice2);

		rollButton = new JButton("Let them roll");
		rollButton.setBounds(71, 317, 440, 41);
		rollButton.setFont(new Font("Sansserif", Font.PLAIN, 24));
		rollButton.addActionListener(this);
		this.add(rollButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		correr();
	}

	public void correr(){
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				if(count > 0){
					count --;
					int num1 = rg.nextInt(6);
					int num2 = rg.nextInt(6);
	     	         
					try {
						Icon icon1;
						Icon icon2;
						icon1 = new ImageIcon(ImageIO.read(new File("d"+(num1+1)+".png")));
						dice1.setIcon(icon1);
						icon2 = new ImageIcon(ImageIO.read(new File("d"+(num2+1)+".png")));
						dice2.setIcon(icon2);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	     	            
					if(num1>num2){
						text.setText("1");
					}else
					{
						if(num1<num2){
							text.setText("2");
						}
						else
						{
							text.setText("3");
						}
					}
				}
				else{
					this.cancel();
				}
			}
		}, 0, 100);
	}
}