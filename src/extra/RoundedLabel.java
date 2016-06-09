package extra;

import javax.swing.*;
import java.awt.*;

public class RoundedLabel extends JLabel {

    public RoundedLabel(ImageIcon k) {
        super(k);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        (new RoundedPanel()).paintComponent(g);
    }
}