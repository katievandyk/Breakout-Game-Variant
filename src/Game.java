import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Game extends Application {

	public static final String TITLE = "Breakout";
	public static final int SIZE = 600;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND = Color.BLACK;
	public static final String BOUNCER_IMAGE = "ball.gif";
	public static final String BLOCK_IMAGE = "brick1.gif";
	public static final String BLOCK2_IMAGE = "brick2.gif";
	
	public static int BOUNCER_SPEED = 30;
	public static double BOUNCER_RADIUS = 7.5;
	public static final Paint PADDLE_COLOR = Color.PLUM;
	public static final int PADDLE_WIDTH = 100;
	public static final int PADDLE_HEIGHT = 25;
	public static final int BLOCK_WIDTH = 70;
	public static final int BLOCK_HEIGHT = 20;
	public static final int BLOCK_MARGIN = 50;
	public static final int MOVER_SPEED = 130;
	

	private Scene myScene;
	public Bouncer myBouncer;
	private Paddle myPaddle;
	Group root = new Group();
	HitBlock[] blocks;

	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	@Override
	public void start (Stage stage) {
		// attach scene to the stage and display it
		myScene = Start(SIZE, SIZE, BACKGROUND);
		stage.setScene(myScene);
		stage.setTitle(TITLE);
		stage.show();
		// attach "game loop" to timeline to play it
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	// Create the game's "scene": what shapes will be in the game and their starting properties
	private Scene Start(int width, int height, Paint background) {

		Scene scene = new Scene(root, width, height, background);
		myBouncer = new Bouncer(MOVER_SPEED);
		myPaddle = new Paddle(width/2, height - 100, PADDLE_WIDTH, PADDLE_HEIGHT);

		root.getChildren().add(myBouncer.DISPLAY);
		root.getChildren().add(myPaddle.DISPLAY);

		int coords[][]; 
		myBouncer.reset(width, height);
		//coords = Levels.Level1();
		coords = Levels.Level1();
		blocks = new HitBlock[coords.length];
		int numhits = 2;

		for(int i=0; i < coords.length; i++) {
			blocks[i] = new HitBlock(coords[i][0], coords[i][1], numhits, BLOCK_IMAGE);
			root.getChildren().add(blocks[i].DISPLAY);
		}

		scene.setOnKeyPressed(e -> myPaddle.handleKeyInput(e.getCode()));
		return scene;
	}

	private void step (double elapsedTime) {
		myBouncer.bounce(elapsedTime, myPaddle); 
		for(int i=0; i < blocks.length; i++) {
			killBlocks(elapsedTime);
		}
		
	}
	
	private void killBlocks(double elapsedTime) {
		for(int i=0; i < blocks.length; i++) {
			if(blocks[i].intersect(myBouncer) && blocks[i].numhits == 1) {
				root.getChildren().remove(blocks[i].DISPLAY);
				myBouncer.bounceBlocks(elapsedTime);
			}
			else if(blocks[i].intersect(myBouncer)) {	
				int nh = blocks[i].numhits -1;
				int x = blocks[i].X;
				int y = blocks[i].Y;
				root.getChildren().remove(blocks[i].DISPLAY);
				blocks[i] = new HitBlock(x, y, nh, BLOCK2_IMAGE);
				root.getChildren().add(blocks[i].DISPLAY);
				myBouncer.bounceBlocks(elapsedTime);
			}
		}
	}


	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}
}

