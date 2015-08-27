package demo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import demo.CardGui;
import data.LoadData;
import java.util.Random;
import demo.HandGui;
public class Gui extends JFrame implements ActionListener
 {
 JPanel jp1,jp2,jp3;
 JButton b1,b2;
 JLabel l1,demo;
 JTextArea text;
  String Nombre1;//nombre del jugador1
  private JMenuBar mb;
  private JMenu menu1,menu2;
  private JMenuItem mi1,mi2,mi3;
  static LoadData data;
  private JPanel contentPane;
  private HandGui mano,mano2;
  public Gui()
 {  
	  
	  super("Gui");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 1024, 768);
  contentPane = new JPanel();
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  contentPane.setLayout(null);
  setContentPane(contentPane);
  setVisible(true);
    
   
      try {
  data=new LoadData();
 } catch (IOException e1) {
  // TODO Auto-generated catch block
  e1.printStackTrace();
 }
   
      
   JButton jb1,jb2;
   

   setLayout(new CardLayout(0, 0));
   

   /***********pruebas******************/
   
   mb=new JMenuBar();
   setJMenuBar(mb);
   menu1=new JMenu("Opciones");
   mb.add(menu1);
   menu2=new JMenu("resoucion");
   menu1.add(menu2);
   mi1=new JMenuItem("900,650(default)");
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
    b1.setBorder(BorderFactory.createEmptyBorder());
    
    b1.addActionListener(this);
    add(b1);
    

  
    
   
    
    demo=new JLabel("<html><font color='white'>Demo version: 0.000001 </font></html>");
    demo.setBounds(740,560,300,30); //esto se mueve como horizontal vertical 100= h 200=v
    this.add(demo);
 
    
   /*********************************/

   
   
   setLocation(100, 50);
   setResizable(false);
 
   
   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
   setVisible(true);
   
 }
 
  public void actionPerformed(ActionEvent e) {
 
   if (e.getSource()==b1)//cuando se le da click al boton 1
        {
          
          
          Nombre1=text.getText();//guarda el nombre del jugador en Nombre1
          contentPane=new JPanel();
          contentPane.setBackground(Color.BLACK);
          contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
          contentPane.setLayout(null);
          contentPane.removeAll();
       
          addjlabel2(this);
          b2=new JButton("Show");
          b2.setBackground(Color.white);
          b2.setBorder(null);
          b2.setBounds(500,350,60,30);
          b2.setBorder(BorderFactory.createEmptyBorder());
         

          b2.addActionListener(this);
          mano=new HandGui(100,450);
         mano2=new HandGui(100,0);
          contentPane.add(b2);
          contentPane.add(mano);
          setContentPane(contentPane);
          setVisible(true);
          
          

          
        }
   if (e.getSource()==b2)//cuando se le da click al boton 1
       {
	 contentPane.removeAll();
     JPanel painel3;
     JPanel painel5;
   
    
    Random randomGenerator = new Random();
    int randomInt = randomGenerator.nextInt(15);
    mano.draw(LoadData.Data.Consultar(randomInt));
    	randomInt = randomGenerator.nextInt(15);
    	mano2.draw(LoadData.Data.Consultar(randomInt));
    contentPane.add(mano2);
    contentPane.add(mano);
    contentPane.add(b2);
    contentPane.repaint();
    	
   
   
      this.setVisible(true);
      this.setVisible(true);
      
  }
        if (e.getSource()==mi1) {
          setSize(900,650);
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
