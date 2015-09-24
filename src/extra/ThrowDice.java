package extra;

import java.util.Random;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * ThrowDice.java
 * Created by Stijn Strickx on Feb. 16 2008
 * Copyright 2008 Stijn Strickx, All rights reserved
 */
class ThrowDice extends TimerTask {
    private JLabel dice1;
    private JLabel dice2;
    private JLabel text;
    private Random rg = new Random();
    private IconGetter getter;
    private int count;

    public ThrowDice(JLabel dice1, JLabel dice2, JLabel text) {
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.text = text;
        count = 25;
        getter = new IconGetter();
    }
    public void run(){
        if(count > 0){
            count --;
            int num1 = rg.nextInt(6);
            int num2 = rg.nextInt(6);
            Icon icon1 = getter.getIcon("d" + (num1+1) + ".png");
            Icon icon2 = getter.getIcon("d" + (num2+1) + ".png");
            dice1.setIcon(icon1);
            dice2.setIcon(icon2);
            if(num1>=num2){
            	 text.setText("1");
            }else{
            	 text.setText("2");
            }
        }
        else{
        	
            this.cancel();
        }
    }

}
