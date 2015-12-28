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

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

public class Drained extends JLayeredPane {

	/**
	 * Create the panel.
	 */
	public  int currentdrained,used;
	public  int currentundrained,currentoken;
	
	public JPanel[]  drained  = new JPanel[20];
	public JPanel[]  undrained  = new JPanel[20];
	public JLabel[]  tokens = new JLabel[20];
	public JPanel panel,panel_1,panel_2; 
	public Drained(int x , int y,String name)
	{
		setBounds(x,y,145,384);
		setLayout(null);
	     
		JLabel lblUndrained = new JLabel("UNDRAINED");
		lblUndrained.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblUndrained.setForeground(new Color(0, 153, 255));
		lblUndrained.setBounds(10, 11, 100, 14);
		add(lblUndrained);
		
		panel=new JPanel(null);
		add(panel);
		panel.setOpaque(false);
		panel.setBounds(0, 36, 200, 75);
		
		
	
		panel.setLayout(null);
		
		 panel_1 = new JPanel((LayoutManager) null);
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 132, 200, 75);
		add(panel_1);
		
		JLabel lblDrained = new JLabel("DRAINED");
		lblDrained.setForeground(new Color(51, 102, 255));
		lblDrained.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblDrained.setBounds(10, 107, 100, 14);
		add(lblDrained);
		
		 panel_2 = new JPanel();
		panel_2.setBounds(0, 237, 145, 56);
		add(panel_2);
		panel_2.setLayout(null);
		panel_2.setOpaque(false);
		
		JLabel lblVolatile = new JLabel("VOLATILE");
		lblVolatile.setForeground(new Color(204, 255, 255));
		lblVolatile.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblVolatile.setBounds(10, 212, 100, 14);
		add(lblVolatile);
		
		/***************************se crean los paneles ***************************************/
		
		
		/********************************************************************************/
		
		/*************************se asignan a una posicion correspondiente ***********************************/

		currentdrained=0;
		used=0;
		currentoken=currentundrained=0;
	
	}
	
	void set()
	{
		
		undrained[currentundrained] = new JPanel();
		undrained[currentundrained].setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		undrained[currentundrained].setBackground(Color.RED);
		
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
		for(i= 0;i<n;i++){
		set();
		System.out.println(currentundrained);
		}
		setVisible(true);
		repaint();
		
	}
	void take()
	{
		
			currentundrained=currentundrained-1;
			used++;
			panel.remove(undrained[currentundrained]);
			setVisible(true);
			repaint();
		
	}
	
	void take2()
	{
		
		repaint();
			currentdrained=currentdrained-1;
			
			panel_1.remove(drained[currentdrained]);
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
		take();
		panel_2.setVisible(true);
		repaint();
		panel_2.setVisible(true);
	}
	void drain(int n)
	{
		if(currentundrained>=n){
		int i ;
		for(i= 0;i< n;i++){	
		
		drain();
		
		}
		
		}
		
	}
	void token()
	{

		try {
			tokens[currentoken]=new JLabel(new ImageIcon(ImageIO.read(new File("token.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		panel_2.add(tokens[currentoken]);
		
		if(currentoken<5)
		{
		tokens[currentoken].setBounds(currentoken*25, 0, 20, 20);
		}else{
			if(currentoken<10)
			{
			tokens[currentoken].setBounds((currentoken-5)*25,25 , 20, 20);
			}else
			{
				tokens[currentoken].setBounds((currentoken*10)*25,50 , 20, 20);
			}
			
		}	
		currentoken++;
		setVisible(true);
		repaint();
		
		
	}
	void reset()
	{
		System.out.println("entro reset con"+used);
		for( int i=0;i<used;i++)
		{
			
			set();
			take2();
			System.out.println("currentundrained"+currentundrained);
			System.out.println("currentdrained"+currentdrained);
		}
		used=0;
		repaint();
		setVisible(true);
	}
	void play(int n)
	{
		drain(n);
	}
}
