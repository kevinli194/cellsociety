package cellsociety_team16;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CellWorld extends Application {
	private CellViewer myViewer;
	private static final int SCREEN_WIDTH = 1500;
	private static final int SCREEN_HEIGHT = 1500;

	@Override
	public void start(Stage stage) {
		Timeline animation = new Timeline();
		stage.setTitle("Cell Society");
		myViewer = new CellViewer(animation);
		Scene view = myViewer.init(stage, SCREEN_WIDTH, SCREEN_HEIGHT);
		stage.setScene(view);
		stage.show();

		KeyFrame frame = myViewer.start();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		
	}

	public static void main(String [] args) {
		launch(args);
	}
}