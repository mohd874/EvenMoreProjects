/*
 * MapPlayerStandalone.java
 *
 * Created on August 27, 2003, 12:22 PM
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
public class GameStandalone implements Loader {
    MediaTracker tracker;
    String fileBase;
    
    /** Creates a new instance of MapPlayerStandalone */
    public GameStandalone() {
        fileBase = "./IsometricGame/";
        JFrame initFrame = new JFrame();
        initFrame.setSize(400,300);
        initFrame.getContentPane().add(new InitPanel(this));
        initFrame.setVisible(true);
        initFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });
        tracker = new MediaTracker(initFrame);
        //PlayerFrame gameFrame = new PlayerFrame(this,"127.0.0.1");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new GameStandalone();
    }
    
    public java.awt.image.BufferedImage loadBufferedImage(String location,boolean translucent) {
        /*System.out.println("Loading" + location);
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
        if(translucent)
            tempBuffImage = gc.createCompatibleImage(tempImage.getWidth(null), tempImage.getHeight(null), Transparency.TRANSLUCENT);
        else
            tempBuffImage = gc.createCompatibleImage(tempImage.getWidth(null), tempImage.getHeight(null), Transparency.BITMASK);
        tempBuffImage.createGraphics().drawImage(tempImage,null,null);
        return tempBuffImage;*/
       try
       {
        return javax.imageio.ImageIO.read(new File(fileBase + "images/" + location));
       }
       catch(Exception e)
       {
	  e.printStackTrace();
       }
       return null;
    }
    
    public ObjectInputStream getObjectInputStream(String location) {
        try {
            ObjectInputStream din = new ObjectInputStream(new FileInputStream(new File(fileBase+"images/" + location)));
            return din;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public BufferedReader getBufferedReader(String location) {
        try {
            BufferedReader bin = new BufferedReader(new FileReader(fileBase + "text/" + location));
            return bin;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void exit() {
        System.exit(0);
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
        VolatileImage tempBuffImage = gc.createCompatibleVolatileImage(tempImage.getWidth(null), tempImage.getHeight(null));
        tempBuffImage.createGraphics().drawImage(tempImage,null,null);
        return tempBuffImage;
    }
    
}
