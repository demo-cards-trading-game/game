package demo;

import data.LoadData;
import data.deckCreator;
import extra.RollDice;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class Gui extends JFrame implements ActionListener, MouseListener
{
	public JButton b1,b2,b3, accionarAgarreAutomatico,Aifirst,playerfirst;
	public JLabel l1,demo, validar,ai,player;
	public JTextArea text,val2;
	public CardGui moving;
	public String Nombre1;//nombre del jugador1
	static LoadData data;
	public PlayGui player1;
	public deckCreator crear;
	public RollDice dados;
	private FileWriter turno = null;
	private PrintWriter pw = null;
   
	public Gui()
	{
		setSize(1024,798);
		setBackground(Color.white);
		this.setTitle("Dyna-stryfe"); /*adds jframe title*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addbackgound(this);
		this.getContentPane().setLayout(null);
		b2 = new JButton("Play");
		b2.setBackground(Color.BLACK);
		b2.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		b2.setForeground(Color.WHITE);
		b2.setBounds(450, 550, 132, 43);
		b2.addActionListener(this);
		add(b2);
		setVisible(true);

		/***********pruebas******************/
		demo=new JLabel("<html><font color='white'>Demo version: 0.000011 </font></html>");
		demo.setBounds(870,660,300,30); //esto se mueve como horizontal vertical 100= h 200=v
		this.add(demo);
		/*********************************/
		setResizable(false);
		setBackground(new Color(204, 204, 204));
		setVisible(true);
		show();

		accionarAgarreAutomatico = new JButton();
		accionarAgarreAutomatico.addActionListener(this);
	}
	public void addlistenerstoselectionbuttons()
	{
		playerfirst.addActionListener(this);
		Aifirst.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(dados!=null){
			if(e.getSource()==dados.pane.rollButton)//dados
			{
				Thread t = new Thread(new Runnable(){
					public void run(){
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(Objects.equals(dados.pane.text.getText(), "3")){
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
						}
						else{
							dados.label.setBounds(150, 316, 507, 41);
							dados.pane.rollButton.setVisible(false);
							try {
								turno= new FileWriter("turno.txt");
								pw=new PrintWriter(turno);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							if(Objects.equals(dados.pane.text.getText(), "1")){
								dados.label.setText("Congratulations , fate is on your side");
								dados.label.setVisible(true);
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								setContentPane(new Container());
								add(dados.label);
								dados.label.setText("Now select who is playing first");
								dados.label.setBounds(295, 316, 507, 41);
								Aifirst= new JButton();
								Aifirst.setBounds(650,400,200,200);
								Aifirst.setIcon(new ImageIcon("seccond.png"));
								add(Aifirst);
								playerfirst= new JButton();
								playerfirst.setBounds(250,400,200,200);
								playerfirst.setIcon(new ImageIcon("first.png"));
								add(playerfirst);
								player=new JLabel("Player");
								player.setBounds(350 ,620,200,30);
								player.setAlignmentX(CENTER_ALIGNMENT);
								add(player);
								ai=new JLabel("Ai");
								ai.setBounds(750 ,620,200,30);
								ai.setAlignmentX(CENTER_ALIGNMENT);
								add(ai);
								addlistenerstoselectionbuttons();
								repaint();
							
								pw.println(1);
							}
							else{
								dados.label.setText("AI player gets the  first turn");
								dados.label.setVisible(true);
								dados.btnPlay.setVisible(true);
								pw.println(2);
							}

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
			if(e.getSource()==Aifirst)
			{
				dados.pane.text.setText("2"); //para decir que comienza el ai
				dados.btnPlay.doClick();
			}
	
			if(e.getSource()==playerfirst)
			{
				dados.pane.text.setText("1"); //para decir que comienza el player
				dados.btnPlay.doClick();
			}
	
			if(e.getSource()==dados.btnPlay)
			{
				try {
					player1=new PlayGui(0,0,Nombre1, this);
					Thread t = new Thread(new Runnable(){
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
							else {
								player1.firstPlayerTurn();
							}
						}
					});
					t.start();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				player1.player.pdeck.btnNewButton_1.addMouseListener(this);//para que se puedan usar los botones del deck
				player1.player.pdeck.btnNewButton.addMouseListener(this);
			
				addbackground3(this);
				getContentPane().setBackground(new Color(153, 204, 204));
				getContentPane().setLayout(null);
				player1.repaint.addActionListener(this);
				add(player1);
				setVisible(true);
			}
		}
		if (e.getSource()==b2)
			try {
				{
					try {
						dados= new RollDice();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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

		if (player1!=null){
			if(e.getSource()==player1.repaint)
			{
				repaint();
			}
			if (e.getSource()==accionarAgarreAutomatico)
			{
				if(this.player1.getPhaseActual()==0)
				{	
					if(player1.cardDrawn==0){
						if(player1.player.pdeck.Deck.cardsLeft()!= 0 )
						{
							CardGui nueva= new CardGui(player1.player.pdeck.Deck.extraerR(),0,0); 
							appear(nueva);
						}else
						{
							gameover(this);
							try {
								setBackground(Color.RED);
								player1=new PlayGui(0,0,Nombre1,this);
							} catch (IOException e1) {
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
	
	void winner(JFrame jfm)
	{
		try {
			jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("frame3.jpg")))));
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

	public void doGameOver(){
		gameover(this);
		try {
			setBackground(Color.RED);
			player1=new PlayGui(0,0,Nombre1,this);
		} catch (IOException e1) {
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
	
	public void doWin(){
		winner(this);
		try {
			setBackground(Color.RED);
			player1=new PlayGui(0,0,Nombre1,this);
		} catch (IOException e1) {
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
	/************************/
	public static void main(String[] args)
	{
		Gui Juego=new Gui();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(player1!=null){
			if (e.getSource()==player1.player.pdeck.btnNewButton)
			{
				if(this.player1.getPhaseActual()==0)
				{	
					if(player1.cardDrawn==0){
						if(player1.player.pdeck.Deck.cardsLeft()!= 0 )
						{
							CardGui nueva= new CardGui(player1.player.pdeck.Deck.extraerR(),0,0);
							appear(nueva);
						}else
						{
							gameover(this);
							try {
								setBackground(Color.RED);
								player1=new PlayGui(0,0,Nombre1,this);
							} catch (IOException e1) {
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
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource()==player1.player.pdeck.btnNewButton)
		{
			player1.player.pdeck.btnNewButton.setIcon(new ImageIcon("draw2.png"));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource()==player1.player.pdeck.btnNewButton)
		{
			player1.player.pdeck.btnNewButton.setIcon(new ImageIcon("draw1.png"));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource()==player1.player.pdeck.btnNewButton)
		{
			player1.player.pdeck.btnNewButton.setIcon(new ImageIcon("draw3.png"));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource()==player1.player.pdeck.btnNewButton)
		{
			player1.player.pdeck.btnNewButton.setIcon(new ImageIcon("draw1.png"));
		}
	}
	public void appear(final CardGui card) {
		moving=card;
		player1.animations.add(moving);
		Thread t = new Thread(new Runnable() {
			public void run() {
				moving.setBounds(925-62,609-93,0,0);
				int i=0,j=0;
				while (i<=124 || j<=186) {
					try {
						if(i<=124){
							i++;
							moving.setBounds(925-62,609-93,i,j);
							Thread.sleep(1);
						}
						if(j<=186){
							j++;
							moving.setBounds(925-62,609-93,i,j);
					
							Thread.sleep(1);
						}
					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				i=925-62;
				while(i>=652)
				{
					i--;
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					moving.setLocation(i, 609-93);
				}
				player1.animations.remove(moving);
				player1.repairListeners(false);
				player1.player.pdeck.textField.setText("cards left "+player1.player.pdeck.Deck.cardsLeft());
				player1.cardDrawn=1;
				player1.player.pdeck.textField.repaint();
				setVisible(true);
				repaint();
			}
		});
		t.start();
	}
}