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
		water=solar=Volatile=wind=earth=power=0;
		lblVolatile = new JLabel("Volatile");
		lblVolatile.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 15));
		lblVolatile.setForeground(new Color(51, 204, 51));
		lblVolatile.setBounds(50, 11, 97, 27);
		//add(lblVolatile);
		
		try {
			fondo = new JLabel(new ImageIcon(ImageIO.read(new File("ele.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void setwp()
	{
		water++;
		lblWater.setText("Water "+water);
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
		lblPower.setText("Power "+ power);
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
		lblPower.setText("Power "+ power);
		setVisible(true);
		repaint();
	}
	void set(int n)
	{
		power=power-n;
		lblPower.setText("Power "+ power);
		setVisible(true);
		repaint();
	}
	void token()
	{
		Volatile++;
		lblVolatile.setText("Volatile "+Volatile);
		setVisible(true);
		repaint();
	}
}