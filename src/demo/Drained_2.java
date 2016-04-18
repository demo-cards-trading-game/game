package demo;

import extra.RoundedPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Drained_2 extends JLayeredPane implements MouseListener{
	/**
	 * Create the panel.
	 */
	public  int currentdrained,used,drain;
	public  int currentundrained,currentoken;
	public int paying,tokenused,N=0;
	public RoundedPanel[]  drained  = new RoundedPanel[20];
	public RoundedPanel[]  undrained  = new RoundedPanel[20];
	public RoundedPanel[]  tokens = new RoundedPanel[20];
	public boolean[]  marks = new boolean[20];
	public JPanel panel,panel_1,panel_2; 
	JLabel label;

	public Drained_2(int x , int y,String name)
	{
		setBounds(x,y,196,384);
		setLayout(null);
	     
		
		
		panel=new JPanel(null);
		add(panel);
		panel.setOpaque(false);
		panel.setBounds(0, 30, 170, 85);
		panel.setLayout(null);
		panel_1 = new JPanel(null);
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		panel_1.setBounds(0, 251, 200, 75);
		add(panel_1);
		
	
		
		panel_2 = new JPanel();
		panel_2.setBounds(0, 140, 145, 75);
		add(panel_2);
		panel_2.setLayout(null);
		panel_2.setOpaque(false);
		
		
		
		label = new JLabel("0");
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		label.setBackground(Color.ORANGE);
		label.setBounds(113, 349, 73, 35);
		for(int i=0;i<20;i++)
		{
			marks[i]=false;
		}
		/***************************se crean los paneles ***************************************/
		
		/********************************************************************************/
		
		/*************************se asignan a una posicion correspondiente ***********************************/
		paying=0;
		currentdrained=0;
		used=drain=0;
		currentoken=currentundrained=0;
	
	}
	void setwp()
	{
		undrained[currentundrained] = new RoundedPanel();
		undrained[currentundrained].setLayout(null);
		marks[currentundrained]=true;
		try {
			undrained[currentundrained].setLayout(null);
			
			JLabel l  = new JLabel(new ImageIcon(ImageIO.read(new File("waterp.jpg"))));
			l.setBounds(0, 0,25,20);
			undrained[currentundrained].add(l);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		undrained[currentundrained].addMouseListener(this);
		panel.add(undrained[currentundrained]);
		
		if(currentundrained<5)
		{
			undrained[currentundrained].setBounds(currentundrained*30, 10, 25, 20);
		}else{
			if(currentundrained<10)
			{
				undrained[currentundrained].setBounds((currentundrained-5)*30,35 , 25, 20);
			}else
			{
				undrained[currentundrained].setBounds((currentundrained-10)*30,60 , 25, 20);
			}
			
		}	
		currentundrained++;
		setVisible(true);
		repaint();
	}
	void set()
	{
		undrained[currentundrained] = new RoundedPanel();
		undrained[currentundrained].setBackground(Color.BLUE);
		undrained[currentundrained].addMouseListener(this);
		undrained[currentundrained].arcs = new Dimension(5, 5); 
		panel.add(undrained[currentundrained]);
		
		if(currentundrained<5)
		{
			undrained[currentundrained].setBounds(currentundrained*30, 10, 25, 20);
		}else{
			if(currentundrained<10)
			{
				undrained[currentundrained].setBounds((currentundrained-5)*30,35 , 25, 20);
			}else
			{
				undrained[currentundrained].setBounds((currentundrained-10)*30,60 , 25, 20);
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
		}
		setVisible(true);
		repaint();
	}
	void take()
	{
		borrar(find2());
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
	
	void take3()
	{
		repaint();
		currentoken--;
		panel_2.remove(tokens[currentoken]);
		setVisible(true);
		repaint();
	}
	
	void drainwp()
	{
		drained[currentdrained] = new RoundedPanel();
		drained[currentdrained].arcs=new Dimension(5, 5);
		try {
			drained[currentdrained].setLayout(null);
			JLabel l  = new JLabel(new ImageIcon(ImageIO.read(new File("waterp.jpg"))));
			l.setBounds(0, 0,25,20);
			drained[currentdrained].add(l);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		panel_2.setVisible(true);
		repaint();
		panel_2.setVisible(true);
	}
	void borrar(int pos)
	{
		panel.remove(undrained[pos]);
		setVisible(true);
		repaint();

		for(int i=pos;i<currentundrained;i++)
		{
			undrained[i]=undrained[i+1];
			marks[i]=marks[i+1];
		}
		currentundrained--;
	}
	void drain()
	{
		drained[currentdrained] = new RoundedPanel();
		drained[currentdrained].arcs=new Dimension(5, 5);
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
		panel_2.setVisible(true);
		repaint();
		panel_2.setVisible(true);
	}
	void drain(int n)
	{
		System.out.println("drain n="+"n="+n);
		while(n>=1)
		{
			drain();
			take();
			n--;
		}
	}
	void token()
	{
		tokens[currentoken]=new RoundedPanel();
		tokens[currentoken].arcs=new Dimension(20,20);
		tokens[currentoken].setBackground(new Color(127, 255, 0));
		tokens[currentoken].addMouseListener(this);
		panel_2.add(tokens[currentoken]);
		
		if(currentoken<=5)
		{
			tokens[currentoken].setBounds(currentoken*25, 10, 20, 20);
		}else{
			if(currentoken<=10)
			{
				tokens[currentoken].setBounds((currentoken-5)*25,25 , 30, 20);
			}else
			{
				tokens[currentoken].setBounds((currentoken*10)*25,60 , 20, 20);
			}
		}
		currentoken++;
		setVisible(true);
		repaint();
	}
	
	void disselect()
	{
		for( int i=0;i<currentundrained;i++)
		{
			undrained[i].setBackground(Color.blue);
			if(i<5)
			{
				undrained[i].setBounds(i*30, 10, 25, 20);
			}else{
				if(i<10)
				{
					undrained[i].setBounds((i-5)*30,35 , 25, 20);
				}else
				{
					undrained[i].setBounds((i-10)*30,60 , 25, 20);
				}
			}
	
			label.setText(""+paying);
		}
		for( int i=0;i<currentoken;i++)
		{
			if(i<=5)
			{
				tokens[i].setBounds(i*25, 10, 20, 20);
			}else{
				if(i<=10)
				{
					tokens[i].setBounds((i-5)*25,25 , 30, 20);
				}else
				{
					tokens[i].setBounds((i*10)*25,60 , 20, 20);
				}
			}
		}
		tokenused=paying=drain=0;
	}
	int find()
	{
		int i=0;
		
		while(!marks[i])
		{
			i++;
		}
		return i;
	}
	int find2()
	{
		int i=0;
		
		while(marks[i])
		{
			i++;
		}
		return i;
	}
	void reset()
	{
		for( int i=0;i<used-2*N;i++)
		{
			set();
			take2();
		}
		while(currentdrained>0)
		{
			take2();
		}
		while(N>0)
		{
			setwp();
			N--;
		}
		disselect();
		used=drain=0;
		tokenused=0;
		paying=0;
		N=0;
		repaint();
		setVisible(true);
	}
	void play(int n)
	{
		System.out.println("N="+N+"n="+n);
		drain(used-2*N);
		for( int i=0;i<N;i++)
		{
			drainwp();
			borrar(find());
		}
		while(tokenused>=1)
		{
			take3();
			tokenused--;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource()==undrained[0])
		{
			if(undrained[0].getY()==10)
			{
				paying++;
				used++;
				undrained[0].setBounds(0,0,25,20);
				undrained[0].setBackground(Color.red);
				
				if(marks[0])
				{
					paying++;
					used++;
					N++;
				}
				label.setText(""+paying);
			}else
			{
				paying--;
				used--;
				undrained[0].setBounds(0,10,25,20);
				undrained[0].setBackground(Color.blue);
				if(marks[0])
				{
					paying--;
					N--;
					used--;
				}
				label.setText(""+paying);
			}
		}
		
		if(e.getSource()==undrained[1])
		{
			if(undrained[1].getY()==10)
			{
				paying++;
				used++;
				label.setText(""+paying);
				undrained[1].setBounds(30,0,25,20);
				undrained[1].setBackground(Color.red);
				
				if(marks[1])
				{
					paying++;
					N++;
					used++;
				}
				label.setText(""+paying);
				System.out.println(""+used);
			}else
			{
				undrained[1].setBackground(Color.blue);
				paying--;
				used--;
				label.setText(""+paying);
				undrained[1].setBounds(30,10,25,20);
				if(marks[1])
				{
					paying--;
					N--;
					used--;
				}
				label.setText(""+paying);
			}
		}
		if(e.getSource()==undrained[2])
		{
			if(undrained[2].getY()==10)
			{
				paying++;
				used++;
				label.setText(""+paying);
				undrained[2].setBounds(60,0,25,20);
				undrained[2].setBackground(Color.red);
				if(marks[2])
				{
					paying++;
					N++;
					used++;
				}
				label.setText(""+paying);
			}else
			{
				undrained[2].setBackground(Color.blue);
				paying--;
				used--;
				label.setText(""+paying);
				undrained[2].setBounds(60,10,25,20);
				if(marks[2])
				{
					paying--;
					N--;
					used--;
				}
				label.setText(""+paying);
			}
		}
		if(e.getSource()==undrained[3])
		{
			if(undrained[3].getY()==10)
			{
				paying++;
				used++;
				undrained[3].setBounds(90,0,25,20);
				undrained[3].setBackground(Color.red);
				if(marks[3])
				{
					paying++;
					N++;
					used++;
				}
				label.setText(""+paying);
			}else
			{
				undrained[3].setBackground(Color.blue);
				paying--;
				used--;
				undrained[3].setBounds(90,10,25,20);
				if(marks[3])
				{
					paying--;
					N--;
					used--;
				}
				label.setText(""+paying);
			}
		}	
		if(e.getSource()==undrained[4])
		{
			if(undrained[4].getY()==10)
			{
				paying++;
				used++;
				
				undrained[4].setBounds(120,0,25,20);
				undrained[4].setBackground(Color.red);
				if(marks[4])
				{
					paying++;
					N++;
					used++;
				}
				label.setText(""+paying);
			}else
			{
				undrained[4].setBackground(Color.blue);
				paying--;
				used--;
				label.setText(""+paying);
				undrained[4].setBounds(120,10,25,20);
				if(marks[4])
				{
					paying--;
					N--;
					used--;
				}
				label.setText(""+paying);
			}
		}

if(e.getSource()==undrained[5])
{
	if(undrained[5].getY()==35)
	{
		paying++;
		used++;
		label.setText(""+paying);
		undrained[5].setBounds(0,25,25,20);
		undrained[5].setBackground(Color.red);
	}else
	{
		used--;
		undrained[5].setBackground(Color.blue);
		paying--;
		label.setText(""+paying);
		undrained[5].setBounds(0,35,25,20);
	}
}

if(e.getSource()==undrained[6])
{
	if(undrained[6].getY()==35)
	{
		paying++;
		used++;
		label.setText(""+paying);
		undrained[6].setBounds(30,25,25,20);
		undrained[6].setBackground(Color.red);
	}else
	{
		used--;
		undrained[6].setBackground(Color.blue);
		paying--;
		label.setText(""+paying);
		undrained[6].setBounds(30,35,25,20);
	}
}
		if(e.getSource()==undrained[7])
		{
			if(undrained[7].getY()==35)
			{
				paying++;
				used++;
				label.setText(""+paying);
				undrained[7].setBounds(60,25,25,20);
				undrained[7].setBackground(Color.red);
			}else
			{
				undrained[7].setBackground(Color.blue);
				paying--;
				used--;
				label.setText(""+paying);
				undrained[7].setBounds(60,35,25,20);
			}
		}

		if(e.getSource()==undrained[8])
		{
			if(undrained[8].getY()==35)
			{
				paying++;
				used++;
				label.setText(""+paying);
				undrained[8].setBounds(90,25,25,20);
				undrained[8].setBackground(Color.red);
			}else
			{
				undrained[8].setBackground(Color.blue);
				paying--;
				used--;
				label.setText(""+paying);
				undrained[8].setBounds(90,35,25,20);
			}
		}
		if(e.getSource()==undrained[9])
		{
			if(undrained[9].getY()==35)
			{
				paying++;
				used++;
				label.setText(""+paying);
				undrained[9].setBounds(120,25,25,20);
				undrained[9].setBackground(Color.red);
			}else
			{
				undrained[9].setBackground(Color.blue);
				paying--;
				used--;
				label.setText(""+paying);
				undrained[9].setBounds(120,35,25,20);
			}
		}
	
		if(e.getSource()==tokens[0])
		{
			if(tokens[0].getY()==10)
			{
				tokenused++;
				paying++;
				label.setText(""+paying);
				tokens[0].setBounds(0,0,20,20);
			}else
			{
				paying--;
				tokenused--;
				label.setText(""+paying);
				tokens[0].setBounds(0,10,20,20);
			}
		}
		if(e.getSource()==tokens[1])
		{
			if(tokens[1].getY()==10)
			{
				tokenused++;
				paying++;
				label.setText(""+paying);
				tokens[1].setBounds(25,0,20,20);
			}else
			{
				paying--;
				tokenused--;
				label.setText(""+paying);
				tokens[1].setBounds(25,10,20,20);
			}
		}
		if(e.getSource()==tokens[2])
		{
			if(tokens[2].getY()==10)
			{
				tokenused++;
				paying++;
				label.setText(""+paying);
				tokens[2].setBounds(50,0,20,20);
			}else
			{
				tokenused--;
				paying--;
				label.setText(""+paying);
				tokens[2].setBounds(50,10,20,20);
			}
		}
		if(e.getSource()==tokens[3])
		{
			if(tokens[3].getY()==10)
			{
				tokenused++;
				paying++;
				label.setText(""+paying);
				tokens[3].setBounds(75,0,20,20);
			}else
			{
				tokenused--;
				paying--;
				label.setText(""+paying);
				tokens[3].setBounds(75,10,20,20);
			}
		}
		if(e.getSource()==tokens[4])
		{
			if(tokens[4].getY()==10)
			{
				tokenused++;
				paying++;
				label.setText(""+paying);
				tokens[4].setBounds(100,0,20,20);
			}else
			{
				paying--;
				label.setText(""+paying);
				tokenused--;
				tokens[4].setBounds(100,10,20,20);
			}
		}

		if(e.getSource()==tokens[5])
		{
			if(tokens[5].getY()==10)
			{
				tokenused++;
				paying++;
				label.setText(""+paying);
				tokens[5].setBounds(125,0,20,20);

}else
			{
				tokenused--;
				paying--;
				label.setText(""+paying);
				tokens[5].setBounds(125,10,20,20);
			}
		}
		repaint();
		panel.repaint();
		setVisible(true);
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
