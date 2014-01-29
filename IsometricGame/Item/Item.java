/*
 * item.java
 *
 * Created on July 3, 2003, 5:20 PM
 */

package IsometricGame.Item;

import IsometricGame.*;

import java.awt.*;
import java.io.*;

/** This class represents an Item in the engine
 * @author jgauci
 */
public abstract class Item implements java.io.Serializable {
   /** This is a byte representing teh ItemType in the Theme class */   
   public byte type;
   /** This is the location of the item. It is either a point on the map or an avatarID */   
   public Point3D.Int location;
   /** This is a unique ID associated with the item. */   
   public Integer ID;
   /** This is the current condition of the item. */   
   public byte condition;
   
   /** Creates a new instance of item */
   public Item(Point3D.Int location, byte type, Integer ID,byte condition) {
      System.out.println("item MADE AT " + location);
      this.location = location;
      this.type = type;
      this.ID = ID;
      this.condition = condition;
   }
   
   public ItemType getModel() {
      return (ItemType)Theme.itemTypes.elementAt(type);
   }
   
   public Item(DataInputStream din) throws IOException {
      System.out.println("item MADE AT " + location);
      this.location = new Point3D.Int(din.readInt(),din.readInt(),din.readInt());
      this.type = din.readByte();
      this.ID = new Integer(din.readInt());
      this.condition = din.readByte();
   }
   
   public Item(Point3D.Int location,org.w3c.dom.Element itemXML) {
      this.location = location;
      this.type = Byte.parseByte(itemXML.getAttribute("type"));
      this.ID = new Integer(itemXML.getAttribute("ID"));
      this.condition = Byte.parseByte(itemXML.getAttribute("condition"));
   }
   
   public void send(DataOutputStream dout) throws IOException {
      dout.writeInt(location.x);
      dout.writeInt(location.y);
      dout.writeInt(location.z);
      dout.writeByte(type);
      dout.writeInt(ID.intValue());
      dout.writeByte(condition);
   }
   
   public org.w3c.dom.Element createXML(org.w3c.dom.Document mapDoc) {
      org.w3c.dom.Element tempElement = (org.w3c.dom.Element) mapDoc.createElement("item");
      tempElement.setAttribute("type", ""+type);
      tempElement.setAttribute("condition",  ""+condition);
      tempElement.setAttribute("ID", ""+ID);
      return tempElement;
   }
   
   public String toString() {
      return getModel().name;
   }
   
   public void update(IsometricGame.Map.IsometricMap owner) {
      if(!owner.projectileTable.containsKey(this.ID)) {
	 if(owner.tileMap[location.z][location.y][location.x]==IsometricGame.Map.TileType.NOTILE) {
	    if(location.z>0)
	       location.z--;
	 }
      }
   }
   
}
