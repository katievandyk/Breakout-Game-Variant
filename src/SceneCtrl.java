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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SceneCtrl extends Driver{
	protected ArrayList<Bouncer> bouncers = new ArrayList<Bouncer>();
	private ArrayList<Life> lives = new ArrayList<Life>();
	protected ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>();
	protected ArrayList<BounceBlock> bounce_blocks = new ArrayList<BounceBlock>();
	protected ArrayList<HitBlock> hit_blocks = new ArrayList<HitBlock>();
	private Paddle myPaddle;
	private String INSTRUCTIONS  = "\n\n\n\n Use the left and right arrow keys to control the\n" +"paddle at the bottom of the screen. Avoid \n"
			+ "the snowballs, or you'll lose a life! Make\n" + "it through all 3 levels by knocking off all\n" + "blocks to win.\n"+ "\n"
			+ "\n" + "\n" + "PRESS ENTER TO BEGIN";
	private String LOSE = "YOU LOST";
	private String WIN = "YOU WON";
	private int CURR_LEVEL;
	private Scene myScene;
	private Stage myStage;
	private Group root = new Group();
	private LevelCtrl levelcontroller;
	private ToolBar toolbar = new ToolBar(0, 1);
	protected int NUM_POINTS;

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
	 * Checks for cheat keys, transition keys, and paddle move keys
	 */
	public void checkKeys() {
		myScene.setOnKeyPressed(f -> checkKey(f.getCode()));
	}

	public void checkKey(KeyCode code) {
		if (code == KeyCode.RIGHT)	myPaddle.getDisplay().setX(myPaddle.getDisplay().getX() + MOVER_SPEED/4);
		else if (code == KeyCode.LEFT)	myPaddle.getDisplay().setX(myPaddle.getDisplay().getX() - MOVER_SPEED/4);
		else if (code == KeyCode.ENTER) {
			clearDisplay();
			levelcontroller = new LevelCtrl(CURR_LEVEL);
			CURR_LEVEL = 1;
			initLevel();
			initializeLives(toolbar.getOffset());
		}
		else if(code == KeyCode.DIGIT1) {
			CURR_LEVEL = 1;
			updateLevel();
		}
		else if(code == KeyCode.DIGIT2) {
			CURR_LEVEL = 2;
			updateLevel();
		}
		else if(code == KeyCode.DIGIT3) {
			CURR_LEVEL = 3;
			updateLevel();
		}
		else if(code == KeyCode.L) {
			resetLives();
			initializeLives(toolbar.getOffset());
		}
		else if(code == KeyCode.R) {
			updateLevel();
		}
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
		Text t;
		clearDisplay();
		Rectangle r = new Rectangle(0, SIZE/4, SIZE, SIZE/2);
		r.setFill(Color.WHITE); 
		addDisplay(r);
		if(s.equals(INSTRUCTIONS)) t = new Text(SIZE/8, SIZE/6, s);
		else t = new Text(SIZE*2/5, SIZE/2, s);
		t.setFill(Color.BLACK);
		t.setTextAlignment(TextAlignment.CENTER);
		t.setFont(Font.font("Advent Pro", 20));
		addDisplay(t);
	}

	/**
	 * Create initial level
	 */
	public void initLevel() {
		levelcontroller.changeLevel(CURR_LEVEL);
		makeToolbar();
		makeBlocks(levelcontroller.getHitCoords(), levelcontroller.getBounceCoords());
		addBouncer();
		myPaddle = new Paddle(SIZE/2, SIZE - 100, PADDLE_WIDTH, PADDLE_HEIGHT);
		addDisplay(myPaddle.getDisplay());	
	}

	/**
	 * Update levels
	 */
	public void updateLevel() {
		levelcontroller.changeLevel(CURR_LEVEL);
		clearBlocks();
		makeBlocks(levelcontroller.getHitCoords(), levelcontroller.getBounceCoords());
		clearLevel();
		updateLevelText();
	}

	/**
	 * Clear all objects in level
	 */
	public void clearLevel() {
		for(int i = 0; i < bouncers.size(); i++) {
			removeDisplay(bouncers.get(i).DISPLAY);
		}
		for(int i=0; i < powerUps.size(); i++) {
			removeDisplay(powerUps.get(i).DISPLAY);
		}
		powerUps.clear();
		bouncers.clear();
		addBouncer();
	}

	/**
	 * Makes block objects from coordinates
	 */
	private void makeBlocks(double[][] hit_coords, double[][] bounce_coords) {
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
		addDisplay(toolbar.getBar());
		addDisplay(toolbar.getLivesLabel());
		addDisplay(toolbar.getPointsLabel());
		addDisplay(toolbar.getLevelLabel());
		addDisplay(toolbar.getPoints());
		addDisplay(toolbar.getLevel());
	}

	/**
	 *  Updates points feature of toolbar
	 */
	public void updatePointsText() {
		removeDisplay(toolbar.getPoints());
		toolbar.updatePoints(NUM_POINTS);
		addDisplay(toolbar.getPoints());
	}

	/**
	 *  Updates level feature of toolbar
	 */
	public void updateLevelText() {
		removeDisplay(toolbar.getLevel());
		toolbar.updateLevel(CURR_LEVEL);
		addDisplay(toolbar.getLevel());
	}

	/**
	 *  Initializes lives list/tool bar feature
	 */
	public void initializeLives(int x) {
		for(int i=0; i < 3; i++) {
			lives.add(new Life(x, SIZE - Y_MARGIN));
			addDisplay(lives.get(i).getDisplay());
			x += X_MARGIN/2;
		}
	} 

	/**
	 * Reset to original 3 lives
	 */
	public void resetLives() {
		for(int i=0; i < lives.size(); i++) {
			removeDisplay(lives.get(i).getDisplay());
		}
		lives.clear();
	}

	/**
	 *  Initializes main bouncer
	 */
	public void addBouncer() {
		bouncers.add(new Bouncer(CURR_LEVEL/2 * MOVER_SPEED + MOVER_SPEED)); 
		bouncers.get(0).reset(SIZE, SIZE);
		addDisplay(bouncers.get(0).getDisplay());
	}

	/**
	 *  Creates powerUp once it's been intersected
	 */
	public void createPowerUp(double elapsedTime, PowerUp p) {
		if(p.TYPE.equals(BALL_POWERUP)){
			bouncers.add(new Bouncer(MOVER_SPEED));
			bouncers.get(bouncers.size()-1).reset(SIZE, SIZE);
			addDisplay(bouncers.get(bouncers.size()-1).DISPLAY);
		}
		else if(p.TYPE.equals(LIFE_POWERUP)) {
			System.out.println(lives.size());
			lives.add(new Life(toolbar.getOffset() + X_MARGIN/2 * lives.size(), SIZE - Y_MARGIN));
			addDisplay(lives.get(lives.size()-1).getDisplay());
		}
		else if(p.TYPE.equals(PADDLE_POWERUP)) {
			removeDisplay(myPaddle.getDisplay());
			myPaddle.Grow();
			addDisplay(myPaddle.getDisplay());
		}
		else if(p.TYPE.equals(SNOWBALL) && lives.size() >= 1) {
			removeDisplay(lives.get(lives.size()-1).getDisplay());
			lives.remove(lives.size()-1);
		}
		else if(p.TYPE.equals(SNOWBALL)) {
			screenText(LOSE);
		}
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
		// Check intersections between all bouncers and all blocks
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
		// Update bouncers
		for(Bouncer bouncer : bouncers){
			if(bouncer.isValid()) bouncer.bounce(elapsedTime, myPaddle.getX(), myPaddle.getY(), myPaddle.WIDTH);
			if(!bouncer.inBounds() && bouncer.isValid()) bouncer.setValid(false);
		}
		// Check for loss
		if(handleLose() && lives.size() > 1) removeLife();
		else if(handleLose()) screenText(LOSE);
		// Update power ups
		checkPowerUps(elapsedTime);
	}

	/**
	 *  Removes life 
	 */
	public void removeLife() {
		removeDisplay(lives.get(lives.size()-1).getDisplay());
		lives.remove(lives.size()-1);
		bouncers.clear();
		addBouncer();
	}

	/**
	 *  Checks for win
	 */
	public boolean handleWin() {
		for(HitBlock block : hit_blocks) if(block.VALID == true) return false;
		CURR_LEVEL++;
		if(CURR_LEVEL < 3) initLevel();
		else screenText(WIN);
		return true;
	}
	
	/**
	 *  Checks for loss
	 */
	public boolean handleLose() {
		for(Bouncer bouncer : bouncers) {
			if(bouncer.isValid()) return false;
		}
		return true;
	}
}
