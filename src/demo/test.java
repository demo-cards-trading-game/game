package demo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class test extends JPanel {
	public test() {
	}

	
	/*
	@Override
    protected void paintComponent(Graphics g) {
        g.draw3DRect(x, y, width, height, raised);
    }
	**/
	 @Override
     public void paint(Graphics g)
     {
         int height = 200;
         int width = 120;
         g.setColor(Color.gray);
         g.drawLine(200, 0, 0, 100);
        
        
     }
	  public static void main(String[] args)
	  {
		  JFrame test= new JFrame("prueba");
	  JPanel basePanel = new JPanel();
	  basePanel = new test();
	  test.getContentPane().add(basePanel);
	  test.setVisible(true);
	  }
}