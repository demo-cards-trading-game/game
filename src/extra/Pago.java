package extra;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class Pago extends JInternalFrame {

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
		setClosable(true);
		setVisible(true);

	}

}
