/*
 * Weapon.java
 *
 * Created on August 8, 2003, 12:51 PM
 */

package IsometricGame.Item;

import IsometricGame.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author  jgauci
 */
public abstract class WeaponType extends ItemType {
    //boolean twoHanded,ranged;
    //Clip clipInside;
    public byte damage;
    public byte delay;
    
    /** Creates a new instance of Weapon */
    public WeaponType(Dimension start,Dimension size,String name,byte weight,  byte damage, byte delay) {
        super(start,size,name,weight);
        this.damage = damage;
        this.delay = delay;
    }
    
    public WeaponType(Dimension start,Dimension size,Container settings)
    {
        super(start,size,settings);
        SpinnerNumberModel damageSpinnerModel = new javax.swing.SpinnerNumberModel(1,1,127,1);
        damageSpinnerModel.addChangeListener(new javax.swing.event.ChangeListener() {
                        public void stateChanged(javax.swing.event.ChangeEvent evt) {
                            damage = ((SpinnerNumberModel)evt.getSource()).getNumber().byteValue();
                        }
                    });
        JSpinner damageSpinner = new JSpinner(damageSpinnerModel);
        settings.add(new JLabel("Damage:"));
        settings.add(damageSpinner);
        SpinnerNumberModel delaySpinnerModel = new javax.swing.SpinnerNumberModel(1,1,127,1);
        delaySpinnerModel.addChangeListener(new javax.swing.event.ChangeListener() {
                        public void stateChanged(javax.swing.event.ChangeEvent evt) {
                            delay = ((SpinnerNumberModel)evt.getSource()).getNumber().byteValue();
                        }
                    });
        JSpinner delaySpinner = new JSpinner(delaySpinnerModel);
        settings.add(new JLabel("Delay time:"));
        settings.add(delaySpinner);
        
    }
    
}
