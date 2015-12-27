package demo;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import javax.swing.JScrollBar;
import java.awt.LayoutManager;

public class Drained extends JLayeredPane {

	/**
	 * Create the panel.
	 */
	public  int currentdrained;
	public  int currentundrained;
	public int volatiles;
	public JPanel[]  drained  = new JPanel[20];
	public JPanel[]  undrained  = new JPanel[20];
	public JPanel panel,panel_1,panel_2; 
	public Drained(int x , int y,String name)
	{
		setBounds(x,y,145,384);
		setLayout(null);
	     
		JLabel lblUndrained = new JLabel("UNDRAINED");
		lblUndrained.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblUndrained.setForeground(Color.BLACK);
		lblUndrained.setBounds(10, 11, 100, 14);
		add(lblUndrained);
		
		panel=new JPanel(null);
		add(panel);
		panel.setOpaque(false);
		panel.setBounds(0, 36, 200, 60);
		
		
		panel.setOpaque(true);
		panel.setLayout(null);
		
		 panel_1 = new JPanel((LayoutManager) null);
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 150, 200, 60);
		add(panel_1);
		
		JLabel lblDrained = new JLabel("DRAINED");
		lblDrained.setForeground(Color.BLACK);
		lblDrained.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblDrained.setBounds(10, 125, 100, 14);
		add(lblDrained);
		set();
		/***************************se crean los paneles ***************************************/
		
		
	
		
		/********************************************************************************/
		
		/*************************se asignan a una posicion correspondiente ***********************************/

		currentdrained=0;
		volatiles=currentundrained=0;
		
	}
	
	void set()
	{
		
		undrained[currentundrained] = new JPanel();
		undrained[currentundrained].setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		undrained[currentundrained].setBackground(new Color(51, 51, 204));
		
		panel.add(undrained[currentundrained]);
		
		if(currentundrained<5)
		{
		undrained[currentundrained].setBounds(currentundrained*30, 0, 25, 20);
		}else{
			if(currentundrained<10)
			{
			undrained[currentundrained].setBounds((currentundrained-5)*30,25 , 25, 20);
			}else
			{
				undrained[currentundrained].setBounds((currentundrained-10)*30,50 , 25, 20);
			}
			
		}	
		currentundrained++;
		setVisible(true);
		repaint();
	}
	void set(int n)
	{
		int i ;
		for(i= currentdrained;i<= currentdrained+n;i++){
		set();
		}
		
	}
	void take()
	{
		
			currentundrained=currentundrained-1;
			panel.remove(undrained[currentundrained]);
			setVisible(true);
			repaint();
		
	}
	
	void drain()
	{
		drained[currentdrained] = new JPanel();
		drained[currentdrained].setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		drained[currentdrained].setBackground(new Color(51, 51, 204));
		
		panel_1.add(drained[currentdrained]);
		
		if(currentdrained<5)
		{
		drained[currentdrained].setBounds(currentdrained*30, 0, 25, 20);
		}else{
			if(currentdrained<10)
			{
			drained[currentdrained].setBounds((currentdrained-5)*30,25 , 25, 20);
			}else
			{
				drained[currentdrained].setBounds((currentdrained-10)*30,50 , 25, 20);
			}
			
		}	
		currentdrained++;
		setVisible(true);
		repaint();
		
	}
	void drain(int n)
	{
		if(currentundrained>=n){
		int i ;
		for(i= currentdrained;i<= currentdrained+n;i++){	
		
		drain();
		take();
		}
		
		}
		
	}
	void play(int n)
	{
		drain(n);
	}
}
