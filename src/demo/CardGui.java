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
		setBackground(new Color(255, 215, 0));
	
		
		setBounds(400, 200, 146, 250);	
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		
		JLabel lblNombre = new JLabel(x.GetName());
		lblNombre.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 11));
		lblNombre.setBounds(10, 29, 126, 14);
		lblNombre.setForeground(new Color(0, 0, 0));
		lblNombre.setBackground(new Color(0, 0, 0));
		add(lblNombre);
		
		JPanel panel = new JPanel();
		
		panel.setBounds(10, 54, 106, 104);
		
		  try {
		      panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("cardtest.jpg")))));
		      
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
		
		JLabel lblAtaque = new JLabel(" "+x.GetHp());
		lblAtaque.setBounds(119, 70, 46, 14);
		add(lblAtaque);
		
		JLabel lblDefensa = new JLabel(" " + x.GetMp());
		lblDefensa.setBounds(119, 99, 46, 14);
		add(lblDefensa);
		
		JLabel lblSupport = new JLabel(" "+ x.GetSup());
		lblSupport.setBounds(119, 124, 46, 14);
		add(lblSupport);

	}
}
