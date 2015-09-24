package extra;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * IconGetter.java
 * Created by Stijn Strickx on Feb. 16 2008
 * Copyright 2008 Stijn Strickx, All rights reserved
 */
public class IconGetter {
    
    public Icon getIcon(String name){
        return new ImageIcon(Dice.class.getResource("" + name));
    }
}
