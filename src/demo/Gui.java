package demo;
import extra.RollDice;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class Gui extends JFrame implements ActionListener, MouseListener{
	public JButton b2, accionarAgarreAutomatico,Aifirst,playerfirst;
	public JLabel demo, ai,player;
//	public JTextArea val2;
	public CardGui moving;
	public String Nombre1;
	public PlayGui player1;
	public RollDice dados;
	private FileWriter turno = null;
	private PrintWriter pw = null;
   
	public Gui() throws IOException {
		setSize(1024,798);
		setBackground(Color.white);
		setTitle("Dyna-stryfe");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addbackgound(this);
		getContentPane().setLayout(null);
		setResizable(false);
		setBackground(new Color(204, 204, 204));
		setVisible(true);

		b2 = new JButton("Play");
		b2.setBackground(Color.BLACK);
		b2.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		b2.setForeground(Color.WHITE);
		b2.setBounds(450, 550, 132, 43);
		b2.addActionListener(this);
		add(b2);

		demo=new JLabel("<html><font color='white'>Demo version: 0.000011 </font></html>");
		demo.setBounds(870,660,300,30);
		this.add(demo);

		accionarAgarreAutomatico = new JButton();
		accionarAgarreAutomatico.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(dados!=null){
			if(e.getSource()==dados.pane.rollButton){
				Thread t = new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }

                    if(Objects.equals(dados.pane.text.getText(), "3")){
                        dados.label.setBounds(70, 316+50, 507, 41);
                        dados.label.setText("Tie, Roll again");
                        dados.label.setVisible(true);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        b2.doClick();
                        dados.label.setBounds(70, 316+50, 507, 41);
                        dados.label.setText("Tie, Roll again");
                        dados.label.setVisible(true);
                    }
                    else{
                        dados.label.setBounds(150, 316, 507, 41);
                        dados.pane.rollButton.setVisible(false);
                        dados.pane.setVisible(false);
                        try {
                            turno= new FileWriter("turno.txt");
                            pw=new PrintWriter(turno);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
						dados.pane.text.setText("2");
                        if(Objects.equals(dados.pane.text.getText(), "1")){
                            dados.label.setText("Congratulations , fate is on your side");
                            dados.label.setVisible(true);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }

							dados.label.setText("Now select who is playing first");
							dados.label.setBounds(295, 316, 507, 41);
							add(dados.label);

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
							player.setForeground(Color.WHITE);
                            add(player);

							ai=new JLabel("Ai");
                            ai.setBounds(750 ,620,200,30);
                            ai.setAlignmentX(CENTER_ALIGNMENT);
							ai.setForeground(Color.WHITE);
                            add(ai);

							playerfirst.addActionListener(this);
							Aifirst.addActionListener(this);

							repaint();
                            pw.println(1);
                        }
                        else{
                            dados.label.setText("AI player gets the  first turn");
							dados.pane.setVisible(true);
                            dados.label.setVisible(true);
                            dados.btnPlay.setVisible(true);
							repaint();
                            pw.println(2);
                        }

                        try {
                            turno.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                });
				t.start();
			}

			if(e.getSource()==Aifirst){
				dados.pane.text.setText("2");
				dados.btnPlay.doClick();
			}
	
			if(e.getSource()==playerfirst){
				dados.pane.text.setText("1");
				dados.btnPlay.doClick();
			}
	
			if(e.getSource()==dados.btnPlay){
				try {
					player1=new PlayGui(0,0,Nombre1, this);
					Thread t = new Thread(() -> {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        if(Objects.equals(dados.pane.text.getText(), "2")){
                            try {
                                player1.Aiturn();
                                player1.contTurn++;
                            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e1) {
                                e1.printStackTrace();
                            }
						}
                        else {
                            player1.firstPlayerTurn();
                        }
                    });
					t.start();
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
					e1.printStackTrace();
				}

				PlayGui.player.pdeck.btnNewButton_1.addMouseListener(this);
				PlayGui.player.pdeck.btnNewButton.addMouseListener(this);
				PlayGui.player.pdeck.textField.addMouseListener(this);

				try {
					addbackground3(this);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				getContentPane().setBackground(new Color(153, 204, 204));
				getContentPane().setLayout(null);

				player1.repaint.addActionListener(this);
				add(player1);

				setVisible(true);
			}
		}

		if (e.getSource()==b2){
			try {
				dados= new RollDice();
				addbackground4(this);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			getContentPane().setLayout(null);
					
			/*val2 = new JTextArea();
			val2.setBounds(480, 580, 0, 0);
			val2.setVisible(true);
			val2.requestFocusInWindow();
			val2.addKeyListener(new myKeyState3());
			add(val2);*/

			dados.pane.rollButton.addActionListener(this);
			dados.btnPlay.addActionListener(this);
			add(dados);

			setVisible(true);
		}

		if (player1!=null){
			if(e.getSource()==player1.repaint){
				repaint();
			}

			if (e.getSource()==accionarAgarreAutomatico){
				if(this.player1.getPhaseActual()==0){
					if(player1.cardDrawn==0){
						if(PlayGui.player.pdeck.Deck.cardsLeft()!= 0 ){
							CardGui nueva= null;
							try {
								nueva = new CardGui(PlayGui.player.pdeck.Deck.extraerR(),0,0);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							appear(nueva);
							final CardGui finalNueva = nueva;
							Thread t1 = new Thread(() -> {
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}
								try {
									PlayGui.player.hand.draw(finalNueva);
								} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
									e1.printStackTrace();
								}
								repaint();
							});
							t1.start();
						}else{
							doGameOver();
						}
					}else{
						JOptionPane.showMessageDialog(null, "Sorry , u can pick only a card per turn");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Sorry , u can only pick cards on the draw phase");
				}
			}
		}
	}

	class myKeyState3 extends KeyAdapter implements KeyListener{
		@Override
		public void keyPressed(KeyEvent arg0) {
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
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
	}

	void addbackgound(JFrame jfm) throws IOException {
		jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("test.jpg")))));
	}

	void gameover(JFrame jfm) throws IOException {
		jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("gameover.jpg")))));
	}
	
	void winner(JFrame jfm) throws IOException{
		jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("frame3.jpg")))));
	}

	void addbackground3(JFrame jfm)throws IOException{
		jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("frame3.jpg")))));
	}

	void addbackground4(JFrame jfm)throws IOException{
		jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("frame4.jpg")))));
	}

	public void doGameOver(){
		try {
			gameover(this);
			setBackground(Color.RED);
			player1=new PlayGui(0,0,Nombre1,this);
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
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
		try {
			winner(this);
			setBackground(Color.RED);
			player1=new PlayGui(0,0,Nombre1,this);
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e1) {
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

	public static void main(String[] args) throws IOException {
		new Gui();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(player1!=null){
			if (e.getSource()== PlayGui.player.pdeck.btnNewButton){
				if(this.player1.getPhaseActual()==0){
					if(player1.cardDrawn==0){
						if(PlayGui.player.pdeck.Deck.cardsLeft()!= 0 ){
							CardGui nueva= null;
							try {
								nueva = new CardGui(PlayGui.player.pdeck.Deck.extraerR(),0,0);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							appear(nueva);
						}else{
							doGameOver();
						}
					}else{
						JOptionPane.showMessageDialog(null, "Sorry , u can pick only a card per turn");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Sorry , u can only pick cards on the draw phase");
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource()== PlayGui.player.pdeck.btnNewButton){
			PlayGui.player.pdeck.btnNewButton.setIcon(new ImageIcon("draw2.png"));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource()== PlayGui.player.pdeck.btnNewButton){
			PlayGui.player.pdeck.btnNewButton.setIcon(new ImageIcon("draw1.png"));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource()== PlayGui.player.pdeck.btnNewButton){
			PlayGui.player.pdeck.btnNewButton.setIcon(new ImageIcon("draw3.png"));
			PlayGui.player.pdeck.textField.setBounds(234,206+70,70,20);
			PlayGui.player.pdeck.textField.setVisible(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource()== PlayGui.player.pdeck.btnNewButton){
			PlayGui.player.pdeck.btnNewButton.setIcon(new ImageIcon("draw1.png"));
		}
	}

	public void appear(final CardGui card) {
		moving=card;
		player1.animations.add(moving);
		Thread t = new Thread(() -> {
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

            while(i>=652){
                i--;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                moving.setLocation(i, 609-93);
            }

			player1.animations.remove(moving);
            player1.repairListeners(false);
            PlayGui.player.pdeck.textField.setText("cards left "+ PlayGui.player.pdeck.Deck.cardsLeft());
            player1.cardDrawn=1;
            PlayGui.player.pdeck.textField.repaint();
            setVisible(true);
            repaint();
        });
		t.start();
	}
}