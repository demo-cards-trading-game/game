package demo;
import utils.GeneralConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Phases extends JPanel implements GeneralConstants{
	public int actual;
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

// TODO: 26/7/2016 definir lo que se debe hacer con las fases restantes
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
		// TODO: 26/7/2016 definir comportamiento del cambio de phases
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
