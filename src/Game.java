import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
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
	public static int NUM_LIVES = 3;
	public static final int SIZE = 600;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final Paint BACKGROUND = Color.BLACK;
	public static final String BOUNCER_IMG = "ball.gif";
	public static final String BLOCK_IMG = "brick1.gif";
	public static final String BLOCK2_IMG = "brick2.gif";
	public static final String LIFE_IMG = "laserpower.gif";

	// Block and paddle parameters
	public static int BOUNCER_SPEED = 30;
	public static double BOUNCER_RADIUS = 7.5;
	public static final Paint PADDLE_COLOR = Color.PLUM;
	public static final int PADDLE_WIDTH = 100;
	public static final int PADDLE_HEIGHT = 25;
	public static final int BLOCK_WIDTH = 70;
	public static final int BLOCK_HEIGHT = 20;
	public static final int MARGIN = 50;
	public static final int MOVER_SPEED = 150;

	public static int CURR_LEVEL = 1;

	public static final String BALL_POWERUP = "newBall";
	public static final String LIFE_POWERUP = "newLife";
	public static final String PADDLE_POWERUP = "biggerPaddle";
	public static final String BALL_POWERUP_IMG = "extraballpower.gif";
	public static final String LIFE_POWERUP_IMG = "laserpower.gif";
	public static final String PADDLE_POWERUP_IMG = "sizepower.gif";
	


	private Scene myScene;
	ArrayList<Bouncer> bouncers = new ArrayList<Bouncer>();
	public ArrayList<Life> lives = new ArrayList<Life>();
	protected Paddle myPaddle;
	Group root = new Group();
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

	/**
	 * Create the game's "scene": what shapes will be in the game and their starting properties
	 */
	private Scene Start(int width, int height, Paint background) {

		Scene scene = new Scene(root, width, height, background);

		// Initialize main bouncer
		bouncers.add(new Bouncer (MOVER_SPEED));
		bouncers.get(0).reset(width, height);
		root.getChildren().add(bouncers.get(0).DISPLAY);

		// Initialize toolbar (EVENTUALLY)
		int x = MARGIN;
		for(int i=0; i < NUM_LIVES; i++) {
			Life l = new Life(x, SIZE - MARGIN);
			x += 20;
			lives.add(l);
			root.getChildren().add(l.DISPLAY);
		}

		// Begin levels
		control();
		scene.setOnKeyPressed(e -> myPaddle.handleKeyInput(e.getCode()));

		return scene;
	}

	/**
	 * What do to in each increment of time 
	 */
	private void step (double elapsedTime) {
		// Check if bouncer in bounds
		for(Bouncer bouncer : bouncers){
			bouncer.bounce(elapsedTime, myPaddle);
			// Multiple bouncers, remove one
			if(bouncer.Y >= SIZE && bouncers.size() > 1){
				bouncers.remove(bouncer);
			}
			// Lose last bouncer, mult. lives
			else if(bouncer.Y >= SIZE && lives.size() > 1) {				
				NUM_LIVES--;
				bouncers.get(0).reset(SIZE, SIZE);
				root.getChildren().remove(lives.get(NUM_LIVES).DISPLAY);
				lives.remove(NUM_LIVES);
			}
			// Lose last bouncer, last life
			else if(bouncer.Y >= SIZE) {
				root.getChildren().clear();
				CURR_LEVEL++;
				control();
				bouncers.get(0).reset(SIZE, SIZE);
				root.getChildren().add(bouncers.get(0).DISPLAY);
			}
		}

		// Move power-ups down screen
		for(PowerUp powerUp : powerUps) {
			powerUp.move(elapsedTime);
			if(powerUp.intersect(myPaddle)) {
				root.getChildren().remove(powerUp.DISPLAY);
				createPowerUp(elapsedTime, powerUp);
			}
		}

		// Kill blocks as appropriate
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
					else if(blocks[i].powerUp.equals(LIFE_POWERUP)) {
						PowerUp p = new PowerUp(LIFE_POWERUP);
						powerUps.add(p);
						p.reset(blocks[i].X, blocks[i].Y);
						root.getChildren().add(p.DISPLAY);
					}
					else if(blocks[i].powerUp.equals(PADDLE_POWERUP)) {
						PowerUp p = new PowerUp(PADDLE_POWERUP);
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
					blocks[i] = new HitBlock(x, y, nh, BLOCK2_IMG, PADDLE_POWERUP);
					root.getChildren().add(blocks[i].DISPLAY);
					bouncer.bounceBlocks(elapsedTime);
				}
			}
		}
	}


	public int[][] control(){
		int[][] coords;
		if(CURR_LEVEL == 1) coords = Levels.Level1();
		else if(CURR_LEVEL == 2) coords = Levels.Level2();
		else coords = Levels.Level1();

		blocks = new HitBlock[coords.length];
		int numhits = 2;
		for(int i=0; i < coords.length; i++) {
			blocks[i] = new HitBlock(coords[i][0], coords[i][1], numhits, BLOCK_IMG, LIFE_POWERUP);
			root.getChildren().add(blocks[i].DISPLAY);
		}

		myPaddle = new Paddle(SIZE/2, SIZE - 100, PADDLE_WIDTH, PADDLE_HEIGHT);
		root.getChildren().add(myPaddle.DISPLAY);

		return coords;
	}
	
	public void createPowerUp(double elapsedTime, PowerUp p) {
		if(p.TYPE.equals(BALL_POWERUP)){
			Bouncer newB = new Bouncer(MOVER_SPEED);
			newB.reset(SIZE, SIZE);
			root.getChildren().add(newB.DISPLAY);
			bouncers.add(newB);
		}
		else if(p.TYPE.equals(LIFE_POWERUP)) {
			Life li = new Life(MARGIN + 20 * NUM_LIVES, SIZE - MARGIN);
			NUM_LIVES++;
			root.getChildren().add(li.DISPLAY);
			lives.add(li);
		}
		else if(p.TYPE.equals(PADDLE_POWERUP)) {
			root.getChildren().remove(myPaddle.DISPLAY);
			myPaddle = new Paddle(SIZE/2, SIZE - 100, 2*PADDLE_WIDTH, PADDLE_HEIGHT);
			root.getChildren().add(myPaddle.DISPLAY);
		}
	}

	/**
	 * Start the program.
	 */
	public static void main (String[] args) {
		launch(args);
	}
}

