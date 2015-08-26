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
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;

import demo.Card;
import javax.swing.border.BevelBorder;




public class CardGui extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	
	public CardGui(Card x) {
	
		
			
				
				
			
		
		setBackground(Color.ORANGE);
		setBounds(50, 50, 268, 220);		
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setLayout(null);
		
		JLabel lblNombre = new JLabel(x.GetName());
		lblNombre.setBounds(44, 11, 178, 14);
		lblNombre.setForeground(Color.WHITE);
		lblNombre.setBackground(Color.WHITE);
		add(lblNombre);
		
		JPanel panel = new JPanel();
		
		panel.setBounds(10, 63, 180, 104);
		
		
		
		textField = new JTextField();
		textField.setBounds(32, 220, 138, 54);
		textField.setText(x.GetDescription());
		add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(32, 41, 208, 101);
		add(panel_1);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(28, 156, 212, 54);
		textArea.setText(x.GetDescription());
		add(textArea);

	}
	
	
	
	
	
}
