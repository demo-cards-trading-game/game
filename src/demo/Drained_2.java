package demo;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;

public class Drained_2 extends JPanel {

	public  int currentdrained;
	public  int currentundrained;
	public static JLabel lblDrained,lblDrained_1;
	public Drained_2(int x , int y)
	{
		setOpaque(false);
		setBackground(new Color(0, 0, 0));
		setBounds(x,y,150,141);
		setLayout(null);
		
	    lblDrained = new JLabel("");
	 
	
	    lblDrained.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
		lblDrained.setForeground(SystemColor.textHighlightText);
		lblDrained.setBackground(SystemColor.activeCaption);
		lblDrained.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DRAINED", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		lblDrained.setOpaque(false);
		lblDrained.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrained.setBounds(10, 11, 130, 49);
		add(lblDrained);
		
	    lblDrained_1 = new JLabel("");
	    lblDrained_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
		lblDrained_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDrained_1.setForeground(Color.WHITE);
		lblDrained_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "UNDRAINED", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		lblDrained_1.setBackground(SystemColor.activeCaption);
		lblDrained_1.setBounds(10, 77, 130, 49);
		add(lblDrained_1);
		
		setVisible(true);
		
	}
	void dmana( int n)
	{
		System.out.println("entro drain_2");
		
		currentdrained=currentdrained+n;
		currentundrained=currentundrained-n;
		System.out.println("n , c "+n +" ."+currentdrained+"current"+lblDrained.getText());
	    lblDrained.setText(""+currentdrained);
		lblDrained.repaint();
		lblDrained_1.repaint();
		lblDrained_1.setText(""+currentundrained);
		setVisible(true);
		repaint();
	}
	void undrain(int n)
	{
		System.out.println("entro undrain_2");
		currentdrained=currentdrained-n;
		currentundrained=currentundrained+n;
		lblDrained.setText(""+currentdrained);
		lblDrained_1.setText(""+currentundrained);
		repaint();
	}
}
