/*
 * ItemType.java
 *
 * Created on August 13, 2003, 6:45 PM
 */

package IsometricGame.Item;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.swing.*;

/**
 *
 * @author  jgauci
 */
public abstract class ItemType implements java.io.Serializable {
    public Dimension imageStart,imageSize;
    public byte weight;
    public String species;
    public String name;
    public transient BufferedImage image,groundImage;
    
    /** Creates a new instance of ItemType */
    public ItemType(Dimension imageStart,Dimension imageSize,String name,byte weight) {
        this.imageStart = imageStart;
        this.imageSize = imageSize;
        this.weight = weight;
        this.name = name;
    }
    
    public ItemType(Dimension start,Dimension size,Container settings) {
        imageStart = start;
        imageSize = size;
        JTextField itemName = new JTextField();
        itemName.addKeyListener(new java.awt.event.KeyListener() {
                        public void keyTyped(java.awt.event.KeyEvent evt) {
                            name = ((JTextField)evt.getSource()).getText();
                        }
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            name = ((JTextField)evt.getSource()).getText();
                        }
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            name = ((JTextField)evt.getSource()).getText();
                        }
                    });
        SpinnerNumberModel weightSpinnerModel = new javax.swing.SpinnerNumberModel(1,1,127,1);
        weightSpinnerModel.addChangeListener(new javax.swing.event.ChangeListener() {
                        public void stateChanged(javax.swing.event.ChangeEvent evt) {
                            weight = ((SpinnerNumberModel)evt.getSource()).getNumber().byteValue();
                        }
                    });
        JSpinner weightSpinner = new JSpinner(weightSpinnerModel);
        settings.add(new JLabel("Name:"));
        settings.add(itemName);
        settings.add(new JLabel("Weight:"));
        settings.add(weightSpinner);
    }
    
    public void loadImage(BufferedImage itemMap) {
        System.out.println(imageStart.width+" "+imageStart.height+" "+imageSize.width+" "+imageSize.height);
        image = itemMap.getSubimage(imageStart.width*50,imageStart.height*50,imageSize.width*50,imageSize.height*50);
	groundImage = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(60,30,Transparency.BITMASK);
	Graphics g = groundImage.getGraphics();
        g.drawImage(image, 0, 0, 60, 30, null);
    }
    
}
