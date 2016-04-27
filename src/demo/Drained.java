package demo;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Drained extends JLayeredPane{
	/**
	 * Create the panel.
	 */
	JLabel lblVolatile ,lblWater,lblWind,lblEarth,lblSolar,lblPower,fondo;
	public int water,solar,Volatile,wind,earth,power;

	public Drained(int x , int y,String name)
	{
		setBackground(new Color(51, 204, 0));
		setBounds(x-20,y+100,196,384);
		setLayout(null);
		water=solar=Volatile=wind=earth=power=100;
		
		//add(lblVolatile);
		
		try {
			fondo = new JLabel(new ImageIcon(ImageIO.read(new File("ele.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void setwp()
	{
		water++;
	
		setVisible(true);
		repaint();
	}

	void drainwp()
	{
		water--;
		lblWater.setText("Water "+water);
		setVisible(true);
		repaint();
	}
	void play(int n)
	{
		
	}
	void reset()
	{
		
		
	}
	void set()
	{
		power++;

		setVisible(true);
		repaint();
	}
	void drain()
	{
		power--;
		lblPower.setText("Power "+ power);
		setVisible(true);
		repaint();
	}
	void drain(int n)
	{
		power=power-n;
		
		setVisible(true);
		repaint();
	}
	void set(int n)
	{
		power=power-n;
		setVisible(true);
		repaint();
	}
	void token()
	{
		Volatile++;
		
		setVisible(true);
		repaint();
	}
}