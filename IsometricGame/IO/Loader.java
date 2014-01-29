/*
 * Loader.java
 *
 * Created on June 29, 2003, 4:28 PM
 */

package IsometricGame.IO;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

/** The Loader interface handles loading of images/text and the creation of streams.
 * @author jgauci
 */
public interface Loader {
    /** This function loads a BufferedImage given the filename as a string and a boolean
     * for whether the object is translucent.
     * @param location The location relative to the working directory where the image is located.
     * @param translucent Whether the image uses alpha transparency or bitmask transparency.
     * @return Returns a BufferedImage
     */    
    public BufferedImage loadBufferedImage(String location,boolean translucent);
    
    /** This function loads a VolatileImage given the filename as a string.
     * @param location The location relative to the working directory where the image is located.
     * @return Returns a VolatileImage
     */    
    public VolatileImage loadVolatileImage(String location);
    
    /** This function creates an ObjectInputStream given the filename as a string.
     * @param location The location relative to the working directory where the image is located.
     * @return The ObjectInputStream
     */    
    public ObjectInputStream getObjectInputStream(String location);
    
    /** This function creates an BufferedReader given the filename as a string.
     * @param location The location relative to the working directory where the image is located.
     * @return The BufferedReader
     */    
    public BufferedReader getBufferedReader(String location);

    /** Responsible for destroying all of the resources and killing the client. */    
    public void exit();
}
