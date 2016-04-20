package demo;

import javax.swing.*;
import java.awt.*;

public class RPanel extends JPanel{
	Graphics2D g2d;
	public RPanel()
	{
		super();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		int  x = this.getWidth() / 2;
		int y = this.getHeight() / 2;
		g2d.rotate(-Math.PI, x, y);
	}
}
