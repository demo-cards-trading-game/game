package demo;
import extra.RoundedPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class BigCard extends RoundedPanel {
	public Card  actual;

	public BigCard(Card x, int a ,int b) throws IOException {
		super();
		setAutoscrolls(true);
		setForeground(Color.green);
		setBounds(a, b, 235, 322);
		setLayout(null);
		actual=x;

		CirclePanel panel = new CirclePanel();
		panel.setBorder(null);
		panel.setBounds(20, 34, 190, 150);

		JTextPane txtpnTexto = new JTextPane();
		txtpnTexto.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		txtpnTexto.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		txtpnTexto.setText(x.getDescription());
		txtpnTexto.setBounds(22, 192, 190, 106);
		txtpnTexto.setEditable(false);
		add(txtpnTexto);

		JLabel lblSource = new JLabel(x.getSource());
		lblSource.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblSource.setBounds(148, 32, 84, 20);
		lblSource.setOpaque(true);
		add(lblSource);

		switch (x.getSource()){
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

		if(Objects.equals(x.getType(), "Warrior")){
			setBackground(Color.ORANGE);

			txtpnTexto.setBackground(new Color(255, 228, 181));
			JLabel lblAtaque = new JLabel();
			lblAtaque.setText(""+x.getHp());
			lblAtaque.setBounds(170, 71, 76, 20);
			lblAtaque.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			lblAtaque.setBackground(new Color(255, 51, 204));
			lblAtaque.setOpaque(true);
			lblAtaque.setVisible(true);
			add(lblAtaque);

			JLabel lblDefensa = new JLabel(" " + x.getMp());
			lblDefensa.setBounds(170, 111, 76, 20);
			lblDefensa.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			lblDefensa.setBackground(new Color(0, 255, 51));
			lblDefensa.setOpaque(true);
			add(lblDefensa);

			JLabel lblSupport = new JLabel(" "+ x.getSup());
			lblSupport.setBounds(150, 150, 96, 20);
			lblSupport.setOpaque(true);
			lblSupport.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			add(lblSupport);
			lblSupport.setBackground(new Color(204, 153, 255));

			panel.setForeground(new Color(204, 153, 51));
			panel.setBackground(new Color(204, 153, 51));

		}else if(Objects.equals(x.getType(), "Disruption")){
			txtpnTexto.setBackground(new Color(255, 105, 180));
			setBackground(new Color(255, 0, 153));
			panel.setForeground(new Color(255, 0, 153));

		}else if(Objects.equals(x.getType(), "Event")){
			txtpnTexto.setBackground(new Color(216, 191, 216));
			setBackground(new Color(147, 112, 219));
			panel.setForeground(new Color(147, 112, 219));
			
		}else{
			txtpnTexto.setBackground(new Color(255, 228, 181));
			setBackground(new Color(0, 255, 0));
			panel.setForeground(new Color(0, 255, 0));
		}
		
		JLabel lblNombre = new JLabel(x.getName());
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Showcard Gothic", Font.ITALIC, 11));
		lblNombre.setBounds(10, 2, 161, 24);
		lblNombre.setForeground(new Color(255, 255, 204));
		lblNombre.setBackground(new Color(0, 0, 0));
		add(lblNombre);

		switch(x.getCardNumber()){
			case 1:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("01big.png")))));
				break;
			case 2:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("02big.png")))));
				break;
			case 3:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("03big.png")))));
				break;
			case 4:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("04big.png")))));
				break;
			case 5:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("05big.png")))));
				break;
			case 6:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("06big.png")))));
				break;
			case 7:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("07big.png")))));
				break;
			default:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("08big.png")))));
		}
		add(panel);

		JLabel lblAbility = new JLabel("Ability");
		lblAbility.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		lblAbility.setBounds(22, 171, 89, 20);
		add(lblAbility);

		JLabel lblNewLabel = new JLabel(""+x.getId());
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.ITALIC, 12));
		lblNewLabel.setForeground(new Color(0, 0, 51));
		lblNewLabel.setBounds(124, 301, 101, 21);
		add(lblNewLabel);
		
		JLabel lblType = new JLabel(x.getType());
		lblType.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setForeground(new Color(255, 255, 255));
		lblType.setBounds(141, 2, 84, 21);
		add(lblType);
	}

	public Card getcard(){
		return actual;
	}

	public class CirclePanel extends JPanel {
		@Override
	    protected void paintComponent(Graphics g) {
	        g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
	    }
	}
}