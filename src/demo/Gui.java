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
import java.io.IOException;
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
import extra.RollDice;
public class Gui extends JFrame implements ActionListener
{
	JPanel jp1,jp2,jp3;
	JButton b1,b2,b3;
	JLabel l1,demo;
	JTextArea text;
	String Nombre1;//nombre del jugador1
	private JMenuBar mb;
	private JMenu menu1,menu2;
	private JMenuItem mi1,mi2,mi3;
	static LoadData data;
	private JPanel contentPane;
	private PlayerGui player1;
	private deckCreator crear;
	JInternalFrame crea;
	RollDice dados;
   
	public Gui()
	{  
		setBounds(0,0, 1024, 768);  
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
		menu1=new JMenu("Opciones");
		mb.add(menu1);
		menu2=new JMenu("resoucion");
		menu1.add(menu2);
		mi1=new JMenuItem("900,650(default)");
		mi1.addActionListener(this);
		menu2.add(mi1);
		mi2=new JMenuItem("800x600");
		mi2.addActionListener(this);
		menu2.add(mi2);
		mi3=new JMenuItem("1024x768");
		mi3.addActionListener(this);
		menu2.add(mi3);         

		/***************************************/
		addbackgound(this);
		addjlabel1(this);

		text = new JTextArea();   
		text.setBounds(480, 580, 90, 20);
		text.setEditable(true);
		add(text);

		b1=new JButton("Play");
		b1.setBackground(Color.white);
		b1.setBorder(null);
		b1.setBounds(380 ,560,60,30);
		b1.setBorder(BorderFactory.createEmptyBorder());
		b1.addActionListener(this);
		add(b1);






		demo=new JLabel("<html><font color='white'>Demo version: 0.000011 </font></html>");
		demo.setBounds(870,660,300,30); //esto se mueve como horizontal vertical 100= h 200=v
		this.add(demo);


		/*********************************/



		setLocation(100, 50);
		setResizable(false);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==b1)
		{
			addbackground2(this);
			b2 = new JButton("Play");
			b2.setBackground(Color.BLACK);
			b2.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
			b2.setForeground(Color.WHITE);
			b2.setBounds(70, 50, 132, 43);
			b2.addActionListener(this);
			add(b2);

			b3=new JButton("Deck menu");
			b3.setForeground(Color.BLACK);
			b3.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
			b3.setBackground(Color.WHITE);
			b3.setBounds(70, 130, 132, 43);
			b3.addActionListener(this);
			add(b3);
			setVisible(true);
		}
	if(dados!=null){
		if(e.getSource()==dados.pane.rollButton)//dados
		{


			dados.pane.rollButton.setVisible(false);
			dados.btnPlay.setVisible(true);
			if(dados.pane.text.getText()=="1"){
				dados.label.setText("Fist turn is yours");
			}
			else{
				dados.label.setText("AI player gets the  first turn");
			}
			dados.label.setVisible(true);
			dados.btnPlay.setVisible(true);

		}
			
	if(e.getSource()==dados.btnPlay)
		{

		try {
			player1=new PlayerGui(0,0,Nombre1);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			Nombre1=text.getText();//guarda el nombre del jugador en Nombre1
			player1.deck.btnNewButton_1.addActionListener(this);//para que se puedan usar los botones del deck
			player1.deck.btnNewButton.addActionListener(this);
			addbackground3(this);
			getContentPane().setLayout(null);

			add(player1);
			setVisible(true);
		}

	}
		if (e.getSource()==b2)//cuando se le da click al boton 1
		{


			Nombre1=text.getText();//guarda el nombre del jugador en Nombre1
			dados= new RollDice();
			dados.pane.rollButton.addActionListener(this);

			addbackground4(this);
			getContentPane().setLayout(null);


			add(dados);
			dados.pane.rollButton.addActionListener(this);
			dados.btnPlay.addActionListener(this);

			setVisible(true);
		}
	if(player1!=null){
		if (e.getSource()==player1.deck.btnNewButton)
		{
			if(player1.barriers.findwhere()!=-1)
			{
				if(player1.deck.Deck.cardsLeft()!= 0 )
				{

					player1.barriers.addbarrier(player1.deck.Deck.extraerR());
					player1.deck.textField.setText("cards left "+player1.deck.Deck.cardsLeft());
					player1.deck.textField.repaint();

					repaint();
				}else
				{
					
					gameover(this);
					
				
					try {
						player1=new PlayerGui(0,0,Nombre1);

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
				

			}
		}
		
	
	
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
		}
		if (e.getSource()==mi2) {
			setSize(800,600);
		}
		if (e.getSource()==mi3) {
			setSize(1024,768);
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

}
