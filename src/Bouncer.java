import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bouncer extends Driver {

	double X_SPEED;
	double Y_SPEED; 
	double X;
	double Y;
	ImageView DISPLAY;
	boolean VALID;

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
		// Send right
		if(ratio > 4/5 && x_SPEED < 0){
			this.X_SPEED = 2 * this.X_SPEED;
		}
		else if(ratio < 1/5 && x_SPEED > 0) {
			this.X_SPEED = -2 * this.X_SPEED;
		}
		this.Y_SPEED = -this.Y_SPEED;
	}

	/**
	 * Check bouuncer intersection with hit block
	 */
	public boolean intersect(HitBlock block) {
		double X = block.DISPLAY.getX();
		double Y = block.DISPLAY.getY();
		double bX = this.DISPLAY.getX();
		double bY = this.DISPLAY.getY();
		if(bX >= X && bX <= (X + BLOCK_WIDTH) && bY >= Y && bY <= (Y + BLOCK_HEIGHT)) {
			return true;
		}
		return false;
	}

	/**
	 * Check block intersection with bounce block
	 */
	public boolean intersect(BounceBlock block) {
		double X = block.DISPLAY.getX();
		double Y = block.DISPLAY.getY();
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
	
	public boolean isValid() {
		return VALID;
	}
	
	public void setValid(boolean t) {
		this.VALID = t;
	}
	
	public ImageView getDisplay() {
		return this.DISPLAY;
	}
}
