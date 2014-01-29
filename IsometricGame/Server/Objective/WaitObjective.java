/*
 * MoveAI.java
 *
 * Created on September 11, 2003, 3:03 PM
 */

package IsometricGame.Server.Objective;

import IsometricGame.*;
import IsometricGame.Entity.*;
import IsometricGame.Server.*;

import java.util.*;

/**
 *
 * @author  jgauci
 */
public class WaitObjective extends Objective {
    
    /** Creates a new instance of WaitAI */
    public WaitObjective() {
    }
    
    /** Tries to initialize the AI, if the
     *  path cannot be created, returns false
     *  else returns true */
    public boolean initObjective(ObjectiveController owner,Object target)
    {
        return true;
    }
    
    public boolean setNextCommand(Command command) {
        return true;
    }
    
}
