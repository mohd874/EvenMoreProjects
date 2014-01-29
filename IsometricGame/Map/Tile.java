/*
 * Tile.java
 *
 * Created on July 1, 2003, 1:14 PM
 */

package IsometricGame.Map;

import IsometricGame.*;


import java.awt.*;
import java.awt.image.*;

/** This represents a tile on the Map
 * @author jgauci
 */
public class Tile {
    /** The type of the tile in the Theme class. */
    
    /** Creates a new instance of Tile
     * @param type The type of the tile
     * @param location The location of the tile
     */
    public Tile() {
    }
    
    /** Converts from Isometric-Space to Camera-Space
     * @param a Location of Tile
     * @return Draw Location
     */
    public static int tileToScreenCoordX(int x,int y,int z) {
        return Theme.tileWidth/2*x-Theme.tileWidth/2*y -Theme.tileWidth/2;
    }
    
    public static int tileToScreenCoordY(int x,int y,int z) {
        return Theme.tileLength/2*x+Theme.tileLength/2*y - z*Theme.floorHeight;
    }
    
    /** Draws a tile */
    /*public static void drawGround(Graphics2D g,int xPos,int yPos,byte type) {
        if(type==TileType.NOTILE)
            return;
        g.drawImage(Theme.tileTypes[type].tileImage, renderPoint.x- cameraLoc.x, renderPoint.y- cameraLoc.y, null);
    }
    
    public void drawFog(Graphics2D g,Point cameraLoc) {
        if(type==TileType.NOTILE)
            return;
        g.drawImage(Theme.fogofwar,                  renderPoint.x- cameraLoc.x, renderPoint.y- cameraLoc.y, null);
    }
    
    /** Draws the Items on the tile
     * @param g Graphics2D Object
     * @param cameraLoc The location of the client's camera
     * @param itemImage The iamge of the item
     */
 /*   public void drawItem(Graphics2D g,Point cameraLoc,BufferedImage itemImage) {
        g.drawImage(itemImage, renderPoint.x- cameraLoc.x, renderPoint.y- cameraLoc.y, 60, 30, null);
    }*/
    
    /** Outlines a tile
     * @param theColor The color to outline
     * @param midBuffer The drawing window
     * @param cameraLoc The location of the Camera
     */
    public static void outline(Color theColor,Graphics2D midBuffer,int xPos,int yPos) {
        Color oldColor = midBuffer.getColor();
        midBuffer.setColor(theColor);
        midBuffer.drawLine(xPos+1,yPos+Theme.tileLength/2, xPos+Theme.tileWidth/2, yPos+Theme.tileLength-1);
        midBuffer.drawLine(xPos+Theme.tileWidth/2,yPos+Theme.tileLength-1, xPos+Theme.tileWidth-1, yPos+Theme.tileLength/2);
        midBuffer.drawLine(xPos+1,yPos+Theme.tileLength/2, xPos+Theme.tileWidth/2, yPos+1);
        midBuffer.drawLine(xPos+Theme.tileWidth/2,yPos+1, xPos+Theme.tileWidth-1, yPos+Theme.tileLength/2);
        midBuffer.setColor(oldColor);
    }
    
}
