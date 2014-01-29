/*
 * Theme.java
 *
 * Created on July 11, 2003, 3:59 PM
 */

package IsometricGame;

import IsometricGame.Item.*;
import IsometricGame.Entity.*;
import IsometricGame.Map.*;
import IsometricGame.Net.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author  jgauci
 */
public class Theme {
    public static TileType[] tileTypes;
    public static Vector itemTypes;
    public static AvatarType[] avatarTypes;
    public static DoodadType[] doodadTypes;
    public static WallType[] wallTypes;
    public static final int tileWidth = 64;
    public static final int tileLength = 32;
    public static final int entityWidth = tileWidth;
    public static final int floorHeight = 96;
    public static final int entityHeight = floorHeight+(tileLength/2);
    public static final int wallWidth = tileWidth/2;
    public static final int wallHeight = floorHeight+(tileLength/2);
    public final static AlphaComposite drawHiddenComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    public final static AlphaComposite drawNormalComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);;
    public final static BasicStroke normalStroke = new BasicStroke(2.0f);
    public final static BasicStroke gridStroke = new BasicStroke(3.0f);
    //public final static BasicStroke wideStroke = new BasicStroke(8.0f);
    public static BufferedImage itemOnTile;
    public static BufferedImage fogofwar;
    public final static byte NOITEM=-127,GENERIC=0,WEAPON=1;
    
    /** Creates a new instance of Theme */
    public Theme(Loader owner,String fogofwarFile,String tileImageFile, String wallImageFile, String doodadImageFile, String itemImageFile,
    String avatarImageFile) throws Exception {
        this.fogofwar = owner.loadBufferedImage(fogofwarFile,false);
        this.itemOnTile = owner.loadBufferedImage("item.png",false);
        BufferedImage tileImage = owner.loadBufferedImage(tileImageFile,false);
        BufferedImage wallImage = owner.loadBufferedImage(wallImageFile,true);
        BufferedImage doodadImage = owner.loadBufferedImage(doodadImageFile,false);
        BufferedImage itemImage = owner.loadBufferedImage(itemImageFile,false);
        BufferedImage avatarImage = owner.loadBufferedImage(avatarImageFile,false);
        ObjectInputStream din;
        
        din = owner.getObjectInputStream(tileImageFile.substring(0,tileImageFile.length()-3)+"dat");
        tileTypes = new TileType[tileImage.getWidth()/tileWidth];
        for(int a=0;a<tileTypes.length;a++)
	{
	    //BufferedImage tempImage = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(tileWidth,tileLength,Transparency.TRANSLUCENT);
	    //tempImage.getGraphics().drawImage(tileImage.getSubimage(a*tileWidth, 0, tileWidth, tileLength),0,0,null);
	    tileTypes[a] = new TileType(tileImage.getSubimage(a*tileWidth, 0, tileWidth, tileLength),din.readInt());
	}
        din.close();
        
        din = owner.getObjectInputStream(wallImageFile.substring(0,wallImageFile.length()-3)+"dat");
        wallTypes = new WallType[wallImage.getWidth()/wallWidth];
        AffineTransform leftWallTransform = new AffineTransform();
        leftWallTransform.setToTranslation(0, wallWidth/2);
        leftWallTransform.shear(0,  -.5);
        for(int a=0;a<wallTypes.length;a++) {
            BufferedImage tempLeftImage = new BufferedImage(wallWidth,wallHeight,BufferedImage.TYPE_4BYTE_ABGR);
            BufferedImage tempRightImage = new BufferedImage(wallWidth,wallHeight,BufferedImage.TYPE_4BYTE_ABGR);
            tempLeftImage.createGraphics().drawImage(wallImage.getSubimage(a*wallWidth, 0, wallWidth, wallImage.getHeight()),leftWallTransform,null);
            tempRightImage.createGraphics().drawImage(tempLeftImage,tempLeftImage.getWidth(),0, 0, tempLeftImage.getHeight(), 0, 0, tempLeftImage.getWidth(),tempLeftImage.getHeight(),null);
            
            wallTypes[a] = new WallType(tempLeftImage,tempRightImage,din.readInt());
        }
        din.close();
        
        
        din = owner.getObjectInputStream(itemImageFile.substring(0,itemImageFile.length()-3)+"dat");
        itemTypes = new Vector();
        byte type;
        while(true) {
            type=din.readByte();
            if(type==-1)
                break;
			//type=GENERIC;
            //BufferedImage tempLeftImage = itemImage.getSubimage(a*entityWidth, 0, entityWidth, entityHeight);
            //BufferedImage tempRightImage = new BufferedImage(tempLeftImage.getWidth(),tempLeftImage.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
            //tempRightImage.createGraphics().drawImage(tempLeftImage,tempLeftImage.getWidth(),0, 0, tempLeftImage.getHeight(), 0, 0, tempLeftImage.getWidth(),tempLeftImage.getHeight(),null);
            switch (type)
            {
                case ItemOperator.RANGEDWEAPON:
                    RangedWeaponType tempType = (RangedWeaponType) din.readObject();
                    System.out.println("read gun with clipsize: " + tempType.clipSize + " and delay " + tempType.delay);
                    tempType.loadImage(itemImage);
                    itemTypes.add(tempType);
                    break;
            }
            
            
            System.out.println(""+entityHeight);
            
        }
		//din.close()
        
        din = owner.getObjectInputStream(doodadImageFile.substring(0,doodadImageFile.length()-3)+"dat");
        doodadTypes = new DoodadType[doodadImage.getWidth()/tileWidth];
        for(int a=0;a<doodadTypes.length;a++) {
            System.out.println(""+entityHeight);
            BufferedImage tempImage = doodadImage.getSubimage(a*entityWidth, 0, entityWidth, entityHeight);
            
            doodadTypes[a] = new DoodadType(tempImage,din.readInt());
        }
        din.close();
        
        avatarTypes = new AvatarType[avatarImage.getWidth()/tileWidth];
        //System.out.println("# of avatar images: " + avatarImage.getWidth());
        for(int a=0;a<avatarTypes.length;a++) {
            BufferedImage tempLeftImage = avatarImage.getSubimage(a*entityWidth, 0, entityWidth, entityHeight);
            BufferedImage tempRightImage = new BufferedImage(tempLeftImage.getWidth(),tempLeftImage.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
            tempRightImage.createGraphics().drawImage(tempLeftImage,tempLeftImage.getWidth(),0, 0, tempLeftImage.getHeight(), 0, 0, tempLeftImage.getWidth(),tempLeftImage.getHeight(),null);
            
            avatarTypes[a] = new AvatarType(tempLeftImage,tempRightImage);
        }
    }
    
}
