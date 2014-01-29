package IsometricGame;

import java.awt.image.*;
import java.awt.*;

/*
 * PixelImage.java
 *
 * Created on August 9, 2003, 1:10 PM
 */

/**
 *
 * @author  root
 */
public class PixelImage {
    int width,height;
    int[] pixels;
    MemoryImageSource memImg;
    public static final int OPAQUE_ALPHA = 255 << 24;  // useful constant, when alpha is ignored
    boolean mask[]; // 1 = there is image color in this spot
    BufferedImage bufImage;
    Graphics2D bufG;

    int alphaMask = (0xFF << 16) | (0xFF << 8) | 0xFF;
    
    
    /** Creates a new instance of PixelImage */
    public PixelImage(int width,int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width*height];
        memImg = new MemoryImageSource(width,height,pixels,0,width);
	bufImage = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(width,height, Transparency.BITMASK);
	bufG = bufImage.createGraphics();
    }
    
    // set a pixel to an rgb color
    /*public void setPixel(int xloc, int yloc, Color c) throws ArrayIndexOutOfBoundsException {
        setPixel(xloc, yloc, c.getRed(), c.getGreen(), c.getBlue());
    }

    public void setPixel(int xloc, int yloc, int r, int g, int b) throws ArrayIndexOutOfBoundsException {
      setPixel(xloc, yloc, r, g, b, 255);
    }

    public void setPixel(int xloc, int yloc, int r, int g, int b, int a) throws ArrayIndexOutOfBoundsException {
       if(xloc<0||yloc<0||xloc>=width||yloc>=height)
	  return;
        pixels[xloc + (yloc * width)] = (a << 24) | (r << 16) | (g << 8) | b;
    }*/
    
    public int getPixel(int xloc, int yloc) throws ArrayIndexOutOfBoundsException 
    {
        return pixels[xloc + (yloc*width)];
    }
    
    public void fillImage(int xShift,int yShift,BufferedImage tempImage,Color c)
    {       
        for(int a=yShift,y=0;a<yShift+tempImage.getHeight();a++,y++)
	{
	   try
	   {
	       java.util.Arrays.fill(pixels,xShift + (a * width),(xShift+tempImage.getWidth())+(a*width),(c.getAlpha() << 24) | (c.getRed() << 16) | (c.getGreen() << 8) | c.getBlue());
	   }
	   catch(ArrayIndexOutOfBoundsException e)
	   {
	   }
	}
    }
    
    public void clearImage()
    {
        for(int a=0;a<pixels.length;a++)
        {
            pixels[a]=0;
        }
    }

    public Image getImage() 
    { 
       Image tempImage = Toolkit.getDefaultToolkit().createImage(memImg);
       //bufG.drawImage(tempImage,0,0,null);
       return tempImage;
    }

}
