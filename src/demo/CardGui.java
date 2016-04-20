package demo;
import extra.RoundedLabel;
import extra.RoundedPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class CardGui extends JLayeredPane  implements ActionListener  {
	private Card  actual;
	public RoundedPanel menu;
	public JButton btnNewButton;
	public JButton Play, Preview,Discard,Set; 
	/** Stroke size. it is recommended to set it to 1 for better view */
	public JLabel lblAtaque;
	/**
	 * Create the panel.
	 */
	public CardGui(Card x, int a ,int b) {
		setBounds(a, b,105, 150);
		setLayout(null);
		setOpaque(false);
		RoundedPanel Fondo = new RoundedPanel();
		Fondo.setBounds(0,0,100,145);
		add(Fondo);
		actual=x;
		/**********************menu******************************/
		menu = new RoundedPanel();
		menu.setLayout(null);
		Play = new JButton("Play");
		Play.setBounds(15, 11, 89, 23);
		menu.add(Play);
		
		Discard = new JButton("Discard");
		Discard.setBounds(15, 45, 89, 23);
		menu.add(Discard);
		
		Preview = new JButton("Preview");
		Preview.setBounds(15, 79, 89, 23);
		menu.add(Preview);
		
		Set = new JButton("Set");
		Set.setBounds(15, 110, 89, 23);
		menu.add(Set);
		menu.setBounds(0,0,124,186);
		menu.setVisible(false);
		/******************************************************/
		actual=x;//el objeto carta que se pinta
		JLabel lblMpp = new JLabel(""+x.GetCost());
		lblMpp.setForeground(new Color(0, 0, 0));
		lblMpp.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
		lblMpp.setBounds(8,5,15,15);
		add(lblMpp);
		setForeground(Color.WHITE);
		JPanel panel = new JPanel();//aca va la imagen
		JTextPane txtpnTexto = new JTextPane();
		txtpnTexto.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 5));
		txtpnTexto.setText(x.GetDescription());
		txtpnTexto.setBounds(6,93, 80, 35);
		txtpnTexto.setEditable(false);
		add(txtpnTexto);
		
		JLabel lblSource = new JLabel(x.GetSource());
		lblSource.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblSource.setBounds(68, 28, 25, 10);
		lblSource.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		lblSource.setOpaque(true);
		add(lblSource);
		switch (x.GetSource())
		{
			case "Water":	lblSource.setBackground(new Color(0, 191, 255));
				break;
			case "Wind": 	lblSource.setBackground(Color.WHITE);
				break;
			case "Fire":   	lblSource.setBackground(Color.RED);
				break;
			case "Earth": 	lblSource.setBackground(new Color(160, 82, 45));
				break;
			default : 	lblSource.setBackground(Color.ORANGE);
		}
		
		panel.setBorder(null);
		panel.setVisible(true);
		panel.setBounds(2, 26, 90, 55);

		if(Objects.equals(x.GetType(), "Warrior"))
		{
			txtpnTexto.setBackground(new Color(255, 228, 181));

			lblAtaque = new JLabel();
			lblAtaque.setText(""+x.GetHp());
			lblAtaque.setBounds(80, 46, 14, 9);
			lblAtaque.setBackground(new Color(255, 51, 204));
			lblAtaque.setOpaque(true);
			lblAtaque.setVisible(true);
			add(lblAtaque);
			lblAtaque.setHorizontalAlignment(SwingConstants.CENTER);
			lblAtaque.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			lblAtaque.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 7));
			JLabel lblDefensa = new JLabel("" + x.GetMp());
			lblDefensa.setBounds(80, 60, 14, 9);
			lblDefensa.setBackground(new Color(0, 255, 51));
			lblDefensa.setOpaque(true);
			add(lblDefensa);
			lblDefensa.setHorizontalAlignment(SwingConstants.CENTER);
			lblDefensa.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			lblDefensa.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 7));
			JLabel lblSupport = new JLabel(""+ x.GetSup());
			lblSupport.setBounds(68, 75, 25, 10);
			lblSupport.setOpaque(true);
			add(lblSupport);
			lblSupport.setBackground(new Color(204, 153, 255));
			lblSupport.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			lblSupport.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 7));
			lblSupport.setHorizontalAlignment(SwingConstants.CENTER);
			Fondo.setBackground(Color.ORANGE);
			panel.setForeground(new Color(204, 153, 51));
		}else if(Objects.equals(x.GetType(), "Disruption"))
		{
			txtpnTexto.setBackground(new Color(255, 105, 180));
			Fondo.setBackground(new Color(255, 0, 153));
			panel.setForeground(new Color(255, 0, 153));
		}else if(Objects.equals(x.GetType(), "Event"))
		{
			txtpnTexto.setBackground(new Color(216, 191, 216));
			Fondo.setBackground(new Color(147, 112, 219));
			panel.setForeground(new Color(147, 112, 219));
		}else
		{
			txtpnTexto.setBackground(new Color(255, 228, 181));
			Fondo.setBackground(new Color(0, 255, 0));
			panel.setForeground(new Color(0, 255, 0));
		}
		txtpnTexto.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		JLabel lblNombre = new JLabel(x.GetName());
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Showcard Gothic", Font.ITALIC, 6));
		lblNombre.setBounds(0, 11, 100, 20);
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setBackground(new Color(0, 0, 0));
		add(lblNombre);
		try {
			switch(x.GetCardNumber()){
				case 1:
					panel.add(new RoundedLabel(new ImageIcon(ImageIO.read(new File("01.png")))));
					break;
				case 2: panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("02.png")))));
					break;
				case 3:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("03.png")))));
					break;
				case 4:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("03.png")))));
					break;
				case 5:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("02.png")))));
					break;
				case 8:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("16short.png")))));
					break;
				case 10:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("04.png")))));
					break;
				case 13:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("04.png")))));
					break;
				default :panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("cardtests.png")))));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		add(panel);

		JLabel lblAbility = new JLabel("Ability");
		lblAbility.setBounds(10, 82, 46, 10);
		lblAbility.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 7));
		add(lblAbility);

		JLabel lblNewLabel = new JLabel(""+x.Getid());
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 7));
		//ESTE COLOR NO SE NOTA MUCHO
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(65, 125, 77, 21);
		
		add(lblNewLabel);
		
		JLabel lblType = new JLabel(x.GetType());
		lblType.setFont(new Font("Showcard Gothic", Font.ITALIC, 7));
		lblType.setForeground(new Color(255, 255, 255));
		lblType.setBounds(55, 2, 52, 14);
		add(lblType);
		
		panel.setOpaque(false);
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel_1.setBounds(-10, -10, 42, 29);
		add(panel_1);
		try {
			panel_1.add(new JLabel(new ImageIcon(ImageIO.read(new File("mp.png")))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		moveToBack(Fondo);
	}
	    
	public Card getcard()
	{
		return actual;
	}
	public class CirclePanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)
		{
			menu.setVisible(false);
		}
	}
}