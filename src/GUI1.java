import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;

public class GUI1 extends JFrame {

	public GUI1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		desktopPane.setBounds(101, 93, 246, 141);
		getContentPane().add(desktopPane);
	}
	
	 public static void main(String[] args)
	 {
	 GUI1 Juego=new GUI1();
	 
	 }
}
