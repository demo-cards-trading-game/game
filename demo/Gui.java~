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
public class Gui// implements ActionListener
{
 JPanel jp1,jp2,jp3;
 JButton b1;
 JLabel l1;
 public Gui()
 {  
  
   JTextArea jtextarea = new JTextArea();   
   JButton jb1,jb2;
   JFrame jfm=new JFrame("GUI");
   jfm.setLayout(null);
   try {
      jfm.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("test.jpg")))));
     } 
   catch (IOException e) {
      e.printStackTrace();
     }

   /***********pruebas******************/
   JTextField textField = new JTextField(12);
    
   jtextarea.setBounds(25, 15, 250, 90);
   jtextarea.setText("Esto es un control \r\n JTextArea \r\n de varias líneas");
   jtextarea.setEditable(false);
   jtextarea.setText("Hola");
   jfm.add(jtextarea, BorderLayout.CENTER);
   /*********************************/
   /**************pruebas con label y botones **************/
    l1=new JLabel("Here is a button");
    l1.setBounds(100,200,300,30); //esto se mueve como horizontal vertical 100= h 200=v
    jfm.add(l1);
    b1=new JButton("I am a button");
    b1.setBounds(100,250,100,30);
    
    jfm.add(b1);
   /***********************************************************/
   jfm.setLocation(100, 50);
   jfm.setResizable(false);
 
   jfm.setSize(800,600);
   jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
   jfm.setVisible(true);
 }

 public static void main(String[] args)
 {
 Gui Juego=new Gui();

 }

}