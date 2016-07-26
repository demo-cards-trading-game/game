package demo;

import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by luisc on 21/7/2016.
 */
public class GuiTest extends JFrame implements ActionListener {

    FieldGui fieldGui;

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
        frame.setLayout(null);
        frame.setBounds(0,0,600,600);

        fieldGui = new FieldGui(0,0);
        fieldGui.setBounds(20,80,500,500);
        frame.setContentPane(fieldGui);

        frame.setVisible(true);
        Card card = new Card();
        card.setSource("Water");

        JButton button = new JButton("deleteCard");
        button.setBounds(200,500,100,50);
        button.addActionListener(this);
        frame.getContentPane().add(button, BorderLayout.CENTER);

        try {
            fieldGui.addCard(new SmallCard(card,0,0),1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
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

    @Test
    public  void testFieldGui(){
        FieldGui fieldGui = new FieldGui(0,0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fieldGui.removeCard(1);
        repaint();
    }
}