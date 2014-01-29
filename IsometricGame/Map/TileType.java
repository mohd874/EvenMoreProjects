/*
 * Tile.java
 *
 * Created on June 17, 2003, 1:45 PM
 */

package IsometricGame.Map;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * @author  jgauci
 */
public class TileType implements java.io.Serializable {
     public boolean passable,seeThrough;
     public Image tileImage;
     public final static byte NOTILE=-127;
     
     public TileType(BufferedImage tileMap,int attributes) {
	  tileImage = tileMap;
	  passable = (attributes & 1) > 0 ? true : false;
	  attributes = attributes >> 1;
	  seeThrough = (attributes & 1) > 0 ? true : false;
	  System.out.println("TILE CREATED: Passable: " + passable + " see-through: " + seeThrough);
     }
     
}
