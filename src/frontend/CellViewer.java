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

	private InitialGameParameters myGameParams;
	private XMLParsing myXMLParser;
	private CellWorld myCellWorld;
	private boolean myGridSet = false;
	private boolean myStepClicked = false;

	private boolean myFileSelected = false;
	private Button myLastClicked = null;

	// Stores the Viewing Cells displayed
	private GridPane[][] myViewingGrid;
	// Stores the Cell objects that are mapped 1:1 to a cell in myViewingGrid
	private Cell[][] myCellsGrid;

	// Stores File object .XML
	private File myFile;
	// Stores the overall Grid (consisting of individual cells)
	private GridPane myGridPane;
	// Border Pane holds the scene graph
	private BorderPane myBorderPane;

	private Button myReset;
	private Button myStart;
	private Button myStop;
	private Button myStep;

	private Simulation myCellSimulation;

	/**
	 * THIS CHANGES IF THE SIMULATION AND CORRESPONDING CELL CLASS MOVE
	 * TO A DIFFERENT PACKAGE
	 */
	private static final String SIMULATION_PACKAGE = "backend.simulations";
	private static final String CLASS_SUFFIX = "simulation";

	/**
	 * ADD NEW SIMULATION TO THE BELOW ARRAY FOR EXTENSIBILITY
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

	public CellViewer(Timeline animation, CellWorld cellWorld) {
		myAnimation = animation;
		myCellWorld = cellWorld;
	}

	private void setHeight(int height) {
		myHeight = height;
	}

	public Scene init(Stage stage, int width, int height) {
		setHeight(height);
		myBorderPane = new BorderPane();
		// myGridPane = new GridPane();
		myXMLParser = new XMLParsing();

		// Border Pane holds the scene graph
		Scene scene = new Scene(myBorderPane, width, height);
		addFileSelector(stage);
		addButtons();
		disableButtons(true);
		setButtonsOnAction();
		return scene;
	}

	private void generateSimulation() {
		for (int i = 0; i < mySimulations.length; i++) {
			if (mySimulations[i].getClass().getName().toLowerCase().equals(SIMULATION_PACKAGE + "." + myGameParams.simulationMode.toLowerCase() + CLASS_SUFFIX)){
				myCellSimulation = mySimulations[i];
				
				return;		
			}
		}
	}

	private void disableButtons(boolean disable) {
		myReset.setDisable(disable);
		myStart.setDisable(disable);
		myStop.setDisable(disable);
		myStep.setDisable(disable);
	}

	private void addCellsToDisplay() {
		disableButtons(false);
		myGridPane = new GridPane();

		myViewingGrid =  new GridPane[myGameParams.gridXSize][myGameParams.gridYSize];
		myBorderPane.setCenter(myGridPane);

		for (int row = 0; row < myGameParams.gridXSize; row++) {
			for (int col = 0; col < myGameParams.gridYSize; col++) {
				GridPane square = new GridPane();
				Cell cell = myCellsGrid[row][col];

				square.setStyle("-fx-background-color: " + myColors[cell.getState()] + ";");
				myGridPane.add(square, col, row);
				// Storing individual cells in this array (easy to access later to update)
				myViewingGrid[row][col] = square;
			}
		}
		// Creates border for each cell
		myGridPane.setStyle("-fx-background-color: black;-fx-hgap: 1; -fx-vgap: 1;");
	}

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
		// Adding padding so there are borders between perimeter cells and
		// window edges
		myGridPane.setPadding(new Insets(5, 5, 5, 5));
	}

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
				} else {
					fileNotSelected(stage);
				}
				resetGrid();

			}
		});
		hbox.getChildren().addAll(text, openButton);
		myBorderPane.setTop(hbox);

	}

	private void fileNotSelected(Stage stage) {
		// Create new stage for separate dialog
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


	private void parseXML() {
		try {
			myGameParams = myXMLParser
					.parseInitialCellsFromFile(myFile);
			myFileSelected = true;

		} catch (ParserConfigurationException | SAXException
				| IOException e1) {
			e1.printStackTrace();
		}
	} 

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
	
	private void addButtons() {

		speedSelected.getItems()
		.addAll(VERY_SLOW, SLOW, NORMAL, FAST, VERY_FAST);

		// By default set speedSelected to normal speed
		speedSelected.setValue(NORMAL);

		myReset = new Button("Reset");
		myStart = new Button("Start/Resume");
		myStop = new Button("Stop/Pause");
		myStep = new Button("Step");
		
		VBox vbox = createButtonsVBox();
		myBorderPane.setLeft(vbox);

	}

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

	private void setCellsGrid() {
		myCellsGrid = myCellSimulation.initialize(
				myGameParams.simulationMode,
				myGameParams.gridXSize, myGameParams.gridYSize,
				myGameParams.thresholdValue,
				myGameParams.initialCells);
	}

	private void resetGrid() {
		myAnimation.stop();
		generateSimulation();
		setCellsGrid();
		addCellsToDisplay();
		addGridConstraints();
		myCellWorld.startAnimation();
		myAnimation.pause();
	}
	
	
	private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent evt) {
			checkFileSelectedAndSetFlags();
			if ((myLastClicked.equals(myStep))) {
				if (myStepClicked) {
					updateGrid();
					updateDisplay();
					// set boolean flag to false so that step works only once
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

	public KeyFrame start() {
		return new KeyFrame(Duration.millis(1000), oneFrame);
	}

	private void checkFileSelectedAndSetFlags() {

		if ((myFile != null) && (myFileSelected)) {
			myGridSet = true;
			myFileSelected = false;
		}
	}

	private void updateGrid() {
		if (myGridSet) {
			myCellSimulation.updateGrid();
		}

	}

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