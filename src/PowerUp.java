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

		this.X = this.DISPLAY.getX();
		this.Y = this.DISPLAY.getY();

	}

	public void checkStatus(double elapsedTime) {
		if(this.intersect(myPaddle)) {
			root.getChildren().remove(this.DISPLAY);
			PowerUp.createPowerUp(elapsedTime, this);
		}
	}


	// Reset powerup to block to center
	public void reset(double x, double y) {
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
	}

	public void move(double elapsedTime) {
		this.DISPLAY.setY(this.DISPLAY.getY() + 2*MOVER_SPEED * elapsedTime);
	}

	public boolean intersect(Paddle myPaddle) {
		double X = myPaddle.DISPLAY.getX();
		double Y = myPaddle.DISPLAY.getY();
		double bX = this.DISPLAY.getX();
		double bY = this.DISPLAY.getY();
		if(bX >= X && bX <= (X + myPaddle.WIDTH) && bY <= Y) {
			return true;
		}
		return false;

	}

	public static void createPowerUp(double elapsedTime, PowerUp p) {
		if(p.TYPE.equals(BALL_POWERUP)){
			Bouncer newB = new Bouncer(MOVER_SPEED);
			newB.reset(SIZE, SIZE);
			root.getChildren().add(newB.DISPLAY);
			bouncers.add(newB);
		}
		else if(p.TYPE.equals(LIFE_POWERUP)) {
			Life li = new Life(MARGIN + 20 * NUM_LIVES, SIZE - MARGIN);
			NUM_LIVES++;
			root.getChildren().add(li.DISPLAY);
			lives.add(li);
		}
		else if(p.TYPE.equals(PADDLE_POWERUP)) {
			root.getChildren().remove(myPaddle.DISPLAY);
			myPaddle.Grow(myPaddle.DISPLAY.getX(), myPaddle.DISPLAY.getY(), myPaddle.WIDTH, myPaddle.HEIGHT);
			root.getChildren().add(myPaddle.DISPLAY);
		}
	}
}
