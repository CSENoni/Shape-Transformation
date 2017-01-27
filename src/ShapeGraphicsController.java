import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

/**
 * Interacts with the interface with mouse click or any other events
 */
public class ShapeGraphicsController extends MouseAdapter implements
		ActionListener {
	private DrawingModel model; // hold information of DrawingModel
	private JRadioButton lvUp; // take information from the button
	private JRadioButton lvDown;// take information from the button

	/**
	 * Build an constructor of this class
	 * 
	 * @param model
	 *            information from DrawingModel
	 * @param lvUp
	 *            the button
	 * @param lvDown
	 *            the button
	 */
	public ShapeGraphicsController(DrawingModel model, JRadioButton lvUp,
			JRadioButton lvDown) {
		this.model = model;
		this.lvUp = lvUp;
		this.lvDown = lvDown;
	}

	/**
	 * Take action when the mouse press is performed
	 */
	public void mousePressed(MouseEvent e) {
		// Is it on the shape?
		if (lvUp.isSelected()) {
			model.levelUp(e.getX(), e.getY());
		} else if (lvDown.isSelected()) {
			model.levelDown(e.getX(), e.getY());
		}
	}

	/**
	 * Take action when the button is pressed
	 */
	public void actionPerformed(ActionEvent arg0) {
		// reset the screen to original
		model.reset();
	}
}
