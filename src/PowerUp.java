import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerUp extends Driver{

	private String TYPE;
	private ImageView DISPLAY;

	/**
	 * Constructor
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
	 * Check if intersect with paddle
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
	 * Set power up block position
	 */
	public void reset(double x, double y) {
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
	}

	/**
	 * Move power up down screen
	 */
	public void move(double elapsedTime) {
		this.DISPLAY.setY(this.DISPLAY.getY() + 3/2*MOVER_SPEED * elapsedTime);
	}

	public ImageView getDisplay() {
		return this.DISPLAY;
	}
	
	public String getType() {
		return this.TYPE;
	}

	public boolean checkBounds() {
		if(this.DISPLAY.getY() >= SIZE) return false;
		return true;
	}
}
