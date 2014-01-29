/*
 * RangedWeapon.java
 *
 * Created on August 13, 2003, 6:33 PM
 */

package IsometricGame.Item;

import IsometricGame.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  jgauci
 */
public class MeleeWeaponType extends WeaponType {
    
    /** Creates a new instance of RangedWeapon */
    public MeleeWeaponType(Dimension start, Dimension size, String name, byte weight, byte damage, byte delay) {
        super(start,size,name,weight,damage,delay);
        species = "MeleeWeapon";
        
    }
    
    public MeleeWeaponType(Dimension start, Dimension size, Container settings)
    {
        super(start,size,settings);
        species = "MeleeWeapon";
        
    }
    
}
