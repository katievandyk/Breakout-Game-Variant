import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Paddle object which moves along bottom of screen, intersects with bouncer/power ups
 * Dependencies: Driver
 * 
 * @author Katherine Van Dyk
 *
 */
public class Paddle extends Driver {
	
	private int growCount;
	private KeyCode prev = null;
	private int baseSpeed = MOVER_SPEED/4;
	private static final int increment = 5;
	private int currSpeed = baseSpeed;
	private Rectangle DISPLAY;
	private double WIDTH;
	private double HEIGHT;
	public static final Paint PADDLE_COLOR = Color.AZURE;
	
	
    /**
     * Constructor for paddle object
     * 
     * @param x			X-coord for top left of paddle
     * @param y			Y-coord for top left of paddle
     * @param width		Width of paddle rectangle
     * @param height		Height of paddle rectangle
     */
	public Paddle(int x, int y, int width, int height) {
		this.DISPLAY = new Rectangle(x, y, width, height);
		this.DISPLAY.setFill(PADDLE_COLOR);
		this.WIDTH = width; 
		this.HEIGHT = height;
	}

	/**
	 *  Grows paddle up size (power up)
	 */
	public void Grow() {
		double width = this.WIDTH;
		double height = this.HEIGHT;
		double x = this.getX();
		double y = this.getY();
		
		if(growCount < 1){
			this.DISPLAY = new Rectangle(x/2, y, 1.5*width, height);
			this.WIDTH = 1.5*width;
			this.DISPLAY.setFill(PADDLE_COLOR);
		}
		growCount++;
	}
	
	/**
	 * Get top left x-coord of paddle
	 * 
	 * @return double 	Top left x-coord
	 */
	public double getX() {
		return this.DISPLAY.getX();
	}	
	
	/**
	 * Returns height of paddle
	 * 
	 * @return double	Paddle height	
	 */
	public double getHeight() {
		return this.HEIGHT;
	}	
	
	/**
	 * Returns width of paddle
	 * 
	 * @return double	Paddle width
	 */
	public double getWidth() {
		return this.WIDTH;
	}
	
	/**
	 * Returns top left y-coord of paddle
	 * 
	 * @return double 	Top left y-coord
	 */
	public double getY() {
		return this.DISPLAY.getY();
	}
	
	/**
	 * Returns display (rectangle) of paddle
	 * 
	 * @return Rectangle		Display of paddle
	 */
	public Rectangle getDisplay() {
		return this.DISPLAY;
	}
	
	/**
	 * Returns speed of paddle (increments if paddle moves in the same direction)
	 * 
	 * @param code	Paddle movement
	 * @return int	Speed of paddle
	 */
	public int getSpeed(KeyCode code) {
		if(code == prev) {	
			currSpeed = currSpeed + increment;
			return currSpeed;
		}
		else {
			prev = code;
			currSpeed = baseSpeed;
			return currSpeed;
		}
	} 
	
}
