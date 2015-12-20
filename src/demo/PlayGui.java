package demo;
import demo.HandGui;
import demo.Fallen.SimpleColorTableModel;
import demo.DeckGui;
import demo.CardGui;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import data.LoadData;

import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.SwingConstants;
import javax.swing.JSlider;
import java.awt.Canvas;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextPane;
import java.awt.Rectangle;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;


public class PlayGui extends JLayeredPane implements ActionListener, MouseListener {

	
	
	public fightpane fight;//panel donde se muestra la animacion de peleas
	public PlayerGui player;//aca estan el hand el deck y lo demas
	public AIGui ai;//lo mismo pero en el ai
	public Previewpane preview;//aca se muestra la carta


	
	optionpane op;
	int turn, contTurn=0;
	int acampo=-1;
	int i=0;
	private Fallen fallen ;
	private LoadData cartas;
	JInternalFrame pane; 
	public Phases phases;
	public JButton changePhase,repaint;
	private FileReader turno;
	private BufferedReader br;
	private JLabel turnoLabel;
	private Drained_2 aidra;
	int warriorPlayed; //indica que se jugo un warrior en el turno
	public int cardDrawn, barierpicked;
	public JLabel swordp1,swordp2,swordp3,swordp4,swordp5;
	public JLabel sworda1,sworda2,sworda3,sworda4,sworda5;
	public JInternalFrame menu1, menu2, menu3, menu4, menu5;
	public JButton attack1, attack2, attack3, attack4, attack5, undrain;
	public JButton dest1, dest2, dest3, dest4, dest5;
	public int atkDest=-1, atkOrigin=-1;
	public int [] aiAttack= new int[5];
	public int [] aiDest= new int[5];
	public int contTargetAttack;
	public JButton j;
	public prueba2 listAll;
	//public Effects effects;
	
	public int getPhaseActual(){
		return phases.actual;
	}
	
	public PlayGui(int x , int y, String name) throws IOException {
		setBorder(null);
		player=new PlayerGui(x,y,name);
		preview= new Previewpane();
		
		player.hand.addMouseListener(this);
		setOpaque(false);
		setLayout(null);
		setBounds(0,0, 1024, 768);
		cardDrawn=0;
		fight=new fightpane();
		moveToFront(fight);//takes to front fightpane
		this.add(fight);
		JLabel name_1 = new JLabel("Player : "+ name);
		add(name_1);
		name_1.setForeground(new Color(255, 248, 220));
		name_1.setBackground(Color.WHITE);
		name_1.setHorizontalAlignment(SwingConstants.CENTER);
		name_1.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		name_1.setBounds(780, 450, 176, 64);
		

		pane = new JInternalFrame("THE FALLEN");

		phases=new Phases(220,300);
		add(phases);
		
		this.add(player);
		repaint=new JButton();

	
		ai = new AIGui();
		

	
		
		this.add(ai);

		/*******************************************/
		

	
		this.add(preview);
		
	
		player.barriers.addMouseListener(this);
		
		
		aidra=new Drained_2(860,0);//mover al ai 
		add(aidra);
		repaint();
		for(int i=1;i<=5;i++)
		{
			int pos= player.hand.draw(player.pdeck.Deck.extraerR());
			player.barriers.addbarrier(player.pdeck.Deck.extraerR());
			
			 Addlisteners2Card(pos-1);
			player.hand.handgui[pos-1].addMouseListener(this);					 //DE HAND A FIELD
			ai.aideck.textField.setText("cards left "+ ai.aideck.Deck.cardsLeft());
			player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
			player.pdeck.textField.repaint();
			

		}
		System.out.println(""+player.pdeck.Deck + "  " +ai.aideck.Deck);
	
		fallen=new Fallen();
		add(fallen);
		
		this.phases.draw.addActionListener(this);
		op=new  optionpane();
		//swords
		swordp1= new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
		swordp1.setBounds(0, 0, 535, 830);
		add(swordp1);
		this.moveToFront(swordp1);
		
		sworda1= new JLabel(new ImageIcon(ImageIO.read(new File("swordR.png"))));
		sworda1.setBounds(0, 0, 540, 385);
		add(sworda1);
		this.moveToFront(sworda1);
		
		swordp2= new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
		swordp2.setBounds(0, 0, 760, 830);
		add(swordp2);
		this.moveToFront(swordp2);
		
		sworda2= new JLabel(new ImageIcon(ImageIO.read(new File("swordR.png"))));
		sworda2.setBounds(0, 0, 760, 385);
		add(sworda2);
		this.moveToFront(sworda2);
		
		swordp3= new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
		swordp3.setBounds(0, 0, 980, 830);
		add(swordp3);
		this.moveToFront(swordp3);
		
		sworda3= new JLabel(new ImageIcon(ImageIO.read(new File("swordR.png"))));
		sworda3.setBounds(0, 0, 980, 385);
		add(sworda3);
		this.moveToFront(sworda3);
		
		swordp4= new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
		swordp4.setBounds(0, 0, 1200, 830);
		add(swordp4);
		this.moveToFront(swordp4);
		
		sworda4= new JLabel(new ImageIcon(ImageIO.read(new File("swordR.png"))));
		sworda4.setBounds(0, 0, 1200, 385);
		add(sworda4);
		this.moveToFront(sworda4);
		
		swordp5= new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
		swordp5.setBounds(0, 0, 1420, 830);
		add(swordp5);
		this.moveToFront(swordp5);
		
		sworda5= new JLabel(new ImageIcon(ImageIO.read(new File("swordR.png"))));
		sworda5.setBounds(0, 0, 1420, 385);
		add(sworda5);
		this.moveToFront(sworda5);
		
		this.swordp1.setVisible(false);
		this.swordp2.setVisible(false);
		this.swordp3.setVisible(false);
		this.swordp4.setVisible(false);
		this.swordp5.setVisible(false);
		this.sworda1.setVisible(false);
		this.sworda2.setVisible(false);
		this.sworda3.setVisible(false);
		this.sworda4.setVisible(false);
		this.sworda5.setVisible(false);
		
		try{
			turno = new FileReader(new File("turno.txt"));
			br= new BufferedReader(turno);
			this.turn = Integer.parseInt(br.readLine());
			turno.close();
		}catch(Exception e2){ 
            e2.printStackTrace();
         }
		
		this.turnoLabel = new JLabel("");
		if(turn==1){//turno player 1
			this.turnoLabel.setText("Turn Player");
		}else{ //turno player 2
			this.turnoLabel.setText("Turn AI Player");
		}
		this.turnoLabel.setBounds(800, 300, 140, 20);
		this.turnoLabel.setForeground(new Color(255, 248, 220));
		this.turnoLabel.setBackground(Color.WHITE);
		this.turnoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.turnoLabel.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 15));
		add(turnoLabel);
		
		menu1 = new JInternalFrame();
		menu1.getContentPane().setLayout(null);
		attack1 = new JButton("A");
		attack1.setBounds(15, 11, 49, 20);
		menu1.getContentPane().add(attack1);
		menu1.setClosable(true);
		menu1.setBounds(230,380,80,70);
		menu1.setVisible(false);
		add(menu1);
		menu1.moveToFront();
		
		menu2 = new JInternalFrame();
		menu2.getContentPane().setLayout(null);
		attack2 = new JButton("A");
		attack2.setBounds(15, 11, 49, 20);
		menu2.getContentPane().add(attack2);
		menu2.setClosable(true);
		menu2.setBounds(340,380,80,70);
		menu2.setVisible(false);
		add(menu2);
		menu2.moveToFront();
		
		menu3 = new JInternalFrame();
		menu3.getContentPane().setLayout(null);
		attack3 = new JButton("A");
		attack3.setBounds(15, 11, 49, 20);
		menu3.getContentPane().add(attack3);
		menu3.setClosable(true);
		menu3.setBounds(450,380,80,70);
		menu3.setVisible(false);
		add(menu3);
		menu3.moveToFront();
		
		menu4= new JInternalFrame();
		menu4.getContentPane().setLayout(null);
		attack4 = new JButton("A");
		attack4.setBounds(15, 11, 49, 20);
		menu4.getContentPane().add(attack4);
		menu4.setClosable(true);
		menu4.setBounds(560,380,80,70);
		menu4.setVisible(false);
		add(menu4);
		menu4.moveToFront();
		
		menu5= new JInternalFrame();
		menu5.getContentPane().setLayout(null);
		attack5 = new JButton("A");
		attack5.setBounds(15, 11, 49, 20);
		menu5.getContentPane().add(attack5);
		menu5.setClosable(true);
		menu5.setBounds(670,380,80,70);
		menu5.setVisible(false);
		add(menu5);
		menu5.moveToFront();
		
		this.dest1 = new JButton("Target");
		this.dest1.setBounds(230, 210, 80, 20);
		this.dest1.setVisible(false);
		add(dest1);
		this.moveToFront(this.dest1);
		
		this.dest2 = new JButton("Target");
		this.dest2.setBounds(340, 210, 80, 20);
		this.dest2.setVisible(false);
		add(dest2);
		this.moveToFront(this.dest2);
		
		this.dest3 = new JButton("Target");
		this.dest3.setBounds(450, 210, 80, 20);
		this.dest3.setVisible(false);
		add(dest3);
		this.moveToFront(this.dest3);
		
		this.dest4 = new JButton("Target");
		this.dest4.setBounds(560, 210, 80, 20);
		this.dest4.setVisible(false);
		add(dest4);
		this.moveToFront(this.dest4);
		
		this.dest5 = new JButton("Target");
		this.dest5.setBounds(670, 210, 80, 20);
		this.dest5.setVisible(false);
		add(dest5);
		this.moveToFront(this.dest5);
		
		this.attack1.addActionListener(this);
		this.attack2.addActionListener(this);
		this.attack3.addActionListener(this);
		this.attack4.addActionListener(this);
		this.attack5.addActionListener(this);
		
		this.dest1.addActionListener(this);
		this.dest2.addActionListener(this);
		this.dest3.addActionListener(this);
		this.dest4.addActionListener(this);
		this.dest5.addActionListener(this);
		
		j = new JButton("prueba de listar cartas de deck para efectos");
		j.setBounds(100, 100, 120, 50);
		add(j);
		j.addActionListener(this);
		this.moveToFront(this.j);
		player.field.addMouseListener(this);
		/*
		this.listAll = new prueba2(player.pdeck.Deck);
		
		this.listAll.setBounds(150, 100, 620, 420);
		this.listAll.setVisible(false);
		add(this.listAll);
		this.moveToFront(this.listAll);
		
		this.listAll.aceptar.addActionListener(this);
		*/
		this.listAll = new prueba2(player.pdeck.Deck);
		
		this.listAll.setBounds(150, 100, 620, 420);
		add(this.listAll);
		this.moveToFront(this.listAll);
		
		this.listAll.aceptar.addActionListener(this);
		this.listAll.setVisible(false);
	}
	
	public void actionPerformed(ActionEvent e) {
	
		int done=0;
		if (e.getSource()==player.pdeck.btnNewButton_1)// si se le da click al boton de fallen 
		{
				
			fallen.setVisible(true);
			moveToFront(fallen);

		}
		
		if(e.getSource()==player.hand.handgui[0].Set)
		{
			if(done==0){
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[0]);	
				player.powers.set(1);
				player.hand.discard(1);
				
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[1].Set)
		{
			if(done==0){
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[1]);	
				player.powers.set(1);
				player.hand.discard(2);
				
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[2].Set)
		{
			if(done==0){
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[2]);	
				player.powers.set(1);
				player.hand.discard(3);
				
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[3].Set)
		{
			if(done==0){
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[3]);	
				player.powers.set(1);
				player.hand.discard(4);
				
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[4].Set)
		{
			if(done==0){
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[4]);	
				player.powers.set(1);
				player.hand.discard(5);
				
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[0].Play)//si se le da play a la carta 1  
		{
			if(done==0){
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[0]);	
				play(0);
				
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[1].Discard)//si se le da play a la carta 1  
		{
			if(done==0){
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[1]);
				player.hand.discard(2);
				
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[2].Discard)//si se le da play a la carta 1  
		{
			if(done==0)
			{
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[2]);
				player.hand.discard(3);
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[3].Discard)//si se le da play a la carta 1  
		{
			if(done==0)
			{
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[3]);
				player.hand.discard(4);
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[4].Discard)//si se le da play a la carta 1  
		{
			if(done==0)
			{
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[4]);
				player.hand.discard(5);
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[0].Preview)//muestra la carta 1 
		{
			if(done==0)
			{
				preview.addCard(new BigCard(player.hand.handgui[0].getcard(),0,0));
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[1].Preview)//muestra la carta 1 
		{
			if(done==0)
			{
				preview.addCard(new BigCard(player.hand.handgui[1].getcard(),0,0));
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[2].Preview)//muestra la carta 1 
		{
			if(done==0)
			{
				preview.addCard(new BigCard(player.hand.handgui[2].getcard(),0,0));
			}
			done=1;
			
		}
		
		if(e.getSource()==player.hand.handgui[3].Preview)//muestra la carta 1 
		{
			if(done==0)
			{
				preview.addCard(new BigCard(player.hand.handgui[3].getcard(),0,0));
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[4].Preview)//muestra la carta 1 
		{
			if(done==0)
			{
				preview.addCard(new BigCard(player.hand.handgui[4].getcard(),0,0));
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[0].Discard)//si se le da play a la carta 1  
		{
			if(done==0)
				player.hand.discard(1);
			done=1;
			
		}
		
		if(e.getSource()==player.hand.handgui[1].Play)//si se le da play a la carta 2  
		{
			if(done==0)
			play(1);
			done=1;
			
			
		}if(e.getSource()==player.hand.handgui[2].Play)//si se le da play a la carta 3
		{
			if(done==0)
			play(2);
			done=1;
			
		}if(e.getSource()==player.hand.handgui[3].Play)//si se le da play a la carta 4 
		{
			if(done==0)
			play(3);
			done=1;
		}if(e.getSource()==player.hand.handgui[4].Play)//si se le da play a la carta 5
		{
			if(done==0)
			play(4);
			done=1;
			
		}
		
		if(e.getSource()==ai.aideck.btnNewButton){
			System.out.println("toque deck ai");
		}
		
		if((e.getSource()==changePhase)||(e.getSource()==phases.setup)||(e.getSource()==phases.draw)||(e.getSource()==phases.action)||(e.getSource()==phases.attack)||(e.getSource()==phases.end)){
			//System.out.println(turn);
		
				done=1;
			if(phases.actual<4){
				phases.change(phases.actual+1);
		
			}else{
				phases.change(0);
		
			}
			
			switch(phases.actual){
				//setup
			
				case 0:
					
					barierpicked=0;
					warriorPlayed=0;
					cardDrawn=0;
					
					for(int i=0;i<5;i++)
						player.hand.handgui[i].Play.setEnabled(false);
					
					if(turn==1)
					{
						JOptionPane.showMessageDialog(null, "you get 1 volatile power, use it wisely");
						player.powers.set(1);
						this.phases.setup.removeActionListener(this);
						this.phases.draw.addActionListener(this);
						
						//enable deck
						//disable barriers
						for (int i=0;i<5;i++)
							player.barriers.barriers[i].removeMouseListener(this);
						//disable hand
						
						//disable field
						//disable battle phase
						//disable end turn
					}
					else{
						this.phases.setup.removeActionListener(this);
						this.phases.draw.addActionListener(this);
						ai.aideck.btnNewButton.addActionListener(this);
						try {
							Aiturn();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				break;
				//draw
				case 1:
					this.phases.draw.removeActionListener(this);
					this.phases.action.addActionListener(this);
					
					//disable deck: done
					//enable barriers
					for (int i=0;i<5;i++)
						player.barriers.barriers[i].addMouseListener(this);
					//disable hand
					
					//disable field
					//disable battle phase
					//disable end turn
				break;
				//action
				case 2:
					this.phases.action.removeActionListener(this);
					this.phases.attack.addActionListener(this);
					
					//disable deck: done
					//disable barriers
					for (int i=0;i<5;i++)
						player.barriers.barriers[i].removeMouseListener(this);
					for(int i=0;i<5;i++)
						player.hand.handgui[i].Play.setEnabled(true);
					//enable field
					//disable battle phase
					//disable end turn
				break;
				//attack
				case 3:
					this.phases.attack.removeActionListener(this);
					this.phases.end.addActionListener(this);
					
					//disable deck: done
					//disable barriers
					for (int i=0;i<5;i++)
						player.barriers.barriers[i].removeMouseListener(this);
					//disable hand
					for(int i=0;i<5;i++)
						player.hand.handgui[i].Play.setEnabled(false);
					//enable field
					//enable battle phase
					if(this.turn==1&&this.contTurn>0){
						for(int i=0;i<5;i++){
							if(player.field.cards[i]!=null){
								switch(i){
								case 0:
									if(!this.player.field.cards[0].down){
										this.swordp1.setVisible(true);
									}
									break;
								case 1:
									if(!this.player.field.cards[1].down){
										this.swordp2.setVisible(true);
									}
									break;
								case 2:
									if(!this.player.field.cards[2].down){
										this.swordp3.setVisible(true);
									}
									break;
								case 3:
									if(!this.player.field.cards[3].down){
										this.swordp4.setVisible(true);
									}
									break;
								case 4:
									if(!this.player.field.cards[4].down){
										this.swordp5.setVisible(true);
									}
									break;
								}
							}
						}
					}
					else{
						/*this.sworda1.setVisible(true);
						this.sworda2.setVisible(true);
						this.sworda3.setVisible(true);
						this.sworda4.setVisible(true);
						this.sworda5.setVisible(true);*/
					}
					
					//disable end turn
					
					
					
				break;
				//end turn
				case 4:
					
					this.phases.end.removeActionListener(this);
					this.phases.setup.addActionListener(this);
					
					//disable deck: done
					//disable barriers
				
					//disable hand
				
					//disable field
					//disable battle phase
					this.swordp1.setVisible(false);
					this.swordp2.setVisible(false);
					this.swordp3.setVisible(false);
					this.swordp4.setVisible(false);
					this.swordp5.setVisible(false);
					this.sworda1.setVisible(false);
					this.sworda2.setVisible(false);
					this.sworda3.setVisible(false);
					this.sworda4.setVisible(false);
					this.sworda5.setVisible(false);
					
					this.menu1.setVisible(false);
					this.menu2.setVisible(false);
					this.menu3.setVisible(false);
					this.menu4.setVisible(false);
					this.menu5.setVisible(false);
					
					this.dest1.setVisible(false);
					this.dest2.setVisible(false);
					this.dest3.setVisible(false);
					this.dest4.setVisible(false);
					this.dest5.setVisible(false);
					//enable end turn
					
					if(turn==1){
						turn=2;
						this.turnoLabel.setText("Turn AI Player");
					}else{
						turn=1;
						this.turnoLabel.setText("Turn Player");
					}
					
					repaint();
					repaint();
					this.contTurn++;
					this.atkDest=this.atkOrigin=-1;
				break;
				
			}
			if(this.phases.actual==4){
				this.phases.setup.doClick();	
			}
			repaint();
		}
		
	done=0;
		if(e.getSource()==this.attack1||e.getSource()==this.attack2||e.getSource()==this.attack3||e.getSource()==this.attack4||e.getSource()==this.attack5){
			
			if(e.getSource()==this.attack1){
				this.atkOrigin=1;
			}
			if(e.getSource()==this.attack2){
				this.atkOrigin=2;
			}
			if(e.getSource()==this.attack3){
				this.atkOrigin=3;
			}
			if(e.getSource()==this.attack4){
				this.atkOrigin=4;
			}
			if(e.getSource()==this.attack5){
				this.atkOrigin=5;
			}
			JOptionPane.showMessageDialog(null, "Select Your Target");
			if(ai.aifield.cards[0]!=null){
				this.dest1.setVisible(true);
			}
			if(ai.aifield.cards[1]!=null){
				this.dest2.setVisible(true);
			}
			if(ai.aifield.cards[2]!=null){
				this.dest3.setVisible(true);
			}
			if(ai.aifield.cards[3]!=null){
				this.dest4.setVisible(true);
			}
			if(ai.aifield.cards[4]!=null){
				this.dest5.setVisible(true);
			}
		}
		
		if(e.getSource()==this.dest1||e.getSource()==this.dest2||e.getSource()==this.dest3||e.getSource()==this.dest4||e.getSource()==this.dest5){
			if(e.getSource()==this.dest1){
				this.atkDest=1;
			}
			if(e.getSource()==this.dest2){
				this.atkDest=2;
			}
			if(e.getSource()==this.dest3){
				this.atkDest=3;
			}
			if(e.getSource()==this.dest4){
				this.atkDest=4;
			}
			if(e.getSource()==this.dest5){
				this.atkDest=5;
			}
			
			this.menu1.setVisible(false);
			this.menu2.setVisible(false);
			this.menu3.setVisible(false);
			this.menu4.setVisible(false);
			this.menu5.setVisible(false);
			
			JOptionPane.showMessageDialog(null, "Card "+this.atkOrigin+" attack to ai Card "+this.atkDest);
			remove(phases);
			if(!fight.isVisible()){
			fight.addCards(new BigCard(player.field.cards[atkOrigin-1].getcard(),0,0),new BigCard(ai.aifield.cards[atkDest-1].getcard(),0,0));
			}
			if(player.field.cards[atkOrigin-1].getcard().GetHp()>ai.aifield.cards[atkDest-1].getcard().GetHp())
			{
				ai.aifield.quitar(atkDest-1);
				
			}else if(player.field.cards[atkOrigin-1].getcard().GetHp()<ai.aifield.cards[atkDest-1].getcard().GetHp())
			{
				player.field.quitar(atkOrigin-1);
			}
			add(phases);
			this.dest1.setVisible(false);
			this.dest2.setVisible(false);
			this.dest3.setVisible(false);
			this.dest4.setVisible(false);
			this.dest5.setVisible(false);
			
		}
		
		if(e.getSource()==j){
			this.listAll = new prueba2(player.pdeck.Deck);
			
			this.listAll.setBounds(150, 100, 620, 420);
			add(this.listAll);
			this.moveToFront(this.listAll);
			
			this.listAll.aceptar.addActionListener(this);
			this.listAll.setVisible(true);
			//System.out.println(this.listAll.current.actual.GetName());
		}
		
		if(e.getSource()==this.listAll.aceptar){
			this.listAll.setVisible(false);
			this.listAll.opciones.setVisible(false);
			
			int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(this.listAll.num));
			player.hand.handgui[pos-1].addMouseListener(this);
			 Addlisteners2Card(pos-1);
			this.player.pdeck.textField.setText("cards left "+this.player.pdeck.Deck.cardsLeft());
			this.player.pdeck.textField.repaint();

			this.listAll.removeAll();
			repaint();
		}
		
	}




	public class CirclePanel extends JPanel {

		/**
		 * 
		 */


		@Override
		protected void paintComponent(Graphics g) {
			g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		}
	}
	
	void Addlisteners2Card(int i)//le coloca listeners al menu de la carta 
	{
		
		player.hand.handgui[i].Play.addActionListener(this);
		player.hand.handgui[i].Discard.addActionListener(this);
		player.hand.handgui[i].Preview.addActionListener(this);
		player.hand.handgui[i].Set.addActionListener(this);
	}
	public DeckGui getDeck() {
		return player.pdeck;
	}

	public void mouseClicked(MouseEvent e) 
	{
		
		if(e.getButton() == MouseEvent.BUTTON1)
		{if(e.getClickCount()==1)
		{
			if(barierpicked==0)
			{
			if(e.getSource()==player.barriers.barriers[0])//si se da click a la barrera 0
			{
				int pos= player.hand.draw(player.barriers.cards[0]);
				player.hand.handgui[pos-1].addMouseListener(this);
				 Addlisteners2Card(pos-1);
				player.barriers.removebarrier(0);
				barierpicked=1;
				repaint();
			}
			if(e.getSource()==player.barriers.barriers[1])
			{
				int pos= player.hand.draw(player.barriers.cards[1]);
				player.hand.handgui[pos-1].addMouseListener(this);
				 Addlisteners2Card(pos-1);
				player.barriers.removebarrier(1);
				barierpicked=1;
				repaint();
			}
			if(e.getSource()==player.barriers.barriers[2])
			{
				int pos= player.hand.draw(player.barriers.cards[2]);
				player.hand.handgui[pos-1].addMouseListener(this);
				 Addlisteners2Card(pos-1);
				player.barriers.removebarrier(2);
				barierpicked=1;
				repaint();
			}
			if(e.getSource()==player.barriers.barriers[3])
			{
				int pos= player.hand.draw(player.barriers.cards[3]);
				player.hand.handgui[pos-1].addMouseListener(this);
				 Addlisteners2Card(pos-1);
				player.barriers.removebarrier(3);
				barierpicked=1;
				repaint();
			}
			if(e.getSource()==player.barriers.barriers[4])
			{
				int pos= player.hand.draw(player.barriers.cards[4]);
				player.hand.handgui[pos-1].addMouseListener(this);
				 Addlisteners2Card(pos-1);
				player.barriers.removebarrier(4);
				barierpicked=1;
				repaint();
			}
			
		}else
		{
			if(phases.actual==1){
				JOptionPane.showMessageDialog(op, "sorry u can only get a card from barriers per turn");
			}	
			
		}				
			
			if(e.getSource()==player.hand.handgui[0])
			{
				player.hand.handgui[0].menu.setVisible(true);
				player.hand.handgui[1].menu.setVisible(false);
				player.hand.handgui[2].menu.setVisible(false);
				player.hand.handgui[3].menu.setVisible(false);
				player.hand.handgui[4].menu.setVisible(false);
			}
			else if(e.getSource()==player.hand.handgui[1])
			{
				player.hand.handgui[1].menu.setVisible(true);
				player.hand.handgui[0].menu.setVisible(false);
				player.hand.handgui[2].menu.setVisible(false);
				player.hand.handgui[3].menu.setVisible(false);
				player.hand.handgui[4].menu.setVisible(false);
			}
			else if(e.getSource()==player.hand.handgui[2])
			{
				player.hand.handgui[2].menu.setVisible(true);
				player.hand.handgui[0].menu.setVisible(false);
				player.hand.handgui[1].menu.setVisible(false);
				player.hand.handgui[3].menu.setVisible(false);
				player.hand.handgui[4].menu.setVisible(false);
			}
			else if(e.getSource()==player.hand.handgui[3])
			{
				player.hand.handgui[3].menu.setVisible(true);
				player.hand.handgui[0].menu.setVisible(false);
				player.hand.handgui[1].menu.setVisible(false);
				player.hand.handgui[2].menu.setVisible(false);
				player.hand.handgui[4].menu.setVisible(false);
				
			}else if(e.getSource()==player.hand.handgui[4])
			{
				player.hand.handgui[4].menu.setVisible(true);
				player.hand.handgui[0].menu.setVisible(false);
				player.hand.handgui[1].menu.setVisible(false);
				player.hand.handgui[2].menu.setVisible(false);
				player.hand.handgui[3].menu.setVisible(false);
			}
			if(phases.actual==3&&this.contTurn>0){
				if(e.getSource()==player.field.cards[0]&&!player.field.cards[0].down)
				{
					this.menu1.setVisible(true);
					this.menu2.setVisible(false);
					this.menu3.setVisible(false);
					this.menu4.setVisible(false);
					this.menu5.setVisible(false);
				}	
				if(e.getSource()==player.field.cards[1]&&!player.field.cards[1].down)
				{
					this.menu2.setVisible(true);
					this.menu1.setVisible(false);
					this.menu3.setVisible(false);
					this.menu4.setVisible(false);
					this.menu5.setVisible(false);
				}
				if(e.getSource()==player.field.cards[2]&&!player.field.cards[2].down)
				{
					this.menu3.setVisible(true);
					this.menu1.setVisible(false);
					this.menu2.setVisible(false);
					this.menu4.setVisible(false);
					this.menu5.setVisible(false);
				}
				if(e.getSource()==player.field.cards[3]&&!player.field.cards[3].down)
				{
					this.menu4.setVisible(true);
					this.menu1.setVisible(false);
					this.menu2.setVisible(false);
					this.menu3.setVisible(false);
					this.menu5.setVisible(false);
					
				}if(e.getSource()==player.field.cards[4]&&!player.field.cards[4].down)
				{
					this.menu5.setVisible(true);
					this.menu1.setVisible(false);
					this.menu2.setVisible(false);
					this.menu3.setVisible(false);
					this.menu4.setVisible(false);
				}
			}
		}
		}	    
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			if(e.getClickCount()==1)
			{
				
			
				if(e.getSource()==player.field.cards[0])
				{
					player.powers.undrain(player.field.cards[0].getcard().GetCost());
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[0].getcard());
					player.field.quitar(0);
				}	
				if(e.getSource()==player.field.cards[1])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[1].getcard());
					player.powers.undrain(player.field.cards[1].getcard().GetCost());
					player.field.quitar(1);
				}
				if(e.getSource()==player.field.cards[2])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[2].getcard());
					player.powers.undrain(player.field.cards[2].getcard().GetCost());
					player.field.quitar(2);
				}
				if(e.getSource()==player.field.cards[3])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[3].getcard());
					player.powers.undrain(player.field.cards[3].getcard().GetCost());
					player.field.quitar(3);
					
				}if(e.getSource()==player.field.cards[4])
				{
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[4].getcard());
					
					player.powers.undrain(player.field.cards[4].getcard().GetCost());
					player.field.quitar(4);
				}
				
				
			}
		}
	}

	public void mousePressed(MouseEvent e)
	{

	}
	public void mouseReleased(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e)
	{

	}


	public void mouseExited(MouseEvent e) {
		if(e.getSource()==player.hand.handgui[0])
		{
			player.hand.handgui[0].setBounds(0, 20, 124, 186);
			preview.Remove();
		}
		else if(e.getSource()==player.hand.handgui[1])
		{
			player.hand.handgui[1].setBounds(124, 20, 124, 186);
			preview.Remove();
		}
		else if(e.getSource()==player.hand.handgui[2])
		{
			player.hand.handgui[2].setBounds(248, 20, 124, 186);
			preview.Remove();
		}
		else if(e.getSource()==player.hand.handgui[3])
		{
			player.hand.handgui[3].setBounds(372, 20, 124, 186);
			preview.Remove();
		}else if(e.getSource()==player.hand.handgui[4])
		{
			player.hand.handgui[4].setBounds(496, 20, 124, 186);
			preview.Remove();
		}
		if(e.getSource()==player.field.cards[0])
		{
			
			preview.Remove();
		}
		if(e.getSource()==player.field.cards[1])
		{
			
			preview.Remove();
		}
		if(e.getSource()==player.field.cards[2])
		{
			
			preview.Remove();
		}
		if(e.getSource()==player.field.cards[3])
		{
			
			preview.Remove();
		}
		if(e.getSource()==player.field.cards[4])
		{
			
			preview.Remove();
		}
		if(e.getSource()==ai.aifield.cards[0])
		{
			preview.Remove();
			
		}
		if(e.getSource()==ai.aifield.cards[1]||e.getSource()==ai.aifield.cards[2]|| e.getSource()==ai.aifield.cards[3]||e.getSource()==ai.aifield.cards[4] )
		{
			preview.Remove();
			
		}
	}



	public void mouseMoved(MouseEvent e) {

	}


	public void mouseEntered(MouseEvent e) 
	
	{
		if(e.getSource()==player.hand.handgui[0])
		{
			player.hand.handgui[0].setBounds(0, 0, 124, 186);
			
		}
		else if(e.getSource()==player.hand.handgui[1])
		{
			player.hand.handgui[1].setBounds(124, 0, 124, 186);
			
		}
		else if(e.getSource()==player.hand.handgui[2])
		{
			player.hand.handgui[2].setBounds(248, 0, 124, 186);
			
		}
		else if(e.getSource()==player.hand.handgui[3])
		{
			player.hand.handgui[3].setBounds(372, 0, 124, 186);
			
		}else if(e.getSource()==player.hand.handgui[4])
		{
			player.hand.handgui[4].setBounds(496, 0, 124, 186);
		
		}
		
		if(e.getSource()==player.field.cards[0])
		{
			
			preview.addCard(new BigCard(player.field.cards[0].getcard(),0,0));
		}
		if(e.getSource()==player.field.cards[1])
		{
			
			preview.addCard(new BigCard(player.field.cards[1].getcard(),0,0));
		}if(e.getSource()==player.field.cards[2])
		{
			
			preview.addCard(new BigCard(player.field.cards[2].getcard(),0,0));
		}if(e.getSource()==player.field.cards[3])
		{
			
			preview.addCard(new BigCard(player.field.cards[3].getcard(),0,0));
		}if(e.getSource()==player.field.cards[4])
		{
			
			preview.addCard(new BigCard(player.field.cards[4].getcard(),0,0));
		}
		if(e.getSource()==ai.aifield.cards[0])
		{
			preview.addCard(new BigCard(ai.aifield.cards[0].getcard(),0,0));
		
			
		}
		if(e.getSource()==ai.aifield.cards[1])
		{
			preview.addCard(new BigCard(ai.aifield.cards[1].getcard(),0,0));
			
			
		}
		if(e.getSource()==ai.aifield.cards[2])
		{
			preview.addCard(new BigCard(ai.aifield.cards[2].getcard(),0,0));
			
			
		}
		if(e.getSource()==ai.aifield.cards[3])
		{
			preview.addCard(new BigCard(ai.aifield.cards[3].getcard(),0,0));
			
			
		}
		if(e.getSource()==ai.aifield.cards[4])
		{
			preview.addCard(new BigCard(ai.aifield.cards[4].getcard(),0,0));
			
			
		}
	}
	
	void play(int pos)// plays a card on field
	{
	
		if (player.hand.handgui[pos].getcard().GetCost() + player.powers.currentundrained <= 12) {//verifica que haya mana

			if ( warriorPlayed == 0 ||(player.hand.handgui[pos].getcard().GetType()!="Warrior" && warriorPlayed ==1 )) {//verifica que un warrior no se ha jugado en ese turno
				int where = player.field.findwhere();// busca en donde poner la carta
				if (where != -1) {

					SmallCard carta;
					if (player.hand.handgui[pos].getcard().GetType() == "Warrior") {
						
						
						warriorPlayed = 1;
						System.out.println(this.warriorPlayed);

					}
					try {
						Random randomGenerator = new Random();
						int test = randomGenerator.nextInt(10);
						if (test % 2 == 0) {
							carta = new SmallCard(true,player.hand.handgui[pos].getcard());

						} else {
							carta = new SmallCard(false, player.hand.handgui[pos].getcard());

						}
						player.powers.drain(player.hand.handgui[pos].getcard().GetCost());
						
						repaint();
						carta.addMouseListener(this);

						player.field.poner(carta, where);
						player.hand.discard(1);

						repaint();

						this.makeEffect(carta.actual.Getid(),where);

					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}else
			{
				JOptionPane.showMessageDialog(op, "Sorry , u can only play a warrior on each turn");
				
			}
		} else {

			JOptionPane.showMessageDialog(op, "Sorry , u dont have enough powers to play it");
		}

		
	}
	
	public void Aiturn() throws IOException//aqui se programara a lo salvaje el turno del ai
		{
		JOptionPane.showMessageDialog(null,"ai is preparing an attack" );
		int which;
		JOptionPane.showMessageDialog(null, "ai gets a volatile powers");
		aidra.get(1);
		JOptionPane.showMessageDialog(null, "ai gets a card from deck");
		ai.barriers.addbarrier(ai.aideck.Deck.extraerR());
		phases.change(phases.actual+1);
		ai.aideck.textField.setText("cards left "+ai.aideck.Deck.cardsLeft());
		ai.aideck.textField.repaint();
		JOptionPane.showMessageDialog(null, "ai gets a card from barriers");
		phases.change(phases.actual+1);
		which=ai.barriers.findwhich();//verifica que exista un barrier
	if(ai.aihand.current!=5){	
		if(which!=-1)//existe un barrier
		{
			int pos= ai.aihand.draw(ai.barriers.cards[which]);
			ai.barriers.removebarrier(which);
			
		}
	}	
		setVisible(true);
		JOptionPane.showMessageDialog(null,"ai is playing a card" );
			ai.smartPlay();
			phases.change(phases.actual+1);
			//attack phase 
			JOptionPane.showMessageDialog(null,"ai is preparing an attack" );
			
			if(this.contTurn>0){
				for(int i=0; i<5; i++){
					this.aiAttack[i]=-1;
					this.aiDest[i]=-1;
				}
				contTargetAttack=0;
				for(int i=0;i<5;i++){
					if(ai.aifield.cards[i]!=null){
						this.aiAttack[i]=1;
						contTargetAttack++;
						
						switch(i){
						case 0:
							this.sworda1.setVisible(true);
							break;
						case 1:
							this.sworda2.setVisible(true);
							break;
						case 2:
							this.sworda3.setVisible(true);
							break;
						case 3:
							this.sworda4.setVisible(true);
							break;
						case 4:
							this.sworda5.setVisible(true);
							break;
						}
					}
					else{
						this.aiAttack[i]=0;
					}
				}	
				atkOrigin=-1;
				atkDest=-1;
				int band=0;
				i=0;
				while(band==0 && i<6){
					Random r = new Random();
					int a = r.nextInt(5);
					if(this.aiAttack[a]==1){
						this.atkOrigin=a;
						band=1;
					}
					i++;
				}
				
				for(int i=0;i<5;i++){
					if(this.player.field.cards[i]!=null){
						this.aiDest[i]=1;
					}
					else{
						this.aiDest[i]=0;
					}
				}	
				
				band=0;
				i=0;//si no haces esto cuando no hay cartas queda un ciclo infinito
				while(band==0 && i<6){
					Random r = new Random();
					int a = r.nextInt(5);
					if(this.aiDest[a]==1){
						this.atkDest=a;
						band=1;
					}
					i++;
				}
				JOptionPane.showMessageDialog(null, "Card "+this.atkOrigin+" attack to player Card "+this.atkDest);
				if(atkDest!=-1 && atkOrigin!=-1  )
				{
			
					if(!player.field.cards[atkDest].getpos()){ //si la carta elegida no esta bocabajo
					remove(phases);
					if(!fight.isVisible()){
					fight.addCards(new BigCard(player.field.cards[atkDest].getcard(),0,0),new BigCard(ai.aifield.cards[atkOrigin].getcard(),0,0));
					}
					if(player.field.cards[atkDest].getcard().GetHp()>ai.aifield.cards[atkOrigin].getcard().GetHp())
					{
						ai.aifield.quitar(atkOrigin);
						
					}else if(player.field.cards[atkDest].getcard().GetHp()<ai.aifield.cards[atkOrigin].getcard().GetHp())
					{
						player.field.quitar(atkDest);
					}
					add(phases);
					}
					
				}
			}
			
			
			phases.change(phases.actual+1);
			JOptionPane.showMessageDialog(null,"ai is finishing your turn" );
			this.sworda1.setVisible(false);
			this.sworda2.setVisible(false);
			this.sworda3.setVisible(false);
			this.sworda4.setVisible(false);
			this.sworda5.setVisible(false);
			turn=1;
			phases.change(0);
			this.turnoLabel.setText("Turn Player");
			this.contTurn++;
		}
	
	public void makeEffect(String id, int pos){
		
		if(this.phases.actual==2){
			if(id.equals("SSD-06")){
				JOptionPane.showMessageDialog(null, "you get 2 volatile power, use it wisely");
				player.powers.set(2);
			}
			if(id.equals("SSD-05")){
				this.undrain= new JButton("undrain");
				this.undrain.setBounds(15, 17, 49, 20);
				if(pos==0){
					menu1.getContentPane().add(this.undrain);
				}
				if(pos==1){
					menu2.getContentPane().add(this.undrain);
				}
				if(pos==2){
					menu3.getContentPane().add(this.undrain);
				}
				if(pos==3){
					menu4.getContentPane().add(this.undrain);
				}
				if(pos==4){
					menu5.getContentPane().add(this.undrain);
				}
			}
				
		}
		if(this.phases.actual==3){
			
		}
		
	}

}
