package demo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Phases extends JPanel implements MouseListener {
	public final JPanel panel_4 = new JPanel();
//	public JLabel arrow;
	public int actual;
	public JPanel panel, panel_1, panel_2, panel_3, panel_5;
	public JLabel setup, draw, action, attack, end, reaction;
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public Phases(int x,int y) throws IOException
	{
		actual=0;
//		arrow=new JLabel(new ImageIcon(ImageIO.read(new File("flecha.png"))));
//		arrow.setBounds(0,0,22,21);
//		add(arrow);
		setOpaque(false);
		setBounds(x,y-30,600,80);
		setLayout(null);

		/*panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(49, 35, 70, 40);
		setup=new JLabel(new ImageIcon(ImageIO.read(new File("setup.png"))));
		setup.setBounds(0, 0, 70, 40);
		panel.add(setup);
		panel.setOpaque(false);
		add(panel);*/
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		draw=new JLabel(new ImageIcon(ImageIO.read(new File("draw11.png"))));
		draw.setBounds(0, 0, 70, 40);
		panel_1.add(draw);
		panel_1.setOpaque(false);
		panel_1.setBounds(55, 35, 70, 40);
//		panel_1.setBounds(149, 35, 70, 40);
		add(panel_1);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		action=new JLabel(new ImageIcon(ImageIO.read(new File("action2.png"))));
		action.setBounds(0, 0, 70, 40);
		panel_2.add(action);
		panel_2.setOpaque(false);
		panel_2.setBounds(155, 35, 70, 40);
//		panel_2.setBounds(249, 35, 70, 40);
		add(panel_2);

		panel_3 = new JPanel();
		panel_3.setLayout(null);
		attack=new JLabel(new ImageIcon(ImageIO.read(new File("attack2.png"))));
		attack.setBounds(0, 0, 70, 40);
		panel_3.add(attack);
		panel_3.setOpaque(false);
		panel_3.setBounds(249, 35, 70, 40);
//		panel_3.setBounds(349, 35, 70, 40);
		add(panel_3);

		panel_5 = new JPanel();
		panel_5.setLayout(null);
		reaction=new JLabel(new ImageIcon(ImageIO.read(new File("reAction2.png"))));
		reaction.setBounds(0, 0, 70, 40);
		panel_5.add(reaction);
		panel_5.setOpaque(false);
		panel_5.setBounds(349, 35, 70, 40);
		add(panel_5);

		panel_4.setBounds(449, 35, 70, 40);
		end=new JLabel(new ImageIcon(ImageIO.read(new File("end2.png"))));
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
			case 0:
//				draw.setIcon(new ImageIcon("draw.png"));
//				end.setIcon(new ImageIcon("end.png"));
				break;
		
			case 1:
				action.setIcon(new ImageIcon("action.png"));
				end.setIcon(new ImageIcon("end.png"));
				break;
			
			case 2:
				draw.setIcon(new ImageIcon("draw22.png"));
				attack.setIcon(new ImageIcon("attack.png"));
				break;

			case 3:
				action.setIcon(new ImageIcon("action2.png"));
				break;

			case 4:
				attack.setIcon(new ImageIcon("attack2.png"));
				end.setIcon(new ImageIcon("end2.png"));
				break;
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
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
