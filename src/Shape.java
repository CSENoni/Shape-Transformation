import java.awt.Color;
import java.awt.Graphics;

/**
 * An interface that hold some useful method that the classes implements could
 * use.
 */
public interface Shape {
	void draw(Graphics g); // All objects implement Shape should implements
							// draw

	boolean addLevel(); // All objects implement Shape should implement
						// addLevel()
						// to increase the level of shape if required

	boolean removeLevel(); // All objects implement Shape should implement
							// removeLevel() to decrease the level of shape if
							// required

	Shape createChildrenShape() throws Exception; // All objects implement Shape
													// should implement
	// this method to make recursive shape

	int getLevel(); // All objects implement Shape should implement this method
					// to return the number level of shape

	void setParent(Shape s); // All objects implement Shape should implement
								// this method to make a parent shape

	Shape getParent(); // All objects implement Shape should implement this
						// method to return a parent shape

	boolean getBoundingBox(int x, int y);// All objects implement Shape should
											// implement this
	// method to check if the click from controller is on the shape

	void removeAll(); // All objects implement Shape should implement this
						// method to remove all of the shape except the original

	void setNorth(Shape s);// All objects implement Shape should implement this
	// method to set North to assigned shape

	void setSouth(Shape s);// All objects implement Shape should implement this
	// method to set South to assigned shape

	void setWest(Shape s);// // All objects implement Shape should implement
							// this
	// method to set west to assigned shape

	void setEast(Shape s);// All objects implement Shape should implement this
	// method to set east to assigned shape

	Color getColor();// All objects implement Shape should implement this
	// method to return this shape color

	double getSize();// All objects implement Shape should implement this
	// method to return size of this shape

	Shape getNorth();// All objects implement Shape should implement this
	// method to return north direction of this shape

	Shape getSouth();// All objects implement Shape should implement this
	// method to return south direction of this shape

	Shape getWest();// All objects implement Shape should implement this
	// method to return west direction of this shape

	Shape getEast();// All objects implement Shape should implement this
	// method to return east direction of this shape
}
