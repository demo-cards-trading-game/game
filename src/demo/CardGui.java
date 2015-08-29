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






public class CardGui extends JPanel {
	private JTextField textField;
	private Card  actual;
	/**
	 * Create the panel.
	 */
	
	public CardGui(Card x) {
		
		super();
		
		actual=x;
		
		
		
		setForeground(Color.WHITE);
	
		
		CirclePanel panel = new CirclePanel();//aca va la imagen
		JTextPane txtpnTexto = new JTextPane();
		txtpnTexto.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 8));
		txtpnTexto.setText(x.GetDescription());
		txtpnTexto.setBounds(10, 126, 102, 49);
		txtpnTexto.setEditable(false);
		add(txtpnTexto);
		
		JLabel lblSource = new JLabel(x.GetSource());
	
		lblSource.setBounds(64, 32, 60, 14);
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
		
		panel.setBounds(10, 36, 102, 72);
	
		if(x.GetType()=="Warrior")
		{
			
			txtpnTexto.setBackground(new Color(255, 228, 181));
			//panel.setBounds(10, 36, 77, 62);
			
			JLabel lblAtaque = new JLabel();
			lblAtaque.setText(""+x.GetHp());
			
			lblAtaque.setBounds(94, 56, 46, 10);
			lblAtaque.setBackground(new Color(255, 51, 204));
			lblAtaque.setOpaque(true);
			lblAtaque.setVisible(true);
			add(lblAtaque);
			
			JLabel lblDefensa = new JLabel(" " + x.GetMp());
			lblDefensa.setBounds(94, 81, 46, 10);
			lblDefensa.setBackground(new Color(0, 255, 51));
			lblDefensa.setOpaque(true);
			add(lblDefensa);
			
			JLabel lblSupport = new JLabel(" "+ x.GetSup());
			lblSupport.setBounds(78, 101, 46, 14);
			lblSupport.setOpaque(true);
			add(lblSupport);
			lblSupport.setBackground(new Color(204, 153, 255));
			
			setBackground(new Color(204, 153, 51));
			panel.setForeground(new Color(204, 153, 51));
			
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
		
		
	
		
		setBounds(400, 200, 124, 186);	
		setBorder(new LineBorder(new Color(102, 51, 0), 2));
		setLayout(null);
		
		JLabel lblNombre = new JLabel(x.GetName());
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Showcard Gothic", Font.ITALIC, 8));
		lblNombre.setBounds(0, 11, 124, 20);
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setBackground(new Color(0, 0, 0));
		add(lblNombre);
		
		
		  try {
			  switch(x.GetCardNumber()){
			  case 1:
				  	panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("01.png")))));
				  	break;
			  case 2: panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("02.png")))));
			  		break;
			  		
			  case 3:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("03.png")))));
			  		break;
			  case 4:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("03.png")))));		
			  		break;
			  case 5:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("02.png")))));		
		  		break;	
		  		
			  case 10:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("04.png")))));		
		  		break;	
		  		
			  case 13:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("04.png")))));		
		  		break;	
		  		
			  default :panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("cardtest.png")))));
			  
			  }
		      
		     } 
		   catch (IOException e) {
		      e.printStackTrace();
		     }
		  
		 add(panel);
		
		
		JLabel lblAbility = new JLabel("Ability");
		lblAbility.setBounds(10, 109, 46, 14);
		add(lblAbility);
		
		
		
		
		
		JLabel lblNewLabel = new JLabel(""+x.Getid());
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 9));
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setBounds(10, 0, 77, 21);
		add(lblNewLabel);
		
		JLabel lblType = new JLabel(x.GetType());
		lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setForeground(new Color(255, 255, 255));
		lblType.setBounds(52, 2, 62, 14);
		add(lblType);
		
	
		

	}
	
	public Card getcard()
	{
		return actual;
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
}
