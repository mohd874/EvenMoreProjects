/*
 * PathCreator.java
 *
 * Created on July 30, 2003, 6:39 PM
 */

package IsometricGame.Server;

import IsometricGame.*;
import IsometricGame.Entity.*;
import IsometricGame.Server.Objective.*;

import java.util.*;
import java.awt.*;

/** This class is responsible for creating paths for avatars.
 * @author jgauci
 */
public class PathCreator {
    final int MOVECOST = 10;
    PriorityQueue searchSpace;
    IsometricMapServer owner;
    Node[][][] nodeMap;
    Stack path;
    int targetx,targety,targetz;
    
    /** Creates a new instance of PathCreator
     * @param owner The MapServer that owns this PathCreator
     */
    public PathCreator(IsometricMapServer owner) {
        this.owner = owner;
        searchSpace = new PriorityQueue(100);
    }
    
    /** Creates a path for an avatar from one location to another.
     * @param source The avatar to be moved.
     * @param destinationx The X coordinate of the destination
     * @param destinationy The Y coordinate of the destination
     * @param destinationz The Z coordinate of the destination
     * @return returns a Stack containing the moves to get to the destination.
     */    
    public Stack createPath(Avatar source,int destinationx,int destinationy,int destinationz) {
        int sourcex = source.getLocation().x;
        int sourcey = source.getLocation().y;
        int sourcez = source.getLocation().z;
        System.out.println("GOING FROM " + sourcex + ',' + sourcey + ',' + sourcez + " TO " + destinationx + ',' + destinationy + ',' + destinationz);
        targetx = destinationx;
        targety = destinationy;
        targetz = destinationz;
        if(owner.tileMap[destinationz][destinationy][destinationx]==IsometricGame.Map.TileType.NOTILE)
            return null;
        path = new Stack();
        this.nodeMap = new Node[owner.tileMap.length][owner.tileMap[0].length][owner.tileMap[0][0].length];
        searchSpace.clear();
        
        nodeMap[sourcez][sourcey][sourcex] = new Node(sourcex,sourcey,sourcez,0,(byte)-1);
        searchSpace.insert(nodeMap[sourcez][sourcey][sourcex],0);
        
        while(!searchSpace.isEmpty()) {
            Node tempNode = (Node)searchSpace.removeFirst();
            //System.out.println("PROCESSING NODE AT " + tempNode.x + ',' + tempNode.y + ',' + tempNode.z + " WITH PRIORITY " + (tempNode.G + tempNode.H));
            if(tempNode.x==targetx&&tempNode.y==targety&&tempNode.z==targetz) {
                while(tempNode!=null) {
                    if(tempNode.toLastNode!=-1) {
                        path.push(new Byte(reverse(tempNode.toLastNode)));
                        System.out.println("ADDING " + tempNode.toLastNode);
                    }
                    tempNode = tempNode.translate(tempNode.toLastNode);
                }
                return path;
            }
            for(int z=-1;z<=1;z++)
                for(int y=-1;y<=1;y++)
                    for(int x=-1;x<=1;x++) {
                        //if(y!=0&&x!=0)
                        //  continue;
                        if(z!=0)
                            if(y!=0||x!=0)
                                continue;
                        if(z==0&&y==0&&x==0)
                            continue;
                        if(tempNode.y+y<0||tempNode.y+y>=owner.length
                        ||tempNode.x+x<0||tempNode.x+x>=owner.width
                        ||tempNode.z+z<0||tempNode.z+z>=owner.height)
                            continue;
                        
                        byte toLastNode=0;
                        if(x==-1) {
                            if(y==-1)
                                toLastNode=Command.DOWN;
                            else if(y==0)
                                toLastNode=Command.DOWNRIGHT;
                            else if(y==1)
                                toLastNode=Command.RIGHT;
                        }
                        else if(x==0) {
                            if(y==-1)
                                toLastNode=Command.DOWNLEFT;
                            else if(y==1)
                                toLastNode=Command.UPRIGHT;
                            else if(z==-1)
                                toLastNode=Command.CLIMB;
                            else if(z==1)
                                toLastNode=Command.FALL;
                        }
                        else if(x==1) {
                            if(y==-1)
                                toLastNode=Command.LEFT;
                            else if(y==0)
                                toLastNode=Command.UPLEFT;
                            else if(y==1)
                                toLastNode=Command.UP;
                        }
                        
                        
                        if(owner.movementBlocked(tempNode.x, tempNode.y, tempNode.z, tempNode.x+x, tempNode.y+y, tempNode.z+z))
                        {
                            System.out.println("BLOCKED NODE AT " + (tempNode.x+x) + ',' + (tempNode.y+y) + ',' + (tempNode.z+z));
                            continue;
                        }
                        
                        if(Point3D.Int.distance(sourcex,sourcey,sourcez,tempNode.x+x,tempNode.y+y,tempNode.z+z)<=2&&owner.avatarMap[tempNode.z+z][tempNode.y+y][tempNode.x+x]!=null) {
                            continue;
                        }
                        
                        if(nodeMap[tempNode.z+z][tempNode.y+y][tempNode.x+x]==null) {
                            nodeMap[tempNode.z+z][tempNode.y+y][tempNode.x+x] = new Node(tempNode.x+x, tempNode.y+y, tempNode.z+z, nodeMap[tempNode.z][tempNode.y][tempNode.x].G+10, toLastNode);
                            //System.out.println("ADDING NODE AT " + (tempNode.x+x) + ',' + (tempNode.y+y) + ',' + (tempNode.z+z) + " WITH PRIORITY " + (nodeMap[tempNode.z+z][tempNode.y+y][tempNode.x+x].G+nodeMap[tempNode.z+z][tempNode.y+y][tempNode.x+x].H) + " AND DIRECTION " + toLastNode);
                            searchSpace.insert(nodeMap[tempNode.z+z][tempNode.y+y][tempNode.x+x],nodeMap[tempNode.z+z][tempNode.y+y][tempNode.x+x].G+nodeMap[tempNode.z+z][tempNode.y+y][tempNode.x+x].H);
                        }
                        else if(nodeMap[tempNode.z+z][tempNode.y+y][tempNode.x+x].G>(nodeMap[tempNode.z][tempNode.y][tempNode.x].G+10)) {
                            nodeMap[tempNode.z+z][tempNode.y+y][tempNode.x+x].G=nodeMap[tempNode.z][tempNode.y][tempNode.x].G+10;
                            searchSpace.changePriority(nodeMap[tempNode.z+z][tempNode.y+y][tempNode.x+x], nodeMap[tempNode.z][tempNode.y][tempNode.x].G+10+nodeMap[tempNode.z+z][tempNode.y+y][tempNode.x+x].H);
                        }
                    }
        }
        return null;
    } 

    
    /** Reverses a direction. (Given up, returns down,  given left, returns right,
     * etc.)
     */    
    public byte reverse(byte direction) {
        switch (direction) {
            case Command.DOWNRIGHT:
                return Command.UPLEFT;
            case Command.DOWNLEFT:
                return Command.UPRIGHT;
            case Command.UPLEFT:
                return Command.DOWNRIGHT;
            case Command.UPRIGHT:
                return Command.DOWNLEFT;
            case Command.RIGHT:
                return Command.LEFT;
            case Command.DOWN:
                return Command.UP;
            case Command.LEFT:
                return Command.RIGHT;
            case Command.UP:
                return Command.DOWN;
            case Command.CLIMB:
                return Command.FALL;
            case Command.FALL:
                return Command.CLIMB;
            default:
                return -1;
        }
    }
    
    /** Contains a location along the path and the respective weights for the heuristic. */    
    public class Node {
        int G,H,x,y,z;
        byte toLastNode;
        
        public Node(int x,int y,int z,int G,byte toLastNode) {
            this.G = G;
            this.x = x;
            this.y = y;
            this.z = z;
            this.toLastNode = toLastNode;
            H = 10*(Math.abs(x-targetx)+Math.abs(y-targety)+Math.abs(z-targetz));
        }
        
        public Node translate(int direction) {
            switch (direction) {
                case Command.DOWNRIGHT:
                    return nodeMap[z][y][x+1];
                case Command.DOWNLEFT:
                    return nodeMap[z][y+1][x];
                case Command.UPLEFT:
                    return nodeMap[z][y][x-1];
                case Command.UPRIGHT:
                    return nodeMap[z][y-1][x];
                case Command.DOWN:
                    return nodeMap[z][y+1][x+1];
                case Command.LEFT:
                    return nodeMap[z][y+1][x-1];
                case Command.UP:
                    return nodeMap[z][y-1][x-1];
                case Command.RIGHT:
                    return nodeMap[z][y-1][x+1];
                case Command.CLIMB:
                    return nodeMap[z+1][y][x];
                case Command.FALL:
                    return nodeMap[z-1][y][x];
                    
            }
            return null;
        }
        
    }
    
}
