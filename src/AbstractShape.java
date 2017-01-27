import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

/**
 * An abstract class that all specific shape could inherits to use some useful
 * methods
 */
public abstract class AbstractShape implements Shape {
	public final static int WIDTH = 2; // width of the shape
	public final static int HEIGHT = 2; // height of the shape
	protected int centerX, centerY; // the center location of the shape
	protected double size; // the size of the shape
	protected Color color; // the color of the shape
	protected int w, h; // take the current w and h having size in it

	// hold the shape objects to north, east, south, west of this shape
	// and the parent shape of this shape
	protected Shape north, east, south, west, parent;

	/**
	 * The constructor that are necessary for the specific shapes includes
	 * 
	 * @param centerX
	 *            the center of the horizontally x-axis
	 * @param centerY
	 *            the center of the vertically y-axis
	 * @param size
	 *            the size of the shape
	 * @param color
	 *            the color of the shape
	 */
	public AbstractShape(int centerX, int centerY, double size, Color color) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.size = size;
		this.color = color;
		w = (int) (WIDTH * size);
		h = (int) (WIDTH * size);
	}

	/**
	 * Set this Shape a Shape parent
	 * 
	 * @param parent
	 *            : this shape
	 */
	public void setParent(Shape parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent of this shape
	 */
	public Shape getParent() {
		return parent;
	}

	/**
	 * Check to see if it is necessary to add 1 level of Shape
	 * 
	 * @return the true or false
	 */

	public boolean addLevel() {
		// add 1 level to the shape
		// if we add shape add north
		// the its children south should
		// not appear, etc to the other directions
		boolean bNorth = false, bSouth = false, bWest = false, bEast = false;
		// try catch it since it may trigger error
		try {
			if (north == null) {
				north = createChildrenShape();
				north.setParent(this);
				north.setSouth(this);
				bNorth = true;
			} else if (north != parent) {
				bNorth = north.addLevel();
			}

			if (south == null) {
				south = createChildrenShape();
				south.setParent(this);
				south.setNorth(this);
				bSouth = true;
			} else if (south != parent) {
				bSouth = south.addLevel();
			}

			if (west == null) {
				west = createChildrenShape();
				west.setParent(this);
				west.setEast(this);
				bWest = true;
			} else if (west != parent) {
				bWest = west.addLevel();
			}

			if (east == null) {
				east = createChildrenShape();
				east.setParent(this);
				east.setWest(this);
				bEast = true;
			} else if (east != parent) {
				bEast = east.addLevel();
			}
			// The field may goes wrong so catch it (size)
		} catch (NoSuchFieldException e) {
			System.out.println(e.getMessage());
			System.exit(0);
			// The other error may occur like the position of children shape
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return bNorth && bSouth && bWest && bEast;
	}

	/**
	 * Check to see if it is necessary to remove 1 level of Shape
	 * 
	 * @return true or false
	 */
	public boolean removeLevel() {
		// base case: check to see if north
		// south, west, east inside of
		// its direction is null or not
		// if null set the father direction
		// null
		try {
			if (north.getNorth() == null) {
				north = null;
				south = null;
				west = null;
				east = null;
				return true;
			} else if (south.getSouth() == null) {
				north = null;
				south = null;
				west = null;
				east = null;
				return true;
			} else if (west.getWest() == null) {
				north = null;
				south = null;
				west = null;
				east = null;
				return true;
			} else if (east.getEast() == null) {
				north = null;
				south = null;
				west = null;
				east = null;
				return true;
			} else {
				// Since the presence of parent is critical
				// we should clean up the parent to successfully
				// remove the edge of the shape
				Shape parent = getParent();
				// at North
				if (north == null) {
					parent = north.getParent();
					parent.setNorth(null);
				} else if (north != this && north != this.getParent()) {
					north.removeLevel();
				}
				// at South
				if (south == null) {
					parent = south.getParent();
					parent.setEast(null);
				} else if (south != this && south != this.getParent()) {
					south.removeLevel();
				}
				// at west
				if (west == null) {
					parent = west.getParent();
					parent.setSouth(null);
				} else if (west != this && west != this.getParent()) {
					west.removeLevel();
				}
				// at East
				if (east == null) {
					parent = east.getParent();
					parent.setWest(null);
				} else if (east != this && east != this.getParent()) {
					east.removeLevel();
				}
				return true;
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,
					"You cannot decrease level anymore.", "Error",
					JOptionPane.CANCEL_OPTION);
			System.out.println("Reach the limit.");
			System.exit(0);
		}
		return false;
	}

	/**
	 * Remove every shape inside the original
	 */
	public void removeAll() {
		north = null;
		south = null;
		east = null;
		west = null;
	}

	/**
	 * Get the number level of parent shape
	 * 
	 * @return level of shape
	 */
	public int getLevel() {
		// base case: no parent shape
		if (north == null) {
			return 0;
		} else {
			return 1 + north.getLevel();
		}
	}

	/**
	 * @return return String of this shape
	 */
	public String toString() {
		String str = this.getClass() + ", " + "centerX = " + centerX
				+ ", centerY = " + centerY;
		if (north == null) {
			return str + ", size = " + size + ", color = " + color
					+ ", level = " + getLevel();
		} else {
			return str + ", size = " + north.getSize() + ", color = "
					+ north.getColor() + ", level = " + getLevel();
		}
	}

	/**
	 * @param x
	 *            , y coordinates of the click
	 * @return true or false x, y on a shape
	 */
	public boolean getBoundingBox(int x, int y) {
		if (x >= centerX - w / 2 && y >= centerY - h / 2
				&& x <= centerX + w / 2 && y <= centerY + h / 2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * set west to assigned shape
	 */
	public void setWest(Shape s) {
		west = s;
	}

	/**
	 * set south to assigned shape
	 */
	public void setSouth(Shape s) {
		south = s;
	}

	/**
	 * set north to assigned shape
	 */
	public void setNorth(Shape s) {
		north = s;
	}

	/**
	 * set east to assigned shape
	 */
	public void setEast(Shape s) {
		east = s;
	}

	/**
	 * @return color of this shape
	 */
	public Color getColor() {
		if (north == null)
			return color;
		else
			return north.getColor();
	}

	/**
	 * @return size of this shape
	 */
	public double getSize() {
		if (north == null)
			return size;
		else
			return north.getSize();
	}

	/**
	 * The basic draw method should have
	 */
	public void draw(Graphics g) {
		if (north != null && north != parent) {
			north.draw(g);
		}
		if (east != null && east != parent) {
			east.draw(g);
		}
		if (south != null && south != parent) {
			south.draw(g);
		}
		if (west != null && west != parent) {
			west.draw(g);
		}
	}

	/**
	 * @return east of this shape
	 */
	public Shape getEast() {
		return east;
	}

	/**
	 * @return north of this shape
	 */
	public Shape getNorth() {
		return north;
	}

	/**
	 * @return south of this shape
	 */
	public Shape getSouth() {
		return south;
	}

	/**
	 * @return west of this shape
	 */
	public Shape getWest() {
		return west;
	}
}