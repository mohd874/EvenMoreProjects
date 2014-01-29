/*
 * InventoryPanel.java
 *
 * Created on September 10, 2003, 10:38 AM
 */

package IsometricGame.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

import IsometricGame.Item.*;
import IsometricGame.Net.PacketOperator;

/**
 *
 * @author  jgauci
 */
public class InventoryPanel extends javax.swing.JPanel {
   PlayerInventoryPanel owner;
   
   /** Creates new form InventoryPanel */
   public InventoryPanel(PlayerInventoryPanel owner) {
      initComponents();
      this.owner = owner;
      this.setVisible(false);
   }
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
    private void initComponents() {//GEN-BEGIN:initComponents

        setLayout(new java.awt.GridBagLayout());

        setMinimumSize(new java.awt.Dimension(300, 200));
        setPreferredSize(new java.awt.Dimension(300, 200));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

    }//GEN-END:initComponents
    
    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
       System.out.println(evt.getPoint());
       if(owner.owner.avatarSelected==null)
	  return;
       if(owner.owner.mouseHeldItem==null) {
	  //owner.owner.mouseHeldItem = items.getItemAt(evt.getX()/50,evt.getY()/50);
	  //int ID = items.getItem(evt.getX()/50,evt.getY()/50).ID;
	  //owner.dout.writeByte(PacketOperator.CHANGE_OBJECTIVE);
	  //owner.dout.writeByte(PacketOperator.
	  //if(owner.owner.mouseHeldItem==null)
	  //    return;
	  
	  //Cursor tempCursor = Toolkit.getDefaultToolkit().createCustomCursor(owner.owner.mouseHeldItem.getModel().image,new Point(0,0),"itemCursor");
	  //owner.setCursor(tempCursor);
	  try {
	     owner.owner.dout.writeByte(PacketOperator.GET_ITEM_FROM_BACKPACK);
	     owner.owner.dout.writeInt(owner.owner.avatarSelected.avatarID.intValue());
	     owner.owner.dout.writeInt(evt.getX()/50);
	     owner.owner.dout.writeInt(evt.getY()/50);
	  }
	  catch(java.io.IOException ioe) {
	     ioe.printStackTrace();
	  }
       }
       else {
		/*if(items.insertItem(owner.itemHeld,evt.getX()/50,evt.getY()/50)) {
		    owner.dout.writeByte(PacketOperator.MOVE_ITEM_IN_INVENTORY);
		    owner.dout.writeInt(owner.itemHeld.ID);
		    owner.dout.writeInt(evt.getX()/50);
		    owner.dout.writeInt(evt.getY()/50);
		    owner.itemHeld=null;
		    owner.setCursor(Cursor.getDefaultCursor());
		 *
		}*/
	  try {
	     owner.owner.dout.writeByte(PacketOperator.PUT_ITEM_IN_BACKPACK);
	     owner.owner.dout.writeInt(owner.owner.avatarSelected.avatarID.intValue());
	     owner.owner.dout.writeInt(evt.getX()/50);
	     owner.owner.dout.writeInt(evt.getY()/50);
	  }
	  catch(java.io.IOException ioe) {
	     ioe.printStackTrace();
	  }
       }
       //repaint();
       owner.repaint();
       // Add your handling code here:
    }//GEN-LAST:event_formMouseClicked
    
    public static void main(String args[]) {
       JFrame myFrame = new JFrame();
       myFrame.setSize(400,300);
       //myFrame.getContentPane().add(new InventoryPanel(new Backpack(6,4)));
       myFrame.setVisible(true);
    }
    
    public void paintComponent(Graphics g) {
       super.paintComponent(g);
       Backpack backpack = owner.owner.avatarSelected.inventory;
       synchronized(backpack) {
	  for(int a=0;a<backpack.height;a++)
	     for(int b=0;b<backpack.width;b++) {
		BufferedImage tempImage = backpack.getImage(b,a);
		if(tempImage!=null)
		   g.drawImage(tempImage,b*50,a*50,null);
	     }
	  g.setColor(Color.WHITE);
	  for(int a=1;a<backpack.width;a++)
	     g.drawLine(a*50, 0, a*50, 200);
	  for(int b=1;b<backpack.height;b++)
	     g.drawLine(0, b*50, 300, b*50);
       }
    }
    
    public void update() {
       if(owner.owner.avatarSelected!=null)
	  this.setVisible(true);
       else {
	  this.setVisible(false);
	  repaint();
	  owner.repaint();
       }
       repaint();
       owner.repaint();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
}