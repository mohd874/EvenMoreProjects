/*
 * ItemOperator.java
 *
 * Created on August 13, 2003, 9:43 PM
 */

package IsometricGame.Item;

/**
 *
 * @author  jgauci
 */
public interface ItemOperator {
    final byte RANGEDWEAPON=0,MELEEWEAPON=1;
    
    final String[] itemCategoryNames = new String[] {"RangedWeapon","MeleeWeapon"};
}
