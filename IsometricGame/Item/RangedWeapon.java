/*
 * RangedWeapon.java
 *
 * Created on August 13, 2003, 6:33 PM
 */

package IsometricGame.Item;

import IsometricGame.*;
import java.io.*;

/**
 *
 * @author  jgauci
 */
public class RangedWeapon extends Weapon {
    int ammoLoaded;
    public RangedWeaponType model;
    
    /** Creates a new instance of RangedWeapon */
    public RangedWeapon(Point3D.Int location, byte type, Integer ID,byte condition) {
        super(location, type, ID, condition);
        this.ammoLoaded = 0;
    }
    
    public RangedWeapon(DataInputStream din) throws IOException
    {
        super(din);
        ammoLoaded = din.readInt();
    }
    
    public RangedWeapon(Point3D.Int location,org.w3c.dom.Element itemXML)
    {
        super(location, itemXML);
        Integer.parseInt(itemXML.getAttribute("ammoLoaded"));
    }
    
    public void send(DataOutputStream dout) throws IOException
    {
        super.send(dout);
        dout.writeInt(ammoLoaded);
    }
    
    public org.w3c.dom.Element createXML(org.w3c.dom.Document mapDoc)
    {
        org.w3c.dom.Element tempElement = super.createXML(mapDoc);
        tempElement.setAttribute("ammoLoaded", ""+ammoLoaded);
        return tempElement;
    }

}
