package extra;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class rain extends JPanel
{
    private float mGravity = 9.8f;

    private int mRepaintTimeMS = 20;
    private double mDdropInitialVelocity = 5;
    private Color mColor=Color.white;
//*********************************************

    private ArrayList<Rain> rainV;
    private ArrayList<Drop> dropV;

    public rain() {
        rainV = new ArrayList<>();
        dropV = new ArrayList<>();
        UpdateThread mUpdateThread = new UpdateThread();
        mUpdateThread.start();
    }

    public void stop() {
        UpdateThread mUpdateThread = null;
        mUpdateThread.stopped=true;
    }

    public int getHeight() {
        return this.getSize().height;
    }

    public int getWidth() {
        return this.getSize().width;
    }

    private class UpdateThread extends Thread {
        public volatile boolean stopped=false;
        @Override
        public void run() {
            while (!stopped) {
                rain.this.repaint();
                try {
                    Thread.sleep(mRepaintTimeMS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        float mRainWidth = 0.5f;
        g2.setStroke(new BasicStroke(mRainWidth));
        g2.setColor(mColor);

        //DRAW DROPS
        Iterator<Drop> iterator2 = dropV.iterator();
        while (iterator2.hasNext()) {
            Drop drop = iterator2.next();
            drop.update();
            drop.draw(g2);

            if (drop.y >= getHeight()) {
                iterator2.remove();
            }
        }

        //DRAW RAIN
        Iterator<Rain> iterator = rainV.iterator();
        while (iterator.hasNext()) {
            Rain rain = iterator.next();
            rain.update();
            rain.draw(g2);

            if (rain.y >= getHeight()) {
                //create new drops (2-8)
                long dropCount = 1 + Math.round(Math.random() * 4);
                for (int i = 0; i < dropCount; i++) {
                    dropV.add(new Drop(rain.x, getHeight()));
                }
                iterator.remove();
            }
        }

        //CREATE NEW RAIN
        double mRainChance = 0.99;
        if (Math.random() < mRainChance) {
            rainV.add(new Rain());
        }
    }


    class Rain {
        float x;
        float y;
        float prevX;
        float prevY;

        public Rain() {
            Random r = new Random();
            x = r.nextInt(getWidth());
            y = 0;
        }

        public void update() {
            prevX = x;
            prevY = y;
            float mWind = 2.05f;
            x += mWind;
            y += mGravity;
        }

        public void draw(Graphics2D g2) {
            Line2D line = new Line2D.Double(x, y, prevX, prevY);
            g2.draw(line);
        }
    }

    private class Drop {
        double x0;
        double y0;
        double v0; //initial velocity
        double t;  //time
        double angle;
        double x;
        double y;

        public Drop(double x0, double y0) {
            super();
            this.x0 = x0;
            this.y0 = y0;

            v0 = mDdropInitialVelocity;
            angle = Math.toRadians(Math.round(Math.random() * 180)); //from 0 - 180 degrees
        }

        private void update() {
            t += mRepaintTimeMS / 100f;
            x = x0 + v0 * t * Math.cos(angle);
            y = y0 - (v0 * t * Math.sin(angle) - mGravity * t * t / 2);
        }

        public void draw(Graphics2D g2) {
            double mDropDiam = 4;
            Ellipse2D.Double circle = new Ellipse2D.Double(x, y, mDropDiam, mDropDiam);
            g2.fill(circle);
        }
    }
}