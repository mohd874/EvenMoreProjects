/*
 * CastleBuilderStandalone.java
 *
 * Created on June 17, 2003, 11:42 AM
 * Programmer:
 * Jason G
 * jgmath2000@hotmail.com
 * All Rights Reserved
 */

package IsometricGame.Client;

import IsometricGame.Net.*;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.net.*;

/**
 *
 * @author  jgauci
 */
public class BuilderFrame extends javax.swing.JFrame {
    BuilderClient gameInstance;
    Hashtable tabNames;
    
    /** Creates new form MapPlayerStandalone */
    public BuilderFrame(Loader owner,ObjectInputStream din,ImprovedObjectOutputStream dout) {
        initComponents();
        
        tabNames = new Hashtable();
        for(int a=0;a<sideBar.getComponentCount();a++)
        {
            tabNames.put(sideBar.getTitleAt(a), new Integer(a));
        }
        
        gameInstance = new BuilderClient(this,gameWindow,sideBar,owner,tabNames,din,dout);
        this.show();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
	private void initComponents() {//GEN-BEGIN:initComponents
		gameWindow = new javax.swing.JPanel();
		sideBar = new javax.swing.JTabbedPane();
		FloorTab = new javax.swing.JPanel();
		WallTab = new javax.swing.JPanel();
		TerrainTab = new javax.swing.JPanel();
		ObjectTab = new javax.swing.JPanel();
		Options = new javax.swing.JPanel();
		
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));
		
		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				formComponentResized(evt);
			}
		});
		addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent evt) {
				exitForm(evt);
			}
		});
		
		gameWindow.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2));
		gameWindow.setMinimumSize(new java.awt.Dimension(800, 400));
		gameWindow.setPreferredSize(new java.awt.Dimension(800, 400));
		gameWindow.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				gameWindowComponentResized(evt);
			}
		});
		gameWindow.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				gameWindowKeyPressed(evt);
			}
			public void keyReleased(java.awt.event.KeyEvent evt) {
				gameWindowKeyReleased(evt);
			}
		});
		gameWindow.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				gameWindowMouseClicked(evt);
			}
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				gameWindowMouseEntered(evt);
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				gameWindowMouseExited(evt);
			}
			public void mousePressed(java.awt.event.MouseEvent evt) {
				gameWindowMousePressed(evt);
			}
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				gameWindowMouseReleased(evt);
			}
		});
		gameWindow.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				gameWindowMouseDragged(evt);
			}
			public void mouseMoved(java.awt.event.MouseEvent evt) {
				gameWindowMouseMoved(evt);
			}
		});
		
		getContentPane().add(gameWindow);
		
		sideBar.setMaximumSize(new java.awt.Dimension(32767, 200));
		sideBar.setMinimumSize(new java.awt.Dimension(800, 200));
		sideBar.setPreferredSize(new java.awt.Dimension(800, 200));
		sideBar.addChangeListener(new javax.swing.event.ChangeListener() {
			public void stateChanged(javax.swing.event.ChangeEvent evt) {
				sideBarStateChanged(evt);
			}
		});
		sideBar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				sideBarMouseEntered(evt);
			}
		});
		
		FloorTab.setLayout(null);
		
		sideBar.addTab("Floors", FloorTab);
		
		sideBar.addTab("Walls", WallTab);
		
		sideBar.addTab("Doodads", TerrainTab);
		
		sideBar.addTab("Items", ObjectTab);
		
		sideBar.addTab("Options", Options);
		
		getContentPane().add(sideBar);
		
		pack();
	}//GEN-END:initComponents
    
     private void sideBarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sideBarStateChanged
         if(gameInstance!=null)
             gameInstance.barState = sideBar.getSelectedIndex();
         // Add your handling code here:
     }//GEN-LAST:event_sideBarStateChanged
     
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // Add your handling code here:
        if(gameInstance!=null)
            gameInstance.resizeWindow();
    }//GEN-LAST:event_formComponentResized
    
    private void sideBarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sideBarMouseEntered
        // Add your handling code here:
        sideBar.grabFocus();
    }//GEN-LAST:event_sideBarMouseEntered
    
    private void gameWindowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameWindowMouseExited
        // Add your handling code here:
        sideBar.grabFocus();
    }//GEN-LAST:event_gameWindowMouseExited
    
    private void gameWindowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameWindowMouseEntered
        // Add your handling code here:
        gameWindow.grabFocus();
    }//GEN-LAST:event_gameWindowMouseEntered
    
	private void gameWindowComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_gameWindowComponentResized
            // Add your handling code here:
            if(gameInstance!=null)
                gameInstance.g2d = (Graphics2D)gameWindow.getGraphics();
	}//GEN-LAST:event_gameWindowComponentResized
        
    private void gameWindowMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameWindowMouseMoved
        // Add your handling code here:
        if(gameInstance!=null)
            gameInstance.mouseMoved(evt);
    }//GEN-LAST:event_gameWindowMouseMoved
    
    private void gameWindowMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameWindowMouseReleased
        // Add your handling code here:
        if(gameInstance!=null)
            gameInstance.mouseReleased(evt);
    }//GEN-LAST:event_gameWindowMouseReleased
    
    private void gameWindowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameWindowMousePressed
        // Add your handling code here:
        if(gameInstance!=null)
            gameInstance.mousePressed(evt);
    }//GEN-LAST:event_gameWindowMousePressed
    
    private void gameWindowMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameWindowMouseDragged
        // Add your handling code here:
        if(gameInstance!=null)
            gameInstance.mouseDragged(evt);
    }//GEN-LAST:event_gameWindowMouseDragged
    
    private void gameWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameWindowMouseClicked
        // Add your handling code here:
        if(gameInstance!=null)
            gameInstance.mouseClicked(evt);
    }//GEN-LAST:event_gameWindowMouseClicked
    
    private void gameWindowKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gameWindowKeyReleased
        // Add your handling code here:
    }//GEN-LAST:event_gameWindowKeyReleased
    
    private void gameWindowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gameWindowKeyPressed
        // Add your handling code here:
    }//GEN-LAST:event_gameWindowKeyPressed
    
    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        if(gameInstance!=null)
            gameInstance.exit();
        this.dispose();
    }//GEN-LAST:event_exitForm
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }
    
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel FloorTab;
	private javax.swing.JPanel ObjectTab;
	private javax.swing.JPanel Options;
	private javax.swing.JPanel TerrainTab;
	private javax.swing.JPanel WallTab;
	private javax.swing.JPanel gameWindow;
	private javax.swing.JTabbedPane sideBar;
	// End of variables declaration//GEN-END:variables
    
}
