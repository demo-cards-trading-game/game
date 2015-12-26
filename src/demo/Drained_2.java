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
	public int  volatil;
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
	    
	    u = new JLabel("  0");
	    u.setHorizontalAlignment(SwingConstants.LEFT);
	    u.setFont(new Font("Comic Sans MS", Font.PLAIN, 27));
	    u.setForeground(Color.WHITE);
	    u.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "UNDRAINED", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
	    u.setBackground(Color.BLACK);
	    u.setBounds(10, 71, 130, 49);
	    add(u);
	    
	    JLabel lblName = new JLabel(name);
	    lblName.setForeground(new Color(152, 251, 152));
	    lblName.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
	    lblName.setHorizontalAlignment(SwingConstants.CENTER);
	    lblName.setBounds(10, 123, 130, 21);
	    add(lblName);
	    
	    JLabel label = new JLabel("0");
	    label.setHorizontalAlignment(SwingConstants.CENTER);
	    label.setForeground(Color.WHITE);
	    label.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
	    label.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "volatile", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
	    label.setBackground(Color.BLACK);
	    label.setBounds(71, 85, 66, 31);
	    add(label);
	    
		volatil=currentdrained=currentundrained=0;
		init();
	
		
	}
	
	void init()
	{
		  d.setText(""+currentdrained);
		  u.setText(""+currentundrained);
	} 
	void initTurn()// aca es algo raro
	{
		
		
		
	}
	void set (int n)
	{
		u.setText(""+(currentundrained+n));
		currentundrained+=n;
		
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
