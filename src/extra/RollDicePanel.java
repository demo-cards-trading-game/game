package extra;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;

public class RollDicePanel extends JPanel implements ActionListener{
 public Die _leftDie;  
 public Die _rightDie;
 public JButton rollButton;

 public JLabel text = new JLabel(""); //donde deberia ir el resultado
 public JLabel dice1;
 public JLabel dice2;
 private Random rg = new Random();
 public  String aux, aux2;
 public int count=25;
 
 RollDicePanel() throws IOException {
	 this.setOpaque(false);
     //... Create the dice
    /* _leftDie  = new Die();
     _leftDie.setBounds(5, 51, 244, 244);
     _rightDie = new Die();
     _rightDie.setBounds(350, 51, 244, 244);
     */
    

		dice1 = new JLabel(new ImageIcon(ImageIO.read(new File("d1.png"))));


	// dice1.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
	 dice1.setBounds(-66, 51, 244, 244);
     dice2 = new JLabel(new ImageIcon(ImageIO.read(new File("d1.png"))));
    // dice2.setBorder(new LineBorder(new Color(0, 0, 0), 5, true));
     dice2.setBounds(542, 51, 244, 244);
     rollButton = new JButton("Let them roll");
     rollButton.setBounds(71, 317, 440, 41);
     rollButton.setFont(new Font("Sansserif", Font.PLAIN, 24));
     
    
     
    
     setLayout(null);
     this.add(rollButton);
    // this.add(_leftDie);
    // this.add(_rightDie);
     this.add(dice1);
     this.add(dice2);
     rollButton.addActionListener(this);
     
     //rollButton.addActionListener(new ButtonListener(dice1, dice2, text));
     this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		correr();
		rollButton.enable(true);
		/*Thread t = new Thread(new Runnable(){
			
			public void start(){
				this.start();
			}
			
			public void run(){

		        try {
		            Thread.sleep(1000); 
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
				System.out.println("soy un hilo");
			}
		});
		t.start();*/
	}
	 public void correr(){
		 Timer timer = new Timer();
		 timer.scheduleAtFixedRate(new TimerTask(){	        		
			 public void run(){
	     		 if(count > 0){
	     	            count --;
	     	            int num1 = rg.nextInt(6);
	     	            int num2 = rg.nextInt(6);
	     	         
						try {
							   Icon icon1;
			     	           Icon icon2;
							icon1 = new ImageIcon(ImageIO.read(new File("d"+(num1+1)+".png")));
							 dice1.setIcon(icon1);
							 icon2 = new ImageIcon(ImageIO.read(new File("d"+(num2+1)+".png")));
						      dice2.setIcon(icon2);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	     	         
					
	     	      
	     	            
	     	            
	     	            if(num1>num2){
	     	            	 text.setText("1");
	     	            }else
	     	            {
	     	            	if(num1<num2){
	     	            		text.setText("2");
	     	            	}
	     	            	else
	     	            	{
		     	            	 text.setText("3");	     	            		
	     	            	}

	     	            }
	     	            /*aux=((ImageIcon)dice1.getIcon()).getDescription();
	     	            aux2=((ImageIcon)dice2.getIcon()).getDescription();
	     	            System.out.println(aux.substring(aux.length()-5, aux.length()-4)+" "+aux2.substring(aux2.length()-5, aux2.length()-4)+" "+text.getText());*/
	     	        }
	     	        else{
	     	        	
	     	            this.cancel();
		     	   }
	     	}
	     }, 0, 100);
	 }
}
