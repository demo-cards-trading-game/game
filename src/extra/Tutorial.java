package extra;

import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.SwingConstants;

public class Tutorial extends JPanel implements ActionListener  {

	public JButton ok,ok2,ok3,cancel;
	public AnimatedButton animation;
	JLabel clickon;
	public JLabel lblSms ;
	public JPanel panel;
	public Tutorial() 
	{
		setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new LineBorder(new Color(0, 191, 255))));
		setOpaque(false);
		setBounds(0,0,1024,768);
		setLayout(null);
		
		 ok=new JButton("Continue");
		 ok.setSelectedIcon(null);
		 ok.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		 ok.setForeground(new Color(0, 0, 0));
		 ok.setBackground(Color.WHITE);
		 ok.setBounds(152,142,100,40);
		 ok.addActionListener(this);
		 ok2=new JButton("Continue");
		 ok2.setSelectedIcon(null);
		 ok2.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		 ok2.setForeground(new Color(0, 0, 0));
		 ok2.setBackground(Color.WHITE);
		 ok2.setBounds(150,120,100,40);
		 ok2.addActionListener(this);
		 ok3=new JButton("Continue");
		 ok3.setSelectedIcon(null);
		 ok3.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		 ok3.setForeground(new Color(0, 0, 0));
		 ok3.setBackground(Color.WHITE);
		 ok3.setBounds(152-50,142,100,40);
		 ok3.addActionListener(this);
		 cancel=new JButton("Cancel");
		 cancel.setSelectedIcon(null);
		 cancel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 14));
		 cancel.setForeground(new Color(0, 0, 0));
		 cancel.setBackground(Color.WHITE);
		 cancel.setBounds(152+50,142,100,40);
		 cancel.addActionListener(this);
		 animation=new AnimatedButton(0,0);
		 add(animation);
		 
		panel= new JPanel();
		 panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 4), new MatteBorder(3, 2, 1, 2, (Color) new Color(255, 255, 255))));
		 panel.setBackground(new Color(0, 128, 128));
		 panel.setBounds(519, 177, 397, 193);
		 panel.add(ok);
		 panel.setLayout(null);
		 add(panel);
		 
		 lblSms = new JLabel("\"ACA VA EL SMS AL USUARIO\"");
		 lblSms.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 16));
		 lblSms.setHorizontalAlignment(SwingConstants.CENTER);
		 lblSms.setForeground(new Color(0, 0, 0));
		 lblSms.setBackground(new Color(0, 128, 0));
		 lblSms.setBounds(42, 35, 320, 40);
		 panel.add(lblSms);
	
		
		
		this.setVisible(false);
		
	}
	public void draw()
	{
		animation.setLocation(910,420);
		panel.setLocation(330, 201);
		lblSms.setText("click on DECK button to get a card");
		animation.anim();
		this.setVisible(true);
	
	
	}
	
	public void barrier()
	{
		animation.setLocation(170,345);
		
		lblSms.setText("click on a barrier to get the card");
		animation.anim();
		this.setVisible(true);
	}
	
	public void Action()
	{
		animation.setLocation(170,375);
		lblSms.setText("now you can play a card , click on the card you are interested in then you will see a menu");
		animation.anim();
		//ok2.setEnabled(false);
		panel.remove(ok);
		panel.add(ok2);
		this.setVisible(true);
		
		
	}
	public void Action2()
	{
		animation.setLocation(170,375);
		lblSms.setText("done , now click on the play button");
		animation.anim();
		this.setVisible(true);
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if( e.getSource()==ok2)
		{
			System.out.println("ok2");
			animation.stop();
			
			panel.remove(ok2);
			panel.add(ok);
			animation.stop();
			this.setVisible(false);
			
			Action2();
			
		}
		
	}
	public void play()
	{
		panel.remove(ok);
		panel.add(ok3);
		panel.add(cancel);
		ok3.setEnabled(false);
		animation.setLocation(20,180);
		lblSms.setText("SELECT POWERS TO DRAIN");
		animation.anim();
		this.setVisible(true);
	}
	public void end()
	{
			animation.setLocation(650,150);
		panel.setLocation(230, 201);
		lblSms.setText("click on end button to end this turn");
		animation.anim();
		this.setVisible(true);
		
	}
}
