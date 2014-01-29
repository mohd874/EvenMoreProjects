/*
 * Avatar.java
 *
 * Created on July 1, 2003, 9:07 AM
 */

package IsometricGame.Entity;

import IsometricGame.*;
import IsometricGame.Item.*;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.event.KeyEvent.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.net.*;

/**
 *
 * @author  Jason
 */
public class Avatar extends Entity {
    public Integer ownerID,avatarID;
    boolean facingRight;
    protected Point3D.Int location;
    byte objective;
    Color avatarColor;
    public int stats[];
    public long exp;
    public int health,maxHealth,stamina,maxStamina,energy,maxEnergy;
    public int level,experience;
    LinkedList enemiesInSight;
    public Item equipped;
    public Backpack inventory;
    //public Race race;
    
    /** Creates a new instance of Avatar */
    public Avatar(Point3D.Int location,Integer ownerID,Integer avatarID) {
        this.ownerID = ownerID;
        this.avatarID = avatarID;
        facingRight=true;
        this.location = location;
        avatarColor = new Color(avatarID.intValue(),false);
        System.out.println("CREATING AVATAR WITH ID: " + avatarID + " AND COLOR " + (avatarColor.getRGB() & 511) );
        //race = Theme.races[0];
        health=maxHealth=60;
        stamina=maxStamina=60;
        level = 1;
        experience = 0;
        energy = maxEnergy = 60;
        inventory = new Backpack(6,3);
    }
    
    public Point3D.Int getLocation()
    {
        return location;
    }
    
    public void setLocation(int x,int y,int z)
    {
        location.setLocation(x,y,z);
    }
    
    /*public Point avatarToMapCoord() {
        System.out.println("LOCATION: " + location);
        return new Point(Theme.tileWidth/2*location.x-Theme.tileWidth/2*location.y -Theme.tileWidth/2
        ,Theme.tileLength/2*location.x+Theme.tileLength/2*location.y - Theme.wallHeight+Theme.tileLength);
    }*/
    
    public void draw(Graphics2D g,PixelImage key,int xPos,int yPos) {
        //if(facingRight)
        BufferedImage tempImage = Theme.avatarTypes[0].rightImage;
        g.drawImage(Theme.avatarTypes[0].rightImage, xPos, yPos, null);
        
        key.fillImage(xPos,yPos,tempImage,avatarColor);
    }
    
    
    public boolean damage(int amount) {
        if(health<=0)
            return true;
        health-=amount;
        if(health <= 0)
            return true;
        else
            return false;
    }
    
    public void changeObjective(byte newObjective) {
        objective = newObjective;
    }
    
}
