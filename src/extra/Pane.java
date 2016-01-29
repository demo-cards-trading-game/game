package extra;

import javax.swing.JFrame;

// BorderLayout stuff
import java.awt.*;
import javax.swing.*;

// Canvas3D
import javax.media.j3d.Canvas3D;

// The Universe
import com.sun.j3d.utils.universe.SimpleUniverse;

// The BranchGroup
import javax.media.j3d.BranchGroup;

// For the Box
import com.sun.j3d.utils.geometry.Box;
import javax.vecmath.*;

// The directional light
import javax.media.j3d.DirectionalLight;

// For the bouding sphere of the light source
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;

// Transformgroup
import javax.media.j3d.TransformGroup;
import com.sun.j3d.utils.behaviors.mouse.*;

public class Pane extends JPanel {
  /**
   * The SimpleUniverse object
   */
  protected SimpleUniverse simpleU;

  /**
   * The root BranchGroup Object.
   */
  protected BranchGroup rootBranch;

  /**
   * Constructor that consturcts the window with the given name.
   * 
   * @param name
   *            The name of the window, in String format
   */
  
  public Pane()
  {
	  setLayout(null);
	  setBounds(100,100,400,200);
	  
	  rootBranch=new BranchGroup();
	
	    simpleU=new SimpleUniverse();
	    Appearance app = new Appearance();
	    Material mat = new Material();
	    mat.setDiffuseColor(new Color3f(1, 0, 0));
	    mat.setSpecularColor(new Color3f(0, 1, 0));
	    mat.setShininess(5.0f);

	    app.setMaterial(mat);
	    Box box = new Box(0.5f, 0.5f, 0.1f, app);
	    rootBranch.addChild(box);
	
	    simpleU.getViewingPlatform().setNominalViewingTransform();
	    simpleU.addBranchGraph(rootBranch);
  }   
}