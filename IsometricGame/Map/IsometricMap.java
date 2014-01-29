/*
 * Mapjava
 *
 * Created on June 17, 2003, 1:18 PM
 */

package IsometricGame.Map;

import IsometricGame.Item.*;
import IsometricGame.Entity.*;
import IsometricGame.Net.*;
import IsometricGame.Client.*;
import IsometricGame.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;


/** Represents a Map for the client.
 * @author jgauci
 */
public class IsometricMap implements PacketOperator, Serializable  {
    /** An array representing all of the walls which are on the top-right border of a
     * tile.
     */
    public byte[][][] rightWalls;
    /** An array representing all of the walls which are on the top-left border of a
     * tile.
     */
    public byte[][][] leftWalls;
    /** An array representing all of the tiles. */
    public byte[][][] tileMap;
    /** An array representing the x coordinate on the screen for the
     * top-left of a tile for drawing purposes
     */
    public int[][][] renderx;
    /** An array representing the y coordinate on the screen for the
     * top-left of a tile for drawing purposes
     */
    public int[][][] rendery;
    
    /** An array holding LinkedLists containing Items. */
    public java.util.List[][][] itemList;
    /** An array representing all of the avatars. */
    public Avatar[][][] avatarMap;
    /** An array representing all of the doodads. */
    public Doodad[][][] doodadMap;
    
    public boolean[][][] redrawCell;
    
    int[][] heightMap;
    /** A hashtable with all of the projectiles keyed by the Item ID. */
    public Hashtable projectileTable;
    
    /** A Hashtable with all of the Items keyed by the item ID. */
    public Hashtable itemTable;
    
    /** A Hashtable of avatars keyed by the Avatar ID */
    public Hashtable avatarTable;
    
    /** The width of the map. */
    public int width;
    
    /** The length of the map. */
    public int length;
    
    /** The height of the map. */
    public int height;
    public int pixelWidth,pixelLength;
    boolean[][][] isVisible;
    
    public final static byte UPLEFT=0,UPRIGHT=1,DOWNRIGHT=2,DOWNLEFT=3;
    
    
    
    
    /** Creates a new instance of MapMap */
    public IsometricMap(int length, int width,int height) {
        createBlankMap(length,width,height);
    }
    
    /** Creates a new instance of MapMap */
    public IsometricMap() {
    }
    
    public IsometricMap(ObjectInputStream inStream) throws IOException {
        length = inStream.readInt();
        width = inStream.readInt();
        height = inStream.readInt();
        initBase();
        
        System.out.println(length + " " + width);
        for(int z=0;z<height;z++)
            for(int a=0;a<length;a++) {
                for(int b=0;b<width;b++) {
                    rightWalls[z][a][b] = inStream.readByte();
                }
            }
        for(int z=0;z<height;z++)
            for(int a=0;a<length;a++) {
                for(int b=0;b<width;b++) {
                    leftWalls[z][a][b] = inStream.readByte();
                }
            }
        
        avatarTable = new Hashtable();
        for(int z=0;z<height;z++)
            for(int a=0;a<length;a++) {
                for(int b=0;b<width;b++) {
                    tileMap[z][a][b]=inStream.readByte();
                    if(inStream.readByte()==ACTOR) {
                        Point3D.Int tempPoint = new Point3D.Int(b,a,z);
                        avatarMap[z][a][b] = new Avatar(tempPoint, new Integer(inStream.readInt()), new Integer(inStream.readInt()));
                        avatarTable.put(avatarMap[z][a][b].avatarID,avatarMap[z][a][b]);
                    }
                    if(inStream.readByte()==DOODAD) {
                        Point3D.Int tempPoint = new Point3D.Int(b,a,z);
                        doodadMap[z][a][b] = new Doodad(tempPoint, inStream.readByte());
                    }
                }
            }
        
        
        while(inStream.readBoolean()) {
            try {
                Item tempItem = (Item)inStream.readObject();
                itemList[tempItem.location.z][tempItem.location.y][tempItem.location.x].add(tempItem);
                itemTable.put(tempItem.ID, tempItem);
            }
            catch(ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
                return;
            }
            //createItem(itemSpecies,inStream);
        }
    }
    
    public void createBlankMap(int length,int width,int height) {
        try {
            System.out.println("CREATING MAP...");
            this.width = width;
            this.length = length;
            this.height = height;
            initBase();
            
            for(int z=0;z<height;z++)
                for(int a=0;a<length;a++)
                    for(int b=0;b<width;b++) {
                        rightWalls[z][a][b] = (byte)WallType.NOWALL;
                        leftWalls[z][a][b] = (byte)WallType.NOWALL;
                    }
            
            for(int z=0;z<height;z++)
                for(int a=0;a<length;a++)
                    for(int b=0;b<width;b++)
                        if(z>0)
                            tileMap[z][a][b]=(byte)TileType.NOTILE;
                        else
                            tileMap[z][a][b]=(byte)1;
        }
        catch(OutOfMemoryError oe) {
            oe.printStackTrace();
        }
        
    }
    
    
    
    public void initBase() {
        pixelWidth = width*Theme.tileWidth/2;
        pixelLength = length*Theme.tileLength;
        rightWalls = new byte[height][length][width];
        leftWalls = new byte[height][length][width];
        tileMap = new byte[height][length][width];
        avatarMap = new Avatar[height][length][width];
        doodadMap = new Doodad[height][length][width];
        isVisible = new boolean[height][length][width];
        renderx = new int[height][length][width];
        rendery = new int[height][length][width];
        heightMap = new int[length][width];
        avatarTable = new Hashtable();
        projectileTable = new Hashtable();
        
        itemList = new java.util.List[height][length][width];
        for(int z=0;z<height;z++)
            for(int a=0;a<length;a++)
                for(int b=0;b<width;b++) {
                    itemList[z][a][b] = Collections.synchronizedList(new LinkedList());
                    renderx[z][a][b] = Tile.tileToScreenCoordX(b,a,z);
                    rendery[z][a][b] = Tile.tileToScreenCoordY(b,a,z);
                }
        itemTable = new Hashtable();
        
        for(int a=0;a<length;a++)
            for(int b=0;b<width;b++)
                heightMap[a][b]=0;
        
    }
    
                /*public void syncMap(DataInputStream inStream) throws IOException {
                                length = inStream.readInt();
                                width = inStream.readInt();
                                System.out.println(length + " " + width);
                                for(int a=0;a<length+1;a++) {
                                                for(int b=0;b<width+1;b++) {
                                                                rightWalls[a][b] = inStream.readByte();
                                                }
                                }
                                for(int a=0;a<length+1;a++) {
                                                for(int b=0;b<width+1;b++) {
                                                                leftWalls[a][b] = inStream.readByte();
                                                }
                                }
                                for(int a=0;a<length;a++) {
                                                for(int b=0;b<width;b++) {
                                                                tileMap[a][b] = inStream.readByte();
                                                }
                                }
                 
                 
                                for(int y=0;y<length;y++)
                                                for(int x=0;x<width;x++)
                                                                itemList[y][x].clear();
                 
                                int ItemNum = inStream.readInt();
                                for(int a=0;a<ItemNum;a++) {
                                                Point location = new Point(inStream.readInt(),inStream.readInt());
                                                Item tempItem = new Item(location,inStream.readByte(),inStream.readInt());
                                                itemList[location.y][location.x].add(tempItem);
                                }
                }*/
    
    
    public void update() {
        Enumeration projectileIterator = projectileTable.elements();
        while(projectileIterator.hasMoreElements()) {
            
            Projectile tempProjectile = ((Projectile)projectileIterator.nextElement());
            Point3D.Int oldPoint = new Point3D.Int((int)tempProjectile.position.x,(int)tempProjectile.position.y,(int)tempProjectile.position.z);
            tempProjectile.updatePosition(this);
        }
        
    }
    
    public boolean projectileNotBlocked(Point3D.Double a,Point3D.Double b) {
        Point3D.Double unitVector = new Point3D.Double(b.x-a.x,b.y-a.y,b.z-a.z);
        double magnitude = unitVector.distance(unitVector.x,unitVector.y,unitVector.z,0.0D,0.0D,0.0D);
        unitVector.x /= (magnitude*2.0D);
        unitVector.y /= (magnitude*2.0D);
        unitVector.z /= (magnitude*2.0D);
        Point3D.Double location = new Point3D.Double(a.x,a.y,a.z);
        //System.out.println("UNITVECTOR " + unitVector);
        Point3D.Int prevTile = null,curTile = new Point3D.Int((int)a.x,(int)a.y,(int)a.z);
        for(int c=0;c<3;c++,location.y+=unitVector.y,location.x+=unitVector.x,location.z+=unitVector.z) {
            System.out.println("COLLISION DETECTION: " + location);
            curTile.setLocation((int)(location.x),(int)(location.y),(int)(location.z));
            if(prevTile!=null&&prevTile.equals(curTile)) {
                continue;
            }
            if(curTile.z<0||curTile.z>=height||curTile.y<0||curTile.x<0||curTile.y>=length||curTile.x>=width) {
                return false;
            }
            if(prevTile==null) {
                prevTile = (Point3D.Int)curTile.clone();
            }
            else if(visionNotBlocked(prevTile.x,prevTile.y,prevTile.z,curTile.x,curTile.y,curTile.z,false)) {
                prevTile.setLocation(curTile);
                //if(avatarMap[curTile.z][curTile.y][curTile.x]!=null)
                //{
                //  avatarMap[curTile.z][curTile.y][curTile.x].takeDamage(10);
                //   return false;
                //}
            }
            else {
                return false;
            }
        }
        System.out.println("NO COLLISION");
        return true;
    }
    
    public void setFieldOfView(Vector avatars,int radius) {
        //Point2D.Double location = new Point2D.Double(0.0f,0.0f);
        for(int z=0;z<height;z++)
            for(int a=0;a<length;a++)
                for(int b=0;b<width;b++) {
                    isVisible[z][a][b]=false;
                    heightMap[a][b]=height;
                }
        
        Point source;
        for(int z=-radius;z<height+radius;z++)
            for(int y=-radius;y<length+radius;y++)
                for(int x=-radius;x<width+radius;x++) {
                    //System.out.println(tileMap[targety][target.x].upRightWall!=NOWALL
                    for(int a=0;a<avatars.size();a++) {
                        Point3D.Int target = ((Avatar)avatars.elementAt(a)).getLocation();
                        if( Math.abs((Math.abs(x-target.x)+Math.abs(y-target.y)+Math.abs(z-target.z))-radius)<=1) {
                            lineOfSight(target,new Point3D.Int(x,y,(z/2)));
                        }
                    }
                }
    }
    
    public boolean lineOfSight(Point3D.Int a,Point3D.Int b) {
        Point3D.Double unitVector = new Point3D.Double(b.x-a.x,b.y-a.y,b.z-a.z);
        double magnitude = unitVector.distance(unitVector.x,unitVector.y,unitVector.z,0.0D,0.0D,0.0D);
        unitVector.x /= (magnitude*5.0D);
        unitVector.y /= (magnitude*5.0D);
        unitVector.z /= (magnitude*5.0D);
        Point3D.Double location = new Point3D.Double((double)a.x,(double)a.y,(double)a.z);
        //System.out.println("UNITVECTOR " + unitVector);
        Point3D.Int prevTile = null,curTile = new Point3D.Int(a.x,a.y,a.z);
        for(;location.distance(b)>1.0D;location.y+=unitVector.y,location.x+=unitVector.x,location.z+=unitVector.z) {
            //System.out.println(location);
            curTile.setLocation((int)(location.x+0.5D),(int)(location.y+0.5D),(int)(location.z+0.5D));
            if(prevTile!=null&&prevTile.equals(curTile)) {
                continue;
            }
            if(curTile.z<0||curTile.z>=height||curTile.y<0||curTile.x<0||curTile.y>=length||curTile.x>=width) {
                return false;
            }
            if(prevTile==null) {
                isVisible[curTile.z][curTile.y][curTile.x]=true;
                prevTile = (Point3D.Int)curTile.clone();
            }
            else if(visionNotBlocked(prevTile.x,prevTile.y,prevTile.z,curTile.x,curTile.y,curTile.z,true)) {
                isVisible[curTile.z][curTile.y][curTile.x]=true;
                prevTile.setLocation(curTile);
            }
            else {
                return false;
            }
        }
        return true;
    }
    
   public boolean visionNotBlocked(int ax,int ay,int az,int bx,int by,int bz,boolean passThroughTransparent) {
      if(bx<0||by<0||bz<0||bx>=width||by>=length) {
	 System.out.println("Vision BLOCKED: OUTSIDE MAP");
	 return false;
      }
      if(ax!=bx&&ay!=by&&az!=bz)
	 return false;
      if(ax!=bx&&ay!=by) {
	 if(Math.abs(ax-bx)==1&&Math.abs(ay-by)==1)
	    return visionNotBlocked(ax, ay, az, ax, by, bz,passThroughTransparent)
	    &&visionNotBlocked(ax, ay, az, bx, ay, bz,passThroughTransparent)
	    &&visionNotBlocked(ax, by, az, bx, by, bz,passThroughTransparent)
	    &&visionNotBlocked(bx, ay, az, bx, by, bz,passThroughTransparent);
	 else
	    return false;
      }
      if(ax!=bx&&az!=bz) {
	 if(Math.abs(ax-bx)==1&&Math.abs(az-bz)==1)
	    return visionNotBlocked(ax,ay, az,bx,ay,az,passThroughTransparent)
	    &&visionNotBlocked(ax,ay, az,ax,ay,bz,passThroughTransparent)
	    &&visionNotBlocked(bx,ay, az,bx,ay,bz,passThroughTransparent)
	    &&visionNotBlocked(ax,ay, bz,bx,ay,bz,passThroughTransparent);
	 else
	    return false;
      }
      if(ay!=by&&az!=bz) {
	 if(Math.abs(ay-by)==1&&Math.abs(az-bz)==1)
	    return visionNotBlocked(ax,ay, az,ax,ay,bz,passThroughTransparent)
	    &&visionNotBlocked(ax,ay, az,ax,by,az,passThroughTransparent)
	    &&visionNotBlocked(ax,ay, bz,ax,by,bz,passThroughTransparent)
	    &&visionNotBlocked(ax,by, az,ax,by,bz,passThroughTransparent);
	 else
	    return false;
      }
      
      if(az>bz) {
	 if(tileMap[az][ay][ax]!=TileType.NOTILE&&(Theme.tileTypes[tileMap[az][ay][ax]].seeThrough==false||!passThroughTransparent))
	    return false;
      }
      if(az<bz) {
	 if(tileMap[bz][by][bx]!=TileType.NOTILE&&(Theme.tileTypes[tileMap[bz][by][bx]].seeThrough==false||!passThroughTransparent))
	    return false;
      }
      //if(tileMap[a.y][a.x].downRightWall!=NOWALL)
      //System.out.println("See Through? " + Theme.wallTypes[tileMap[a.y][a.x].downRightWall].seeThrough);
      if(ax<bx&&(leftWalls[az][ay][ax+1]!=WallType.NOWALL&&(Theme.wallTypes[leftWalls[az][ay][ax+1]].seeThrough==false||!passThroughTransparent)))
	 return false;
      if(ax>bx&&(leftWalls[az][ay][ax]!=WallType.NOWALL&&(Theme.wallTypes[leftWalls[az][ay][ax]].seeThrough==false||!passThroughTransparent)))
	 return false;
      if(ay<by&&(rightWalls[az][ay+1][ax]!=WallType.NOWALL&&(Theme.wallTypes[rightWalls[az][ay+1][ax]].seeThrough==false||!passThroughTransparent)))
	 return false;
      if(ay>by&&(rightWalls[az][ay][ax]!=WallType.NOWALL&&(Theme.wallTypes[rightWalls[az][ay][ax]].seeThrough==false||!passThroughTransparent)))
	 return false;
      //System.out.println("No Wall Found");
      return true;
      
   }
   
   public boolean movementBlocked(int ax,int ay,int az,int bx,int by,int bz) {
      if(az>bz) {
	 if(doodadMap[az][ay][ax]!=null&&Theme.doodadTypes[doodadMap[az][ay][ax].type].fallable==true)
	    return false;
	 else
	    return true;
      }
      if(az<bz) {
	 if(doodadMap[az][ay][ax]!=null&&Theme.doodadTypes[doodadMap[az][ay][ax].type].climbable==true)
	    return false;
	 else
	    return true;
      }
      if(ax!=bx&&ay!=by) {
	 if(Math.abs(ax-bx)==1&&Math.abs(ay-by)==1)
	    return movementBlocked(ax, ay, az, ax, by, bz)||movementBlocked(ax, ay, az, bx, ay, bz)||movementBlocked(ax, by, az, bx, by, bz)||movementBlocked(bx, ay, az, bx, by, bz);
	 else
	    return true;
      }
      if(ax<bx
      &&(tileMap[bz][by][bx]==TileType.NOTILE||
      Theme.tileTypes[tileMap[bz][by][bx]].passable==false||
      (leftWalls[az][ay][ax+1]!=WallType.NOWALL&&
      Theme.wallTypes[leftWalls[az][ay][ax+1]].passable==false))
      ) {
	 System.out.println("CASE 1");
	 return true;
      }
      
      if(ax>bx
      &&(tileMap[bz][by][bx]==TileType.NOTILE||
      Theme.tileTypes[tileMap[bz][by][bx]].passable==false||
      (leftWalls[az][ay][ax]!=WallType.NOWALL&&
      Theme.wallTypes[leftWalls[az][ay][ax]].passable==false))
      ) {
	 System.out.println("CASE 2");
	 return true;
      }
      
      if(ay<by
      &&(tileMap[bz][by][bx]==TileType.NOTILE||
      Theme.tileTypes[tileMap[bz][by][bx]].passable==false||
      (rightWalls[az][ay+1][ax]!=WallType.NOWALL&&
      Theme.wallTypes[rightWalls[az][ay+1][ax]].passable==false))
      ) {
	 System.out.println("CASE 3");
	 return true;
      }
      
      if(ay>by
      &&(tileMap[bz][by][bx]==TileType.NOTILE||
      Theme.tileTypes[tileMap[bz][by][bx]].passable==false||
      (rightWalls[az][ay][ax]!=WallType.NOWALL&&
      Theme.wallTypes[rightWalls[az][ay][ax]].passable==false))
      ) {
	 System.out.println("CASE 4");
	 return true;
      }
      
      //System.out.println("No Wall Found");
      return false;
      
   }

    
    public void getRoof(Vector avatars) {
        for(int a=0;a<avatars.size();a++) {
            Avatar tempAvatar = (Avatar)avatars.elementAt(a);
            int tempHeight = height;
            Point3D.Int tempLoc = tempAvatar.getLocation();
            while(tempHeight-1>tempLoc.z) {
                tempHeight--;
                if(tileMap[tempHeight][tempLoc.y][tempLoc.x]!=TileType.NOTILE
                &&Theme.tileTypes[tileMap[tempHeight][tempLoc.y][tempLoc.x]].seeThrough==false) {
                    System.out.println("Running heightmap on height " + tempHeight);
                    floodHeight(tempLoc.x, tempLoc.y, tempHeight);
                }
                int shiftMult = (tempHeight-tempLoc.z) - 1;
                for(int b=1;b<=3;b++) {
                    if(tempLoc.y+b+(3*shiftMult)>=length)
                        continue;
                    if(tempLoc.x+b+(3*shiftMult)>=width)
                        continue;
                    if(tileMap[tempHeight][tempLoc.y+b+(3*shiftMult)][tempLoc.x+b+(3*shiftMult)]!=TileType.NOTILE
                    &&Theme.tileTypes[tileMap[tempHeight][tempLoc.y+b+(3*shiftMult)][tempLoc.x+b+(3*shiftMult)]].seeThrough==false) {
                        System.out.println("Running heightmap on height " + tempHeight);
                        floodHeight(tempLoc.x+b+(3*shiftMult), tempLoc.y+b+(3*shiftMult), tempHeight);
                    }
                }
            }
        }
    }
    
    public void floodHeight(int x,int y,int height) {
        if(heightMap[y][x]==(height-1))
            return;
        heightMap[y][x]=height-1;
        if(y>0&&tileMap[height][y-1][x]!=TileType.NOTILE)
            floodHeight(x,y-1, height);
        if(x>0&&tileMap[height][y][x-1]!=TileType.NOTILE)
            floodHeight(x-1,y, height);
        if(y<length-1&&tileMap[height][y+1][x]!=TileType.NOTILE)
            floodHeight(x,y+1, height);
        if(x<width-1&&tileMap[height][y][x+1]!=TileType.NOTILE)
            floodHeight(x+1,y, height);
        
        
    }
    
    
    public void drawMap(Graphics2D midBuffer,VolatileImage midImage,PixelImage avatarImage,
    Point cameraLoc,boolean isPlayer,boolean gridOn,boolean alphaOn,
    boolean wallsOn,int onHeight,Rectangle lasso,int barState,int wallCurrent,
    Point3D.Int tileCurrent,Avatar avatarSelected,Integer connectionID,boolean groundChanged) {
        //client.midBuffer.setColor(Color.BLACK);
        //client.midBuffer.setStroke(Theme.gridStroke);
        avatarImage.clearImage();
        
        //midBuffer.setClip(0,0, midImage.getWidth(), midImage.getHeight());
        //midBuffer.drawImage(mi,0,0,null);
        //midBuffer.setClip(0,0, 50,50);
        midBuffer.clearRect(0,0,midImage.getWidth(), midImage.getHeight());
        
        for(int z=0;z<height;z++) {
         /*for(int y=0;y<length;y++) {
            for(int x=0;x<width;x++) {
               if(isPlayer&&heightMap[y][x]<z&&onHeight<z)
                  continue;
               if(!isVisible[z][y][x]&&isPlayer)
                  tileMap[z][y][x].drawFog(midBuffer,cameraLoc);
            }
         }*/
            //midBuffer.setComposite(Theme.drawNormalComposite);
            if(gridOn&&z==onHeight)
                drawGrid(midBuffer,cameraLoc,onHeight);
            for(int y=0;y<length;y++) {
                for(int x=0,itemNum=0;x<width;x++) {
                    if(isPlayer&&heightMap[y][x]<z&&onHeight<z)
                        continue;
                    if(y>0&&isPlayer&&heightMap[y-1][x]<z&&onHeight<z)
                        continue;
                    if(x>0&&isPlayer&&heightMap[y][x-1]<z&&onHeight<z)
                        continue;
                    if(x>0&&y>0&&isPlayer&&heightMap[y-1][x-1]<z&&onHeight<z)
                        continue;
                    if(tileMap[z][y][x]!=TileType.NOTILE)
                        midBuffer.drawImage(Theme.tileTypes[tileMap[z][y][x]].tileImage, renderx[z][y][x] - cameraLoc.x, rendery[z][y][x] - cameraLoc.y, null);
                    if(doodadMap[z][y][x]!=null)
                        doodadMap[z][y][x].draw(midBuffer,cameraLoc);
                    
                    if(!isVisible[z][y][x]&&isPlayer) {
                        if(tileMap[z][y][x]!=TileType.NOTILE)
                            midBuffer.drawImage(Theme.fogofwar,renderx[z][y][x]- cameraLoc.x, rendery[z][y][x] - cameraLoc.y, null);
                    }
                    else {
                        
                        for(ListIterator t=itemList[z][y][x].listIterator();t.hasNext();) {
                            Item tempItem = (Item)t.next();
                            midBuffer.drawImage(tempItem.getModel().groundImage,renderx[z][y][x]- cameraLoc.x, rendery[z][y][x] - cameraLoc.y, null);
                        }
                        
                        
                        
                        
                    }
                    
                    if(leftWalls[z][y][x]!=WallType.NOWALL)
                        midBuffer.drawImage(Theme.wallTypes[leftWalls[z][y][x]].leftImage, renderx[z][y][x]- cameraLoc.x, rendery[z][y][x]-Theme.floorHeight- cameraLoc.y, null);
                    if(rightWalls[z][y][x]!=WallType.NOWALL)
                        midBuffer.drawImage(Theme.wallTypes[rightWalls[z][y][x]].rightImage, renderx[z][y][x]+(Theme.tileWidth/2)- cameraLoc.x, rendery[z][y][x]-Theme.floorHeight- cameraLoc.y, null);
                    
                    if(isVisible[z][y][x]||!isPlayer) {
                        if((!(isPlayer)||isVisible[z][y][x])&&avatarMap[z][y][x]!=null) {
                            //System.out.println("drawing avatar at " + x + ',' + y);
                            avatarMap[z][y][x].draw(midBuffer,avatarImage,renderx[z][y][x]- cameraLoc.x,rendery[z][y][x]-Theme.floorHeight+(Theme.tileLength/2)- cameraLoc.y);
                        }
                    }
                    
                    if(lasso!=null&& lasso.contains(x,y)) {
                        Tile.outline(Color.YELLOW, midBuffer, renderx[z][y][x]-cameraLoc.x, rendery[z][y][x]-cameraLoc.y);
                    }
                    if(isVisible[z][y][x]&&avatarSelected!=null&&avatarSelected.getLocation().distance(x,y,z)==0) {
                        if(connectionID.equals(avatarMap[z][y][x].ownerID))
                            Tile.outline(Color.GREEN, midBuffer, renderx[z][y][x]-cameraLoc.x, rendery[z][y][x]-cameraLoc.y);
                        else
                            Tile.outline(Color.RED, midBuffer, renderx[z][y][x]-cameraLoc.x, rendery[z][y][x]-cameraLoc.y);
                    }
                    else if(tileCurrent.distance(x,y,z)==0) {
                        //System.out.println("DRAWING OUTLINE");
                        if(barState==1) {
                            switch(wallCurrent) {
                                case GameClient.DOWNLEFT:
                                    midBuffer.setColor(Color.BLUE);
                                    midBuffer.drawLine(renderx[z][y][x]- cameraLoc.x,rendery[z][y][x]+Theme.tileLength/2 - cameraLoc.y, renderx[z][y][x]+Theme.tileWidth/2- cameraLoc.x, rendery[z][y][x]+Theme.tileLength - cameraLoc.y);
                                    break;
                                    
                                case GameClient.DOWNRIGHT:
                                    midBuffer.setColor(Color.BLUE);
                                    midBuffer.drawLine(renderx[z][y][x]+Theme.tileWidth/2- cameraLoc.x,rendery[z][y][x]+Theme.tileLength- cameraLoc.y, renderx[z][y][x]+Theme.tileWidth- cameraLoc.x, rendery[z][y][x]+Theme.tileLength/2- cameraLoc.y);
                                    break;
                                    
                                case GameClient.UPLEFT:
                                    midBuffer.setColor(Color.BLUE);
                                    midBuffer.drawLine(renderx[z][y][x]- cameraLoc.x,rendery[z][y][x]+Theme.tileLength/2- cameraLoc.y, renderx[z][y][x]+Theme.tileWidth/2- cameraLoc.x, rendery[z][y][x] - cameraLoc.y);
                                    break;
                                    
                                case GameClient.UPRIGHT:
                                    midBuffer.setColor(Color.BLUE);
                                    midBuffer.drawLine(renderx[z][y][x] + Theme.tileWidth/2 - cameraLoc.x,rendery[z][y][x] - cameraLoc.y, renderx[z][y][x]+Theme.tileWidth- cameraLoc.x, rendery[z][y][x]+Theme.tileLength/2 - cameraLoc.y);
                                    break;
                                    
                            }
                        }
                        else if(tileMap[z][y][x]!=TileType.NOTILE) {
                            Tile.outline(Color.YELLOW, midBuffer, renderx[z][y][x]-cameraLoc.x, rendery[z][y][x]-cameraLoc.y);
                        }
                    }
                }
            }
        }
        
        Enumeration projectileIterator = projectileTable.elements();
        while(projectileIterator.hasMoreElements()) {
            ((Projectile)projectileIterator.nextElement()).draw(midBuffer,cameraLoc);
        }
        
        midBuffer.setStroke(Theme.normalStroke);
        midBuffer.setColor(Color.YELLOW);
    }
    
    public void drawGrid(Graphics2D buffer,Point cameraLoc,int height) {
        buffer.setColor(Color.WHITE);
        for(int a=1;a<length;a++) {
            buffer.drawLine(renderx[height][a][0]-cameraLoc.x+Theme.tileWidth/2, rendery[height][a][0]-cameraLoc.y, renderx[height][a][0]+Theme.tileWidth*width/2-cameraLoc.x+Theme.tileWidth/2, rendery[height][a][0]+Theme.tileLength*width/2-cameraLoc.y);
        }
        for(int a=1;a<width;a++) {
            buffer.drawLine(renderx[height][0][a]-cameraLoc.x+Theme.tileWidth/2, rendery[height][0][a]-cameraLoc.y, renderx[height][0][a]-Theme.tileWidth*length/2-cameraLoc.x+Theme.tileWidth/2, rendery[height][0][a]+Theme.tileLength*length/2-cameraLoc.y);
        }
        buffer.setColor(Color.BLACK);
    }
    
    public void delete() {
        try {
            finalize();
        }
        catch(java.lang.Throwable e) {
            e.printStackTrace();
        }
    }
    
   public void createRoom(Rectangle lasso,int height,byte barState,byte type) {
      for(int y=0;y<(length-1);y++)
	 for(int x=0;x<(width-1);x++) {
	    if(lasso.contains(x,y)) {
	       if(barState==0) {
		  tileMap[height][y][x] = type;
	       }
	       else if(barState==1) {
		  if(x+2==width||!lasso.contains(x+1,y))
		     leftWalls[height][y][x+1] = type;
		  if(x==0||!lasso.contains(x-1,y))
		     leftWalls[height][y][x] = type;
		  if(y+2==length||!lasso.contains(x,y+1))
		     rightWalls[height][y+1][x] = type;
		  if(y==0||!lasso.contains(x,y-1))
		     rightWalls[height][y][x] = type;
	       }
	    }
	 }
   }
   
   public void deleteWall(int locationx,int locationy,int locationz,byte wallDirection) {
      changeWall(locationx,locationy,locationz,wallDirection, WallType.NOWALL);
      
   }
   
   public void changeWall(int locationx,int locationy,int locationz,byte wallDirection,byte wallType) {
      switch(wallDirection) {
	 case UPRIGHT:
	    System.out.println("drawUPRIGHT: " + locationx + " " + locationy);
	    rightWalls[locationz][locationy][locationx] = wallType;
	    break;
	 case UPLEFT:
	    System.out.println("drawUPLEFT: " + locationx + " " + locationy);
	    leftWalls[locationz][locationy][locationx] = wallType;
	    break;
	 case DOWNLEFT:
	    System.out.println("drawDOWNLEFT: " + locationx + " " + locationy);
            if(locationy+1==length)
                break;
	    rightWalls[locationz][locationy+1][locationx] = wallType;
	    break;
	 case DOWNRIGHT:
	    System.out.println("drawDOWNRIGHT: " + locationx + " " + locationy);
            if(locationx+1==width)
                break;
	    leftWalls[locationz][locationy][locationx+1] = wallType;
	    break;
	    
      }
   }
    
    public void createDoodad(Point3D.Int location,byte doodadType) {
        doodadMap[location.z][location.y][location.x] = new Doodad(location,doodadType);
    }
    
    public void deleteDoodad(Point3D.Int location) {
        doodadMap[location.z][location.y][location.x] = null;
    }
    
    public Item createItem(ObjectInputStream inStream) {
        Item tempItem = null;
        try {
            tempItem = (Item) inStream.readObject();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        itemList[tempItem.location.z][tempItem.location.y][tempItem.location.x].add(tempItem);
        itemTable.put(tempItem.ID,tempItem);
        return tempItem;
    }
    
    public void moveItem(Point3D.Int oldLoc,Integer ItemID,Point3D.Int newLoc) {
        Item tempItem = null;
        for(int t=0;t<itemList[oldLoc.z][oldLoc.y][oldLoc.x].size();t++) {
            if( ((Item)itemList[oldLoc.z][oldLoc.y][oldLoc.x].get(t)).ID.equals(ItemID)) {
                tempItem = (Item)itemList[oldLoc.z][oldLoc.y][oldLoc.x].remove(t);
                break;
            }
        }
        if(tempItem!=null) {
            tempItem.location.setLocation(newLoc);
            itemList[newLoc.z][newLoc.y][newLoc.x].add(tempItem);
        }
    }
    
    public void removeItemFromGround(Integer ID) {
        Item target = (Item)itemTable.get(ID);
        System.out.println("Removing " + target + " from " + target.location);
        itemList[target.location.z][target.location.y][target.location.x].remove(target);
    }
    
    public void deleteItem(Integer ID) {
        Item target = (Item)itemTable.get(ID);
        itemList[target.location.z][target.location.y][target.location.x].remove(target);
        itemTable.remove(ID);
    }
    
    public boolean throwItem(Item item,Point3D.Int target) {
        try {
            Projectile proj = new Projectile(item, 1.0D/2.0D, target);
            projectileTable.put(item.ID,proj);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    
    public void dropProjectileAt(Projectile pj,Point3D.Int location) {
        pj.itemProjected.location.setLocation(pj.position);
        itemList[location.z][location.y][location.x].add(pj.itemProjected);
        projectileTable.remove(pj.itemProjected.ID);
    }
    
    public boolean putItemInBackpack(Integer avatarID,Item theItem,int x,int y) {
        if(((Avatar)avatarTable.get(avatarID)).inventory.insertItem(theItem,x,y))
            return true;
        else
            return false;
        
    }
    
    public Item getItemFromBackpack(Integer avatarID,int x,int y) {
        Item tempItem = ((Avatar)avatarTable.get(avatarID)).inventory.removeItem(x,y);
        return tempItem;
    }
    
    public void deleteEntitiesAt(int locationx,int locationy,int locationz) {
        itemList[locationz][locationy][locationx].clear();
    }
    
    public void createAvatar(int locationx,int locationy,int locationz,Integer ownerID,Integer avatarID) {
        avatarMap[locationz][locationy][locationx]=new Avatar(new Point3D.Int(locationx,locationy,locationz), ownerID, avatarID);
        avatarTable.put(avatarMap[locationz][locationy][locationx].avatarID,avatarMap[locationz][locationy][locationx]);
        System.out.println("AVATAR CREATED AT " + locationx + ',' + locationy);
    }
    
    public void moveAvatar(Integer avatarID,int newx,int newy,int newz) {
        Avatar tempAvatar = ((Avatar)avatarTable.get(avatarID));
        System.out.println("AVATAR " + tempAvatar + " IS MOVING TO TILE TYPE" + tileMap[newz][newy][newx]);
        Point3D.Int tempLoc = tempAvatar.getLocation();
        avatarMap[tempLoc.z][tempLoc.y][tempLoc.x] = null;
        avatarMap[newz][newy][newx] = tempAvatar;
        tempAvatar.setLocation(newx,newy,newz);
        //avatarMap[newy][newx].avatarToMapCoord();
        //System.out.println("AVATAR MOVED FROM " + oldx + ',' + oldy + " TO " + newx + ',' + newy);
    }
    
    public void deleteAvatar(int x,int y,int z) {
        avatarTable.remove(avatarMap[z][y][x].avatarID);
        avatarMap[z][y][x] = null;
    }
    
    
    
}
