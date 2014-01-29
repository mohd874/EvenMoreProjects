/*
 * MapBuilder.java
 *
 * Created on June 17, 2003, 12:21 PM
 */

package IsometricGame.Server;

import IsometricGame.Entity.*;
import IsometricGame.Map.*;
import IsometricGame.*;
import IsometricGame.Server.Objective.*;

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
import javax.sound.sampled.*;
import java.lang.reflect.*;
import java.io.*;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

/** This class manages the maps and handles the player-map interactions.
 * @author jgauci
 */
public class GameEngine {
    int frame;
    IsometricMapServer gameMap;
    GameServer owner;
    Timer gameUpdate;
    private int nextID;
    public Random r;
    
    /** Creates a new instance of GameEngine
     * @param owner The GameServer that owns this Engine.
     */
    public GameEngine(GameServer owner) {
        this.owner = owner;
        r = new Random();
        int mapLength=16,mapWidth=16,mapHeight=1;
        //backImage = (BufferedImage) gameWindow.createImage(mapHeight,mapWidth);
        //backBuffer = (Graphics2D) backImage.getGraphics();
        frame=0;
        try {
            new Theme(owner,"fogofwar.gif","isograss.gif","castleWall.png","doodads.png","items.png","player.gif");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        //gameMap = new IsometricMapServer(mapLength,mapWidth,mapHeight,owner,this);
        gameMap = new IsometricMapServer(new File(owner.fileBase+"maps/mymap.xml"),owner,this);
        gameUpdate = new Timer();
        gameUpdate.scheduleAtFixedRate(new GameTask(),0,1000/30);
        nextID = 1;
        
    }
    
    /** Saves a map to XML */    
    public void saveMap()
    {
        Document mapDoc = gameMap.saveMap();
        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(mapDoc);
            StreamResult result = new StreamResult(new File(owner.fileBase+"maps/mymap.xml"));
            transformer.transform(source, result);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    /** Removes a client and anything that belongs to him/her from the game.
     * @param connectionID The ID of the player to be removed.
     */    
    public void removePlayer(Integer connectionID) {
        gameMap.removePlayer(connectionID);
    }
    
    /** gets a new unique ID for players/items/etc.
     * @return The unique ID
     */    
    public Integer getNewID() {
        nextID++;
        return new Integer(nextID);
    }
    
    
    
    class GameTask extends TimerTask {
        public void run() {
            frame = (frame+1)%60000;
            synchronized(gameMap) {
	       gameMap.update();
                if(frame%20==0) {
                    for(Enumeration a = gameMap.avatarTable.elements();a.hasMoreElements();) {
                        Avatar tempAvatar = ((Avatar)a.nextElement());
                        updateAvatar(tempAvatar);
                    }
                }
                
            }
            //if(g2d==null) return;
            //backBuffer.setClip(cameraLoc.x,cameraLoc.y, gameWindow.getWidth(),gameWindow.getHeight());
            //g2d.setClip(0,0, 10000,10000);
            
        }
        
        public void updateAvatar(Avatar tempAvatar) {
            ((ObjectiveController)gameMap.objectiveTable.get(tempAvatar)).processNextCommand();
        }
    }
    
}
