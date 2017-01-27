import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import javax.swing.JOptionPane;

/**
 * The specific shape of Diamond
 */
public class Diamond extends AbstractShape implements Cloneable {

	private int[] x, y; // make the vertices of polygon

	/**
	 * The constructor that are necessary for diamond to make its shape
	 * includes:
	 * 
	 * @param centerX
	 *            the center of the horizontally x-axis
	 * @param centerY
	 *            the center of the vertically y-axis
	 * @param size
	 *            the size of the diamond
	 * @param color
	 *            the color of the diamond
	 */
	public Diamond(int centerX, int centerY, double size, Color color) {
		super(centerX, centerY, size, color);
		// declare numbers of vertices of polygon (suppose to be 4)
		x = new int[4];
		y = new int[4];
	}

	/**
	 * Create the diamond
	 */
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setColor(this.color);
		g2D.fillPolygon(createPolygon());
		super.draw(g2D);
	}

	/**
	 * Using polygon to make a diamond
	 */
	private Polygon createPolygon() {
		int w = (int) (WIDTH * size);
		int h = (int) (HEIGHT * size);
		// make a polygon

		// first point
		x[0] = centerX - w / 2;
		y[0] = centerY;

		// second point
		x[1] = centerX;
		y[1] = centerY - h / 2;

		// third point
		x[2] = centerX + w / 2;
		y[2] = centerY;

		// fourth point
		x[3] = centerX;
		y[3] = centerY + h / 2;

		return new Polygon(x, y, x.length);
	}

	// Set up several methods to test
	// if deepCopy() method works
	/**
	 * This is just for test set size for diamond
	 */
	public void setSize(double size) {
		this.size = size;
	}

	/**
	 * This is just for test set color for diamond
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * return a Diamond children
	 * 
	 * @throws Exception
	 */
	public Shape createChildrenShape() throws Exception {
		// Change the color each time it is created in recursion
		Color nColor = new Color(255 - color.getRed(), 255 - color.getGreen(),
				255 - color.getBlue());
		// set the new size
		double nSize = size / 2;
		// If new size < 1 pixel or is not in correct direction, throw an
		// exception
		if (nSize < 1) {
			JOptionPane.showMessageDialog(null,
					"You cannot add anymore Diamond shape.", "size <= 1",
					JOptionPane.INFORMATION_MESSAGE);
			throw new NoSuchFieldException("The size of Diamond is too small.");
		} else if (centerX < centerX - w / 2 || centerX > centerX + w / 2
				|| centerY < centerY - h / 2 || centerY > centerY + h / 2) {
			throw new Exception("Invalid centerX:centerY (" + centerX + ":"
					+ centerY + ")");
		} else if (north == null) {
			return new Diamond(this.centerX, this.centerY - h / 2, nSize,
					nColor);
		} else if (south == null) {
			return new Diamond(this.centerX, this.centerY + h / 2, nSize,
					nColor);
		} else if (west == null) {
			return new Diamond(this.centerX - w / 2, this.centerY, nSize,
					nColor);
		} else {
			return new Diamond(this.centerX + w / 2, this.centerY, nSize,
					nColor);
		}
	}
}
