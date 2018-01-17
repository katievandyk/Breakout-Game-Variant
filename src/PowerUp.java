import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerUp extends Game{

	String TYPE;
	ImageView DISPLAY;
	double X;
	double Y;

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

		this.X = this.DISPLAY.getX();
		this.Y = this.DISPLAY.getY();

	}

	// Reset powerup to block to center
	public void reset(double x, double y) {
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
	}

	public void move(double elapsedTime) {
		this.DISPLAY.setY(this.DISPLAY.getY() + MOVER_SPEED * elapsedTime);
	}

	public boolean intersect(Paddle myPaddle) {
		double X = myPaddle.DISPLAY.getX();
		double Y = myPaddle.DISPLAY.getY();
		double bX = this.DISPLAY.getX();
		double bY = this.DISPLAY.getY();
		if(bX >= X && bX <= (X + PADDLE_WIDTH) && bY == Y) {
			return true;
		}
		return false;

	}
}
