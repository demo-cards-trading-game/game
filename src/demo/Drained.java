package demo;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;

public class Drained extends JLayeredPane {

	/**
	 * Create the panel.
	 */
	public Drained(int x , int y)
	{
		setBounds(x,y,150,384);
		setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_7.setBackground(new Color(51, 51, 204));
		panel_7.setBounds(89, 168, 38, 41);
		add(panel_7);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_6.setLayout(null);
		panel_6.setBackground(new Color(255, 153, 0));
		panel_6.setBounds(68, 152, 38, 41);
		add(panel_6);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_5.setBackground(new Color(153, 50, 204));
		panel_5.setBounds(41, 142, 38, 41);
		add(panel_5);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_15.setBackground(new Color(51, 51, 204));
		panel_15.setBounds(89, 325, 51, 59);
		add(panel_15);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_14.setLayout(null);
		panel_14.setBackground(new Color(255, 153, 0));
		panel_14.setBounds(89, 306, 51, 59);
		add(panel_14);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_13.setBackground(new Color(153, 50, 204));
		panel_13.setBounds(89, 285, 51, 59);
		add(panel_13);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_11.setBackground(new Color(51, 51, 204));
		panel_11.setBounds(20, 325, 51, 59);
		add(panel_11);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_10.setLayout(null);
		panel_10.setBackground(new Color(255, 153, 0));
		panel_10.setBounds(20, 306, 51, 59);
		add(panel_10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(51, 51, 204));
		panel_2.setBounds(89, 100, 38, 41);
		add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_3.setBackground(new Color(255, 153, 0));
		panel_3.setBounds(68, 78, 38, 41);
		add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UnDrained power");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 20, 106, 14);
		add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(new Color(153, 50, 204));
		panel_1.setBounds(41, 60, 38, 41);
		add(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(204, 153, 51));
		panel.setBounds(20, 45, 38, 41);
		add(panel);
		panel.setLayout(null);
		setOpaque(false);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(204, 153, 51));
		panel_4.setBounds(20, 124, 38, 41);
		add(panel_4);
		
		JLabel label = new JLabel("Drained power");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 225, 84, 14);
		add(label);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_9.setBackground(new Color(153, 50, 204));
		panel_9.setBounds(20, 285, 51, 59);
		add(panel_9);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_8.setLayout(null);
		panel_8.setBackground(new Color(204, 153, 51));
		panel_8.setBounds(20, 263, 51, 59);
		add(panel_8);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_12.setLayout(null);
		panel_12.setBackground(new Color(204, 153, 51));
		panel_12.setBounds(89, 263, 51, 59);
		add(panel_12);
	}
}
