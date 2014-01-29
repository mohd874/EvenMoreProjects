/*
 * Projectile.java
 *
 * Created on September 22, 2003, 6:46 PM
 */

package IsometricGame.Map;

import IsometricGame.Item.*;
import IsometricGame.*;

/**
 *
 * @author  jgauci
 */
public class Projectile {
   public Item itemProjected;
   Point3D.Double velocity;
   public Point3D.Double position;
   public Point3D.Double destination;
   
   public Projectile()
   {
       
   }
   
   /** Creates a new instance of Projectile */
   public Projectile(Item itemProjected,double velocityMag,Point3D.Int dest) throws Exception {
      this.itemProjected = itemProjected;
      this.destination = new Point3D.Double();
      this.destination.setLocation(dest.x+0.5D,dest.y+0.5D,dest.z);
      this.position = new Point3D.Double();
      this.position.setLocation(itemProjected.location.x+0.5D,itemProjected.location.y+0.5D,itemProjected.location.z+0.5D);
      System.out.println("THROWING FROM " + position + " TO " + destination);
      if(position.equals(destination))
      {
	 Exception e = new Exception();
	 throw e;
      }
      velocity = new Point3D.Double();
      double timeToThrow = this.getSmallestPositiveQuadraticSolution(.0001D, ((destination.z-position.z)/50.0D) -(velocityMag*velocityMag), 
	 (((destination.x-position.x)*(destination.x-position.x))+((destination.y-position.y)*(destination.y-position.y))+((destination.z-position.z)*(destination.z-position.z))) - (destination.z-position.z));
      if(timeToThrow==-1)
	 throw new Exception();
      
      velocity.x = (destination.x-position.x)/timeToThrow;
      velocity.y = (destination.y-position.y)/timeToThrow;
      velocity.z = (destination.z-position.z)/timeToThrow + (timeToThrow/100.0D);
      System.out.println("Velocity: " + velocity);
   }
   
   /* a(x^2) + b(x) + c = 0
    */
   public double getSmallestPositiveQuadraticSolution(double a,double b,double c)
   {
      System.out.println("QUADRATIC IS RUNNING " + a + "x^2 + " + b + "x + " + c + " = 0");
      double temp = (b*b) - (4*a*c);
      if(temp<0)
      {
	 System.out.println("NO SOLUTION");
	 return -1;
      }
      double sol1 = (((-1*b) + Math.sqrt(temp))/(2*a));
      double sol2 = (((-1*b) - Math.sqrt(temp))/(2*a));
      System.out.println("solutions for time: " + sol1 + " " + sol2);
      if(sol1<=0&&sol2<=0)
	 return -1;
      else if(sol1<=0)
	 return Math.sqrt(sol2);
      else if(sol2<=0)
	 return Math.sqrt(sol1);
      else
	 return Math.min(Math.sqrt(sol1),Math.sqrt(sol2));
      
   }
   
   public boolean updatePosition(IsometricMap owner) {
      if((position.x+velocity.x)<0
      ||(position.y+velocity.y)<0
      ||(position.z+velocity.z)<0)
	 return false;
      destination.setLocation((position.x+velocity.x), (position.y+velocity.y), (position.z+velocity.z));
      if(!owner.projectileNotBlocked(position,destination))
      {
	 System.out.println("DROPPING " + this + " AT " + position);
	 return false;
	 //owner.dropProjectileAt(this,new Point3D.Int((int)position.x,(int)position.y,(int)position.z));
      }
      else
      {
	 position.x += velocity.x;
	 position.y += velocity.y;
	 position.z += velocity.z;
	 velocity.z -= (1.0D/50.0D);
	 System.out.println("UPDATING " + this + " TO " + position);
	 return true;
      }
      
      
   }

   public Point3D.Int projectileToMapCoord() {
        return new Point3D.Int((int)(Theme.tileWidth/2*position.x-Theme.tileWidth/2*position.y -Theme.tileWidth/2)
        ,(int)(Theme.tileLength/2*position.x+Theme.tileLength/2*position.y -position.z*Theme.floorHeight),0);
    }
   
   public void draw(java.awt.Graphics2D g2d,java.awt.Point cameraLoc)
   {
      Point3D.Int tempPoint = projectileToMapCoord();
      g2d.drawImage(itemProjected.getModel().image,tempPoint.x-cameraLoc.x,tempPoint.y-cameraLoc.y,60,30, null);
   }
   
}
