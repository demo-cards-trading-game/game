package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Gui extends JFrame implements ActionListener
 {
 JPanel jp1,jp2,jp3;
 JButton b1;
 JLabel l1,demo;
 JTextArea text;
  String Nombre1;//nombre del jugador1
  private JMenuBar mb;
  private JMenu menu1,menu2;
  private JMenuItem mi1,mi2,mi3;
  public Gui()
 {  
      super("Gui");
   
   

   JButton jb1,jb2;
   

   setLayout(null);
   

   /***********pruebas******************/
   
   mb=new JMenuBar();
   setJMenuBar(mb);
   menu1=new JMenu("Opciones");
   mb.add(menu1);
   menu2=new JMenu("resoucion");
   menu1.add(menu2);
   mi1=new JMenuItem("640x480");
   mi1.addActionListener(this);
   menu2.add(mi1);
   mi2=new JMenuItem("800x600");
   mi2.addActionListener(this);
   menu2.add(mi2);
   mi3=new JMenuItem("1024x768");
   mi3.addActionListener(this);
   menu2.add(mi3);         
   
/***************************************/
   addbackgound(this);
   addjlabel1(this);
  // addtext1(this,text);
   text = new JTextArea();   
   text.setBounds(380, 450, 90, 20);
   text.setEditable(true);
   add(text);
   
   
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
	
	  if (e.getSource()==b1)//cuando se le da click al boton 1
        {
          
          
          Nombre1=text.getText();//guarda el nombre del jugador en Nombre1
           
           try {
             
             setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("test1.jpg")))));
              
              addjlabel2(this);
              setVisible(true);
             } 
           catch (IOException a) {
             a.printStackTrace();
           }
           
        }
       
        if (e.getSource()==mi1) {
        	 setSize(640,480);
        }
        if (e.getSource()==mi2) {
        	 setSize(800,600);
        }
        if (e.getSource()==mi3) {
        	 setSize(1024,768);
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

 
 
void addjlabel2(JFrame jfm)
 {
 l1=new JLabel("<html><font color='black'>welcome "+ Nombre1+" .</font></html>");
 l1.setBounds(380,20,300,30); //esto se mueve como horizontal vertical 100= h 200=v
 jfm.add(l1);
 
 
 
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
   
   jfm.add(text);
 }
 
 /************************/
 
 public static void main(String[] args)
 {
 Gui Juego=new Gui();
 
 }

}
