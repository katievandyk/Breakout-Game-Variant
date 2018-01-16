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
	public static final int SIZE = 500;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND = Color.BLACK;
	public static final String BOUNCER_IMAGE = "ball.gif";
	public static final String BLOCK_IMAGE = "brick1.gif";
	public static final String BLOCK2_IMAGE = "brick2.gif";
	public static int BOUNCER_XSPEED = 30;
	public static int BOUNCER_YSPEED = 30;
	public static final Paint PADDLE_COLOR = Color.PLUM;
	public static final int PADDLE_WIDTH = 100;
	public static final int PADDLE_HEIGHT = 25;
	public static final int MOVER_SPEED = 150;

	private Scene myScene;
	private Bouncer myBouncer;
	private Rectangle myPaddle;
	Group root = new Group();
	Block[] blocks = new Block[20];

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
		// create one top level collection to organize the things in the scene

		// INITIALIZE LEVEL 1
		Scene scene = new Scene(root, width, height, background);
		myBouncer = new Bouncer(MOVER_SPEED);
		myBouncer.reset(width, height);
		myPaddle = new Rectangle(width / 2, height - 100, PADDLE_WIDTH, PADDLE_HEIGHT);
		myPaddle.setFill(PADDLE_COLOR);

		root.getChildren().add(myBouncer.DISPLAY);
		root.getChildren().add(myPaddle);
		int x = 50;
		int y = 20;
		int numhits = 1; 
			for(int i=0; i < blocks.length; i++) {
				blocks[i] = new Block(x, y, numhits, BLOCK_IMAGE);
				root.getChildren().add(blocks[i].DISPLAY);
				x += 75;
				if(x >= (SIZE - 125)) {
					x = 50;
					y += 50;
					numhits = 2;
				}
			}
	


	// RESPOND TO INPUT 
	scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
	return scene;
}

private void step (double elapsedTime) {
	myBouncer.bounce(elapsedTime, myPaddle); // Bounce if hit 3 borders

	int x = 50;
	int y = 20;
	for(int i=0; i < blocks.length; i++) {
		if(myBouncer.intersect(blocks[i]) && blocks[i].numhits == 1) {
			root.getChildren().remove(blocks[i].DISPLAY);
		}
		else if(myBouncer.intersect(blocks[i])) {			
			int numHits = blocks[i].numhits - 1;
			int x1 = blocks[i].X;
			int y1 = blocks[i].Y;
			root.getChildren().remove(blocks[i].DISPLAY);
			blocks[i] = new Block(x1,y1,numHits, BLOCK2_IMAGE);
			root.getChildren().add(blocks[i].DISPLAY);
			myBouncer.bounceBlocks(elapsedTime);
		}
	}
}



// What to do each time a key is pressed
private void handleKeyInput (KeyCode code) {
	if (code == KeyCode.RIGHT) {
		myPaddle.setX(myPaddle.getX() + MOVER_SPEED/2);
	}
	else if (code == KeyCode.LEFT) {
		myPaddle.setX(myPaddle.getX() - MOVER_SPEED/2);
	}
}

/**
 * Start the program.
 */
public static void main (String[] args) {
	launch(args);
}
}

