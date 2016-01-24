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
	public static PlayerGui player;//aca estan el hand el deck y lo demas
	public AIGui ai;//lo mismo pero en el ai
	public Previewpane preview;//aca se muestra la carta
	int p,w,c;//pos , where, cost
	public Tutorial tuto;
	boolean checking;//sirve para frenar al hilo que checkea y activa el boton de pago
	
	optionpane op;
	int turn, contTurn=0;
    public int ready=0;
	int acampo=-1;
	int pr,s,pl,d;
	int i=0;
	private Fallen fallen ;
	private LoadData cartas;
	JInternalFrame pane; 
	public Phases phases;
	public  JButton changePhase,repaint;
	private FileReader turno;
	private BufferedReader br;
	private JLabel turnoLabel;
	private Card BeingPlayed;
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
	//esto es para sdd-09
	public JButton ptarjet91, ptarjet92, ptarjet93, ptarjet94, ptarjet95;
	public JButton aitarjet91, aitarjet92, aitarjet93, aitarjet94, aitarjet95;
	//esto es para ssd-10
	public JButton ptarjet101, ptarjet102, ptarjet103, ptarjet104, ptarjet105;
	public JButton aitarjet101, aitarjet102, aitarjet103, aitarjet104, aitarjet105;
	//esto es para ssd-11
	public JButton ptarjet111, ptarjet112, ptarjet113, ptarjet114, ptarjet115;
	public JButton aitarjet111, aitarjet112, aitarjet113, aitarjet114, aitarjet115;
	//visor de las cartas totales

	
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
		player.pdeck.btnNewButton_1.addActionListener(this);
		
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
			this.turnoLabel.setText("Player's Turn");
			
		}else{ //turno player 2
			this.turnoLabel.setText("Ai Player's Turn");
			this.phases.end.addActionListener(this);
		}
		this.turnoLabel.setBounds(50, 320, 200, 20);
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
		
	
	
		changePhase=new JButton();
		changePhase.setVisible(false);
		changePhase.addActionListener(this);
		player.field.addMouseListener(this);
		
		this.listAll = new prueba2(player.pdeck.Deck);
		
		this.listAll.setBounds(150, 100, 620, 420);
		this.listAll.setVisible(false);
		//add(this.listAll);
		this.moveToFront(this.listAll);
		
		this.listAll.aceptar.addActionListener(this);
		
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
		
		
		
		
		this.ptarjet91= new JButton("tarjet");
		this.ptarjet91.setBounds(230,380, 69, 20);
		this.ptarjet92= new JButton("tarjet");
		this.ptarjet92.setBounds(340,380, 69, 20);
		this.ptarjet93= new JButton("tarjet");
		this.ptarjet93.setBounds(450,380, 69, 20);
		this.ptarjet94= new JButton("tarjet");
		this.ptarjet94.setBounds(560,380, 69, 20);
		this.ptarjet95= new JButton("tarjet");
		this.ptarjet95.setBounds(670,380, 69, 20);
		add(ptarjet91);
		add(ptarjet92);
		add(ptarjet93);
		add(ptarjet94);
		add(ptarjet95);
		this.moveToFront(ptarjet91);
		this.moveToFront(ptarjet92);
		this.moveToFront(ptarjet93);
		this.moveToFront(ptarjet94);
		this.moveToFront(ptarjet95);
		
		this.aitarjet91= new JButton("tarjet");
		this.aitarjet91.setBounds(230, 210, 69, 20);
		this.aitarjet92= new JButton("tarjet");
		this.aitarjet92.setBounds(340,210, 69, 20);
		this.aitarjet93= new JButton("tarjet");
		this.aitarjet93.setBounds(450,210, 69, 20);
		this.aitarjet94= new JButton("tarjet");
		this.aitarjet94.setBounds(560,210, 69, 20);
		this.aitarjet95= new JButton("tarjet");
		this.aitarjet95.setBounds(670,210, 69, 20);
		add(aitarjet91);
		add(aitarjet92);
		add(aitarjet93);
		add(aitarjet94);
		add(aitarjet95);
		this.moveToFront(aitarjet91);
		this.moveToFront(aitarjet92);
		this.moveToFront(aitarjet93);
		this.moveToFront(aitarjet94);
		this.moveToFront(aitarjet95);
		
		this.ptarjet91.addActionListener(this);
		this.ptarjet92.addActionListener(this);
		this.ptarjet93.addActionListener(this);
		this.ptarjet94.addActionListener(this);
		this.ptarjet95.addActionListener(this);
		this.aitarjet91.addActionListener(this);
		this.aitarjet92.addActionListener(this);
		this.aitarjet93.addActionListener(this);
		this.aitarjet94.addActionListener(this);
		this.aitarjet95.addActionListener(this);
		
		this.ptarjet91.setVisible(false);
		this.ptarjet92.setVisible(false);
		this.ptarjet93.setVisible(false);
		this.ptarjet94.setVisible(false);
		this.ptarjet95.setVisible(false);
		this.aitarjet91.setVisible(false);
		this.aitarjet92.setVisible(false);
		this.aitarjet93.setVisible(false);
		this.aitarjet94.setVisible(false);
		this.aitarjet95.setVisible(false);
		
		
		
		
		this.ptarjet101= new JButton("tarjet");
		this.ptarjet101.setBounds(230,380, 69, 20);
		this.ptarjet102= new JButton("tarjet");
		this.ptarjet102.setBounds(340,380, 69, 20);
		this.ptarjet103= new JButton("tarjet");
		this.ptarjet103.setBounds(450,380, 69, 20);
		this.ptarjet104= new JButton("tarjet");
		this.ptarjet104.setBounds(560,380, 69, 20);
		this.ptarjet105= new JButton("tarjet");
		this.ptarjet105.setBounds(670,380, 69, 20);
		add(ptarjet101);
		add(ptarjet102);
		add(ptarjet103);
		add(ptarjet104);
		add(ptarjet105);
		this.moveToFront(ptarjet101);
		this.moveToFront(ptarjet102);
		this.moveToFront(ptarjet103);
		this.moveToFront(ptarjet104);
		this.moveToFront(ptarjet105);
		
		this.aitarjet101= new JButton("tarjet");
		this.aitarjet101.setBounds(230, 210, 69, 20);
		this.aitarjet102= new JButton("tarjet");
		this.aitarjet102.setBounds(340,210, 69, 20);
		this.aitarjet103= new JButton("tarjet");
		this.aitarjet103.setBounds(450,210, 69, 20);
		this.aitarjet104= new JButton("tarjet");
		this.aitarjet104.setBounds(560,210, 69, 20);
		this.aitarjet105= new JButton("tarjet");
		this.aitarjet105.setBounds(670,210, 69, 20);
		add(aitarjet101);
		add(aitarjet102);
		add(aitarjet103);
		add(aitarjet104);
		add(aitarjet105);
		this.moveToFront(aitarjet101);
		this.moveToFront(aitarjet102);
		this.moveToFront(aitarjet103);
		this.moveToFront(aitarjet104);
		this.moveToFront(aitarjet105);
		
		this.ptarjet101.addActionListener(this);
		this.ptarjet102.addActionListener(this);
		this.ptarjet103.addActionListener(this);
		this.ptarjet104.addActionListener(this);
		this.ptarjet105.addActionListener(this);
		this.aitarjet101.addActionListener(this);
		this.aitarjet102.addActionListener(this);
		this.aitarjet103.addActionListener(this);
		this.aitarjet104.addActionListener(this);
		this.aitarjet105.addActionListener(this);
		
		this.ptarjet101.setVisible(false);
		this.ptarjet102.setVisible(false);
		this.ptarjet103.setVisible(false);
		this.ptarjet104.setVisible(false);
		this.ptarjet105.setVisible(false);
		this.aitarjet101.setVisible(false);
		this.aitarjet102.setVisible(false);
		this.aitarjet103.setVisible(false);
		this.aitarjet104.setVisible(false);
		this.aitarjet105.setVisible(false);
		
		
		
		
		
		this.ptarjet111= new JButton("tarjet");
		this.ptarjet111.setBounds(230,380, 69, 20);
		this.ptarjet112= new JButton("tarjet");
		this.ptarjet112.setBounds(340,380, 69, 20);
		this.ptarjet113= new JButton("tarjet");
		this.ptarjet113.setBounds(450,380, 69, 20);
		this.ptarjet114= new JButton("tarjet");
		this.ptarjet114.setBounds(560,380, 69, 20);
		this.ptarjet115= new JButton("tarjet");
		this.ptarjet115.setBounds(670,380, 69, 20);
		add(ptarjet111);
		add(ptarjet112);
		add(ptarjet113);
		add(ptarjet114);
		add(ptarjet115);
		this.moveToFront(ptarjet111);
		this.moveToFront(ptarjet112);
		this.moveToFront(ptarjet113);
		this.moveToFront(ptarjet114);
		this.moveToFront(ptarjet115);
		
		this.aitarjet111= new JButton("tarjet");
		this.aitarjet111.setBounds(230, 210, 69, 20);
		this.aitarjet112= new JButton("tarjet");
		this.aitarjet112.setBounds(340,210, 69, 20);
		this.aitarjet113= new JButton("tarjet");
		this.aitarjet113.setBounds(450,210, 69, 20);
		this.aitarjet114= new JButton("tarjet");
		this.aitarjet114.setBounds(560,210, 69, 20);
		this.aitarjet115= new JButton("tarjet");
		this.aitarjet115.setBounds(670,210, 69, 20);
		add(aitarjet111);
		add(aitarjet112);
		add(aitarjet113);
		add(aitarjet114);
		add(aitarjet115);
		this.moveToFront(aitarjet111);
		this.moveToFront(aitarjet112);
		this.moveToFront(aitarjet113);
		this.moveToFront(aitarjet114);
		this.moveToFront(aitarjet115);
		
		this.ptarjet111.addActionListener(this);
		this.ptarjet112.addActionListener(this);
		this.ptarjet113.addActionListener(this);
		this.ptarjet114.addActionListener(this);
		this.ptarjet115.addActionListener(this);
		this.aitarjet111.addActionListener(this);
		this.aitarjet112.addActionListener(this);
		this.aitarjet113.addActionListener(this);
		this.aitarjet114.addActionListener(this);
		this.aitarjet115.addActionListener(this);
		
		this.ptarjet111.setVisible(false);
		this.ptarjet112.setVisible(false);
		this.ptarjet113.setVisible(false);
		this.ptarjet114.setVisible(false);
		this.ptarjet115.setVisible(false);
		this.aitarjet111.setVisible(false);
		this.aitarjet112.setVisible(false);
		this.aitarjet113.setVisible(false);
		this.aitarjet114.setVisible(false);
		this.aitarjet115.setVisible(false);
		tuto.ok.addActionListener(this);
		tuto.ok3.addActionListener(this);
		tuto.cancel.addActionListener(this);

		tuto.panel.add(player.powers.label);
		player.powers.label.setVisible(false);

		
	
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
	
		int done=0;
		if( e.getSource()==tuto.ok)
		{
			
			tuto.animation.stop();
			
		
			tuto.animation.stop();
			tuto.setVisible(false);
			checking=false;
			
			
		}
		if( e.getSource()==tuto.ok3)
		{
			
			tuto.animation.stop();
			
		    
			tuto.animation.stop();
			tuto.setVisible(false);
			
			player.powers.label.setVisible(false);
			set(p,w);
			checking=false;
			tuto.panel.remove(tuto.ok3);
			tuto.panel.remove(tuto.cancel);
			tuto.panel.add(tuto.ok);
			player.powers.paying=0;
			player.powers.tokenused=0;
		
			
		}
		if( e.getSource()==tuto.cancel)
		{
			
			tuto.animation.stop();
			player.powers.label.setVisible(false);
			tuto.panel.remove(tuto.ok3);
			tuto.panel.remove(tuto.cancel);
			tuto.animation.stop();
			tuto.setVisible(false);
			tuto.panel.add(tuto.ok);
		
			
		}
		if (e.getSource()==player.pdeck.btnNewButton_1)// si se le da click al boton de fallen 
		{
				
			fallen.setVisible(true);
			moveToFront(fallen);

		}
		s=-1;
		if(e.getSource()==player.hand.handgui[0].Set)
		{
			if(done==0){
				s=0;
				
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[1].Set)
		{
			if(done==0){
				s=1;
				
				
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[2].Set)
		{
			if(done==0){
				s=2;
				
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[3].Set)
		{
			if(done==0){
			s=3;


			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[4].Set)
		{
			if(done==0){
				s=4;
				
				
					
			
			}
			done=1;
			
		}

		if(s!=-1)
		{
			
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[s]);	
				if(player.hand.cards[s].GetName()=="Water power")
				player.powers.setwp();
				else
					player.powers.set();
				
				player.hand.discard(s+1);
			
					this.repairListeners();
				
				
					
			
			
		}

			
		
		
		if(e.getSource()==player.hand.handgui[1].Discard)//si se le da play a la carta 1  
		{
			if(done==0){
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[1]);
				player.hand.discard(2);
				
					this.repairListeners();
				
				
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[2].Discard)//si se le da play a la carta 1  
		{
			if(done==0)
			{
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[2]);
				player.hand.discard(3);
			
					this.repairListeners();
				
			
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[3].Discard)//si se le da play a la carta 1  
		{
			if(done==0)
			{
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[3]);
				player.hand.discard(4);
			
					this.repairListeners();
				
			
			}
			done=1;
			
		}
		if(e.getSource()==player.hand.handgui[4].Discard)//si se le da play a la carta 1  
		{
			if(done==0)
			{
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[4]);
				
					this.repairListeners();
				
			
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
			
				this.repairListeners();
			
			
			done=1;
			
		}
		pl=-1;
		if(e.getSource()==player.hand.handgui[0].Play)//si se le da play a la carta 2  
		{
			pl=0;
				
					
		}
		if(e.getSource()==player.hand.handgui[1].Play)//si se le da play a la carta 2  
		{
			pl=1;
				
					
		}
		if(e.getSource()==player.hand.handgui[2].Play)//si se le da play a la carta 2  
		{
			pl=2;
				
					
		}
		if(e.getSource()==player.hand.handgui[3].Play)//si se le da play a la carta 2  
		{
			pl=3;
				
					
		}
		if(e.getSource()==player.hand.handgui[4].Play)//si se le da play a la carta 2  
		{
			pl=4;
				
					
		}
		if(player.hand.handgui[5]!=null){
		if(e.getSource()==player.hand.handgui[5].Play)//si se le da play a la carta 2  
		{
			pl=5;
				
					
		}
		}
		
		if(player.hand.handgui[6]!=null){
			if(e.getSource()==player.hand.handgui[6].Play)//si se le da play a la carta 2  
			{
				pl=6;
					
						
			}
		}
		
		if(pl!=-1)
		{

			if(player.hand.cards[pl].Getid().equals("SSD-10")&&(contarBarriers()>=0)){
				JOptionPane.showMessageDialog(null, "You must have 0 barriers to play this card");
			}
			else{
				if(done==0)
					play(pl);
					done=1;
			
						this.repairListeners();
					
					
			}
			
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
						player.powers.reset();
						player.powers.token();
						this.phases.setup.removeActionListener(this);
						this.phases.draw.removeActionListener(this);
						this.phases.draw.addActionListener(this);
						
						//enable deck
						//disable barriers
						
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
						player.barriers.barriers[i].addMouseListener(this);
				
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
						
						for (int i=0;i<5;i++)
							player.barriers.barriers[i].removeMouseListener(this);
						
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
						this.turnoLabel.setText("Ai Player's Turn");
					}else{
						turn=1;
						this.turnoLabel.setText("Player's Turn");
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
			if(ai.aifield.countcards()==0)
			{
				
				int which=ai.barriers.findwhich();//verifica que exista un barrier
				JOptionPane.showMessageDialog(null, "congratulations , direct hit");
				if(ai.aihand.current==5){	
					ai.aihand.discard(5);
				}
					
					if(which!=-1)//existe un barrier
					{
						int pos= ai.aihand.draw(ai.barriers.cards[which]);
						ai.barriers.removebarrier(which);
						
					}
				
					
			}else{
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
			//this.listAll.setVisible(true);
			 
			 
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
			
				this.repairListeners();
		
			
			
			JOptionPane.showMessageDialog(null, "adding a water power from the deck");
			p= -1;
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
			
				this.repairListeners();
			
			
			
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
			
				this.repairListeners();
			
			

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
	
				this.repairListeners();
			
			

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
	
				this.repairListeners();
			
			
			
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
		
		if(e.getSource()==this.ptarjet91||e.getSource()==this.ptarjet92||e.getSource()==this.ptarjet93||e.getSource()==this.ptarjet94||e.getSource()==this.ptarjet95){
			
			this.ptarjet91.setVisible(false);
			this.ptarjet92.setVisible(false);
			this.ptarjet93.setVisible(false);
			this.ptarjet94.setVisible(false);
			this.ptarjet95.setVisible(false);
			
			this.player.field.quitar(this.selected);
			JOptionPane.showMessageDialog(null, "select an ai card to destroy");
			
			if(this.ai.aifield.cards[0]!=null){
				this.aitarjet91.setVisible(true);
			}
			
			if(this.ai.aifield.cards[1]!=null){
				this.aitarjet92.setVisible(true);
			}
			
			if(this.ai.aifield.cards[2]!=null){
				this.aitarjet93.setVisible(true);
			}
			
			if(this.ai.aifield.cards[3]!=null){
				this.aitarjet94.setVisible(true);
			}
			
			if(this.ai.aifield.cards[4]!=null){
				this.aitarjet95.setVisible(true);
			}
		}
		
		if(e.getSource()==this.aitarjet91||e.getSource()==this.aitarjet92||e.getSource()==this.aitarjet93||e.getSource()==this.aitarjet94||e.getSource()==this.aitarjet95){
			if(e.getSource()==this.aitarjet91){
				this.ai.aifield.quitar(0);
			}
			if(e.getSource()==this.aitarjet92){
				this.ai.aifield.quitar(1);
			}
			if(e.getSource()==this.aitarjet93){
				this.ai.aifield.quitar(2);
			}
			if(e.getSource()==this.aitarjet94){
				this.ai.aifield.quitar(3);
			}
			if(e.getSource()==this.aitarjet95){
				this.ai.aifield.quitar(4);
			}
			
			this.selected=-1;
			
			this.aitarjet91.setVisible(false);
			this.aitarjet92.setVisible(false);
			this.aitarjet93.setVisible(false);
			this.aitarjet94.setVisible(false);
			this.aitarjet95.setVisible(false);
			
			JOptionPane.showMessageDialog(null, "destroyed succefully");
			repaint();
		}
		
		if(e.getSource()==this.ptarjet101||e.getSource()==this.ptarjet102||e.getSource()==this.ptarjet103||e.getSource()==this.ptarjet104||e.getSource()==this.ptarjet105||e.getSource()==this.aitarjet101||e.getSource()==this.aitarjet102||e.getSource()==this.aitarjet103||e.getSource()==this.aitarjet104||e.getSource()==this.aitarjet105){
			
			this.ptarjet101.setVisible(false);
			this.ptarjet102.setVisible(false);
			this.ptarjet103.setVisible(false);
			this.ptarjet104.setVisible(false);
			this.ptarjet105.setVisible(false);
			this.aitarjet101.setVisible(false);
			this.aitarjet102.setVisible(false);
			this.aitarjet103.setVisible(false);
			this.aitarjet104.setVisible(false);
			this.aitarjet105.setVisible(false);
			
			if(e.getSource()==this.ptarjet101){
				this.player.field.quitar(0);
			}
			if(e.getSource()==this.ptarjet102){
				this.player.field.quitar(1);
			}
			if(e.getSource()==this.ptarjet103){
				this.player.field.quitar(2);
			}
			if(e.getSource()==this.ptarjet104){
				this.player.field.quitar(3);
			}
			if(e.getSource()==this.ptarjet105){
				this.player.field.quitar(4);
			}
			if(e.getSource()==this.aitarjet101){
				this.ai.aifield.quitar(0);
			}
			if(e.getSource()==this.aitarjet102){
				this.ai.aifield.quitar(1);
			}
			if(e.getSource()==this.aitarjet103){
				this.ai.aifield.quitar(2);
			}
			if(e.getSource()==this.aitarjet104){
				this.ai.aifield.quitar(3);
			}
			if(e.getSource()==this.aitarjet105){
				this.ai.aifield.quitar(4);
			}
			
			JOptionPane.showMessageDialog(null, "destroyed succefully");
			repaint();
		}
		
		if(e.getSource()==this.ptarjet111||e.getSource()==this.ptarjet112||e.getSource()==this.ptarjet113||e.getSource()==this.ptarjet114||e.getSource()==this.ptarjet115||e.getSource()==this.aitarjet111||e.getSource()==this.aitarjet112||e.getSource()==this.aitarjet113||e.getSource()==this.aitarjet114||e.getSource()==this.aitarjet115){
			Card c = new Card();
			
			this.ptarjet111.setVisible(false);
			this.ptarjet112.setVisible(false);
			this.ptarjet113.setVisible(false);
			this.ptarjet114.setVisible(false);
			this.ptarjet115.setVisible(false);
			this.aitarjet111.setVisible(false);
			this.aitarjet112.setVisible(false);
			this.aitarjet113.setVisible(false);
			this.aitarjet114.setVisible(false);
			this.aitarjet115.setVisible(false);
			
			if(e.getSource()==this.ptarjet111){
				c=this.player.field.cards[0].getcard();
				this.player.field.quitar(0);
				try {
					this.player.field.poner(new SmallCard(true,c), 0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==this.ptarjet112){
				c=this.player.field.cards[1].getcard();
				this.player.field.quitar(1);
				try {
					this.player.field.poner(new SmallCard(true,c), 1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==this.ptarjet113){
				c=this.player.field.cards[2].getcard();
				this.player.field.quitar(2);
				try {
					this.player.field.poner(new SmallCard(true,c), 2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==this.ptarjet114){
				c=this.player.field.cards[3].getcard();
				this.player.field.quitar(3);
				try {
					this.player.field.poner(new SmallCard(true,c), 3);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==this.ptarjet115){
				c=this.player.field.cards[4].getcard();
				this.player.field.quitar(4);
				try {
					this.player.field.poner(new SmallCard(true,c), 4);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==this.aitarjet111){
				c=this.ai.aifield.cards[0].getcard();
				this.ai.aifield.quitar(0);
				try {
					this.ai.aifield.poner(new SmallCard(true,c), 0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==this.aitarjet112){
				c=this.ai.aifield.cards[1].getcard();
				this.ai.aifield.quitar(1);
				try {
					this.ai.aifield.poner(new SmallCard(true,c), 1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==this.aitarjet113){
				c=this.ai.aifield.cards[2].getcard();
				this.ai.aifield.quitar(2);
				try {
					this.ai.aifield.poner(new SmallCard(true,c), 2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==this.aitarjet114){
				c=this.ai.aifield.cards[3].getcard();
				this.ai.aifield.quitar(3);
				try {
					this.ai.aifield.poner(new SmallCard(true,c), 3);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(e.getSource()==this.aitarjet115){
				c=this.ai.aifield.cards[4].getcard();
				this.ai.aifield.quitar(4);
				try {
					this.ai.aifield.poner(new SmallCard(true,c), 4);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			repaint();
			JOptionPane.showMessageDialog(null, "face down succefully");
			repaint();
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
			int x=-1; //destino
			if(e.getSource()==player.hand.handgui[0])
			{
				x=0;
			}
			else if(e.getSource()==player.hand.handgui[1])
			{
				x=1;
			}
			else if(e.getSource()==player.hand.handgui[2])
			{
				x=2;
			}
			else if(e.getSource()==player.hand.handgui[3])
			{
				x=3;
				
			}else if(e.getSource()==player.hand.handgui[4])
			{
				x=4;
			}
			else if(e.getSource()==player.hand.handgui[5])
			{
				x=5;
			}
			else if(e.getSource()==player.hand.handgui[6])
			{
				x=6;
			}
			else if(e.getSource()==player.hand.handgui[7])
			{
				x=7;
			}
			if(x!=-1)
			{
				for(int j=0;j<player.hand.current;j++)
				{
					if(x!=j)
					{
						player.hand.handgui[j].menu.setVisible(false);
					}else
					{
						player.hand.handgui[j].menu.setVisible(true);
					}
				}	
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
						player.powers.set(player.field.cards[0].getcard().GetCost()*4);
					}
					else{
						player.powers.play(player.field.cards[0].getcard().GetCost());
					}
					
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[0].getcard());
					player.field.quitar(0);
				}	
				if(e.getSource()==player.field.cards[1])
				{
					if(player.field.cards[1].getcard().Getid().equals("SSD-01")){
						JOptionPane.showMessageDialog(null, "This card referred to 4 water power");
						player.powers.set(player.field.cards[1].getcard().GetCost()*4);
					}
					else{
						player.powers.play(player.field.cards[1].getcard().GetCost());
					}
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[1].getcard());
					player.field.quitar(1);
				}
				if(e.getSource()==player.field.cards[2])
				{
					if(player.field.cards[2].getcard().Getid().equals("SSD-01")){
						JOptionPane.showMessageDialog(null, "This card referred to 4 water power");
						player.powers.set(player.field.cards[2].getcard().GetCost()*4);
					}
					else{
						player.powers.play(player.field.cards[2].getcard().GetCost());
					}
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[2].getcard());
					player.field.quitar(2);
				}
				if(e.getSource()==player.field.cards[3])
				{
					if(player.field.cards[3].getcard().Getid().equals("SSD-01")){
						JOptionPane.showMessageDialog(null, "This card referred to 4 water power");
						player.powers.set(player.field.cards[3].getcard().GetCost()*4);
					}
					else{
						player.powers.play(player.field.cards[3].getcard().GetCost());
					}
					fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[3].getcard());
					player.field.quitar(3);
					
				}if(e.getSource()==player.field.cards[4])
				{
					if(player.field.cards[4].getcard().Getid().equals("SSD-01")){
						JOptionPane.showMessageDialog(null, "This card referred to 4 water power");
						player.powers.set(player.field.cards[4].getcard().GetCost()*4);
					}
					else{
						player.powers.play(player.field.cards[4].getcard().GetCost());
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
		int x=-1;
		
		int x2=498/(player.hand.current-1);
		if(player.hand.current<5)
			x2=124;
		if(e.getSource()==player.hand.handgui[0])
		{
			x=0;
			preview.Remove();
		}
		else if(e.getSource()==player.hand.handgui[1])
		{
			x=1;
			preview.Remove();
		}
		else if(e.getSource()==player.hand.handgui[2])
		{
			x=2;
			preview.Remove();
		}
		else if(e.getSource()==player.hand.handgui[3])
		{
			x=3;
			preview.Remove();
		}else if(e.getSource()==player.hand.handgui[4])
		{
			x=4;
			preview.Remove();
		}else if(e.getSource()==player.hand.handgui[5])
		{
			x=5;
			
		}else if(e.getSource()==player.hand.handgui[6])
		{
			x=6;
			
		}else if(e.getSource()==player.hand.handgui[7])
		{
			x=7;
			
		}else if(e.getSource()==player.hand.handgui[8])
		{
			x=8;
			
		}else if(e.getSource()==player.hand.handgui[9])
		{
			x=9;
			
		}
		
		if(x!=-1)
		{	
			
			if(x!=player.hand.current-1 || x<5){
				
			player.hand.handgui[x].setBounds(x*x2+30,20,124,186);
			
			}else
			{
				player.hand.handgui[x].setBounds(528,20,124,186);
				
			}
			
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
		int x=-1;
		int x2;
		if(player.hand.current<5)
			x2=124;
		else
			x2=498/(player.hand.current-1);
		
		if(e.getSource()==player.hand.handgui[0])
		{
			x=0;
		
			
			
		}
		else if(e.getSource()==player.hand.handgui[1])
		{
			x=1;
			
		}
		else if(e.getSource()==player.hand.handgui[2])
		{
			x=2;
			
		}
		else if(e.getSource()==player.hand.handgui[3])
		{
			x=3;
			
		}else if(e.getSource()==player.hand.handgui[4])
		{
			x=4;
		
		}
		else if(e.getSource()==player.hand.handgui[5])
		{
			x=5;
			
		}else if(e.getSource()==player.hand.handgui[6])
		{
			x=6;
			
		}else if(e.getSource()==player.hand.handgui[7])
		{
			x=7;
			
		}else if(e.getSource()==player.hand.handgui[8])
		{
			x=8;
			
		}else if(e.getSource()==player.hand.handgui[9])
		{
			x=9;
			
		}
		if(x!=-1)
		{	
			if(x!=player.hand.current){
				System.out.println(x2);
			player.hand.handgui[x].setBounds(x*x2,0,124,186);
			player.hand.moveToFront(player.hand.handgui[x]);
			}
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
	
	void set(int pos,int where)
	{

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
			player.powers.play(player.hand.handgui[pos].getcard().GetCost());
			
			repaint();
			carta.addMouseListener(this);

			player.field.poner(carta, where);
			player.hand.music();
			player.hand.handgui[pos].Preview.doClick();
			player.hand.discard(1);
			
				this.repairListeners();
		

			repaint();
			
			Thread t = new Thread(new Runnable() {

				public void start() {
					this.start();
				}

				public void run() {
					try {
						Thread.sleep(2000);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					preview.Remove();
					repaint();
				}
			});
			t.start();
			
			this.makeEffect(carta.actual.Getid(),where);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
	}

	void hilo() {

		Thread t = new Thread(new Runnable() {

			public void start() {
				this.start();
			}

			public void run() {

				checking = true;

				while (checking) {

					try {
						Thread.sleep(15);
						
						
						if(player.powers.paying + player.powers.tokenused==c)
						{
							tuto.ok3.setEnabled(true);
							
						}
					} catch (InterruptedException e) {
						e.printStackTrace();

					}

				}

			}
		});
		t.start();
		
	}
	void play(int pos)// plays a card on field
	{
	 c=player.hand.handgui[pos].getcard().GetCost();
		if ( player.powers.currentundrained+player.powers.currentoken - c >=0 ) {//verifica que haya mana

			if ( warriorPlayed == 0 ||(player.hand.handgui[pos].getcard().GetType()!="Warrior" && warriorPlayed ==1 )) {//verifica que un warrior no se ha jugado en ese turno
				int where = player.field.findwhere();// busca en donde poner la carta
				if (where != -1)
				{
					
					p=pos;
					w=where;
					tuto.play();
					
					player.powers.label.setVisible(true);
					
					hilo();
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
		ai.aidra.set(1);
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
						this.makeAiEffect(this.ai.aifield.cards[atkOrigin-1].getcard().Getid(),atkOrigin-1 );
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
			
			this.turnoLabel.setText("Player's Turn");
			this.contTurn++;
			changePhase.doClick();
		}
	
	public void makeEffect(String id, int pos){
		
		if(this.phases.actual==2){
			if(id.equals("SSD-06")){
				JOptionPane.showMessageDialog(null, "you get 2 volatile power, use it wisely");
				player.powers.reset();
				player.powers.token();
				player.powers.reset();
				player.powers.token();
				repaint();
				
			}
			if(id.equals("SSD-05")){
				JOptionPane.showMessageDialog(null, "power undrained");
				//player.powers.play(player.field.cards[pos].getcard().GetCost());
				player.powers.set(1);
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
			
			if(id.equals("SSD-09")){
				JOptionPane.showMessageDialog(null, "please select a Siren Character");
				this.selected=-1;
				if(this.player.field.cards[0]!=null&&this.player.field.cards[0].getcard().Getid().equals("SSD-03")){
					this.ptarjet91.setVisible(true);
					this.selected=0;
				}
				if(this.player.field.cards[1]!=null&&this.player.field.cards[1].getcard().Getid().equals("SSD-03")){
					this.ptarjet92.setVisible(true);
					this.selected=1;
				}
				if(this.player.field.cards[2]!=null&&this.player.field.cards[2].getcard().Getid().equals("SSD-03")){
					this.ptarjet93.setVisible(true);
					this.selected=2;
				}
				if(this.player.field.cards[3]!=null&&this.player.field.cards[3].getcard().Getid().equals("SSD-03")){
					this.ptarjet94.setVisible(true);
					this.selected=3;
				}
				if(this.player.field.cards[4]!=null&&this.player.field.cards[4].getcard().Getid().equals("SSD-03")){
					this.ptarjet95.setVisible(true);
					this.selected=4;
				}
				
				if(this.selected==-1){
					JOptionPane.showMessageDialog(null, "sorry, you don't contain a Siren Character in the field");
				}
			}
			
			if(id.equals("SSD-10")){//aca debes poner atencion
				JOptionPane.showMessageDialog(null, "select an ai card to destroy");
				if(this.player.field.cards[0]!=null){
					this.ptarjet101.setVisible(true);
				}
				if(this.player.field.cards[1]!=null){//
					this.ptarjet102.setVisible(true);				
				}
				if(this.player.field.cards[2]!=null){
					this.ptarjet103.setVisible(true);
				}
				if(this.player.field.cards[3]!=null){
					this.ptarjet104.setVisible(true);
				}
				if(this.player.field.cards[4]!=null){
					this.ptarjet105.setVisible(true);
				}
				if(this.ai.aifield.cards[0]!=null){
					this.aitarjet101.setVisible(true);
				}
				
				if(this.ai.aifield.cards[1]!=null){
					this.aitarjet102.setVisible(true);
				}
				
				if(this.ai.aifield.cards[2]!=null){
					this.aitarjet103.setVisible(true);
				}
				
				if(this.ai.aifield.cards[3]!=null){
					this.aitarjet104.setVisible(true);
				}
				
				if(this.ai.aifield.cards[4]!=null){
					this.aitarjet105.setVisible(true);
				}
			}
			
			if(id.equals("SSD-11")){
				JOptionPane.showMessageDialog(null, "select 1 Character to Turn face-down");
				if(this.player.field.cards[0]!=null){
					if(!this.player.field.cards[0].down){
						this.ptarjet111.setVisible(true);
					}	
				}
				if(this.player.field.cards[1]!=null){
					if(!this.player.field.cards[1].down){
						this.ptarjet112.setVisible(true);
					}				
				}
				if(this.player.field.cards[2]!=null){
					if(!this.player.field.cards[2].down){
						this.ptarjet113.setVisible(true);
					}
				}
				if(this.player.field.cards[3]!=null){
					if(!this.player.field.cards[3].down){
						this.ptarjet114.setVisible(true);
					}
				}
				if(this.player.field.cards[4]!=null){
					if(!this.player.field.cards[4].down){
						this.ptarjet115.setVisible(true);
					}
				}
				if(this.ai.aifield.cards[0]!=null){
					if(!this.ai.aifield.cards[0].down){
						this.aitarjet111.setVisible(true);
					}
				}
				
				if(this.ai.aifield.cards[1]!=null){
					if(!this.ai.aifield.cards[1].down){
						this.aitarjet112.setVisible(true);
					}
				}
				
				if(this.ai.aifield.cards[2]!=null){
					if(!this.ai.aifield.cards[2].down){
						this.aitarjet113.setVisible(true);
					}
				}
				
				if(this.ai.aifield.cards[3]!=null){
					if(!this.ai.aifield.cards[3].down){
						this.aitarjet114.setVisible(true);
					}
				}
				
				if(this.ai.aifield.cards[4]!=null){
					if(!this.ai.aifield.cards[4].down){
						this.aitarjet115.setVisible(true);
					}
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
			
			if(id.equals("SSD-12")){
				JOptionPane.showMessageDialog(null, "you get 4 volatile power, use it wisely");
				player.powers.set(4);
			}	

			repaint();
		}
		
	}

	public void makeAiEffect(String id, int pos){
		
		if(this.phases.actual==2){
			
			if(id.equals("SSD-2")){
				int p;
				JOptionPane.showMessageDialog(null, "adding a water power from the deck");
				p= -1;
				p=this.ai.aideck.Deck.posCard("SSD-15");
				if(p==-1){
					JOptionPane.showMessageDialog(null, "cannot find a water power");
				}else{
					
					int poss = ai.aihand.draw(ai.aideck.Deck.ConsultarYextraer(p));
					this.ai.aideck.textField.setText("cards left "+this.ai.aideck.Deck.cardsLeft());
					this.ai.aideck.textField.repaint();
				}
			}
			
			if(id.equals("SSD-4")){
				int p;
				
				JOptionPane.showMessageDialog(null, "AI cards' player will be placed on top of the deck");
				Card c = new Card();
				Random randomGenerator = new Random();
				int test = randomGenerator.nextInt(this.ai.aihand.countcards());
				c=ai.aihand.cards[test];
				ai.aideck.Deck.insertar(c);
				ai.aihand.discard(test);
				
				JOptionPane.showMessageDialog(null, "adding a water power from the deck");
				p= -1;
				p=this.ai.aideck.Deck.posCard("SSD-15");
				if(p==-1){
					JOptionPane.showMessageDialog(null, "cannot find a water power");
				}else{
					
					int poss = ai.aihand.draw(ai.aideck.Deck.ConsultarYextraer(p));
					this.ai.aideck.textField.setText("cards left "+this.ai.aideck.Deck.cardsLeft());
					this.ai.aideck.textField.repaint();
				}
			}
			
			if(id.equals("SSD-05")){
				//NO HAY IMPLEMENTACION DE POWERS EN AI PLAYER
			}
			
			if(id.equals("SSD-06")){
				//NO HAY IMPLEMENTACION DE POWERS EN AI PLAYER
			}
			
			if(id.equals("SSD-07")){
					for(int i=0; i<5; i++){
						this.aiAttack[i]=-1;
						this.aiDest[i]=-1;
					}
					contTargetAttack=0;
					for(int i=0;i<5;i++){
						if(ai.aifield.cards[i]!=null){
							this.aiAttack[i]=1;
							contTargetAttack++;
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
					
					//dest
					int poss= player.hand.draw(this.player.field.cards[this.atkDest].getcard());
					player.hand.handgui[this.atkDest].addMouseListener(this);
					 //Addlisteners2Card(pos-1);
					 player.field.quitar(this.atkDest);
					
					 //origin
					 pos= this.ai.aihand.draw(this.ai.aifield.cards[this.atkOrigin].getcard());
						this.ai.aifield.quitar(this.atkOrigin); 
			}
			
			if(id.equals("SSD-08")){
				for(int i=0; i<5; i++){
					this.aiAttack[i]=-1;
					this.aiDest[i]=-1;
				}
				contTargetAttack=0;
				for(int i=0;i<5;i++){
					if(ai.aifield.cards[i]!=null){
						this.aiAttack[i]=1;
						contTargetAttack++;
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
					if(this.atkDest!=-1){
						//dest
						int poss= player.hand.draw(this.player.field.cards[this.atkDest].getcard());
						player.hand.handgui[this.atkDest].addMouseListener(this);
						 //Addlisteners2Card(pos-1);
						 player.field.quitar(this.atkDest);
						
					}else{
						//origin
						 pos= this.ai.aihand.draw(this.ai.aifield.cards[this.atkOrigin].getcard());
							this.ai.aifield.quitar(this.atkOrigin);
					}
			}
			
			if(id.equals("SSD-09")){
				this.selected=-1;
				if(this.ai.aifield.cards[0]!=null&&this.ai.aifield.cards[0].getcard().Getid().equals("SSD-03")){
					this.selected=0;
				}
				if(this.ai.aifield.cards[1]!=null&&this.ai.aifield.cards[1].getcard().Getid().equals("SSD-03")){
					this.selected=1;
				}
				if(this.ai.aifield.cards[2]!=null&&this.ai.aifield.cards[2].getcard().Getid().equals("SSD-03")){
					this.selected=2;
				}
				if(this.ai.aifield.cards[3]!=null&&this.ai.aifield.cards[3].getcard().Getid().equals("SSD-03")){
					this.selected=3;
				}
				if(this.ai.aifield.cards[4]!=null&&this.ai.aifield.cards[4].getcard().Getid().equals("SSD-03")){
					this.selected=4;
				}
				if(this.selected==-1){
					JOptionPane.showMessageDialog(null, "cannot find a Siren");
				}else{
					this.ai.aifield.quitar(this.selected);
					
					for(int i=0; i<5; i++){
						this.aiDest[i]=-1;
					}
					
					atkDest=-1;
					
					for(int i=0;i<5;i++){
						if(this.player.field.cards[i]!=null){
							this.aiDest[i]=1;
						}
						else{
							this.aiDest[i]=0;
						}
					}	
					
					int band=0;
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
					
					if(this.atkDest!=-1){
						//dest
						this.player.field.quitar(this.atkDest);
					}else{
						JOptionPane.showMessageDialog(null, "can't find a target");
					}
				}
				
				if(id.equals("SSD-10")){
					for(int i=0; i<5; i++){
						this.aiAttack[i]=-1;
						this.aiDest[i]=-1;
					}
					contTargetAttack=0;
					for(int i=0;i<5;i++){
						if(ai.aifield.cards[i]!=null){
							this.aiAttack[i]=1;
							contTargetAttack++;
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
				}
				
				if(this.atkDest!=-1){
					this.player.field.quitar(this.atkDest);
				}
				else{
					this.ai.aifield.quitar(this.atkOrigin);
				}
			}
			
			if(id.equals("SSD-11")){
				for(int i=0; i<5; i++){
					this.aiAttack[i]=-1;
					this.aiDest[i]=-1;
				}
				contTargetAttack=0;
				for(int i=0;i<5;i++){
					if(ai.aifield.cards[i]!=null){
						this.aiAttack[i]=1;
						contTargetAttack++;
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
			
				Card c = new Card();
				
				if(this.atkDest!=-1){
					c=this.player.field.cards[this.atkDest].getcard();
					this.player.field.quitar(this.atkDest);
					try {
						this.player.field.poner(new SmallCard(true,c), this.atkDest);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					c=this.ai.aifield.cards[this.atkOrigin].getcard();
					this.ai.aifield.quitar(this.atkOrigin);
					try {
						this.ai.aifield.poner(new SmallCard(true,c), this.atkOrigin);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			
			repaint();
		}
		if(this.phases.actual==3){
			repaint();
		}
	}
	
	public int contarBarriers(){
		int cont=0;
		for(int i=0; i<5; i++){
			if(player.barriers.cards[i]!=null){
				cont++;
			}
		}
		return cont;
	}
	
	public int contarAiBarriers(){
		int cont=0;
		for(int i=0; i<5; i++){
			if(ai.barriers.cards[i]!=null){
				cont++;
			}
		}
		return cont;
	}
	
	public void repairListeners()
	{
		
		for(int i=0;i<player.hand.current;i++)
		{
			player.hand.handgui[i].removeMouseListener(this);
		}
		
		for(int i=0;i<player.hand.current;i++){
			 Addlisteners2Card(i);
				player.hand.handgui[i].addMouseListener(this);	
		}
		if(player.hand.handgui[player.hand.current]!=null)
		{
			 Addlisteners2Card(player.hand.current);
				player.hand.handgui[player.hand.current].addMouseListener(this);	
		}
		
	}
}
