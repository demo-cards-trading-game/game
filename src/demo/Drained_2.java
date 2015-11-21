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
	public static JLabel d,u;
	public Drained_2(int x , int y)
	{
		setOpaque(false);
		setBackground(new Color(0, 0, 0));
		setBounds(x,y,150,141);
		setLayout(null);
	    
	    d = new JLabel("");
	    
	 
	
	      d.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
	      
	      d.setBackground(SystemColor.activeCaption);
	      d.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DRAINED", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
	      d.setHorizontalAlignment(SwingConstants.CENTER);
	     d.setBounds(10, 11, 130, 49);
	      add(d);
	    
	    u = new JLabel("");
	    u.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
	    u.setHorizontalAlignment(SwingConstants.CENTER);
	    u.setForeground(Color.WHITE);
	    u.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "UNDRAINED", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
	    u.setBackground(SystemColor.activeCaption);
	    u.setBounds(10, 77, 130, 49);
	    add(u);
	    
		currentdrained=currentundrained=6;
		
	
		
	}
	void init()
	{
		  d.setText(""+currentdrained);
		  u.setText(""+currentundrained);
	} 
	
	void get (int n)
	{
		u.setText(""+(currentundrained+n));
		currentundrained+=n;
		
	}
	void undrain(int n)
	{
		u.setText(""+(currentundrained+n));
		d.setText(""+(currentdrained-n));
		currentundrained+=n;
		currentdrained-=n;
		
	}
	
	void drain(int n)
	{
		u.setText(""+(currentundrained-n));
		d.setText(""+(currentdrained+n));
		currentundrained-=n;
		currentdrained+=n;
		
	}
}
