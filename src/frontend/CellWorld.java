package frontend;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * 
 * Entry point of Cell Society simulations
 *
 */
public class CellWorld extends Application {
	private CellViewer myViewer;
	private int ANIMATION_WIDTH;
	private int ANIMATION_HEIGHT;

	@Override
	public void start(Stage stage) {
		getScreenBounds();
		stage.setTitle("Cell Society");
		startAnimation();
		Scene view = myViewer.init(stage, ANIMATION_WIDTH, ANIMATION_HEIGHT);
		stage.setScene(view);
		stage.show();
	}
	
	public void startAnimation() {
		Timeline animation = new Timeline();
		myViewer = new CellViewer(animation, this);
		KeyFrame frame = myViewer.start();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
	}

	private void getScreenBounds() {
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds(); 
		ANIMATION_HEIGHT =(int) (screenBounds.getHeight()/1.1); 
		ANIMATION_WIDTH = (int) (screenBounds.getWidth()/1.7);
	}
	

	public static void main(String [] args) {
		launch(args);
	}
}