import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerUp extends Driver{

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
		else if(type.equals("snowBall")) {
			Image image = new Image(getClass().getClassLoader().getResourceAsStream(SNOWBALL_IMG));
			this.DISPLAY = new ImageView(image);
		}

		this.X = this.DISPLAY.getX();
		this.Y = this.DISPLAY.getY();

	}

	public boolean checkIntersect(double padX, double padY, double pWIDTH) {
		double bX = this.DISPLAY.getX();
		double bY = this.DISPLAY.getY();
		if(bX >= padX && bX <= (padX + pWIDTH) && padY <= bY) {
			return true;
		}
		return false;

	}

	// Reset powerup to block to center
	public void reset(double x, double y) {
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
	}

	public void move(double elapsedTime) {
		this.DISPLAY.setY(this.DISPLAY.getY() + 3/2*MOVER_SPEED * elapsedTime);
	}
	
	public void remove() {
		//super.removeDisplay(this.DISPLAY);
	}



	public boolean checkBounds() {
		if(this.DISPLAY.getY() >= SIZE) return false;
		return true;
	}
}
