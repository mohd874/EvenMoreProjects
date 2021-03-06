/*
 * PlayerClient.java
 *
 * Created on July 8, 2003, 9:22 PM
 */

package IsometricGame.Client;

import IsometricGame.Entity.*;
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
public class PlayerClient extends GameClient {
    final static int VIEW_RADIUS = 15;
    Vector avatars;
    PlayerStatsPanel statsPanel;
    PlayerInventoryPanel inventoryPanel;
    
    /** Creates a new instance of PlayerClient */
    public PlayerClient(JFrame gameFrame,JPanel gameWindow, JTabbedPane sideBar, Loader owner,Hashtable tabNames,ObjectInputStream din,ImprovedObjectOutputStream dout) {
        super(gameFrame,gameWindow, sideBar, owner, tabNames,din,dout);
        avatars = new Vector();
        
        statsPanel = new PlayerStatsPanel();
        sideBar.setComponentAt(0,statsPanel);
        inventoryPanel = new PlayerInventoryPanel(this);
        sideBar.setComponentAt(1,inventoryPanel);
        
        try {
            dout.writeByte(CREATE_AVATAR);
            dout.writeInt(4);
            dout.writeInt(4);
            dout.writeInt(onHeight);
            //dout.writeByte(CREATE_ITEM);
            //dout.writeInt(4);
            //dout.writeInt(4);
            //dout.writeInt(onHeight);
            //dout.writeByte(0);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void initButtons() {
        super.initButtons();
    }
    
    public void mouseMoved(java.awt.event.MouseEvent evt) {
        //System.out.println("Moved to " + evt.getPoint());
        currentPoint.x = evt.getX();
        currentPoint.y = evt.getY();
        screenToTile(evt.getPoint());
        //System.out.println(tileCurrent);
    }
    
    public void mousePressed(java.awt.event.MouseEvent evt) {
    }
    
    public void mouseEntered(java.awt.event.MouseEvent evt) {
    }
    
    public void mouseExited(java.awt.event.MouseEvent evt) {
    }
    
    public void mouseReleased(java.awt.event.MouseEvent evt) {
    }
    
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        try {
            System.out.println("Clicked " + evt.getPoint());
            screenToTile(evt.getPoint());
            if(evt.getButton()==evt.BUTTON1) {
                System.out.println(avatarImage.getPixel(evt.getX(),evt.getY())&511);
                if((avatarImage.getPixel(evt.getX(),evt.getY())&511)!=0) {
                    avatarSelected = (Avatar)currentMap.avatarTable.get(new Integer(avatarImage.getPixel(evt.getX(),evt.getY())&511));
                    Point3D.Int tempPoint = avatarSelected.getLocation();
                    inventoryPanel.update();
                }
                else {
                    if(avatarSelected!=null) {
                    }
                }
            }
            else {
	       if(mouseHeldItem!=null)
	       {
		  dout.writeByte(THROW_ITEM);
		  dout.improvedWriteObject(tileCurrent);
	       }
	       else if(avatarSelected!=null) {
                    if((avatarImage.getPixel(evt.getX(),evt.getY())&511)!=0) {
                        //dout.writeByte();
                        //dout.writeInt(tileSelected.x);
                        //dout.writeInt(tileSelected.y);
                        //dout.writeInt(tileSelected.z);
                        //dout.writeInt(avatarImage.getPixel(evt.getX(),evt.getY())&511);
                        //dout.writeInt(tileCurrent.y);
                        //dout.writeInt(tileCurrent.z);
                    }
                    else {
                        dout.writeByte(CHANGE_AVATAR_OBJECTIVE);
                        dout.writeInt(avatarSelected.avatarID.intValue());
                        dout.writeByte(MOVE_OBJECTIVE);
                        dout.improvedWriteObject(tileCurrent);
                        tileSelected.setLocation(tileCurrent);
                        System.out.println("DONE");
                    }
                }
                
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void mouseDragged(java.awt.event.MouseEvent evt) {
    }
    
    public void updateSideBar() {
        statsPanel.update(avatarSelected);
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
                    dout.writeChars("myMap.imp");
                    //((MapBuilderStandalone)ioLoader).saveMap(gameMap,"myMap.imp");
                }
                catch(Exception exp) {
                    exp.printStackTrace();
                }
                break;
            case 'L':
                try {
                    dout.writeByte(LOAD_MAP_REQUEST);
                    dout.writeChars("myMap.imp");
                    //((MapBuilderStandalone)ioLoader).loadMap(gameMap,"myMap.imp");
                }
                catch(Exception exp) {
                    exp.printStackTrace();
                }
                break;
            case 'H':
                JFrame myFrame = new JFrame();
                myFrame.setVisible(true);
                new TextViewer(myFrame,true,owner.getBufferedReader("playerInstructions.txt")).show();
        }
    }
    
}

