package demo;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Reverse extends SmallCard{
	Graphics2D g2d;
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public Reverse(boolean bocabajo,Card x ) throws IOException 
	{
		super(bocabajo,x);
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		int  x = this.getWidth() / 2;
		int y = this.getHeight() / 2;
		g2d.rotate(-Math.PI, x, y);
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