/*
 * IsometricMapCreator.java
 *
 * Created on July 11, 2003, 6:43 PM
 */

package IsometricGame.Server;

import IsometricGame.Item.*;
import IsometricGame.Entity.*;
import IsometricGame.Net.*;
import IsometricGame.Server.*;
import IsometricGame.Server.Objective.*;
import IsometricGame.*;
import IsometricGame.Map.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 *
 * @author  jgauci
 */
public class IsometricMapServer extends IsometricGame.Map.IsometricMap implements PacketOperator {
   GameServer owner;
   GameEngine ownerEngine;
   PathCreator pathMaker;
   Hashtable objectiveTable;
   
   /** Creates a new instance of IsometricMapCreator */
   public IsometricMapServer(int length,int width,int height,GameServer owner,GameEngine ownerEngine) {
      super(length,width,height);
      this.owner = owner;
      this.ownerEngine = ownerEngine;
      pathMaker = new PathCreator(this);
      objectiveTable = new Hashtable();
   }
   
   public IsometricMapServer(File mapFile,GameServer owner,GameEngine ownerEngine) {
      super();
      pathMaker = new PathCreator(this);
      objectiveTable = new Hashtable();
      this.owner = owner;
      this.ownerEngine = ownerEngine;
      System.out.println("LOADING XML from" + mapFile.getAbsolutePath());
      try {
	 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder builder = factory.newDocumentBuilder();
	 Document mapDoc = builder.parse(mapFile);
	 Element root = (Element) mapDoc.getFirstChild();
	 length = Integer.parseInt(root.getAttribute("length"));
	 width = Integer.parseInt(root.getAttribute("width"));
	 height = Integer.parseInt(root.getAttribute("height"));
	 super.createBlankMap(length,width,height);
	 
	 NodeList tiles = root.getFirstChild().getChildNodes();
	 for(int z=0;z<height;z++)
	    for(int a=0;a<length;a++) {
	       for(int b=0;b<width;b++) {
		  Element tile = (Element) tiles.item(b+(a*width)+(z*length*width));
		  tileMap[z][a][b] = Byte.parseByte(tile.getAttribute("type"));
		  if(tile.hasChildNodes()) {
		     NodeList nList = tile.getChildNodes();
		     for(int w=0;w<nList.getLength();w++) {
			Element tileAtt = (Element) nList.item(w);
			if(tileAtt.getNodeName().equalsIgnoreCase("upLeftWall")) {
			   leftWalls[z][a][b] = Byte.parseByte(tileAtt.getAttribute("type"));
			}
			if(tileAtt.getNodeName().equalsIgnoreCase("upRightWall")) {
			   rightWalls[z][a][b] = Byte.parseByte(tileAtt.getAttribute("type"));
			}
			if(tileAtt.getNodeName().equalsIgnoreCase("doodad")) {
			   doodadMap[z][a][b] = new Doodad(new Point3D.Int(b,a,z), Byte.parseByte(tileAtt.getAttribute("type")));
			}
			if(tileAtt.getNodeName().equalsIgnoreCase("item")) {
			   System.out.println("Creating item");
			   createItem(Byte.parseByte(tileAtt.getAttribute("type")),new Point3D.Int(b,a,z));
			}
		     }
		  }
	       }
	    }
	 
	 
	 
      }
      catch(Exception e) {
	 e.printStackTrace();
      }
      System.out.println("DONE LOADING XML");
   }
   
   
   public void sendMap(ImprovedObjectOutputStream outStream) throws IOException {
      outStream.writeInt(length);
      outStream.writeInt(width);
      outStream.writeInt(height);
      for(int z=0;z<height;z++)
	 for(int a=0;a<length;a++) {
	    for(int b=0;b<width;b++) {
	       outStream.writeByte(rightWalls[z][a][b]);
	    }
	 }
      for(int z=0;z<height;z++)
	 for(int a=0;a<length;a++) {
	    for(int b=0;b<width;b++) {
	       outStream.writeByte(leftWalls[z][a][b]);
	    }
	 }
      for(int z=0;z<height;z++)
	 for(int a=0;a<length;a++) {
	    for(int b=0;b<width;b++) {
	       outStream.writeByte(tileMap[z][a][b]);
	       if(avatarMap[z][a][b]==null)
		  outStream.writeByte((byte)NONE);
	       else {
		  outStream.writeByte((byte)ACTOR);
		  //outStream.writeInt(b);
		  //outStream.writeInt(a);
		  //outStream.writeInt(z);
		  outStream.writeInt(avatarMap[z][a][b].ownerID.intValue());
		  outStream.writeInt(avatarMap[z][a][b].avatarID.intValue());
	       }
	       if(doodadMap[z][a][b]==null)
		  outStream.writeByte((byte)NONE);
	       else {
		  outStream.writeByte((byte)DOODAD);
		  //outStream.writeInt(b);
		  //outStream.writeInt(a);
		  //outStream.writeInt(z);
		  //outStream.writeInt(avatarMap[z][a][b].ownerID);
		  //outStream.writeInt(avatarMap[z][a][b].avatarID);
		  outStream.writeByte(doodadMap[z][a][b].type);
	       }
	    }
	 }
      
      for(int z=0;z<height;z++)
	 for(int y=0;y<length;y++)
	    for(int x=0;x<width;x++)
	       for(int t=0;t<itemList[z][y][x].size();t++) {
		  Item tempItem = (Item) itemList[z][y][x].get(t);
		  outStream.writeBoolean(true);
		  outStream.writeObject(tempItem);
	       }
      outStream.writeBoolean(false);
   }
   
   public Document saveMap() {
      try {
	 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder builder = factory.newDocumentBuilder();
	 Document mapDoc = builder.newDocument();
	 Element root = (Element) mapDoc.createElement("map");
	 root.setAttribute("length",""+length);
	 root.setAttribute("width",""+width);
	 root.setAttribute("height",""+height);
	 Element tiles = (Element) mapDoc.createElement("tiles");
	 for(int z=0;z<height;z++)
	    for(int a=0;a<length;a++) {
	       for(int b=0;b<width;b++) {
		  Element tile = (Element) mapDoc.createElement("tile");
		  tile.setAttribute("type", ""+tileMap[z][a][b]);
		  if(leftWalls[z][a][b]!=WallType.NOWALL) {
		     Element wall = (Element) mapDoc.createElement("upLeftWall");
		     wall.setAttribute("type", ""+leftWalls[z][a][b]);
		     tile.appendChild(wall);
		  }
		  if(rightWalls[z][a][b]!=WallType.NOWALL) {
		     Element wall = (Element) mapDoc.createElement("upRightWall");
		     wall.setAttribute("type", ""+rightWalls[z][a][b]);
		     tile.appendChild(wall);
		  }
		  if(doodadMap[z][a][b]!=null) {
		     Element doodad = (Element) mapDoc.createElement("doodad");
		     doodad.setAttribute("type", ""+doodadMap[z][a][b].type);
		     tile.appendChild(doodad);
		  }
		  
		  for(int t=0;t<itemList[z][a][b].size();t++) {
		     Item tempItem = (Item) itemList[z][a][b].get(t);
		     tile.appendChild(tempItem.createXML(mapDoc));
		  }
		  tiles.appendChild(tile);
	       }
	    }
	 root.appendChild(tiles);
	 
	 mapDoc.appendChild(root);
	 mapDoc.getDocumentElement().normalize();
	 System.out.println("Saved!");
	 return mapDoc;
      }
      catch(Exception e) {
	 e.printStackTrace();
	 return null;
      }
   }
   
   public void update()
   {
      Enumeration projectileIterator = projectileTable.elements();
      while(projectileIterator.hasMoreElements())
      {
	 
	 Projectile tempProjectile = ((Projectile)projectileIterator.nextElement());
	 Point3D.Int oldPoint = new Point3D.Int((int)tempProjectile.position.x,(int)tempProjectile.position.y,(int)tempProjectile.position.z);
	 if(!tempProjectile.updatePosition(this))
	 {
	    dropProjectileAt(tempProjectile,oldPoint);
	    owner.broadcastByte(PROJECTILE_DROPPED_AT);
	    owner.broadcastInt(tempProjectile.itemProjected.ID.intValue());
	    owner.broadcastObject(oldPoint);
	 }
      }
      
      for(int z=0;z<height;z++)
      {
	 for(int y=0;y<length;y++)
	 {
	    for(int x=0;x<width;x++)
	    {
	       if(avatarMap[z][y][x]!=null&&tileMap[z][y][x]==TileType.NOTILE)
	       {
		  if(z==0)
		  {
		     this.deleteAvatar(x,y,z);
		     System.out.println("AVATAR FELL THROUGH MAP!!!!!!!!!!!!!!!!!!!!");
		  }
		  this.moveAvatar(avatarMap[z][y][x].avatarID, x, y, z-1);
	       }
	       
	       for(int t=0;t<itemList[z][y][x].size();t++)
	       {
		  ((Item)itemList[z][y][x].get(t)).update(this);
	       }
	    }
	 }
      }
   }
   
   public void createItem(byte type,Point3D.Int location) {
      try {
	 Item tempItem=null;
	 Constructor myConst = (Class.forName("IsometricGame.Item."+(((ItemType)Theme.itemTypes.elementAt(type)).species)).getConstructor(new Class[] {Point3D.Int.class, byte.class, Integer.class, byte.class}));
	 System.out.println(location);
	 System.out.println(ownerEngine.getNewID());
	 System.out.println(((Constructor)myConst).newInstance(new Object[] { location, new Byte(type), ownerEngine.getNewID(), new Byte((byte)100)} ));
	 
	 tempItem = (Item) ((Constructor)myConst).newInstance(new Object[] { location, new Byte(type), ownerEngine.getNewID(), new Byte((byte)100)} );
	 //tempItem = new RangedWeapon(location,type,ownerEngine.getNewID(),(byte)100,0);
	 
	 itemList[tempItem.location.z][tempItem.location.y][tempItem.location.x].add(tempItem);
	 itemTable.put(tempItem.ID, tempItem);
	 owner.broadcastByte(ITEM_CREATED);
	 owner.broadcastByte(type);
	 owner.broadcastObject(tempItem);
      }
      catch(Exception e) {
	 e.printStackTrace();
      }
   }
   
   public void loadItem(Item tempItem,Point3D.Int location) {
      tempItem.location.setLocation(location);
      itemList[location.z][location.y][location.x].add(tempItem);
      owner.broadcastByte(ITEM_CREATED);
      owner.broadcastObject(tempItem);
   }
   
   public void removeItemFromGround(Integer ID) {
      super.removeItemFromGround(ID);
      owner.broadcastByte(ITEM_REMOVED_FROM_GROUND);
      owner.broadcastInt(ID.intValue());
   }

   public void deleteItem(Integer ID) {
      super.deleteItem(ID);
      owner.broadcastByte(ITEM_DELETED);
      owner.broadcastInt(ID.intValue());
   }
   
   public boolean throwItem(Item item,Point3D.Int target)
   {
      if(super.throwItem(item,target))
      {
	 System.out.println("THROW TIME IS " + System.currentTimeMillis());
	 owner.broadcastByte(ITEM_THROWN);
	 owner.broadcastInt(item.ID.intValue());
	 owner.broadcastObject(target);
	 return true;
      }
      return false;
   }
   
   public void dropItemFromPlayer(Item playerSlot,Point3D.Int location) {
      System.out.println("Loading " + playerSlot + " at " + location);
      owner.broadcastByte(HELD_ITEM_REMOVED);
      loadItem(playerSlot,location);
   }
   
   public boolean putItemInBackpack(Integer avatarID,Item theItem,int x,int y) {
      if(  ((Avatar)avatarTable.get(avatarID)).inventory.insertItem(theItem,x,y)) {
	 owner.broadcastByte(ITEM_PUT_IN_BACKPACK);
	 owner.broadcastInt(avatarID.intValue());
	 owner.broadcastObject(theItem);
	 owner.broadcastInt(x);
	 owner.broadcastInt(y);
	 return true;
      }
      else
	 return false;
   }
   
   public Item getItemFromBackpack(Integer avatarID,int x,int y) {
      Item tempItem = ((Avatar)avatarTable.get(avatarID)).inventory.removeItem(x,y);
      if(tempItem!=null)
      {
	 owner.broadcastByte(ITEM_GOT_FROM_BACKPACK);
	 owner.broadcastInt(avatarID.intValue());
	 owner.broadcastInt(x);
	 owner.broadcastInt(y);
      }
      return tempItem;
   }
   
   public void createDoodad(Point3D.Int location,byte doodadType) {
      super.createDoodad(location,doodadType);
      owner.broadcastByte(DOODAD_CREATED);
      owner.broadcastInt(location.x);
      owner.broadcastInt(location.y);
      owner.broadcastInt(location.z);
      owner.broadcastByte(doodadType);
   }
   
   public void deleteDoodad(Point3D.Int location) {
      super.deleteDoodad(location);
      owner.broadcastByte(DOODAD_DELETED);
      owner.broadcastInt(location.x);
      owner.broadcastInt(location.y);
      owner.broadcastInt(location.z);
   }
   
   public boolean createAvatar(int locationx,int locationy,int locationz,Integer ownerID,boolean[][][] usedNodes) {
      if(usedNodes[locationz][locationy][locationx])
	 return false;
      else
	 usedNodes[locationz][locationy][locationx]=true;
      
      if(avatarMap[locationz][locationy][locationx]==null) {
	 Integer avatarID = ownerEngine.getNewID();
	 avatarMap[locationz][locationy][locationx]=new Avatar(new Point3D.Int(locationx,locationy,locationz), ownerID, avatarID);
	 avatarTable.put(avatarMap[locationz][locationy][locationx].avatarID,avatarMap[locationz][locationy][locationx]);
	 objectiveTable.put(avatarMap[locationz][locationy][locationx], new ObjectiveController(avatarMap[locationz][locationy][locationx],this));
	 owner.broadcastByte(AVATAR_CREATED);
	 owner.broadcastInt(locationx);
	 owner.broadcastInt(locationy);
	 owner.broadcastInt(locationz);
	 owner.broadcastInt(ownerID.intValue());
	 owner.broadcastInt(avatarID.intValue());
	 return true;
      }
      if(locationy>0 && createAvatar(locationx,locationy-1,locationz,ownerID,usedNodes))
	 return true;
      if(locationx>0 && createAvatar(locationx-1,locationy,locationz,ownerID,usedNodes))
	 return true;
      if(locationy<length-1 && createAvatar(locationx,locationy+1,locationz,ownerID,usedNodes))
	 return true;
      if(locationx<width-1 && createAvatar(locationx+1,locationy,locationz,ownerID,usedNodes))
	 return true;
      return false;
   }
   
   public Stack setAvatarPath(Avatar target,int newx,int newy,int newz) {
      return pathMaker.createPath(target,newx, newy,newz);
   }
   
   public void moveAvatar(Integer avatarID, int newx, int newy, int newz) {
      super.moveAvatar(avatarID,newx, newy, newz);
      //Avatar tempAvatar = ((Avatar)avatarTable.get(new Integer(avatarID)));
      //avatarMap[tempAvatar.location.z][tempAvatar.location.y][tempAvatar.location.x] = null;
      //avatarMap[newz][newy][newx] = tempAvatar;
      //tempAvatar.location.setLocation(newx,newy,newz);
      //avatarMap[newy][newx].avatarToMapCoord();
      owner.broadcastByte(AVATAR_MOVED);
      owner.broadcastInt(avatarID.intValue());
      owner.broadcastInt(newx);
      owner.broadcastInt(newy);
      owner.broadcastInt(newz);
      System.out.println("AVATAR MOVED!");
   }

   public void createRoom(Rectangle lasso, int zVal, byte barState, byte type) {
      super.createRoom(lasso, zVal,barState, type);
      owner.broadcastByte(ROOM_CREATED);
      owner.broadcastInt(lasso.x);
      owner.broadcastInt(lasso.y);
      owner.broadcastInt(lasso.width);
      owner.broadcastInt(lasso.height);
      owner.broadcastInt(zVal);
      owner.broadcastByte(barState);
      owner.broadcastByte(type);
   }
   
   public void deleteWall(int locationx,int locationy,int locationz,byte wallDirection) {
      changeWall(locationx,locationy,locationz,wallDirection, WallType.NOWALL);
   }
   
   public void changeWall(int locationx,int locationy,int locationz,byte wallDirection,byte wallType) {
      super.changeWall(locationx,locationy, locationz,wallDirection,wallType);
      owner.broadcastByte(WALL_CHANGED);
      owner.broadcastInt(locationx);
      owner.broadcastInt(locationy);
      owner.broadcastInt(locationz);
      owner.broadcastByte(wallDirection);
      owner.broadcastByte(wallType);
   }
   
   public void deleteAvatar(int x,int y,int z) {
      objectiveTable.remove(avatarMap[z][y][x]);
      super.deleteAvatar(x,y,z);
      owner.broadcastByte(AVATAR_DELETED);
      owner.broadcastInt(x);
      owner.broadcastInt(y);
      owner.broadcastInt(z);
   }
   
   public void removePlayer(Integer connectionID) {
      for(int z=0;z<height;z++)
	 for(int y=0;y<length;y++)
	    for(int x=0;x<width;x++)
	       if(avatarMap[z][y][x]!=null&&avatarMap[z][y][x].ownerID.equals(connectionID))
		  deleteAvatar(x,y,z);
   }
   
}
