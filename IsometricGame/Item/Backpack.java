/*
 * Backpack.java
 *
 * Created on September 10, 2003, 10:27 AM
 */

package IsometricGame.Item;

import java.awt.image.*;

/** This class represents an Avatar's inventory.
 * @author jgauci
 */
public class Backpack {
   private Item[][] contents;
   private boolean[][] draw;
   
   /** This is the width of the backpack in items */
   public int width;
   
   /** This is the height of the backpack in items */   
   public int height;
   
   /** Creates a new instance of Backpack
    * @param width This is the width of the backpack in items
    * @param height This is the height of the backpack in items
    */
   public Backpack(int width,int height) {
      contents = new Item[height][width];
      draw = new boolean[height][width];
      this.width = width;
      this.height = height;
   }
   
   public Item[][] getContents() {
      return contents;
   }
   
   public boolean insertItem(Item item,int width,int height) {
      synchronized(this) {
	 System.out.println("Inserting item at " + width + "," + height + " with size " + item.getModel().imageSize.width + "," + item.getModel().imageSize.height);
	 for(int a=height;a<height+item.getModel().imageSize.height;a++)
	    for(int b=width;b<width+item.getModel().imageSize.width;b++) {
	       try {
		  if(contents[a][b]!=null)
		     return false;
	       }
	       catch(java.lang.ArrayIndexOutOfBoundsException aioobe) {
		  return false;
	       }
	    }
	 for(int a=height;a<height+item.getModel().imageSize.height;a++)
	    for(int b=width;b<width+item.getModel().imageSize.width;b++) {
	       contents[a][b]=item;
	    }
	 draw[height][width] = true;
	 System.out.println("Insert Sucessful");
	 return true;
      }
   }
   
   public Item removeItem(int width,int height) {
      synchronized(this) {
	 Item item = contents[height][width];
	 if(item==null)
	    return null;
	 
	 
	 for(int a=0;a<this.height;a++)
	    for(int b=0;b<this.width;b++) {
	       if(contents[a][b]==item)
		  draw[a][b]=false;
		  contents[a][b]=null;
	    }
	 return item;
      }
   }
   
   public Item getItemAt(int width,int height) {
      synchronized(this) {
	 return contents[height][width];
      }
   }
   
   public BufferedImage getImage(int width,int height) {
      synchronized(this) {
	 if(draw[height][width]==true)
	    return contents[height][width].getModel().image;
	 return null;
      }
   }
   
   
   
}
