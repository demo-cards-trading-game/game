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






 	public class CardGui extends JLayeredPane   {
	private JTextField textField;
	private Card  actual;
	public JInternalFrame menu;
	public JButton Play, Preview,Discard,Set; 
	/**
	 * Create the panel.
	 */
	
	public CardGui(Card x, int a ,int b) {
		setOpaque(true);
		
	setBounds(a, b, 124, 186);	
	
		
		setBorder(new LineBorder(new Color(102, 51, 0), 2));
		setLayout(null);
		
		
		actual=x;
		/**********************menu******************************/
		menu = new JInternalFrame();
		menu.getContentPane().setLayout(null);
		
		JButton Play = new JButton("Play");
		Play.setBounds(15, 11, 89, 23);
		menu.getContentPane().add(Play);
		
		Discard = new JButton("Discard");
		Discard.setBounds(15, 45, 89, 23);
		menu.getContentPane().add(Discard);
		
		Preview = new JButton("Preview");
		Preview.setBounds(15, 79, 89, 23);
		menu.getContentPane().add(Preview);
		
		Set = new JButton("Set");
		Set.setBounds(15, 113, 89, 23);
		menu.getContentPane().add(Set);
		menu.setClosable(true);
		menu.setBounds(0,0,124,186);
		menu.setVisible(false);
		/******************************************************/
		
		
		
	
		
		CirclePanel panel = new CirclePanel();
		JTextPane txtpnTexto = new JTextPane();
		txtpnTexto.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		txtpnTexto.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		txtpnTexto.setVerifyInputWhenFocusTarget(false);
		txtpnTexto.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 8));
		txtpnTexto.setText(x.GetDescription());
		txtpnTexto.setBounds(10, 126, 102, 49);
		txtpnTexto.setEditable(false);
		
	
		add(txtpnTexto);
		
		
		JLabel lblSource = new JLabel(x.GetSource());
		lblSource.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	
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
			menu.setBackground(new Color(204, 153, 51));//the menu bacckground is added
			setBackground(new Color(204, 153, 51));
			panel.setForeground(new Color(204, 153, 51));
			
		}else if(x.GetType()=="Disruption")
		{
			txtpnTexto.setBackground(new Color(255, 105, 180));
			setBackground(new Color(255, 0, 153));
			panel.setForeground(new Color(255, 0, 153));
			menu.setBackground(new Color(255, 105, 180));//the menu bacckground is added
			
		}else if(x.GetType()=="Event")
		{
			txtpnTexto.setBackground(new Color(216, 191, 216));
			setBackground(new Color(147, 112, 219));
			panel.setForeground(new Color(147, 112, 219));
			menu.setBackground(new Color(147, 112, 219));
		}else
		{
			txtpnTexto.setBackground(new Color(255, 228, 181));
			setBackground(new Color(0, 255, 0));
			panel.setForeground(new Color(0, 255, 0));
			menu.setBackground(new Color(0, 255, 0));
			
		}
		
		
	
		
	
		
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
		add(menu);
		

	}

	 @Override
     public void paintComponent(Graphics g)
     {
		 
         int height = 200;
         int width = 120;
         g.setColor(Color.gray);
         g.drawRoundRect(0, 0, width, height, 20, 30);
        
        
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
