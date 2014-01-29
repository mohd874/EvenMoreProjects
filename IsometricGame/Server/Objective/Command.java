/*
 * Command.java
 *
 * Created on September 11, 2003, 2:59 PM
 */

package IsometricGame.Server.Objective;

/** This represents a single Command to an Avatar
 * @author jgauci
 */
public class Command {
    byte commandType;
    Object commandTarget;
    
    public final static byte WAIT=0,UPRIGHT=1,RIGHT=2,DOWNRIGHT=3,DOWN=4,DOWNLEFT=5,LEFT=6,UPLEFT=7,UP=8,CLIMB=9,FALL=10;
    
    /** Creates a new instance of Command */
    public Command() {
    }
    
    /** Sets the command to a certain command
     * @param commandType The type of the command
     * @param commandTarget The Target of the command
     */    
    public void setCommand(byte commandType,Object commandTarget)
    {
        this.commandType = commandType;
        this.commandTarget = commandTarget;
    }
    
}
