// This entire file is part of my masterpiece.
// RAHUL HARIKRISHNAN
package frontend;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

/**
 * This class creates the buttons on the left pane of the GUI and handles response
 * to button clicks as well as selection of pull-down menu items.
 * @author Rahul
 *
 */
public class SimulationButtons {
	private static final int SCALE_HEIGHT = 40;
	private static final String VERY_FAST = "Faster";
	private static final String FAST = "Fast";
	private static final String NORMAL = "Normal";
	private static final String SLOW = "Slow";
	private static final String VERY_SLOW = "Slower";

	private CellViewer myCellViewer;
	protected boolean myStepClicked = false;
	/**
	 * Lowest speed of the animation.
	 */
	private static final double LOWEST_SPEED = 0.5;
	/**
	 * Button last clicked to handle processing "stepping into" simulation
	 * frames
	 */
	protected Button myLastClicked = null;
	/**
	 * Buttons used for user interaction.
	 */
	protected Button myReset;
	protected Button myStart;
	protected Button myStop;
	protected Button myStep;
	protected VBox myButtonVBox;
	protected Timeline myAnimation;	
	protected ComboBox<String> mySpeedSelected;

	public SimulationButtons(Timeline animation, CellViewer cellViewer) {
		myAnimation = animation;
		myCellViewer = cellViewer;
	}
	
	public SimulationButtons() {
		
	}

	/**
	 * Initializes the buttons by creating a vertical box, disabling the buttons, and setting them on action. 
	 */
	protected void initializeButtons() {
		mySpeedSelected = new ComboBox<String>();
		addButtons();
		myButtonVBox = createButtonsVBox();
		disableButtons(true);
		setButtonsOnAction();
	}

	/**
	 * Enables/disables buttons based on boolean passed in. Ensures
	 * buttons are not active until necessary.
	 * @param disable
	 * 		whether the buttons are deactivated such as prior to 
	 * 		the user selecting a valid XML file.
	 */
	protected void disableButtons(boolean disable) {
		myReset.setDisable(disable);
		myStart.setDisable(disable);
		myStop.setDisable(disable);
		myStep.setDisable(disable);
	}

	/**
	 * Returns layout with vertically aligned buttons.
	 */
	private VBox createButtonsVBox() {
		VBox vbox = new VBox();
		vbox.setSpacing((myCellViewer.myHeight) / SCALE_HEIGHT);
		vbox.setPadding(new Insets((myCellViewer.myHeight)/4, 0, 0, 2));

		vbox.getChildren().add(myReset);
		vbox.getChildren().add(myStart);
		vbox.getChildren().add(myStop);
		vbox.getChildren().add(myStep);
		vbox.getChildren().add(new Text("Speed"));
		vbox.getChildren().add(mySpeedSelected);

		return vbox;
	}

	/**
	 * Add buttons placed on left side of window.
	 */
	protected void addButtons() {
		mySpeedSelected.getItems().addAll(VERY_SLOW, SLOW, NORMAL, FAST,
				VERY_FAST);
		mySpeedSelected.setValue(NORMAL);

		myReset = new Button("Reset");
		myStart = new Button("Start/Resume");
		myStop = new Button("Stop/Pause");
		myStep = new Button("Step");
	}

	/**
	 * Sets event handlers for all buttons and action on clicked.
	 */
	protected void setButtonsOnAction() {
		myStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				myAnimation.play();
				myLastClicked = myStart;
			}

		});

		myStop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				myAnimation.pause();
				myLastClicked = myStop;
			}

		});

		myReset.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				myCellViewer.resetGrid();
			}

		});

		myStep.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {
				myAnimation.play();
				myStepClicked = true;
				myLastClicked = myStep;
			}
		});
	}

	/**
	 * Update animation speed based on selection.
	 */
	protected void checkSpeedSelection() {
		List<String> possibleSpeeds = new ArrayList<String>();
		Collections.addAll(possibleSpeeds, VERY_SLOW, SLOW, NORMAL,
				FAST, VERY_FAST);
		for (int i = 0; i < possibleSpeeds.size(); i++) {
			if (mySpeedSelected.getValue().equals(
					possibleSpeeds.get(i))) {
				myAnimation.setRate(LOWEST_SPEED * (i + 1));
				return;
			}
		}
	}
}