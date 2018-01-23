import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

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
     * Constructor
     */
	public Paddle(int x, int y, int width, int height) {
		this.DISPLAY = new Rectangle(x, y, width, height);
		this.DISPLAY.setFill(PADDLE_COLOR);
		this.WIDTH = width; 
		this.HEIGHT = height;
	}

	/**
	 * Grow power up
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
	
	// Get top left x coord of paddle
	public double getX() {
		return this.DISPLAY.getX();
	}	
	
	// Get height of paddle
	public double getHeight() {
		return this.HEIGHT;
	}	
	
	// Get top left y coord of paddle
	public double getY() {
		return this.DISPLAY.getY();
	}
	
	// Get width of paddle
	public double getWidth() {
		return this.WIDTH;
	}
	
	// Get display of paddle
	public Rectangle getDisplay() {
		return this.DISPLAY;
	}
	
	// Get speed of paddle movement
	public int getSpeed(KeyCode code) {
		// Increment speed
		if(code == prev) {	
			currSpeed = currSpeed + increment;
			return currSpeed;
		}
		// Reset speed
		else {
			prev = code;
			currSpeed = baseSpeed;
			return currSpeed;
		}
	} 
	
}
