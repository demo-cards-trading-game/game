package extra;

/*
Swing Hacks Tips and Tools for Killer GUIs
By Joshua Marinacci, Chris Adamson
First Edition June 2005  
Series: Hacks
ISBN: 0-596-00907-0
Pages: 542
website: http://www.oreilly.com/catalog/swinghks/
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.LineMetrics;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Rlabel extends JLabel {

private int tracking;

public Rlabel(String text, int tracking) {
super(text);
this.tracking = tracking;
}

private int left_x, left_y, right_x, right_y;

private Color left_color, right_color;

public void setLeftShadow(int x, int y, Color color) {
left_x = x;
left_y = y;
left_color = color;
}

public void setRightShadow(int x, int y, Color color) {
right_x = x;
right_y = y;
right_color = color;
}

public Dimension getPreferredSize() {
String text = getText();
FontMetrics fm = this.getFontMetrics(getFont());

int w = fm.stringWidth(text);
w += (text.length() - 1) * tracking;
w += left_x + right_x;

int h = fm.getHeight();
h += left_y + right_y;

return new Dimension(w, h);
}

public void paintComponent(Graphics g) {
((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

char[] chars = getText().toCharArray();

FontMetrics fm = this.getFontMetrics(getFont());
int h = fm.getAscent();
LineMetrics lm = fm.getLineMetrics(getText(), g);
g.setFont(getFont());

int x = 0;

for (int i = 0; i < chars.length; i++) {
  char ch = chars[i];
  int w = fm.charWidth(ch) + tracking;

  g.setColor(left_color);
  g.drawString("" + chars[i], x - left_x, h - left_y);

  g.setColor(right_color);
  g.drawString("" + chars[i], x + right_x, h + right_y);

  g.setColor(getForeground());
  g.drawString("" + chars[i], x, h);

  x += w;
}

}

public static void main(String[] args) {
Rlabel label = new Rlabel("www.java2s.com", 0);
label.setRightShadow(1, 1, Color.black);
label.setLeftShadow(1, 1, Color.black);
label.setForeground(Color.blue);
label.setFont(label.getFont().deriveFont(140f));

label.setRightShadow(5,5,Color.black);
label.setLeftShadow(-3,-3, new Color(0xccccff));
label.setForeground(new Color(0x8888ff));
label.setFont(label.getFont().deriveFont(140f));


JFrame frame = new JFrame("RichJLabel hack");
frame.getContentPane().add(label);
frame.pack();
frame.setVisible(true);
}

public static void p(String str) {
System.out.println(str);
}
}

   