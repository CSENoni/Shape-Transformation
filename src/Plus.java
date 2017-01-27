import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

/**
 * The specific shape of Plus
 */
public class Plus extends AbstractShape implements Cloneable {

	/**
	 * The constructor that are necessary for the plus to make its shape
	 * includes
	 * 
	 * @param centerX
	 *            the center of the horizontally x-axis
	 * @param centerY
	 *            the center of the vertically y-axis
	 * @param size
	 *            the size of the plus
	 * @param color
	 *            the color of the plus
	 */
	public Plus(int centerX, int centerY, double size, Color color) {
		super(centerX, centerY, size, color);
	}

	/**
	 * Create the plus
	 */
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.drawLine(this.centerX - w / 2, this.centerY, this.centerX + w / 2,
				this.centerY);
		g.drawLine(this.centerX, this.centerY - h / 2, this.centerX,
				this.centerY + h / 2);
		super.draw(g);
	}

	/**
	 * return a Plus children
	 * 
	 * @throws Exception
	 */
	public Shape createChildrenShape() throws Exception {
		// set the new size
		double nSize = size / 2;
		// If new size < 1 pixel or is not in correct direction, throw an
		// exception
		if (nSize < 1) {
			JOptionPane.showMessageDialog(null,
					"You cannot add anymore Plus shape.", "size <= 1",
					JOptionPane.INFORMATION_MESSAGE);
			throw new NoSuchFieldException("The size of Plus is too small.");
		} else if (centerX < centerX - w / 2 || centerX > centerX + w / 2
				|| centerY < centerY - h / 2 || centerY > centerY + h / 2) {
			throw new Exception("Invalid centerX:centerY (" + centerX + ":"
					+ centerY + ")");
		} else if (north == null) {
			return new Plus(this.centerX, this.centerY - h / 2, nSize,
					this.color);
		} else if (south == null) {
			return new Plus(this.centerX, this.centerY + h / 2, nSize,
					this.color);
		} else if (west == null) {
			return new Plus(this.centerX - w / 2, this.centerY, nSize,
					this.color);
		} else {
			return new Plus(this.centerX + w / 2, this.centerY, nSize,
					this.color);
		}
	}
}
