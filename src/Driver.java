import java.util.ArrayList;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.text.Text;

public class Driver extends Application {

	public static final String TITLE = "Breakout";
	public static int NUM_LIVES = 3;
	public static int NUM_POINTS = 0;
	public static Text NUM_POINTS_TXT;
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
	public static int BOUNCER_SPEED = 30;
	public static double BOUNCER_RADIUS = 7.5;
	public static final Paint PADDLE_COLOR = Color.PLUM;
	public static final int PADDLE_WIDTH = 100;
	public static final int PADDLE_HEIGHT = 25;
	public static final double BLOCK_WIDTH = 70;
	public static final double BLOCK_HEIGHT = 20;
	public static final double BOUNCE_BLOCK = 20;
	public static final int MARGIN = 50;
	public static final int MOVER_SPEED = 150;
	public static int CURR_LEVEL = 0;
	public static Text CURR_LEVEL_TXT;
	public static final String BALL_POWERUP = "newBall";
	public static final String LIFE_POWERUP = "newLife";
	public static final String PADDLE_POWERUP = "biggerPaddle";
	public static final String BALL_POWERUP_IMG = "extraballpower.gif";
	public static final String LIFE_POWERUP_IMG = "laserpower.gif";
	public static final String PADDLE_POWERUP_IMG = "sizepower.gif";
	public static final String BACKGROUND_IMG = "mountain.gif";

	public static Scene myScene;
	static Timeline animation = new Timeline();
	static ArrayList<Bouncer> bouncers = new ArrayList<Bouncer>();
	static ArrayList<Life> lives = new ArrayList<Life>();
	protected static Paddle myPaddle;
	static Group root = new Group();
	static ArrayList<HitBlock> hit_blocks = new ArrayList<HitBlock>();
	static ArrayList<BounceBlock> bounce_blocks = new ArrayList<BounceBlock>();
	static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	static double[][] hit_coords = null;
	static double[][] bounce_coords = null;

	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	@Override
	public void start (Stage stage) {
		SceneCtrl.createScene(stage);	
		SceneCtrl.setBackgroundImage(Image(BACKGROUND_IMG));
		SceneCtrl.playAnimation();
		SceneCtrl.createStartScreen();
	}

	/**
	 * What do to in each increment of time 
	 */
	public static void step (double elapsedTime) {
		// Move bouncer, determine intersections
		for(Bouncer bouncer : bouncers){
			bouncer.bounce(elapsedTime, myPaddle);
			bouncer.checkStatus();
		}
		// Move power-ups down screen
		for(PowerUp powerUp : powerUps) {
			powerUp.move(elapsedTime);
			powerUp.checkStatus(elapsedTime);
		}
		// Kill blocks
		HitBlock.killBlocks(elapsedTime);
		BounceBlock.killBlocks(elapsedTime);
		if(Win() && hit_blocks.size() > 0) {
			CURR_LEVEL++;
			SceneCtrl.updateLevelText();
			updateLevel();
		}
	}


	/**
	 * Check for win
	 */
	public static boolean Win() {
		for(HitBlock block : hit_blocks) {
			if(block.VALID == true) return false;
		}
		return true;
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
	public static void updateLevel() {
		LevelCtrl.changeLevel();
		LevelCtrl.clearLevel();
		LevelCtrl.makeBlocks();
	}

	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}

}

