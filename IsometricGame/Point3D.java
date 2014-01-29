/*
 * @(#)Point3D.java	1.16 01/12/03
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package IsometricGame;

/**
 * The <code>Point3D</code> class defines a point representing a location
 * in (x,&nbsp;y) coordinate space.
 * <p>
 * This class is only the abstract superclass for all objects that
 * store a 2D coordinate.
 * The actual storage representation of the coordinates is left to
 * the subclass.
 *
 * @version 	1.16, 12/03/01
 * @author	Jim Graham
 */
public abstract class Point3D implements Cloneable, java.io.Serializable {
    /**
     * The <code>Int</code> class defines a point specified in int
     * precision.
     */
    public static class Int extends Point3D {
	/**
	 * The X coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public int x;

	/**
	 * The Y coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public int y;
	
	/**
	 * The Z coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public int z;

	/**
	 * Constructs and initializes a <code>Point3D</code> with
         * coordinates (0,0,0).
	 * @since 1.2
	 */
	public Int() {
	}

	/**
	 * Constructs and initializes a <code>Point3D</code> with 
         * the specified coordinates.
         * @param x,y,z the coordinates to which to set the newly
         * constructed <code>Point3D</code>
	 * @since 1.2
	 */
	public Int(int x, int y,int z) {
	    this.x = x;
	    this.y = y;
		this.z = z;
	}

	/**
	 * Returns the X coordinate of this <code>Point3D</code> in 
         * <code>double</code> precision.
         * @return the X coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public double getX() {
	    return (double) x;
	}

	/**
	 * Returns the Y coordinate of this <code>Point3D</code> in 
         * <code>double</code> precision.
         * @return the Y coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public double getY() {
	    return (double) y;
	}

	/**
	 * Returns the Y coordinate of this <code>Point3D</code> in 
         * <code>double</code> precision.
         * @return the Y coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public double getZ() {
	    return (double) z;
	}

	/**
	 * Sets the location of this <code>Point3D</code> to the 
         * specified <code>double</code> coordinates.
         * @param x,&nbsp;y the coordinates to which to set this
         * <code>Point3D</code>
	 * @since 1.2
	 */
	public void setLocation(double x, double y,double z) {
	    this.x = (int) x;
	    this.y = (int) y;
	    this.z = (int) z;
	}

	/**
	 * Sets the location of this <code>Point3D</code> to the 
         * specified <code>int</code> coordinates.
         * @param x,y,z the coordinates to which to set this
         * <code>Point3D</code>
	 * @since 1.2
	 */
	public void setLocation(int x, int y,int z) {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	}

	/**
	 * Returns a <code>String</code> that represents the value 
         * of this <code>Point3D</code>.
         * @return a string representation of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public String toString() {
	    return "Point3D.Int["+x+", "+y+", "+z+"]";
	}
    }

    /**
     * The <code>Double</code> class defines a point specified in 
     * <code>double</code> precision.
     */
    public static class Double extends Point3D {
	/**
	 * The X coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public double x;

	/**
	 * The Y coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public double y;

	/**
	 * The Z coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public double z;

	/**
	 * Constructs and initializes a <code>Point3D</code> with
         * coordinates (0,0,0).
	 * @since 1.2
	 */
	public Double() {
	}

	/**
	 * Constructs and initializes a <code>Point3D</code> with the
         * specified coordinates.
         * @param x,y,z the coordinates to which to set the newly
         * constructed <code>Point3D</code>
	 * @since 1.2
	 */
	public Double(double x, double y,double z) {
	    this.x = x;
	    this.y = y;
		this.z = z;
	}
        
	/** Constructs and initializes a <code>Point3D</code> with the
	 * specified Point3D.Int.
	 */	
        public Double(Point3D.Int intPoint)
        {
            this.x = intPoint.x;
            this.y = intPoint.y;
            this.z = intPoint.z;
        }

	/**
	 * Returns the X coordinate of this <code>Point3D</code> 
         * in <code>double</code> precision.
         * @return the X coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public double getX() {
	    return x;
	}

	/**
	 * Returns the Y coordinate of this <code>Point3D</code> in 
         * <code>double</code> precision.
         * @return the Y coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public double getY() {
	    return y;
	}

	/**
	 * Returns the Z coordinate of this <code>Point3D</code> in 
         * <code>double</code> precision.
         * @return the Z coordinate of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public double getZ() {
	    return z;
	}

	/**
	 * Sets the location of this <code>Point3D</code> to the 
         * specified <code>double</code> coordinates.
         * @param x,y,z the coordinates to which to set this
         * <code>Point3D</code>
	 * @since 1.2
	 */
	public void setLocation(double x, double y,double z) {
	    this.x = x;
	    this.y = y;
		this.z = z;
	}

	/**
	 * Returns a <code>String</code> that represents the value 
         * of this <code>Point3D</code>.
         * @return a string representation of this <code>Point3D</code>.
	 * @since 1.2
	 */
	public String toString() {
	    return "Point3D.Double["+x+", "+y+", "+z+"]";
	}
    }

    /**
     * This is an abstract class that cannot be instantiated directly.
     * Type-specific implementation subclasses are available for
     * instantiation and provide a number of formats for storing
     * the information necessary to satisfy the various accessor
     * methods below.
     *
     * @see java.awt.geom.Point3D.Int
     * @see java.awt.geom.Point3D.Double
     * @see java.awt.Point
     */
    protected Point3D() {
    }

    /**
     * Returns the X coordinate of this <code>Point3D</code> in 
     * <code>double</code> precision.
     * @return the X coordinate of this <code>Point3D</code>.
     * @since 1.2
     */
    public abstract double getX();

    /**
     * Returns the Y coordinate of this <code>Point3D</code> in 
     * <code>double</code> precision.
     * @return the Y coordinate of this <code>Point3D</code>. 
     * @since 1.2
     */
    public abstract double getY();

    /**
     * Returns the Z coordinate of this <code>Point3D</code> in 
     * <code>double</code> precision.
     * @return the Z coordinate of this <code>Point3D</code>. 
     * @since 1.2
     */
    public abstract double getZ();
	
    /** */    
	public java.awt.Point getHorizontalPoint()
	{
		return new java.awt.Point((int)getX(), (int)getY());
	}

    /**
     * Sets the location of this <code>Point3D</code> to the 
     * specified <code>double</code> coordinates.
     * @param x,&nbsp;y the coordinates of this <code>Point3D</code>
     * @since 1.2
     */
    public abstract void setLocation(double x, double y,double z);

    /**
     * Sets the location of this <code>Point3D</code> to the same
     * coordinates as the specified <code>Point3D</code> object.
     * @param p the specified <code>Point3D</code> the which to set
     * this <code>Point3D</code>
     * @since 1.2
     */
    public void setLocation(Point3D p) {
	setLocation(p.getX(), p.getY(), p.getZ());
    }

    /**
     * Returns the square of the distance between two points.
     * @param X1,&nbsp;Y1 the coordinates of the first point
     * @param X2,&nbsp;Y2 the coordinates of the second point
     * @return the square of the distance between the two
     * sets of specified coordinates.
     */
    public static double distanceSq(double X1, double Y1,
				    double Z1,double X2, double Y2,double Z2) {
	X1 -= X2;
	Y1 -= Y2;
	Z1 -= Z2;
	return (X1 * X1 + Y1 * Y1 + Z1 * Z1);
    }

    /**
     * Returns the distance between two points.
     * @param X1,&nbsp;Y1 the coordinates of the first point
     * @param X2,&nbsp;Y2 the coordinates of the second point
     * @return the distance between the two sets of specified
     * coordinates.
     */
    public static double distance(double X1, double Y1, double Z1,
				  double X2, double Y2, double Z2) {
	X1 -= X2;
	Y1 -= Y2;
	Z1 -= Z2;
	return Math.sqrt(X1 * X1 + Y1 * Y1 + Z1 * Z1);
    }

    /**
     * Returns the square of the distance from this 
     * <code>Point3D</code> to a specified point.
     * @param PX,&nbsp;PY the coordinates of the other point
     * @return the square of the distance between this
     * <code>Point3D</code> and the specified point.
     */
    public double distanceSq(double PX, double PY,double PZ) {
	PX -= getX();
	PY -= getY();
	PZ -= getZ();
	return (PX * PX + PY * PY + PZ * PZ);
    }

    /**
     * Returns the square of the distance from this 
     * <code>Point3D</code> to a specified <code>Point3D</code>.
     * @param pt the specified <code>Point3D</code>
     * @return the square of the distance between this
     * <code>Point3D</code> to a specified <code>Point3D</code>.
     */
    public double distanceSq(Point3D pt) {
	double PX = pt.getX() - this.getX();
	double PY = pt.getY() - this.getY();
	double PZ = pt.getZ() - this.getZ();
	return (PX * PX + PY * PY + PZ * PZ);
    }

    /**
     * Returns the distance from this <code>Point3D</code> to 
     * a specified point.
     * @param PX,&nbsp;PY the coordinates of the specified
     * <code>Point3D</code>
     * @return the distance between this <code>Point3D</code>
     * and a specified point.
     */
    public double distance(double PX, double PY,double PZ) {
	PX -= getX();
	PY -= getY();
	PZ -= getZ();
	return Math.sqrt(PX * PX + PY * PY + PZ * PZ);
    }

    /**
     * Returns the distance from this <code>Point3D</code> to a
     * specified <code>Point3D</code>.
     * @param pt the specified <code>Point3D</code>
     * @return the distance between this <code>Point3D</code> and
     * the specified <code>Point3D</code>.
     */
    public double distance(Point3D pt) {
	double PX = pt.getX() - this.getX();
	double PY = pt.getY() - this.getY();
	double PZ = pt.getZ() - this.getZ();
	return Math.sqrt(PX * PX + PY * PY + PZ * PZ);
    }

    /**
     * Creates a new object of the same class and with the
     * same contents as this object.
     * @return     a clone of this instance.
     * @exception  OutOfMemoryError            if there is not enough memory.
     * @see        java.lang.Cloneable
     * @since      1.2
     */
    public Object clone() {
	try {
	    return super.clone();
	} catch (CloneNotSupportedException e) {
	    // this shouldn't happen, since we are Cloneable
	    throw new InternalError();
	}
    }

    /**
     * Determines whether or not two points are equal. Two instances of
     * <code>Point3D</code> are equal if the values of their 
     * <code>x</code> and <code>y</code> member fields, representing
     * their position in the coordinate space, are the same.
     * @param obj an object to be compared with this <code>Point3D</code>
     * @return <code>true</code> if the object to be compared is
     *         an instance of <code>Point3D</code> and has
     *         the same values; <code>false</code> otherwise.
     * @since 1.2
     */
    public boolean equals(Object obj) {
	if (obj instanceof Point3D) {
	    Point3D p2d = (Point3D) obj;
	    return (getX() == p2d.getX()) && (getY() == p2d.getY()) && (getZ() == p2d.getZ());
	}
	return super.equals(obj);
    }
}
