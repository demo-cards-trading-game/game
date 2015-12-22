package demo;
import demo.HandGui;
import extra.Tutorial;
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
	public Tutorial tuto;

	
	optionpane op;
	int turn, contTurn=0;
    public int ready=0;
	int acampo=-1;
	int i=0;
	private Fallen fallen ;
	private LoadData cartas;
	JInternalFrame pane; 
	public Phases phases;
	public  JButton changePhase,repaint;
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
	public JButton top1,top2,top3,top4,top5;
	public JButton ptarjet1, ptarjet2, ptarjet3, ptarjet4, ptarjet5;
	public JButton aitarjet1, aitarjet2, aitarjet3, aitarjet4, aitarjet5;
	public int selected=-1;
	//esto es para ssd-08
	public JButton ptarjet81, ptarjet82, ptarjet83, ptarjet84, ptarjet85;
	public JButton aitarjet81, aitarjet82, aitarjet83, aitarjet84, aitarjet85;
	
	public int getPhaseActual(){
		return phases.actual;
	}
	public void battle()
	{
		int pos=player.field.findwarrior();//busca un warrior en el campo para hacer la animacion
		
		if(pos!=-1)
		{
		tuto.animation.setLocation(210+pos*115,250);
		tuto.lblSms.setText("select a warrior to fight");
		tuto.animation.anim();
		tuto.setVisible(true);
		}
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
		tuto= new Tutorial();
		add(tuto);
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
	;
	
		fallen=new Fallen();
		add(fallen);
		
		
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
		changePhase=new JButton();
		changePhase.setVisible(false);
		changePhase.addActionListener(this);
		player.field.addMouseListener(this);
		
		this.listAll = new prueba2(player.pdeck.Deck);
		
		this.listAll.setBounds(150, 100, 620, 420);
		this.listAll.setVisible(false);
		add(this.listAll);
		this.moveToFront(this.listAll);
		
		this.listAll.aceptar.addActionListener(this);
		this.listAll = new prueba2(player.pdeck.Deck);
		
		this.listAll.setBounds(150, 100, 620, 420);
		add(this.listAll);
		this.moveToFront(this.listAll);
		
		this.listAll.aceptar.addActionListener(this);
		this.listAll.setVisible(false);
		
		this.top1=new JButton("tarjet");
		this.top1.setBounds(200,580,80,30);
		add(top1);
		this.moveToFront(top1);
		this.top1.addActionListener(this);
		
		this.top2=new JButton("tarjet");
		this.top2.setBounds(330,580,80,30);
		add(top2);
		this.moveToFront(top2);
		this.top2.addActionListener(this);
		
		this.top3=new JButton("tarjet");
		this.top3.setBounds(450,580,80,30);
		add(top3);
		this.moveToFront(top3);
		this.top3.addActionListener(this);
		
		this.top4=new JButton("tarjet");
		this.top4.setBounds(570,580,80,30);
		add(top4);
		this.moveToFront(top4);
		this.top4.addActionListener(this);
		
		this.top5=new JButton("tarjet");
		this.top5.setBounds(700,580,80,30);
		add(top5);
		this.moveToFront(top5);
		this.top5.addActionListener(this);
		
		this.top1.setVisible(false);
		this.top2.setVisible(false);
		this.top3.setVisible(false);
				this.top4.setVisible(false);
		this.top5.setVisible(false);
		phases.draw.addActionListener(this);
		
		this.ptarjet1= new JButton("tarjet");
		this.ptarjet1.setBounds(230,380, 69, 20);
		this.ptarjet2= new JButton("tarjet");
		this.ptarjet2.setBounds(340,380, 69, 20);
		this.ptarjet3= new JButton("tarjet");
		this.ptarjet3.setBounds(450,380, 69, 20);
		this.ptarjet4= new JButton("tarjet");
		this.ptarjet4.setBounds(560,380, 69, 20);
		this.ptarjet5= new JButton("tarjet");
		this.ptarjet5.setBounds(670,380, 69, 20);
		add(ptarjet1);
		add(ptarjet2);
		add(ptarjet3);
		add(ptarjet4);
		add(ptarjet5);
		this.moveToFront(ptarjet1);
		this.moveToFront(ptarjet2);
		this.moveToFront(ptarjet3);
		this.moveToFront(ptarjet4);
		this.moveToFront(ptarjet5);
		
		this.aitarjet1= new JButton("tarjet");
		this.aitarjet1.setBounds(230, 210, 69, 20);
		this.aitarjet2= new JButton("tarjet");
		this.aitarjet2.setBounds(340,210, 69, 20);
		this.aitarjet3= new JButton("tarjet");
		this.aitarjet3.setBounds(450,210, 69, 20);
		this.aitarjet4= new JButton("tarjet");
		this.aitarjet4.setBounds(560,210, 69, 20);
		this.aitarjet5= new JButton("tarjet");
		this.aitarjet5.setBounds(670,210, 69, 20);
		add(aitarjet1);
		add(aitarjet2);
		add(aitarjet3);
		add(aitarjet4);
		add(aitarjet5);
		this.moveToFront(aitarjet1);
		this.moveToFront(aitarjet2);
		this.moveToFront(aitarjet3);
		this.moveToFront(aitarjet4);
		this.moveToFront(aitarjet5);
		
		this.ptarjet1.addActionListener(this);
		this.ptarjet2.addActionListener(this);
		this.ptarjet3.addActionListener(this);
		this.ptarjet4.addActionListener(this);
		this.ptarjet5.addActionListener(this);
		this.aitarjet1.addActionListener(this);
		this.aitarjet2.addActionListener(this);
		this.aitarjet3.addActionListener(this);
		this.aitarjet4.addActionListener(this);
		this.aitarjet5.addActionListener(this);
		
		this.ptarjet1.setVisible(false);
		this.ptarjet2.setVisible(false);
		this.ptarjet3.setVisible(false);
		this.ptarjet4.setVisible(false);
		this.ptarjet5.setVisible(false);
		this.aitarjet1.setVisible(false);
		this.aitarjet2.setVisible(false);
		this.aitarjet3.setVisible(false);
		this.aitarjet4.setVisible(false);
		this.aitarjet5.setVisible(false);
	
		this.top4.setVisible(false);
		this.top5.setVisible(false);
		
		
		this.ptarjet1= new JButton("tarjet");
		this.ptarjet1.setBounds(230,380, 69, 20);
		this.ptarjet2= new JButton("tarjet");
		this.ptarjet2.setBounds(340,380, 69, 20);
		this.ptarjet3= new JButton("tarjet");
		this.ptarjet3.setBounds(450,380, 69, 20);
		this.ptarjet4= new JButton("tarjet");
		this.ptarjet4.setBounds(560,380, 69, 20);
		this.ptarjet5= new JButton("tarjet");
		this.ptarjet5.setBounds(670,380, 69, 20);
		add(ptarjet1);
		add(ptarjet2);
		add(ptarjet3);
		add(ptarjet4);
		add(ptarjet5);
		this.moveToFront(ptarjet1);
		this.moveToFront(ptarjet2);
		this.moveToFront(ptarjet3);
		this.moveToFront(ptarjet4);
		this.moveToFront(ptarjet5);
		
		this.aitarjet1= new JButton("tarjet");
		this.aitarjet1.setBounds(230, 210, 69, 20);
		this.aitarjet2= new JButton("tarjet");
		this.aitarjet2.setBounds(340,210, 69, 20);
		this.aitarjet3= new JButton("tarjet");
		this.aitarjet3.setBounds(450,210, 69, 20);
		this.aitarjet4= new JButton("tarjet");
		this.aitarjet4.setBounds(560,210, 69, 20);
		this.aitarjet5= new JButton("tarjet");
		this.aitarjet5.setBounds(670,210, 69, 20);
		add(aitarjet1);
		add(aitarjet2);
		add(aitarjet3);
		add(aitarjet4);
		add(aitarjet5);
		this.moveToFront(aitarjet1);
		this.moveToFront(aitarjet2);
		this.moveToFront(aitarjet3);
		this.moveToFront(aitarjet4);
		this.moveToFront(aitarjet5);
		
		this.ptarjet1.addActionListener(this);
		this.ptarjet2.addActionListener(this);
		this.ptarjet3.addActionListener(this);
		this.ptarjet4.addActionListener(this);
		this.ptarjet5.addActionListener(this);
		this.aitarjet1.addActionListener(this);
		this.aitarjet2.addActionListener(this);
		this.aitarjet3.addActionListener(this);
		this.aitarjet4.addActionListener(this);
		this.aitarjet5.addActionListener(this);
		
		this.ptarjet1.setVisible(false);
		this.ptarjet2.setVisible(false);
		this.ptarjet3.setVisible(false);
		this.ptarjet4.setVisible(false);
		this.ptarjet5.setVisible(false);
		this.aitarjet1.setVisible(false);
		this.aitarjet2.setVisible(false);
		this.aitarjet3.setVisible(false);
		this.aitarjet4.setVisible(false);
		this.aitarjet5.setVisible(false);
		
		
		
		
		
		this.ptarjet81= new JButton("tarjet");
		this.ptarjet81.setBounds(230,380, 69, 20);
		this.ptarjet82= new JButton("tarjet");
		this.ptarjet82.setBounds(340,380, 69, 20);
		this.ptarjet83= new JButton("tarjet");
		this.ptarjet83.setBounds(450,380, 69, 20);
		this.ptarjet84= new JButton("tarjet");
		this.ptarjet84.setBounds(560,380, 69, 20);
		this.ptarjet85= new JButton("tarjet");
		this.ptarjet85.setBounds(670,380, 69, 20);
		add(ptarjet81);
		add(ptarjet82);
		add(ptarjet83);
		add(ptarjet84);
		add(ptarjet85);
		this.moveToFront(ptarjet81);
		this.moveToFront(ptarjet82);
		this.moveToFront(ptarjet83);
		this.moveToFront(ptarjet84);
		this.moveToFront(ptarjet85);
		
		this.aitarjet81= new JButton("tarjet");
		this.aitarjet81.setBounds(230, 210, 69, 20);
		this.aitarjet82= new JButton("tarjet");
		this.aitarjet82.setBounds(340,210, 69, 20);
		this.aitarjet83= new JButton("tarjet");
		this.aitarjet83.setBounds(450,210, 69, 20);
		this.aitarjet84= new JButton("tarjet");
		this.aitarjet84.setBounds(560,210, 69, 20);
		this.aitarjet85= new JButton("tarjet");
		this.aitarjet85.setBounds(670,210, 69, 20);
		add(aitarjet81);
		add(aitarjet82);
		add(aitarjet83);
		add(aitarjet84);
		add(aitarjet85);
		this.moveToFront(aitarjet81);
		this.moveToFront(aitarjet82);
		this.moveToFront(aitarjet83);
		this.moveToFront(aitarjet84);
		this.moveToFront(aitarjet85);
		
		this.ptarjet81.addActionListener(this);
		this.ptarjet82.addActionListener(this);
		this.ptarjet83.addActionListener(this);
		this.ptarjet84.addActionListener(this);
		this.ptarjet85.addActionListener(this);
		this.aitarjet81.addActionListener(this);
		this.aitarjet82.addActionListener(this);
		this.aitarjet83.addActionListener(this);
		this.aitarjet84.addActionListener(this);
		this.aitarjet85.addActionListener(this);
		
		this.ptarjet81.setVisible(false);
		this.ptarjet82.setVisible(false);
		this.ptarjet83.setVisible(false);
		this.ptarjet84.setVisible(false);
		this.ptarjet85.setVisible(false);
		this.aitarjet81.setVisible(false);
		this.aitarjet82.setVisible(false);
		this.aitarjet83.setVisible(false);
		this.aitarjet84.setVisible(false);
		this.aitarjet85.setVisible(false);
		tuto.ok.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
	
		int done=0;
		if( e.getSource()==tuto.ok)
		{
			
			tuto.animation.stop();
			
		
			tuto.animation.stop();
			tuto.setVisible(false);
			
		
			
		}
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
		
		if((e.getSource()==changePhase)||(e.getSource()==phases.setup)||(e.getSource()==phases.draw)||(e.getSource()==phases.action)||(e.getSource()==phases.attack)||(e.getSource()==phases.end))
		{
			//System.out.println(turn);
		
				done=1;
				
			if(phases.actual<4){
				phases.change(phases.actual+1);
		
			}else{
				if(ready==1){
				phases.change(0);
				}
			}
			System.out.println(""+turno);
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
						tuto.draw();
						player.powers.set(1);
						this.phases.setup.removeActionListener(this);
						this.phases.draw.removeActionListener(this);
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
					tuto.barrier();
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
					tuto.Action();
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
					battle();
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
					if(ready==1){//funciona como segunda oportunidad
						ready=0;
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
			
				
			}else
			{
				tuto.end();
				ready=1;
			}
				break;
		}
			repaint();
			done=0;
		}
		
	
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
				this.makeEffect(player.field.cards[atkOrigin-1].getcard().Getid(),atkOrigin-1 );
				
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
		
		if(e.getSource()==this.top1){
			int p;
			
			this.top1.setVisible(false);
			this.top2.setVisible(false);
			this.top3.setVisible(false);
			this.top4.setVisible(false);
			this.top5.setVisible(false);
			
			JOptionPane.showMessageDialog(null, "your card will be placed on top of the deck");
			Card c = new Card();
			c=player.hand.cards[0];	
			player.pdeck.Deck.insertar(c);
			player.hand.discard(1);
			
			JOptionPane.showMessageDialog(null, "adding a water power from the deck");
			
			p=this.player.pdeck.Deck.posCard("SSD-15");
			if(p==-1){
				JOptionPane.showMessageDialog(null, "cannot find a water power");
			}else{
				int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
				player.hand.handgui[pos-1].addMouseListener(this);
				 Addlisteners2Card(pos-1);
				this.player.pdeck.textField.setText("cards left "+this.player.pdeck.Deck.cardsLeft());
				this.player.pdeck.textField.repaint();
			}
			
			repaint();
		}
		
		if(e.getSource()==this.top2){
			int p;
			this.top1.setVisible(false);
			this.top2.setVisible(false);
			this.top3.setVisible(false);
			this.top4.setVisible(false);
			this.top5.setVisible(false);
			
			JOptionPane.showMessageDialog(null, "your card will be placed on top of the deck");
			Card c = new Card();
			c=player.hand.cards[1];	
			player.pdeck.Deck.insertar(c);
			player.hand.discard(2);
			
			JOptionPane.showMessageDialog(null, "adding a water power from the deck");
			
			p=this.player.pdeck.Deck.posCard("SSD-15");
			if(p==-1){
				JOptionPane.showMessageDialog(null, "cannot find a water power");
			}else{
				int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
				player.hand.handgui[pos-1].addMouseListener(this);
				 Addlisteners2Card(pos-1);
				this.player.pdeck.textField.setText("cards left "+this.player.pdeck.Deck.cardsLeft());
				this.player.pdeck.textField.repaint();
			}
			
			repaint();
		}
		
		if(e.getSource()==this.top3){
			int p;
			this.top1.setVisible(false);
			this.top2.setVisible(false);
			this.top3.setVisible(false);
			this.top4.setVisible(false);
			this.top5.setVisible(false);
			
			JOptionPane.showMessageDialog(null, "your card will be placed on top of the deck");
			Card c = new Card();
			c=player.hand.cards[2];	
			player.pdeck.Deck.insertar(c);
			player.hand.discard(3);

			JOptionPane.showMessageDialog(null, "adding a water power from the deck");
			p=this.player.pdeck.Deck.posCard("SSD-15");
			if(p==-1){
				JOptionPane.showMessageDialog(null, "cannot find a water power");
			}else{
				int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
				player.hand.handgui[pos-1].addMouseListener(this);
				 Addlisteners2Card(pos-1);
				this.player.pdeck.textField.setText("cards left "+this.player.pdeck.Deck.cardsLeft());
				this.player.pdeck.textField.repaint();
			}
			
			repaint();
		}

		if(e.getSource()==this.top4){
			int p;
			this.top1.setVisible(false);
			this.top2.setVisible(false);
			this.top3.setVisible(false);
			this.top4.setVisible(false);
			this.top5.setVisible(false);
			
			JOptionPane.showMessageDialog(null, "your card will be placed on top of the deck");
			Card c = new Card();
			c=player.hand.cards[3];	
			player.pdeck.Deck.insertar(c);
			player.hand.discard(4);

			JOptionPane.showMessageDialog(null, "adding a water power from the deck");
			p=this.player.pdeck.Deck.posCard("SSD-15");
			if(p==-1){
				JOptionPane.showMessageDialog(null, "cannot find a water power");
			}else{
				int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
				player.hand.handgui[pos-1].addMouseListener(this);
				 Addlisteners2Card(pos-1);
				this.player.pdeck.textField.setText("cards left "+this.player.pdeck.Deck.cardsLeft());
				this.player.pdeck.textField.repaint();
			}
			
			repaint();
		}
		
		if(e.getSource()==this.top5){
			int p;
			this.top1.setVisible(false);
			this.top2.setVisible(false);
			this.top3.setVisible(false);
			this.top4.setVisible(false);
			this.top5.setVisible(false);
			
			JOptionPane.showMessageDialog(null, "your card will be placed on top of the deck");
			Card c = new Card();
			c=player.hand.cards[4];	
			player.pdeck.Deck.insertar(c);
			player.hand.discard(5);
			
			JOptionPane.showMessageDialog(null, "adding a water power from the deck");
			p=this.player.pdeck.Deck.posCard("SSD-15");
			if(p==-1){
				JOptionPane.showMessageDialog(null, "cannot find a water power");
			}else{
				int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
				player.hand.handgui[pos-1].addMouseListener(this);
				 Addlisteners2Card(pos-1);
				this.player.pdeck.textField.setText("cards left "+this.player.pdeck.Deck.cardsLeft());
				this.player.pdeck.textField.repaint();
			}
			
			repaint();
		}
		
		if(e.getSource()==this.ptarjet1||e.getSource()==this.ptarjet2||e.getSource()==this.ptarjet3||e.getSource()==this.ptarjet4||e.getSource()==this.ptarjet5){
			if(e.getSource()==this.ptarjet1){
				this.selected=1;
			}
			if(e.getSource()==this.ptarjet2){
				this.selected=2;
			}
			if(e.getSource()==this.ptarjet3){
				this.selected=3;
			}
			if(e.getSource()==this.ptarjet4){
				this.selected=4;
			}
			if(e.getSource()==this.ptarjet5){
				this.selected=5;
			}
			this.ptarjet1.setVisible(false);
			this.ptarjet2.setVisible(false);
			this.ptarjet3.setVisible(false);
			this.ptarjet4.setVisible(false);
			this.ptarjet5.setVisible(false);
			
			if(this.ai.aifield.cards[0]!=null){
				this.aitarjet1.setVisible(true);
			}
			
			if(this.ai.aifield.cards[1]!=null){
				this.aitarjet2.setVisible(true);
			}
			
			if(this.ai.aifield.cards[2]!=null){
				this.aitarjet3.setVisible(true);
			}
			
			if(this.ai.aifield.cards[3]!=null){
				this.aitarjet4.setVisible(true);
			}
			
			if(this.ai.aifield.cards[4]!=null){
				this.aitarjet5.setVisible(true);
			}
			
			repaint();
			JOptionPane.showMessageDialog(null, "please select a card from ai field");
			repaint();
		}
		
		if(e.getSource()==this.aitarjet1||e.getSource()==this.aitarjet2||e.getSource()==this.aitarjet3||e.getSource()==this.aitarjet4||e.getSource()==this.aitarjet5){
		
			int pos= player.hand.draw(this.player.field.cards[this.selected-1].getcard());
			player.hand.handgui[pos-1].addMouseListener(this);
			 Addlisteners2Card(pos-1);
			 player.field.quitar(this.selected-1);
			 
			 this.selected=-1;
			if(e.getSource()==this.aitarjet1){
				this.selected=1;
			}
			if(e.getSource()==this.aitarjet2){
				this.selected=2;
			}
			if(e.getSource()==this.aitarjet3){
				this.selected=3;
			}
			if(e.getSource()==this.aitarjet4){
				this.selected=4;
			}
			if(e.getSource()==this.aitarjet5){
				this.selected=5;
			}
			this.aitarjet1.setVisible(false);
			this.aitarjet2.setVisible(false);
			this.aitarjet3.setVisible(false);
			this.aitarjet4.setVisible(false);
			this.aitarjet5.setVisible(false);
			
			pos= this.ai.aihand.draw(this.ai.aifield.cards[this.selected-1].getcard());
			this.ai.aifield.quitar(this.selected-1); 
			
			JOptionPane.showMessageDialog(null, "cards were returned to the hands of owners");
		}
		
		if(e.getSource()==this.ptarjet81||e.getSource()==this.ptarjet82||e.getSource()==this.ptarjet83||e.getSource()==this.ptarjet84||e.getSource()==this.ptarjet85||e.getSource()==this.aitarjet81||e.getSource()==this.aitarjet82||e.getSource()==this.aitarjet83||e.getSource()==this.aitarjet84||e.getSource()==this.aitarjet85){
			this.selected=-1;
			int pos;
			
			this.ptarjet81.setVisible(false);
			this.ptarjet82.setVisible(false);
			this.ptarjet83.setVisible(false);
			this.ptarjet84.setVisible(false);
			this.ptarjet85.setVisible(false);
			this.aitarjet81.setVisible(false);
			this.aitarjet82.setVisible(false);
			this.aitarjet83.setVisible(false);
			this.aitarjet84.setVisible(false);
			this.aitarjet85.setVisible(false);
			
			if(e.getSource()==this.ptarjet81){
				this.selected=1;
			}
			if(e.getSource()==this.ptarjet82){
				this.selected=2;
			}
			if(e.getSource()==this.ptarjet83){
				this.selected=3;
			}
			if(e.getSource()==this.ptarjet84){
				this.selected=4;
			}
			if(e.getSource()==this.ptarjet85){
				this.selected=5;
			}
			
			if(this.selected==-1){
				if(e.getSource()==this.aitarjet81){
					this.selected=1;
				}
				if(e.getSource()==this.aitarjet82){
					this.selected=2;
				}
				if(e.getSource()==this.aitarjet83){
					this.selected=3;
				}
				if(e.getSource()==this.aitarjet84){
					this.selected=4;
				}
				if(e.getSource()==this.aitarjet85){
					this.selected=5;
				}
				pos= this.ai.aihand.draw(this.ai.aifield.cards[this.selected-1].getcard());
				this.ai.aifield.quitar(this.selected-1);
			}
			else{
				pos= player.hand.draw(this.player.field.cards[this.selected-1].getcard());
				player.hand.handgui[pos-1].addMouseListener(this);
				 Addlisteners2Card(pos-1);
				 player.field.quitar(this.selected-1);
			}
			
			repaint();
			JOptionPane.showMessageDialog(null, "cards were returned to the hands of owners");
		}
	}




	public class CirclePanel extends JPanel {

		


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
					if(player.field.cards[0].getcard().Getid().equals("SSD-01")){
						JOptionPane.showMessageDialog(null, "This card referred to 4 water power");
						player.powers.drain(player.field.cards[0].getcard().GetCost()*4);
					}
					else{
						player.powers.undrain(player.field.cards[0].getcard().GetCost());
					}
					
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[0].getcard());
					player.field.quitar(0);
				}	
				if(e.getSource()==player.field.cards[1])
				{
					if(player.field.cards[1].getcard().Getid().equals("SSD-01")){
						JOptionPane.showMessageDialog(null, "This card referred to 4 water power");
						player.powers.drain(player.field.cards[1].getcard().GetCost()*4);
					}
					else{
						player.powers.undrain(player.field.cards[1].getcard().GetCost());
					}
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[1].getcard());
					player.field.quitar(1);
				}
				if(e.getSource()==player.field.cards[2])
				{
					if(player.field.cards[2].getcard().Getid().equals("SSD-01")){
						JOptionPane.showMessageDialog(null, "This card referred to 4 water power");
						player.powers.drain(player.field.cards[2].getcard().GetCost()*4);
					}
					else{
						player.powers.undrain(player.field.cards[2].getcard().GetCost());
					}
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[2].getcard());
					player.field.quitar(2);
				}
				if(e.getSource()==player.field.cards[3])
				{
					if(player.field.cards[3].getcard().Getid().equals("SSD-01")){
						JOptionPane.showMessageDialog(null, "This card referred to 4 water power");
						player.powers.drain(player.field.cards[3].getcard().GetCost()*4);
					}
					else{
						player.powers.undrain(player.field.cards[3].getcard().GetCost());
					}
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[3].getcard());
					player.field.quitar(3);
					
				}if(e.getSource()==player.field.cards[4])
				{
					if(player.field.cards[4].getcard().Getid().equals("SSD-01")){
						JOptionPane.showMessageDialog(null, "This card referred to 4 water power");
						player.powers.drain(player.field.cards[4].getcard().GetCost()*4);
					}
					else{
						player.powers.undrain(player.field.cards[4].getcard().GetCost());
					}
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[4].getcard());
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
			
			this.turnoLabel.setText("Turn Player");
			this.contTurn++;
			changePhase.doClick();
		}
	
	public void makeEffect(String id, int pos){
		
		if(this.phases.actual==2){
			if(id.equals("SSD-06")){
				JOptionPane.showMessageDialog(null, "you get 2 volatile power, use it wisely");
				player.powers.set(2);
				repaint();
			}
			if(id.equals("SSD-05")){
				JOptionPane.showMessageDialog(null, "power undrained");
				player.powers.undrain(player.field.cards[pos].getcard().GetCost());
				repaint();
			}
			if(id.equals("SSD-04")){
				JOptionPane.showMessageDialog(null, "please, select your hand card");				

				if(player.hand.current>0){
					this.top1.setVisible(true);
				}
				if(player.hand.current>1){
					this.top2.setVisible(true);
				}

				if(player.hand.current>2){
					this.top3.setVisible(true);
				}
				if(player.hand.current>3){
					this.top4.setVisible(true);
				}

				if(player.hand.current>4){
					this.top5.setVisible(true);
				}
				
			}
			if(id.equals("SSD-07")){
				JOptionPane.showMessageDialog(null, "please select a card from your field");
				if(this.player.field.cards[0]!=null){
					this.ptarjet1.setVisible(true);
				}
				if(this.player.field.cards[1]!=null){
					this.ptarjet2.setVisible(true);				
				}
				if(this.player.field.cards[2]!=null){
					this.ptarjet3.setVisible(true);
				}
				if(this.player.field.cards[3]!=null){
					this.ptarjet4.setVisible(true);
				}
				if(this.player.field.cards[4]!=null){
					this.ptarjet5.setVisible(true);
				}
			}
			
			if(id.equals("SSD-08")){
				JOptionPane.showMessageDialog(null, "please select a card from the field");
				if(this.player.field.cards[0]!=null){
					this.ptarjet81.setVisible(true);
				}
				if(this.player.field.cards[1]!=null){
					this.ptarjet82.setVisible(true);				
				}
				if(this.player.field.cards[2]!=null){
					this.ptarjet83.setVisible(true);
				}
				if(this.player.field.cards[3]!=null){
					this.ptarjet84.setVisible(true);
				}
				if(this.player.field.cards[4]!=null){
					this.ptarjet85.setVisible(true);
				}
				if(this.ai.aifield.cards[0]!=null){
					this.aitarjet81.setVisible(true);
				}
				
				if(this.ai.aifield.cards[1]!=null){
					this.aitarjet82.setVisible(true);
				}
				
				if(this.ai.aifield.cards[2]!=null){
					this.aitarjet83.setVisible(true);
				}
				
				if(this.ai.aifield.cards[3]!=null){
					this.aitarjet84.setVisible(true);
				}
				
				if(this.ai.aifield.cards[4]!=null){
					this.aitarjet85.setVisible(true);
				}
			}
				
			repaint();
		}
		if(this.phases.actual==3){
			if(id.equals("SSD-02")){
				int p;
				JOptionPane.showMessageDialog(null, "adding a water power from the deck");
				
				p=this.player.pdeck.Deck.posCard("SSD-15");
				if(p==-1){
					JOptionPane.showMessageDialog(null, "cannot find a water power");
				}else{
					int poss= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
					player.hand.handgui[poss-1].addMouseListener(this);
					 Addlisteners2Card(poss-1);
					this.player.pdeck.textField.setText("cards left "+this.player.pdeck.Deck.cardsLeft());
					this.player.pdeck.textField.repaint();
				}
			}
			repaint();
		}
		
	}

	
	
}
