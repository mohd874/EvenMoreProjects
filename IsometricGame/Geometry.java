/*
 * Algebra.java
 *
 * Created on July 8, 2003, 10:02 PM
 */

package IsometricGame;

import java.awt.*;
import java.awt.geom.*;

/**
 *
 * @author  jgauci
 */
public class Geometry implements java.io.Serializable {
    
    /** Creates a new instance of Algebra */
    public Geometry() {
    }
    
    
    public static int getDeterminant(int a,int b,int c,int d) {
        return (a*d-b*c);
    }
    
    /** Solves systems of linear equations with 2 unknowns.
     * Takes parameters in the form Ax+By=C, Dx+Ey=F
     */    
    public static Point2D.Double kramersRule(int A,int B,int C,int D,int E,int F) {
        return(new Point2D.Double(((double)getDeterminant(C,B,F,E))/getDeterminant(A,B,D,E),((double)getDeterminant(A,C,D,F))/getDeterminant(A,B,D,E)));
        
    }
    
}
