package demo;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.util.Random;
public class fieldGui extends JPanel {
	public JButton btn;
	
	public fieldGui(int posx,int posy) {
		
		setOpaque(false);
		setBounds(posx,posy, 620, 186);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel.setBackground(new Color(204, 204, 204));
		panel.setBounds(305, 505, 100, 35);
		add(panel);
		
		JPanel panel2 = new JPanel();
		panel2.setOpaque(true);
		panel2.setLayout(null);
		panel2.setForeground(Color.WHITE);
		panel2.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel2.setBackground(new Color(204, 204, 204));
		panel2.setBounds(305, 505, 100, 35);
		add(panel2);
		
		JPanel panel3 = new JPanel();
		panel3.setOpaque(true);
		panel3.setLayout(null);
		panel3.setForeground(Color.WHITE);
		panel3.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel3.setBackground(new Color(204, 204, 204));
		panel3.setBounds(305, 505, 100, 35);
		add(panel3);
		
		JPanel panel4 = new JPanel();
		panel4.setOpaque(true);
		panel4.setLayout(null);
		panel4.setForeground(Color.WHITE);
		panel4.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel4.setBackground(new Color(204, 204, 204));
		panel4.setBounds(305, 505, 100, 35);
		add(panel4);
		
		JPanel panel5 = new JPanel();
		panel5.setOpaque(true);
		panel5.setLayout(null);
		panel5.setForeground(Color.WHITE);
		panel5.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel5.setBackground(new Color(204, 204, 204));
		panel5.setBounds(305, 505, 100, 35);
		add(panel5);
			
	}
	 
}
