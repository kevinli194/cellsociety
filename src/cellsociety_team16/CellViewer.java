package cellsociety_team16;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import parent.Cell;
import parent.CellManager;
import parent.SegCellManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
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
	private Button myReset =  new Button("Reset");
	private Button myStart= new Button("Start/Resume");
	private Button myStop = new Button("Stop/Pause");
	private Button myStep = new Button ("Step");
	private Button myLastClicked = null;

	private Cell[][] myGrid;

	// Stores File object .XML
	private File myFile;
	// Stores the overall Grid (consisting of individual cells) 
	private GridPane myGridPane;
	//Border Pane holds the scene graph
	private BorderPane myBorderPane;

	private CellManager myCellManager;
	private Timeline myAnimation = new Timeline();
	private static final String [] POSSIBLE_COLORS = {"white", "red", "blue"};
	private final FileChooser fileChooser = new FileChooser();
	private final Button openButton = new Button("...");
	private final ComboBox<String> speedOptions = new ComboBox<String>();
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
		//myGridPane = new GridPane();
		myXMLParser = new XMLParsing();
		
		/*
		 * ***HERE IS WHAT NEEDS TO BE CHANGED****
		 */
		myCellManager = new SegCellManager();

		//Border Pane holds the scene graph		
		Scene scene = new Scene(myBorderPane, width, height);
		addFileSelector(stage);
		addButtons();
		disableButtons(true);
		setButtonsOnAction();
		return scene;
	}


	private void disableButtons(boolean disable) {
		myReset.setDisable(disable);
		myStart.setDisable(disable);
		myStop.setDisable(disable); 
		myStep.setDisable(disable);
	}


	private void addIndividualCells() {
		disableButtons(false);
		myGridPane = new GridPane();
		myBorderPane.setCenter(myGridPane);

		for (int row = 0; row < myGameParams.gridXSize ; row++) {
			for (int col = 0; col < myGameParams.gridYSize; col ++) {
				GridPane square = new GridPane();
				Cell cell = myGrid[row][col];

				square.setStyle("-fx-background-color: "+ POSSIBLE_COLORS[cell.getState()] +";");
				myGridPane.add(square, col, row);
			}
		}	
		// Creates border for each cell
		myGridPane.setStyle("-fx-background-color: black;-fx-hgap: 1; -fx-vgap: 1;");
	}
	
	private void addGridConstraints() {

		for (int i = 0; i < myGameParams.gridXSize; i++) {
			myGridPane.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
		}

		for (int i = 0; i < myGameParams.gridYSize; i++) {
			myGridPane.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));

		}
		// Adding padding so there are borders between perimeter cells and window edges
		myGridPane.setPadding(new Insets(5, 5, 5, 5));
	}



	private void addFileSelector(Stage stage) {
		HBox hbox = new HBox();
		Text text = new Text("Load an XML file to begin the simulation:  ");
		openButton.setScaleX(0.8);
		openButton.setScaleY(0.8);
		openButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {

						fileChooser.setTitle("Open XML File");
						fileChooser.getExtensionFilters().addAll(
								new ExtensionFilter("XML Files", "*.xml"));
						myFile = fileChooser.showOpenDialog(stage);
						if (myFile == null) {
							System.out.println("No file selected");
							Stage dialog = new Stage();
							dialog.initModality(Modality.APPLICATION_MODAL);
							dialog.initOwner(stage);
							VBox textBox = new VBox();
							textBox.getChildren().add(new Text("You haven't selected an XML file.\nPlease select one."));
							Scene dialogScene = new Scene(textBox, 500, 100);
							dialog.setScene(dialogScene);
							dialog.show();

						} else {
							try {
								myGameParams = myXMLParser.parseInitialCellsFromFile(myFile);
								myFileSelected = true;

							} catch (ParserConfigurationException
									| SAXException | IOException e1) {
								e1.printStackTrace();
							}
							myAnimation.stop();
							myGrid = myCellManager.initialize(myGameParams.simulationMode, myGameParams.gridXSize,
									myGameParams.gridYSize, myGameParams.initialCells);
							addIndividualCells();
							addGridConstraints();
							myCellWorld.startAnimation();


						}
					}
				});
		hbox.getChildren().addAll(text, openButton);
		myBorderPane.setTop(hbox);

	}


	private Node getNodeFromGridPane(int row, int col) {
		for (Node node : myGridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}


	private void addButtons() {
		VBox vbox = new VBox();
		vbox.setSpacing(myHeight/40);
		vbox.setPadding(new Insets(myHeight/4, 0, 0, 2));

		Text speed = new Text("Speed");

		speedOptions.getItems().addAll(
				VERY_SLOW, SLOW, NORMAL, FAST, VERY_FAST);

		// By default set this to normal speed
		speedOptions.setValue(NORMAL);


		// Adding buttons to vertical box. This could have been cleaner with an array of Buttons but from
		// a readability standpoint, this is probably better
		vbox.getChildren().add(myReset);
		vbox.getChildren().add(myStart);
		vbox.getChildren().add(myStop);
		vbox.getChildren().add(myStep);


		vbox.getChildren().add(speed);
		vbox.getChildren().add(speedOptions);

		myBorderPane.setLeft(vbox);

	}

	private void setButtonsOnAction() {
		myStart.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						myAnimation.play();
						myLastClicked = myStart;

					}

				});

		myStop.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						myAnimation.pause();
						myLastClicked = myStop;
					}

				});

		myReset.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						myAnimation.stop();
						myGrid = myCellManager.initialize(myGameParams.simulationMode, myGameParams.gridXSize,
								myGameParams.gridYSize, myGameParams.initialCells);
						addIndividualCells();
						addGridConstraints();
						myCellWorld.startAnimation();
						myAnimation.pause();
					}

				});

		myStep.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {
						myAnimation.play();
						myStepClicked = true;
						myLastClicked = myStep;

					}

				});	
	}

	private EventHandler<ActionEvent> oneFrame = new EventHandler<ActionEvent>() {
		@Override
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

	public KeyFrame start () {
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
			myCellManager.updateGrid();
		}		

	}


	// Currently updating display by picking a random color;
	private void updateDisplay () {
		if (myGridSet) {
			for (int i = 0; i < myGrid.length; i++) {
				for (int j = 0; j < myGrid[0].length; j++) {
					Node node = getNodeFromGridPane(i, j);
					Cell cell = myGrid[i][j];
					node.setStyle("-fx-background-color: "+ POSSIBLE_COLORS[cell.getState()] +";");
				}
			}
		}
	}		

	private void checkSpeedSelection() {
		if (speedOptions.getValue().equals(VERY_SLOW)) {
			myAnimation.setRate(0.25);
		} else {
			if ((speedOptions.getValue().equals(SLOW))) {
				myAnimation.setRate(0.5);
			} else { 
				if ((speedOptions.getValue().equals(NORMAL))) {
					myAnimation.setRate(1.0);

				} else {
					if ((speedOptions.getValue().equals(FAST))) {
						myAnimation.setRate(2.0);
					} else {
						myAnimation.setRate(4.0);
					}
				}
			}
		}
	}
}