/*
 * GameClient.java
 *
 * Created on July 14, 2003, 7:34 PM
 */

package IsometricGame.Client;

import IsometricGame.Entity.*;
import IsometricGame.Client.*;
import IsometricGame.Net.*;
import IsometricGame.*;
import IsometricGame.Map.*;
import IsometricGame.Item.*;

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
public class GameClient implements PacketOperator, ActionListener, Runnable {
   Point cameraLoc;
   Timer gameUpdate;
   JPanel gameWindow;
   JTabbedPane sideBar;
   Graphics2D g2d,midBuffer,terrainGraphics;
   VolatileImage midImage;
   VolatileImage terrainImage;
   public final static int UPLEFT=0,UPRIGHT=1,DOWNRIGHT=2,DOWNLEFT=3;
   ImageObserver obs;
   AffineTransform worldTransform;
   Point clickPoint,currentPoint;
   Point2D.Double pointCurrent;
   Point3D.Int tilePressed,tileReleased,tileCurrent,tileSelected;
   static final int WHITE = 0;
   static final int RED = -65536;
   static final int GREEN = -16711936;
   static final int BLUE = -16776961;
   static final int YELLOW = -256;
   Loader owner;
   boolean isDragging;
   Rectangle lasso;
   int wallCurrent;
   int barState;
   boolean wallsOn,gridOn,alphaOn;
   boolean builderMode;
   Timer gameClientTimer;
   ImprovedObjectOutputStream dout;
   ObjectInputStream din;
   Socket mySocket;
   IsometricMap currentMap;
   GameClient thisClient;
   Theme currentTheme;
   LinkedList[] sideButtons;
   Integer connectionID;
   GameClientDataReader dataReader;
   Avatar avatarSelected;
   int zoom;
   Hashtable tabNames;
   int onHeight,viewHeight;
   PixelImage avatarImage,XMapImage,YMapImage;
   JFrame gameFrame;
   Item mouseHeldItem;
   GraphicsConfiguration gc;
   boolean groundChanged;
   Thread drawThread;
   int framesPerUpdate;
   String frameCount;
   
   /** Creates a new instance of GameClient */
   public GameClient(JFrame gameFrame,JPanel gameWindow, javax.swing.JTabbedPane sideBar, Loader owner,Hashtable tabNames,ObjectInputStream din,ImprovedObjectOutputStream dout) {
      this.gameFrame = gameFrame;
      this.owner = owner;
      this.gameWindow = gameWindow;
      this.sideBar = sideBar;
      this.tabNames = tabNames;
      this.din = din;
      this.dout = dout;
      thisClient = this;
      barState =0;
      groundChanged=true;
      frameCount = new String();
      zoom=1;
      gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
      //midImage = gc.createCompatibleImage(gameWindow.getWidth(), gameWindow.getHeight(), Transparency.TRANSLUCENT);
      midImage = gc.createCompatibleVolatileImage(gameWindow.getWidth(), gameWindow.getHeight());
      //midImage = new BufferedImage(gameWindow.getWidth(), gameWindow.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
      midBuffer = midImage.createGraphics();
      midBuffer.setBackground(Color.GRAY);
      midBuffer.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
      midBuffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
      midBuffer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
      midBuffer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
      avatarImage = new PixelImage(gameWindow.getWidth(), gameWindow.getHeight());
      XMapImage = new PixelImage(gameWindow.getWidth(), gameWindow.getHeight());
      YMapImage = new PixelImage(gameWindow.getWidth(), gameWindow.getHeight());
      g2d = (Graphics2D) gameWindow.getGraphics();
      g2d.setColor(Color.BLACK);
      g2d.fillRect(0, 0, gameWindow.getWidth(), 20);
      g2d.setColor(Color.WHITE);
      g2d.drawString("LOADING MAP...",gameWindow.getWidth()/2-100,15);
      cameraLoc = new Point(0,0);
      worldTransform = new AffineTransform();
      clickPoint = new Point();
      currentPoint = new Point();
      onHeight = viewHeight = 0;
      if(this instanceof BuilderClient)
	 gridOn=true;
      else
	 gridOn=false;
      alphaOn=true;
      wallsOn=true;
      tilePressed = new Point3D.Int();
      tileReleased = new Point3D.Int();
      tileCurrent = new Point3D.Int();
      tileSelected = new Point3D.Int();
      pointCurrent = new Point2D.Double();
      isDragging=false;
      g2d.setBackground(Color.GRAY);
      lasso = new Rectangle();
      sideButtons = new LinkedList[sideBar.getTabCount()];
      for(int a=0;a<sideBar.getTabCount();a++)
	 sideButtons[a] = new LinkedList();
      // Connect to the server
      try {
	 new Theme(owner,"fogofwar.gif","isograss.gif","castleWall.gif","doodads.gif","items.gif","player.gif");
      }
      catch(Exception e) {
	 e.printStackTrace();
	 return;
      }
      
      try {
	 
	 System.out.println("Receiving ID");
	 connectionID = new Integer(din.readInt());
	 
	 dout.writeByte(SEND_MAP);
	 System.out.println("Requesting map");
	 din.readByte();
	 currentMap = new IsometricMap(din);
	 synchronized(currentMap) {
	    System.out.println("Received map");
	    initButtons();
	 }
	 dataReader = new GameClientDataReader();
	 
      } catch( Exception e ) { e.printStackTrace(); return; }
      
      while(currentMap==null) {
      }
      drawThread = new Thread(this);
      drawThread.setPriority(Thread.MIN_PRIORITY);
      drawThread.start();
      gameClientTimer = new Timer(true);
      gameClientTimer.scheduleAtFixedRate(new GameClientTask(),0,1000/30);
   }
   
   public int getDeterminant(int a,int b,int c,int d) {
      return (a*d-b*c);
   }
   
   public void kramersRule(int A,int B,int C,int D,int E,int F) {
      pointCurrent.setLocation(((double)getDeterminant(C,B,F,E))/getDeterminant(A,B,D,E),((double)getDeterminant(A,C,D,F))/getDeterminant(A,B,D,E));
      tileCurrent.setLocation((int)pointCurrent.x,(int)pointCurrent.y,onHeight);
   }
   
   public void screenToTile(Point evt) {
      synchronized(currentMap) {
	 Color myCol;
	 try {
	    myCol = new Color(avatarImage.getPixel(evt.x,evt.y),true);
	 }
	 catch(ArrayIndexOutOfBoundsException aiooe) {
	    myCol = new Color(0,true);
	 }
	 //System.out.println("RGB VAL: " + evt.x + " " + evt.y + " " + myCol.getRed() + " " + myCol.getGreen() + " " + myCol.getBlue() + " " + myCol.getAlpha());
	 //System.out.println("RGB INT: " + (avatarImage.getPixel(evt.x,evt.y)&511));
      }
      kramersRule(Theme.tileWidth/2,-Theme.tileWidth/2,evt.x+cameraLoc.x,Theme.tileLength/2,Theme.tileLength/2,evt.y+cameraLoc.y+onHeight*Theme.floorHeight);
      double xPart = pointCurrent.x-(int)pointCurrent.x;
      double yPart = pointCurrent.y-(int)pointCurrent.y;
      if(yPart<xPart) {
	 if(yPart<1-xPart) {
	    wallCurrent = UPRIGHT;
	 }
	 else {
	    wallCurrent = DOWNRIGHT;
	 }
      }
      else {
	 if(yPart<1-xPart) {
	    wallCurrent = UPLEFT;
	 }
	 else {
	    wallCurrent = DOWNLEFT;
	 }
      }
   }
   
   public void resizeWindow() {
      //midImage = (BufferedImage) gameWindow.createImage(gameWindow.getWidth(), gameWindow.getHeight());
      //midBuffer = (Graphics2D) midImage.getGraphics();
      //midBuffer.setBackground(Color.GRAY);
      //g2d = (Graphics2D) gameWindow.getGraphics();
   }
   
   public void initButtons() {
      makeButton("Options",  "Help","H");
      makeButton("Options",  "Grid","G");
      makeButton("Options",  "Transparent","T");
      makeButton("Options",  "No Walls", "N");
      makeButton("Options",  "Raise Level", "R1");
      makeButton("Options",  "Lower Level", "L1");
      makeButton("Options",  "Raise View", "R2");
      makeButton("Options",  "Lower View", "L2");
   }
   
   public void actionPerformed(ActionEvent e) {
   }
   
   public void makeButton(String tabName,String name,String actionName) {
      int tabNumber = ((Integer)tabNames.get(tabName)).intValue();
      int position = ((JPanel)sideBar.getComponentAt(tabNumber)).getComponentCount();
      JButton tempButton = new JButton();
      sideButtons[tabNumber].add(tempButton);
      tempButton.setText(name);
      tempButton.setActionCommand(actionName);
      tempButton.setLocation(position*100,0);
      tempButton.setSize(100,50);
      tempButton.addActionListener(this);
      ((JPanel)sideBar.getComponentAt(tabNumber)).add(tempButton);
      ((JPanel)sideBar.getComponentAt(tabNumber)).repaint();
   }
   
   public void makeButton(String tabName,Image icon,String actionName) {
      int tabNumber = ((Integer)tabNames.get(tabName)).intValue();
      int position = ((JPanel)sideBar.getComponentAt(tabNumber)).getComponentCount();
      JButton tempButton = new JButton();
      tempButton.setIcon(new ImageIcon(icon));
      tempButton.setActionCommand(actionName);
      tempButton.setLocation(position*100,0);
      tempButton.setSize(100,50);
      tempButton.addActionListener(this);
      ((JPanel)sideBar.getComponentAt(tabNumber)).add(tempButton);
      ((JPanel)sideBar.getComponentAt(tabNumber)).repaint();
   }
   
   public void mouseMoved(java.awt.event.MouseEvent evt) {
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
   }
   
   public void mouseDragged(java.awt.event.MouseEvent evt) {
   }
   
   public void exit() {
      try {
	 dout.writeByte(CLIENT_EXIT);
	 dout.flush();
      }
      catch(Exception e) {
	 e.printStackTrace();
      }
      gameClientTimer.cancel();
      owner.exit();
   }
   
   public void run() {
       groundChanged=true;
      while(true) {
	 synchronized(currentMap) {
	    framesPerUpdate++;
	    if(framesPerUpdate%10==0&&thisClient instanceof PlayerClient)
	       ((PlayerClient)thisClient).updateSideBar();
            if(framesPerUpdate==10000)
                framesPerUpdate=0;
	    /*if(framesPerUpdate%10==1) {
	    //midBuffer.clearRect(0,0, midImage.getWidth(), midImage.getHeight());
	    if(groundChanged) {
	       terrainGraphics.setColor(Color.DARK_GRAY);
	       terrainGraphics.setComposite(AlphaComposite.Src);
	       terrainGraphics.fillRect(0,0, terrainImage.getWidth(), terrainImage.getHeight());
	       terrainGraphics.setComposite(AlphaComposite.SrcOver);
	       currentMap.drawGround(terrainGraphics, XMapImage, YMapImage, cameraLoc, (thisClient instanceof PlayerClient), gridOn, alphaOn, wallsOn, onHeight);
	       groundChanged=false;
	    }
            }*/
	    //midBuffer.drawImage(groundImage[a],0,0,null);
	    //midBuffer.clearRect(0,0,gameWindow.getWidth(),gameWindow.getHeight());
	    
	    /*if(groundChanged)
	    {
	       terrainGraphics.setColor(Color.DARK_GRAY);
	       terrainGraphics.fillRect(0,0, terrainImage.getWidth(), terrainImage.getHeight());
	    }*/
	    currentMap.drawMap(midBuffer,midImage,avatarImage,cameraLoc,(thisClient instanceof PlayerClient),gridOn,
	    alphaOn,wallsOn,onHeight,lasso,barState,wallCurrent,tileCurrent,avatarSelected,connectionID,groundChanged);
	    //groundChanged=false;
	    //midBuffer.drawImage(avatarImage.getImage(),0,0,null);
	    //}
	    if(isDragging)
	       midBuffer.drawLine(clickPoint.x,clickPoint.y,currentPoint.x,currentPoint.y);
	    //if(thisClient instanceof BuilderClient)
	    g2d.drawImage(midImage,0,0,gameWindow.getWidth(),gameWindow.getHeight(),null);
	    g2d.drawString(frameCount,10,10);
	    //else if(((PlayerClient)thisClient).avatarImage!=null)
	    //	g2d.drawImage(avatarImage.getImage(),0,0,gameWindow.getWidth(),gameWindow.getHeight(),null);
	 }
      }
   }
   
   class GameClientTask extends TimerTask {
      int frame;
      
      public void run() {
	 frame = (frame+1)%600;
	 if(frame%60==0) {
	    frameCount= ""+(framesPerUpdate/2);
	    framesPerUpdate=0;
	 }
	 
						/*if(frame%30==1) {
								try {
										dout.writeByte(SEND_MAP);
								}
								catch(IOException ie) {
										ie.printStackTrace();
								}
						}*/
	 //if(g2d==null) return;
	 //backBuffer.setClip(cameraLoc.x,cameraLoc.y, gameWindow.getWidth(),gameWindow.getHeight());
	 //g2d.setClip(0,0, 10000,10000);
	 
	 if(frame%5==0&&gameWindow.hasFocus()) {
	    if(currentPoint.x+60>gameWindow.getWidth()) {
	       cameraLoc.x+=((currentPoint.x+60)-gameWindow.getWidth())/2;
	       groundChanged=true;
	    }
	    if(currentPoint.y+60>gameWindow.getHeight()&&currentPoint.y<gameWindow.getHeight()) {
	       cameraLoc.y+=((currentPoint.y+60)-gameWindow.getHeight())/4;
	       groundChanged=true;
	    }
	    if(currentPoint.x-60<0) {
	       cameraLoc.x-=(60-currentPoint.x)/2;
	       groundChanged=true;
	    }
	    if(currentPoint.y-60<0) {
	       cameraLoc.y-=(60-currentPoint.y)/4;
	       groundChanged=true;
	    }
	 }
	 
	 synchronized (currentMap) {
	    synchronized (this) {
	       currentMap.update();
	    }
	 }
						/*
						if(cameraLoc.x>currentMap.pixelWidth-gameWindow.getWidth())
								cameraLoc.x=currentMap.pixelWidth-gameWindow.getWidth();
						if(cameraLoc.y>currentMap.pixelLength-gameWindow.getHeight())
								cameraLoc.y=currentMap.pixelLength-gameWindow.getHeight();
						if(cameraLoc.x<-currentMap.pixelWidth) cameraLoc.x=-currentMap.pixelWidth;
						if(cameraLoc.y<0) cameraLoc.y=0;
						 */
	 //System.out.println(cameraLoc.x + " " + cameraLoc.y);
      }
   }
   
   
   
   public class GameClientDataReader extends Thread {
      
      // Constructor.
      public GameClientDataReader() {
	 System.out.println("Making a new client thread");
	 start();
      }
      
      // This runs in a separate thread when start() is called in the
      // constructor.
      public void run() {
	 boolean done;
	 try {
	    done=false;
	    
	    while (!done) {
	       System.out.println("WAITING FOR PACKET");
	       // ... read the next message ...
	       int header = din.readByte();
	       System.out.println("HEADER: " + header);
	       synchronized (currentMap) {
		  synchronized(this) {
		     byte type;
		     Point3D.Int tempPoint;
		     Item tempItem;
		     Integer tempInteger;
		     switch(header) {
			//case MAP_SENT:
			//  currentMap.syncMap(din);
			//System.out.println("synced map");
			//break;
			case WALL_CHANGED:
			   int locationx = din.readInt();
			   int locationy = din.readInt();
			   int locationz = din.readInt();
			   byte wallCurrent = din.readByte();
			   byte wallType = din.readByte();
			   //System.out.println("CREATING WALL " + location + " " + wallCurrent + " " + wallType);
			   currentMap.changeWall(locationx,locationy, locationz,wallCurrent, wallType);
			   System.out.println("WALL CREATED");
			   groundChanged=true;
			   break;
			case WALL_DELETED:
			   currentMap.deleteWall(din.readInt(),din.readInt(),din.readInt(),din.readByte());
			   groundChanged=true;
			   break;
			case ROOM_CREATED:
			   Rectangle tempRect = new Rectangle(din.readInt(),din.readInt(),din.readInt(),din.readInt());
			   int zVal = din.readInt();
			   byte arg1,arg2;
			   arg1 = din.readByte();
			   arg2 = din.readByte();
			   System.out.println("Creating room:" + tempRect + ' ' + arg1 + ' ' + arg2);
			   currentMap.createRoom(tempRect,zVal,arg1,arg2);
			   groundChanged=true;
			   break;
			case ITEM_CREATED:
			   currentMap.createItem(din);
			   break;
			case ITEM_MOVED:
			   break;
			case ITEM_DELETED:
			   tempInteger = new Integer(din.readInt());
			   currentMap.deleteItem(tempInteger);
			   break;
			case HELD_ITEM_ADDED:
			   Integer targetID = new Integer(din.readInt());
			   mouseHeldItem = (Item) currentMap.itemTable.get(targetID);
			   Cursor tempCursor = Toolkit.getDefaultToolkit().createCustomCursor(mouseHeldItem.getModel().image,new Point(0,0),"itemCursor");
			   gameFrame.setCursor(tempCursor);
			   break;
			case HELD_ITEM_REMOVED:
			   System.out.println("GOT REMOVE REQUEST");
			   mouseHeldItem = null;
			   gameFrame.setCursor(Cursor.getDefaultCursor());
			   break;
			case DOODAD_CREATED:
			   tempPoint = new Point3D.Int(din.readInt(),din.readInt(),din.readInt());
			   type = din.readByte();
			   currentMap.createDoodad(tempPoint, type);
			   groundChanged=true;
			   break;
			case DOODAD_DELETED:
			   tempPoint = new Point3D.Int(din.readInt(),din.readInt(),din.readInt());
			   currentMap.deleteDoodad(tempPoint);
			   groundChanged=true;
			   break;
			case AVATAR_CREATED:
			   int px = din.readInt();
			   int py = din.readInt();
			   int pz = din.readInt();
			   Integer ownerID = new Integer(din.readInt());
			   Integer avatarID = new Integer(din.readInt());
			   currentMap.createAvatar(px,py,pz,ownerID,avatarID);
			   if(thisClient instanceof PlayerClient&&ownerID.equals(thisClient.connectionID))
			      ((PlayerClient)thisClient).avatars.add(currentMap.avatarMap[pz][py][px]);
			   groundChanged=true;
			   break;
			case AVATAR_MOVED:
			   currentMap.moveAvatar(new Integer(din.readInt()), din.readInt(), din.readInt(),din.readInt());
			   groundChanged=true;
			   break;
			case AVATAR_DELETED:
			   currentMap.deleteAvatar(din.readInt(),din.readInt(), din.readInt());
			   groundChanged=true;
			   break;
			case ITEM_PUT_IN_BACKPACK:
			   Integer avaID = new Integer(din.readInt());
			   Item theItem = (Item) din.readObject();
			   currentMap.putItemInBackpack(avaID,theItem,din.readInt(),din.readInt());
			   break;
			case ITEM_GOT_FROM_BACKPACK:
			   Integer avaID2 = new Integer(din.readInt());
			   currentMap.getItemFromBackpack(avaID2,din.readInt(),din.readInt());
			   break;
			case ITEM_REMOVED_FROM_GROUND:
			   Integer tempIDG = new Integer(din.readInt());
			   currentMap.removeItemFromGround(tempIDG);
			   break;
			case ITEM_THROWN:
			   System.out.println("SERVER THREW ITEM");
			   Item theItemz = (Item)currentMap.itemTable.get(new Integer(din.readInt()));
			   Point3D.Int tgPoint = (Point3D.Int)din.readObject();
			   currentMap.throwItem(theItemz, tgPoint);
			   System.out.println("THROW TIME IS " + System.currentTimeMillis());
			   break;
			case PROJECTILE_DROPPED_AT:
			   System.out.println("dropped!");
			   tempInteger = new Integer(din.readInt());
			   tempPoint = (Point3D.Int) din.readObject();
			   currentMap.dropProjectileAt((Projectile)currentMap.projectileTable.get(tempInteger),tempPoint);
			   break;
		     }
		     if(thisClient instanceof PlayerClient) {
			((PlayerClient)thisClient).inventoryPanel.update();
		     }
		     if(thisClient instanceof PlayerClient) {
			currentMap.setFieldOfView(((PlayerClient)thisClient).avatars, ((PlayerClient)thisClient).VIEW_RADIUS);
			currentMap.getRoof(((PlayerClient)thisClient).avatars);
		     }
		  }
	       }
	       
	    }
	    
	 }
	 catch (Exception e) {
	    e.printStackTrace();
	    return;
	 }
      }
   }
}
