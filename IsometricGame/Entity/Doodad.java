/*
 * item.java
 *
 * Created on July 3, 2003, 5:20 PM
 */

package IsometricGame.Entity;

import IsometricGame.*;

import java.awt.*;

/**
 *
 * @author  jgauci
 */
public class Doodad extends Entity implements java.io.Serializable {
    public byte type;
    public Point3D.Int location;
    Point renderPoint;
    
    /** Creates a new instance of item */
    public Doodad(Point3D.Int location, byte type) {
        System.out.println("Doodad MADE AT " + location);
        this.location = location;
        this.type = type;
        renderPoint = doodadToMapCoord(location);
    }
    
    public Point doodadToMapCoord(Point3D.Int a) {
        return new Point(Theme.tileWidth/2*a.x-Theme.tileWidth/2*a.y -Theme.tileWidth/2
        ,Theme.tileLength/2*a.x+Theme.tileLength/2*a.y -(a.z+1)*Theme.floorHeight+Theme.tileLength/2);
    }
    
    public void draw(Graphics2D g,Point cameraLoc) {
        g.drawImage(Theme.doodadTypes[type].image, renderPoint.x- cameraLoc.x, renderPoint.y- cameraLoc.y, null);
    }
}
