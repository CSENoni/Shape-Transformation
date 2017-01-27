import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Show and display Shape and Plus by making a frame for them to be seen
 */

public class MainClass {

	public static void main(String[] args) {
		// Create Graphics window and show the graphics view
		JFrame frame = new JFrame("Shape Show");
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 100);

		// Make 2 radio buttons
		JRadioButton lvUpButton = new JRadioButton();
		JRadioButton lvDownButton = new JRadioButton();

		// Make a reset button
		JButton resetButton = new JButton("Reset");

		// Group 2 radio buttons
		ButtonGroup group = new ButtonGroup();

		// Make the menu panel for the
		JPanel menu = new JPanel();

		// Make labels for two Buttons
		JLabel upLabel = new JLabel("Increase:");
		JLabel downLabel = new JLabel("Decrese:");

		// Add 2 buttons to the group
		group.add(lvUpButton);
		group.add(lvDownButton);

		// Show menu, labels and buttons on menu
		menu.add(upLabel);
		menu.add(lvUpButton);
		menu.add(downLabel);
		menu.add(lvDownButton);
		menu.add(resetButton);
		frame.add(menu, BorderLayout.SOUTH);

		// show window
		frame.setVisible(true);

		// Make the graphics and add it to window
		ShapeGraphicsView view = new ShapeGraphicsView();
		TextViewer textView = new TextViewer();
		frame.add(view, BorderLayout.CENTER);

		// Create a model from DrawingModel
		// let the viewer register to get the data
		DrawingModel model = new DrawingModel();
		model.addViewers(view);
		model.addViewers(textView);
		
		// Add information of shapes to the model
		// the model will keep track of them and
		// let the viewers update any new shape
		model.addShape(new Plus(120, 170, 60, Color.RED));
		model.addShape(new Diamond(360, 170, 60, Color.GREEN));
		ShapeGraphicsController controller = new ShapeGraphicsController(model,
				lvUpButton, lvDownButton);
		// make lvUpButton is default selected
		lvUpButton.setSelected(true);
		
		// enable the events
		frame.addMouseListener(controller);
		resetButton.addActionListener(controller);
	}
}
