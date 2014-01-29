/*
 * MoveAI.java
 *
 * Created on September 11, 2003, 3:03 PM
 */

package IsometricGame.Server.Objective;

import IsometricGame.*;
import IsometricGame.Entity.*;
import IsometricGame.Server.*;
import IsometricGame.Item.Item;
import IsometricGame.Net.PacketOperator;

import java.util.*;

/**
 *
 * @author  jgauci
 */
public class GetObjective extends Objective {
   Stack path;
   Item target;
   ObjectiveController owner;
   
   /** Creates a new instance of MoveAI */
   public GetObjective() {
   }
   
   /** Tries to initialize the AI, if the
    *  path cannot be created, returns false
    *  else returns true */
   public boolean initObjective(ObjectiveController owner,Item target) {
      System.out.println("trying to get " + target);
      this.target = target;
      this.owner = owner;
      path = owner.sourceMapServer.setAvatarPath(owner.sourceAvatar, target.location.x, target.location.y, target.location.z);
      return true;
   }
   
   public boolean setNextCommand(Command nextCommand) {
      if(owner.owner.playerHeldItem!=null||(!owner.sourceMapServer.itemTable.containsKey(target.ID))) {
	 owner.setObjective(Objective.WAIT,null,null);
	 return false;
      }
      if(path.empty()) {
	 System.out.println("Trying to get " + target);
	 owner.sourceMapServer.removeItemFromGround(target.ID);
	 owner.owner.playerHeldItem = target;
	 System.out.println("Player grabbed " + owner.owner.playerHeldItem + " " + target);
	 try {
	    owner.owner.dout.writeByte(PacketOperator.HELD_ITEM_ADDED);
	    owner.owner.dout.writeInt(target.ID.intValue());
	 }
	 catch(Exception e) {
	    e.printStackTrace();
	 }
	 owner.setObjective(Objective.WAIT, null, null);
	 return false;
      }
      Point3D.Int location = owner.sourceAvatar.getLocation();
      int x=location.x,y=location.y,z=location.z;
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
	 //if((target.location).distance(x,y,z)==0) {
	 owner.setObjective(Objective.WAIT, null, null);
	 return false;
	 //}
	 //else {
	 //    owner.setObjective(Objective.MOVE, target);
	 //    return false;
	 //}  Need to this about this before I implement.
      }
      owner.sourceMapServer.moveAvatar(owner.sourceAvatar.avatarID, x, y, z);
      
      return true;
   }
   
}
