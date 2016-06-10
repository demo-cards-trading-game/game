package demo;
import demo.Fallen.SimpleColorTableModel;
import extra.RoundedPanel;
import extra.movePanel;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	public static PlayerGui player;
	public AIGui ai;
	public Previewpane preview;
	public int p,w,c;
	public SmallCard Hero;
	public int donde;
	public int number,X,Y;
	public SmallCard moving;
	public movePanel animations;
	public optionpane op;
	public int turn, contTurn=0;
	public int ready=0;
	public int s,pl;
	public int i=0;
	public Fallen fallen ;
	public JInternalFrame pane;
	public Phases phases;
	public Fallen fallenAi;
	public  JButton repaint;
	public JLabel turnoLabel;
	public int warriorPlayed;
	public int cardDrawn, barierpicked;
	public JLabel swordsPlayer[], swordsAi[];
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
	public JLabel ptarjet91, ptarjet92, ptarjet93, ptarjet94, ptarjet95;
	public JLabel aitarjet91, aitarjet92, aitarjet93, aitarjet94, aitarjet95;
	public JLabel ptarjet101, ptarjet102, ptarjet103, ptarjet104, ptarjet105;
	public JLabel aitarjet101, aitarjet102, aitarjet103, aitarjet104, aitarjet105;
	public JLabel ptarjet111, ptarjet112, ptarjet113, ptarjet114, ptarjet115;
	public JLabel aitarjet111, aitarjet112, aitarjet113, aitarjet114, aitarjet115;
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

	public PlayGui(int x , int y, String name, Gui g) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		this.instanciaGui = g;

		setBorder(null);
		setOpaque(false);
		setLayout(null);
		setBounds(0,0, 1024, 768);

		player=new PlayerGui(x,y,name);
		player.hand.addMouseListener(this);

		cardDrawn=0;

		fight=new fightpane();
		moveToFront(fight);
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

		fallenAi=new Fallen();
		add(fallenAi);

		preview= new Previewpane();
		this.add(preview);

		player.barriers.addMouseListener(this);

		for(int i=1;i<=5;i++)
		{
			int pos= player.hand.draw(player.pdeck.Deck.extraerR());
			player.barriers.addbarrier(player.pdeck.Deck.extraerR());

			Addlisteners2Card(pos-1);
			player.hand.handgui[pos-1].addMouseListener(this);
			ai.aideck.textField.setText("cards left "+ ai.aideck.Deck.cardsLeft());
			player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
			player.pdeck.textField.repaint();
		}
			
		fallen=new Fallen();
		add(fallen);

		player.pdeck.btnNewButton_1.addMouseListener(this);

		op=new  optionpane();

		swordsPlayer = new JLabel[5];
		for(int i=0; i<5; i++){
			swordsPlayer[i] = new JLabel(new ImageIcon(ImageIO.read(new File("sword.png"))));
			swordsPlayer[i].setBounds(240+(110*i), 350, 50, 120);
			swordsPlayer[i].setVisible(false);
			swordsPlayer[i].addMouseListener(this);
			moveToFront(swordsPlayer[i]);
			add(swordsPlayer[i]);
		}

		swordsAi = new JLabel[5];
		for(int i=0; i<5; i++){
			swordsAi[i] = new JLabel(new ImageIcon(ImageIO.read(new File("swordR.png"))));
			swordsAi[i].setBounds(0, 0, 540+(220*i), 385);
			swordsAi[i].setVisible(false);
			moveToFront(swordsAi[i]);
			add(swordsAi[i]);
		}

		FileReader turno = new FileReader(new File("turno.txt"));
		BufferedReader br = new BufferedReader(turno);
		this.turn = Integer.parseInt(br.readLine());
		turno.close();

		this.turnoLabel = new JLabel();
		turnoLabel.setFont(new Font("Comic Sans MS", turnoLabel.getFont().getStyle(), 20));
		turnoLabel.setForeground(Color.green);
		this.turnoLabel.setBounds(50, 320, 200, 20);
		this.turnoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.turnoLabel.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 15));
		if(turn==1){
			this.turnoLabel.setText("Player'S turn");
			Thread t1 = new Thread(() -> {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				this.instanciaGui.accionarAgarreAutomatico.doClick();
				repaint();
			});
			t1.start();
		}else{
			this.turnoLabel.setText("AI Player'S turn");
		}
		add(turnoLabel);


		this.dest1 = new JLabel();
		dest1.setIcon(new ImageIcon("redTarget1.png"));
		this.dest1.setBounds(230, 210, 50, 50);
		this.dest1.setVisible(false);
		this.moveToFront(this.dest1);
		add(dest1);

		this.dest2 = new JLabel();
		dest2.setIcon(new ImageIcon("redTarget1.png"));
		this.dest2.setBounds(340, 210, 50, 50);
		this.dest2.setVisible(false);
		this.moveToFront(this.dest2);
		add(dest2);

		this.dest3 = new JLabel();
		dest3.setIcon(new ImageIcon("redTarget1.png"));
		this.dest3.setBounds(450, 210, 50, 50);
		this.dest3.setVisible(false);
		this.moveToFront(this.dest3);
		add(dest3);

		this.dest4 = new JLabel();
		dest4.setIcon(new ImageIcon("redTarget1.png"));
		this.dest4.setBounds(560, 210, 50, 50);
		this.dest4.setVisible(false);
		this.moveToFront(this.dest4);
		add(dest4);

		this.dest5 = new JLabel();
		dest5.setIcon(new ImageIcon("redTarget1.png"));
		this.dest5.setBounds(670, 210, 50, 50);
		this.dest5.setVisible(false);
		this.moveToFront(this.dest5);
		add(dest5);

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
		this.moveToFront(top1);
		this.top1.addActionListener(this);
		add(top1);

		this.top2=new JButton("target");
		this.top2.setBounds(330,580,80,30);
		this.moveToFront(top2);
		this.top2.addActionListener(this);
		add(top2);

		this.top3=new JButton("target");
		this.top3.setBounds(450,580,80,30);
		this.moveToFront(top3);
		this.top3.addActionListener(this);
		add(top3);

		this.top4=new JButton("target");
		this.top4.setBounds(570,580,80,30);
		this.moveToFront(top4);
		this.top4.addActionListener(this);
		add(top4);

		this.top5=new JButton("target");
		this.top5.setBounds(700,580,80,30);
		this.moveToFront(top5);
		this.top5.addActionListener(this);
		add(top5);

		this.top1.setVisible(false);
		this.top2.setVisible(false);
		this.top3.setVisible(false);
		this.top4.setVisible(false);
		this.top5.setVisible(false);

		phases.draw.addMouseListener(this);

		this.ptarjet1= new JLabel();
		this.ptarjet1.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet1.setBounds(230,380, 50, 50);
		add(ptarjet1);

		this.ptarjet2= new JLabel();
		this.ptarjet2.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet2.setBounds(340,380, 50, 50);
		add(ptarjet2);

		this.ptarjet3= new JLabel();
		this.ptarjet3.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet3.setBounds(450,380, 50, 50);
		add(ptarjet3);

		this.ptarjet4= new JLabel();
		this.ptarjet4.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet4.setBounds(560,380, 50, 50);
		add(ptarjet4);

		this.ptarjet5= new JLabel();
		this.ptarjet5.setIcon(new ImageIcon("redTarget1.png"));
		this.ptarjet5.setBounds(670,380, 50, 50);
		add(ptarjet5);

		this.moveToFront(ptarjet1);
		this.moveToFront(ptarjet2);
		this.moveToFront(ptarjet3);
		this.moveToFront(ptarjet4);
		this.moveToFront(ptarjet5);

		this.aitarjet1= new JLabel();
		this.aitarjet1.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet1.setBounds(230, 210, 50, 50);
		add(aitarjet1);

		this.aitarjet2= new JLabel();
		this.aitarjet2.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet2.setBounds(340,210, 50, 50);
		add(aitarjet2);

		this.aitarjet3= new JLabel();
		this.aitarjet3.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet3.setBounds(450,210, 50, 50);
		add(aitarjet3);

		this.aitarjet4= new JLabel();
		this.aitarjet4.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet4.setBounds(560,210, 50, 50);
		add(aitarjet4);

		this.aitarjet5= new JLabel();
		this.aitarjet5.setIcon(new ImageIcon("redTarget1.png"));
		this.aitarjet5.setBounds(670,210, 50, 50);
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
		}
	}

	public void actionPerformed(ActionEvent e) {
		done = 0;

		if (e.getSource() == fallenAi.confirmcardsfromfallen) {
			SmallCard aux = null;
			System.out.println("entro");

			try {
				aux = new SmallCard(fallenAi.cards[0].getcard(),0,0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			final int where = ai.aifield.findwhere();

			if (fallenAi.effectnumber == 13){
				if (aux != null) {
					number = aux.getcard().GetCardNumber();
				}
				ai.aifield.poner(aux, where);

				fallenAi.remove();
				fallenAi.confirmcardsfromfallen.setEnabled(false);
				fallenAi.button.setEnabled(false);
				fallenAi.effectnumber = 0;
				fallenAi.setVisible(false);

				if (aux != null && !Objects.equals(aux.actual.GetType(), "Warrior")) {
					Thread t1 = new Thread(() -> {
						try {
							Thread.sleep(750);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						ai.aifield.quitar(where);
						if (number == 18) {
							try {
								ai.aidra.setwp();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						repaint();
					});
					t1.start();
				}
			}
		}

		if(e.getSource()==fallen.confirmcardsfromfallen){
			SmallCard aux = null;
			System.out.println("entro");
			final int where=player.field.findwhere();
			System.out.println(where);

			try {
				aux = new SmallCard(fallen.cards[0].getcard(),0,0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(fallen.effectnumber==13){
				if (aux != null) {
					number  =aux.getcard().GetCardNumber();
				}
				player.field.poner(aux,where);
				fallen.remove();
				fallen.confirmcardsfromfallen.setEnabled(false);
				fallen.button.setEnabled(false);
				fallen.effectnumber=0;
				fallen.setVisible(false);
				if (aux != null && !Objects.equals(aux.actual.GetType(), "Warrior")) {
					Thread t1 = new Thread(() -> {
						try {
							Thread.sleep(750);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						player.field.quitar(where);
						if (number == 18) {
							player.powers.setwp();
						}
						repaint();

						fallenAi.setVisible(true);
						fallenAi.effectnumber = 13;
						fallenAi.button.setEnabled(true);
					});
					t1.start();
				}
			}
			else{
				try {
					player.hand.draw(fallen.cards[0].getcard() );
					player.hand.draw(fallen.cards[1].getcard() );
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}

				fallen.confirmcardsfromfallen.setEnabled(false);
				if(fallen.cards[0]!=null){
					fallen.remove(fallen.cards[0]);
				}
				if(fallen.cards[1]!=null){
					fallen.remove(fallen.cards[1]);
				}
				if(fallen.cards[2]!=null){
					fallen.remove(fallen.cards[2]);
				}
				fallen.setVisible(false);
				fallen.button.setEnabled(false);
			}
			fallen.selecting=0;
		}

		s = -1;
		for(int i=0; i<5;i++){
			if (e.getSource() == player.hand.handgui[i].Set) {
				if (done == i) {
					s = i;
				}
				done = 1;
			}
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
			try {
				player.hand.discard(s + 1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			if(phases.actual==2){
				this.repairListeners(true);
			}else{
				this.repairListeners(false);
			}
		}

		for(int i=0; i<5;i++){
			if(e.getSource()==player.hand.handgui[i].Discard){
				if(done==0)
					if(i!=0){
						fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.hand.cards[i]);
					}
					try {
						if(i!=4){
							player.hand.discard(i+1);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				if(phases.actual==2){
					this.repairListeners(true);
				}else{
					this.repairListeners(false);
				}
				done=1;
			}
		}

		if(e.getSource()==player.pdeck.Preview){
			if(done==0){
				try {
					preview.addCard(new BigCard(player.pdeck.Hero.getcard(),0,0));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			done=1;
		}

		for(int i=0; i<5; i++){
			if(e.getSource()==player.hand.handgui[i].Preview){
				if(done==0){
					try {
						preview.addCard(new BigCard(player.hand.handgui[i].getcard(),0,0));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				done=1;
			}
		}
		
		pl=-1;
		if(e.getSource()==player.pdeck.Play){
			pl=-2;
		}

		for(int i=0;i<5;i++){
			if(e.getSource()==player.hand.handgui[i].Play){
				pl=i;
			}
		}

		if(player.hand.handgui[5]!=null){
			if(e.getSource()==player.hand.handgui[5].Play){
				pl=5;
			}
		}

		if(player.hand.handgui[6]!=null){
			if(e.getSource()==player.hand.handgui[6].Play){
				pl=6;
			}
		}

		if(pl!=-1){
			try{
				if(pl!=-2){
					if(player.hand.cards[pl].Getid().equals("SSD-10")&&(contarBarriers()>=0)){
						System.out.println("You must have 0 barriers to play this card");
					}
					else{
						playPlayerCard();
					}
				}else{
					playPlayerCard();
				}
			}catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
		}

		if(e.getSource()==j){
			try {
				this.listAll = new prueba2(player.pdeck.Deck);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.listAll.setBounds(150, 100, 620, 420);
			add(this.listAll);
			this.moveToFront(this.listAll);
			this.listAll.aceptar.addActionListener(this);
		}

		if(e.getSource()==this.listAll.aceptar){
			this.listAll.setVisible(false);
			this.listAll.opciones.setVisible(false);
			int pos= 0;
			try {
				pos = player.hand.draw(player.pdeck.Deck.ConsultarYextraer(this.listAll.num));
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
			player.hand.handgui[pos-1].addMouseListener(this);
			Addlisteners2Card(pos-1);
			player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
			player.pdeck.textField.repaint();
			this.listAll.removeAll();
			repaint();
		}

		if(e.getSource()==this.top1){
			this.top1.setVisible(false);
			this.top2.setVisible(false);
			this.top3.setVisible(false);
			this.top4.setVisible(false);
			this.top5.setVisible(false);

			System.out.println("your card will be placed on top of the deck");
			Card c;
			c=player.hand.cards[0];	
			player.pdeck.Deck.insertar(c);
			try {
				player.hand.discard(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.repairListeners(true);

			try {
				addPowerCardFromTheDeck();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
			repaint();
		}

		if(e.getSource()==this.top2){
			this.top1.setVisible(false);
			this.top2.setVisible(false);
			this.top3.setVisible(false);
			this.top4.setVisible(false);
			this.top5.setVisible(false);

			System.out.println("your card will be placed on top of the deck");
			Card c;
			c=player.hand.cards[1];	
			player.pdeck.Deck.insertar(c);
			try {
				player.hand.discard(2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.repairListeners(true);

			try {
				addPowerCardFromTheDeck();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
			repaint();
		}

		if(e.getSource()==this.top3){
			this.top1.setVisible(false);
			this.top2.setVisible(false);
			this.top3.setVisible(false);
			this.top4.setVisible(false);
			this.top5.setVisible(false);

			System.out.println("your card will be placed on top of the deck");
			Card c;
			c=player.hand.cards[2];	
			player.pdeck.Deck.insertar(c);
			try {
				player.hand.discard(3);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.repairListeners(true);

			try {
				addPowerCardFromTheDeck();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
			repaint();
		}

		if(e.getSource()==this.top4){
			this.top1.setVisible(false);
			this.top2.setVisible(false);
			this.top3.setVisible(false);
			this.top4.setVisible(false);
			this.top5.setVisible(false);

			System.out.println("your card will be placed on top of the deck");
			Card c;
			c=player.hand.cards[3];	
			player.pdeck.Deck.insertar(c);
			try {
				player.hand.discard(4);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.repairListeners(true);

			try {
				addPowerCardFromTheDeck();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
			repaint();
		}

		if(e.getSource()==this.top5){
			this.top1.setVisible(false);
			this.top2.setVisible(false);
			this.top3.setVisible(false);
			this.top4.setVisible(false);
			this.top5.setVisible(false);

			System.out.println("your card will be placed on top of the deck");
			Card c;
			c=player.hand.cards[4];	
			player.pdeck.Deck.insertar(c);
			try {
				player.hand.discard(5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			this.repairListeners(true);

			try {
				addPowerCardFromTheDeck();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
			repaint();
		}
	}

	void Addlisteners2Card(int i){
		player.hand.handgui[i].Play.addActionListener(this);
		player.hand.handgui[i].Discard.addActionListener(this);
		player.hand.handgui[i].Preview.addActionListener(this);
		player.hand.handgui[i].Set.addActionListener(this);
	}

	public void mouseClicked(MouseEvent e){
		if(e.getSource()==Hero){
			remove(unleash);
			try {
				play(-3);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
				e1.printStackTrace();
			}
			repaint();
		}

		if(e.getButton() == MouseEvent.BUTTON1){
			if(e.getClickCount()==1){
				if(e.getSource()==ai.aideck.btnNewButton_1){
					fallenAi.setVisible(true);
					moveToFront(fallenAi);
				}

				if(barierpicked==0){
					for(int i=0;i<5;i++){
						if(e.getSource()== Barriers.barriers[i]){
							int pos= 0;
							try {
								pos = player.hand.draw(Barriers.cards[i]);
							} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
								e1.printStackTrace();
							}
							player.hand.handgui[pos-1].addMouseListener(this);
							Addlisteners2Card(pos-1);
							player.barriers.removebarrier(i);
							barierpicked=1;
							repaint();
							repairListeners(false);
						}
					}
				}else{
					if(phases.actual==1){
						System.out.println("sorry u can only get a card from barriers per turn");
					}
				}	
				
				if((e.getSource()==phases.setup)||(e.getSource()==phases.draw)||(e.getSource()==phases.action)||(e.getSource()==phases.attack)||(e.getSource()==phases.end)){
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
							for(int i=0;i<5;i++){
								if(Barriers.barriers[i]!=null){
									Barriers.barriers[i].addMouseListener(this);
								}
							}
							this.phases.action.addMouseListener(this);
							break;
						case 2:
							for(int i=0;i<5;i++){
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
									if(player.field.cards[i]!=null && !player.field.cards[i].down){
										this.swordsPlayer[i].setVisible(true);
									}
								}
							}
							break;
						case 4:
							if(ready==1){
								for (int i=0;i<5;i++)
									Barriers.barriers[i].removeMouseListener(this);
								ready=0;
								this.phases.setup.addMouseListener(this);

								for(int i=0;i<5;i++){
									swordsPlayer[i].setVisible(false);
									swordsAi[i].setVisible(false);
								}

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
							}else{
								ready=1;
							}
							this.phases.end.removeMouseListener(this);
							ai.aideck.btnNewButton.addMouseListener(this);
							phases.change(0);
							try {
								Aiturn();
							} catch (IOException | UnsupportedAudioFileException | InterruptedException | LineUnavailableException e1) {
								e1.printStackTrace();
							}
							break;
					}
					repaint();
					done=0;
				}

				int x=-1;
				for(int i=0;i<8;i++){
					if(e.getSource()==player.hand.handgui[i]){
						x=i;
					}
				}

				if(x!=-1){
					for(int j=0;j<player.hand.current;j++){
						if(x!=j){
							player.hand.handgui[j].menu.setVisible(false);
						}else{
							player.hand.handgui[j].menu.setVisible(true);
						}
					}	
				}

				if(phases.actual==3&&this.contTurn>0){
					for(int i=0;i<5;i++){
						if(e.getSource()==player.field.cards[i]&&!player.field.cards[i].down){
							for(int j=0; j<5; j++){
								swordsPlayer[j].setVisible(false);
							}
							swordsPlayer[i].setVisible(true);
						}
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
					int pos= 0;
					try {
						pos = player.hand.draw(player.field.cards[this.selected-1].getcard());
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
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
					int pos = 0;

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
						try {
							pos= player.hand.draw(player.field.cards[this.selected-1].getcard());
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							e1.printStackTrace();
						}
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
							player.field.poner(new SmallCard(c,0,0), 0);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.ptarjet112){
						c= player.field.cards[1].getcard();
						player.field.quitar(1);

						try {
							player.field.poner(new SmallCard(c,0,0), 1);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.ptarjet113){
						c= player.field.cards[2].getcard();
						player.field.quitar(2);

						try {
							player.field.poner(new SmallCard(c,0,0), 2);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.ptarjet114){
						c= player.field.cards[3].getcard();
						player.field.quitar(3);

						try {
							player.field.poner(new SmallCard(c,0,0), 3);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.ptarjet115){
						c= player.field.cards[4].getcard();
						player.field.quitar(4);

						try {
							player.field.poner(new SmallCard(c,0,0), 4);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.aitarjet111){
						c=this.ai.aifield.cards[0].getcard();
						this.ai.aifield.quitar(0);

						try {
							this.ai.aifield.poner(new SmallCard(c,0,0), 0);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.aitarjet112){
						c=this.ai.aifield.cards[1].getcard();
						this.ai.aifield.quitar(1);

						try {
							this.ai.aifield.poner(new SmallCard(c,0,0), 1);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.aitarjet113){
						c=this.ai.aifield.cards[2].getcard();
						this.ai.aifield.quitar(2);

						try {
							this.ai.aifield.poner(new SmallCard(c,0,0), 2);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.aitarjet114){
						c=this.ai.aifield.cards[3].getcard();
						this.ai.aifield.quitar(3);

						try {
							this.ai.aifield.poner(new SmallCard(c,0,0), 3);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(e.getSource()==this.aitarjet115){
						c=this.ai.aifield.cards[4].getcard();
						this.ai.aifield.quitar(4);

						try {
							this.ai.aifield.poner(new SmallCard(c,0,0), 4);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					repaint();
					System.out.println("face down succesfully");
					repaint();
				}

				for(int i=0;i<5;i++){
					if(e.getSource()==swordsPlayer[i]){
						atkOrigin= i+1;
					}
					if(ai.aifield.countcards()==0){
						int which=ai.barriers.findwhich();
						System.out.println("congratulations , direct hit");
						if(ai.aihand.current==5){
							ai.aihand.discard(5);
						}
						if(which!=-1){
							ai.barriers.removebarrier(which);
							Barriers2.barriers[which].setVisible(false);
							repaint();
						}else{
							instanciaGui.doWin();
							repaint();
						}

						swordsPlayer[i].setVisible(false);
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

					for(int i=0;i<5;i++){
						swordsPlayer[i].setVisible(false);
					}

					System.out.println("Card "+this.atkOrigin+" attack to ai Card "+this.atkDest);
					remove(phases);

					if(!fight.isVisible()){
						try {
							fight.addCards(new BigCard(player.field.cards[atkOrigin-1].getcard(),0,0),new BigCard(ai.aifield.cards[atkDest-1].getcard(),0,0));
						} catch (IOException | InterruptedException e1) {
							e1.printStackTrace();
						}
					}

					if(player.field.cards[atkOrigin-1].getcard().GetHp()>ai.aifield.cards[atkDest-1].getcard().GetHp()){
						ai.aifield.quitar(atkDest-1);
						try {
							this.makeEffect(player.field.cards[atkOrigin-1].getcard().Getid(),atkOrigin-1 );
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							e1.printStackTrace();
						}

					}else if(player.field.cards[atkOrigin-1].getcard().GetHp()<ai.aifield.cards[atkDest-1].getcard().GetHp()){
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

			if (e.getSource()==player.pdeck.btnNewButton_1){
				fallen.setVisible(true);
				moveToFront(fallen);
			}
		}	    
		else if(e.getButton() == MouseEvent.BUTTON3){
			if(e.getClickCount()==1){
				for(int i=0;i<5;i++){
					if(e.getSource()==player.field.cards[i]){
						if(player.field.cards[i].getcard().Getid().equals("SSD-01")){
							System.out.println("This card referred to 4 water power");
							player.powers.set(player.field.cards[i].getcard().GetCost()*4);
						}
						else{
							player.powers.play(player.field.cards[i].getcard().GetCost());
						}
						fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[i].getcard());
						player.field.quitar(i);
					}
				}
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		for(int i=0;i<5;i++){
			if(e.getSource()== Barriers.barriers[i]){
				Barriers.barriers[i].setBackground(new Color(128, 128, 128));
				repaint();
			}
		}
		
		if(e.getSource()==Hero){
			remove(unleash);
			repaint();
		}

		int x;
		int x2;

		if(player.hand.current<5)
			x2=100;
		else
			x2=400/(player.hand.current-1);

		x=selectionHandgui(e);
		preview.Remove();

		if(x!=-1){
			if(x!=player.hand.current-1 || x<5){
				player.hand.handgui[x].setBounds(x*x2+30,20,83,145);
			}else{
				player.hand.handgui[x].setBounds(400,20,83,145);
			}
		}

		for(int i=0;i<5;i++){
			if(e.getSource()==player.field.cards[i]){
				preview.Remove();
			}
		}

		for(int i=0;i<5;i++){
			if(e.getSource()==ai.aifield.cards[i]){
				preview.Remove();
			}
		}

		if (e.getSource()==player.pdeck.btnNewButton_1){
			player.pdeck.btnNewButton_1.setIcon(new ImageIcon("fallen1.png"));
		}

		if (e.getSource()==player.pdeck.btnNewButton_2){
			player.pdeck.btnNewButton_2.setIcon(new ImageIcon("forgotten1.png"));
		}
		
		if(e.getSource()==phases.setup){
			phases.setup.setIcon(new ImageIcon(("setup.png")));
		}
		if(e.getSource()==phases.action){
			phases.action.setIcon(new ImageIcon(("action.png")));
		}
		if(e.getSource()==phases.draw){
			phases.draw.setIcon(new ImageIcon(("draw11.png")));
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

		for(int i=0;i<5;i++){
			if (e.getSource()==swordsPlayer[i]) {
				swordsPlayer[i].setIcon(new ImageIcon("sword.png"));
			}
		}
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
		for(int i=0;i<5;i++){
			if(e.getSource()== Barriers.barriers[i]){
				Barriers.barriers[i].setBackground(Color.red);
				repaint();
			}
		}
		
		if(e.getSource()==Hero){
			unleash.setBounds(Hero.getX()+220,Hero.getY()+350,100,150);
			add(unleash);
			moveToFront(unleash);
			repaint();
		}

		int x;
		int x2;
		if(player.hand.current<5)
			x2=100;
		else
			x2=400/(player.hand.current-1);

		x=selectionHandgui(e);

		if(x!=-1){
			if(x!=player.hand.current){
				player.hand.handgui[x].setBounds(x*x2,0,83,145);
				player.hand.moveToFront(player.hand.handgui[x]);
			}
		}

		for(int i=0;i<5;i++){
			if(e.getSource()==player.field.cards[i]){
				try {
					preview.addCard(new BigCard(player.field.cards[i].getcard(),0,0));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		for(int i=0;i<5;i++){
			if(e.getSource()==ai.aifield.cards[i]){
				try {
					preview.addCard(new BigCard(ai.aifield.cards[i].getcard(),0,0));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		if (e.getSource()==player.pdeck.btnNewButton_1){
			player.pdeck.btnNewButton_1.setIcon(new ImageIcon("fallen3.png"));
		}

		if (e.getSource()==player.pdeck.btnNewButton_2){
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

		for(int i=0;i<5;i++){
			if (e.getSource()==swordsPlayer[i]) {
				swordsPlayer[i].setIcon(new ImageIcon("swordEnfoqued.png"));
			}
		}

		repaint();
	}

	void set(final int pos,final int where) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		final SmallCard carta  ;

		if(pos!=-2 && pos!=-3){
			X=player.hand.handgui[pos].getX();
			Y=player.hand.handgui[pos].getY();
			if (Objects.equals(player.hand.handgui[pos].getcard().GetType(), "Warrior")) {
				warriorPlayed = 1;
			}
		}
		
			if(pos>=0){
				carta = new SmallCard(player.hand.handgui[pos].getcard(),0,0);
				moving=new SmallCard(player.hand.handgui[pos].getcard(),0,0);
				player.hand.discard(pos+1);
				animations.add(moving);
				moveToFront(moving);
				Thread t = new Thread(() -> {
                    moving.setBounds(650+20, X,0,0);
                    int i1 =0, j1 =0;
                    while (i1 <=100 || j1 <=145) {
                        try {
                            if(i1 <=100){
                                i1++;
                                moving.setBounds(200+X,Y+600, i1, j1);
                                Thread.sleep(3);
                            }
                            if(j1 <=145){
                                j1++;
                                moving.setBounds(180+X,Y+550, i1, j1);

                                Thread.sleep(3);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int x1 =0, y1 =350;
                    i1 =180+X;
                    j1 =Y+550;
                    switch(where)
                    {
                        case 0: x1 = 220;
                            break;
                        case 1: x1 =330;
                            break;
                        case 2: x1 =440;
                            break;
                        case 3: x1 =550;
                            break;
                        case 4 : x1 =440;
                            break;
                    }
                    while (i1 != x1 || j1 != y1) {
                        try {
                            if(i1 < x1){
                                i1++;
                                moving.setBounds(i1, j1,75,145);
                                Thread.sleep(3);
                            }
                            if(i1 > x1){
                                i1--;
                                moving.setBounds(i1, j1,75,145);
                                Thread.sleep(3);
                            }

                            if(j1 < y1){
                                j1++;
                                moving.setBounds(i1, j1,75,145);

                                Thread.sleep(3);
                            }
                            if(j1 > y1){
                                j1--;
                                moving.setBounds(i1, j1,75,145);

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
                });
				t.start();
				carta.addMouseListener(this);
				carta.repaint();
				System.out.println(""+ carta.getcard().GetCardNumber() + " "+ carta.getcard().GetName());

				if(carta.getcard().GetCardNumber()==15){
					player.powers.token();
					player.powers.token();
					player.powers.token();
					player.powers.token();
				}

				if(carta.getcard().GetCardNumber()==16){
					fallen.setVisible(true);
					fallen.effectnumber=13;
					fallen.button.setEnabled(true);
				}

				if(carta.getcard().GetCardNumber()==17){
					fallen.setVisible(true);
					fallen.button.setEnabled(true);
					fallen.effectnumber=14;
				}

				this.makeEffect(carta.actual.Getid(),where);
				ubicacionDeCarta = where;
				repaint();

				if (!Objects.equals(carta.actual.GetType(), "Warrior")) {
					Thread t1 = new Thread(() -> {
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
                    });
					t1.start();
				}
			}
			if(pos==-2){
				donde=w;
				Hero = new SmallCard(player.pdeck.Hero.getcard(),0,0);
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

			if(pos==-3){
				Hero=new SmallCard(player.pdeck.Deck.lista.Data.Consultar(9),0,0);
				player.field.quitar(donde);
				player.powers.play(Hero.getcard().GetCost());
				player.field.poner(Hero, donde);
			}

			repaint();
			AiHand.music();
			this.repairListeners(true);
			repaint();

			Thread t = new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                preview.Remove();
            });
			t.start();
			repaint();
		removeNoWarriorsToPlayerFiedl();
	}

	
	void play(int pos) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		boolean allowed = true;
		if(pos>=0){
			c=player.hand.handgui[pos].getcard().GetCost();
			allowed= warriorPlayed == 0 ||(!Objects.equals(player.hand.handgui[pos].getcard().GetType(), "Warrior") && warriorPlayed ==1 );
		}else if (pos==-3)
			c=player.pdeck.Deck.lista.Data.Consultar(9).GetCost();
		else
			c=player.pdeck.Hero.getcard().GetCost();

		if ( player.powers.power+player.powers.Volatile - c >=0 ) {
			if (allowed) {
				int where = player.field.findwhere();
				if (where != -1){
					p=pos;
					w=where;
					set(p, w);
				}
			}else{
				System.out.println("Sorry , u can only play a warrior on each turn");
			}
		} else {
			System.out.println("Sorry , u dont have enough powers to play it");
		}
	}

	public void Aiturn() throws IOException, LineUnavailableException, UnsupportedAudioFileException, InterruptedException//aqui se programara a lo salvaje el turno del ai
	{
		if (ai.aideck.Deck.cardsLeft()==0) {
			instanciaGui.doWin();
			repaint();
		}

		ai.aidra.token();
		ai.aidra.reset();

		phases.change(phases.actual+1);

		ai.aihand.draw(ai.aideck.Deck.extraerR());
		ai.aideck.textField.setText("cards left "+ai.aideck.Deck.cardsLeft());
		ai.aideck.textField.repaint();

		phases.change(phases.actual+1);
		setVisible(true);

		aiMovements();

		phases.reaction.setIcon(new ImageIcon(("reAction.png")));
		repaint();

		aiPhases();
	}

	public void makeEffect(String id, int pos) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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
				addPowerCardFromTheDeck();
			}
			repaint();
		}
	}

	public void makeAiEffect(String id, int pos) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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
				initPossiblesAttacks();
				contPossibleTargetAttaks(pos);
				aiAttackOrigin();

				for(int i=0;i<5;i++){
					if(player.field.cards[i]!=null){
						this.aiDest[i]=1;
					}
					else{
						this.aiDest[i]=0;
					}
				}

				aiAttackDest();

				if (atkDest!=-1 && atkOrigin!=-1) {
					player.hand.draw(player.field.cards[this.atkDest].getcard());
					player.field.quitar(this.atkDest);

					pos= this.ai.aihand.draw(this.ai.aifield.cards[this.atkOrigin].getcard());
					this.ai.aifield.quitar(this.atkOrigin); 
				}
				else{
					System.out.println("cannot find targets");
				}
			}

			if(id.equals("SSD-08")){
				initPossiblesAttacks();
				contTargetAttack=0;
				contPossibleTargetAttaks(pos);
				aiAttackOrigin();

				for(int i=0;i<5;i++){
					if(player.field.cards[i]!=null&&pos!=i){
						this.aiDest[i]=1;
					}
					else{
						this.aiDest[i]=0;
					}
				}

				aiAttackDest();

				if (this.atkDest!=-1 || this.atkOrigin!=-1) {
					if(this.atkDest!=-1){
						player.hand.draw(player.field.cards[this.atkDest].getcard());
						player.hand.handgui[this.atkDest].addMouseListener(this);
						player.field.quitar(this.atkDest);
					}else{
						pos= this.ai.aihand.draw(this.ai.aifield.cards[this.atkOrigin].getcard());
						this.ai.aifield.quitar(this.atkOrigin);
					}
				}else {
					System.out.println("cannot find targets");
				}
			}

			if(id.equals("SSD-09")){
				this.selected=-1;
				for(int i=0;i<5;i++){
					if(this.ai.aifield.cards[i]!=null&&pos!=i&&this.ai.aifield.cards[i].getcard().Getid().equals("SSD-03")){
						this.selected=i;
					}
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

					aiAttackDest();
					if(this.atkDest!=-1){
						player.field.quitar(this.atkDest);
					}else{
						System.out.println("can't find a target");
					}
				}

				if(id.equals("SSD-10")){
					initPossiblesAttacks();
					contPossibleTargetAttaks(pos);
					aiAttackOrigin();

					for(int i=0;i<5;i++){
						if(player.field.cards[i]!=null){
							this.aiDest[i]=1;
						}
						else{
							this.aiDest[i]=0;
						}
					}

					aiAttackDest();
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
				initPossiblesAttacks();
				contTargetAttack=0;
				contPossibleTargetAttaks(pos);
				aiAttackOrigin();

				for(int i=0;i<5;i++){
					if(player.field.cards[i]!=null){
						this.aiDest[i]=1;
					}
					else{
						this.aiDest[i]=0;
					}
				}

				aiAttackDest();
				Card c;

				if (this.atkDest!=-1 || this.atkOrigin!=-1) {
					if (this.atkDest != -1) {
						c = player.field.cards[this.atkDest].getcard();
						player.field.quitar(this.atkDest);
						player.field.poner(new SmallCard( c,0,0), this.atkDest);
						
					} else {
						c = this.ai.aifield.cards[this.atkOrigin].getcard();
						this.ai.aifield.quitar(this.atkOrigin);
						this.ai.aifield.poner(new SmallCard( c,0,0), this.atkOrigin);
					}
				}else {
					System.out.println("can't find a target");
				}
			}

			if (id.equals("SSD-10")) {
				for(int i=0;i<4;i++)
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

	public void repairListeners(boolean enabled){
		for(int i=0;i<player.hand.current-1;i++){
			player.hand.handgui[i].removeMouseListener(this);
		}

		for(int i=0;i<player.hand.current;i++){
			Addlisteners2Card(i);
			player.hand.handgui[i].Play.setEnabled(enabled);
			player.hand.handgui[i].addMouseListener(this);	
		}
		
		player.pdeck.Play.setEnabled(enabled);
		if(player.hand.handgui[player.hand.current]!=null){
			Addlisteners2Card(player.hand.current);
			player.hand.handgui[player.hand.current].addMouseListener(this);	
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource()==player.pdeck.btnNewButton_1){
			player.pdeck.btnNewButton_1.setIcon(new ImageIcon("fallen2.png"));
		}
		if (e.getSource()==player.pdeck.btnNewButton_2){
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
		if (e.getSource()==player.pdeck.btnNewButton_1){
			player.pdeck.btnNewButton_1.setIcon(new ImageIcon("fallen1.png"));
		}
		if (e.getSource()==player.pdeck.btnNewButton_2){
			player.pdeck.btnNewButton_2.setIcon(new ImageIcon("forgotten1.png"));
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
			phases.end.setIcon(new ImageIcon(("end2.png")));
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
			if (ai.aihand.cards[i]!=null && Objects.equals(ai.aihand.cards[i].GetType(), "Warrior")) {
				band=true;
				break;
			}
		}
		return band;
	}
	
	public boolean canInvoqueWarriorToHand(){
		boolean band=false;
		
		for (int i = 0; i < getCantAiHandCards(); i++) {
			if (Objects.equals(ai.aihand.cards[i].GetType(), "Warrior") && ai.aihand.cards[i].GetCost()<(getCantVolatilePower()+getCantUndrainedPower())) {
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
		Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fallenAi.populate((SimpleColorTableModel) fallenAi.leftTable.getModel(), ai.aifield.cards[ai.whereInvoqued].getcard());
            ai.aifield.quitar(ai.whereInvoqued);
            preview.Remove();
            repaint();
        });
		t1.start();
	}
	
	public void waitToPlayAnotherCard(){
		Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			try {
				playAiCard("anywhere");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				e.printStackTrace();
			}
		});
		t1.start();
	}
	
	public void playAiCard(String type) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		int cardAiHandLocation = getCardAiHandLocation(type);
		int cardAiFieldLocation = getCardAiFieldLocation();
		SmallCard carta = null;
		
		try {
			carta = new Reverse(false,ai.aihand.handgui[cardAiHandLocation].GetCard());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ai.aifield.poner(carta, cardAiFieldLocation);

		if (carta != null && carta.getcard().GetCardNumber() == 15) {
			for(int i=0;i<5;i++)
				ai.aidra.token();
		}
		ai.aihand.discard(cardAiHandLocation);
		ai.whereInvoqued=cardAiFieldLocation;
		repaint();
		
		this.makeAiEffect(ai.aifield.cards[ai.whereInvoqued].getcard().Getid(),ai.whereInvoqued);
		preview.addCard(new BigCard(ai.aifield.cards[ai.whereInvoqued].getcard(), 0, 0));
		if (!Objects.equals(ai.aifield.cards[ai.whereInvoqued].getcard().GetType(), "Warrior")) {
			removeCardToFieldThread();
		}
		repaint();
	}
	
	public void possiblesAiMovements() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		Random al = new Random();
		int aleatorio;
		ai.whereInvoqued=-1;
		int cantAiHandCards;
		int cantAiFieldCards;

		cantAiHandCards = getCantAiHandCards();
		cantAiFieldCards = getCantAiFieldCards();

		if (cantAiFieldCards<5) {
			if(ExistWarriorsInHand()){
				if(canInvoqueWarriorToHand()){
					playAiCard("Warrior");
				}else {
					if (existPowerToPlayAnotherCard()) {
						aleatorio = al.nextInt(2); 
						if (aleatorio == 0) {
							playAiCard("anywhere");
							aleatorio = al.nextInt(2);
							if (aleatorio == 0 && cantAiHandCards>1) {
								randomSetCardToHand();
							}
						}else {
							if (cantAiHandCards>1) {
								playAiCard("anywhere");
								waitToPlayAnotherCard();
							}
						}
					}
					else {
						randomSetCardToHand();
						aleatorio = al.nextInt(2);
						if (aleatorio == 0){
							if(ExistWarriorsInHand()){
								if(canInvoqueWarriorToHand()){
									playAiCard("Warrior");
								}
							}	
						}
					}
				}
			}else {
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
		Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i1 = 0; i1 < 5; i1++) {
                if(player.field.cards[i1]!=null && !Objects.equals(player.field.cards[i1].getcard().GetType(), "Warrior")){
                    fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(), player.field.cards[i1].getcard());
                    player.field.quitar(i1);
                }
            }
            preview.Remove();
            repaint();
        });
		t1.start();
	}

	public void addPowerCardFromTheDeck() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		System.out.println("adding a water power from the deck");
		int p = player.pdeck.Deck.posCard("SSD-15");
		if(p ==-1){
			System.out.println("cannot find a water power");
		}else{
			int pos= player.hand.draw(player.pdeck.Deck.ConsultarYextraer(p));
			player.hand.handgui[pos-1].addMouseListener(this);
			Addlisteners2Card(pos-1);
			player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
			player.pdeck.textField.repaint();
		}
	}

	public int selectionHandgui(MouseEvent e){
		for(int i=0;i<10;i++){
			if(e.getSource()==player.hand.handgui[i]){
				return i;
			}
		}
		return -1;
	}

	public void aiAttackOrigin(){
		atkOrigin=-1;
		atkDest=-1;
		i=0;
		while(i<26){
			Random r = new Random();
			int a = r.nextInt(5);
			if(this.aiAttack[a]==1){
				this.atkOrigin=a;
				break;
			}
			i++;
		}
	}

	public void aiAttackDest(){
		i=0;
		while(i<26){
			Random r = new Random();
			int a = r.nextInt(5);
			if(this.aiDest[a]==1){
				this.atkDest=a;
				break;
			}
			i++;
		}
	}

	public void accionarAgarreAutomatico(){
		Thread t1 = new Thread(() -> {
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			phases.actual-=1;
			this.instanciaGui.accionarAgarreAutomatico.doClick();
			phases.actual+=1;
			repaint();
		});
		t1.start();
	}

	public void initPossiblesAttacks(){
		for(int i=0; i<5; i++){
			this.aiAttack[i]=-1;
			this.aiDest[i]=-1;
		}
	}

	public void contPossibleTargetAttaks(int pos){
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
	}

	public void aiMovements() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if(ExistCardsInAiField()){
			Random al = new Random();
			int aleatorio = al.nextInt(2);
			if (aleatorio==0) {
				possiblesAiMovements();
			}
		}
		else {
			possiblesAiMovements();
		}
	}
	public void playPlayerCard() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		if(done==0)
			play(pl);
		phases.reaction.setIcon(new ImageIcon(("reAction.png")));
		repaint();
		aiMovements();
		phases.reaction.setIcon(new ImageIcon(("reAction2.png")));
		done=1;
		this.repairListeners(true);
	}

	public void aiPhases() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		phases.reaction.setIcon(new ImageIcon(("reAction2.png")));
		repaint();
		phases.change(phases.actual+1);

		if(this.contTurn>0){
			for(int i=0; i<5; i++){
				this.aiAttack[i]=-1;
				this.aiDest[i]=-1;
			}
			contTargetAttack=0;
			for(int i=0;i<5;i++){
				if(ai.aifield.cards[i]!=null && Objects.equals(ai.aifield.cards[i].getcard().GetType(), "Warrior")){
					this.aiAttack[i]=1;
					contTargetAttack++;
					swordsAi[i].setVisible(true);
				}
				else{
					this.aiAttack[i]=0;
				}
			}

			Random rr = new Random();
			if (contTargetAttack>0) {
				for (int iterador = 0; iterador < rr.nextInt(contTargetAttack+1); iterador++) {
					aiAttackOrigin();

					for(int i=0;i<5;i++){
						if(player.field.cards[i]!=null){
							this.aiDest[i]=1;
						}
						else{
							this.aiDest[i]=0;
						}
					}

					aiAttackDest();
					JOptionPane.showMessageDialog(null, "Card "+this.atkOrigin+" attack to player Card "+this.atkDest);

					for(int i=0;i<5;i++){
						if (this.atkOrigin==i) {
							swordsAi[i].setVisible(false);
						}
					}

					this.aiAttack[atkOrigin]=0;

					if(atkOrigin!=-1 && atkDest==-1 && countCardsInPlayerField()==0){
						int location = findPlayerBarrierToRemove();

						if(location!=-1){
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
						if(!player.field.cards[atkDest].getpos()){
							remove(phases);
							if(!fight.isVisible()){
								fight.addCards(new BigCard(player.field.cards[atkDest].getcard(),0,0),new BigCard(ai.aifield.cards[atkOrigin].getcard(),0,0));
							}
							if(player.field.cards[atkDest].getcard().GetHp()>ai.aifield.cards[atkOrigin].getcard().GetHp()){
								ai.aifield.quitar(atkOrigin);
								fallenAi.populate((SimpleColorTableModel) fallenAi.leftTable.getModel(), player.field.cards[atkOrigin].getcard());

							}else if(player.field.cards[atkDest].getcard().GetHp()<ai.aifield.cards[atkOrigin].getcard().GetHp()){
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

		for(int i=0;i<5;i++){
			swordsAi[i].setVisible(false);
		}
		turn=1;

		this.turnoLabel.setText("Player'S turn");
		this.contTurn++;

		ready=1;
		done=1;
		if(phases.actual<4){
			phases.change(phases.actual+1);
		}else{
			phases.change(-1);
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

		this.phases.draw.addMouseListener(this);

		if(bugPrimerTurnoUSer==0){
			bugPrimerTurnoUSer=1;
			phases.change(phases.actual+1);

			accionarAgarreAutomatico();
			repaint();
		}
		this.phases.end.addMouseListener(this);
		if(phases.actual==-1){
			phases.change(phases.actual+1);

			accionarAgarreAutomatico();
			repaint();
		}

		this.phases.draw.removeMouseListener(this);
		for(int i=0;i<5;i++)
		{
			if(Barriers.barriers[i]!=null){
				Barriers.barriers[i].addMouseListener(this);
			}
		}
		this.phases.action.addMouseListener(this);

		Thread t = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			phases.change(phases.actual+1);
			repaint();
		});
		t.start();
	}
}