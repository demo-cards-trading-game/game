package extra;

import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class AnimatedButton extends JPanel {

	JPanel panel;
	public boolean moving;

	public AnimatedButton(int x, int y) {

		setBounds(x, y, 100, 200);
		setOpaque(false);
	
		panel = new JPanel();
		panel.setBounds(0, 0, 100, 100);
		panel.setOpaque(false);
		moving = false;

		try {
			panel.add(new JLabel(new ImageIcon(ImageIO.read(new File("select.png")))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(panel);
		
		

	}

	public void anim() {
		
		Thread t = new Thread(new Runnable() {

			public void start() {
				this.start();
			}

			public void run() {
				int i = 0;
				moving = true;
				
				while (moving) {
					while (i <= 100) {
						panel.setLocation(0,i);
						i++;
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();

						}

					}
					while (i >= 0) {
						panel.setLocation(0,i);
						i--;
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();

						}

					}

				}

			}
		});
		t.start();
	}
	
	public void stop()
	{
		moving=false;
		
		
		
	}

}