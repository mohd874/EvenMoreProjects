/*
 * WallType.java
 *
 * Created on July 1, 2003, 2:25 PM
 */

package IsometricGame.Map;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * @author  jgauci
 */
public class WallType implements java.io.Serializable {
	public Image leftImage,rightImage;
	public boolean passable,seeThrough,bulletProof,indestructable,scalable,door;
//	final static int PASSABLEMASK=32,SEETHROUGHMASK=16,BULLETPROOF=8,INDESTRUCTABLE=4,SCALABLE=2,DOOR=1;
        final public static byte NOWALL=-127;
	
	/** Creates a new instance of WallType */
	public WallType(BufferedImage leftImage,BufferedImage rightImage,int attributes) {
		this.leftImage = leftImage;
		this.rightImage = rightImage;
		passable = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
		seeThrough = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
		bulletProof = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
		indestructable = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
		scalable = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
		door = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
		System.out.println("Wall Created! Passable: " + passable);
	}
	
}
