/*
 * LeftWall.java
 *
 * Created on July 2, 2003, 5:27 PM
 */

package IsometricGame.Map;

import IsometricGame.*;


import java.awt.*;

/**
 *
 * @author  jgauci
 */
public class LeftWall implements Wall, java.io.Serializable {
    public byte type;
    Point3D.Int location;
	Point renderPoint;
    
    /** Creates a new instance of LeftWall */
    public LeftWall(byte type,Point3D.Int location) {
        this.location = location;
        renderPoint = leftWallToMapCoord(location);
        this.type = type;
    }
    
    public java.awt.Point leftWallToMapCoord(Point3D.Int a) {
        return new Point(Theme.tileWidth/2*a.x-Theme.tileWidth/2*a.y
        -Theme.tileWidth/2,Theme.tileLength/2*a.x+Theme.tileLength/2*a.y -Theme.wallHeight+Theme.wallWidth/2-a.z*Theme.floorHeight);
    }
    
   /* public void draw(Graphics2D g,Point cameraLoc) {
        if(type!=IsometricMap.NOWALL) {
            //System.out.println("LEFT WALL EXISTS AT" + location);
            g.drawImage(Theme.wallTypes[type].leftImage, renderPoint.x- cameraLoc.x, renderPoint.y- cameraLoc.y, null);
        }
    }*/
}
