package demo;

import javax.swing.JPanel;

public class Previewpane extends JPanel {

	/**
	 * Create the panel.
	 */
	public Previewpane() {
		this.setBounds(770,120,250,350);
		this.setOpaque(false);
		setLayout(null);
		
	}
	public void addCard(BigCard recieved)
	{
		removeAll();
		add(recieved);
		recieved.setLocation(5,15);
		setVisible(true);
		repaint();
		
	}
	
	public void Remove()
	{
		removeAll();
		repaint();
		
	}

}
