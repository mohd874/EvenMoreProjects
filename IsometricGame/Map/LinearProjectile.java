/*
 * LinearProjectile.java
 *
 * Created on October 22, 2003, 3:03 PM
 */

package IsometricGame.Map;

import IsometricGame.Item.*;
import IsometricGame.*;


/**
 *
 * @author  jgauci
 */
public class LinearProjectile extends Projectile {
   
   /** Creates a new instance of LinearProjectile */
   public LinearProjectile(Item itemProjected,double velocityMag,Point3D.Int dest) throws Exception {
       
      this.itemProjected = itemProjected;
      this.destination = new Point3D.Double();
      this.destination.setLocation(dest.x+0.5D,dest.y+0.5D,dest.z+0.5D);
      this.position = new Point3D.Double();
      this.position.setLocation(itemProjected.location.x+0.5D,itemProjected.location.y+0.5D,itemProjected.location.z+0.5D);
      System.out.println("THROWING FROM " + position + " TO " + destination);
      if(position.equals(destination))
      {
	 Exception e = new Exception();
	 throw e;
      }
      velocity = new Point3D.Double();
      double timeToThrow = Math.sqrt( ( ( (destination.x-position.x)*(destination.x-position.x) )+( (destination.y-position.y)*(destination.y-position.y) )+( (destination.z-position.z)*(destination.z-position.z) )   )  / (velocityMag*velocityMag) );
      if(timeToThrow==Double.NaN)
	 throw new Exception();
      
      velocity.x = (destination.x-position.x)/timeToThrow;
      velocity.y = (destination.y-position.y)/timeToThrow;
      velocity.z = (destination.z-position.z)/timeToThrow;
      System.out.println("Velocity: " + velocity);
   }
   
}
