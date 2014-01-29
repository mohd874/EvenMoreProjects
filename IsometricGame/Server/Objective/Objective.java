/*
 * Objective.java
 *
 * Created on September 12, 2003, 5:44 PM
 */

package IsometricGame.Server.Objective;

import IsometricGame.Net.PacketOperator;

/**
 *
 * @author  jgauci
 */
public abstract class Objective {
    public final static byte WAIT=PacketOperator.WAIT_OBJECTIVE,MOVE=PacketOperator.MOVE_OBJECTIVE,ATTACK=PacketOperator.ATTACK_OBJECTIVE,GET=PacketOperator.GET_OBJECTIVE,DROP=PacketOperator.DROP_OBJECTIVE;
    
    /** Creates a new instance of Objective */
    public Objective() {
    }
    
    /** Gets the next Command for an Objective
     * @param nextCommand The command to be set
     * @return Returns true if the command was set and false if it was not.
     */    
    public abstract boolean setNextCommand(Command nextCommand);
}
