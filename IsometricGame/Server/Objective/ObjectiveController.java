/*
 * ObjectiveController.java
 *
 * Created on September 12, 2003, 6:00 PM
 */

package IsometricGame.Server.Objective;

import IsometricGame.Entity.*;
import IsometricGame.Server.*;
import IsometricGame.*;

/** Controls, switches, and updates Objectives.
 * @author jgauci
 */
public class ObjectiveController {
   MoveObjective moveObjective;
   WaitObjective waitObjective;
   GetObjective getObjective;
   DropObjective dropObjective;
   Avatar sourceAvatar;
   Objective currentObjective;
   IsometricMapServer sourceMapServer;
   Command command;
   GameServer.GameConnection owner;
   
   /** Creates a new instance of ObjectiveController */
   public ObjectiveController(Avatar source,IsometricMapServer sourceMapServer) {
      this.sourceAvatar = source;
      this.sourceMapServer = sourceMapServer;
      moveObjective = new MoveObjective();
      waitObjective = new WaitObjective();
      getObjective = new GetObjective();
      dropObjective = new DropObjective();
      currentObjective = waitObjective;
      command = new Command();
   }
   
   public void setObjective(byte type,Object target,GameServer.GameConnection owner) {
      this.owner = owner;
      switch(type) {
	 case Objective.WAIT:
	    if(waitObjective.initObjective(this,null)) {
	       currentObjective = waitObjective;
	       return;
	    }
	    break;
	 case Objective.MOVE:
	    if(moveObjective.initObjective(this,(Point3D.Int)target)) {
	       currentObjective = moveObjective;
	       return;
	    }
	    break;
	 case Objective.GET:
	    if(owner.playerHeldItem==null&&getObjective.initObjective(this,(IsometricGame.Item.Item)sourceMapServer.itemTable.get(((Integer)target)))) {
	       currentObjective = getObjective;
	       return;
	    }
	    break;
	 case Objective.DROP:
	    System.out.println("Player is holding " + owner.playerHeldItem);
	    if(owner.playerHeldItem!=null&&dropObjective.initObjective(this,(Point3D.Int)target)) {
	       System.out.println("Setting drop objective");
	       currentObjective = dropObjective;
	       return;
	    }
	    break;
      }
      currentObjective = waitObjective;
      return;
   }
   
   /** Pulls back the next command for an Avatar. */   
   public void processNextCommand() {
      while(!currentObjective.setNextCommand(command)) {
      }
      
   }
   
}
