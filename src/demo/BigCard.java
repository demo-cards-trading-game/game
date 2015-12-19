package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import demo.CardGui;
import data.LoadData;



import demo.Card;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;






public class BigCard extends JPanel {
	private JTextField textField;
	public Card  actual;
	/**
	 * Create the panel.
	 */
	
	public BigCard(){
		
	}
	
	public BigCard(Card x, int a ,int b) {
		
		super();
		setAutoscrolls(true);
		
		actual=x;
		
		
		
		setForeground(Color.WHITE);
	
		
		CirclePanel panel = new CirclePanel();//aca va la imagen
		JTextPane txtpnTexto = new JTextPane();
		txtpnTexto.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		txtpnTexto.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		txtpnTexto.setText(x.GetDescription());
		txtpnTexto.setBounds(22, 192, 190, 106);
		txtpnTexto.setEditable(false);
		add(txtpnTexto);
		
		JLabel lblSource = new JLabel(x.GetSource());
		lblSource.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblSource.setBounds(148, 32, 84, 20);
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
		
		panel.setBounds(20, 34, 190, 150);
		//panel.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
	
		if(x.GetType()=="Warrior")
		{
			
			txtpnTexto.setBackground(new Color(255, 228, 181));
			//panel.setBounds(10, 36, 77, 62);
			
			JLabel lblAtaque = new JLabel();
			lblAtaque.setText(""+x.GetHp());
			
			lblAtaque.setBounds(170, 71, 76, 20);
			lblAtaque.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			lblAtaque.setBackground(new Color(255, 51, 204));
			lblAtaque.setOpaque(true);
			lblAtaque.setVisible(true);
			add(lblAtaque);
			
			JLabel lblDefensa = new JLabel(" " + x.GetMp());
			lblDefensa.setBounds(170, 111, 76, 20);
			lblDefensa.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			lblDefensa.setBackground(new Color(0, 255, 51));
			lblDefensa.setOpaque(true);
			add(lblDefensa);
			
			JLabel lblSupport = new JLabel(" "+ x.GetSup());
			lblSupport.setBounds(150, 150, 96, 20);
			lblSupport.setOpaque(true);
			lblSupport.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
			add(lblSupport);
			lblSupport.setBackground(new Color(204, 153, 255));
			
			setBackground(new Color(204, 153, 51));
			panel.setForeground(new Color(204, 153, 51));
			panel.setBackground(new Color(204, 153, 51));
		}else if(x.GetType()=="Disruption")
		{
			txtpnTexto.setBackground(new Color(255, 105, 180));
			setBackground(new Color(255, 0, 153));
			panel.setForeground(new Color(255, 0, 153));
			
			
		}else if(x.GetType()=="Event")
		{
			txtpnTexto.setBackground(new Color(216, 191, 216));
			setBackground(new Color(147, 112, 219));
			panel.setForeground(new Color(147, 112, 219));
			
		}else
		{
			txtpnTexto.setBackground(new Color(255, 228, 181));
			setBackground(new Color(0, 255, 0));
			panel.setForeground(new Color(0, 255, 0));
			
			
		}
		
		
	
		
		setBounds(a, b, 235, 322);	
		setBorder(new LineBorder(new Color(102, 51, 0), 2));
		setLayout(null);
		
		JLabel lblNombre = new JLabel(x.GetName());
		lblNombre.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombre.setFont(new Font("Showcard Gothic", Font.ITALIC, 11));
		lblNombre.setBounds(10, 2, 161, 24);
		lblNombre.setForeground(new Color(255, 255, 204));
		lblNombre.setBackground(new Color(0, 0, 0));
		add(lblNombre);
		
		
		  try {
			  switch(x.GetCardNumber()){
			  case 1:
				  	panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("01big.png")))));
				  	break;
			  case 2: panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("02big.png")))));
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
		  		
			  default :panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("08big.png")))));
			  
			  }
		      
		     } 
		   catch (IOException e) {
		      e.printStackTrace();
		     }
		  
		 add(panel);
		
		
		JLabel lblAbility = new JLabel("Ability");
		lblAbility.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 11));
		lblAbility.setBounds(22, 171, 89, 20);
		add(lblAbility);
		
		
		
		
		
		JLabel lblNewLabel = new JLabel(""+x.Getid());
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.ITALIC, 12));
		lblNewLabel.setForeground(new Color(0, 0, 51));
		lblNewLabel.setBounds(124, 301, 101, 21);
		add(lblNewLabel);
		
		JLabel lblType = new JLabel(x.GetType());
		lblType.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setForeground(new Color(255, 255, 255));
		lblType.setBounds(141, 2, 84, 21);
		add(lblType);
		
		
	
	
		

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
}
