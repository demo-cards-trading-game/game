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






public class SmallCard extends JLayeredPane{
	private JTextField textField;
	private Card  actual;
	Graphics2D g2d;
    boolean rotating = false;
    int count;
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	
	public SmallCard(boolean bocabajo,Card x ) throws IOException {
		
		setOpaque(true);
		actual=x;

		//setBounds(new Rectangle(0, 0, 100, 145));
		setBounds(0,0, 100, 145);	
		
		setLayout(null);
		if(!bocabajo)
			normal(x);
		else
			abajo();
	

	}
	public void abajo()
	{
		
		try {
			JPanel panel_1= new JPanel();
			panel_1.setBounds(0, 0, 100, 145);
			panel_1.setOpaque(true);
			panel_1.setVisible(true);
			panel_1.setLayout(null);
			JLabel prueba=new JLabel(new ImageIcon(ImageIO.read(new File("back.png"))));
			panel_1.setBorder(new LineBorder(Color.GRAY, 2, true));
			panel_1.add(prueba);
			prueba.setBounds(2,2,96,141);
			add(panel_1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		repaint();
		
	
		
	}
	public void normal(Card x )
	{
		

		setBorder(new LineBorder(new Color(102, 51, 0), 2));
		actual=x;//el objeto carta que se pinta
		
		
		
		setForeground(Color.WHITE);
	
		
		CirclePanel panel = new CirclePanel();//aca va la imagen
		
		
			
			
			
		
		JTextPane txtpnTexto = new JTextPane();
		txtpnTexto.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 5));
		txtpnTexto.setText(x.GetDescription());
		txtpnTexto.setBounds(5,100, 90, 40);
		txtpnTexto.setEditable(false);
		add(txtpnTexto);
		
		JLabel lblSource = new JLabel(x.GetSource());
		lblSource.setFont(new Font("Tahoma", Font.PLAIN, 8));
	
		lblSource.setBounds(70, 32, 30, 10);
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
		panel.setOpaque(true);
		panel.setVisible(true);
		panel.setBounds(10, 36, 60, 55);
		
	
		if(x.GetType()=="Warrior")
		{
			
			txtpnTexto.setBackground(new Color(255, 228, 181));
			//panel.setBounds(10, 36, 77, 62);
			
			JLabel lblAtaque = new JLabel();
			lblAtaque.setText(""+x.GetHp());
			
			lblAtaque.setBounds(75, 56, 24, 9);
			lblAtaque.setBackground(new Color(255, 51, 204));
			lblAtaque.setOpaque(true);
			lblAtaque.setVisible(true);
			add(lblAtaque);
			
			JLabel lblDefensa = new JLabel("" + x.GetMp());
			lblDefensa.setBounds(75, 70, 24, 9);
			lblDefensa.setBackground(new Color(0, 255, 51));
			lblDefensa.setOpaque(true);
			add(lblDefensa);
			
			JLabel lblSupport = new JLabel(""+ x.GetSup());
			lblSupport.setBounds(75, 85, 24, 9);
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
		
		
	
		
	
		
		JLabel lblNombre = new JLabel(x.GetName());
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Showcard Gothic", Font.ITALIC, 5));
		lblNombre.setBounds(0, 11, 112, 20);
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
		lblAbility.setBounds(10, 92, 46, 7);
		add(lblAbility);
		
		
		
		
		
		JLabel lblNewLabel = new JLabel(""+x.Getid());
		lblNewLabel.setFont(new Font("Showcard Gothic", Font.BOLD | Font.ITALIC, 7));
		//ESTE COLOR NO SE NOTA MUCHO
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(10, 0, 77, 21);
		add(lblNewLabel);
		
		JLabel lblType = new JLabel(x.GetType());
		//lblType.setHorizontalAlignment(SwingConstants.RIGHT);
		lblType.setFont(new Font("Showcard Gothic", Font.ITALIC, 7));
		lblType.setForeground(new Color(255, 255, 255));
		lblType.setBounds(55, 2, 52, 14);
		add(lblType);
		
		
		
		
	}



	public Card getcard()
	{

		return actual;
	}

	public class CirclePanel extends JPanel {



		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d;
			g.drawOval(0, 0, g.getClipBounds().width, g.getClipBounds().height);
			g2d = (Graphics2D) g;
			g2d.rotate( 180.0 * Math.PI,g.getClipBounds().width, g.getClipBounds().height);
			
		}
	}
}