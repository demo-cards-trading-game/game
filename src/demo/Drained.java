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
		add(lblVolatile);
		
		lblWater = new JLabel("Water");
		lblWater.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 15));
		lblWater.setForeground(new Color(51, 153, 255));
		lblWater.setBounds(10, 69, 79, 32);
		add(lblWater);
		
		lblWind = new JLabel("Wind");
		lblWind.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 15));
		lblWind.setForeground(new Color(255, 255, 255));
		lblWind.setBounds(10, 138, 79, 27);
		add(lblWind);
		
		lblPower = new JLabel("Power");
		lblPower.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 15));
		lblPower.setBounds(10, 193, 95, 32);
		add(lblPower);
		
		lblEarth = new JLabel("Earth");
		lblEarth.setForeground(new Color(153, 102, 102));
		lblEarth.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 15));
		lblEarth.setBounds(99, 69, 97, 32);
		add(lblEarth);
		
		lblSolar = new JLabel("Solar");
		lblSolar.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 15));
		lblSolar.setForeground(new Color(255, 102, 0));
		lblSolar.setBounds(101, 131, 95, 41);
		add(lblSolar);
		
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