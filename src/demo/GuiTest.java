package demo;

import org.junit.Test;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by luisc on 21/7/2016.
 */
public class GuiTest implements ActionListener {
    @Test
    public void initData() {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("hola");
            //con esto puedes pausar el proceso del wait
            Utils.flag = true;
        });
        t1.start();
        Utils.waitSecondsWithFlag(10);
        System.out.println("fin");
    }

    @Test
    public void testFrame(){
        JFrame frame = new JFrame("FrameDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button = new JButton("aceptar");
        button.addActionListener(this);
        frame.getContentPane().add(button, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        Utils.waitWithFlag();
    }

    @Test
    public void testBreak(){
        Thread t1 = new Thread(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }
            System.out.println("hola");
            Utils.waitSecondsWithFlag(5);
            //con esto puedes pausar el proceso del wait
            Utils.flag = true;
        });
        t1.start();
        System.out.println("hola2");
        Utils.waitSecondsWithFlag(10);
        System.out.println("fin");
    }

    @Test
    public void testPhases(){
        Phases phases = new Phases(0,0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Utils.flag = true;
    }
}