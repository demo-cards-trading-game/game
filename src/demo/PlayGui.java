package demo;
import demo.Fallen.SimpleColorTableModel;
import extra.RoundedPanel;
import extra.movePanel;
import utils.GeneralConstants;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class PlayGui extends JLayeredPane implements ActionListener, MouseListener, GeneralConstants {
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
	public Phases phases;
	public Fallen fallenAi;
	public  JButton repaint;
	public int warriorPlayed;
	public int cardDrawn, barierpicked;
	public JLabel swordsPlayer[], swordsAi[];
	public JLabel dest[], top[];
	public int atkDest=-1, atkOrigin=-1;
	public int [] aiAttack= new int[5];
	public int [] aiDest= new int[5];
	public int contTargetAttack;
	public JButton j;
	public prueba2 listAll;
	public JLabel ptarjet[], aitarjet[];
	public int selected=-1;
	public RoundedPanel unleash;
	public JLabel ptarjet8[], aitarjet8[];
	public JLabel ptarjet9[], aitarjet9[];
	public JLabel ptarjet10[], aitarjet10[];
	public JLabel ptarjet11[], aitarjet11[];
	public int done;
	public int bugPrimerTurnoUSer=0;
	public int liberarTutoEnd=1;
	public int ubicacionDeCarta;
	public Gui instanciaGui;
	public TransferHandler transferCard;

	public PlayGui(int x , int y, String name, Gui g) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		this.instanciaGui = g;
		transferCard=new TransferHandler("carta");
		setBorder(null);
		setOpaque(false);
		setLayout(null);
		setBounds(0,0, 1024, 768);

		player=new PlayerGui(x,y,name);
		player.hand.addMouseListener(this);
		this.add(player);

		cardDrawn=0;

		fight=new fightpane();
		moveToFront(fight);
		this.add(fight);

		unleash=new RoundedPanel();
		unleash.setBounds(0,0,100,145);
		unleash.add (new JLabel(new ImageIcon(ImageIO.read(new File("unleash.png")))));

		phases=new Phases(220,360);
		phases.getLabel(PHASES_DRAW).addMouseListener(this);
		add(phases);

		repaint=new JButton();

		ai = new AIGui();
		this.add(ai);

		fallenAi=new Fallen();
		add(fallenAi);

		preview= new Previewpane();
		this.add(preview);

		fallen=new Fallen();
		add(fallen);

		op = new optionpane();

		FileReader turno = new FileReader(new File("turno.txt"));
		BufferedReader br = new BufferedReader(turno);
		this.turn = Integer.parseInt(br.readLine());
		turno.close();

		if(turn==1){
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
		}


		this.listAll = new prueba2(player.pdeck.Deck);
		this.listAll.setBounds(150, 100, 620, 420);
		this.listAll.setVisible(false);
		this.moveToFront(this.listAll);
		this.listAll.aceptar.addActionListener(this);

		player.barriers.addMouseListener(this);
		player.pdeck.btnNewButton_1.addMouseListener(this);
		player.field.addMouseListener(this);

		swordsPlayer = new JLabel[5];
		swordsAi = new JLabel[5];
		dest = new JLabel[5];
		top = new JLabel[5];
		ptarjet = new JLabel[5];
		aitarjet = new JLabel[5];
		ptarjet8 = new JLabel[5];
		aitarjet8 = new JLabel[5];
		ptarjet9 = new JLabel[5];
		aitarjet9 = new JLabel[5];
		ptarjet10 = new JLabel[5];
		aitarjet10 = new JLabel[5];
		ptarjet11 = new JLabel[5];
		aitarjet11 = new JLabel[5];

		for(int i=1;i<=5;i++){
			int pos= player.hand.draw(player.pdeck.Deck.extraerR());
			player.barriers.addbarrier(player.pdeck.Deck.extraerR());
			Addlisteners2Card(pos-1);
			player.hand.handgui[pos-1].addMouseListener(this);
			ai.aideck.textField.setText("cards left "+ ai.aideck.Deck.cardsLeft());
			player.pdeck.textField.setText("cards left "+ player.pdeck.Deck.cardsLeft());
			player.pdeck.textField.repaint();

			swordsPlayer[i-1]=setLabel("sword.png", new Rectangle(240+(110*i), 350, 50, 120));
			swordsAi[i-1]=setLabel("swordR.png", new Rectangle(0, 0, 540+(220*i), 385));
			dest[i-1]=setLabel("redTarget1.png", new Rectangle(230+(i*110), 210, 50, 50));
			top[i-1]=setLabel("redTarget1.png", new Rectangle(200+(i*130),580,80,30));
			ptarjet[i-1]=setLabel("redTarget1.png", new Rectangle(230+(i*110),380, 50, 50));
			aitarjet[i-1]=setLabel("redTarget1.png", new Rectangle(230+(i*110), 210, 50, 50));
			ptarjet8[i-1]=setLabel("redTarget1.png", new Rectangle(230+(i*110),380, 50, 50));
			aitarjet8[i-1]=setLabel("redTarget1.png", new Rectangle(230+(i*110), 210, 50, 50));
			ptarjet9[i-1]=setLabel("redTarget1.png", new Rectangle(230+(i*110), 380, 50, 50));
			aitarjet9[i-1]=setLabel("redTarget1.png", new Rectangle(230+(i*110), 210, 50, 50));
			ptarjet10[i-1]=setLabel("redTarget1.png", new Rectangle(230+(i*110), 380, 50, 50));
			aitarjet10[i-1]=setLabel("redTarget1.png", new Rectangle(230+(i*110), 210, 50, 50));
			ptarjet11[i-1]=setLabel("redTarget1.png", new Rectangle(230+(i*110), 380, 50, 50));
			aitarjet11[i-1]=setLabel("redTarget1.png", new Rectangle(230+(i*110), 210, 50, 50));
		}

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
			this.phases.getLabel(PHASES_END).addMouseListener(this);
		}
		
	
	}

	public void actionPerformed(ActionEvent e) {
		done = 0;

		if (e.getSource() == fallenAi.confirmcardsfromfallen) {
			SmallCard aux = null;

			try {
				aux = new SmallCard(fallenAi.cards[0].getCard(),0,0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			final int where = ai.aifield.findwhereSetCard();

			if (fallenAi.effectnumber == 13){
				if (aux != null) {
					number = aux.getCard().getCardNumber();
				}
				ai.aifield.addCard(aux, where);

				fallenAi.remove();
				fallenAi.confirmcardsfromfallen.setEnabled(false);
				fallenAi.button.setEnabled(false);
				fallenAi.effectnumber = 0;
				fallenAi.setVisible(false);

				if (aux != null && !Objects.equals(aux.actual.getType(), "Warrior")) {
					Thread t1 = new Thread(() -> {
						try {
							Thread.sleep(750);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						ai.aifield.removeCard(where);
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
			final int where=player.field.findwhereSetCard();

			try {
				aux = new SmallCard(fallen.cards[0].getCard(),0,0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if(fallen.effectnumber==13){
				if (aux != null) {
					number  =aux.getCard().getCardNumber();
				}
				player.field.addCard(aux,where);
				fallen.remove();
				fallen.confirmcardsfromfallen.setEnabled(false);
				fallen.button.setEnabled(false);
				fallen.effectnumber=0;
				fallen.setVisible(false);
				if (aux != null && !Objects.equals(aux.actual.getType(), "Warrior")) {
					Thread t1 = new Thread(() -> {
						try {
							Thread.sleep(750);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						player.field.removeCard(where);
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
					player.hand.draw(fallen.cards[0].getCard() );
					player.hand.draw(fallen.cards[1].getCard() );
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
			if (player.hand.cards[s].getCardNumber() == 18)
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

		for(int i=0; i<player.hand.current;i++){
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
					preview.addCard(new BigCard(player.pdeck.Hero.getCard(),0,0));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			done=1;
		}

		for(int i=0; i<player.hand.current; i++){
			if(e.getSource()==player.hand.handgui[i].Preview){
				if(done==0){
					try {
						preview.addCard(new BigCard(player.hand.handgui[i].getCard(),0,0));
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

		for(int i=0;i<player.hand.current;i++){
			if(e.getSource()==player.hand.handgui[i].Play){
				pl=i;
			}
		}

		if(pl!=-1){
			try{
				if(pl!=-2){
					if(player.hand.cards[pl].getId().equals("SSD-10")&&(contarBarriers()>=0)){
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
					for(int i=0;i<Barriers.barriers.length;i++){
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

				if((e.getSource()==phases.getLabel(PHASES_SETUP))||(e.getSource()==phases.getLabel(PHASES_DRAW))||(e.getSource()==phases.getLabel(PHASES_ACTION))||(e.getSource()==phases.getLabel(PHASES_ATTACK))||(e.getSource()==phases.getLabel(PHASES_END))){
					repaint();
					done=1;
					if (e.getSource()==phases.getLabel(PHASES_END) && phases.actual < 3) {
						barierpicked=0;
						warriorPlayed=0;
						cardDrawn=0;

						for(int i=0;i<player.hand.current;i++)
							player.hand.handgui[i].Play.setEnabled(false);

						this.phases.getLabel(PHASES_SETUP).removeMouseListener(this);
						this.phases.getLabel(PHASES_DRAW).removeMouseListener(this);
						this.phases.getLabel(PHASES_ACTION).removeMouseListener(this);
						this.phases.getLabel(PHASES_ATTACK).removeMouseListener(this);
						phases.changeTurn(4);
					}
					else {
						if(phases.actual<4){
							phases.changeTurn(phases.actual+1);
						}else{
							if(ready==1){
								phases.changeTurn(0);
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

							this.phases.getLabel(PHASES_SETUP).removeMouseListener(this);
							this.phases.getLabel(PHASES_DRAW).removeMouseListener(this);
							this.phases.getLabel(PHASES_DRAW).addMouseListener(this);
							break;
						case 1:
							this.phases.getLabel(PHASES_DRAW).removeMouseListener(this);
							for(int i=0;i<Barriers.barriers.length;i++){
								if(Barriers.barriers[i]!=null){
									Barriers.barriers[i].addMouseListener(this);
								}
							}
							this.phases.getLabel(PHASES_ACTION).addMouseListener(this);
							break;
						case 2:
							for(int i=0;i<Barriers.barriers.length;i++){
								if(Barriers.barriers[i]!=null){
									Barriers.barriers[i].removeMouseListener(this);
								}
							}
							this.phases.getLabel(PHASES_ACTION).removeMouseListener(this);
							this.phases.getLabel(PHASES_ATTACK).addMouseListener(this);

							for(int i=0;i<player.hand.current;i++)
								player.hand.handgui[i].Play.setEnabled(true);
							player.pdeck.Play.setEnabled(true);
							break;
						case 3:
							liberarTutoEnd=0;
							battle();
							this.phases.getLabel(PHASES_ATTACK).removeMouseListener(this);

							for(int i=0;i<player.hand.current;i++)
								player.hand.handgui[i].Play.setEnabled(false);

							if(this.turn==1&&this.contTurn>0){
								for(int i=0;i<player.field.cards.length;i++){
									if(player.field.cards[i]!=null && !player.field.cards[i].down){
										this.swordsPlayer[i].setVisible(true);
									}
								}
							}
							break;
						case 4:
							if(ready==1){
								for (int i=0;i<Barriers.barriers.length;i++)
									Barriers.barriers[i].removeMouseListener(this);
								ready=0;
								this.phases.getLabel(PHASES_SETUP).addMouseListener(this);

								for(int i=0;i<swordsPlayer.length;i++){
									swordsPlayer[i].setVisible(false);
									swordsAi[i].setVisible(false);
								}

								for (JLabel aDest : dest) {
									aDest.setVisible(false);
								}

								if(turn==1){
									turn=2;
								}else{
									turn=1;
								
								}
								repaint();
								repaint();
								this.contTurn++;
								this.atkDest=this.atkOrigin=-1;
							}else{
								ready=1;
							}
							this.phases.getLabel(PHASES_END).removeMouseListener(this);
							ai.aideck.btnNewButton.addMouseListener(this);
							phases.changeTurn(0);
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
				for(int i=0;i<player.hand.current;i++){
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
					for(int i=0;i<player.field.cards.length;i++){
						if(e.getSource()==player.field.cards[i]&&!player.field.cards[i].down){
							for(int j=0; j<swordsPlayer.length; j++){
								swordsPlayer[j].setVisible(false);
							}
							swordsPlayer[i].setVisible(true);
						}
					}
				}

				if(e.getSource()==ptarjet[0]||e.getSource()==ptarjet[1]||e.getSource()==ptarjet[2]||e.getSource()==ptarjet[3]||e.getSource()==ptarjet[4]){
					for(int i=0; i<ptarjet.length; i++){
						if(e.getSource()==ptarjet[i]){
							selected=i+1;
						}
						ptarjet[i].setVisible(false);

						if(this.ai.aifield.cards[i]!=null){
							aitarjet[i].setVisible(true);
						}
					}
					repaint();
					System.out.println("please select a card from ai field");
					repaint();
				}

				if(e.getSource()==aitarjet[0]||e.getSource()==aitarjet[1]||e.getSource()==aitarjet[2]||e.getSource()==aitarjet[3]||e.getSource()==aitarjet[4]){
					int pos=0;
					try {
						pos = player.hand.draw(player.field.cards[this.selected-1].getCard());
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
						e1.printStackTrace();
					}
					player.hand.handgui[pos-1].addMouseListener(this);
					Addlisteners2Card(pos-1);
					player.field.removeCard(this.selected-1);
					this.selected=-1;

					for(int i=0; i<aitarjet.length; i++){
						if(e.getSource()==aitarjet[i]){
							this.selected=i+1;
						}
						aitarjet[i].setVisible(false);
					}

					this.ai.aifield.removeCard(this.selected-1);
					System.out.println("cards were returned to the hands of owners");
				}

				if(e.getSource()==ptarjet8[0]||e.getSource()==ptarjet8[1]||e.getSource()==ptarjet8[2]||e.getSource()==ptarjet8[3]||e.getSource()==ptarjet8[4]){
					this.selected=-1;
					int pos = 0;

					for(int i=0; i<ptarjet8.length;i++){
						ptarjet8[i].setVisible(false);
						aitarjet8[i].setVisible(false);

						if(e.getSource()==ptarjet8[i]){
							selected=i+1;
						}
					}

					if(this.selected==-1){
						for(int i=0; i<aitarjet8.length;i++){
							if(e.getSource()==aitarjet8[i]){
								this.selected=i+1;
							}
						}
						this.ai.aifield.removeCard(this.selected-1);
					}else{
						try {
							pos= player.hand.draw(player.field.cards[this.selected-1].getCard());
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							e1.printStackTrace();
						}
						player.hand.handgui[pos-1].addMouseListener(this);
						Addlisteners2Card(pos-1);
						player.field.removeCard(this.selected-1);
					}
					repaint();
					System.out.println("cards were returned to the hands of owners");
				}

				if(e.getSource()==ptarjet9[0]||e.getSource()==ptarjet9[1]||e.getSource()==ptarjet9[2]||e.getSource()==ptarjet9[3]||e.getSource()==ptarjet9[4]){
					for(int i=0; i<ptarjet9.length; i++){
						ptarjet9[i].setVisible(false);
					}

					player.field.removeCard(this.selected);
					System.out.println("select an ai card to destroy");

					for(int i=0; i<aitarjet9.length; i++){
						if(this.ai.aifield.cards[i]!=null){
							aitarjet9[i].setVisible(true);
						}
					}
				}

				if(e.getSource()==aitarjet9[0]||e.getSource()==aitarjet9[1]||e.getSource()==aitarjet9[2]||e.getSource()==aitarjet9[3]||e.getSource()==aitarjet9[4]){
					for(int i=0; i<aitarjet9.length; i++){
						if(e.getSource()==aitarjet9[i]){
							this.ai.aifield.removeCard(i);
						}
						aitarjet9[i].setVisible(false);
					}

					this.selected=-1;
					System.out.println("destroyed succefully");
					repaint();
				}

				if(e.getSource()==ptarjet10[0]||e.getSource()==ptarjet10[1]||e.getSource()==ptarjet10[2]||e.getSource()==ptarjet10[3]||e.getSource()==ptarjet10[4]||e.getSource()==aitarjet10[0]||e.getSource()==aitarjet10[1]||e.getSource()==aitarjet10[2]||e.getSource()==aitarjet10[3]||e.getSource()==aitarjet10[4]){
					for(int i=0; i<5; i++){
						ptarjet10[i].setVisible(false);
						aitarjet10[i].setVisible(false);

						if(e.getSource()==ptarjet10[i]){
							player.field.removeCard(i);
						}

						if(e.getSource()==aitarjet10[i]){
							this.ai.aifield.removeCard(i);
						}
					}

					System.out.println("destroyed succefully");
					repaint();
				}

				if(e.getSource()==ptarjet11[0]||e.getSource()==ptarjet11[1]||e.getSource()==ptarjet11[2]||e.getSource()==ptarjet11[3]||e.getSource()==ptarjet11[4]||e.getSource()==aitarjet11[0]||e.getSource()==aitarjet11[1]||e.getSource()==aitarjet11[2]||e.getSource()==aitarjet11[3]||e.getSource()==aitarjet11[4]){
					Card c;

					for(int i=0; i<5; i++){
						ptarjet11[i].setVisible(false);
						aitarjet11[i].setVisible(false);

						if(e.getSource()==ptarjet11[i]){
							c= player.field.cards[i].getCard();
							player.field.removeCard(i);

							try {
								player.field.addCard(new SmallCard(c,0,0), i);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}

						if(e.getSource()==aitarjet11[i]){
							c=this.ai.aifield.cards[i].getCard();
							this.ai.aifield.removeCard(i);

							try {
								this.ai.aifield.addCard(new SmallCard(c,0,0), i);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}

					repaint();
					System.out.println("face down succesfully");
					repaint();
				}

				for(int i=0;i<swordsPlayer.length;i++){
					if(e.getSource()==swordsPlayer[i]){
						atkOrigin= i+1;
					}

					if(atkOrigin!=-1){
						if(ai.aifield.getCardsLength()==0){
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
							for(int j=0; j<dest.length; j++){
								if(ai.aifield.cards[j]!=null){
									this.dest[j].setVisible(true);
								}
							}
						}
					}
				}

				if(e.getSource()==this.dest[0]||e.getSource()==this.dest[1]||e.getSource()==this.dest[2]||e.getSource()==this.dest[3]||e.getSource()==this.dest[4]){
					for(int i=0; i<dest.length; i++){
						if(e.getSource()==this.dest[i]){
							this.atkDest=i+1;
						}
						swordsPlayer[i].setVisible(false);
					}

					System.out.println("Card "+this.atkOrigin+" attack to ai Card "+this.atkDest);
					remove(phases);

					if(!fight.isVisible()){
						try {
							fight.addCards(new BigCard(player.field.cards[atkOrigin-1].getCard(),0,0),new BigCard(ai.aifield.cards[atkDest-1].getCard(),0,0));
						} catch (IOException | InterruptedException e1) {
							e1.printStackTrace();
						}
					}

					if(player.field.cards[atkOrigin-1].getCard().getHp()>ai.aifield.cards[atkDest-1].getCard().getHp()){
						ai.aifield.removeCard(atkDest-1);
						try {
							this.makeEffect(player.field.cards[atkOrigin-1].getCard().getId(),atkOrigin-1 );
						} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
							e1.printStackTrace();
						}

					}else if(player.field.cards[atkOrigin-1].getCard().getHp()<ai.aifield.cards[atkDest-1].getCard().getHp()){
						player.field.removeCard(atkOrigin-1);
					}
					add(phases);

					for (JLabel aDest : dest) {
						aDest.setVisible(false);
					}
				}
			}

			if (e.getSource()==player.pdeck.btnNewButton_1){
				fallen.setVisible(true);
				moveToFront(fallen);
			}

			if(e.getSource()==top[0]||e.getSource()==top[1]||e.getSource()==top[2]||e.getSource()==top[3]||e.getSource()==top[4]){
				for(int i=0; i<top.length; i++){
					top[i].setVisible(false);
				}

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
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
				repaint();
			}
		}
		else if(e.getButton() == MouseEvent.BUTTON3){
			if(e.getClickCount()==1){
				for(int i=0;i<player.field.cards.length;i++){
					if(e.getSource()==player.field.cards[i]){
						if(player.field.cards[i].getCard().getId().equals("SSD-01")){
							System.out.println("This card referred to 4 water power");
							player.powers.set(player.field.cards[i].getCard().getCost()*4);
						}
						else{
							player.powers.play(player.field.cards[i].getCard().getCost());
						}
						fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(),player.field.cards[i].getCard());
						player.field.removeCard(i);
					}
				}
			}
		}
	}

	public void mouseExited(MouseEvent e) {
		for(int i=0;i<Barriers.barriers.length;i++){
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

		for(int i=0;i<player.field.cards.length;i++){
			if(e.getSource()==player.field.cards[i]){
				preview.Remove();
			}
		}

		for(int i=0;i<ai.aifield.cards.length;i++){
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

		if(e.getSource()==phases.getLabel(PHASES_SETUP)){
			phases.getLabel(PHASES_SETUP).setIcon(new ImageIcon(("setup.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_ACTION)){
			phases.getLabel(PHASES_ACTION).setIcon(new ImageIcon(("action.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_DRAW)){
			phases.getLabel(PHASES_DRAW).setIcon(new ImageIcon(("draw11.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_ATTACK)){
			phases.getLabel(PHASES_ATTACK).setIcon(new ImageIcon(("attack.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_END)){
			phases.getLabel(PHASES_END).setIcon(new ImageIcon(("getLabel(PHASES_END).png")));
		}

		evaluateTarjects(e.getSource());

		for(int i=0;i<swordsPlayer.length;i++){
			if (e.getSource()==swordsPlayer[i]) {
				swordsPlayer[i].setIcon(new ImageIcon("sword.png"));
			}
		}
		repaint();
	}

	public void mouseEntered(MouseEvent e) {
		for(int i=0;i<Barriers.barriers.length;i++){
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

		for(int i=0;i<player.field.cards.length;i++){
			if(e.getSource()==player.field.cards[i]){
				try {
					preview.addCard(new BigCard(player.field.cards[i].getCard(),0,0));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		for(int i=0;i<ai.aifield.cards.length;i++){
			if(e.getSource()==ai.aifield.cards[i]){
				try {
					preview.addCard(new BigCard(ai.aifield.cards[i].getCard(),0,0));
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

		if(e.getSource()==phases.getLabel(PHASES_SETUP)){
			phases.getLabel(PHASES_SETUP).setIcon(new ImageIcon(("setup3.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_ACTION)){
			phases.getLabel(PHASES_ACTION).setIcon(new ImageIcon(("action3.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_DRAW)){
			phases.getLabel(PHASES_DRAW).setIcon(new ImageIcon(("draw33.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_ATTACK)){
			phases.getLabel(PHASES_ATTACK).setIcon(new ImageIcon(("attack3.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_END)){
			phases.getLabel(PHASES_END).setIcon(new ImageIcon(("end3.png")));
			if (liberarTutoEnd==0) {
				liberarTutoEnd=1;
			}
		}

		for(int i=0; i<5; i++){
			if(e.getSource()==ptarjet[i]){
				ptarjet[i].setIcon(new ImageIcon("redTarget3.png"));
			}

			if(e.getSource()==aitarjet[i]){
				aitarjet[i].setIcon(new ImageIcon("redTarget3.png"));
			}

			if(e.getSource()==ptarjet8[i]){
				ptarjet8[i].setIcon(new ImageIcon("redTarget3.png"));
			}

			if(e.getSource()==aitarjet8[i]){
				aitarjet8[i].setIcon(new ImageIcon("redTarget3.png"));
			}

			if(e.getSource()==ptarjet9[i]){
				ptarjet9[i].setIcon(new ImageIcon("redTarget3.png"));
			}

			if(e.getSource()==aitarjet9[i]){
				aitarjet9[i].setIcon(new ImageIcon("redTarget3.png"));
			}
		}

		if(e.getSource()==ptarjet10[i]){
			ptarjet10[i].setIcon(new ImageIcon("redTarget3.png"));
		}

		if(e.getSource()==aitarjet10[i]){
			aitarjet10[i].setIcon(new ImageIcon("redTarget3.png"));
		}

		if(e.getSource()==ptarjet11[i]){
			ptarjet11[i].setIcon(new ImageIcon("redTarget3.png"));
		}

		if(e.getSource()==aitarjet11[i]){
			aitarjet11[i].setIcon(new ImageIcon("redTarget3.png"));
		}

		for(int i=0;i<swordsPlayer.length;i++){
			if (e.getSource()==swordsPlayer[i]) {
				swordsPlayer[i].setIcon(new ImageIcon("swordEnfoqued.png"));
			}
		}

		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource()==player.pdeck.btnNewButton_1){
			player.pdeck.btnNewButton_1.setIcon(new ImageIcon("fallen2.png"));
		}
		if (e.getSource()==player.pdeck.btnNewButton_2){
			player.pdeck.btnNewButton_2.setIcon(new ImageIcon("forgotten2.png"));
		}
		if(e.getSource()==phases.getLabel(PHASES_SETUP)){
			phases.getLabel(PHASES_SETUP).setIcon(new ImageIcon(("setup2.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_ACTION)){
			phases.getLabel(PHASES_ACTION).setIcon(new ImageIcon(("action2.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_DRAW)){
			phases.getLabel(PHASES_DRAW).setIcon(new ImageIcon(("draw22.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_ATTACK)){
			phases.getLabel(PHASES_ATTACK).setIcon(new ImageIcon(("attack2.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_END)){
			phases.getLabel(PHASES_END).setIcon(new ImageIcon(("endz.png")));
		}

		for(int i=0; i<5; i++){
			if(e.getSource()==ptarjet[i]){
				ptarjet[i].setIcon(new ImageIcon("redTarget2.png"));
			}

			if(e.getSource()==aitarjet[i]){
				aitarjet[i].setIcon(new ImageIcon("redTarget2.png"));
			}

			if(e.getSource()==ptarjet8[i]){
				ptarjet8[i].setIcon(new ImageIcon("redTarget2.png"));
			}

			if(e.getSource()==aitarjet8[i]){
				aitarjet8[i].setIcon(new ImageIcon("redTarget2.png"));
			}

			if(e.getSource()==ptarjet9[i]){
				ptarjet9[i].setIcon(new ImageIcon("redTarget2.png"));
			}

			if(e.getSource()==aitarjet9[i]){
				aitarjet9[i].setIcon(new ImageIcon("redTarget2.png"));
			}

			if(e.getSource()==ptarjet10[i]){
				ptarjet10[i].setIcon(new ImageIcon("redTarget2.png"));
			}

			if(e.getSource()==aitarjet10[i]){
				aitarjet10[i].setIcon(new ImageIcon("redTarget2.png"));
			}

			if(e.getSource()==ptarjet11[i]){
				ptarjet11[i].setIcon(new ImageIcon("redTarget2.png"));
			}

			if(e.getSource()==aitarjet11[i]){
				aitarjet11[i].setIcon(new ImageIcon("redTarget2.png"));
			}
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
		if(e.getSource()==phases.getLabel(PHASES_SETUP)){
			phases.getLabel(PHASES_SETUP).setIcon(new ImageIcon(("setup2.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_ACTION)){
			phases.getLabel(PHASES_ACTION).setIcon(new ImageIcon(("action2.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_DRAW)){
			phases.getLabel(PHASES_DRAW).setIcon(new ImageIcon(("draw22.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_ATTACK)){
			phases.getLabel(PHASES_ATTACK).setIcon(new ImageIcon(("attack2.png")));
		}
		if(e.getSource()==phases.getLabel(PHASES_END)){
			phases.getLabel(PHASES_END).setIcon(new ImageIcon(("end2.png")));
		}

		evaluateTarjects(e.getSource());
	}

	public int getPhaseActual(){
		return phases.actual;
	}

	public void battle(){
		//int pos=player.field.findwarrior();//busca un warrior en el campo para hacer la animacion
	}

	private void Addlisteners2Card(int i){
		player.hand.handgui[i].Play.addActionListener(this);
		player.hand.handgui[i].Discard.addActionListener(this);
		player.hand.handgui[i].Preview.addActionListener(this);
		player.hand.handgui[i].Set.addActionListener(this);
	}

	void set(final int pos,final int where) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		final SmallCard carta  ;

		if(pos!=-2 && pos!=-3){
			X=player.hand.handgui[pos].getX();
			Y=player.hand.handgui[pos].getY();
			if (Objects.equals(player.hand.handgui[pos].getCard().getType(), "Warrior")) {
				warriorPlayed = 1;
			}
		}

			if(pos>=0){
				carta = new SmallCard(player.hand.handgui[pos].getCard(),0,0);
				moving=new SmallCard(player.hand.handgui[pos].getCard(),0,0);
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
                    player.powers.play(carta.getCard().getCost());
                    player.field.addCard(carta, where);
                    carta.repaint();
                });
				t.start();
				carta.addMouseListener(this);
				carta.repaint();

				if(carta.getCard().getCardNumber()==15){
					player.powers.token();
					player.powers.token();
					player.powers.token();
					player.powers.token();
				}

				if(carta.getCard().getCardNumber()==16){
					fallen.setVisible(true);
					fallen.effectnumber=13;
					fallen.button.setEnabled(true);
				}

				if(carta.getCard().getCardNumber()==17){
					fallen.setVisible(true);
					fallen.button.setEnabled(true);
					fallen.effectnumber=14;
				}

				this.makeEffect(carta.actual.getId(),where);
				ubicacionDeCarta = where;
				repaint();

				if (!Objects.equals(carta.actual.getType(), "Warrior")) {
					Thread t1 = new Thread(() -> {
                        try {
                            Thread.sleep(750);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        player.field.removeCard(ubicacionDeCarta);
                        if(carta.getCard().getCardNumber()==18){
                            player.powers.setwp();
                        }
                        repaint();
                    });
					t1.start();
				}
			}
			if(pos==-2){
				donde=w;
				Hero = new SmallCard(player.pdeck.Hero.getCard(),0,0);
				Hero.shadowColor=Color.black.darker();
				Hero.addMouseListener(this);
				player.pdeck.panel.remove(player.pdeck.Hero);
				player.pdeck.panel.remove(player.pdeck.menu);
				player.powers.play(player.pdeck.Hero.getCard().getCost());
				RoundedPanel show=new RoundedPanel();
				show.setBounds(0,0,100,145);
				player.pdeck.panel.add(show);
				player.field.addCard(Hero, where);
			}

			if(pos==-3){
				Hero=new SmallCard(player.pdeck.Deck.lista.Data.Consultar(9),0,0);
				player.field.removeCard(donde);
				player.powers.play(Hero.getCard().getCost());
				player.field.addCard(Hero, donde);
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
			c=player.hand.handgui[pos].getCard().getCost();
			allowed= warriorPlayed == 0 ||(!Objects.equals(player.hand.handgui[pos].getCard().getType(), "Warrior") && warriorPlayed ==1 );
		}else if (pos==-3)
			c=player.pdeck.Deck.lista.Data.Consultar(9).getCost();
		else
			c=player.pdeck.Hero.getCard().getCost();

		if ( player.powers.power+player.powers.Volatile - c >=0 ) {
			if (allowed) {
				int where = player.field.findwhereSetCard();
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

	public void Aiturn() throws IOException, LineUnavailableException, UnsupportedAudioFileException, InterruptedException{
		if (ai.aideck.Deck.cardsLeft()==0) {
			instanciaGui.doWin();
			repaint();
		}

		ai.aidra.token();
		ai.aidra.reset();

		phases.changeTurn(phases.actual+1);

		ai.aihand.draw(ai.aideck.Deck.extraerR());
		ai.aideck.textField.setText("cards left "+ai.aideck.Deck.cardsLeft());
		ai.aideck.textField.repaint();

		phases.changeTurn(phases.actual+1);
		setVisible(true);

		aiMovements();

		phases.getLabel(PHASES_REACTION).setIcon(new ImageIcon(("reAction.png")));
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

				for(int i=0; i<top.length; i++){
					if(player.hand.current>i){
						top[i].setVisible(true);
					}
				}
			}
			if(id.equals("SSD-07")){
				System.out.println("please select a card from your field");
				for(int i=0; i<ptarjet.length; i++){
					if(player.field.cards[i]!=null && pos!=0){
						ptarjet[i].setVisible(true);
					}
				}
			}

			if(id.equals("SSD-08")){
				System.out.println("please select a card from the field");
				for(int i=0; i<5; i++){
					if(player.field.cards[i]!=null && pos!=i){
						ptarjet8[i].setVisible(true);
					}
					if(this.ai.aifield.cards[i]!=null){
						aitarjet8[i].setVisible(true);
					}
				}
			}

			if(id.equals("SSD-09")){
				System.out.println("please select a Siren Character");
				this.selected=-1;

				for(int i=0; i<ptarjet9.length;i++){
					if(player.field.cards[i]!=null&& player.field.cards[i].getCard().getId().equals("SSD-03")){
						ptarjet9[i].setVisible(true);
						selected=0;
					}
				}

				if(this.selected==-1){
					System.out.println("sorry, you don't contain a Siren Character in the field");
				}
			}

			if(id.equals("SSD-10")){
				System.out.println("select an ai card to destroy");
				for(int i=0; i<ptarjet10.length;i++){
					if(player.field.cards[i]!=null && pos!=i){
						ptarjet10[i].setVisible(true);
					}

					if(this.ai.aifield.cards[i]!=null){
						aitarjet10[i].setVisible(true);
					}
				}
			}

			if(id.equals("SSD-11")){
				System.out.println("select 1 Character to Turn face-down");
				for(int i=0; i<ptarjet11.length;i++){
					if(player.field.cards[i]!=null && pos!=i && !player.field.cards[i].down){
						ptarjet11[i].setVisible(true);
					}

					if(ai.aifield.cards[i]!=null && !ai.aifield.cards[i].down){
						aitarjet11[i].setVisible(true);
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

				for(int i=0;i<player.field.cards.length;i++){
					if(player.field.cards[i]!=null){
						this.aiDest[i]=1;
					}
					else{
						this.aiDest[i]=0;
					}
				}

				aiAttackDest();

				if (atkDest!=-1 && atkOrigin!=-1) {
					player.hand.draw(player.field.cards[this.atkDest].getCard());
					player.field.removeCard(this.atkDest);

					pos= this.ai.aihand.draw(this.ai.aifield.cards[this.atkOrigin].getCard());
					this.ai.aifield.removeCard(this.atkOrigin);
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

				for(int i=0;i<player.field.cards.length;i++){
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
						player.hand.draw(player.field.cards[this.atkDest].getCard());
						player.hand.handgui[this.atkDest].addMouseListener(this);
						player.field.removeCard(this.atkDest);
					}else{
						pos= this.ai.aihand.draw(this.ai.aifield.cards[this.atkOrigin].getCard());
						this.ai.aifield.removeCard(this.atkOrigin);
					}
				}else {
					System.out.println("cannot find targets");
				}
			}

			if(id.equals("SSD-09")){
				this.selected=-1;
				for(int i=0;i<ai.aifield.cards.length;i++){
					if(this.ai.aifield.cards[i]!=null&&pos!=i&&this.ai.aifield.cards[i].getCard().getId().equals("SSD-03")){
						this.selected=i;
					}
				}

				if(this.selected==-1){
					System.out.println("cannot find a Siren");
				}else{
					this.ai.aifield.removeCard(this.selected);
					for(int i=0; i<aiDest.length; i++){
						this.aiDest[i]=-1;
					}

					atkDest=-1;
					for(int i=0;i<aiDest.length;i++){
						if(player.field.cards[i]!=null){
							this.aiDest[i]=1;
						}
						else{
							this.aiDest[i]=0;
						}
					}

					aiAttackDest();
					if(this.atkDest!=-1){
						player.field.removeCard(this.atkDest);
					}else{
						System.out.println("can't find a target");
					}
				}

				if(id.equals("SSD-10")){
					initPossiblesAttacks();
					contPossibleTargetAttaks(pos);
					aiAttackOrigin();

					for(int i=0;i<player.field.cards.length;i++){
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
							player.field.removeCard(this.atkDest);
						}
						else{
							this.ai.aifield.removeCard(this.atkOrigin);
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

				for(int i=0;i<player.field.cards.length;i++){
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
						c = player.field.cards[this.atkDest].getCard();
						player.field.removeCard(this.atkDest);
						player.field.addCard(new SmallCard( c,0,0), this.atkDest);

					} else {
						c = this.ai.aifield.cards[this.atkOrigin].getCard();
						this.ai.aifield.removeCard(this.atkOrigin);
						this.ai.aifield.addCard(new SmallCard( c,0,0), this.atkOrigin);
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
		for (int i = 0; i < Barriers.cards.length; i++) {
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

	public void firstPlayerTurn(){
		barierpicked=0;
		warriorPlayed=0;
		cardDrawn=0;

		for(int i=0;i<player.hand.current;i++)
			player.hand.handgui[i].Play.setEnabled(false);

		player.pdeck.Play.setEnabled(false);
		player.powers.reset();
	    
		this.phases.getLabel(PHASES_DRAW).addMouseListener(this);
	}

	public int countCardsInPlayerField(){
		int aux=0;

		for (int i = 0; i < player.field.cards.length; i++) {
			if (player.field.cards[i]!=null) {
				aux++;
			}
		}
		return aux;
	}
	
	public int findPlayerBarrierToRemove(){
		int aux=-1;
		
		for (int i = 0; i < Barriers.barriers.length; i++) {
			if (Barriers.barriers[i].isVisible()) {
				aux=i;
				break;
			}
		}
		return aux;
	}
	
	public boolean ExistCardsInAiField(){
		boolean band= false;

		for(int i=0;i<ai.aifield.cards.length;i++){
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
			if (ai.aihand.cards[i]!=null && Objects.equals(ai.aihand.cards[i].getType(), "Warrior")) {
				band=true;
				break;
			}
		}
		return band;
	}
	
	public boolean canInvoqueWarriorToHand(){
		boolean band=false;
		
		for (int i = 0; i < getCantAiHandCards(); i++) {
			if (Objects.equals(ai.aihand.cards[i].getType(), "Warrior") && ai.aihand.cards[i].getCost()<(getCantVolatilePower()+getCantUndrainedPower())) {
				band = true;
				break;
			}
		}
		return band;
	}
	
	public boolean existPowerToPlayAnotherCard(){
		boolean band = false;
		
		for (int i = 0; i < getCantAiHandCards(); i++) {
			if (!Objects.equals(ai.aihand.cards[i].getType(), "Warrior") && ai.aihand.cards[i].getCost()<(getCantVolatilePower()+getCantUndrainedPower())) {
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
		return ai.aifield.getCardsLength();
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
			if (ai.aihand.cards[i].getCost()<(getCantVolatilePower()+getCantUndrainedPower())){
				if (Objects.equals(type, "Warrior")) {
					if (Objects.equals(ai.aihand.cards[i].getType(), "Warrior")) {
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
		if(aleatorio>0)
			fallenAi.populate((SimpleColorTableModel) fallenAi.leftTable.getModel(), ai.aihand.cards[aleatorio-1]);
		ai.aihand.discard(aleatorio);
		repaint();
	}
	
	public int getCardAiFieldLocation(){
		int location = -1;
		
		for (int i = 0; i < ai.aifield.cards.length; i++) {
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
            fallenAi.populate((SimpleColorTableModel) fallenAi.leftTable.getModel(), ai.aifield.cards[ai.whereInvoqued].getCard());
            ai.aifield.removeCard(ai.whereInvoqued);
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
		ai.aifield.addCard(carta, cardAiFieldLocation);

		if (carta != null && carta.getCard().getCardNumber() == 15) {
			for(int i=0;i<5;i++)
				ai.aidra.token();
		}
		ai.aihand.discard(cardAiHandLocation);
		ai.whereInvoqued=cardAiFieldLocation;
		repaint();
		
		this.makeAiEffect(ai.aifield.cards[ai.whereInvoqued].getCard().getId(),ai.whereInvoqued);
		preview.addCard(new BigCard(ai.aifield.cards[ai.whereInvoqued].getCard(), 0, 0));
		if (!Objects.equals(ai.aifield.cards[ai.whereInvoqued].getCard().getType(), "Warrior")) {
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
            for (int i1 = 0; i1 < player.field.cards.length; i1++) {
                if(player.field.cards[i1]!=null && !Objects.equals(player.field.cards[i1].getCard().getType(), "Warrior")){
                    fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(), player.field.cards[i1].getCard());
                    player.field.removeCard(i1);
                }
            }
            preview.Remove();
            repaint();
        });
		t1.start();
	}

	public void addPowerCardFromTheDeck() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
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
		for(int i=0;i<player.hand.current;i++){
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
		for(int i=0; i<aiAttack.length; i++){
			this.aiAttack[i]=-1;
			this.aiDest[i]=-1;
		}
	}

	public void contPossibleTargetAttaks(int pos){
		contTargetAttack=0;
		for(int i=0;i<ai.aifield.cards.length;i++){
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
		phases.getLabel(PHASES_REACTION).setIcon(new ImageIcon(("reAction.png")));
		repaint();
		aiMovements();
		phases.getLabel(PHASES_REACTION).setIcon(new ImageIcon(("reAction2.png")));
		done=1;
		this.repairListeners(true);
	}

	public void aiPhases() throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
		phases.getLabel(PHASES_REACTION).setIcon(new ImageIcon(("reAction2.png")));
		repaint();
		phases.changeTurn(phases.actual+1);

		if(this.contTurn>0){
			for(int i=0; i<aiAttack.length; i++){
				this.aiAttack[i]=-1;
				this.aiDest[i]=-1;
			}
			contTargetAttack=0;
			for(int i=0;i<ai.aifield.cards.length;i++){
				if(ai.aifield.cards[i]!=null && Objects.equals(ai.aifield.cards[i].getCard().getType(), "Warrior")){
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

					for(int i=0;i<player.field.cards.length;i++){
						if(player.field.cards[i]!=null){
							this.aiDest[i]=1;
						}
						else{
							this.aiDest[i]=0;
						}
					}

					aiAttackDest();
					JOptionPane.showMessageDialog(null, "Card "+this.atkOrigin+" attack to player Card "+this.atkDest);

					for(int i=0;i<swordsAi.length;i++){
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
						if(!player.field.cards[atkDest].getPos()){
							remove(phases);
							if(!fight.isVisible()){
								fight.addCards(new BigCard(player.field.cards[atkDest].getCard(),0,0),new BigCard(ai.aifield.cards[atkOrigin].getCard(),0,0));
							}
							if(player.field.cards[atkDest].getCard().getHp()>ai.aifield.cards[atkOrigin].getCard().getHp()){
								ai.aifield.removeCard(atkOrigin);
								fallenAi.populate((SimpleColorTableModel) fallenAi.leftTable.getModel(), player.field.cards[atkOrigin].getCard());

							}else if(player.field.cards[atkDest].getCard().getHp()<ai.aifield.cards[atkOrigin].getCard().getHp()){
								player.field.removeCard(atkDest);
								fallen.populate((SimpleColorTableModel) fallen.leftTable.getModel(), player.field.cards[atkDest].getCard());
								this.makeAiEffect(this.ai.aifield.cards[atkOrigin].getCard().getId(),atkOrigin );
							}
							add(phases);
						}
					}
					repaint();
				}
			}
		}

		phases.changeTurn(phases.actual+1);

		for(int i=0;i<swordsAi.length;i++){
			swordsAi[i].setVisible(false);
		}
		turn=1;

		
		this.contTurn++;

		ready=1;
		done=1;
		if(phases.actual<4){
			phases.changeTurn(phases.actual+1);
		}else{
			phases.changeTurn(-1);
		}
		barierpicked=0;
		warriorPlayed=0;
		cardDrawn=0;

		for(int i=0;i<player.hand.current;i++)
			player.hand.handgui[i].Play.setEnabled(false);

		System.out.println("you get 1 volatile power, use it wisely");
		player.powers.token();
		player.powers.reset();
		repaint();

		this.phases.getLabel(PHASES_DRAW).addMouseListener(this);

		if(bugPrimerTurnoUSer==0){
			bugPrimerTurnoUSer=1;
			phases.changeTurn(phases.actual+1);

			accionarAgarreAutomatico();
			repaint();
		}
		this.phases.getLabel(PHASES_END).addMouseListener(this);
		if(phases.actual==-1){
			phases.changeTurn(phases.actual+1);

			accionarAgarreAutomatico();
			repaint();
		}

		this.phases.getLabel(PHASES_DRAW).removeMouseListener(this);
		for(int i=0;i<Barriers.barriers.length;i++){
			if(Barriers.barriers[i]!=null){
				Barriers.barriers[i].addMouseListener(this);
			}
		}
		this.phases.getLabel(PHASES_ACTION).addMouseListener(this);

		Thread t = new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			phases.changeTurn(phases.actual+1);
			repaint();
		});
		t.start();
	}

	public JLabel setLabel(String image, Rectangle rectangle) throws IOException {
		JLabel label = new JLabel(new ImageIcon(ImageIO.read(new File(image))));
		label.setBounds(rectangle);
		label.setVisible(false);
		label.addMouseListener(this);
		moveToFront(label);
		add(label);

		return label;
	}

	public void evaluateTarjects(Object o){
		for(int i=0; i<5; i++){
			if(o==ptarjet[i]){
				ptarjet[i].setIcon(new ImageIcon("redTarget1.png"));
			}

			if(o==aitarjet[i]){
				aitarjet[i].setIcon(new ImageIcon("redTarget1.png"));
			}

			if(o==ptarjet8[i]){
				ptarjet8[i].setIcon(new ImageIcon("redTarget1.png"));
			}

			if(o==aitarjet8[i]){
				aitarjet8[i].setIcon(new ImageIcon("redTarget1.png"));
			}

			if(o==ptarjet9[i]){
				ptarjet9[i].setIcon(new ImageIcon("redTarget1.png"));
			}

			if(o==aitarjet9[i]){
				aitarjet9[i].setIcon(new ImageIcon("redTarget1.png"));
			}

			if(o==ptarjet10[i]){
				ptarjet10[i].setIcon(new ImageIcon("redTarget1.png"));
			}

			if(o==aitarjet10[i]){
				aitarjet10[i].setIcon(new ImageIcon("redTarget1.png"));
			}

			if(o==ptarjet11[i]){
				ptarjet11[i].setIcon(new ImageIcon("redTarget1.png"));
			}

			if(o==aitarjet11[i]){
				aitarjet11[i].setIcon(new ImageIcon("redTarget1.png"));
			}
		}
	}
}