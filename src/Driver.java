import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;

public class Driver extends Application {

	public final String TITLE = "Breakout";
	public static final int SIZE = 600;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND = Color.BLACK;
	public static final String BOUNCER_IMG = "ball.gif";
	public static final String BOUNCEBLOCK_IMG = "brick3.gif";
	public static final String BLOCK_IMG = "brick1.gif";
	public static final String BLOCK2_IMG = "brick2.gif";
	public static final String LIFE_IMG = "laserpower.gif";
	public static final int BOUNCER_SPEED = 30;
	public static final double BOUNCER_RADIUS = 7.5;
	public static final Paint PADDLE_COLOR = Color.PLUM;
	public static final int PADDLE_WIDTH = 100;
	public static final int PADDLE_HEIGHT = 25;
	public static final double BLOCK_WIDTH = 70;
	public static final double BLOCK_HEIGHT = 20;
	public static final double BOUNCE_BLOCK = 20;
	public static final int MARGIN = 50;
	public static final int MOVER_SPEED = 150;
	public static final String BALL_POWERUP = "newBall";
	public static final String LIFE_POWERUP = "newLife";
	public static final String PADDLE_POWERUP = "biggerPaddle";
	public static final String SNOWBALL = "snowBall";
	public static final String BALL_POWERUP_IMG = "extraballpower.gif";
	public static final String LIFE_POWERUP_IMG = "laserpower.gif";
	public static final String SNOWBALL_IMG = "snowball.gif";
	public static final String PADDLE_POWERUP_IMG = "sizepower.gif";
	public static final String BACKGROUND_IMG = "mountain.gif";

	private Paddle myPaddle;
	private SceneCtrl sceneController;
	private LevelCtrl levelController;
	
	public Group root = new Group();

	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	@Override
	public void start (Stage stage) {
		int curr_level = 0;
		
        
        // attach "game loop" to timeline to play it
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();

		sceneController = new SceneCtrl(stage);
		levelController = new LevelCtrl(curr_level);

	//	myScene.setOnKeyPressed(f -> checkKey(f.getCode()));
		sceneController.setScene();
		sceneController.createStartScreen();
		
	}

	/**
	 * What do to in each increment of time 
	 */
	public void step (double elapsedTime) {
		sceneController.checkKeys();

		sceneController.move(elapsedTime);
		sceneController.killBlocks(elapsedTime);


		int level = sceneController.getCurrLevel();
		if(sceneController.Win() && level == 1 || level ==2) {
			System.out.println(level);
			sceneController.updateLevelScreen();
		}
		else if(sceneController.Win() && level == 3) sceneController.createWinScreen();

	}


	/**
	 * Convert string to image
	 */
	public Image Image(String img) {
		Image myImage = new Image(getClass().getClassLoader().getResourceAsStream(img));
		return myImage;
	}

	/**
	 * Update level
	 */


	
	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}


}

