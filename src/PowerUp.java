import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * PowerUp object, falls from block and contains type that can be translated into powerUp if intersected by paddle
 * Dependencies: Driver
 * 
 * @author Katherine Van Dyk
 *
 */
public class PowerUp extends Driver{

	private String TYPE;
	private ImageView DISPLAY;
	
	/**
	 * PowerUp constructor
	 * 
	 * @param type	Type of powerUp
	 */
	public PowerUp(String type){
		this.TYPE = type;
		if(type.equals("newBall")) {
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(BALL_POWERUP_IMG));
			this.DISPLAY = new ImageView(image);
		}
		else if(type.equals("newLife")) {
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(LIFE_POWERUP_IMG));
			this.DISPLAY = new ImageView(image);
		}
		else if(type.equals("biggerPaddle")) {
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(PADDLE_POWERUP_IMG));
			this.DISPLAY = new ImageView(image);
		}
		else if(type.equals("snowBall")) {
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(SNOWBALL_IMG));
			this.DISPLAY = new ImageView(image);
		}
	}

	/**
	 * Check if powerUp intersects with paddle
	 * 
	 * @param padX		Top left x-coord of paddle
	 * @param padY		Top left y-coord of paddle
	 * @param pWIDTH		Width of paddle
	 * @param pHEIGHT	Height of paddle
	 * @return
	 */
	public boolean checkIntersect(double padX, double padY, double pWIDTH, double pHEIGHT) {
		double bX = this.DISPLAY.getX();
		double bY = this.DISPLAY.getY();
		if(bX >= padX && bX <= (padX + pWIDTH) && padY <= bY && (padY + pHEIGHT) > bY) {
			return true;
		}
		return false;

	}

	/**
	 * Resets powerup to center of screen
	 * 
	 * @return double[][] 	
	 */
	public void reset(double x, double y) {
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
	}

	/**
	 * Moves power up down screen
	 * 
	 * @param elapsedTime	Instance of time
	 */
	public void move(double elapsedTime) {
		this.DISPLAY.setY(this.DISPLAY.getY() + 3/2*MOVER_SPEED * elapsedTime);
	}

	/**
	 * Returns image of power up
	 * 
	 * @return ImageView		Returns display of image	
	 */
	public ImageView getDisplay() {
		return this.DISPLAY;
	}
	
	/**
	 * Returns type of power up
	 * 
	 * @return String
	 */
	public String getType() {
		return this.TYPE;
	}

	/**
	 * Checks if power up is within bounds of screen
	 * 
	 * @return boolean 	True if within bounds of screen, false otherwise 	
	 */
	public boolean checkBounds() {
		if(this.DISPLAY.getY() >= SIZE) return false;
		return true;
	}
}
