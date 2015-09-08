package data;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import demo.deck;
import data.LoadData;


public class deckCreator extends JInternalFrame implements ActionListener {
	public JPanel panel1,panel2;
	private JTextField nombred;
	JButton create;
	prueba lista;
	JInternalFrame  error;
	String Nombre=null;
	public deckCreator(int x , int y) 
	{
		
		getContentPane().setLayout(null);
		setBounds(x,y,800,600);
	
		setClosable(true);
		setIconifiable(false);
		addbackground(this);
	
		
		JTextPane txtpnTexto = new JTextPane();
		txtpnTexto.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		txtpnTexto.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 13));
		txtpnTexto.setText("You must enter a valid name for the deck if you ever want to create one");
		txtpnTexto.setBounds(0, 0, 200, 180);
		txtpnTexto.setEditable(false);
	
		
		error= new JInternalFrame("Error");
		error.setLayout(null);
		error.setBounds(500, 250, 200, 200);
		error.setClosable(true);
		error.setIconifiable(false);
		error.setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		error.add(txtpnTexto);
		add(error);
		
		
		
		//pane.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		error.setVisible(false);
		setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		panel2=new JPanel();
		panel2.setBounds(400,0,400,600);
		panel2.setOpaque(false);
		
		
		panel1=new JPanel();
		
		
		panel1.setBounds(0,0,400,600);
		panel1.setOpaque(false);
		
		getContentPane().add(panel1);
		panel1.setLayout(null);
		
		JButton btnStarter = new JButton("Use a starter deck");
		btnStarter.setBounds(85, 42, 226, 53);
		panel1.add(btnStarter);
		
		JButton btnSlot = new JButton("Slot 1");
		btnSlot.setEnabled(false);
		btnSlot.setBounds(85, 189, 226, 53);
		panel1.add(btnSlot);
		
		JLabel lblLoadADeck = new JLabel("Load a deck ");
		lblLoadADeck.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoadADeck.setBounds(150, 147, 92, 27);
		panel1.add(lblLoadADeck);
		
		JButton btnSlot_1 = new JButton("Slot  2");
		btnSlot_1.setEnabled(false);
		btnSlot_1.setBounds(83, 290, 228, 53);
		panel1.add(btnSlot_1);
		
		JButton btnNewButton_1 = new JButton("Slot 3");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(85, 390, 226, 53);
		panel1.add(btnNewButton_1);
		getContentPane().add(panel2);
		panel2.setLayout(null);
		
		
		create = new JButton("Create");
		create.setBounds(104, 132, 173, 48);
		panel2.add(create);
		
		nombred = new JTextField();
		nombred.setBounds(145, 72, 86, 20);
		panel2.add(nombred);
		nombred.setColumns(10);
		
		JLabel lblCreateANew = new JLabel("create a new Deck");
		lblCreateANew.setFont(new Font("Showcard Gothic", Font.PLAIN, 11));
		lblCreateANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateANew.setBounds(124, 24, 123, 20);
		panel2.add(lblCreateANew);
		
		JLabel lblSetAName = new JLabel("set a name 4 it ");
		lblSetAName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetAName.setBounds(145, 86, 92, 35);
		panel2.add(lblSetAName);
		create.addActionListener(this);
		
	}
	
	 void addbackground(JInternalFrame jfm)
	 {
	   try {
	      jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("800x600.jpg")))));
	     } 
	   catch (IOException e) {
	      e.printStackTrace();
	     }
	 
	 }
	 
	 public void actionPerformed(ActionEvent e)
	 {
		  if (e.getSource()== create)
		 {
			 Nombre=nombred.getText(); 
			 
			if(!Nombre.isEmpty()){  
			addbackground(this);
			 lista=new prueba();
			 add(lista);
			 repaint();
			 System.out.println(Nombre+"pene");
			}else{
				

				if(!error.isVisible())
			  	{
			  	
				error.setVisible(true);
				
			  	}
				
				
				
			}
			 
		 }
		 
		 
	 }
}
