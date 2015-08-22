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
public class Gui// implements ActionListener
 {
 JPanel jp1,jp2,jp3;
 JButton b1;
 JLabel l1;
 JTextArea text;
 public Gui()
 {  
   
   
   

   JButton jb1,jb2;
   
   JFrame jfm=new JFrame("GUI");
   jfm.setLayout(null);
   

   /***********pruebas******************/
   
    
   
  
   addbackgound(jfm);
   addbutton1(jfm,b1);
   addjlabel1(jfm);
   addtext1(jfm,text);
   /*********************************/
 
    
  
   
   jfm.setLocation(100, 50);
   jfm.setResizable(false);
 
   jfm.setSize(800,600);
   jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
   jfm.setVisible(true);
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
 
 void addbutton1(JFrame jfm,JButton b1 )
 {
   b1=new JButton("Play");
    b1.setBackground(Color.white);
    b1.setBorder(null);
    b1.setBounds(280,420,60,30);
    
    jfm.add(b1);
 }
 
 void addjlabel1(JFrame jfm)
 {
 l1=new JLabel("<html><font color='white'>nombre jugador</font></html>");
 l1.setBounds(380,400,300,30); //esto se mueve como horizontal vertical 100= h 200=v
 jfm.add(l1);
 
 }
 void addtext1(JFrame jfm,JTextArea text)
 {
 JTextArea jtextarea = new JTextArea();   
 
   jtextarea.setBounds(380, 450, 90, 20);
   jtextarea.setEditable(true);
   jtextarea.setText("Hola");
   jfm.add(jtextarea);
 }
 
 /************************/
 
 public static void main(String[] args)
 {
 Gui Juego=new Gui();

 }

}