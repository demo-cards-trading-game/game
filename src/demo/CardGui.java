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






public class CardGui extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	
	public CardGui(Card x) {
		super();
	
		
		CirclePanel panel = new CirclePanel();//aca va la imagen
	
		
		panel.setBounds(10, 54, 130, 104);
		if(x.GetType()=="Warrior")
		{
			panel.setBounds(10, 54, 106, 104);
			JLabel lblAtaque = new JLabel(" "+x.GetHp());
			lblAtaque.setBounds(119, 70, 46, 14);
			add(lblAtaque);
			
			JLabel lblDefensa = new JLabel(" " + x.GetMp());
			lblDefensa.setBounds(119, 99, 46, 14);
			add(lblDefensa);
			
			JLabel lblSupport = new JLabel(" "+ x.GetSup());
			lblSupport.setBounds(119, 124, 46, 14);
			add(lblSupport);
			setBackground(new Color(255, 215, 0));
			
		}else if(x.GetType()=="Disruption")
		{
			setBackground(new Color(255, 0, 153));
			
			
		}else if(x.GetType()=="Event")
		{
			setBackground(new Color(102, 0, 255));
			
		}else
		{
			setBackground(new Color(0, 255, 0));
			
			
		}
		
		
	
		
		setBounds(400, 200, 146, 250);	
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		
		JLabel lblNombre = new JLabel(x.GetName());
		lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 11));
		lblNombre.setBounds(10, 29, 126, 14);
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setBackground(new Color(0, 0, 0));
		add(lblNombre);
		
		
		  try {
			  switch(x.GetCardNumber()){
			  case 1:
				  	panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("01.jpg")))));
				  	break;
			  case 2: panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("02.jpg")))));
			  		break;
			  		
			  case 3:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("03.jpg")))));
			  		break;
			  case 4:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("04.jpg")))));		
			  		break;
			  case 5:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("05.jpg")))));		
		  		break;	
		  		
			  case 10:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("05.jpg")))));		
		  		break;	
		  		
			  case 13:panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("05.jpg")))));		
		  		break;	
		  		
			  default :panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("cardtest.jpg")))));
			  
			  }
		      
		     } 
		   catch (IOException e) {
		      e.printStackTrace();
		     }
		  
		 add(panel);
		
		
		JLabel lblAbility = new JLabel("Ability");
		lblAbility.setBounds(10, 169, 46, 14);
		add(lblAbility);
		
		JTextPane txtpnTexto = new JTextPane();
		txtpnTexto.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 10));
		txtpnTexto.setText(x.GetDescription());
		txtpnTexto.setBounds(10, 183, 126, 56);
		txtpnTexto.setEditable(false);
		add(txtpnTexto);
		
		
		
		JLabel lblNewLabel = new JLabel(""+x.Getid());
		lblNewLabel.setBounds(10, 11, 87, 21);
		add(lblNewLabel);

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
