  package demo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import extra.*;
import demo.DeckGui;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import demo.CardGui;
import data.LoadData;
import data.deckCreator;

import java.util.Random;
import demo.HandGui;
public class Gui extends JFrame implements ActionListener
{
	JPanel jp1,jp2,jp3;
	JButton b1,b2,b3;
	JLabel l1,demo, validar;
	JTextArea text, val1,val2;
	String Nombre1;//nombre del jugador1
	private JMenuBar mb;
	private JMenu menu1,menu2;
	private JMenuItem mi1,mi2,mi3,quit;
	static LoadData data;
	private JPanel contentPane;
	private PlayGui player1;
	private deckCreator crear;
	JInternalFrame crea;
	RollDice dados;
	
	private FileWriter turno = null;
    private PrintWriter pw = null;
   
	public Gui()
	{  
	
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		setSize(xSize, ySize);
		setUndecorated(true);


		this.setTitle("Dyna-stryfe"); /*adds jframe title*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setVisible(true);

		crea = new JInternalFrame("Crear deck");
		crear= new deckCreator(112,84);







		setLayout(new CardLayout(0, 0));

	
		/***********pruebas******************/

		mb=new JMenuBar();
		setJMenuBar(mb);
		menu1=new JMenu("OPTIONS");
		mb.add(menu1);
		quit= new JMenuItem("EXIT");
		menu2=new JMenu("resolucion");
		menu1.add(menu2);
		mb.add(quit);
		mi1=new JMenuItem("900,650(default)");
		mi1.addActionListener(this);
		menu2.add(mi1);
		mi2=new JMenuItem("800x600");
		mi2.addActionListener(this);
		menu2.add(mi2);
		mi3=new JMenuItem("1024x768");
		mi3.addActionListener(this);
		quit.addActionListener(this);
		menu2.add(mi3);         

		/***************************************/
		addbackgound(this);
		addjlabel1(this);

		text = new JTextArea();   
		text.setBounds(480, 580, 90, 20);
		text.setEditable(true);
		add(text);
		
		validar = new JLabel("please, input a valid name and press enter!");
		validar.setBounds(580, 580, 250, 20);
		validar.setVisible(false);
		add(validar);
		
		b1=new JButton("Play");
		b1.setBackground(Color.white);
		b1.setBorder(null);
		b1.setBounds(380 ,560,60,30);
		b1.setBorder(BorderFactory.createEmptyBorder());
		b1.addActionListener(this);
		add(b1);
		text.requestFocusInWindow();
		text.addKeyListener(new myKeyState1());





		demo=new JLabel("<html><font color='white'>Demo version: 0.000011 </font></html>");
		demo.setBounds(870,660,300,30); //esto se mueve como horizontal vertical 100= h 200=v
		this.add(demo);
		
		

		/*********************************/



		
		setResizable(false);


		

		setVisible(true);
		show();
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource()==mi2)
		{
		
			this.setBounds(0,0,800,600);
			
			
		}
		if (e.getSource()==quit)
		{
			System.out.println("deberia cerrarse");
			this.dispose();
			
			
		}
		if(player1!=null){
			if (e.getSource()==player1.player.pdeck.btnNewButton)
				
			{
				player1.tuto.ok.doClick();
				if(this.player1.getPhaseActual()==0)
				{	
					
				if(player1.cardDrawn==0){
					

					
					
						if(player1.player.pdeck.Deck.cardsLeft()!= 0 )
						{
							int pos =player1.player.hand.draw(player1.player.pdeck.Deck.extraerR());
							player1.player.pdeck.textField.setText("cards left "+player1.player.pdeck.Deck.cardsLeft());
							player1.cardDrawn=1;
							player1.player.pdeck.textField.repaint();
						
							setVisible(true);
							repaint();
						}else
						{
							
							gameover(this);
							
						
							try {
							
								player1=new PlayGui(0,0,Nombre1);
								
		
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
							b2 = new JButton("rematch");
							b2.setBackground(Color.BLACK);
							b2.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
							b2.setForeground(Color.WHITE);
							b2.setBounds(70, 50, 132, 43);
							b2.addActionListener(this);
							add(b2);
							repaint();
							setVisible(true);
						
							
						}
						
		
					
				}else
				{
					JOptionPane.showMessageDialog(null, "Sorry , u can pick only a card per turn");
				}
			}else
			{
				
					
					JOptionPane.showMessageDialog(null, "Sorry , u can only pick cards on the draw phase");
				
				
			}
			}
			
		}
		if(e.getSource()==b1)
		{
			
			if(text.getText().trim().length()==0){
				
				validar.setVisible(true);
			}
			else{
				validar.setVisible(false);
				b1.setEnabled(true);
		      
				addbackground2(this);
				b2 = new JButton("Play");
				b2.setBackground(Color.BLACK);
				b2.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
				b2.setForeground(Color.WHITE);
				b2.setBounds(70, 50, 132, 43);
				b2.addActionListener(this);
				add(b2);
				
				//validacion para entrar directamente con enter
				val1 = new JTextArea();   
				val1.setBounds(480, 580, 0, 0);
				val1.setVisible(true);
				add(val1);
				val1.requestFocusInWindow();
				val1.addKeyListener(new myKeyState2());
				
				b3=new JButton("Deck menu");
				b3.setForeground(Color.BLACK);
				b3.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
				b3.setBackground(Color.WHITE);
				b3.setBounds(70, 130, 132, 43);
				b3.addActionListener(this);
				add(b3);
				setVisible(true);
		    }
		}
	if(dados!=null){
		if(e.getSource()==dados.pane.rollButton)//dados
		{
			Thread t = new Thread(new Runnable(){
				
				public void start(){
					this.start();
				}
				
				public void run(){

			        try {
			            Thread.sleep(3000); 
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			        //dados.pane.text.setText("3");
			        if(dados.pane.text.getText()=="3"){
			        	dados.label.setBounds(70, 316+50, 507, 41);
			        	dados.label.setText("Tie, Roll again");
						dados.label.setVisible(true);
			        	try {
				            Thread.sleep(1000); 
				        } catch (InterruptedException e) {
				            e.printStackTrace();
				        }
			        	b2.doClick();
			        	dados.label.setBounds(70, 316+50, 507, 41);
			        	dados.label.setText("Tie, Roll again");
						dados.label.setVisible(true);
						//repaint();
			        }
			        else{
			        	dados.label.setBounds(100, 316, 507, 41);
			        	dados.pane.rollButton.setVisible(false);
						dados.btnPlay.setVisible(true);
						
						try {
							turno= new FileWriter("turno.txt");
							pw=new PrintWriter(turno);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								
						
						if(dados.pane.text.getText()=="1"){
							dados.label.setText("Fist turn is yours");
							pw.println(1);
						}
						else{
							dados.label.setText("AI player gets the  first turn");
							pw.println(2);
						}
						dados.label.setVisible(true);
						dados.btnPlay.setVisible(true);
						
						try {
							turno.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			        }
			       
				}
			});
			t.start();
		}
			
	if(e.getSource()==dados.btnPlay)
		{
		try {
			player1=new PlayGui(0,0,Nombre1);
            Thread t = new Thread(new Runnable(){
				
				public void start(){
					this.start();
				}
				
				public void run(){

			        try {
			            Thread.sleep(1000); 
			        } catch (InterruptedException e) {
			            e.printStackTrace();
			        }
			        if(dados.pane.text.getText()=="2"){
						try {
							player1.Aiturn();
							player1.contTurn++;
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
			t.start();


		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			Nombre1=text.getText();//guarda el nombre del jugador en Nombre1
			player1.player.pdeck.btnNewButton_1.addActionListener(this);//para que se puedan usar los botones del deck
			player1.player.pdeck.btnNewButton.addActionListener(this);
			addbackground3(this);
			getContentPane().setLayout(null);
			player1.repaint.addActionListener(this);
			add(player1);
			setVisible(true);
			
			
			
			
		}

	}
		if (e.getSource()==b2)
			try {
				{


					Nombre1=text.getText();//guarda el nombre del jugador en Nombre1
					try {
						dados= new RollDice();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//dados.pane.rollButton.addActionListener(this);

					addbackground4(this);
					getContentPane().setLayout(null);
					
					//validacion para entrar directamente con enter
					val2 = new JTextArea();   
					val2.setBounds(480, 580, 0, 0);
					val2.setVisible(true);
					add(val2);
					val2.requestFocusInWindow();
					val2.addKeyListener(new myKeyState3());

					add(dados);
					dados.pane.rollButton.addActionListener(this);
					dados.btnPlay.addActionListener(this);

					setVisible(true);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		if (e.getSource()==b3)
		{

			if(!crear.isVisible()){



				crear.setVisible(true);


				add(crear);


			}
		}
		
		

		if (e.getSource()==mi1) {
			setSize(900,650);
			pack();
		}
		
		if (e.getSource()==mi3) {
			setSize(1024,768);
			pack();
		}   
		if (player1!=null){
		if(e.getSource()==player1.repaint)
		{
			repaint();
			
		}
		}
	}
	
	class myKeyState1 extends KeyAdapter implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			      if(text.getText().trim().length()==0){
			    	  b1.setEnabled(false);
			    	  validar.setVisible(true);
			      }
			      else{
			    	  validar.setVisible(false);
			    	  b1.setEnabled(true);
			    	  b1.doClick();
			      }
					
			   }
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class myKeyState2 extends KeyAdapter implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			      b2.doClick();
			   }
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class myKeyState3 extends KeyAdapter implements KeyListener{

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				if(!dados.btnPlay.isVisible()){
					dados.pane.rollButton.doClick();
				}
				else{
					dados.btnPlay.doClick();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private deckCreator deckCreator() {
		// TODO Auto-generated method stub
		return null;
	}

	/***********funciones*************/
	void addbackgound(JFrame jfm)
	{
		try {
			jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("test.jpg")))));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	void gameover(JFrame jfm)
	{
		try {
			jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("gameover.jpg")))));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	void addbackground2(JFrame jfm)
	{
		try {
			jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("frame2.jpg")))));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	void addbackground3(JFrame jfm)
	{
		try {
			jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("frame3.jpg")))));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	void addbackground4(JFrame jfm)
	{
		try {
			jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("frame4.jpg")))));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	void addjlabel1(JFrame jfm)
	{
		l1=new JLabel("<html><font color='white'>player's name.</font></html>");
		l1.setBounds(480,530,300,30); //esto se mueve como horizontal vertical 100= h 200=v
		jfm.add(l1);



	}



	void addtext1(JFrame jfm,JTextArea text)
	{
		text = new JTextArea();   

		text.setBounds(380, 450, 90, 20);
		text.setEditable(true);

		jfm.add(text);
	}

	/************************/



	public static void main(String[] args)
	{
		Gui Juego=new Gui();
		
		
	}
/*
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
		      if(text.getText().trim().length()==0){
		    	  b1.setEnabled(false);
		    	  validar.setVisible(true);
		      }
		      else{
		    	  validar.setVisible(false);
		    	  b1.setEnabled(true);
		    	  b1.doClick();
		      }
				
		   }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	*/
}
