/*
 * Weapon.java
 *
 * Created on August 13, 2003, 9:08 PM
 */

package IsometricGame.Item;

import IsometricGame.*;
import java.io.*;

/**
 *
 * @author  jgauci
 */
public abstract class Weapon extends Item {
    public WeaponType model;
    
    /** Creates a new instance of Weapon */
    public Weapon(Point3D.Int location, byte type, Integer ID,byte condition) {
        super(location, type,ID,condition);
    }
    
    public Weapon(DataInputStream din) throws IOException
    {
        super(din);
    }
    
    public Weapon(Point3D.Int location,org.w3c.dom.Element itemXML)
    {
        super(location, itemXML);
    }
    
    public void send(DataOutputStream dout) throws IOException
    {
        super.send(dout);
    }
    
    public org.w3c.dom.Element createXML(org.w3c.dom.Document mapDoc)
    {
        return super.createXML(mapDoc);
    }
    
}
