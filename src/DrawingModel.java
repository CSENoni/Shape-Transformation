import java.util.ArrayList;

/**
 * The model keeps track of the data of the drawing of Shape
 */
public class DrawingModel {
	private ArrayList<Shape> shapes; // List of all shapes
	private ArrayList<DrawingView> viewers; // DrawingView registers to view the
											// data

	/**
	 * Create a constructor that instantiates the list of shapes and viewers
	 */
	public DrawingModel() {
		shapes = new ArrayList<Shape>();
		viewers = new ArrayList<DrawingView>();
	}

	/**
	 * add the viewers to the list to notify them if there is any update
	 * 
	 * @param v
	 *            : the viewer
	 */
	public void addViewers(DrawingView v) {
		viewers.add(v);
	}

	/**
	 * add the shapes to the list of keep track of them and notify the viewers
	 * 
	 * @param s
	 *            : the specific shape
	 */
	public void addShape(Shape s) {
		shapes.add(s);
		for (DrawingView v : viewers) {
			v.notify(this);
		}
	}

	/**
	 * get the deep copy version when requested
	 * 
	 * @return the deep copy of each shape
	 */
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	/**
	 * Increase one level to the shape
	 */
	public void levelUp(int x, int y) {
		for (Shape s : shapes) {
			if (s.getBoundingBox(x, y))
				s.addLevel();
		}
		update();
	}

	/**
	 * Decrease one level to the shape
	 */
	public void levelDown(int x, int y) {
		for (Shape s : shapes) {
			if (s.getBoundingBox(x, y))
				s.removeLevel();
		}
		update();
	}

	/**
	 * Update to Graphics
	 */

	private void update() {
		for (DrawingView v : viewers) {
			v.notify(this);
		}
	}

	/**
	 * Reset the Shape
	 */
	public void reset() {
		for (Shape s : shapes) {
			s.removeAll();
		}
		update();
	}
}
