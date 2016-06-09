package demo;

import javax.swing.JPanel;

public class Previewpane extends JPanel {

	public Previewpane() {
		setBounds(770,120,250,350);
		setOpaque(false);
		setLayout(null);
	}

	public void addCard(BigCard recieved){
		removeAll();
		add(recieved);
		recieved.setLocation(5,15);
		setVisible(true);
		repaint();
	}
	
	public void Remove(){
		removeAll();
		repaint();
	}
}
