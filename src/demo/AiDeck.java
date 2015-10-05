package demo;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AiDeck extends DeckGui {

	Graphics2D g2d;
	/**
	 * Create the panel.
	 */
	public AiDeck(int x , int y) {
		super(x,y);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textField.setLocation(115, 276);
		lblTheFallen.setLocation(10, 11);
		lblForgotten.setLocation(10, 134);
		lblDeck.setLocation(127, 163);
		btnNewButton.setLocation(115, 188);
		btnNewButton_1.setLocation(10, 36);
		btnNewButton_2.setLocation(10, 163);
		panel.setLocation(100, 11);
		repaint();
	}
	
}
