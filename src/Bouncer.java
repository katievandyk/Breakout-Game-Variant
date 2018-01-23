import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bouncer extends Driver {

	private double X_SPEED;
	private double Y_SPEED; 
	private double X;
	private double Y;
	private ImageView DISPLAY;
	private boolean VALID;

	/**
	 * Constructor
	 */
	public Bouncer (int speed){
		this.X_SPEED = speed;
		this.Y_SPEED = speed;
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BOUNCER_IMG));
		this.DISPLAY = new ImageView(image);
		this.VALID = true;
	}
	

	/**
	 *  Reset bouncer to center 
	 */
	public void reset(int width, int height) {
		this.DISPLAY.setX(width / 2 - this.DISPLAY.getBoundsInLocal().getWidth() / 2);
		this.DISPLAY.setY(height / 2 - this.DISPLAY.getBoundsInLocal().getHeight() / 2);
	}

	/**
	 * Bounce off of walls/objects
	 */
	public void bounce(double elapsedTime, double pX, double pY, double pWIDTH) {
		X = this.DISPLAY.getX();
		Y = this.DISPLAY.getY();
		// Bounces against either side
		if(X <= 0 || X >= SIZE) {
			this.X_SPEED = -this.X_SPEED;
		}	
		// Bounces against top
		if(Y <= 0) {
			this.Y_SPEED = -this.Y_SPEED;
		}
		// Bounces against paddle
		if(X >= pX && X <= (pX + pWIDTH) && Y >= pY) {
			this.paddleBounce(X, pX, pWIDTH);
		}

		this.DISPLAY.setX(X + this.X_SPEED * elapsedTime); 
		this.DISPLAY.setY(Y + this.Y_SPEED* elapsedTime);
	}
	
	/**
	 * Bounce off of paddle
	 */
	public void paddleBounce(double x, double x_SPEED, double pWIDTH) {
		double ratio = x / pWIDTH;
		double speedUp = 2;
		// Send right
		if(ratio > 2/3 && this.X_SPEED < 0){
			this.X_SPEED = -speedUp * this.X_SPEED;
		}
		else if(ratio < 1/3 && this.X_SPEED > 0) {
			this.X_SPEED = -speedUp * this.X_SPEED;
		}

		this.Y_SPEED = -this.Y_SPEED;
	}

	/**
	 * Check bouncer intersection with hit block
	 */
	public boolean intersect(HitBlock block) {
		double X = block.getX();
		double Y = block.getY();
		double bX = this.getX();
		double bY = this.getY();
		if(bX >= X && bX <= (X + BLOCK_WIDTH) && bY >= Y && bY <= (Y + BLOCK_HEIGHT)) {
			return true;
		}
		return false;
	}

	/**
	 * Check block intersection with bounce block
	 */
	public boolean intersect(BounceBlock block) {
		double X = block.getX();
		double Y = block.getY();
		double bX = this.DISPLAY.getX();
		double bY = this.DISPLAY.getY();
		if(bX >= X && bX <= (X + BLOCK_HEIGHT) && bY >= Y && bY <= (Y + BLOCK_HEIGHT)) {
			return true;
		}
		return false;
	}

	/**
	 * Bounce off of block
	 */
	public void bounceBlocks(double elapsedTime) {
		if(this.VALID) {
			this.X_SPEED = -MOVER_SPEED;
			this.Y_SPEED = -this.Y_SPEED;
			this.DISPLAY.setX(X + this.X_SPEED * elapsedTime);
			this.DISPLAY.setY(Y + this.Y_SPEED* elapsedTime);
		}
	}

	/**
	 * Check if valid (within stage)
	 */
	public boolean inBounds() {
		if(this.DISPLAY.getY() >= SIZE){
			this.VALID = false;
			return false;
		}
		return true;
	}
	
	// Returns if block is valid
	public boolean isValid() {
		return VALID;
	}
	
	// Sets block to valid or not valid
	public void setValid(boolean t) {
		this.VALID = t;
	}
	
	// Get image display of bouncer
	public ImageView getDisplay() {
		return this.DISPLAY;
	}
	
	// Get top left x coord of bouncer
	public double getX() {
		X = this.DISPLAY.getX();
		return X;
	}
	
	// Get top left y coord of bouncer
	public double getY() {
		return this.DISPLAY.getY();
	}
}
