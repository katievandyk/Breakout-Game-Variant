import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SceneCtrl extends Driver{

	protected ArrayList<Bouncer> bouncers = new ArrayList<Bouncer>();
	private ArrayList<Life> lives = new ArrayList<Life>();
	protected ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	protected ArrayList<BounceBlock> bounce_blocks = new ArrayList<BounceBlock>();
	protected ArrayList<HitBlock> hit_blocks = new ArrayList<HitBlock>();
	private Paddle myPaddle;


	private String INSTRUCTIONS = "WELCOME TO BREAKOUT!";
	private String LOSE = "YOU LOST:(";
	private String WIN = "YOU WON!!:)";
	protected int NUM_POINTS;
	private int CURR_LEVEL;
	private Scene myScene;
	private Stage myStage;
	private Group root = new Group();
	private LevelCtrl levelcontroller;
	private ToolBar t = new ToolBar(0, 1);

	public SceneCtrl(Stage stage) {
		this.myScene = null;
		this.myStage = stage;
		this.CURR_LEVEL = 0;
		this.NUM_POINTS = 0;
	}
	
	/**
	 * Constructor
	 */
	public void setScene() {
		this.myScene = new Scene(root, SIZE, SIZE);
		ImagePattern pattern = new ImagePattern(Image(BACKGROUND_IMG));
		myScene.setFill(pattern); 
		this.myStage.setScene(myScene);
		this.myStage.setTitle(TITLE);
		this.myStage.show();

		screenText(INSTRUCTIONS);

	}

	/**
	 * Returns scene
	 */
	public Scene getScene() {
		return this.myScene;
	}

	/**
	 * Checks for cheat keys, transition keys, and paddle move keys
	 */
	public void checkKeys() {
		myScene.setOnKeyPressed(f -> checkKey(f.getCode()));
	}

	public void checkKey(KeyCode code) {
		if (code == KeyCode.RIGHT)	myPaddle.getDisplay().setX(myPaddle.getDisplay().getX() + MOVER_SPEED/4);
		else if (code == KeyCode.LEFT)	myPaddle.getDisplay().setX(myPaddle.getDisplay().getX() - MOVER_SPEED/4);
		else if (code == KeyCode.ENTER) {
			levelcontroller = new LevelCtrl(CURR_LEVEL);
			CURR_LEVEL = 1;
			createLevel();
		}
		else if(code == KeyCode.DIGIT1) {
			CURR_LEVEL = 1;
			createLevel();
		}
		else if(code == KeyCode.DIGIT2) {
			CURR_LEVEL = 2;
			createLevel();
		}
		else if(code == KeyCode.DIGIT3) {
			CURR_LEVEL = 3;
			createLevel();
		}
		else screenText(LOSE);
	}

	/**
	 * Removes images, rectangles, and text from screen 
	 */
	public void removeDisplay(ImageView image) {
		root.getChildren().remove(image);
	}
	
	public void removeDisplay(Text image) {
		root.getChildren().remove(image);
	}
	
	public void removeDisplay(Rectangle image) {
		root.getChildren().remove(image);
	}
	
	/**
	 * Adds images, rectangles, and text to screen 
	 */
	public void addDisplay(ImageView image) {
		root.getChildren().add(image);
	}

	public void addDisplay(Text t) {
		root.getChildren().add(t);
	}

	public void addDisplay(Rectangle image) {
		root.getChildren().add(image);
	}
	
	/**
	 * Returns current level
	 */
	public int getLevel() {
		return CURR_LEVEL;
	}

	/**
	 * Clears entire display
	 */
	public void clearDisplay() {
		root.getChildren().clear();
	}

	/**
	 * Returns win, lose and start screens
	 */
	public void screenText(String s) {
		clearDisplay();
		Text t  = new Text(MARGIN, MARGIN, s);
		t.setFill(Color.BLACK);
		t.setFont(Font.font(java.awt.Font.SERIF, 25));
		addDisplay(t);
	}

	/**
	 * Returns level screen
	 */
	public void createLevel() {
		levelcontroller.changeLevel(CURR_LEVEL);
		clearDisplay();
		makeToolbar();
		clearBlocks();
		makeBlocks(levelcontroller.getHitCoords(), levelcontroller.getBounceCoords());
		addBouncer();
		myPaddle = new Paddle(SIZE/2, SIZE - 100, PADDLE_WIDTH, PADDLE_HEIGHT);
		addDisplay(myPaddle.getDisplay());	
	}

	/**
	 * Makes block objects from coordinates
	 */
	private void makeBlocks(double[][] hit_coords, double[][] bounce_coords) {
		System.out.println(CURR_LEVEL);
		for(int i=0; i < hit_coords.length; i++) {
			hit_blocks.add(new HitBlock(hit_coords[i][0], hit_coords[i][1], i));
			addDisplay(hit_blocks.get(i).getDisplay());
		}
		
		if(bounce_coords != null) {
			for(int i=0; i < bounce_coords.length; i++) {
				bounce_blocks.add(new BounceBlock(bounce_coords[i][0], bounce_coords[i][1], BOUNCEBLOCK_IMG));
				addDisplay(bounce_blocks.get(i).getDisplay());
			}
		}
	}

	/**
	 * Clears blocks from display/storage array
	 */
	public void clearBlocks() {
		for(HitBlock block : hit_blocks) removeDisplay(block.getDisplay());
		for(BounceBlock block : bounce_blocks) removeDisplay(block.getDisplay());
		hit_blocks.clear();
		bounce_blocks.clear();
	}
	
	/**
	 *  Initializes toolbar
	 */
	public void makeToolbar() {
		addDisplay(t.getBar());
		addDisplay(t.getLivesLabel());
		addDisplay(t.getPointsLabel());
		addDisplay(t.getLevelLabel());
		addDisplay(t.getPoints());
		addDisplay(t.getLevel());
		initializeLives(t.getOffset() + MARGIN);
	}

	/**
	 *  Updates points feature of toolbar
	 */
	public void updatePointsText() {
		removeDisplay(t.getPoints());
		t.updatePoints(NUM_POINTS);
		addDisplay(t.getPoints());
	}

	/**
	 *  Updates level feature of toolbar
	 */
	public void updateLevelText() {
		removeDisplay(t.getLevel());
		t.updateLevel(CURR_LEVEL);
		addDisplay(t.getLevel());
	}

	/**
	 *  Initializes lives list/tool bar feature
	 */
	public void initializeLives(int x) {
		for(int i=0; i < 3; i++) {
			lives.add(new Life(x, SIZE - 30));
			addDisplay(lives.get(i).getDisplay());
			x += MARGIN/2;
		}
	} 
	
	/**
	 *  Initializes main bouncer
	 */
	public void addBouncer() {
		bouncers.add(new Bouncer(MOVER_SPEED)); 
		bouncers.get(0).reset(SIZE, SIZE);
		addDisplay(bouncers.get(0).getDisplay());
	}

	/**
	 *  Clears level of all objects, resets main bouncer
	 */
	public void clearLevel() {
		if(this.CURR_LEVEL > 1) {
			for(Bouncer bouncer : bouncers) removeDisplay(bouncer.getDisplay());
			bouncers.clear();
			addBouncer();
		}
		hit_blocks.clear();
		bounce_blocks.clear();
		for(PowerUp p : powerUps) {
			removeDisplay(p.getDisplay());
		}
		powerUps.clear();
	}
	
	/**
	 *  Creates powerUp once it's been intersected
	 */
	public void createPowerUp(double elapsedTime, PowerUp p) {
		int num_lives = lives.size();
		if(p.TYPE.equals(BALL_POWERUP)){
			bouncers.add(new Bouncer(MOVER_SPEED));
			bouncers.get(bouncers.size()-1).reset(SIZE, SIZE);
			addDisplay(bouncers.get(bouncers.size()-1).DISPLAY);
		}
		else if(p.TYPE.equals(LIFE_POWERUP)) {
			lives.add(new Life(MARGIN + 20 * lives.size(), SIZE - MARGIN));
			addDisplay(lives.get(lives.size()-1).getDisplay());
		}
		else if(p.TYPE.equals(PADDLE_POWERUP)) {
			removeDisplay(myPaddle.getDisplay());
			myPaddle.Grow();
			addDisplay(myPaddle.getDisplay());
		}
		else if(p.TYPE.equals(SNOWBALL) && lives.size() >= 1) {
			num_lives--;
			removeDisplay(lives.get(num_lives).getDisplay());
			lives.remove(num_lives);
			bouncers.get(0).reset(SIZE, SIZE);
		}
		else if(p.TYPE.equals(SNOWBALL)) screenText(LOSE);
	}

	/**
	 *  Checks for intersections with any falling power ups
	 */
	public void checkPowerUps(double elapsedTime) {
		ArrayList<PowerUp> toRemove = new ArrayList<PowerUp>();
		for(PowerUp powerUp : powerUps) {
			powerUp.move(elapsedTime);
			if(powerUp.checkIntersect(myPaddle.getX(), myPaddle.getY(), myPaddle.WIDTH)) {
				toRemove.add(powerUp);
				createPowerUp(elapsedTime, powerUp);
			}
			if(!powerUp.checkBounds()) toRemove.add(powerUp);
		}
		for(PowerUp p : toRemove) {
			removeDisplay(p.getDisplay());
			powerUps.remove(p);
		}
	}

	/**
	 *  Removes/updates any blocks that are hit
	 */
	public void killBlocks(double elapsedTime) {
		String powerUp = null;

		for(HitBlock block : hit_blocks) {
			for(Bouncer bouncer : bouncers) {
				if(block.canRemove(bouncer)) {
					block.VALID = false;
					removeDisplay(block.getDisplay());
					NUM_POINTS++;
					updatePointsText();
					powerUp = block.powerUp;
					if(powerUp != null) {
						powerUps.add(new PowerUp(powerUp));
						powerUps.get(powerUps.size()-1).reset(block.getX(), block.getY());
						addDisplay(powerUps.get(powerUps.size()-1).getDisplay());
					}
					bouncer.bounceBlocks(elapsedTime);
				}
				else if(block.canHit(bouncer)) {	
					removeDisplay(block.getDisplay());
					block.decreaseHits(BLOCK_IMG);
					addDisplay(block.getDisplay());
					bouncer.bounceBlocks(elapsedTime);
				}
			}
		}
		for(BounceBlock block : bounce_blocks) {
			for(Bouncer bouncer : bouncers) {
				if(block.intersect(bouncer)) {
					bouncer.bounceBlocks(elapsedTime);
				}
			}
		}
	}

	/**
	 *  Moves bouncer and powerUps
	 */
	public void move(double elapsedTime) {
		// Move bouncer, determine intersections
		for(Bouncer bouncer : bouncers){
			if(bouncer.isValid()) bouncer.bounce(elapsedTime, myPaddle.getX(), myPaddle.getY(), myPaddle.WIDTH);
			if(!bouncer.inBounds() && lives.size() >=1) {
				removeDisplay(lives.get(lives.size()-1).getDisplay());
				lives.remove(lives.size()-1);
				bouncers.clear();
				addBouncer();
			}
			else if (!bouncer.inBounds()) screenText(LOSE);
		}
		checkPowerUps(elapsedTime);
	}

	/**
	 *  Checks for win
	 */
	public boolean handleWin() {
		for(HitBlock block : hit_blocks) {
			if(block.VALID == true) {
				return false;
			}
		}
		CURR_LEVEL++;
		if(CURR_LEVEL < 3) createLevel();
		else screenText(WIN);
		return true;
	}
}
