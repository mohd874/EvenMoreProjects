/*
 * GameServer.java
 *
 * Created on July 8, 2003, 8:03 PM
 */

package IsometricGame.Server;

import IsometricGame.Item.*;
import IsometricGame.Entity.*;
import IsometricGame.Net.*;
import IsometricGame.Map.*;
import IsometricGame.*;
import IsometricGame.Server.Objective.*;

import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;

import java.sql.*;

/** This class handles the architectural work of the GameServer, broadcasting
 * information, managing connections, etc.
 * @author jgauci
 */
public class GameServer implements PacketOperator, Loader {
   ServerSocket serverSocket;
   Socket tempSocket;
   Hashtable connections;
   MediaTracker tracker;
   String fileBase;
   GameServer thisServer;
   GameEngine thisEngine;
   
   /** Creates a new instance of GameServer
    * @param port the port number you which to run the server on.
    * @param database (depricated) for mysql database connectivity.
    * @throws IOException Throws an IOException if it cannot load.
    */
   public GameServer(int port,String database) throws IOException {
      thisServer = this;
      fileBase = "./IsometricGame/";
      connections = new Hashtable();
      javax.swing.JPanel myWindow = new javax.swing.JPanel();
      tracker = new MediaTracker(myWindow);
      thisEngine = new GameEngine(thisServer);
      
      // Create the serverSocket
      serverSocket = new ServerSocket( port );
      
      // Tell the world we're ready to go
      System.out.println( "Listening on "+serverSocket );
      Integer connectionID=new Integer(0);
      
	/*try {
	    new IsometricGame.IO.mysql.com.mysql.jdbc.Driver();
	    dbCon = DriverManager.getConnection( "jdbc:mysql://" + database + "/corewarrior?autoReconnect=true", "coremaster", "coremaster");
	    System.out.println("Server connection established");
	}
	catch(Exception e) {
	    e.printStackTrace();
	    return;
	}*/
      
      // Keep accepting connections forever
      while (true) {
	 
	 // Grab the next incoming connection
	 tempSocket = serverSocket.accept();
	 
	 // Tell the world we've got it
	 System.out.println( "Connection from "+tempSocket );
	 
	 while(connections.containsKey(connectionID)) {
	    System.out.println("Connection " + connectionID + " used");
	    connectionID = new Integer(  (connectionID.intValue()%30000)+1  );
	 }
	 
	 
	 // Create a new thread for this connection
	 connections.put(connectionID,new GameConnection(connectionID, tempSocket));
      }
   }
   
   /** Runs a server on port 7766
    * @param args the command line arguments
    * @throws Exception Throws all Exceptions up
    */
   public static void main(String[] args) throws Exception {
      GameServer myGameServer = new GameServer(7766,"ucfpawn.homelinux.com");
   }
   
   public BufferedImage loadBufferedImage(String location, boolean translucent) {
      System.out.println("Loading" + location);
      Image tempImage = Toolkit.getDefaultToolkit().getImage(fileBase + "images/" + location);
      tracker.addImage(tempImage,0);
      try {
	 tracker.waitForAll();
      }
      catch(Exception e) {
	 e.printStackTrace();
      }
      //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      //GraphicsDevice gs = ge.getDefaultScreenDevice();
      //GraphicsConfiguration gc = gs.getDefaultConfiguration();
      //BufferedImage tempBuffImage = gc.createCompatibleImage(tempImage.getWidth(null), tempImage.getHeight(null), Transparency.BITMASK);
      BufferedImage tempBuffImage = new BufferedImage(tempImage.getWidth(null), tempImage.getHeight(null),BufferedImage.TYPE_4BYTE_ABGR);
      tempBuffImage.createGraphics().drawImage(tempImage,null,null);
      System.out.println("Done!");
      return tempBuffImage;
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
   
   /** Broadcasts a byte to all clients. */   
   public void broadcastByte(byte data) {
      synchronized(connections) {
	 try {
	    for(Enumeration e=connections.elements();e.hasMoreElements();) {
	       GameConnection myConnection = (GameConnection)e.nextElement();
	       // System.out.println("ID " + myConnection.connectionID);
	       myConnection.dout.writeByte(data);
	       //myConnection.dout.flush();
	    }
	 }
	 catch(Exception e) {
	    e.printStackTrace();
	 }
      }
   }
   
   /** Broadcasts an int to all clients. */   
   public void broadcastInt(int data) {
      synchronized(connections) {
	 try {
	    for(Enumeration e=connections.elements();e.hasMoreElements();) {
	       GameConnection myConnection = (GameConnection)e.nextElement();
	       //System.out.println("ID " + myConnection.connectionID);
	       myConnection.dout.writeInt(data);
	       //myConnection.dout.flush();
	    }
	 }
	 catch(Exception e) {
	    e.printStackTrace();
	 }
      }
   }
   
   /** Broadcasts an object to all clients. */   
   public void broadcastObject(Object data) {
      synchronized(connections) {
	 try {
	    for(Enumeration e=connections.elements();e.hasMoreElements();) {
	       GameConnection myConnection = (GameConnection)e.nextElement();
	       //System.out.println("ID " + myConnection.connectionID);
	       //data.send(myConnection.dout);
	       myConnection.dout.improvedWriteObject(data);
	       //myConnection.dout.flush();
	    }
	 }
	 catch(Exception e) {
	    e.printStackTrace();
	 }
      }
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
   
   public void exit() {
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
   
   /** A GameConnection is a single connection with a client and the server.  It is
    * responsible to receiving and interpreting all of the client's commands.
    */   
   public class GameConnection extends Thread {
      Socket clientSocket;
      public ImprovedObjectOutputStream dout;
      public ObjectInputStream din;
      Integer connectionID;
      String playerDirectory;
      /** This is the item currently "held" (in the mouse) of the player.  This is to
       * handle potential splits if someone should disconnect or if someone should try to
       * pick something up that is already in the hands of another player.
       */      
      public Item playerHeldItem;
      
      // Constructor.
      /** Creates a GameConnection given a Socket to the client and a client ID.
       * @param connectionID A unique client ID number
       * @param clientSocket The network connection to the client.
       */      
      public GameConnection(Integer connectionID,Socket clientSocket) {
	 System.out.println("Making a new thread");
	 this.clientSocket = clientSocket;
	 this.connectionID = connectionID;
	 playerDirectory = fileBase+"players/";
	 //Start the thread
	 start();
      }
      
      // This runs in a separate thread when start() is called in the
      // constructor.
      /** Executes the thread which monitors for client input. */      
      public void run() {
	 boolean done;
	 try {
	    // Create an DataOutputStream for writing data to the
	    // other side
	    dout = new ImprovedObjectOutputStream(clientSocket.getOutputStream());
	    din = new ObjectInputStream(clientSocket.getInputStream());
	    
		/*Statement stmt = dbCon.createStatement();
		String testString = "SELECT * FROM tblUser where name = \""+din.readUTF()+"\" and password = password(\""+din.readUTF()+"\");";
		ResultSet initSet = stmt.executeQuery(testString);
		if(!initSet.first()) {
		    dout.writeByte(PacketOperator.LOGIN_FAILED);
		    return;
		}
		initSet.first();
		String name = initSet.getString("name");
		System.out.println(name);
		dout.writeByte(initSet.getByte("userType"));*/
	    
	    String userName = din.readUTF();
	    String password = din.readUTF();
	    File tempFile = new File(playerDirectory+userName+".xml");
	    if(tempFile.canRead()) {
	       if(userName.equalsIgnoreCase("testbuilder"))
		  dout.writeByte(PacketOperator.LOGIN_BUILDER);
	       else
		  dout.writeByte(PacketOperator.LOGIN_PLAYER);
	    }
	    else {
	       dout.writeByte(PacketOperator.LOGIN_FAILED);
	       return;
	    }
	    
	    InetAddress clientAddress = clientSocket.getInetAddress();
	    System.out.println("Creating new player");
	    System.out.println("Sending ID" + connectionID);
	    dout.writeInt(connectionID.intValue());
	    System.out.println("MAKING PLAYER FOR " + clientSocket);
	    
	    
	    done=false;
	    //thisEngine.gameMap.createAvatar(0,0,0,connectionID,new boolean[thisEngine.gameMap.height][thisEngine.gameMap.length][thisEngine.gameMap.width]);
	    
	    while (!done) {
	       
	       try {
		  
		  // ... read the next message ...
		  int header = din.readByte();
		  System.out.println("read header");
		  synchronized(thisEngine.gameMap) {
		     synchronized(connections) {
			Point3D.Int tempPoint;
			byte type;
			switch(header) {
			   case CREATE_WALL:
			      int locationx = din.readInt();
			      int locationy = din.readInt();
			      int locationz = din.readInt();
			      byte wallCurrent = din.readByte();
			      byte wallType = din.readByte();
			      //System.out.println("CREATING WALL " + location + " " + wallCurrent + " " + wallType);
			      thisEngine.gameMap.changeWall(locationx,locationy,locationz, wallCurrent, wallType);
			      System.out.println("WALL CREATED");
			      break;
			   case DELETE_WALL:
			      int dlocationx = din.readInt();
			      int dlocationy = din.readInt();
			      int dlocationz = din.readInt();
			      byte dwallCurrent = din.readByte();
			      thisEngine.gameMap.deleteWall(dlocationx,dlocationy, dlocationz, dwallCurrent);
			      System.out.println("WALL DELETED");
			      break;
			   case CREATE_TILE:
			      Rectangle tempRect = new Rectangle(din.readInt(),din.readInt(),din.readInt(),din.readInt());
			      int zVal = din.readInt();
			      byte arg1,arg2;
			      arg1 = din.readByte();
			      arg2 = din.readByte();
			      System.out.println("Creating room:" + tempRect + ' ' + zVal + ' ' + arg1 + ' ' + arg2);
			      thisEngine.gameMap.createRoom(tempRect,zVal,arg1,arg2);
			      break;
			   case CREATE_ITEM:
			      byte itemType = din.readByte();
			      thisEngine.gameMap.createItem(itemType,new Point3D.Int(din.readInt(),din.readInt(),din.readInt()));
			      break;
			   case DELETE_ITEM:
			      Integer itemID = new Integer(din.readInt());
			      thisEngine.gameMap.deleteItem(itemID);
			      break;
			   case MOVE_ITEM:
			      break;
			   case SEND_MAP:
			      System.out.println("Sending Map");
			      dout.writeByte(MAP_SENT);
			      thisEngine.gameMap.sendMap(dout);
			      break;
			   case CREATE_AVATAR:
			      boolean[][][] usedArray = new boolean[thisEngine.gameMap.height][thisEngine.gameMap.length][thisEngine.gameMap.width];
					/*for(int z=0;z<thisEngine.gameMap.height;z++)
					    for(int a=0;a<thisEngine.gameMap.length;a++)
						for(int b=0;b<thisEngine.gameMap.width;b++) {
						    //System.out.println(a + " " + b + " " + usedArray.length + " " + usedArray[a].length);
						    usedArray[z][a][b]=false;
						}*/
			      System.out.println("Creating Avatar for" + connectionID);
			      int p2x = din.readInt();
			      int p2y = din.readInt();
			      int p2z = din.readInt();
			      thisEngine.gameMap.createAvatar(p2x,p2y,p2z,connectionID,usedArray);
			      break;
			   case CHANGE_AVATAR_OBJECTIVE:
			      int avatarID;
			      byte objectiveType;
			      Object target;
			      avatarID = din.readInt();
			      objectiveType = din.readByte();
			      target = din.readObject();
			      System.out.println("Changing objective to" + objectiveType);
			      Avatar tempAvatar = ((Avatar)thisEngine.gameMap.avatarTable.get(new Integer(avatarID)));
			      if(connectionID!=tempAvatar.ownerID) {
				 System.out.println("NOT YOUR PIECE ");
				 break;
			      }
			      ObjectiveController cont = (ObjectiveController) thisEngine.gameMap.objectiveTable.get(tempAvatar);
			      cont.setObjective(objectiveType, target, this);
			      //thisEngine.gameMap.setAvatarPath(tempAvatar,newx,newy,newz);
			      break;
			   case CREATE_DOODAD:
                               System.out.println("creating doodad");
			      tempPoint = new Point3D.Int(din.readInt(),din.readInt(),din.readInt());
                               System.out.println("got ints");
			      byte doodadType = din.readByte();
                               System.out.println("got information");
			      thisEngine.gameMap.createDoodad(tempPoint,doodadType);
			      break;
			   case DELETE_DOODAD:
			      tempPoint = new Point3D.Int(din.readInt(),din.readInt(),din.readInt());
			      thisEngine.gameMap.deleteDoodad(tempPoint);
			      break;
			   case CLIENT_EXIT:
			      System.out.println(connectionID + " EXITED!");
			      System.out.println("REMOVING " + thisServer.connections.remove(connectionID));
			      if(playerHeldItem!=null)
				 thisEngine.gameMap.dropItemFromPlayer(playerHeldItem, playerHeldItem.location);
			      thisEngine.removePlayer(connectionID);
			      done=true;
			      break;
			   case SAVE_MAP_REQUEST:
			      thisEngine.saveMap();
			      break;
			   case PUT_ITEM_IN_BACKPACK:
			      Integer tempID = new Integer(din.readInt());
			      if(  thisEngine.gameMap.putItemInBackpack(tempID,playerHeldItem,din.readInt(),din.readInt()))
			      {
				 playerHeldItem=null;
				 dout.writeByte(HELD_ITEM_REMOVED);
			      }
			      break;
			   case GET_ITEM_FROM_BACKPACK:
			      Integer tempID2 = new Integer(din.readInt());
			      int x = din.readInt();
			      int y = din.readInt();
			      if(playerHeldItem!=null)
			      {
				 System.out.println("ALREADY HOLDING SOMETHING!!!");
				 break;
			      }
			      playerHeldItem = thisEngine.gameMap.getItemFromBackpack(tempID2,x,y);
			      if(playerHeldItem!=null)
			      {
				 dout.writeByte(HELD_ITEM_ADDED);
				 dout.writeInt(playerHeldItem.ID.intValue());
			      }
			      break;
			   case THROW_ITEM:
			      Point3D.Int dest = (Point3D.Int)din.readObject();
			      if(thisEngine.gameMap.throwItem(playerHeldItem,dest))
			      {
				 playerHeldItem=null;
				 dout.writeByte(HELD_ITEM_REMOVED);
			      }
			      break;
			}
		     }
		  }
	       }
	       catch(SocketException se) {
		  synchronized(connections) {
		     System.out.println(connectionID + " EXITED!");
		     System.out.println("REMOVING " + thisServer.connections.remove(connectionID));
		     if(playerHeldItem!=null)
			thisEngine.gameMap.dropItemFromPlayer(playerHeldItem, playerHeldItem.location);
		     thisEngine.removePlayer(connectionID);
		     done=true;
		  }
	       }
	       catch(Exception e) {
		  e.printStackTrace();
		  done=true;
	       }
	    }
	    //System.out.println("CONNECTION CLOSED");
	    // The connection is closed for one reason or another,
	    // so have the server dealing with it
	 }
	 catch (Exception e) {
	    e.printStackTrace();
	    return;
	 }
      }
      
      void savePlayers() {
	 
      }
   }
   
}
