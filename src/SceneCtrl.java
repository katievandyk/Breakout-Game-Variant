import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SceneCtrl extends Driver{

	private ArrayList<Bouncer> bouncers = new ArrayList<Bouncer>();
	private ArrayList<Life> lives = new ArrayList<Life>();
	private ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	private ArrayList<BounceBlock> bounce_blocks = new ArrayList<BounceBlock>();
	private ArrayList<HitBlock> hit_blocks = new ArrayList<HitBlock>();
	private Paddle myPaddle;

	private String INSTRUCTIONS = "WELCOME TO BREAKOUT!";
	private String LOSE = "YOU LOST:(";
	private String WIN = "YOU WON!!:)";
	private String LIVES = "LIVES:";
	private String POINTS = "POINTS:";
	private String LEVEL = "LEVEL:";
	private Text CURR_LEVEL_TXT;
	private Text NUM_POINTS_TXT;
	private int NUM_POINTS;
	private int CURR_LEVEL;
	private Scene myScene;
	private Stage myStage;
	private Group root = new Group();
	private LevelCtrl levelcontroller;

	public SceneCtrl(Stage stage) {
		this.myScene = null;
		this.myStage = stage;
		this.CURR_LEVEL = 0;
		this.NUM_POINTS = 0;
	}

	public void setScene() {
		this.myScene = new Scene(root, SIZE, SIZE);
		ImagePattern pattern = new ImagePattern(Image(BACKGROUND_IMG));
		myScene.setFill(pattern); 
		this.myStage.setScene(myScene);
		this.myStage.setTitle(TITLE);
		this.myStage.show();

		levelcontroller = new LevelCtrl(CURR_LEVEL);
	}

	public Scene getScene() {
		return this.myScene;
	}


	public void checkKeys() {
		myScene.setOnKeyPressed(f -> checkKey(f.getCode()));
	}


	public void checkKey(KeyCode code) {
		if (code == KeyCode.RIGHT) {
			myPaddle.DISPLAY.setX(myPaddle.DISPLAY.getX() + MOVER_SPEED/4);
		}
		else if(code == KeyCode.ENTER) {
			CURR_LEVEL++;
			levelcontroller.changeLevel(CURR_LEVEL);
			clearDisplay();
			createToolbar();
			makeBlocks(levelcontroller.getHitCoords(), levelcontroller.getBounceCoords());
			addBouncer();
			myPaddle = new Paddle(SIZE/2, SIZE - 100, PADDLE_WIDTH, PADDLE_HEIGHT);
			addDisplay(myPaddle.DISPLAY);


		}
		else if (code == KeyCode.LEFT) {
			myPaddle.DISPLAY.setX(myPaddle.DISPLAY.getX() - MOVER_SPEED/4);
		}
	}

	public void removeDisplay(ImageView image) {
		root.getChildren().remove(image);
	}
	
	public int getCurrLevel() {
		return CURR_LEVEL;
	}

	public void removeDisplay(Text image) {
		root.getChildren().remove(image);
	}

	public void removeDisplay(Rectangle image) {
		root.getChildren().remove(image);
	}

	public void addDisplay(ImageView image) {
		root.getChildren().add(image);
	}

	public void addDisplay(Text t) {
		root.getChildren().add(t);
	}

	public void addDisplay(Rectangle image) {
		root.getChildren().add(image);

	}

	public void clearDisplay() {
		root.getChildren().clear();
	}


	public void createStartScreen() {
		Text t = new Text(MARGIN, MARGIN, INSTRUCTIONS);
		t.setFill(Color.BLACK);
		t.setFont(Font.font(java.awt.Font.SERIF, 25));
		root.getChildren().add(t);
	}

	public void createLoseScreen() {
		clearDisplay();
		Text t = new Text(MARGIN, MARGIN, LOSE);
		t.setFill(Color.BLACK);
		t.setFont(Font.font(java.awt.Font.SERIF, 25));
		addDisplay(t);
	}

	public void createWinScreen() {
		clearDisplay();
		Text t = new Text(MARGIN, MARGIN, WIN);
		t.setFill(Color.BLACK);
		t.setFont(Font.font(java.awt.Font.SERIF, 25));
		addDisplay(t);
	}

	public void updateLevelScreen() {
		CURR_LEVEL++;
		levelcontroller.changeLevel(CURR_LEVEL);
		double[][] hit_coords = levelcontroller.getHitCoords();
		double[][] bounce_coords = levelcontroller.getBounceCoords();
		clearBlocks();
		makeBlocks(hit_coords, bounce_coords);

	}

	private void makeBlocks(double[][] hit_coords, double[][] bounce_coords) {
		String img;
		int numhits;
		for(int i=0; i < hit_coords.length; i++) {
			if(i % 2 == 0) {
				numhits = 1;
				img = BLOCK2_IMG;
			}
			else {
				numhits = 1;
				img = BLOCK_IMG;
			} 
			hit_blocks.add(new HitBlock(hit_coords[i][0], hit_coords[i][1], numhits, img, PADDLE_POWERUP));
			addDisplay(hit_blocks.get(i).DISPLAY);
		}

		if(bounce_coords != null) {
			for(int i=0; i < bounce_coords.length; i++) {
				bounce_blocks.add(new BounceBlock(bounce_coords[i][0], bounce_coords[i][1], BOUNCEBLOCK_IMG));
				addDisplay(bounce_blocks.get(i).DISPLAY);
			}
		}
	}


	public void clearBlocks() {
		for(HitBlock block : hit_blocks) {
			removeDisplay(block.DISPLAY);
		}
		for(BounceBlock block : bounce_blocks) {
			removeDisplay(block.DISPLAY);
		}

		hit_blocks.clear();
		bounce_blocks.clear();
	}

	public void createToolbar() {
		int x = MARGIN/2;
		int y = SIZE - 20;
		//Create bar
		Rectangle bar = new Rectangle(0, SIZE - MARGIN, SIZE, MARGIN);
		addDisplay(bar);
		addDisplay(makeText(LIVES, x, y));

		x = initializeLives(x);
		x += MARGIN/2;
		addDisplay(makeText(POINTS, x, SIZE - 20));
		x += MARGIN*3/2;
		NUM_POINTS_TXT = makeText(Integer.toString(this.NUM_POINTS), x, SIZE - 20);
		addDisplay(NUM_POINTS_TXT);
		x += MARGIN;
		addDisplay(makeText(LEVEL, x, SIZE - 20));	
		x += MARGIN*3/2;
		CURR_LEVEL_TXT = makeText(Integer.toString(this.CURR_LEVEL), x, SIZE - 20);
		addDisplay(CURR_LEVEL_TXT);
	}

	public void updatePointsText() {
		removeDisplay(NUM_POINTS_TXT);
		NUM_POINTS_TXT.setText(Integer.toString(this.CURR_LEVEL));
		addDisplay(NUM_POINTS_TXT);
	}

	public void updateLevelText() {
		removeDisplay(CURR_LEVEL_TXT);
		CURR_LEVEL_TXT.setText(Integer.toString(this.CURR_LEVEL));
		addDisplay(CURR_LEVEL_TXT);
	}


	public void addBouncer() {
		// Initialize main bouncer
		bouncers.add(new Bouncer(MOVER_SPEED)); 
		bouncers.get(0).reset(SIZE, SIZE);
		addDisplay(bouncers.get(0).DISPLAY);
	}

	public void clearLevel() {
		if(this.CURR_LEVEL > 1) {
			for(Bouncer bouncer : bouncers) {
				removeDisplay(bouncer.DISPLAY);
			}
			bouncers.clear();
			Bouncer b = new Bouncer(MOVER_SPEED);
			b.reset(SIZE, SIZE);
			bouncers.add(b);
			addDisplay(b.DISPLAY);
		}

		hit_blocks.clear();
		bounce_blocks.clear();
		for(PowerUp p : powerUps) {
			removeDisplay(p.DISPLAY);
		}
		powerUps.clear();
	}


	public void createPowerUp(double elapsedTime, PowerUp p) {
		int num_lives = lives.size();
		if(p.TYPE.equals(BALL_POWERUP)){
			Bouncer newB = new Bouncer(MOVER_SPEED);
			newB.reset(SIZE, SIZE);
			addDisplay(newB.DISPLAY);
			bouncers.add(newB);
		}
		else if(p.TYPE.equals(LIFE_POWERUP)) {
			Life l = new Life(MARGIN + 20 * lives.size(), SIZE - MARGIN);
			addDisplay(l.DISPLAY);
			lives.add(l);
		}
		else if(p.TYPE.equals(PADDLE_POWERUP)) {
			removeDisplay(myPaddle.DISPLAY);
			myPaddle.Grow();
			addDisplay(myPaddle.DISPLAY);
		}
		else if(p.TYPE.equals(SNOWBALL) && lives.size() >= 1) {
			num_lives--;
			removeDisplay(lives.get(num_lives).DISPLAY);
			lives.remove(num_lives);
			bouncers.get(0).reset(SIZE, SIZE);
		}
		else if(p.TYPE.equals(SNOWBALL)) {
			createLoseScreen();
		}
	}

	public void checkPowerUps(double elapsedTime) {
		ArrayList<PowerUp> toRemove = new ArrayList<PowerUp>();
		for(PowerUp powerUp : powerUps) {
			powerUp.move(elapsedTime);
			if(powerUp.checkIntersect(myPaddle.getX(), myPaddle.getY(), myPaddle.WIDTH)) {
				powerUp.remove();
				toRemove.add(powerUp);
				createPowerUp(elapsedTime, powerUp);
			}
			if(!powerUp.checkBounds()) toRemove.add(powerUp);
		}

		for(PowerUp p : toRemove) {
			removeDisplay(p.DISPLAY);
			powerUps.remove(p);
		}
	}

	public Text makeText(String in, int x, int y){
		Text t = new Text(x, y, in);
		t.setFill(Color.WHITE);
		t.setFont(Font.font("Advent Pro", 15));
		return t;
	}

	public void killBlocks(double elapsedTime) {
		ArrayList<HitBlock> save = new ArrayList<HitBlock>();
		for(HitBlock block : hit_blocks) {
			for(Bouncer bouncer : bouncers) {
				if(block.intersect(bouncer) && block.numhits == 1 && block.VALID) {
					block.VALID = false;
					removeDisplay(block.DISPLAY);
					NUM_POINTS++;
					updatePointsText();
					if(block.powerUp.equals(BALL_POWERUP)) {
						PowerUp p = new PowerUp(BALL_POWERUP);
						powerUps.add(p);
						p.reset(block.X, block.Y);
						addDisplay(p.DISPLAY);
					}
					else if(block.powerUp.equals(LIFE_POWERUP)) {
						PowerUp p = new PowerUp(LIFE_POWERUP);
						powerUps.add(p);
						p.reset(block.X, block.Y);
						addDisplay(p.DISPLAY);
					}
					else if(block.powerUp.equals(PADDLE_POWERUP)) {
						PowerUp p = new PowerUp(PADDLE_POWERUP);
						powerUps.add(p);
						p.reset(block.X, block.Y);
						addDisplay(p.DISPLAY);
					}
					else if(block.powerUp.equals(SNOWBALL)) {
						PowerUp p = new PowerUp(SNOWBALL);
						powerUps.add(p);
						p.reset(block.X, block.Y);
						addDisplay(p.DISPLAY);
					}
					bouncer.bounceBlocks(elapsedTime);
				}
				else if(block.intersect(bouncer) && block.VALID) {	
					int nh = block.numhits -1;
					double x = block.X;
					double y = block.Y;
					removeDisplay(block.DISPLAY);
					block.VALID = false; 
					block = new HitBlock(x, y, nh, BLOCK_IMG, BALL_POWERUP);
					save.add(block);
					addDisplay(block.DISPLAY);
					bouncer.bounceBlocks(elapsedTime);
				}
			}
		}
		for(HitBlock newBlock : save) {
			hit_blocks.add(newBlock);
		}

		for(BounceBlock block : bounce_blocks) {
			for(Bouncer bouncer : bouncers) {
				if(block.intersect(bouncer)) {
					bouncer.bounceBlocks(elapsedTime);
				}
			}
		}
	}

	// Update positions
	public void move(double elapsedTime) {
		// Move bouncer, determine intersections
		for(Bouncer bouncer : bouncers){
			if(bouncer.VALID) {
				bouncer.bounce(elapsedTime, myPaddle.getX(), myPaddle.getY(), myPaddle.WIDTH);
				if(!bouncer.inBounds() && lives.size() >=1) {
					removeDisplay(lives.get(lives.size()-1).DISPLAY);
					lives.remove(lives.size()-1);
					bouncers.clear();
					addBouncer();
				}
				else if (!bouncer.inBounds()) createLoseScreen();
			}
		}

		checkPowerUps(elapsedTime);
	}

	public boolean Win() {
		for(HitBlock block : hit_blocks) {
			if(block.VALID == true) {
				return false;
			}
		}
		return true;
	}

	public int initializeLives(int x) {
		x += MARGIN;
		for(int i=0; i < 3; i++) {
			Life l = new Life(x, SIZE - 30);
			addDisplay(l.DISPLAY);
			lives.add(l);
			x += MARGIN/2;
		}

		return x;
	}

}
