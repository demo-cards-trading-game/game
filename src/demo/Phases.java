package demo;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Phases extends JPanel implements MouseListener {
	public final JPanel panel_4 = new JPanel();
	public int actual;
	public JPanel panel, panel_1, panel_2, panel_3, panel_5;
	public JLabel setup, draw, action, attack, end, reaction;

	public Phases(int x,int y) throws IOException{
		actual=0;
		setOpaque(false);
		setBounds(x,y-30,600,80);
		setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		panel_1.setBounds(55, 35, 70, 40);
		draw=new JLabel(new ImageIcon(ImageIO.read(new File("draw11.png"))));
		draw.setBounds(0, 0, 70, 40);
		panel_1.add(draw);
		add(panel_1);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setOpaque(false);
		panel_2.setBounds(155, 35, 70, 40);
		action=new JLabel(new ImageIcon(ImageIO.read(new File("action2.png"))));
		action.setBounds(0, 0, 70, 40);
		panel_2.add(action);
		add(panel_2);

		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setOpaque(false);
		panel_3.setBounds(249, 35, 70, 40);
		attack=new JLabel(new ImageIcon(ImageIO.read(new File("attack2.png"))));
		attack.setBounds(0, 0, 70, 40);
		panel_3.add(attack);
		add(panel_3);

		panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setOpaque(false);
		panel_5.setBounds(349, 35, 70, 40);
		reaction=new JLabel(new ImageIcon(ImageIO.read(new File("reAction2.png"))));
		reaction.setBounds(0, 0, 70, 40);
		panel_5.add(reaction);
		add(panel_5);

		panel_4.setBounds(449, 35, 70, 40);
		panel_4.setLayout(null);
		panel_4.setOpaque(false);
		end=new JLabel(new ImageIcon(ImageIO.read(new File("end2.png"))));
		end.setBounds(0, 0, 70, 40);
		panel_4.add(end);
		add(panel_4);

		change(0);
	}
	public void change(int phase){
		actual=phase;
		switch(actual){
			case 0:
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
