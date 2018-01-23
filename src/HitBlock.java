import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HitBlock extends Driver {

	private ImageView DISPLAY;
	private int numhits;
	private double X;
	private double Y;
	private String POWERUP;
	private boolean VALID;
	private String[] availablePowerUps = {SNOWBALL, BALL_POWERUP, LIFE_POWERUP, PADDLE_POWERUP, null};

	/**
	 * Constructor, known power up
	 */
	public HitBlock (double x, double y, int nHits, String BLOCK_IMAGE, String powerUp){
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BLOCK_IMAGE));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
		this.numhits = nHits;
		this.X = x;
		this.Y = y;
		this.POWERUP = powerUp;
		this.VALID = true;
	}
	
	
	/**
	 * Constructor, unknown power up
	 */
	public HitBlock (double x, double y, int i) {
		String img;
		int numhits;
		if(i % 2 == 0) {
			numhits = 2;
			img = BLOCK2_IMG;
		}
		else {
			numhits = 1;
			img = BLOCK_IMG;
		} 

		java.util.Random random = new java.util.Random();
		int r = random.nextInt(availablePowerUps.length);
		
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(img));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
		this.numhits = numhits;
		this.X = x;
		this.Y = y;
		this.POWERUP = availablePowerUps[r];
		this.VALID = true;
	}
	
	/**
	 * Check that block can be removed from screen
	 */
	public boolean canRemove(Bouncer bouncer) {
		return this.intersect(bouncer) && this.numhits == 1 && this.VALID;
	}

	/**
	 * Check that block can be hit, possibly removed
	 */
	public boolean canHit(Bouncer bouncer) {
		return this.intersect(bouncer) && this.VALID;	
	}

	/**
	 * Decrease number of hits left before removal
	 */
	public void decreaseHits(String newImage) {
		this.numhits--;
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(newImage));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(this.X);
		this.DISPLAY.setY(this.Y);
	}

	/**
	 * Check if intersect with bouncer
	 */
	public boolean intersect(Bouncer bouncer) {
		double X = this.DISPLAY.getX();
		double Y = this.DISPLAY.getY();
		double bX = bouncer.getX();
		double bY = bouncer.getY();	

		if(bX >= X && bX <= (X + BLOCK_WIDTH) && bY >= Y && bY <= (Y + BLOCK_HEIGHT)) {
			return true;
		}
		return false;
	}

	// Get display of block
	public ImageView getDisplay() {
		return this.DISPLAY;
	}

	// Get top left x coord of block
	public double getX() {
		return this.DISPLAY.getX();
	}

	// Get top left y coord of block
	public double getY() {
		return this.DISPLAY.getY();
	}
	
	// Get if block is valid
	public boolean getValid() {
		return this.VALID;
	}
	
	// Set block to be valid/invalid
	public void setValid(boolean v) {
		this.VALID = v;
	}
	
	// Get type of power up block contains
	public String getPowerUp() {
		return this.POWERUP;
	}
}





