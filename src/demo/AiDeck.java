package demo;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AiDeck extends DeckGui {

	Graphics2D g2d;
	/**
	 * Create the panel.
	 */
	public AiDeck(int x , int y) {
		
		super(x,y);
		Deck=new deck();
		 
		 try {
			Deck.Load("resources/siren.in");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel.setSize(53, 68);
		btnNewButton.setSize(43, 43);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_2.setSize(43, 43);
		btnNewButton_1.setSize(43, 43);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textField.setLocation(10, 242);
		lblTheFallen.setLocation(10, 11);
		lblForgotten.setLocation(10, 96);
		lblDeck.setLocation(20, 175);
		btnNewButton.setLocation(20, 188);
		btnNewButton_1.setLocation(20, 36);
		btnNewButton_2.setLocation(20, 121);
		panel.setLocation(116, 11);
		repaint();
	}
	
}
