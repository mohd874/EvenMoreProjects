/*
 * JApplet.java
 *
 * Created on August 27, 2003, 11:41 AM
 */

package IsometricGame.Client;

import IsometricGame.Net.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.*;


/**
 *
 * @author  jgauci
 */
public class GameApplet extends javax.swing.JApplet implements Loader {
    MediaTracker tracker;
    String fileBase;
    
    /** Creates a new instance of JApplet */
    public GameApplet() {
        
    }
    
    public void start() {
        fileBase = this.getCodeBase()+"IsometricGame/";
        tracker = new MediaTracker(new JPanel());
        this.getContentPane().add(new InitPanel(this));
    }
    
    public BufferedImage loadBufferedImage(String location, boolean translucent) {
       Image tempImage;
        try {
            tempImage = Toolkit.getDefaultToolkit().getImage(new URL(fileBase+"images/" + location));
            tracker.addImage(tempImage,0);
            tracker.waitForAll();
        }
        catch(Exception e) {
            e.printStackTrace();
	    return null;
        }
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage tempBuffImage;
        if(translucent)
            tempBuffImage = gc.createCompatibleImage(tempImage.getWidth(null), tempImage.getHeight(null), Transparency.TRANSLUCENT);
        else
            tempBuffImage = gc.createCompatibleImage(tempImage.getWidth(null), tempImage.getHeight(null), Transparency.BITMASK);
        tempBuffImage.createGraphics().drawImage(tempImage,null,null);
        return tempBuffImage;
    }
    
    public ObjectInputStream getObjectInputStream(String location) {
        try {
            ObjectInputStream din = new ObjectInputStream(new URL(fileBase+"images/" + location).openStream());
            return din;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public BufferedReader getBufferedReader(String location) {
        try {
            BufferedReader bin = new BufferedReader(new InputStreamReader(new URL(fileBase+"text/" + location).openStream()));
            return bin;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void exit() {
        this.stop();
        this.destroy();
    }
    
    public VolatileImage loadVolatileImage(String location) {
        System.out.println("Loading" + location);
        Image tempImage = Toolkit.getDefaultToolkit().getImage(fileBase + "images/" + location);
        tracker.addImage(tempImage,0);
        try {
            tracker.waitForAll();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage tempBuffImage;
        return gc.createCompatibleVolatileImage(tempImage.getWidth(null), tempImage.getHeight(null));
    }
    
}
