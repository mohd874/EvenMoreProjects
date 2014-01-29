/*
 * GameClient.java
 *
 * Created on July 8, 2003, 9:22 PM
 */

package IsometricGame.Client;

import IsometricGame.Item.*;
import IsometricGame.Client.*;
import IsometricGame.Net.*;
import IsometricGame.*;

import javax.swing.*;
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
import java.io.*;

/**
 *
 * @author  jgauci
 */
public class BuilderClient extends GameClient {
    int tileType,wallType,itemType,doodadType;
    
    /** Creates a new instance of GameClient */
    public BuilderClient(JFrame gameFrame,JPanel gameWindow, javax.swing.JTabbedPane sideBar, Loader owner,Hashtable tabNames,ObjectInputStream din,ImprovedObjectOutputStream dout) {
        super(gameFrame,gameWindow, sideBar, owner,tabNames,din,dout);
        tileType=wallType=itemType=doodadType=0;
    }
    
    public void initButtons() {
        super.initButtons();
        for(int a=0;a<Theme.tileTypes.length;a++)
            makeButton("Floors",Theme.tileTypes[a].tileImage,"0"+a);
        for(int a=0;a<Theme.wallTypes.length;a++)
            makeButton("Walls",Theme.wallTypes[a].leftImage,"1"+a);
        for(int a=0;a<Theme.doodadTypes.length;a++)
            makeButton("Doodads",Theme.doodadTypes[a].image,"2"+a);
        for(int a=0;a<Theme.itemTypes.size();a++)
            makeButton("Items",((ItemType)Theme.itemTypes.elementAt(a)).name,"3"+a);
        makeButton("Options",  "Save", "S");
        makeButton("Options",  "Load", "L");
    }
    
    public void mouseMoved(java.awt.event.MouseEvent evt) {
        //System.out.println("Moved to " + evt.getPoint());
        currentPoint.x = evt.getX();
        currentPoint.y = evt.getY();
        screenToTile(evt.getPoint());
        //System.out.println(tileCurrent);
    }
    
    public void mousePressed(java.awt.event.MouseEvent evt) {
        System.out.println("Pressed " + (evt.getX()+cameraLoc.x) + "," + (evt.getY()+cameraLoc.y));
        clickPoint.x = evt.getX();
        clickPoint.y = evt.getY();
        screenToTile(evt.getPoint());
        tilePressed.setLocation(tileCurrent);
        
    }
    
    public void mouseEntered(java.awt.event.MouseEvent evt) {
    }
    public void mouseExited(java.awt.event.MouseEvent evt) {
        
    }
    
    public void mouseReleased(java.awt.event.MouseEvent evt) {
        System.out.println("Released " + evt.getPoint());
        isDragging=false;
        screenToTile(evt.getPoint());
        tileReleased.setLocation(tileCurrent);
        System.out.println(tileReleased.x+"   "+tileReleased.y);
        lasso.setFrameFromDiagonal(tilePressed.getHorizontalPoint(), tileReleased.getHorizontalPoint());
        if(lasso.height!=0||lasso.width!=0||barState!=1) {
            lasso.height++;
            lasso.width++;
        }
        System.out.println(lasso);
        if(barState==0||barState==1&&!isDragging) {
            //gameMap.createRoom();
            try {
                dout.writeByte(CREATE_TILE);
                dout.writeInt(lasso.x);
                dout.writeInt(lasso.y);
                dout.writeInt(lasso.width);
                dout.writeInt(lasso.height);
                dout.writeInt(onHeight);
                dout.writeByte(barState);
                if(barState==0)
                    dout.writeByte(tileType);
                else if(barState==1)
                    dout.writeByte(wallType);
                dout.flush();
            }
            catch(IOException ie) {
                ie.printStackTrace();
            }
            
        }
        //else if(barState==2||barState==3)
        //  lineOfSight(tilePressed,tileReleased);
        
        lasso.setFrameFromDiagonal(-1,-1,-1,-1);
    }
    
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        try {
            System.out.println("Clicked " + evt.getPoint() + ". Meta Keys: "+evt.getModifiersEx());
            screenToTile(evt.getPoint());
            //tileReleased.setLocation(tileCurrent);
            if(barState==1) {
                if(evt.getButton()==evt.BUTTON1) {
                    dout.writeByte(CREATE_WALL);
                    dout.writeInt(tileCurrent.x);
                    dout.writeInt(tileCurrent.y);
                    dout.writeInt(tileCurrent.z);
                    dout.writeByte(wallCurrent);
                    dout.writeByte(wallType);
                    //gameMap.createWall(tileReleased,wallCurrent,wallType);
                }
                else if(evt.getButton()==evt.BUTTON3||evt.getButton()==evt.BUTTON2) {
                    dout.writeByte(DELETE_WALL);
                    dout.writeInt(tileCurrent.x);
                    dout.writeInt(tileCurrent.y);
                    dout.writeInt(tileCurrent.z);
                    dout.writeByte(wallCurrent);
                    //gameMap.deleteWall(tileReleased,wallCurrent);
                }
            }
            else if(barState==2) {
                if(evt.getButton()==evt.BUTTON1) {
                    dout.writeByte(CREATE_DOODAD);
                    dout.writeInt(tileCurrent.x);
                    dout.writeInt(tileCurrent.y);
                    dout.writeInt(tileCurrent.z);
                    dout.writeByte(doodadType);
                    dout.flush();
                    ///gameMap.createObject(new Point(tileReleased),itemType);
                }
                else if(evt.getButton()==evt.BUTTON3||evt.getButton()==evt.BUTTON2) {
                    dout.writeByte(DELETE_DOODAD);
                    dout.writeInt(tileCurrent.x);
                    dout.writeInt(tileCurrent.y);
                    dout.writeInt(tileCurrent.z);
                    //gameMap.deleteObjectsAt(tileReleased);
                }
            }
            else if(barState==3) {
                if(evt.getButton()==evt.BUTTON1) {
                    dout.writeByte(CREATE_ITEM);
                    dout.writeByte(itemType);
                    dout.writeInt(tileCurrent.x);
                    dout.writeInt(tileCurrent.y);
                    dout.writeInt(tileCurrent.z);
                    //(new RangedWeapon(tileCurrent,itemType,-1, 100, 0)).send(dout);
                    ///gameMap.createObject(new Point(tileReleased),itemType);
                }
                else if(evt.getButton()==evt.BUTTON3||evt.getButton()==evt.BUTTON2) {
                    dout.writeByte(DELETE_ITEM);
                    dout.writeInt(tileCurrent.x);
                    dout.writeInt(tileCurrent.y);
                    dout.writeInt(tileCurrent.z);
                    //gameMap.deleteObjectsAt(tileReleased);
                }
            }
            dout.flush();
        }
        catch(IOException e) {
            System.out.println("Error sending data!!!");
        }
    }
    
    public void mouseDragged(java.awt.event.MouseEvent evt) {
        //System.out.println("Dragged " + evt.getPoint()+ "Line of Angle: " + 180/Math.PI*Math.atan2(currentPoint.y-clickPoint.y,currentPoint.x-clickPoint.x));
        currentPoint.x = evt.getX();
        currentPoint.y = evt.getY();
        screenToTile(evt.getPoint());
        tileReleased.setLocation(tileCurrent);
        System.out.println(tileReleased.x+"   "+tileReleased.y);
        lasso.setFrameFromDiagonal(tilePressed.getHorizontalPoint(), tileReleased.getHorizontalPoint());
        lasso.height++;
        lasso.width++;
        System.out.println(lasso);
        //highlight.setFrameFromDiagonal(clickPoint.x, clickPoint.y, evt.getX(), evt.getY());
        isDragging=true;
    }
    
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("R1")) {
            if(onHeight+1<currentMap.height) {
                cameraLoc.y-=Theme.floorHeight;
                onHeight++;
            }
            if(onHeight>viewHeight)
                viewHeight=onHeight;
            return;
        }
        if(e.getActionCommand().equals("L1")) {
            if(onHeight>0) {
                cameraLoc.y+=Theme.floorHeight;
                onHeight--;
                viewHeight--;
            }
            return;
        }
        if(e.getActionCommand().equals("R2")) {
            if(viewHeight+1<currentMap.height)
                viewHeight++;
            return;
        }
        if(e.getActionCommand().equals("L2")) {
            if(viewHeight>onHeight&&viewHeight>0)
                viewHeight--;
            return;
        }
        switch(e.getActionCommand().charAt(0)) {
            case '0':
                tileType = (int)(e.getActionCommand().charAt(1)-'0');
                break;
            case '1':
                wallType = ((int)(e.getActionCommand().charAt(1)-'0'));
                break;
            case '2':
                doodadType = ((int)(e.getActionCommand().charAt(1)-'0'));
                break;
            case '3':
                itemType = ((int)(e.getActionCommand().charAt(1)-'0'));
                
                break;
            case 'G':
                gridOn = !gridOn;
                break;
            case 'T':
                alphaOn = !alphaOn;
                break;
            case 'N':
                wallsOn = !wallsOn;
                break;
            case 'S':
                try {
                    dout.writeByte(SAVE_MAP_REQUEST);
                    //((MapBuilderStandalone)ioLoader).saveMap(gameMap,"myMap.imp");
                }
                catch(Exception exp) {
                    exp.printStackTrace();
                }
                break;
            case 'L':
                try {
                    dout.writeByte(LOAD_MAP_REQUEST);
                    dout.writeUTF("myMap.imp");
                    //((MapBuilderStandalone)ioLoader).loadMap(gameMap,"myMap.imp");
                }
                catch(Exception exp) {
                    exp.printStackTrace();
                }
                break;
        }
    }
}

