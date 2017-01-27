import java.util.List;

/**
 * Print any current information on the terminal window about the shapes as
 * given by the toString method of a shape
 */
public class TextViewer implements DrawingView {
	private DrawingModel model; // keep track of information from model

	/**
	 * Be notified by the model if there is anything changed
	 * 
	 * @param model
	 *            : get model
	 */
	public void notify(DrawingModel model) {
		this.model = model;
		printOut();
	}

	/**
	 * print the data to the console
	 */
	private void printOut() {
		List<Shape> l = model.getShapes();
		for (Shape s : l) {
			System.out.println(s);
		}
	}
}
