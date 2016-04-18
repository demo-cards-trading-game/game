package demo;
import demo.Fallen.SimpleColorTableModel;
import extra.RoundedPanel;
import extra.movePanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class PlayGui extends JLayeredPane implements ActionListener, MouseListener {
	public fightpane fight;
	public static PlayerGui player;//aca estan el hand el deck y lo demas
	public AIGui ai;//lo mismo pero en el ai
	public Previewpane preview;
	int p,w,c;//pos , where, cost

	SmallCard Hero;
	boolean checking;//sirve para frenar al hilo que checkea y activa el boton de pago
	int donde;
	int number,X,Y;
	SmallCard moving;
	movePanel animations;
	optionpane op;
	int turn, contTurn=0;
	public int ready=0;
	int s,pl;
	int i=0;
	private Fallen fallen ;
	JInternalFrame pane;
	public Phases phases;
	public Fallen fallenAi;
	public  JButton repaint;
	private JLabel turnoLabel;
	int warriorPlayed; //indica que se jugo un warrior en el turno
	public int cardDrawn, barierpicked;
	public JLabel swordp1,swordp2,swordp3,swordp4,swordp5;
	public JLabel sworda1,sworda2,sworda3,sworda4,sworda5;
	public JLabel dest1, dest2, dest3, dest4, dest5;
	public int atkDest=-1, atkOrigin=-1;
	public int [] aiAttack= new int[5];
	public int [] aiDest= new int[5];
	public int contTargetAttack;
	public JButton j;
	public prueba2 listAll;
	public JButton top1,top2,top3,top4,top5;
	public JLabel ptarjet1, ptarjet2, ptarjet3, ptarjet4, ptarjet5;
	public JLabel aitarjet1, aitarjet2, aitarjet3, aitarjet4, aitarjet5;
	public int selected=-1;
	public RoundedPanel unleash;
	
	public JLabel ptarjet81, ptarjet82, ptarjet83, ptarjet84, ptarjet85;
	public JLabel aitarjet81, aitarjet82, aitarjet83, aitarjet84, aitarjet85;
	//esto es para sdd-09
	public JLabel ptarjet91, ptarjet92, ptarjet93, ptarjet94, ptarjet95;
	public JLabel aitarjet91, aitarjet92, aitarjet93, aitarjet94, aitarjet95;
	//esto es para ssd-10
	public JLabel ptarjet101, ptarjet102, ptarjet103, ptarjet104, ptarjet105;
	public JLabel aitarjet101, aitarjet102, aitarjet103, aitarjet104, aitarjet105;
	//esto es para ssd-11
	public JLabel ptarjet111, ptarjet112, ptarjet113, ptarjet114, ptarjet115;
	public JLabel aitarjet111, aitarjet112, aitarjet113, aitarjet114, aitarjet115;
	//visor de las cartas totales
	public int done;
	public int bugPrimerTurnoUSer=0;
	public int liberarTutoEnd=1;
	public int ubicacionDeCarta;
	public Gui instanciaGui;
	
	public int getPhaseActual(){
		return phases.actual;
	}
	public void battle()
	{
		//int pos=player.field.findwarrior();//busca un warrior en el campo para hacer la animacion
	}

	public PlayGui(int x , int y, String name, Gui g) throws IOException {
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

		unleash=new RoundedPanel();
		unleash.setBounds(0,0,100,145);
		unleash.add (new JLabel(new ImageIcon(ImageIO.read(new File("unleash.png")))));
		pane = new JInternalFrame("THE FALLEN");
		phases=new Phases(220,360);
		add(phases);

		this.add(player);
		repaint=new JButton();

		ai = new AIGui();

		this.add(ai);

		/*******************************************/

		fallenAi=new Fallen();
		add(fallenAi);

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
			
		fallen=new Fallen();
		add(fallen);
		player.pdeck.btnNewButton_1.addMouseListener(this);

		op=new  optionpane();
		//swords
		swordp1= new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
		swordp1.setBounds(240, 350, 50, 120);
		add(swordp1);
		this.moveToFront(swordp1);

		sworda1= new JLabel(new ImageIcon(ImageIO.read(new File("swordR.png"))));
		sworda1.setBounds(0, 0, 540, 385);
		add(sworda1);
		this.moveToFront(sworda1);

		swordp2= new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
		swordp2.setBounds(350, 350, 50, 120);
		add(swordp2);
		this.moveToFront(swordp2);

		sworda2= new JLabel(new ImageIcon(ImageIO.read(new File("swordR.png"))));
		sworda2.setBounds(0, 0, 760, 385);
		add(sworda2);
		this.moveToFront(sworda2);

		swordp3= new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
		swordp3.setBounds(460, 350, 50, 120);
		add(swordp3);
		this.moveToFront(swordp3);

		sworda3= new JLabel(new ImageIcon(ImageIO.read(new File("swordR.png"))));
		sworda3.setBounds(0, 0, 980, 385);
		add(sworda3);
		this.moveToFront(sworda3);

		swordp4= new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
		swordp4.setBounds(570, 350, 50, 120);
		add(swordp4);
		this.moveToFront(swordp4);

		sworda4= new JLabel(new ImageIcon(ImageIO.read(new File("swordR.png"))));
		sworda4.setBounds(0, 0, 1200, 385);
		add(sworda4);
		this.moveToFront(sworda4);

		swordp5= new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
		swordp5.setBounds(680, 350, 50, 120);
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

		swordp1.addMouseListener(this);
		swordp2.addMouseListener(this);
		swordp3.addMouseListener(this);
		swordp4.addMouseListener(this);
		swordp5.addMouseListener(this);
		
		try{
			FileReader turno = new FileReader(new File("turno.txt"));
			BufferedReader br = new BufferedReader(turno);
			this.turn = Integer.parseInt(br.readLine());
			turno.close();
		}catch(Exception e2){ 
			e2.printStackTrace();
		}

		this.turnoLabel = new JLabel();
		
		turnoLabel.setFont(new Font("Comic Sans MS", turnoLabel.getFont().getStyle(), 20));

		turnoLabel.setForeground(Color.green);
		
		
		if(turn==1){//turno player 1
			this.turnoLabel.setText("Player'S turn");

		}else{ //turno player 2
			this.turnoLabel.setText("AI Player'S turn");
			
		}
		this.turnoLabel.setBounds(50, 320, 200, 20);
		
	
		this.turnoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.turnoLabel.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 15));
		add(turnoLabel);

		this.dest1 = new JLabel();
		dest1.setIcon(new ImageIcon("redTarget1.png"));
		this.dest1.setBounds(230, 210, 50, 50);
		this.dest1.setVisible(false);
		add(dest1);
		this.moveToFront(this.dest1);

		this.dest2 = new JLabel();
		dest2.setIcon(new ImageIcon("redTarget1.png"));
		this.dest2.setBounds(340, 210, 50, 50);
		this.dest2.setVisible(false);
		add(dest2);
		this.moveToFront(this.dest2);

		this.dest3 = new JLabel();
		dest3.setIcon(new ImageIcon("redTarget1.png"));
		this.dest3.setBounds(450, 210, 50, 50);
		this.dest3.setVisible(false);
		add(dest3);
		this.moveToFront(this.dest3);

		this.dest4 = new JLabel();
		dest4.setIcon(new ImageIcon("redTarget1.png"));
		this.dest4.setBounds(560, 210, 50, 50);
		this.dest4.setVisible(false);
		add(dest4);
		this.moveToFront(this.dest4);

		this.dest5 = new JLabel();
		dest5.setIcon(new ImageIcon("redTarget1.png"));
		this.dest5.setBounds(670, 210, 50, 50);
		this.dest5.setVisible(false);
		add(dest5);
		this.moveToFront(this.dest5);

		this.dest1.addMouseListener(this);
		this.dest2.addMouseListener(this);
		this.dest3.addMouseListener(this);
		this.dest4.addMouseListener(this);
		this.dest5.addMouseListener(this);

		player.field.addMouseListener(this);

		this.listAll = new prueba2(player.pdeck.Deck);

		this.listAll.setBounds(150, 100, 620, 420);
		this.listAll.setVisible(false);
		this.moveToFront(this.listAll);

		this.listAll.aceptar.addActionListener(this);

		this.top1=new JButton("target");
		this.top1.setBounds(200,580,80,30);
		add(top1);
		this.moveToFront(top1);
		this.top1.addActionListener(this);

		this.top2=new JButton("target");
		this.top2.setBounds(330,580,80,30);
		add(top2);
		this.moveToFront(top2);
		this.top2.addActionListener(this);

		this.top3=new JButton("target");
		this.top3.setBounds(450,580,80,30);
		add(top3);
		this.moveToFront(top3);
		this.top3.addActionListener(this);

		this.top4=new JButton("target");
		this.top4.setBounds(570,580,80,30);
		add(top4);
		this.moveToFront(top4);
		this.top4.addActionListener(this);

		this.top5=new JButton("target");
		this.top5.setBounds(700,580,80,30);
		add(top5);
		this.moveToFront(top5);
		this.top5.addActionListener(this);

		this.top1.setVisible(false);
		this.top2.setVisible(false);
		this.top3.setVisible(false);
		this.top4.setVisible(false);
		this.top5.setVisible(false);
		phases.draw.addMouseListener(this);

		this.ptarjet1= new JLabel();
		this.ptarjet1.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet1.setBounds(230,380, 50, 50);
		this.ptarjet2= new JLabel();
		this.ptarjet2.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet2.setBounds(340,380, 50, 50);
		this.ptarjet3= new JLabel();
		this.ptarjet3.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet3.setBounds(450,380, 50, 50);
		this.ptarjet4= new JLabel();
		this.ptarjet4.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet4.setBounds(560,380, 50, 50);
		this.ptarjet5= new JLabel();
		this.ptarjet5.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet5.setBounds(670,380, 50, 50);
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

		this.aitarjet1= new JLabel();
		this.aitarjet1.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet1.setBounds(230, 210, 50, 50);
		this.aitarjet2= new JLabel();
		this.aitarjet2.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet2.setBounds(340,210, 50, 50);
		this.aitarjet3= new JLabel();
		this.aitarjet3.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet3.setBounds(450,210, 50, 50);
		this.aitarjet4= new JLabel();
		this.aitarjet4.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet4.setBounds(560,210, 50, 50);
		this.aitarjet5= new JLabel();
		this.aitarjet5.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet5.setBounds(670,210, 50, 50);
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

		this.ptarjet1.addMouseListener(this);
		this.ptarjet2.addMouseListener(this);
		this.ptarjet3.addMouseListener(this);
		this.ptarjet4.addMouseListener(this);
		this.ptarjet5.addMouseListener(this);
		this.aitarjet1.addMouseListener(this);
		this.aitarjet2.addMouseListener(this);
		this.aitarjet3.addMouseListener(this);
		this.aitarjet4.addMouseListener(this);
		this.aitarjet5.addMouseListener(this);

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


		this.ptarjet1= new JLabel();
		this.ptarjet1.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet1.setBounds(230,380, 50, 50);
		this.ptarjet2= new JLabel();
		this.ptarjet2.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet2.setBounds(340,380, 50, 50);
		this.ptarjet3= new JLabel();
		this.ptarjet3.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet3.setBounds(450,380, 50, 50);
		this.ptarjet4= new JLabel();
		this.ptarjet4.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet4.setBounds(560,380, 50, 50);
		this.ptarjet5= new JLabel();
		this.ptarjet5.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet5.setBounds(670,380, 50, 50);
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

		this.aitarjet1= new JLabel();
		this.aitarjet1.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet1.setBounds(230, 210, 50, 50);
		this.aitarjet2= new JLabel();
		this.aitarjet2.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet2.setBounds(340,210, 50, 50);
		this.aitarjet3= new JLabel();
		this.aitarjet3.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet3.setBounds(450,210, 50, 50);
		this.aitarjet4= new JLabel();
		this.aitarjet4.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet4.setBounds(560,210, 50, 50);
		this.aitarjet5= new JLabel();
		this.aitarjet5.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet5.setBounds(670,210, 50, 50);
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

		this.ptarjet1.addMouseListener(this);
		this.ptarjet2.addMouseListener(this);
		this.ptarjet3.addMouseListener(this);
		this.ptarjet4.addMouseListener(this);
		this.ptarjet5.addMouseListener(this);
		this.aitarjet1.addMouseListener(this);
		this.aitarjet2.addMouseListener(this);
		this.aitarjet3.addMouseListener(this);
		this.aitarjet4.addMouseListener(this);
		this.aitarjet5.addMouseListener(this);

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


		this.ptarjet81= new JLabel();
		this.ptarjet81.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet81.setBounds(230,380, 50, 50);
		this.ptarjet82= new JLabel();
		this.ptarjet82.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet82.setBounds(340,380, 50, 50);
		this.ptarjet83= new JLabel();
		this.ptarjet83.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet83.setBounds(450,380, 50, 50);
		this.ptarjet84= new JLabel();
		this.ptarjet84.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet84.setBounds(560,380, 50, 50);
		this.ptarjet85= new JLabel();
		this.ptarjet85.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet85.setBounds(670,380, 50, 50);
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

		this.aitarjet81= new JLabel();
		this.aitarjet81.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet81.setBounds(230, 210, 50, 50);
		this.aitarjet82= new JLabel();
		this.aitarjet82.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet82.setBounds(340,210, 50, 50);
		this.aitarjet83= new JLabel();
		this.aitarjet83.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet83.setBounds(450,210, 50, 50);
		this.aitarjet84= new JLabel();
		this.aitarjet84.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet84.setBounds(560,210, 50, 50);
		this.aitarjet85= new JLabel();
		this.aitarjet85.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet85.setBounds(670,210, 50, 50);
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

		this.ptarjet81.addMouseListener(this);
		this.ptarjet82.addMouseListener(this);
		this.ptarjet83.addMouseListener(this);
		this.ptarjet84.addMouseListener(this);
		this.ptarjet85.addMouseListener(this);
		this.aitarjet81.addMouseListener(this);
		this.aitarjet82.addMouseListener(this);
		this.aitarjet83.addMouseListener(this);
		this.aitarjet84.addMouseListener(this);
		this.aitarjet85.addMouseListener(this);

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




		this.ptarjet91= new JLabel();
		this.ptarjet91.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet91.setBounds(230,380, 50, 50);
		this.ptarjet92 = new JLabel();
		this.ptarjet92.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet92.setBounds(340, 380, 50, 50);
		this.ptarjet93 = new JLabel();
		this.ptarjet93.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet93.setBounds(450, 380, 50, 50);
		this.ptarjet94 = new JLabel();
		this.ptarjet94.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet94.setBounds(560, 380, 50, 50);
		this.ptarjet95 = new JLabel();
		this.ptarjet95.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet95.setBounds(670, 380, 50, 50);
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

		this.aitarjet91 = new JLabel();
		this.aitarjet91.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet91.setBounds(230, 210, 50, 50);
		this.aitarjet92 = new JLabel();
		this.aitarjet92.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet92.setBounds(340, 210, 50, 50);
		this.aitarjet93 = new JLabel();
		this.aitarjet93.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet93.setBounds(450, 210, 50, 50);
		this.aitarjet94 = new JLabel();
		this.aitarjet94.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet94.setBounds(560, 210, 50, 50);
		this.aitarjet95 = new JLabel();
		this.aitarjet95.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet95.setBounds(670, 210, 50, 50);
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

		this.ptarjet91.addMouseListener(this);
		this.ptarjet92.addMouseListener(this);
		this.ptarjet93.addMouseListener(this);
		this.ptarjet94.addMouseListener(this);
		this.ptarjet95.addMouseListener(this);
		this.aitarjet91.addMouseListener(this);
		this.aitarjet92.addMouseListener(this);
		this.aitarjet93.addMouseListener(this);
		this.aitarjet94.addMouseListener(this);
		this.aitarjet95.addMouseListener(this);

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

		this.ptarjet101 = new JLabel();
		this.ptarjet101.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet101.setBounds(230, 380, 50, 50);
		this.ptarjet102 = new JLabel();
		this.ptarjet102.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet102.setBounds(340, 380, 50, 50);
		this.ptarjet103 = new JLabel();
		this.ptarjet103.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet103.setBounds(450, 380, 50, 50);
		this.ptarjet104 = new JLabel();
		this.ptarjet104.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet104.setBounds(560, 380, 50, 50);
		this.ptarjet105 = new JLabel();
		this.ptarjet105.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet105.setBounds(670, 380, 50, 50);
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

		this.aitarjet101 = new JLabel();
		this.aitarjet101.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet101.setBounds(230, 210, 50, 50);
		this.aitarjet102 = new JLabel();
		this.aitarjet101.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet102.setBounds(340, 210, 50, 50);
		this.aitarjet103 = new JLabel();
		this.aitarjet101.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet103.setBounds(450, 210, 50, 50);
		this.aitarjet104 = new JLabel();
		this.aitarjet101.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet104.setBounds(560, 210, 50, 50);
		this.aitarjet105 = new JLabel();
		this.aitarjet101.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet105.setBounds(670, 210, 50, 50);
		add(aitarjet101);
		add(aitarjet103);
		add(aitarjet102);
		add(aitarjet104);
		add(aitarjet105);
		this.moveToFront(aitarjet101);
		this.moveToFront(aitarjet102);
		this.moveToFront(aitarjet103);
		this.moveToFront(aitarjet104);
		this.moveToFront(aitarjet105);
		this.aitarjet102.addMouseListener(this);

		this.ptarjet102.addMouseListener(this);
		this.ptarjet101.addMouseListener(this);
		this.ptarjet103.addMouseListener(this);
		this.ptarjet104.addMouseListener(this);
		this.ptarjet105.addMouseListener(this);
		this.aitarjet101.addMouseListener(this);
		this.aitarjet103.addMouseListener(this);
		this.aitarjet104.addMouseListener(this);
		this.aitarjet105.addMouseListener(this);

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

		this.ptarjet111 = new JLabel();
		this.ptarjet111.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet111.setBounds(230, 380, 50, 50);
		this.ptarjet112 = new JLabel();
		this.ptarjet112.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet112.setBounds(340, 380, 50, 50);
		this.ptarjet113 = new JLabel();
		this.ptarjet113.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet113.setBounds(450, 380, 50, 50);
		this.ptarjet114 = new JLabel();
		this.ptarjet114.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet114.setBounds(560, 380, 50, 50);
		this.ptarjet115 = new JLabel();
		this.ptarjet115.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet115.setBounds(670, 380, 50, 50);
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

		this.aitarjet111 = new JLabel();
		this.aitarjet111.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet111.setBounds(230, 210, 50, 50);
		this.aitarjet112 = new JLabel();
		this.aitarjet112.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet112.setBounds(340, 210, 50, 50);
		this.aitarjet113 = new JLabel();
		this.aitarjet113.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet113.setBounds(450, 210, 50, 50);
		this.aitarjet114 = new JLabel();
		this.aitarjet114.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet114.setBounds(560, 210, 50, 50);
		this.aitarjet115 = new JLabel();
		this.aitarjet115.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet115.setBounds(670, 210, 50, 50);
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

		this.ptarjet111.addMouseListener(this);
		this.ptarjet112.addMouseListener(this);
		this.ptarjet113.addMouseListener(this);
		this.ptarjet114.addMouseListener(this);
		this.ptarjet115.addMouseListener(this);
		this.aitarjet111.addMouseListener(this);
		this.aitarjet112.addMouseListener(this);
		this.aitarjet113.addMouseListener(this);
		this.aitarjet114.addMouseListener(this);
		this.aitarjet115.addMouseListener(this);

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


		for (int i = 0; i < player.hand.current; i++)
			player.hand.handgui[i].Play.setEnabled(false);

		player.pdeck.Play.addActionListener(this);
		player.pdeck.Preview.addActionListener(this);
		animations= new movePanel();
		add(animations);
		moveToFront(animations);
		animations.setOpaque(false);
		fallen.confirmcardsfromfallen.addActionListener(this);
		fallenAi.confirmcardsfromfallen.addActionListener(this);
		ai.aideck.btnNewButton_1.addMouseListener(this);
		if(turn==1){
			this.phases.end.addMouseListener(this);

			Thread t = new Thread(new Runnable(){

				public void run(){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
		}
		this.instanciaGui = g;
	}

	public void actionPerformed(ActionEvent e) {
		
		done = 0;
		if (e.getSource() == fallenAi.confirmcardsfromfallen) {
			SmallCard aux = null;
			System.out.println("entro");
			
			try {
				aux = new SmallCard(false, fallenAi.cards[0].getcard());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			final int where = ai.aifield.findwhere();
			if (fallenAi.effectnumber == 13) // si es el efecto 13
			{
				if (aux != null) {
					number = aux.getcard().GetCardNumber();
				}
				ai.aifield.poner(aux, where);
				fallenAi.remove();
				fallenAi.confirmcardsfromfallen.setEnabled(false);
				fallenAi.button.setEnabled(false);
				fallenAi.effectnumber = 0;
				fallenAi.setVisible(false);
				if (!Objects.equals(aux.actual.GetType(), "Warrior")) {
					Thread t1 = new Thread(new Runnable() {
						public void run() {
							try {
								Thread.sleep(750);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							ai.aifield.quitar(where);
							if (number == 18) {
								ai.aidra.setwp();
							}
							repaint();
						}
					});
					t1.start();
				}
			}
		}
		if(e.getSource()==fallen.confirmcardsfromfallen)
		{
			SmallCard aux = null;
			System.out.println("entro");
			final int where=player.field.findwhere();
			System.out.println(where);
			try {
				aux = new SmallCard(false,fallen.cards[0].getcard());
				if(fallen.effectnumber==13)//si es el efecto 13
				{
					number  =aux.getcard().GetCardNumber();
					player.field.poner(aux,where);
					fallen.remove();
					fallen.confirmcardsfromfallen.setEnabled(false);
					fallen.button.setEnabled(false);
					fallen.effectnumber=0;
					fallen.setVisible(false);
					if (!Objects.equals(aux.actual.GetType(), "Warrior")) {
						Thread t1 = new Thread(new Runnable() {
							public void run() {
								try {
									Thread.sleep(750);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								player.field.quitar(where);
								if(number==18)
								{
									player.powers.setwp();
								}
								repaint();
								fallenAi.setVisible(true);

								fallenAi.effectnumber=13;
								fallenAi.button.setEnabled(true);
							}
						});
						t1.start();
					}
				}
				else{
					player.hand.draw(fallen.cards[0].getcard() );
					player.hand.draw(fallen.cards[1].getcard() );
					fallen.confirmcardsfromfallen.setEnabled(false);
					if(fallen.cards[0]!=null)
					{
						fallen.remove(fallen.cards[0]);
					}
					if(fallen.cards[1]!=null)
					{
						fallen.remove(fallen.cards[1]);
					}
					if(fallen.cards[2]!=null)
					{
						fallen.remove(fallen.cards[2]);
					}
					fallen.setVisible(false);
					fallen.button.setEnabled(false);
				}
				fallen.selecting=0;
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		s = -1;
		if (e.getSource() == player.hand.handgui[0].Set) {
			if (done == 0) {
				s = 0;
			}
			done = 1;
		}
		if (e.getSource() == player.hand.handgui[1].Set) {
			if (done == 0) {
				s = 1;
			}
			done = 1;
		}
		if (e.getSource() == player.hand.handgui[2].Set) {
			if (done == 0) {
				s = 2;
			}
			done = 1;
		}
		if (e.getSource() == player.hand.handgui[3].Set) {
			if (done == 0) {
				s = 3;
			}
			done = 1;
		}
		if (e.getSource() == player.hand.handgui[4].Set) {
			if (done == 0) {
				s = 4;
			}
			done = 1;
		}
		if (player.hand.handgui[5] != null) {
			if (e.getSource() == player.hand.handgui[5].Set) {
				if (done == 0) {
					s = 5;
				}
				done = 1;
			}
		}
		if (s != -1) {
			fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(), player.hand.cards[s]);
			if (player.hand.cards[s].GetCardNumber() == 18)
				player.powers.setwp();
			else
				player.powers.set();
			player.hand.discard(s + 1);

			if(phases.actual==2){
				this.repairListeners(true);
			}else
			{
				this.repairListeners(false);
			}
		}

		if(e.getSource()==player.hand.handgui[1].Discard)//si se le da play a la carta 1  
		{
			if(done==0){
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[1]);
				player.hand.discard(2);
				if(phases.actual==2){
					this.repairListeners(true);
				}else
				{
					this.repairListeners(false);
				}
			}
			done=1;
		}
		if(e.getSource()==player.hand.handgui[2].Discard)//si se le da play a la carta 1  
		{
			if(done==0)
			{
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[2]);
				player.hand.discard(3);
				if(phases.actual==2){
					this.repairListeners(true);
				}else
				{
					this.repairListeners(false);
				}
			}
			done=1;
		}
		if(e.getSource()==player.hand.handgui[3].Discard)//si se le da play a la carta 1  
		{
			if(done==0)
			{
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[3]);
				player.hand.discard(4);
				if(phases.actual==2){
					this.repairListeners(true);
				}else
				{
					this.repairListeners(false);
				}
			}
			done=1;
		}
		if(e.getSource()==player.hand.handgui[4].Discard)//si se le da play a la carta 1  
		{
			if(done==0)
			{
				fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[4]);

				if(phases.actual==2){
					this.repairListeners(true);
				}else
				{
					this.repairListeners(false);
				}
			}
			done=1;
		}
		
		if(e.getSource()==player.pdeck.Preview)//muestra la carta 1 
		{
			if(done==0)
			{
				preview.addCard(new BigCard(player.pdeck.Hero.getcard(),0,0));
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

			if(phases.actual==2){
				this.repairListeners(true);
			}else
			{
				this.repairListeners(false);
			}

			done=1;
		}
		
		pl=-1;
		if(e.getSource()==player.pdeck.Play)
		{
			pl=-2;
		}
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
			if(pl!=-2){
				if(player.hand.cards[pl].Getid().equals("SSD-10")&&(contarBarriers()>=0)){
					System.out.println("You must have 0 barriers to play this card");
				}
				else{
					if(done==0)
						play(pl);
					done=1;
					this.repairListeners(true);
				}
			}else
			{
				if(done==0)
					play(pl);
				done=1;
				this.repairListeners(true);
			}
		}

		if(e.getSource()==j){
			this.listAll = new prueba2(player.pdeck.Deck);
			this.listAll.setBounds(150, 100, 620, 420);
			add(this.listAll);
			this.moveToFront(this.listAll);
			this.listAll.aceptar.addActionListener(this);
		}

		if(e.getSource()==this.listAll.aceptar){
			this.listAll.setVisible(false);
			this.listAll.opciones.setVisible(false);
			int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(this.listAll.num));
			player.hand.handgui[pos-1].addMouseListener(this);
			Addlisteners2Card(pos-1);
			player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
			player.pdeck.textField.repaint();
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

			System.out.println("your card will be placed on top of the deck");
			Card c = new Card();
			c=player.hand.cards[0];	
			player.pdeck.Deck.insertar(c);
			player.hand.discard(1);
			this.repairListeners(true);

			System.out.println("adding a water power from the deck");
			p= player.pdeck.Deck.posCard("SSD-15");
			if(p==-1){
				System.out.println("cannot find a water power");
			}else{
				int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
				player.hand.handgui[pos-1].addMouseListener(this);
				Addlisteners2Card(pos-1);
				player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
				player.pdeck.textField.repaint();
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

			System.out.println("your card will be placed on top of the deck");
			Card c = new Card();
			c=player.hand.cards[1];	
			player.pdeck.Deck.insertar(c);
			player.hand.discard(2);
			this.repairListeners(true);

			System.out.println("adding a water power from the deck");
			p= player.pdeck.Deck.posCard("SSD-15");
			if(p==-1){
				System.out.println("cannot find a water power");
			}else{
				int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
				player.hand.handgui[pos-1].addMouseListener(this);
				Addlisteners2Card(pos-1);
				player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
				player.pdeck.textField.repaint();
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

			System.out.println("your card will be placed on top of the deck");
			Card c = new Card();
			c=player.hand.cards[2];	
			player.pdeck.Deck.insertar(c);
			player.hand.discard(3);
			this.repairListeners(true);

			System.out.println("adding a water power from the deck");
			p= player.pdeck.Deck.posCard("SSD-15");
			if(p==-1){
				System.out.println("cannot find a water power");
			}else{
				int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
				player.hand.handgui[pos-1].addMouseListener(this);
				Addlisteners2Card(pos-1);
				player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
				player.pdeck.textField.repaint();
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

			System.out.println("your card will be placed on top of the deck");
			Card c = new Card();
			c=player.hand.cards[3];	
			player.pdeck.Deck.insertar(c);
			player.hand.discard(4);
			this.repairListeners(true);

			System.out.println("adding a water power from the deck");
			p= player.pdeck.Deck.posCard("SSD-15");
			if(p==-1){
				System.out.println("cannot find a water power");
			}else{
				int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
				player.hand.handgui[pos-1].addMouseListener(this);
				Addlisteners2Card(pos-1);
				player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
				player.pdeck.textField.repaint();
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

			System.out.println("your card will be placed on top of the deck");
			Card c = new Card();
			c=player.hand.cards[4];	
			player.pdeck.Deck.insertar(c);
			player.hand.discard(5);

			this.repairListeners(true);

			System.out.println("adding a water power from the deck");
			p= player.pdeck.Deck.posCard("SSD-15");
			if(p==-1){
				System.out.println("cannot find a water power");
			}else{
				int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
				player.hand.handgui[pos-1].addMouseListener(this);
				Addlisteners2Card(pos-1);
				player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
				player.pdeck.textField.repaint();
			}
			repaint();
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
		if(e.getSource()==Hero)
		{
			remove(unleash);
			play(-3);
			repaint();
		}
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			if(e.getClickCount()==1)
			{
				if(e.getSource()==ai.aideck.btnNewButton_1)
				{
					fallenAi.setVisible(true);
					moveToFront(fallenAi);
				}

				if(barierpicked==0)
				{
					if(e.getSource()== Barriers.barriers[0])//si se da click a la barrera 0
					{
						int pos= player.hand.draw(Barriers.cards[0]);
						player.hand.handgui[pos-1].addMouseListener(this);
						Addlisteners2Card(pos-1);
						player.barriers.removebarrier(0);
						barierpicked=1;
						repaint();
						repairListeners(false);
					}
					if(e.getSource()== Barriers.barriers[1])
					{
						int pos= player.hand.draw(Barriers.cards[1]);
						player.hand.handgui[pos-1].addMouseListener(this);
						Addlisteners2Card(pos-1);
						player.barriers.removebarrier(1);
						barierpicked=1;
						repaint();
						repairListeners(false);
					}
					if(e.getSource()== Barriers.barriers[2])
					{
						int pos= player.hand.draw(Barriers.cards[2]);
						player.hand.handgui[pos-1].addMouseListener(this);
						Addlisteners2Card(pos-1);
						player.barriers.removebarrier(2);
						barierpicked=1;
						repairListeners(false);
						repaint();
					}
					if(e.getSource()== Barriers.barriers[3])
					{
						int pos= player.hand.draw(Barriers.cards[3]);
						player.hand.handgui[pos-1].addMouseListener(this);
						Addlisteners2Card(pos-1);
						player.barriers.removebarrier(3);
						barierpicked=1;
						repairListeners(false);
						repaint();
					}
					if(e.getSource()== Barriers.barriers[4])
					{
						int pos= player.hand.draw(Barriers.cards[4]);
						player.hand.handgui[pos-1].addMouseListener(this);
						Addlisteners2Card(pos-1);
						player.barriers.removebarrier(4);
						barierpicked=1;
						repairListeners(false);
						repaint();
					}
				}else
				{
					if(phases.actual==1){
						System.out.println("sorry u can only get a card from barriers per turn");
					}
				}	
				
				if((e.getSource()==phases.setup)||(e.getSource()==phases.draw)||(e.getSource()==phases.action)||(e.getSource()==phases.attack)||(e.getSource()==phases.end))
				{
					repaint();
					done=1;
					if (e.getSource()==phases.end && phases.actual < 3) {
						barierpicked=0;
						warriorPlayed=0;
						cardDrawn=0;
						
						for(int i=0;i<player.hand.current;i++)
							player.hand.handgui[i].Play.setEnabled(false);
						
						this.phases.setup.removeMouseListener(this);
						this.phases.draw.removeMouseListener(this);
						this.phases.action.removeMouseListener(this);
						this.phases.attack.removeMouseListener(this);
						phases.change(4);
					}
					else {
						if(phases.actual<4){
							phases.change(phases.actual+1);
						}else{
							if(ready==1){
								phases.change(0);
							}
						}
					}
					
					switch(phases.actual){
						//setup
						case 0:
							barierpicked=0;
							warriorPlayed=0;
							cardDrawn=0;
						
							for(int i=0;i<player.hand.current;i++)
								player.hand.handgui[i].Play.setEnabled(false);

							this.phases.setup.removeMouseListener(this);
							this.phases.draw.removeMouseListener(this);
							this.phases.draw.addMouseListener(this);
							break;
						case 1:
							this.phases.draw.removeMouseListener(this);
							for(int i=0;i<5;i++)
							{
								if(Barriers.barriers[i]!=null){
									Barriers.barriers[i].addMouseListener(this);
								}
							}
							this.phases.action.addMouseListener(this);
							break;
						case 2:
							for(int i=0;i<5;i++)
							{
								if(Barriers.barriers[i]!=null){
									Barriers.barriers[i].removeMouseListener(this);
								}
							}
							this.phases.action.removeMouseListener(this);
							this.phases.attack.addMouseListener(this);

							for(int i=0;i<player.hand.current;i++)
								player.hand.handgui[i].Play.setEnabled(true);
							player.pdeck.Play.setEnabled(true);
							break;
						case 3:
							liberarTutoEnd=0;
							battle();
							this.phases.attack.removeMouseListener(this);

							for(int i=0;i<5;i++)
								player.hand.handgui[i].Play.setEnabled(false);
						
							if(this.turn==1&&this.contTurn>0){
								for(int i=0;i<5;i++){
									if(player.field.cards[i]!=null){
										switch(i){
											case 0:
												if(!player.field.cards[0].down){
													this.swordp1.setVisible(true);
												}
												break;
											case 1:
												if(!player.field.cards[1].down){
													this.swordp2.setVisible(true);
												}
												break;
											case 2:
												if(!player.field.cards[2].down){
													this.swordp3.setVisible(true);
												}
												break;
											case 3:
												if(!player.field.cards[3].down){
													this.swordp4.setVisible(true);
												}
												break;
											case 4:
												if(!player.field.cards[4].down){
													this.swordp5.setVisible(true);
												}
												break;
										}
									}
								}
							}
							break;
						case 4:
							if(ready==1){//funciona como segunda oportunidad

								for (int i=0;i<5;i++)
									Barriers.barriers[i].removeMouseListener(this);
								ready=0;
								this.phases.setup.addMouseListener(this);

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

								this.dest1.setVisible(false);
								this.dest2.setVisible(false);
								this.dest3.setVisible(false);
								this.dest4.setVisible(false);
								this.dest5.setVisible(false);
								if(turn==1){
									turn=2;
									this.turnoLabel.setText("AI Player'S turn");
								}else{
									turn=1;
									this.turnoLabel.setText("Player'S turn");
								}
								repaint();
								repaint();
								this.contTurn++;
								this.atkDest=this.atkOrigin=-1;
							}else
							{
//							tuto.end();
								ready=1;
							}
							this.phases.end.removeMouseListener(this);
							//turno del oponente
							phases.change(0);
							ai.aideck.btnNewButton.addMouseListener(this);
							try {
								Aiturn();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							break;
					}
					repaint();
					done=0;
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
						this.swordp1.setVisible(true);
						this.swordp2.setVisible(false);
						this.swordp3.setVisible(false);
						this.swordp4.setVisible(false);
						this.swordp5.setVisible(false);
					}	
					if(e.getSource()==player.field.cards[1]&&!player.field.cards[1].down)
					{
						this.swordp2.setVisible(true);
						this.swordp1.setVisible(false);
						this.swordp3.setVisible(false);
						this.swordp4.setVisible(false);
						this.swordp5.setVisible(false);
					}
					if(e.getSource()==player.field.cards[2]&&!player.field.cards[2].down)
					{
						this.swordp3.setVisible(true);
						this.swordp1.setVisible(false);
						this.swordp2.setVisible(false);
						this.swordp4.setVisible(false);
						this.swordp5.setVisible(false);
					}
					if(e.getSource()==player.field.cards[3]&&!player.field.cards[3].down)
					{
						this.swordp4.setVisible(true);
						this.swordp1.setVisible(false);
						this.swordp2.setVisible(false);
						this.swordp3.setVisible(false);
						this.swordp5.setVisible(false);

					}if(e.getSource()==player.field.cards[4]&&!player.field.cards[4].down)
					{
						this.swordp5.setVisible(true);
						this.swordp1.setVisible(false);
						this.swordp2.setVisible(false);
						this.swordp3.setVisible(false);
						this.swordp4.setVisible(false);
					}
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
					System.out.println("please select a card from ai field");
					repaint();
				}

				if(e.getSource()==this.aitarjet1||e.getSource()==this.aitarjet2||e.getSource()==this.aitarjet3||e.getSource()==this.aitarjet4||e.getSource()==this.aitarjet5){
					int pos= player.hand.draw(player.field.cards[this.selected-1].getcard());
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

					this.ai.aifield.quitar(this.selected-1);
					System.out.println("cards were returned to the hands of owners");
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
						this.ai.aifield.quitar(this.selected-1);
					}
					else{
						pos= player.hand.draw(player.field.cards[this.selected-1].getcard());
						player.hand.handgui[pos-1].addMouseListener(this);
						Addlisteners2Card(pos-1);
						player.field.quitar(this.selected-1);
					}

					repaint();
					System.out.println("cards were returned to the hands of owners");
				}

				if(e.getSource()==this.ptarjet91||e.getSource()==this.ptarjet92||e.getSource()==this.ptarjet93||e.getSource()==this.ptarjet94||e.getSource()==this.ptarjet95){
					this.ptarjet91.setVisible(false);
					this.ptarjet92.setVisible(false);
					this.ptarjet93.setVisible(false);
					this.ptarjet94.setVisible(false);
					this.ptarjet95.setVisible(false);

					player.field.quitar(this.selected);
					System.out.println("select an ai card to destroy");

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

					System.out.println("destroyed succefully");
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
						player.field.quitar(0);
					}
					if(e.getSource()==this.ptarjet102){
						player.field.quitar(1);
					}
					if(e.getSource()==this.ptarjet103){
						player.field.quitar(2);
					}
					if(e.getSource()==this.ptarjet104){
						player.field.quitar(3);
					}
					if(e.getSource()==this.ptarjet105){
						player.field.quitar(4);
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

					System.out.println("destroyed succefully");
					repaint();
				}

				if(e.getSource()==this.ptarjet111||e.getSource()==this.ptarjet112||e.getSource()==this.ptarjet113||e.getSource()==this.ptarjet114||e.getSource()==this.ptarjet115||e.getSource()==this.aitarjet111||e.getSource()==this.aitarjet112||e.getSource()==this.aitarjet113||e.getSource()==this.aitarjet114||e.getSource()==this.aitarjet115){
					Card c;

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
						c= player.field.cards[0].getcard();
						player.field.quitar(0);
						try {
							player.field.poner(new SmallCard(true,c), 0);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.ptarjet112){
						c= player.field.cards[1].getcard();
						player.field.quitar(1);
						try {
							player.field.poner(new SmallCard(true,c), 1);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.ptarjet113){
						c= player.field.cards[2].getcard();
						player.field.quitar(2);
						try {
							player.field.poner(new SmallCard(true,c), 2);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.ptarjet114){
						c= player.field.cards[3].getcard();
						player.field.quitar(3);
						try {
							player.field.poner(new SmallCard(true,c), 3);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.ptarjet115){
						c= player.field.cards[4].getcard();
						player.field.quitar(4);
						try {
							player.field.poner(new SmallCard(true,c), 4);
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
					System.out.println("face down succesfully");
					repaint();
				}

				if(e.getSource()==this.swordp1||e.getSource()==this.swordp2||e.getSource()==this.swordp3||e.getSource()==this.swordp4||e.getSource()==this.swordp5){
					if(e.getSource()==this.swordp1){
						this.atkOrigin=1;
					}
					if(e.getSource()==this.swordp2){
						this.atkOrigin=2;
					}
					if(e.getSource()==this.swordp3){
						this.atkOrigin=3;
					}
					if(e.getSource()==this.swordp4){
						this.atkOrigin=4;
					}
					if(e.getSource()==this.swordp5){
						this.atkOrigin=5;
					}
					if(ai.aifield.countcards()==0)
					{
						int which=ai.barriers.findwhich();//verifica que exista un barrier
						System.out.println("congratulations , direct hit");
						if(ai.aihand.current==5){	
							ai.aihand.discard(5);
						}
						if(which!=-1)//existe un barrier
						{
							ai.barriers.removebarrier(which);
							Barriers2.barriers[which].setVisible(false);
							repaint();
						}
						else{
							//gana el usuario
							instanciaGui.doWin();
							repaint();
						}

						if(e.getSource()==this.swordp1){
							this.swordp1.setVisible(false);
						}
						if(e.getSource()==this.swordp2){
							this.swordp2.setVisible(false);
						}
						if(e.getSource()==this.swordp3){
							this.swordp3.setVisible(false);
						}
						if(e.getSource()==this.swordp4){
							this.swordp4.setVisible(false);
						}
						if(e.getSource()==this.swordp5){
							this.swordp5.setVisible(false);
						}
					}else{
						System.out.println("Select Your Target");
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

					swordp1.setVisible(false);
					swordp2.setVisible(false);
					swordp3.setVisible(false);
					swordp4.setVisible(false);
					swordp5.setVisible(false);

					System.out.println("Card "+this.atkOrigin+" attack to ai Card "+this.atkDest);
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
			}

			if (e.getSource()==player.pdeck.btnNewButton_1)// si se le da click al boton de fallen 
			{
				fallen.setVisible(true);
				moveToFront(fallen);
			}

			
		}	    
		else if(e.getButton() == MouseEvent.BUTTON3)
		{
			if(e.getClickCount()==1)
			{
				if(e.getSource()==player.field.cards[0])
				{
					if(player.field.cards[0].getcard().Getid().equals("SSD-01")){
						System.out.println("This card referred to 4 water power");
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
						System.out.println("This card referred to 4 water power");
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
						System.out.println("This card referred to 4 water power");
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
						System.out.println("This card referred to 4 water power");
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
					System.out.println("This card referred to 4 water power");
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

	public void mouseExited(MouseEvent e) {
		if(e.getSource()== Barriers.barriers[0])
		{
			Barriers.barriers[0].setBackground(new Color(128, 128, 128));
			repaint();
		}
		if(e.getSource()== Barriers.barriers[1])
		{
			Barriers.barriers[1].setBackground(new Color(128, 128, 128));
			repaint();
		}
		if(e.getSource()== Barriers.barriers[2])
		{
			Barriers.barriers[2].setBackground(new Color(128, 128, 128));
			repaint();
		}
		if(e.getSource()== Barriers.barriers[3])
		{
			Barriers.barriers[3].setBackground(new Color(128, 128, 128));
			repaint();
		}
		if(e.getSource()== Barriers.barriers[4])
		{
			Barriers.barriers[4].setBackground(new Color(128, 128, 128));
			repaint();
		}
		
		if(e.getSource()==Hero)
		{
			remove(unleash);
			repaint();
		}

		int x=-1;
		int x2;

		if(player.hand.current<5)
			x2=100;
		else
			x2=400/(player.hand.current-1);
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
				player.hand.handgui[x].setBounds(x*x2+30,20,100,145);
			}else
			{
				player.hand.handgui[x].setBounds(528,20,100,145);
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

		if (e.getSource()==player.pdeck.btnNewButton_1)
		{
			player.pdeck.btnNewButton_1.setIcon(new ImageIcon("fallen1.png"));
		}

		if (e.getSource()==player.pdeck.btnNewButton_2)
		{
			player.pdeck.btnNewButton_2.setIcon(new ImageIcon("forgotten1.png"));
		}
		
		if(e.getSource()==phases.setup){
			phases.setup.setIcon(new ImageIcon(("setup.png")));
		}
		if(e.getSource()==phases.action){
			phases.action.setIcon(new ImageIcon(("action.png")));
		}
		if(e.getSource()==phases.draw){
			phases.draw.setIcon(new ImageIcon(("draw.png")));
		}
		if(e.getSource()==phases.attack){
			phases.attack.setIcon(new ImageIcon(("attack.png")));
		}
		if(e.getSource()==phases.end){
			phases.end.setIcon(new ImageIcon(("end.png")));
		}
		
		if(e.getSource()==ptarjet1){
			ptarjet1.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet2){
			ptarjet2.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet3){
			ptarjet3.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet4){
			ptarjet4.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet5){
			ptarjet5.setIcon(new ImageIcon("redTarget1.png"));
		}
		
		if(e.getSource()==aitarjet1){
			aitarjet1.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet2){
			aitarjet2.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet3){
			aitarjet3.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet4){
			aitarjet4.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet5){
			aitarjet5.setIcon(new ImageIcon("redTarget1.png"));
		}
		
		if(e.getSource()==ptarjet81){
			ptarjet81.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet82){
			ptarjet82.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet83){
			ptarjet83.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet84){
			ptarjet84.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet85){
			ptarjet85.setIcon(new ImageIcon("redTarget1.png"));
		}
		
		if(e.getSource()==aitarjet81){
			aitarjet81.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet82){
			aitarjet82.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet83){
			aitarjet83.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet84){
			aitarjet84.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet85){
			aitarjet85.setIcon(new ImageIcon("redTarget1.png"));
		}

		if(e.getSource()==ptarjet91){
			ptarjet91.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet92){
			ptarjet92.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet93){
			ptarjet93.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet94){
			ptarjet94.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet95){
			ptarjet95.setIcon(new ImageIcon("redTarget1.png"));
		}
		
		if(e.getSource()==aitarjet91){
			aitarjet91.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet92){
			aitarjet92.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet93){
			aitarjet93.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet94){
			aitarjet94.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet95){
			aitarjet95.setIcon(new ImageIcon("redTarget1.png"));
		}

		if(e.getSource()==ptarjet101){
			ptarjet101.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet102){
			ptarjet102.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet103){
			ptarjet103.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet104){
			ptarjet104.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet105){
			ptarjet105.setIcon(new ImageIcon("redTarget1.png"));
		}
		
		if(e.getSource()==aitarjet101){
			aitarjet101.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet102){
			aitarjet102.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet103){
			aitarjet103.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet104){
			aitarjet104.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet105){
			aitarjet105.setIcon(new ImageIcon("redTarget1.png"));
		}
		
		if(e.getSource()==ptarjet111){
			ptarjet111.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet112){
			ptarjet112.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet113){
			ptarjet113.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet114){
			ptarjet114.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet115){
			ptarjet115.setIcon(new ImageIcon("redTarget1.png"));
		}
		
		if(e.getSource()==aitarjet111){
			aitarjet111.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet112){
			aitarjet112.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet113){
			aitarjet113.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet114){
			aitarjet114.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet115){
			aitarjet115.setIcon(new ImageIcon("redTarget1.png"));
		}
		if (e.getSource()==swordp1) {
			swordp1.setIcon(new ImageIcon("sword.png"));
		}
		if (e.getSource()==swordp2) {
			swordp2.setIcon(new ImageIcon("sword.png"));
		}
		if (e.getSource()==swordp3) {
			swordp3.setIcon(new ImageIcon("sword.png"));
		}
		if (e.getSource()==swordp4) {
			swordp4.setIcon(new ImageIcon("sword.png"));
		}
		if (e.getSource()==swordp5) {
			swordp5.setIcon(new ImageIcon("sword.png"));
		}
		repaint();
	}

	public void mouseEntered(MouseEvent e) 
	{
		if(e.getSource()== Barriers.barriers[0])
		{
			Barriers.barriers[0].setBackground(Color.red);
			repaint();
		}
		if(e.getSource()== Barriers.barriers[1])
		{
			Barriers.barriers[1].setBackground(Color.red);
			repaint();
		}
		if(e.getSource()== Barriers.barriers[2])
		{
			Barriers.barriers[2].setBackground(Color.red);
			repaint();
		}
		if(e.getSource()== Barriers.barriers[3])
		{
			Barriers.barriers[3].setBackground(Color.red);
			repaint();
		}
		if(e.getSource()== Barriers.barriers[4])
		{
			Barriers.barriers[4].setBackground(Color.red);
			repaint();
		}
		
		if(e.getSource()==Hero)
		{
			unleash.setBounds(Hero.getX()+220,Hero.getY()+350,100,150);
			add(unleash);
			moveToFront(unleash);
			repaint();
		}
		int x=-1;
		int x2;
		if(player.hand.current<5)
			x2=100;
		else
			x2=400/(player.hand.current-1);

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
				player.hand.handgui[x].setBounds(x*x2,0,100,145);
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

		if (e.getSource()==player.pdeck.btnNewButton_1)
		{
			player.pdeck.btnNewButton_1.setIcon(new ImageIcon("fallen3.png"));
		}

		if (e.getSource()==player.pdeck.btnNewButton_2)
		{
			player.pdeck.btnNewButton_2.setIcon(new ImageIcon("forgotten3.png"));
		}
		
		if(e.getSource()==phases.setup){
			phases.setup.setIcon(new ImageIcon(("setup3.png")));
		}
		if(e.getSource()==phases.action){
			phases.action.setIcon(new ImageIcon(("action3.png")));
		}
		if(e.getSource()==phases.draw){
			phases.draw.setIcon(new ImageIcon(("draw33.png")));
		}
		if(e.getSource()==phases.attack){
			phases.attack.setIcon(new ImageIcon(("attack3.png")));
		}
		if(e.getSource()==phases.end){
			phases.end.setIcon(new ImageIcon(("end3.png")));
			if (liberarTutoEnd==0) {
				liberarTutoEnd=1;
			}
		}
		
		if(e.getSource()==ptarjet1){
			ptarjet1.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet2){
			ptarjet2.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet3){
			ptarjet3.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet4){
			ptarjet4.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet5){
			ptarjet5.setIcon(new ImageIcon("redTarget3.png"));
		}
		
		if(e.getSource()==aitarjet1){
			aitarjet1.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet2){
			aitarjet2.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet3){
			aitarjet3.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet4){
			aitarjet4.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet5){
			aitarjet5.setIcon(new ImageIcon("redTarget3.png"));
		}
		
		if(e.getSource()==ptarjet81){
			ptarjet81.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet82){
			ptarjet82.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet83){
			ptarjet83.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet84){
			ptarjet84.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet85){
			ptarjet85.setIcon(new ImageIcon("redTarget3.png"));
		}
		
		if(e.getSource()==aitarjet81){
			aitarjet81.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet82){
			aitarjet82.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet83){
			aitarjet83.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet84){
			aitarjet84.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet85){
			aitarjet85.setIcon(new ImageIcon("redTarget3.png"));
		}

		if(e.getSource()==ptarjet91){
			ptarjet91.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet92){
			ptarjet92.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet93){
			ptarjet93.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet94){
			ptarjet94.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet95){
			ptarjet95.setIcon(new ImageIcon("redTarget3.png"));
		}
		
		if(e.getSource()==aitarjet91){
			aitarjet91.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet92){
			aitarjet92.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet93){
			aitarjet93.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet94){
			aitarjet94.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet95){
			aitarjet95.setIcon(new ImageIcon("redTarget3.png"));
		}

		if(e.getSource()==ptarjet101){
			ptarjet101.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet102){
			ptarjet102.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet103){
			ptarjet103.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet104){
			ptarjet104.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet105){
			ptarjet105.setIcon(new ImageIcon("redTarget3.png"));
		}
		
		if(e.getSource()==aitarjet101){
			aitarjet101.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet102){
			aitarjet102.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet103){
			aitarjet103.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet104){
			aitarjet104.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet105){
			aitarjet105.setIcon(new ImageIcon("redTarget3.png"));
		}
		
		if(e.getSource()==ptarjet111){
			ptarjet111.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet112){
			ptarjet112.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet113){
			ptarjet113.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet114){
			ptarjet114.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==ptarjet115){
			ptarjet115.setIcon(new ImageIcon("redTarget3.png"));
		}
		
		if(e.getSource()==aitarjet111){
			aitarjet111.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet112){
			aitarjet112.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet113){
			aitarjet113.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet114){
			aitarjet114.setIcon(new ImageIcon("redTarget3.png"));
		}
		if(e.getSource()==aitarjet115){
			aitarjet115.setIcon(new ImageIcon("redTarget3.png"));
		}
		if (e.getSource()==swordp1) {
			swordp1.setIcon(new ImageIcon("swordEnfoqued.png"));
		}
		if (e.getSource()==swordp2) {
			swordp2.setIcon(new ImageIcon("swordEnfoqued.png"));
		}
		if (e.getSource()==swordp3) {
			swordp3.setIcon(new ImageIcon("swordEnfoqued.png"));
		}
		if (e.getSource()==swordp4) {
			swordp4.setIcon(new ImageIcon("swordEnfoqued.png"));
		}
		if (e.getSource()==swordp5) {
			swordp5.setIcon(new ImageIcon("swordEnfoqued.png"));
		}
		repaint();
	}

	void set(final int pos,final int where)
	{

		final SmallCard carta  ;

		if(pos!=-2 && pos!=-3){
			X=player.hand.handgui[pos].getX();
			Y=player.hand.handgui[pos].getY();
			if (player.hand.handgui[pos].getcard().GetType() == "Warrior") {
				warriorPlayed = 1;
			}
		}
		try {
			if(pos>=0){
				carta = new SmallCard(false,player.hand.handgui[pos].getcard());
				moving=new SmallCard(false,player.hand.handgui[pos].getcard());
				
				player.hand.discard(pos+1);
				animations.add(moving);
				moveToFront(moving);
				Thread t = new Thread(new Runnable() {
					public void run() {
						moving.setBounds(650+20,X,0,0);
						int i=0,j=0;
						while (i<=100 || j<=145) {
							try {
								if(i<=100){
									i++;
									moving.setBounds(200+X,Y+600,i,j);
									Thread.sleep(3);
								}
								if(j<=145){
									j++;
									moving.setBounds(180+X,Y+550,i,j);
							
									Thread.sleep(3);
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						int x=0,y=350;
						i=180+X;
						j=Y+550;
						switch(where)
						{
							case 0: x= 220;
								break;
							case 1: x=330;
								break;
							case 2: x=440;
								break;
							case 3: x=550;
								break;
							case 4 : x=440;
								break;
						}
						while (i!=x || j!=y) {
							try {
								if(i<x){
									i++;
									moving.setBounds(i,j,100,145);
									Thread.sleep(3);
								}
								if(i>x){
									i--;
									moving.setBounds(i,j,100,145);
									Thread.sleep(3);
								}
								
								if(j<y){
									j++;
									moving.setBounds(i,j,100,145);
							
									Thread.sleep(3);
								}
								if(j>y){
									j--;
									moving.setBounds(i,j,100,145);
								
									Thread.sleep(3);
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						repaint();
						animations.remove(moving);
						player.powers.play(carta.getcard().GetCost());
						player.field.poner(carta, where);
						carta.repaint();
					}
				});
				t.start();
				carta.addMouseListener(this);
				carta.repaint();
				System.out.println(""+ carta.getcard().GetCardNumber() + " "+ carta.getcard().GetName());
				/***************************EFECTOS*******************************/
				if(carta.getcard().GetCardNumber()==15)
				{
					player.powers.token();
					player.powers.token();
					player.powers.token();
					player.powers.token();
				}
				if(carta.getcard().GetCardNumber()==16)
				{
					fallen.setVisible(true);
					fallen.effectnumber=13;
					fallen.button.setEnabled(true);
				}
				if(carta.getcard().GetCardNumber()==17)
				{
					fallen.setVisible(true);
					fallen.button.setEnabled(true);
					fallen.effectnumber=14;
				}
				this.makeEffect(carta.actual.Getid(),where);
				ubicacionDeCarta = where;
				repaint();
				if (!Objects.equals(carta.actual.GetType(), "Warrior")) {
					Thread t1 = new Thread(new Runnable() {

						public void run() {
							try {
								Thread.sleep(750);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							player.field.quitar(ubicacionDeCarta);
							if(carta.getcard().GetCardNumber()==18)
							{
								player.powers.setwp();
							}
							repaint();
						}
					});
					t1.start();
				}
			}
			if(pos==-2){
				donde=w;
				Hero = new SmallCard(false,player.pdeck.Hero.getcard());
				Hero.shadowColor=Color.black.darker();
				Hero.addMouseListener(this);
				player.pdeck.panel.remove(player.pdeck.Hero);
				player.pdeck.panel.remove(player.pdeck.menu);
				player.powers.play(player.pdeck.Hero.getcard().GetCost());
				RoundedPanel show=new RoundedPanel();
				show.setBounds(0,0,100,145);
				player.pdeck.panel.add(show);
				player.field.poner(Hero, where);
			}
			if(pos==-3)
			{
				Hero=new SmallCard(false,player.pdeck.Deck.lista.Data.Consultar(9));
				player.field.quitar(donde);
				player.powers.play(Hero.getcard().GetCost());
				player.field.poner(Hero, donde);
			}
			repaint();
			HandGui.music();
			this.repairListeners(true);
			repaint();

			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					preview.Remove();
				}
			});
			t.start();
			repaint();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		removeNoWarriorsToPlayerFiedl();
	}

	void hilo() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				checking = true;
				while (checking) {
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
	}
	void play(int pos){// plays a card on field
		boolean allowed = true;
		if(pos>=0){
			c=player.hand.handgui[pos].getcard().GetCost();
			allowed= warriorPlayed == 0 ||(player.hand.handgui[pos].getcard().GetType()!="Warrior" && warriorPlayed ==1 );
		}else if (pos==-3)
			c=player.pdeck.Deck.lista.Data.Consultar(9).GetCost();
		else
			c=player.pdeck.Hero.getcard().GetCost();

		if ( player.powers.power+player.powers.Volatile - c >=0 ) {//verifica que haya mana
			if (allowed) {//verifica que un warrior no se ha jugado en ese turno
				int where = player.field.findwhere();
				if (where != -1)
				{
					p=pos;
					w=where;
					if(c==0)
					{
						set(p, w);
					}else{
						hilo();
					}
				}
			}else
			{
				System.out.println("Sorry , u can only play a warrior on each turn");
			}
		} else {

			System.out.println("Sorry , u dont have enough powers to play it");
		}
	}

	public void Aiturn() throws IOException//aqui se programara a lo salvaje el turno del ai
	{
		if (ai.aideck.Deck.cardsLeft()==0) {
			instanciaGui.doWin();
			repaint();
		}

		ai.aidra.token();
		ai.aidra.reset();

		phases.change(phases.actual+1);
		ai.aideck.textField.setText("cards left "+ai.aideck.Deck.cardsLeft());
		ai.aideck.textField.repaint();

		phases.change(phases.actual+1);
		setVisible(true);
	
		if(ExistCardsInAiField()){
			Random al = new Random();
			int aleatorio = al.nextInt(2); //retornara 0 o 1
			if (aleatorio==0) {
				possiblesAiMovements();
			}
		}
		else {
			possiblesAiMovements();
		}
		
		phases.change(phases.actual+1);
		//attack phase 

		if(this.contTurn>0){
			for(int i=0; i<5; i++){
				this.aiAttack[i]=-1;
				this.aiDest[i]=-1;
			}
			contTargetAttack=0;
			for(int i=0;i<5;i++){
				if(ai.aifield.cards[i]!=null && ai.aifield.cards[i].getcard().GetType()=="Warrior"){
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
			
			Random rr = new Random();
			if (contTargetAttack>0) {

				for (int iterador = 0; iterador < rr.nextInt(contTargetAttack+1); iterador++) {
					atkOrigin=-1;
					atkDest=-1;
					int band=0;
					i=0;
					while(band==0 && i<26){
						Random r = new Random();
						int a = r.nextInt(5);
						if(this.aiAttack[a]==1){
							this.atkOrigin=a;
							band=1;
							break;
						}
						i++;
					}
					
					for(int i=0;i<5;i++){
						if(player.field.cards[i]!=null){
							this.aiDest[i]=1;
						}
						else{
							this.aiDest[i]=0;
						}
					}	
					
					band=0;
					i=0;//si no haces esto cuando no hay cartas queda un ciclo infinito
					while(band==0 && i<26){
						Random r = new Random();
						int a = r.nextInt(5);
						if(this.aiDest[a]==1){
							this.atkDest=a;
							band=1;
							break;
						}
						i++;
					}
					JOptionPane.showMessageDialog(null, "Card "+this.atkOrigin+" attack to player Card "+this.atkDest);

					if (this.atkOrigin==0) {
						this.sworda1.setVisible(false);
					}
					if (this.atkOrigin==1) {
						this.sworda2.setVisible(false);
					}
					if (this.atkOrigin==2) {
						this.sworda3.setVisible(false);
					}
					if (this.atkOrigin==3) {
						this.sworda4.setVisible(false);
					}
					if (this.atkOrigin==4) {
						this.sworda5.setVisible(false);
					}
					this.aiAttack[atkOrigin]=0;
					
					if(atkOrigin!=-1 && atkDest==-1 && countCardsInPlayerField()==0){
						int location = findPlayerBarrierToRemove();
					
						if(location!=-1)//existe un barrier
						{
							player.hand.draw(Barriers.cards[location]);
							player.barriers.removebarrier(location);
							Barriers.barriers[location].setVisible(false);
						}
						else {
							instanciaGui.doGameOver();
							repaint();
						}
					}
					
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
								fallenAi.populate((SimpleColorTableModel) fallenAi.leftTable.getModel(), player.field.cards[atkOrigin].getcard());
								
							}else if(player.field.cards[atkDest].getcard().GetHp()<ai.aifield.cards[atkOrigin].getcard().GetHp())
							{
								player.field.quitar(atkDest);
								fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(), player.field.cards[atkDest].getcard());
								this.makeAiEffect(this.ai.aifield.cards[atkOrigin].getcard().Getid(),atkOrigin );
							}
							add(phases);
						}
					}
					repaint();
				}
			}
		}

		phases.change(phases.actual+1);

		this.sworda1.setVisible(false);
		this.sworda2.setVisible(false);
		this.sworda3.setVisible(false);
		this.sworda4.setVisible(false);
		this.sworda5.setVisible(false);
		turn=1;

		this.turnoLabel.setText("Player'S turn");
		this.contTurn++;

		//primer turno del user
		ready=1;
		done=1;
		if(phases.actual<4){
			phases.change(phases.actual+1);
		}else{
			if(ready==1){
				phases.change(-1);
			}
		}
		barierpicked=0;
		warriorPlayed=0;
		cardDrawn=0;

		for(int i=0;i<5;i++)
			player.hand.handgui[i].Play.setEnabled(false);

		System.out.println("you get 1 volatile power, use it wisely");
		player.powers.token();
		player.powers.reset();
		repaint();
		
		this.phases.setup.removeMouseListener(this);
		this.phases.draw.removeMouseListener(this);
		this.phases.draw.addMouseListener(this);
		
		if(bugPrimerTurnoUSer==0){
			bugPrimerTurnoUSer=1;
			//phases.change(phases.actual+1);
			//this.instanciaGui.accionarAgarreAutomatico.doClick();
			repaint();
		}
		this.phases.end.addMouseListener(this);
		if(phases.actual==-1){
			phases.change(phases.actual+1);
			//	this.instanciaGui.accionarAgarreAutomatico.doClick();
			repaint();
		}
		
		//setup phase
		this.phases.draw.removeMouseListener(this);
		for(int i=0;i<5;i++)
		{
			if(Barriers.barriers[i]!=null){
				Barriers.barriers[i].addMouseListener(this);
			}
		}
		this.phases.action.addMouseListener(this);
		
		Thread t = new Thread(new Runnable(){
			public void run(){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				phases.change(phases.actual+1);
				repaint();
			}
		});
		t.start();
	}

	public void makeEffect(String id, int pos){
		if(this.phases.actual==2){
			if(id.equals("SSD-06")){
				System.out.println("you get 2 volatile power, use it wisely");
				player.powers.token();
				player.powers.token();
				repaint();
			}
			if(id.equals("SSD-05")){
				System.out.println("power undrained");
				player.powers.set(1);
				repaint();
			}
			if(id.equals("SSD-04")){
				System.out.println("please, select your hand card");

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
				System.out.println("please select a card from your field");
				if(player.field.cards[0]!=null && pos!=0){
					this.ptarjet1.setVisible(true);
				}
				if(player.field.cards[1]!=null && pos!=1){
					this.ptarjet2.setVisible(true);				
				}
				if(player.field.cards[2]!=null && pos!=2){
					this.ptarjet3.setVisible(true);
				}
				if(player.field.cards[3]!=null && pos!=3){
					this.ptarjet4.setVisible(true);
				}
				if(player.field.cards[4]!=null && pos!=4){
					this.ptarjet5.setVisible(true);
				}
			}

			if(id.equals("SSD-08")){
				System.out.println("please select a card from the field");
				if(player.field.cards[0]!=null && pos!=0){
					this.ptarjet81.setVisible(true);
				}
				if(player.field.cards[1]!=null && pos!=1){
					this.ptarjet82.setVisible(true);				
				}
				if(player.field.cards[2]!=null && pos!=2){
					this.ptarjet83.setVisible(true);
				}
				if(player.field.cards[3]!=null && pos!=3){
					this.ptarjet84.setVisible(true);
				}
				if(player.field.cards[4]!=null && pos!=4){
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
				System.out.println("please select a Siren Character");
				this.selected=-1;
				if(player.field.cards[0]!=null&& player.field.cards[0].getcard().Getid().equals("SSD-03")){
					this.ptarjet91.setVisible(true);
					this.selected=0;
				}
				if(player.field.cards[1]!=null&& player.field.cards[1].getcard().Getid().equals("SSD-03")){
					this.ptarjet92.setVisible(true);
					this.selected=1;
				}
				if(player.field.cards[2]!=null&& player.field.cards[2].getcard().Getid().equals("SSD-03")){
					this.ptarjet93.setVisible(true);
					this.selected=2;
				}
				if(player.field.cards[3]!=null&& player.field.cards[3].getcard().Getid().equals("SSD-03")){
					this.ptarjet94.setVisible(true);
					this.selected=3;
				}
				if(player.field.cards[4]!=null&& player.field.cards[4].getcard().Getid().equals("SSD-03")){
					this.ptarjet95.setVisible(true);
					this.selected=4;
				}
				if(this.selected==-1){
					System.out.println("sorry, you don't contain a Siren Character in the field");
				}
			}

			if(id.equals("SSD-10")){
				System.out.println("select an ai card to destroy");
				if(player.field.cards[0]!=null && pos!=0){
					this.ptarjet101.setVisible(true);
				}
				if(player.field.cards[1]!=null && pos!=1){//
					this.ptarjet102.setVisible(true);				
				}
				if(player.field.cards[2]!=null && pos!=2){
					this.ptarjet103.setVisible(true);
				}
				if(player.field.cards[3]!=null && pos!=3){
					this.ptarjet104.setVisible(true);
				}
				if(player.field.cards[4]!=null && pos!=4){
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
				System.out.println("select 1 Character to Turn face-down");
				if(player.field.cards[0]!=null && pos!=0){
					if(!player.field.cards[0].down){
						this.ptarjet111.setVisible(true);
					}	
				}
				if(player.field.cards[1]!=null && pos!=1){
					if(!player.field.cards[1].down){
						this.ptarjet112.setVisible(true);
					}				
				}
				if(player.field.cards[2]!=null && pos!=2){
					if(!player.field.cards[2].down){
						this.ptarjet113.setVisible(true);
					}
				}
				if(player.field.cards[3]!=null && pos!=3){
					if(!player.field.cards[3].down){
						this.ptarjet114.setVisible(true);
					}
				}
				if(player.field.cards[4]!=null && pos!=4){
					if(!player.field.cards[4].down){
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
				System.out.println("adding a water power from the deck");

				p= player.pdeck.Deck.posCard("SSD-15");
				if(p==-1){
					System.out.println("cannot find a water power");
				}else{
					int poss= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
					player.hand.handgui[poss-1].addMouseListener(this);
					Addlisteners2Card(poss-1);
					player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
					player.pdeck.textField.repaint();
				}
			}
			repaint();
		}

	}

	public void makeAiEffect(String id, int pos){
		if(this.phases.actual==2){

			if(id.equals("SSD-2")){
				int p;
				System.out.println("adding a water power from the deck");
				p=this.ai.aideck.Deck.posCard("SSD-15");
				if(p==-1){
					System.out.println("cannot find a water power");
				}else{
					this.ai.aideck.textField.setText("cards left "+this.ai.aideck.Deck.cardsLeft());
					this.ai.aideck.textField.repaint();
				}
			}

			if(id.equals("SSD-4")){
				int p;
				System.out.println("AI cards' player will be placed on top of the deck");
				Card c;
				Random randomGenerator = new Random();
				int test = randomGenerator.nextInt(this.ai.aihand.countcards());
				c=ai.aihand.cards[test];
				ai.aideck.Deck.insertar(c);
				ai.aihand.discard(test);

				System.out.println("adding a water power from the deck");
				p=this.ai.aideck.Deck.posCard("SSD-15");
				if(p==-1){
					System.out.println("cannot find a water power");
				}else{
					this.ai.aideck.textField.setText("cards left "+this.ai.aideck.Deck.cardsLeft());
					this.ai.aideck.textField.repaint();
				}
			}

			if(id.equals("SSD-05")){
				ai.aidra.set(1);
			}

			if(id.equals("SSD-06")){
				ai.aidra.set(1);
				ai.aidra.set(1);
			}

			if(id.equals("SSD-07")){
				for(int i=0; i<5; i++){
					this.aiAttack[i]=-1;
					this.aiDest[i]=-1;
				}
				contTargetAttack=0;
				for(int i=0;i<5;i++){
					if(ai.aifield.cards[i]!=null&&pos!=i){
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
				while(band==0 && i<26){
					Random r = new Random();
					int a = r.nextInt(5);
					if(this.aiAttack[a]==1){
						this.atkOrigin=a;
						band=1;
						break;
					}
					i++;
				}

				for(int i=0;i<5;i++){
					if(player.field.cards[i]!=null){
						this.aiDest[i]=1;
					}
					else{
						this.aiDest[i]=0;
					}
				}	

				band=0;
				i=0;//si no haces esto cuando no hay cartas queda un ciclo infinito
				while(band==0 && i<26){
					Random r = new Random();
					int a = r.nextInt(5);
					if(this.aiDest[a]==1){
						this.atkDest=a;
						band=1;
						break;
					}
					i++;
				}

				//dest
				if (atkDest!=-1 && atkOrigin!=-1) {
					int poss= player.hand.draw(player.field.cards[this.atkDest].getcard());
					player.field.quitar(this.atkDest);

					//origin
					pos= this.ai.aihand.draw(this.ai.aifield.cards[this.atkOrigin].getcard());
					this.ai.aifield.quitar(this.atkOrigin); 
				}
				else{
					System.out.println("cannot find targets");
				}
			}

			if(id.equals("SSD-08")){
				for(int i=0; i<5; i++){
					this.aiAttack[i]=-1;
					this.aiDest[i]=-1;
				}
				contTargetAttack=0;
				for(int i=0;i<5;i++){
					if(ai.aifield.cards[i]!=null&&pos!=i){
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
				while(band==0 && i<26){
					Random r = new Random();
					int a = r.nextInt(5);
					if(this.aiAttack[a]==1){
						this.atkOrigin=a;
						band=1;
						break;
					}
					i++;
				}

				for(int i=0;i<5;i++){
					if(player.field.cards[i]!=null&&pos!=i){
						this.aiDest[i]=1;
					}
					else{
						this.aiDest[i]=0;
					}
				}	

				band=0;
				i=0;//si no haces esto cuando no hay cartas queda un ciclo infinito
				while(band==0 && i<26){
					Random r = new Random();
					int a = r.nextInt(5);
					if(this.aiDest[a]==1){
						this.atkDest=a;
						band=1;
						break;
					}
					i++;
				}
				if (this.atkDest!=-1 || this.atkOrigin!=-1) {
					if(this.atkDest!=-1){
						//dest
						int poss= player.hand.draw(player.field.cards[this.atkDest].getcard());
						player.hand.handgui[this.atkDest].addMouseListener(this);
						//Addlisteners2Card(pos-1);
						player.field.quitar(this.atkDest);
					}else{
						//origin
						pos= this.ai.aihand.draw(this.ai.aifield.cards[this.atkOrigin].getcard());
						this.ai.aifield.quitar(this.atkOrigin);
					}
				}else {
					System.out.println("cannot find targets");
				}
			}

			if(id.equals("SSD-09")){
				this.selected=-1;
				if(this.ai.aifield.cards[0]!=null&&pos!=0&&this.ai.aifield.cards[0].getcard().Getid().equals("SSD-03")){
					this.selected=0;
				}
				if(this.ai.aifield.cards[1]!=null&&pos!=1&&this.ai.aifield.cards[1].getcard().Getid().equals("SSD-03")){
					this.selected=1;
				}
				if(this.ai.aifield.cards[2]!=null&&pos!=2&&this.ai.aifield.cards[2].getcard().Getid().equals("SSD-03")){
					this.selected=2;
				}
				if(this.ai.aifield.cards[3]!=null&&pos!=3&&this.ai.aifield.cards[3].getcard().Getid().equals("SSD-03")){
					this.selected=3;
				}
				if(this.ai.aifield.cards[4]!=null&&pos!=4&&this.ai.aifield.cards[4].getcard().Getid().equals("SSD-03")){
					this.selected=4;
				}
				if(this.selected==-1){
					System.out.println("cannot find a Siren");
				}else{
					this.ai.aifield.quitar(this.selected);

					for(int i=0; i<5; i++){
						this.aiDest[i]=-1;
					}

					atkDest=-1;
					for(int i=0;i<5;i++){
						if(player.field.cards[i]!=null){
							this.aiDest[i]=1;
						}
						else{
							this.aiDest[i]=0;
						}
					}	

					int band=0;
					i=0;//si no haces esto cuando no hay cartas queda un ciclo infinito
					while(band==0 && i<26){
						Random r = new Random();
						int a = r.nextInt(5);
						if(this.aiDest[a]==1){
							this.atkDest=a;
							band=1;
							break;
						}
						i++;
					}

					if(this.atkDest!=-1){
						//dest
						player.field.quitar(this.atkDest);
					}else{
						System.out.println("can't find a target");
					}
				}

				if(id.equals("SSD-10")){
					for(int i=0; i<5; i++){
						this.aiAttack[i]=-1;
						this.aiDest[i]=-1;
					}
					contTargetAttack=0;
					for(int i=0;i<5;i++){
						if(ai.aifield.cards[i]!=null&&pos!=i){
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
					while(band==0 && i<26){
						Random r = new Random();
						int a = r.nextInt(5);
						if(this.aiAttack[a]==1){
							this.atkOrigin=a;
							band=1;
							break;
						}
						i++;
					}

					for(int i=0;i<5;i++){
						if(player.field.cards[i]!=null){
							this.aiDest[i]=1;
						}
						else{
							this.aiDest[i]=0;
						}
					}	

					band=0;
					i=0;//si no haces esto cuando no hay cartas queda un ciclo infinito
					while(band==0 && i<26){
						Random r = new Random();
						int a = r.nextInt(5);
						if(this.aiDest[a]==1){
							this.atkDest=a;
							band=1;
							break;
						}
						i++;
					}
					if (this.atkDest!=-1 || this.atkOrigin!=-1) {
						if(this.atkDest!=-1){
							player.field.quitar(this.atkDest);
						}
						else{
							this.ai.aifield.quitar(this.atkOrigin);
						}
					}else {
						System.out.println("can't find a target");
					}
				}
			}

			if(id.equals("SSD-11")){
				for(int i=0; i<5; i++){
					this.aiAttack[i]=-1;
					this.aiDest[i]=-1;
				}
				contTargetAttack=0;
				for(int i=0;i<5;i++){
					if(ai.aifield.cards[i]!=null&&pos!=i){
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
				while(band==0 && i<26){
					Random r = new Random();
					int a = r.nextInt(5);
					if(this.aiAttack[a]==1){
						this.atkOrigin=a;
						band=1;
						break;
					}
					i++;
				}

				for(int i=0;i<5;i++){
					if(player.field.cards[i]!=null){
						this.aiDest[i]=1;
					}
					else{
						this.aiDest[i]=0;
					}
				}	

				band=0;
				i=0;//si no haces esto cuando no hay cartas queda un ciclo infinito
				while(band==0 && i<26){
					Random r = new Random();
					int a = r.nextInt(5);
					if(this.aiDest[a]==1){
						this.atkDest=a;
						band=1;
						break;
					}
					i++;
				}
				Card c;

				if (this.atkDest!=-1 || this.atkOrigin!=-1) {
					if (this.atkDest != -1) {
						c = player.field.cards[this.atkDest].getcard();
						player.field.quitar(this.atkDest);
						try {
							player.field.poner(new SmallCard(true, c), this.atkDest);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						c = this.ai.aifield.cards[this.atkOrigin].getcard();
						this.ai.aifield.quitar(this.atkOrigin);
						try {
							this.ai.aifield.poner(new SmallCard(true, c), this.atkOrigin);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else {
					System.out.println("can't find a target");
				}
			}
			
			if (id.equals("SSD-10")) {
				ai.aidra.set(1);
				ai.aidra.set(1);
				ai.aidra.set(1);
				ai.aidra.set(1);
			}
			repaint();
		}
		if (this.phases.actual == 3) {
			if(id.equals("SSD-02")){
				int p;
				p=this.ai.aideck.Deck.posCard("SSD-15");
				if(p==-1){
					System.out.println("cannot find a water power");
				}else{
					this.ai.aideck.textField.setText("cards left "+this.ai.aideck.Deck.cardsLeft());
					this.ai.aideck.textField.repaint();
				}
			}
			repaint();
		}
	}

	public int contarBarriers() {
		int cont = 0;
		for (int i = 0; i < 5; i++) {
			if (Barriers.cards[i] != null) {
				cont++;
			}
		}
		return cont;
	}

	public void repairListeners(boolean enabled)//enabled dice si el boton playesta on o no
	{
		for(int i=0;i<player.hand.current;i++)
		{
			player.hand.handgui[i].removeMouseListener(this);
		}

		for(int i=0;i<player.hand.current;i++){
			Addlisteners2Card(i);
			player.hand.handgui[i].Play.setEnabled(enabled);
			player.hand.handgui[i].addMouseListener(this);	
		}
		
		player.pdeck.Play.setEnabled(enabled);
		if(player.hand.handgui[player.hand.current]!=null)
		{
			Addlisteners2Card(player.hand.current);
			player.hand.handgui[player.hand.current].addMouseListener(this);	
		}
		

	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource()==player.pdeck.btnNewButton_1)
		{
			player.pdeck.btnNewButton_1.setIcon(new ImageIcon("fallen2.png"));
		}
		if (e.getSource()==player.pdeck.btnNewButton_2)
		{
			player.pdeck.btnNewButton_2.setIcon(new ImageIcon("forgotten2.png"));
		}
		if(e.getSource()==phases.setup){
			phases.setup.setIcon(new ImageIcon(("setup2.png")));
		}
		if(e.getSource()==phases.action){
			phases.action.setIcon(new ImageIcon(("action2.png")));
		}
		if(e.getSource()==phases.draw){
			phases.draw.setIcon(new ImageIcon(("draw22.png")));
		}
		if(e.getSource()==phases.attack){
			phases.attack.setIcon(new ImageIcon(("attack2.png")));
		}
		if(e.getSource()==phases.end){
			phases.end.setIcon(new ImageIcon(("endz.png")));
		}
		
		if(e.getSource()==ptarjet1){
			ptarjet1.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet2){
			ptarjet2.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet3){
			ptarjet3.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet4){
			ptarjet4.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet5){
			ptarjet5.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet1){
			aitarjet1.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet2){
			aitarjet2.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet3){
			aitarjet3.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet4){
			aitarjet4.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet5){
			aitarjet5.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet81){
			ptarjet81.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet82){
			ptarjet82.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet83){
			ptarjet83.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet84){
			ptarjet84.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet85){
			ptarjet85.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet81){
			aitarjet81.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet82){
			aitarjet82.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet83){
			aitarjet83.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet84){
			aitarjet84.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet85){
			aitarjet85.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet91){
			ptarjet91.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet92){
			ptarjet92.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet93){
			ptarjet93.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet94){
			ptarjet94.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet95){
			ptarjet95.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet91){
			aitarjet91.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet92){
			aitarjet92.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet93){
			aitarjet93.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet94){
			aitarjet94.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet95){
			aitarjet95.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet101){
			ptarjet101.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet102){
			ptarjet102.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet103){
			ptarjet103.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet104){
			ptarjet104.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet105){
			ptarjet105.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet101){
			aitarjet101.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet102){
			aitarjet102.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet103){
			aitarjet103.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet104){
			aitarjet104.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet105){
			aitarjet105.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet111){
			ptarjet111.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet112){
			ptarjet112.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet113){
			ptarjet113.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet114){
			ptarjet114.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==ptarjet115){
			ptarjet115.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet111){
			aitarjet111.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet112){
			aitarjet112.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet113){
			aitarjet113.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet114){
			aitarjet114.setIcon(new ImageIcon("redTarget2.png"));
		}
		if(e.getSource()==aitarjet115){
			aitarjet115.setIcon(new ImageIcon("redTarget2.png"));
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource()==player.pdeck.btnNewButton_1)
		{
			player.pdeck.btnNewButton_1.setIcon(new ImageIcon("fallen1.png"));
		}
		if (e.getSource()==player.pdeck.btnNewButton_2)
		{
			player.pdeck.btnNewButton_2.setIcon(new ImageIcon("forgotten1.png"));
		}
		if(e.getSource()==phases.setup){
			phases.setup.setIcon(new ImageIcon(("setup.png")));
		}
		if(e.getSource()==phases.action){
			phases.action.setIcon(new ImageIcon(("action.png")));
		}
		if(e.getSource()==phases.draw){
			phases.draw.setIcon(new ImageIcon(("draw.png")));
		}
		if(e.getSource()==phases.attack){
			phases.attack.setIcon(new ImageIcon(("attack.png")));
		}
		if(e.getSource()==phases.end){
			phases.end.setIcon(new ImageIcon(("end.png")));
		}
		if(e.getSource()==ptarjet1){
			ptarjet1.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet2){
			ptarjet2.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet3){
			ptarjet3.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet4){
			ptarjet4.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet5){
			ptarjet5.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet1){
			aitarjet1.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet2){
			aitarjet2.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet3){
			aitarjet3.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet4){
			aitarjet4.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet5){
			aitarjet5.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet81){
			ptarjet81.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet82){
			ptarjet82.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet83){
			ptarjet83.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet84){
			ptarjet84.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet85){
			ptarjet85.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet81){
			aitarjet81.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet82){
			aitarjet82.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet83){
			aitarjet83.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet84){
			aitarjet84.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet85){
			aitarjet85.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet91){
			ptarjet91.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet92){
			ptarjet92.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet93){
			ptarjet93.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet94){
			ptarjet94.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet95){
			ptarjet95.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet91){
			aitarjet91.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet92){
			aitarjet92.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet93){
			aitarjet93.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet94){
			aitarjet94.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet95){
			aitarjet95.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet101){
			ptarjet101.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet102){
			ptarjet102.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet103){
			ptarjet103.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet104){
			ptarjet104.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet105){
			ptarjet105.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet101){
			aitarjet101.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet102){
			aitarjet102.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet103){
			aitarjet103.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet104){
			aitarjet104.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet105){
			aitarjet105.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet111){
			ptarjet111.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet112){
			ptarjet112.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet113){
			ptarjet113.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet114){
			ptarjet114.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==ptarjet115){
			ptarjet115.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet111){
			aitarjet111.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet112){
			aitarjet112.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet113){
			aitarjet113.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet114){
			aitarjet114.setIcon(new ImageIcon("redTarget1.png"));
		}
		if(e.getSource()==aitarjet115){
			aitarjet115.setIcon(new ImageIcon("redTarget1.png"));
		}
	}
	
	public void firstPlayerTurn(){
		barierpicked=0;
		warriorPlayed=0;
		cardDrawn=0;

		for(int i=0;i<5;i++)
			player.hand.handgui[i].Play.setEnabled(false);

		player.pdeck.Play.setEnabled(false);
		player.powers.reset();
	    
		this.phases.setup.removeMouseListener(this);
		this.phases.draw.removeMouseListener(this);
		this.phases.draw.addMouseListener(this);
	}

	public int countCardsInPlayerField(){
		int aux=0;

		for (int i = 0; i < 5; i++) {
			if (player.field.cards[i]!=null) {
				aux++;
			}
		}
		return aux;
	}
	
	public int findPlayerBarrierToRemove(){
		int aux=-1;
		
		for (int i = 0; i < 5; i++) {
			if (Barriers.barriers[i].isVisible()) {
				aux=i;
				break;
			}
		}
		return aux;
	}
	
	public boolean ExistCardsInAiField(){
		boolean band= false;

		for(int i=0;i<5;i++){
			if(ai.aifield.cards[i]!=null){
				band = true;
				break;
			}
		}
		return band;
	}
	
	public boolean ExistWarriorsInHand(){
		boolean band = false;
		
		for (int i = 0; i < getCantAiHandCards(); i++) {
			if (ai.aihand.cards[i]!=null && ai.aihand.cards[i].GetType()=="Warrior") {
				band=true;
				break;
			}
		}
		return band;
	}
	
	public boolean canInvoqueWarriorToHand(){
		boolean band=false;
		
		for (int i = 0; i < getCantAiHandCards(); i++) {
			if (ai.aihand.cards[i].GetType()=="Warrior" && ai.aihand.cards[i].GetCost()<(getCantVolatilePower()+getCantUndrainedPower())) {
				band = true;
				break;
			}
		}
		return band;
	}
	
	public boolean existPowerToPlayAnotherCard(){
		boolean band = false;
		
		for (int i = 0; i < getCantAiHandCards(); i++) {
			if (!Objects.equals(ai.aihand.cards[i].GetType(), "Warrior") && ai.aihand.cards[i].GetCost()<(getCantVolatilePower()+getCantUndrainedPower())) {
				band = true;
				break;
			}
		}
		return band;
	}
	
	public int getCantAiHandCards(){
		return ai.aihand.current;
	}
	
	public int getCantAiFieldCards(){
		return ai.aifield.countcards();
	}
	
	public int getCantVolatilePower(){
		return ai.aidra.currentoken;
	}
	
	public int getCantUndrainedPower(){
		return ai.aidra.currentundrained;
	}
	
	public int getCardAiHandLocation(String type){
		int location = -1;
		
		for (int i = 0; i < getCantAiHandCards(); i++) {
			if (ai.aihand.cards[i].GetCost()<(getCantVolatilePower()+getCantUndrainedPower())){
				if (Objects.equals(type, "Warrior")) {
					if (Objects.equals(ai.aihand.cards[i].GetType(), "Warrior")) {
						location = i;
						break;
					}
				}
				else {
					location = i;
					break;
				}
			}
		}
		return location;
	}
	
	public void randomSetCardToHand(){
		Random al = new Random();
		int aleatorio;
		aleatorio = al.nextInt(getCantAiHandCards()+1);
		
		ai.aidra.set();
		System.out.println(aleatorio);
		if(aleatorio>0)
			fallenAi.populate((SimpleColorTableModel) fallenAi.leftTable.getModel(), ai.aihand.cards[aleatorio-1]);
		ai.aihand.discard(aleatorio);
		repaint();
	}
	
	public int getCardAiFieldLocation(){
		int location = -1;
		
		for (int i = 0; i < 5; i++) {
			if (ai.aifield.cards[i]==null){
				location = i;
				break;
			}
		}
		return location;
	}
	
	public void removeCardToFieldThread(){
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				fallenAi.populate((SimpleColorTableModel) fallenAi.leftTable.getModel(), ai.aifield.cards[ai.whereInvoqued].getcard());
				ai.aifield.quitar(ai.whereInvoqued);
				preview.Remove();
				repaint();
			}
		});
		t1.start();
	}
	
	public void waitToPlayAnotherCard(){
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				try {
					Thread.sleep(2500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				playAiCard("anywhere");
			}
		});
		t1.start();
	}
	
	public void playAiCard(String type){
//		fase 1 : invocacion
		int cardAiHandLocation = getCardAiHandLocation(type); //ubicar la carta a colocar en hand
		int cardAiFieldLocation = getCardAiFieldLocation();  //ubicar la carta en una posicion del fiel disponible
		SmallCard carta = null;
		
		try {
			carta = new Reverse(false,ai.aihand.handgui[cardAiHandLocation].GetCard());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ai.aifield.poner(carta, cardAiFieldLocation);

		if (carta != null && carta.getcard().GetCardNumber() == 15) {
			ai.aidra.token();
			ai.aidra.token();
			ai.aidra.token();
			ai.aidra.token();
		}
		ai.aihand.discard(cardAiHandLocation);
		ai.whereInvoqued=cardAiFieldLocation;
		repaint();
		
//		fase 2: efecto correspondiente
		this.makeAiEffect(ai.aifield.cards[ai.whereInvoqued].getcard().Getid(),ai.whereInvoqued);
//		fase 3: preview
		preview.addCard(new BigCard(ai.aifield.cards[ai.whereInvoqued].getcard(), 0, 0));
//		fase 4: hilo de ejecucion
		if (ai.aifield.cards[ai.whereInvoqued].getcard().GetType()!="Warrior") {
			removeCardToFieldThread();
		}
		repaint();
	}
	
	public void possiblesAiMovements(){
		Random al = new Random();
		int aleatorio;
		ai.whereInvoqued=-1;
//		verificamos los recursos que ai tiene por si acaso
		int cantAiHandCards=0;
		int cantAiFieldCards=0;
		int cantVolatilePower=0;
		int cantUndrainedPower=0;

		cantAiHandCards = getCantAiHandCards();
		cantAiFieldCards = getCantAiFieldCards();
		cantVolatilePower = getCantVolatilePower();
		cantUndrainedPower = getCantUndrainedPower();

		if (cantAiFieldCards<5) {
			if(ExistWarriorsInHand()){
				if(canInvoqueWarriorToHand()){
					playAiCard("Warrior");
				}else {
					if (existPowerToPlayAnotherCard()) {
						aleatorio = al.nextInt(2); 
						if (aleatorio == 0) {
							//JUGAR UNA SOLA CARTA
							playAiCard("anywhere");
							aleatorio = al.nextInt(2);
							if (aleatorio == 0 && cantAiHandCards>1) {
								//SET A CARTA ALEATORIAMENTE DE LA MANO
								randomSetCardToHand();
							}
						}else {
							//JUGAR DOS CARTAS
							if (cantAiHandCards>1) {
								playAiCard("anywhere");
								waitToPlayAnotherCard();
							}
						}
					}
					else {
						//SET A CARD
						randomSetCardToHand();
						aleatorio = al.nextInt(2);
						if (aleatorio == 0){
							if(ExistWarriorsInHand()){
								if(canInvoqueWarriorToHand()){
									//SE JUEGA EL WARRIOR
									playAiCard("Warrior");
								}
							}	
						}
					}
				}
			}else {
				//SET A RANDOM CARD
				aleatorio = al.nextInt(2);
				if (aleatorio==0) {
					randomSetCardToHand();
				}
				else {
					if (existPowerToPlayAnotherCard()) {
						playAiCard("anywhere");
					}
				}
			}
		}
	}
	
	public void removeNoWarriorsToPlayerFiedl(){
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < 5; i++) {
					if(player.field.cards[i]!=null && player.field.cards[i].getcard().GetType()!="Warrior"){
						fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(), player.field.cards[i].getcard());
						player.field.quitar(i);
					}
				}
				preview.Remove();
				repaint();
			}
		});
		t1.start();
	}
}