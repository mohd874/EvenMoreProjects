/*
 * ObjectType.java
 *
 * Created on July 3, 2003, 12:43 PM
 */

package IsometricGame.Entity;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * @author  jgauci
 */
public class DoodadType implements java.io.Serializable {
     public BufferedImage image;
     public boolean passable,seeThrough,bulletProof,indestructable,climbable,fallable;
     
     /** Creates a new instance of ObjectType */
     public DoodadType(BufferedImage image,int attributes) {
	  this.image = image;
          System.out.println("ATTS " + attributes);
	  passable = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
	  seeThrough = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
	  bulletProof = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
	  indestructable = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
	  climbable = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
	  fallable = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
	  System.out.println("DOODAD CREATED: Climbable: " + climbable + " see-through: " + seeThrough);
     }
     
}
