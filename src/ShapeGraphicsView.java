import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

/**
 * Make the graphics out of the shape by using the information from
 * DrawingModel. The viewer should be notified of change by the model
 */
public class ShapeGraphicsView extends JPanel implements DrawingView {

	private DrawingModel model; // keep track of the information from model

	/**
	 * Create a constructor that set the color of the panel background white
	 */
	public ShapeGraphicsView() {
		this.setBackground(Color.WHITE);
	}

	/**
	 * Be notified by the model if there is any update. Show it by graphics
	 */
	public void notify(DrawingModel model) {
		this.model = model;
		repaint();
	}

	/**
	 * Repaint the graphics if there is any update
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (model != null) {
			List<Shape> l = model.getShapes();
			for (int i = 0; i < l.size(); i++) {
				Shape shape = l.get(i);
				shape.draw(g);
			}
		}
	}
}
