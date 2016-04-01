package extra;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import java.awt.Color;

public class Pago extends JInternalFrame {
	/**
	 * @wbp.nonvisual location=173,131
	 */
	private final JLabel label = new JLabel("Volatile 3");
	public JLabel water,wind,power,earth;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Pago() {
		
		setBounds(100, 100, 800, 600);
		try {
			setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("pago.jpg")))));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		getContentPane().setLayout(null);
		label.setBounds(new Rectangle(150, 95, 200, 50));
		label.setIgnoreRepaint(true);
		label.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 17));
		getContentPane().add(label);
		
		water= new JLabel("Water 1");
		water.setForeground(new Color(0, 102, 255));
		water.setFont(new Font("Elephant", Font.BOLD | Font.ITALIC, 17));
		water.setBounds(new Rectangle(570, 95, 200, 50));
		getContentPane().add(water);
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		scrollBar.setBounds(268, 106, 19, 27);
		getContentPane().add(scrollBar);
		JScrollBar scrollBarw = new JScrollBar();
		scrollBarw.setForeground(new Color(0, 102, 255));
		scrollBarw.setOrientation(JScrollBar.HORIZONTAL);
		scrollBarw.setBounds(670, 106, 19, 27);
		getContentPane().add(scrollBarw);
		setClosable(true);
		setVisible(true);

	}

}
