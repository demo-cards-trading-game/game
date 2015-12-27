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
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;

public class Drained extends JLayeredPane {

	/**
	 * Create the panel.
	 */
	public  int currentdrained;
	public  int currentundrained;
	public int volatiles;
	public JPanel[]  drained  = new JPanel[20];
	public JPanel[]  undrained  = new JPanel[20];
	public JPanel panel; 
	public Drained(int x , int y,String name)
	{
		setBounds(x,y,150,384);
		setLayout(null);
		
		panel=new JPanel();
		panel.setBounds(10, 29, 130, 71);
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setOpaque(false);
		JLabel lblUndrained = new JLabel("UNDRAINED");
		lblUndrained.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblUndrained.setForeground(Color.BLACK);
		lblUndrained.setBounds(10, 11, 100, 14);
		add(lblUndrained);

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
		undrained[currentundrained].setBounds(104, 325, 36, 37);
		panel.add(undrained[currentundrained]);
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
	void drain(int n)
	{
		if(currentundrained>=n){
		int i ;
		for(i= currentdrained;i<= currentdrained+n;i++){	
		drained[currentundrained] = new JPanel();
		drained[currentundrained].setBorder(new MatteBorder(4, 3, 1, 3, (Color) new Color(0, 0, 0)));
		drained[currentundrained].setBackground(new Color(51, 51, 204));
		drained[currentundrained].setBounds(104, 325, 36, 37);
		add(drained[currentundrained]);
		currentdrained++;
		setVisible(true);
		repaint();
		take();
		}
		
		}
		
	}
	void play(int n)
	{
		drain(n);
	}
}
