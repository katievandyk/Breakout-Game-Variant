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
	public static final int MOVER_SPEED = 150;


	public static final String BALL_POWERUP = "newBall";
	public static final String BALL_POWERUP_IMG = "extraballpower.gif";


	private Scene myScene;
	ArrayList<Bouncer> bouncers = new ArrayList<Bouncer>();
	private Paddle myPaddle;
	static Group root = new Group();
	HitBlock[] blocks;
	ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();

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
		bouncers.add(new Bouncer (MOVER_SPEED));
		myPaddle = new Paddle(width/2, height - 100, PADDLE_WIDTH, PADDLE_HEIGHT);

		for(Bouncer bouncer : bouncers) {
			root.getChildren().add(bouncer.DISPLAY);
		}

		root.getChildren().add(myPaddle.DISPLAY);

		int coords[][]; 
		for(Bouncer bouncer : bouncers)
		{
			bouncer.reset(width, height);
		}


		//coords = Levels.Level1();
		coords = Levels.Level1();
		blocks = new HitBlock[coords.length];
		int numhits = 2;

		for(int i=0; i < coords.length; i++) {
			blocks[i] = new HitBlock(coords[i][0], coords[i][1], numhits, BLOCK_IMAGE, BALL_POWERUP);
			root.getChildren().add(blocks[i].DISPLAY);
		}

		scene.setOnKeyPressed(e -> myPaddle.handleKeyInput(e.getCode()));
		return scene;
	}

	private void step (double elapsedTime) {
		for(Bouncer bouncer : bouncers){
			bouncer.bounce(elapsedTime, myPaddle);
			if(bouncer.Y >= SIZE && bouncers.size() > 1){
				bouncers.remove(bouncer);
			}
			else if(bouncer.Y >= SIZE) {
				System.out.println("LOST");
			}
		}

		//MOVE POWER-UPS DOWN THE SCREEN
		for(int i=0; i < powerUps.size(); i++) {
			powerUps.get(i).move(elapsedTime);
			if(powerUps.get(i).intersect(myPaddle)) {
				if(powerUps.get(i).TYPE.equals(BALL_POWERUP)){
					Bouncer newB = new Bouncer(MOVER_SPEED);
					newB.reset(SIZE, SIZE);
					root.getChildren().add(newB.DISPLAY);
					bouncers.add(newB);
				}

			}
		}

		for(int i=0; i < blocks.length; i++) {
			killBlocks(elapsedTime);
		}

	}

	private void killBlocks(double elapsedTime) {
		for(int i=0; i < blocks.length; i++) {
			for(Bouncer bouncer : bouncers) {
				if(blocks[i].intersect(bouncer) && blocks[i].numhits == 1) {
					root.getChildren().remove(blocks[i].DISPLAY);
					if(blocks[i].powerUp.equals(BALL_POWERUP)) {
						PowerUp p = new PowerUp(BALL_POWERUP);
						powerUps.add(p);
						p.reset(blocks[i].X, blocks[i].Y);
						root.getChildren().add(p.DISPLAY);

					}
					bouncer.bounceBlocks(elapsedTime);
				}
				else if(blocks[i].intersect(bouncer)) {	
					int nh = blocks[i].numhits -1;
					int x = blocks[i].X;
					int y = blocks[i].Y;
					root.getChildren().remove(blocks[i].DISPLAY);
					blocks[i] = new HitBlock(x, y, nh, BLOCK2_IMAGE, BALL_POWERUP);
					root.getChildren().add(blocks[i].DISPLAY);
					bouncer.bounceBlocks(elapsedTime);
				}
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

