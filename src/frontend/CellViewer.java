package frontend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import backend.cells.Cell;
import backend.simulations.EcoSimulation;
import backend.simulations.FireSimulation;
import backend.simulations.GoLSimulation;
import backend.simulations.SegSimulation;
import backend.simulations.Simulation;
import backend.xml.InitialGameParameters;
import backend.xml.XMLParsing;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CellViewer {
	private static final String VERY_FAST = "Faster";
	private static final String FAST = "Fast";
	private static final String NORMAL = "Normal";
	private static final String SLOW = "Slow";
	private static final String VERY_SLOW = "Slower";

	/**
	 * XML parsing output stored in this object
	 */
	private InitialGameParameters myGameParams;
	private XMLParsing myXMLParser;
	private CellWorld myCellWorld;
	private boolean myGridSet = false;
	private boolean myStepClicked = false;

	/**
	 * Whether file is currently selected used to display cells only after created 
	 */
	private boolean myFileSelected = false;
	/**
	 * Button last clicked to handle processing "stepping into" simulation frames
	 */
	private Button myLastClicked = null;

	/**
	 * Array of cells that are viewed during the simulation
	 */
	private GridPane[][] myViewingGrid;
	/**
	 * Array of cell objects mapped 1:1 to a viewing cell 
	 */
	private Cell[][] myCellsGrid;

	/**
	 * Currently loaded file used in XML parsing
	 */
	private File myFile;
	
	/**
	 * Stores the individual grid panes (cells) as children
	 */
	private GridPane myGridPane;
	/**
	 * Holds layout of the whole GUI
	 */
	private BorderPane myBorderPane;

	private Button myReset;
	private Button myStart;
	private Button myStop;
	private Button myStep;

	/**
	 * Stores the previously loaded XML file to avoid reloading the
	 */
	private File myPreviousFile;

	/**
	 * Superclass object that get instantiated with the appropriate simulation
	 * retrieved from the XML file
	 */
	private Simulation myCellSimulation;

	
	/**
	 * Used in reflection to match simulation mode to appropriate Simulation subclass. 
	 * This changes if the simulation and corresponding cell classes move
	 * to a different package
	 */
	private static final String SIMULATION_PACKAGE = "backend.simulations";
	/**
	 * Used in matching simulation mode (testing equality of class names) with appropriate
	 * Simulation subclass
	 */
	private static final String CLASS_SUFFIX = "simulation";

	/**
	 * Add new simulation to the below array for extensibility
	 * 
	 */
	private Simulation [] mySimulations = { new EcoSimulation(), new FireSimulation(),
			new GoLSimulation(),new SegSimulation()};

	private Timeline myAnimation = new Timeline();
	private String[] myColors;
	private final FileChooser fileChooser = new FileChooser();
	private final Button openButton = new Button("...");
	private final ComboBox<String> speedSelected = new ComboBox<String>();
	private int myHeight;

	/**
	 * Creates CellViewer object which acquires the animation timeline for 
	 * start/stop/reset interaction and CellWorld object to get the initial state
	 * of the grid.
	 * 
	 */
	public CellViewer(Timeline animation, CellWorld cellWorld) {
		myAnimation = animation;
		myCellWorld = cellWorld;
	}

	/**
	 * Set the height of the simulation window
	 *
	 */
	private void setHeight(int height) {
		myHeight = height;
	}

	/**
	 * This method returns the GUI scene with buttons, file chooser, and simulation grid
	 * 
	 */
	public Scene init(Stage stage, int width, int height) {
		setHeight(height);
		myBorderPane = new BorderPane();
		myXMLParser = new XMLParsing();

		Scene scene = new Scene(myBorderPane, width, height);
		addFileSelector(stage);
		addButtons();
		disableButtons(true);
		setButtonsOnAction();
		return scene;
	}

	/**
	 * Generates simulation with reflection by matching simulation name and corresponding class
	 * 
	 */
	private void generateSimulation() {
		for (int i = 0; i < mySimulations.length; i++) {
			if (mySimulations[i].getClass().getName().toLowerCase().equals(SIMULATION_PACKAGE + "." + myGameParams.simulationMode.toLowerCase() + CLASS_SUFFIX)){
				myCellSimulation = mySimulations[i];
				return;		
			}
		}
	}
	
	/**
	 * Enables/disables buttons based on boolean passed in. Ensures buttons are not active
	 * until necessary
	 * 
	 */

	private void disableButtons(boolean disable) {
		myReset.setDisable(disable);
		myStart.setDisable(disable);
		myStop.setDisable(disable);
		myStep.setDisable(disable);
	}

	/**
	 * Initial call to add starting position and states of cells on viewing grid
	 */
	private void addCellsToDisplay() {
		disableButtons(false);
		myGridPane = new GridPane();
		myViewingGrid =  new GridPane[myGameParams.gridXSize][myGameParams.gridYSize];
		myColors = myCellSimulation.myColors;
		myBorderPane.setCenter(myGridPane);

		for (int row = 0; row < myGameParams.gridXSize; row++) {
			for (int col = 0; col < myGameParams.gridYSize; col++) {
				GridPane square = new GridPane();
				Cell cell = myCellsGrid[row][col];

				square.setStyle("-fx-background-color: " + myColors[cell.getState()] + ";");
				myGridPane.add(square, col, row);
				myViewingGrid[row][col] = square;
			}
		}
		/**
		 * Add borders for the cell grid
		 */
		myGridPane.setStyle("-fx-background-color: black;-fx-hgap: 1; -fx-vgap: 1;");
	}

	/**
	 * Adds width and height of rows and columns to ensure identically sized cells
	 */
	private void addGridConstraints() {

		for (int i = 0; i < myGameParams.gridXSize; i++) {
			myGridPane.getRowConstraints().add(
					new RowConstraints(5, Control.USE_COMPUTED_SIZE,
							Double.POSITIVE_INFINITY, Priority.ALWAYS,
							VPos.CENTER, true));
		}

		for (int i = 0; i < myGameParams.gridYSize; i++) {
			myGridPane.getColumnConstraints().add(
					new ColumnConstraints(5, Control.USE_COMPUTED_SIZE,
							Double.POSITIVE_INFINITY, Priority.ALWAYS,
							HPos.CENTER, true));

		}
		myGridPane.setPadding(new Insets(5, 5, 5, 5));
	}

	/**
	 * Adds file selector button to choose XML file with simulation parameters
	 * 
	 */
	private void addFileSelector(Stage stage) {
		HBox hbox = new HBox();
		Text text = new Text("Load an XML file to begin the simulation:  ");
		openButton.setScaleX(0.8);
		openButton.setScaleY(0.8);
		openButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(final ActionEvent e) {

				fileChooser.setTitle("Open XML File");
				fileChooser.getExtensionFilters().addAll(
						new ExtensionFilter("XML Files", "*.xml"));
				myFile = fileChooser.showOpenDialog(stage);
				if (myFile != null) {
					parseXML();
					resetGrid();
				} else {
					fileNotSelected(stage);
				}
			}
		});
		hbox.getChildren().addAll(text, openButton);
		myBorderPane.setTop(hbox);

	}

	/**
	 * Handles user notification (pop-up window) when XML file not selected
	 * 
	 */
	private void fileNotSelected(Stage stage) {

		if (myPreviousFile != null) {
			myFile = myPreviousFile;
		} else {
			/**
			 * Setting separate stage to show pop-up window (missing XML file)
			 * when there is no previously loaded file.
			 * 
			 */
			Stage dialog = new Stage();
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.initOwner(stage);
			VBox textBox = new VBox();
			textBox.getChildren()
			.add(new Text(
					"You haven't selected an XML file.\nPlease select one."));
			Scene dialogScene = new Scene(textBox, 500, 100);
			dialog.setScene(dialogScene);
			dialog.show();
		}
	}

	/**
	 * Parses XML file selected into simulation parameters
	 */
	private void parseXML() {
		try {
			myGameParams = myXMLParser
					.parseInitialCellsFromFile(myFile);
			myFileSelected = true;
			myPreviousFile = myFile;

		} catch (ParserConfigurationException | SAXException
				| IOException e1) {
			e1.printStackTrace();
		}
	} 

	/**
	 * Returns layout with vertically aligned buttons
	 * 
	 */
	private VBox createButtonsVBox() {
		VBox vbox = new VBox();
		vbox.setSpacing(myHeight / 40);
		vbox.setPadding(new Insets(myHeight / 4, 0, 0, 2));

		vbox.getChildren().add(myReset);
		vbox.getChildren().add(myStart);
		vbox.getChildren().add(myStop);
		vbox.getChildren().add(myStep);
		vbox.getChildren().add(new Text("Speed"));
		vbox.getChildren().add(speedSelected);

		return vbox;
	}

	/**
	 * Add buttons placed on left side of window
	 */
	private void addButtons() {
		speedSelected.getItems()
		.addAll(VERY_SLOW, SLOW, NORMAL, FAST, VERY_FAST);
		speedSelected.setValue(NORMAL);

		myReset = new Button("Reset");
		myStart = new Button("Start/Resume");
		myStop = new Button("Stop/Pause");
		myStep = new Button("Step");

		VBox vbox = createButtonsVBox();
		myBorderPane.setLeft(vbox);

	}

	/**
	 * Sets event handlers for all buttons and action on clicked
	 * 
	 */
	private void setButtonsOnAction() {
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
				resetGrid();
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
	 * Sets the original of Cell objects
	 */
	private void setCellsGrid() {
		myCellsGrid = myCellSimulation.initialize(
				myGameParams.simulationMode,
				myGameParams.gridXSize, myGameParams.gridYSize,
				myGameParams.thresholdValue,
				myGameParams.initialCells);
	}


	/**
	 * Reset viewing grid to original states
	 */
	private void resetGrid() {
		myAnimation.stop();
		generateSimulation();
		setCellsGrid();
		addCellsToDisplay();
		addGridConstraints();
		myCellWorld.startAnimation();
		myAnimation.pause();
	}

	private EventHandler<ActionEvent> myOneFrame = new EventHandler<ActionEvent>() {
		@Override

		/**
		 * Update routine for each frame of the simulation
		 */
		public void handle(ActionEvent evt) {
			checkFileSelectedAndSetFlags();
			if ((myLastClicked.equals(myStep))) {
				if (myStepClicked) {
					updateGrid();
					updateDisplay();
					myStepClicked = false;
					myAnimation.pause();
				}
			} else {
				updateGrid();
				updateDisplay();
			}
			checkSpeedSelection();
		}
	};


	/**
	 * Creates animation frame for simulation
	 * 
	 */
	public KeyFrame start() {
		return new KeyFrame(Duration.millis(1000), myOneFrame);
	}

	private void checkFileSelectedAndSetFlags() {

		if ((myFile != null) && (myFileSelected)) {
			myGridSet = true;
			myFileSelected = false;
		}
	}

	/**
	 * Update states of simulation cells 
	 */
	private void updateGrid() {
		if (myGridSet) 
			myCellSimulation.updateGrid();
	}

	/**
	 *
	 * Display updated states of simulation cells
	 * 
	 */
	private void updateDisplay() {
		if (myGridSet) {
			for (int i = 0; i < myCellsGrid.length; i++) {
				for (int j = 0; j < myCellsGrid[0].length; j++) {
					Cell cell = myCellsGrid[i][j];
					myViewingGrid[i][j].setStyle("-fx-background-color: "
							+ myColors[cell.getState()] + ";");
				}
			}
		}
	}

	/**
	 * Update animation speed based on selection 
	 */
	private void checkSpeedSelection() {
		ArrayList<String> possibleSpeeds = new ArrayList<String>();
		Collections.addAll(possibleSpeeds, VERY_SLOW, SLOW, NORMAL, FAST, VERY_FAST);
		for (int i = 0; i < possibleSpeeds.size(); i++) {
			if (speedSelected.getValue().equals(possibleSpeeds.get(i))){
				myAnimation.setRate(0.25*(i+1));
				return;
			}
		}
	}
}