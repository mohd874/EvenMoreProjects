/*
 * RightWall.java
 *
 * Created on July 2, 2003, 5:21 PM
 */

package IsometricGame.Map;

import IsometricGame.*;

import java.awt.*;
import java.awt.image.*;

/**
 *
 * @author  jgauci
 */
public class RightWall implements Wall, java.io.Serializable  {
    public byte type;
    Point3D.Int location;
	Point renderPoint;
    
    /** Creates a new instance of RightWall */
    public RightWall(byte type,Point3D.Int location) {
        this.location = location;
        renderPoint = rightWallToMapCoord(location);
        this.type = type;
    }
    
    public Point rightWallToMapCoord(Point3D.Int a) {
        return new Point(Theme.tileWidth/2*a.x-Theme.tileWidth/2*a.y
        ,Theme.tileLength/2*a.x+Theme.tileLength/2*a.y -Theme.wallHeight+Theme.wallWidth/2-a.z*Theme.floorHeight);
    }
    
    public void draw(Graphics2D g,Point cameraLoc) {
        if(type!=WallType.NOWALL) {
            //System.out.println("RIGHT WALL EXISTS AT" + location);
            g.drawImage(Theme.wallTypes[type].rightImage, renderPoint.x- cameraLoc.x, renderPoint.y- cameraLoc.y, null);
        }
    }
    
}
