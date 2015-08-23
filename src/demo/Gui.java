package demo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
public class Gui extends JFrame implements ActionListener
 {
 JPanel jp1,jp2,jp3;
 JButton b1;
 JLabel l1,demo;
 JTextArea text;
 String Nombre1;//nombre del jugador1
 public Gui()
 {  
      super("Gui");
   
   

   JButton jb1,jb2;
   

   setLayout(null);
   

   /***********pruebas******************/
   
    
   

   addbackgound(this);
   addjlabel1(this);
   addtext1(this,text);
    b1=new JButton("Play");
    b1.setBackground(Color.white);
    b1.setBorder(null);
    b1.setBounds(280,420,60,30);
    b1.addActionListener(this);
    add(b1);
  
    demo=new JLabel("<html><font color='white'>Demo version: 0.000001 </font></html>");
    demo.setBounds(740,560,300,30); //esto se mueve como horizontal vertical 100= h 200=v
    this.add(demo);
 
    
   /*********************************/

  
   
   setLocation(100, 50);
   setResizable(false);
 
   setSize(900,630);
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
   setVisible(true);
   
 }
 
  public void actionPerformed(ActionEvent e) {
        if (e.getSource()==b1)
        {
           try {
             
             setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("test1.jpg")))));
              setVisible(true);
            
           } 
           catch (IOException a) {
             a.printStackTrace();
           }
            //System.exit(0);
        }
  }
 /***********funciones*************/
 void addbackgound(JFrame jfm)
 {
   try {
      jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("test.jpg")))));
     } 
   catch (IOException e) {
      e.printStackTrace();
     }
 
 }

 
 
 
 
 void addjlabel1(JFrame jfm)
 {
 l1=new JLabel("<html><font color='white'>player's name.</font></html>");
 l1.setBounds(380,400,300,30); //esto se mueve como horizontal vertical 100= h 200=v
 jfm.add(l1);
 
 
 
 }
 void addtext1(JFrame jfm,JTextArea text)
 {
   text = new JTextArea();   
 
   text.setBounds(380, 450, 90, 20);
   text.setEditable(true);
   text.setText("Hola");
   jfm.add(text);
 }
 
 /************************/
 
 public static void main(String[] args)
 {
 Gui Juego=new Gui();

 }

}