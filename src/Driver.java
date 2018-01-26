import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Driver extends Application {

	public static final String TITLE = "Breakout";
	public static final int SIZE = 600;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND = Color.BLACK;
	public static final String BOUNCER_IMG = "ball.gif";
	public static final String BLOCK_IMG = "brick1.gif";
	public static final String BLOCK2_IMG = "brick2.gif";
	public static final String LIFE_IMG = "laserpower.gif";
	public static final int BOUNCER_SPEED = 30;
	public static final double BOUNCER_RADIUS = 7.5;
	public static final int PADDLE_WIDTH = 100;
	public static final int PADDLE_HEIGHT = 25;
	public static final double BLOCK_WIDTH = 70;
	public static final double BLOCK_HEIGHT = 20;
	public static final double BOUNCE_BLOCK = 20;
	public static final int X_MARGIN = 50;
	public static final int Y_MARGIN = 30;
	public static final int MOVER_SPEED = 150;
	public static final String BALL_POWERUP = "newBall";
	public static final String LIFE_POWERUP = "newLife";
	public static final String PADDLE_POWERUP = "biggerPaddle";
	public static final String SNOWBALL = "snowBall"; 
	public static final String BALL_POWERUP_IMG = "extraballpower.gif";
	public static final String LIFE_POWERUP_IMG = "laserpower.gif";
	public static final String SNOWBALL_IMG = "snowball.gif"; //source: http://clubpenguin.wikia.com/wiki/File:Puffle_Hotel_Spa_snowball_cover.gif
	public static final String PADDLE_POWERUP_IMG = "sizepower.gif";
	public static final String BACKGROUND_IMG = "mountain.gif"; //source: https://plus.google.com/103802650377226624210
	private SceneCtrl sceneController;
	
	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	@Override
	public void start (Stage stage) {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();

		sceneController = new SceneCtrl(stage);
		sceneController.setScene();
	}

	/**
	 * What do to in each increment of time 
	 */
	public void step (double elapsedTime) {
		sceneController.checkKeys();
		if(sceneController.getLevel() > 0) {
			sceneController.move(elapsedTime);
			sceneController.killBlocks(elapsedTime);
			sceneController.handleWin();	
		}
	}

	/**
	 * Convert string to image
	 */
	public Image Image(String img) {
		Image myImage = new Image(getClass().getClassLoader().getResourceAsStream(img));
		return myImage;
	}
	
	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}

}

