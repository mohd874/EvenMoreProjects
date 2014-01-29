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
public class RangedWeaponType extends WeaponType {
    public int clipSize;
    
    /** Creates a new instance of RangedWeapon */
    public RangedWeaponType(Dimension start,Dimension size,String name,byte weight,  byte damage, byte delay, int clipSize) {
        super(start,size,name,weight,damage,delay);
        species = "RangedWeapon";
        
        this.clipSize = clipSize;
    }
    
    public RangedWeaponType(Dimension start,Dimension size,Container settings)
    {
        super(start,size,settings);
        species = "RangedWeapon";
        
        SpinnerNumberModel clipSpinnerModel = new javax.swing.SpinnerNumberModel(1,1,30000,1);
        clipSpinnerModel.addChangeListener(new javax.swing.event.ChangeListener() {
                        public void stateChanged(javax.swing.event.ChangeEvent evt) {
                            clipSize = ((SpinnerNumberModel)evt.getSource()).getNumber().intValue();
                        }
                    });
        JSpinner clipSpinner = new JSpinner(clipSpinnerModel);
        settings.add(new JLabel("Clip Size:"));
        settings.add(clipSpinner);
        
    }
    
}
