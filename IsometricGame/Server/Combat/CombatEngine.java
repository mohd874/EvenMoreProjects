/*
 * CombatEngine.java
 *
 * Created on June 25, 2004, 9:46 PM
 */

package IsometricGame.Server.Combat;

import IsometricGame.Server.*;
import IsometricGame.Entity.*;
import IsometricGame.Item.*;
import java.util.*;
import java.lang.*;
/**
 *
 * @author  jgauci
 */
public class CombatEngine {
    GameEngine source;
    Random r;
    
    static final int sizePenalty[] = {8,4,2,1,0,-1,-2,-4,-8};
    
    /** Creates a new instance of CombatEngine */
    public CombatEngine(GameEngine source) {
        this.source = source;
        this.r = source.r;
    }
    
    /**
     * Conducts a strike from one avatar to another.
     * Returns true if the state of the map has changed after the combat, and false if it has not.
     */    
    public boolean meleeAttack(Avatar attacker, Avatar target, Item weapon)
    {
        int attackRoll = (r.nextInt(20)+1);
        if(attackRoll==1)
        {
            return false;
        }
      /*  else if(attackRoll==20 || (attackRoll+attacker.attackBonus+attacker.strength+sizePenalty[attacker.size])>target.AC)
        {
            if(weapon.cattackRoll>weapon.criticalScore)
                weapon.strike(target,true);
            else
                weapon.strike(target,false);
            
        }*/
        return false;
        
    }

}
