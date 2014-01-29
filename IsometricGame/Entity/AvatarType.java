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
public class AvatarType implements java.io.Serializable {
     BufferedImage leftImage,rightImage;
     
     /** Creates a new instance of ObjectType */
     public AvatarType(BufferedImage leftImage, BufferedImage rightImage) {
	  this.leftImage = leftImage;
	  this.rightImage = rightImage;
     }
     
}
