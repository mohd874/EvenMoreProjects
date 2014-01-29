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
public class MoveObjective extends Objective {
    Stack path;
    Point3D.Int target;
    ObjectiveController owner;
    
    /** Creates a new instance of MoveAI */
    public MoveObjective() {
    }
    
    /** Tries to initialize the AI, if the
     *  path cannot be created, returns false
     *  else returns true */
    public boolean initObjective(ObjectiveController owner,Point3D.Int target)
    {
        this.target = target;
        this.owner = owner;
        path = owner.sourceMapServer.setAvatarPath(owner.sourceAvatar, target.x, target.y, target.z);
        if(path==null)
            return false;
        return true;
    }
    
    public boolean setNextCommand(Command nextCommand)
    {
        Point3D.Int location = owner.sourceAvatar.getLocation();
        int x=location.x,y=location.y,z=location.z;
        if(path.empty())
        {
            owner.setObjective(Objective.WAIT, null, null);
            return false;
        }
        switch(((Byte)path.pop()).byteValue()) {
            case Command.DOWNRIGHT:
                x = location.x+1;
                break;
            case Command.DOWNLEFT:
                y = location.y+1;
                break;
            case Command.UPLEFT:
                x = location.x-1;
                break;
            case Command.UPRIGHT:
                y = location.y-1;
                break;
            case Command.DOWN:
                x = location.x+1;
                y = location.y+1;
                break;
            case Command.LEFT:
                x = location.x-1;
                y = location.y+1;
                break;
            case Command.UP:
                x = location.x-1;
                y = location.y-1;
                break;
            case Command.RIGHT:
                x = location.x+1;
                y = location.y-1;
                break;
            case Command.CLIMB:
                z = location.z+1;
                break;
            case Command.FALL:
                z = location.z-1;
                break;
            default:
                x=y=z=-1;
        }
        if(owner.sourceMapServer.avatarMap[z][y][x]!=null) {
            //if(((Point3D.Int)target).distance(x,y,z)==0) {
                owner.setObjective(Objective.WAIT, null, null);
                return false;
            //}
            //else {
             //   owner.setObjective(Objective.MOVE, target);
             //   return false;
           // }  Need ot this about this before I implement.
        }
        owner.sourceMapServer.moveAvatar(owner.sourceAvatar.avatarID, x, y, z);
        
        return true;
    }
    
}
