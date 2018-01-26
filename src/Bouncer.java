import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Creates bouncer object that moves, intersects with paddle/blocks
 * Dependencies: Driver
 * 
 * @author Katherine Van Dyk
 *
 */
public class Bouncer extends Driver {

	private double X_SPEED;
	private double Y_SPEED; 
	private double X;
	private double Y;
	private ImageView DISPLAY;
	private boolean VALID;

	/**
	 * Constructor
	 * 
	 * @param speed
	 */
	public Bouncer (int speed){
		this.X_SPEED = speed;
		this.Y_SPEED = speed;
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BOUNCER_IMG));
		this.DISPLAY = new ImageView(image);
		this.VALID = true;
	}
	

	/**
	 * Resets bouncer to center of screen
	 * 
	 * @param width 		Width of screen
	 * @param height		Height of screen
	 */
	public void reset(int width, int height) {
		this.DISPLAY.setX(width / 2 - this.DISPLAY.getBoundsInLocal().getWidth() / 2);
		this.DISPLAY.setY(height / 2 - this.DISPLAY.getBoundsInLocal().getHeight() / 2);
	}

	/**
	 * Bounces bouncer off walls/paddle if intersect
	 * 
	 * @param elapsedTime	Instance of time
	 * @param pX				Top-left x-coord of paddle
	 * @param pY				Top-left y-coord of paddle
	 * @param pWIDTH			Width of paddle
	 */
	public void bounce(double elapsedTime, double pX, double pY, double pWIDTH) {
		X = this.DISPLAY.getX();
		Y = this.DISPLAY.getY();
		if(X <= 0 || X >= SIZE) {
			this.X_SPEED = -this.X_SPEED;
		}	
		if(Y <= 0) {
			this.Y_SPEED = -this.Y_SPEED;
		}
		if(X >= pX && X <= (pX + pWIDTH) && Y >= pY) {
			this.paddleBounce(X, pX, pWIDTH);
		}

		this.DISPLAY.setX(X + this.X_SPEED * elapsedTime); 
		this.DISPLAY.setY(Y + this.Y_SPEED* elapsedTime);
	}
	
	/**
	 * Bounces bouncer off paddle depending on paddle position
	 * 
	 * @param x			Top-left x-coord of paddle
	 * @param x_SPEED	X-speed of bouncer
	 * @param pWIDTH		Width of paddle
	 */
	private void paddleBounce(double x, double x_SPEED, double pWIDTH) {
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
	 * Bounces bouncer off blocks
	 * 
	 * @param elapsedTime	Instance of time
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
	 * Check if within bounds of screen
	 * 
	 * @return boolean	True if bouncer within screen, false otherwise
	 */
	public boolean inBounds() {
		if(this.DISPLAY.getY() >= SIZE){
			this.VALID = false;
			return false;
		}
		return true;
	}
	
	/**
	 * Check if bouncer is valid (present on screen)
	 * 
	 * @return boolean	True if bouncer is valid, false otherwise
	 */
	public boolean isValid() {
		return VALID;
	}
	
	/**
	 * Set bouncer to be valid/invalid
	 * 
	 * @param t		True if bouncer is valid, false otherwise
	 */
	public void setValid(boolean t) {
		this.VALID = t;
	}
	
	/**
	 * Get image of bouncer
	 * 
	 * @return ImageView		Image of bouncer to be displayed
	 */
	public ImageView getDisplay() {
		return this.DISPLAY;
	}
	
	/**
	 * Get top left x-coord of bouncer
	 * 
	 * @return double	Top left x-coord
	 */
	public double getX() {
		X = this.DISPLAY.getX();
		return X;
	}
	
	/**
	 * Get top left y-coord of bouncer
	 * 
	 * @return
	 */
	public double getY() {
		return this.DISPLAY.getY();
	}
}
