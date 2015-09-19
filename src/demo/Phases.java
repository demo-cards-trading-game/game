package demo;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Icon;

public class Phases extends JPanel {
	private final JPanel panel_4 = new JPanel();
	public JLabel arrow;
	int actual;
	

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public Phases(int x,int y) throws IOException
	{
		
		actual=0;
		arrow=new JLabel(new ImageIcon(ImageIO.read(new File("arrowr.png"))));
		arrow.setBounds(0,0,35,30);
		add(arrow);
		setOpaque(false);
		setBounds(x,y-30,539,80);
		setLayout(null);
		
	
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(10, 35, 70, 40);
		JLabel setup=new JLabel(new ImageIcon(ImageIO.read(new File("setup.png"))));
		setup.setBounds(0, 0, 70, 40);
		panel.add(setup);
		panel.setOpaque(false);
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		JLabel draw=new JLabel(new ImageIcon(ImageIO.read(new File("draw.png"))));
		draw.setBounds(0, 0, 70, 40);
		panel_1.add(draw);
		panel_1.setOpaque(false);
		panel_1.setBounds(113, 35, 70, 40);
		add(panel_1);
		

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		JLabel action=new JLabel(new ImageIcon(ImageIO.read(new File("action.png"))));
		action.setBounds(0, 0, 70, 40);
		panel_2.add(action);
		panel_2.setOpaque(false);
		panel_2.setBounds(229, 35, 70, 40);
		add(panel_2);
		

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		JLabel attack=new JLabel(new ImageIcon(ImageIO.read(new File("attack.png"))));
		attack.setBounds(0, 0, 70, 40);
		panel_3.add(attack);
		panel_3.setOpaque(false);
	
		panel_3.setBounds(343, 35, 70, 40);
		add(panel_3);
		panel_4.setBounds(459, 35, 70, 40);
		JLabel end=new JLabel(new ImageIcon(ImageIO.read(new File("end.png"))));
		end.setBounds(0, 0, 70, 40);
		panel_4.add(end);
		add(panel_4);
		panel_4.setLayout(null);
		panel_4.setOpaque(false);
		change(0);
		
	}
	public void change(int phase) //esta funcion mueve la flecha a el phase actual
	{
		actual=phase;
		switch(actual)
		{
		case 0:arrow.setLocation(27, 0);
			break;
		
		case 1:arrow.setLocation(130, 0);
			break;
			
		case 2:arrow.setLocation(245, 0);
			break;
		case 3:arrow.setLocation(360, 0);
			break;
		case 4:arrow.setLocation(475, 0);
		break;
		
		}
		
	}
}
