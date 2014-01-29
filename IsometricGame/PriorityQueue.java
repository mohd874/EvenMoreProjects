/*
 * PriorityQueue.java
 *
 * Created on July 29, 2003, 1:23 PM
 */

package IsometricGame;

import java.io.Serializable;
import java.util.*;

/** Represents a Priority Queue. */
public class PriorityQueue {
    Vector nodes;
    
    /** Creates a PriorityQueue with a specified maximum priority
     * @param size The maximum priority
     */    
    public PriorityQueue(int size) {
        nodes = new Vector(size);
    }
    
    public void insert(Object data,int priority) {
        Node tempNode = new Node(data,priority);
        for(int a=0;;a++) {
            if(a==nodes.size()) {
                nodes.add(tempNode);
                break;
            }
            
            Node listNode = (Node)nodes.elementAt(a);
            if(listNode.priority>tempNode.priority) {
                nodes.insertElementAt(tempNode, a);
                break;
            }
        }
        
    }
    
    /** Finds and removes an Object from the PriorityQueue */    
    public Object remove(Object o) {
        for(int a=0;a<nodes.size();a++)
            if(((Node)nodes.elementAt(a)).data==o)
                return ((Node)nodes.remove(a)).data;
        return null;
    }
    
    public Object removeAt(int index) {
        return ((Node)nodes.remove(index)).data;
    }
    
    /** Gives an object a new priority. */    
    public void changePriority(Object o,int newPriority) {
        for(int a=0;a<nodes.size();a++)
            if(((Node)nodes.elementAt(a)).data==o)
                insert(removeAt(a),newPriority);
    }
    
    /** Clears the PriorityQueue and deletes all nodes. */    
    public void clear()
    {
        nodes.clear();
    }
    
    /** Checks if the PriorityQueue is empty */    
    public boolean isEmpty()
    {
        return(nodes.isEmpty());
    }
    
    /** Gets the first item in the PriorityQueue */    
    public Object getFirst()
    {
        return ((Node)nodes.elementAt(0)).data;
    }
    
    /** Removes the first item in the PriorityQueue */    
    public Object removeFirst()
    {
        return ((Node)nodes.remove(0)).data;
    }
    
    /** Represents a node in the PriorityQueue */    
    public class Node{
        Object data;
        int priority;
	/** Creates a Node with a specified object and priority */	
        public Node(Object data,int priority) {
            this.priority = priority;
            this.data = data;
        }
    }
}