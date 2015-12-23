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
	public  JLabel d,u;
	public Drained_2(int x , int y,String name)
	{
		setOpaque(false);
		setBackground(new Color(0, 0, 0));
		setBounds(x,y,150,206);
		setLayout(null);
	    
	    d = new JLabel("0");
	    d.setForeground(Color.WHITE);
	    
	 
	
	      d.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
	      
	      d.setBackground(Color.BLACK);
	      d.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DRAINED", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
	      d.setHorizontalAlignment(SwingConstants.CENTER);
	     d.setBounds(10, 11, 130, 49);
	      add(d);
	    
	    u = new JLabel("0");
	    u.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
	    u.setHorizontalAlignment(SwingConstants.CENTER);
	    u.setForeground(Color.WHITE);
	    u.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "UNDRAINED", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
	    u.setBackground(Color.BLACK);
	    u.setBounds(10, 77, 130, 49);
	    add(u);
	    
	    JLabel lblName = new JLabel(name);
	    lblName.setForeground(new Color(152, 251, 152));
	    lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
	    lblName.setHorizontalAlignment(SwingConstants.CENTER);
	    lblName.setBounds(10, 133, 130, 21);
	    add(lblName);
	    
		currentdrained=currentundrained=0;
		init();
	
		
	}
	
	void init()
	{
		  d.setText(""+currentdrained);
		  u.setText(""+currentundrained);
	} 
	
	void set (int n)
	{
		u.setText(""+(currentundrained+n));
		currentundrained+=n;
		
	}
	void undrain(int n)
	{
		u.setText(""+(currentundrained+n));
		d.setText(""+(currentdrained-n));
		currentundrained+=n;
		init();
	}
	
	void play(int n)
	{
		u.setText(""+(currentundrained-n));
		d.setText(""+(currentdrained+n));
		currentundrained-=n;
		currentdrained+=n;
		init();
	}
}
