package demo;
import utils.GeneralConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Phases extends JPanel implements GeneralConstants{
//	public final JPanel panel_4 = new JPanel();
	public int actual;
//	public JPanel panel_1, panel_2, panel_3, panel_5;
//	public JLabel setup, draw, action, attack, end, reaction;

	public JPanel panels[];
	public JLabel labels[];
	public String imageNames[] = {"setup","draw11.png", "action2.png", "attack2.png", "reAction2.png", "end2.png"};

	public Phases(int x,int y) {
		actual=0;
		setOpaque(false);
		setBounds(x,y-30,600,80);
		setLayout(null);

		panels = new JPanel[6];
		labels = new JLabel[6];

/*
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
		add(panel_4);*/

//		panels[PHASES_SETUP] = getCustomPanel(new Rectangle(?,?,?,?), imageNames[PHASES_SETUP], PHASES_SETUP);
//		add(panels[PHASES_SETUP]);
		panels[PHASES_DRAW] = getCustomPanel(new Rectangle(55, 35, 70, 40), imageNames[PHASES_DRAW], PHASES_DRAW);
		add(panels[PHASES_DRAW]);
		panels[PHASES_ACTION] = getCustomPanel(new Rectangle(155, 35, 70, 40), imageNames[PHASES_ACTION], PHASES_ACTION);
		add(panels[PHASES_ACTION]);
		panels[PHASES_ATTACK] = getCustomPanel(new Rectangle(249, 35, 70, 40), imageNames[PHASES_ATTACK], PHASES_ATTACK);
		add(panels[PHASES_ATTACK]);
		panels[PHASES_REACTION] = getCustomPanel(new Rectangle(349, 35, 70, 40), imageNames[PHASES_REACTION], PHASES_REACTION);
		add(panels[PHASES_REACTION]);
		panels[PHASES_END] = getCustomPanel(new Rectangle(449, 35, 70, 40), imageNames[PHASES_END], PHASES_END);
		add(panels[PHASES_END]);

		changeTurn(PHASES_SETUP);
	}

	public JPanel getCustomPanel(Rectangle rectangle, String image, int posLabel){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		panel.setBounds(rectangle);
		try {
			labels[posLabel] = new JLabel(new ImageIcon(ImageIO.read(new File(image))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		labels[posLabel].setBounds(0,0,70,40);
		panel.add(labels[posLabel]);
		return panel;
	}

	public JLabel getLabel(int posLabel){
		return labels[posLabel];
	}

	public int getActualTurn(){
		return actual;
	}

	public void changeTurn(int phase){
		actual=phase;
		switch(actual){
			case 0:
				break;
		
			case 1:
				labels[PHASES_ACTION].setIcon(new ImageIcon("action.png"));
				labels[PHASES_END].setIcon(new ImageIcon("end.png"));
				break;

			case 2:
				labels[PHASES_DRAW].setIcon(new ImageIcon("draw22.png"));
				labels[PHASES_ATTACK].setIcon(new ImageIcon("attack.png"));
				break;

			case 3:
				labels[PHASES_ACTION].setIcon(new ImageIcon("action2.png"));
				break;

			case 4:
				labels[PHASES_ATTACK].setIcon(new ImageIcon("attack2.png"));
				labels[PHASES_END].setIcon(new ImageIcon("end2.png"));
				break;
		}
	}
}
