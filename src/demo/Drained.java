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
	private  int currentdrained;
	private  int currentundrained;
	public JPanel[]  drained  = new JPanel[20];
	public JPanel[]  undrained  = new JPanel[20];
	public Drained(int x , int y)
	{
		setBounds(x,y,150,384);
		setLayout(null);
		/***************************se crean los paneles ***************************************/
		
		JPanel panel_23 = new JPanel();
		panel_23.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_23.setBackground(new Color(51, 51, 204));
		panel_23.setBounds(104, 325, 36, 37);
		add(panel_23);
		
		JPanel panel_22 = new JPanel();
		panel_22.setLayout(null);
		panel_22.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_22.setBackground(new Color(255, 153, 0));
		panel_22.setBounds(104, 285, 36, 41);
		add(panel_22);
		
		JPanel panel_21 = new JPanel();
		panel_21.setBounds(0, 22, 36, 38);
		panel_22.add(panel_21);
		panel_21.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_21.setBackground(new Color(153, 50, 204));
		
		JPanel panel_15 = new JPanel();
		panel_15.setBounds(58, 325, 36, 37);
		add(panel_15);
		panel_15.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_15.setBackground(new Color(51, 51, 204));
		undrained[7]=panel_15;
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_11.setBackground(new Color(51, 51, 204));
		panel_11.setBounds(10, 325, 38, 37);
		add(panel_11);
		undrained[3]=panel_11;
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(10, 303, 38, 41);
		add(panel_9);
		panel_9.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_9.setBackground(new Color(153, 50, 204));
		undrained[1]=panel_9;
		
		JPanel panel_19 = new JPanel();
		panel_19.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_19.setBackground(new Color(51, 51, 204));
		panel_19.setBounds(89, 198, 38, 41);
		add(panel_19);
		
		JPanel panel_18 = new JPanel();
		panel_18.setLayout(null);
		panel_18.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_18.setBackground(new Color(255, 153, 0));
		panel_18.setBounds(68, 177, 38, 41);
		add(panel_18);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_17.setBackground(new Color(153, 50, 204));
		panel_17.setBounds(41, 166, 38, 41);
		add(panel_17);
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_7.setBackground(new Color(51, 51, 204));
		panel_7.setBounds(89, 125, 38, 41);
		add(panel_7);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_6.setLayout(null);
		panel_6.setBackground(new Color(255, 153, 0));
		panel_6.setBounds(68, 114, 38, 41);
		add(panel_6);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_5.setBackground(new Color(153, 50, 204));
		panel_5.setBounds(41, 98, 38, 41);
		add(panel_5);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_14.setLayout(null);
		panel_14.setBackground(new Color(255, 153, 0));
		panel_14.setBounds(58, 303, 36, 41);
		add(panel_14);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_13.setBackground(new Color(153, 50, 204));
		panel_13.setBounds(58, 285, 36, 38);
		add(panel_13);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_10.setLayout(null);
		panel_10.setBackground(new Color(255, 153, 0));
		panel_10.setBounds(10, 285, 38, 38);
		add(panel_10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_2.setBackground(new Color(51, 51, 204));
		panel_2.setBounds(89, 62, 38, 41);
		add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_3.setBackground(new Color(255, 153, 0));
		panel_3.setBounds(68, 46, 38, 41);
		add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("UnDrained power");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(21, 0, 106, 14);
		add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBackground(new Color(153, 50, 204));
		panel_1.setBounds(41, 35, 38, 41);
		add(panel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBackground(new Color(204, 153, 51));
		panel.setBounds(20, 21, 38, 41);
		add(panel);
		panel.setLayout(null);
		setOpaque(false);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(204, 153, 51));
		panel_4.setBounds(20, 87, 38, 41);
		add(panel_4);
		
		JLabel label = new JLabel("Drained power");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 238, 84, 14);
		add(label);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_8.setLayout(null);
		panel_8.setBackground(new Color(204, 153, 51));
		panel_8.setBounds(10, 263, 38, 38);
		add(panel_8);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_12.setLayout(null);
		panel_12.setBackground(new Color(204, 153, 51));
		panel_12.setBounds(58, 264, 36, 37);
		add(panel_12);
		
		
		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBorder(new MatteBorder(3, 2, 1, 1, (Color) new Color(0, 0, 0)));
		panel_16.setBackground(new Color(204, 153, 51));
		panel_16.setBounds(20, 150, 38, 41);
		add(panel_16);
		
		JPanel panel_20 = new JPanel();
		panel_20.setLayout(null);
		panel_20.setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		panel_20.setBackground(new Color(204, 153, 51));
		panel_20.setBounds(104, 263, 36, 37);
		add(panel_20);
		
		/********************************************************************************/
		
		/*************************se asignan a una posicion correspondiente ***********************************/
		drained[0]=panel;
		drained[1]=panel_1;
		drained[2]=panel_3;
		drained[3]=panel_2;
		drained[4]=panel_4;
		drained[5]=panel_5;
		drained[6]=panel_6;
		drained[7]=panel_7;
		drained[8]=panel_16;
		drained[9]=panel_17;
		drained[10]=panel_18;
		drained[11]=panel_19;
	
		undrained[0]=panel_8;
		undrained[1]=panel_9;
		undrained[2]=panel_10;
		undrained[3]=panel_11;
		undrained[4]=panel_12;
		undrained[5]=panel_13;
		undrained[6]=panel_14;
		undrained[7]=panel_15;
		undrained[8]=panel_20;
		undrained[9]=panel_21;
		undrained[10]=panel_22;
		undrained[11]=panel_23;
		currentdrained=6;
		currentundrained=6;
		for(int i=6;i<12;i++)
		{
			undrained[i].setVisible(false);
			drained[i].setVisible(false);
			
		}
		
		
		
		
	}
	
	void drain(int n)
	{
		
		
		if(currentundrained-n>=0 && currentdrained + n<=12  )
		{	System.out.println("funcion drain entro");
			for(int i=currentdrained;i<currentdrained+n;i++)
			{
				undrained[i].setVisible(true);
				undrained[i].repaint();
			}
			for(int i=currentundrained-1 ;i>=currentundrained-n;i--)
			{
				drained[i].setVisible(false);
				drained[i].repaint();
				
			}
			currentundrained=currentundrained-n;
			currentdrained=currentdrained+n;
			repaint();
		}
		
	}
	
	void undrain(int n)
	{
		/*confusion drained en realidad es undrained*/
		
		if(currentundrained+n<=12 && currentdrained-n >=0)
			
		{
			System.out.println("funcion undrain entro");
			for(int i=currentundrained;i<currentundrained+n;i++)
			{
				drained[i].setVisible(true);
				drained[i].repaint();
			}
			for(int i=currentdrained-1;i>=currentdrained-n;i--)
			{
				undrained[i].setVisible(false);
				undrained[i].repaint();
			}
			currentundrained=currentundrained+n;
			currentdrained=currentdrained-n;
			repaint();
		}
		
	}
}
