package extra;

import java.awt.Container;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Dice.java
 * Created by Stijn Strickx on Feb. 16 2008
 * Copyright 2008 Stijn Strickx, All rights reserved
 */
public class Dice {
    public Dice(){

    }

    public void start() throws IOException {
        JLabel dice1 = new JLabel(new ImageIcon(ImageIO.read(new File("d1.png"))));
        JLabel dice2 = new JLabel(new ImageIcon(ImageIO.read(new File("d1.png"))));
        JButton button = new JButton("Throw");
        JLabel text = new JLabel("Total: 2");
        JFrame window = new JFrame("throw dice (c) Stijn Strickx");
        Container cp = window.getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(dice1);
        cp.add(dice2);
        cp.add(button);
        cp.add(text);
        window.pack();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}