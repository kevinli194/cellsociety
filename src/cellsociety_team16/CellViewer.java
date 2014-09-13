package cellsociety_team16;

import java.io.File;

import javafx.application.Application;
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

public class CellViewer extends Application {
	File selectedFile;
	private final FileChooser fileChooser = new FileChooser();
	private final Button openButton = new Button("...");
	final ComboBox<String> speedOptions = new ComboBox<String>();


	private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
				return node;
			}
		}
		return null;
	}
	@Override		
	public void start(Stage stage) {

		BorderPane border = new BorderPane();
		HBox hbox = addHBox();
		border.setTop(hbox);


		stage.setTitle("Cell Society");
		GridPane panel = new GridPane();

		panel.setPadding(new Insets(0, 10, 10, 0));
		VBox vbox = addVBox();
		border.setLeft(vbox);

		border.setCenter(panel);


		final int size = 25 ;
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < (size + 4); col ++) {
				GridPane square = new GridPane();
				String color ;
				if ((row + col) % 2 == 0) {
					color = "green";
				} else {
					color = "blue";
				}
				square.setStyle("-fx-background-color: "+ color +";");
				panel.add(square, col, row);
			}
		}

		openButton.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(final ActionEvent e) {

						fileChooser.setTitle("Open XML File");
						fileChooser.getExtensionFilters().addAll(
								new ExtensionFilter("XML Files", "*.xml"));
						selectedFile = fileChooser.showOpenDialog(stage);
						if (selectedFile == null) {
							System.out.println("No file selected");
							final Stage dialog = new Stage();
							dialog.initModality(Modality.APPLICATION_MODAL);
							dialog.initOwner(stage);
							VBox dialogVbox = new VBox();
							dialogVbox.getChildren().add(new Text("You haven't selected an XML file.\nPlease select one."));
							Scene dialogScene = new Scene(dialogVbox, 500, 100);
							dialog.setScene(dialogScene);
							dialog.show();

						}
					}
				});


		Node requestedNode = getNodeFromGridPane(panel, 4, 4);
		

		if (requestedNode != null) {
			requestedNode.setStyle("-fx-background-color: yellow");
		}
		//panel.getColumnConstraints().add(new ColumnConstraints(15, Control.USE_COMPUTED_SIZE, 1000, Priority.ALWAYS, HPos.CENTER, true));
		//panel.getRowConstraints().add(new RowConstraints(15, Control.USE_COMPUTED_SIZE, 1000, Priority.ALWAYS, VPos.CENTER, true));
		for (int i = 0; i < (size+4); i++) {
			panel.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
			
		}
		
		for (int i = 0; i < size; i++) {
		panel.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
		} 


		stage.setScene(new Scene(border, 1750, 1750));
		stage.show();
	}



	public HBox addHBox() {
		HBox hbox = new HBox();
		Text text = new Text("Load an XML file to begin the simulation:  ");
		openButton.setScaleX(0.8);
		openButton.setScaleY(0.8);
		hbox.getChildren().addAll(text, openButton);

		return hbox;
	}

	public VBox addVBox() {
		VBox vbox = new VBox();
		vbox.setSpacing(30);
		vbox.setPadding(new Insets(500, 0, 0, 2));

		Button options[] = new Button[] {
				new Button("Start"),
				new Button("Reset"),
				new Button("Stop/Pause"),
				new Button ("Step")};


		Text speed = new Text("Speed");
		speedOptions.getItems().addAll(
				"Very Slow", "Slow", "Medium", "Fast", "Very Fast");


		for (int i=0; i<4; i++) {
			//VBox.setMargin(options[i], new Insets(0, 0, 0, 2));
			vbox.getChildren().add(options[i]);
		}
		vbox.getChildren().add(speed);
		vbox.getChildren().add(speedOptions);

		return vbox;
	}

}
