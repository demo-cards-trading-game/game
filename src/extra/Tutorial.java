package extra;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class Tutorial extends JPanel implements ActionListener  {

	public JButton ok;
	public AnimatedButton animation;
	JLabel clickon;
	JLabel lblSms ;
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
		 ok.setBounds(150,120,100,40);
		 ok.addActionListener(this);
		 animation=new AnimatedButton(0,0);
		 add(animation);
		 
		 JPanel panel = new JPanel();
		 panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 4), new MatteBorder(3, 2, 1, 2, (Color) new Color(255, 255, 255))));
		 panel.setBackground(new Color(0, 128, 128));
		 panel.setBounds(330, 201, 397, 193);
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource()==ok)
		{
			animation.stop();
			
			this.setVisible(false);
			
			
		}
		
	}
	
	
	
}
