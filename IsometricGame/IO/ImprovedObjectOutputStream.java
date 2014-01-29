/*
 * ImprovedObjectOutputStream.java
 *
 * Created on September 12, 2003, 8:29 PM
 */

package IsometricGame.IO;

import java.io.*;

/** This is a wrapper around ObjectOutputStream for the purpose of being able to
 * send objects without forcing the programmer to .reset() and .flush() every time.
 * @author jgauci
 */
public class ImprovedObjectOutputStream extends ObjectOutputStream {
    
    /** Creates a new instance of ImprovedObjectOutputStream */
    public ImprovedObjectOutputStream() throws IOException {
        super();
    }
    
    /** Creates a new instance of ImprovedObjectOutputStream */    
    public ImprovedObjectOutputStream(java.io.OutputStream o) throws IOException
    {
        super(o);
    }
    
    /** Calls writeObject() from ObjectOutputStream, but then flushes and resets the
     * buffer.
     */    
    public void improvedWriteObject(final Object o) throws IOException
    {
        super.writeObject(o);
        flush();
        reset();
    }
    
    public void writeBoolean(boolean o) throws IOException
    {
        super.writeBoolean(o);
        flush();
    }

    public void writeByte(byte o) throws IOException
    {
        super.writeByte(o);
        flush();
    }
    
    public void writeUTF(String o) throws IOException
    {
        super.writeUTF(o);
        flush();
    }
    
    public void writeInt(int o) throws IOException
    {
        super.writeInt(o);
        flush();
    }

}
