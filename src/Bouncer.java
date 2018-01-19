import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Bouncer extends Driver {

	double X_SPEED;
	double Y_SPEED; 
	double X;
	double Y;
	ImageView DISPLAY;
	boolean VALID;

	// Constructor
	public Bouncer (int speed){
		this.X_SPEED = speed;
		this.Y_SPEED = speed;
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BOUNCER_IMG));
		this.DISPLAY = new ImageView(image);
		this.VALID = true;
	}

	// Reset bouncer to center
	public void reset(int width, int height) {
		this.DISPLAY.setX(width / 2 - this.DISPLAY.getBoundsInLocal().getWidth() / 2);
		this.DISPLAY.setY(height / 2 - this.DISPLAY.getBoundsInLocal().getHeight() / 2);
	}

	// Bounce as appropriate
	public void bounce(double elapsedTime, Paddle myPaddle) {
		X = this.DISPLAY.getX();
		Y = this.DISPLAY.getY();
		double pX = myPaddle.DISPLAY.getX();
		double pY = myPaddle.DISPLAY.getY();
		// Bounces against either side
		if(X <= 0 || X >= SIZE) {
			this.X_SPEED = -this.X_SPEED;
		}	
		// Bounces against top
		if(Y <= 0) {
			this.Y_SPEED = -this.Y_SPEED;
		}
		// Bounces against paddle
		if(X >= pX && X <= (pX + PADDLE_WIDTH) && Y >= pY) {
			String dir= myPaddle.paddleDir(X, this.X_SPEED); //bounce according to paddle section
			if(dir.equals("extRight") || dir.equals("extLeft")) this.X_SPEED = -2 * this.X_SPEED;
			this.Y_SPEED = -this.Y_SPEED;
		}

		this.DISPLAY.setX(X + this.X_SPEED * elapsedTime);
		this.DISPLAY.setY(Y + this.Y_SPEED* elapsedTime);
	}


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


	public void bounceBlocks(double elapsedTime) {
		if(this.VALID) {
			this.X_SPEED = -MOVER_SPEED;
			this.Y_SPEED = -this.Y_SPEED;
			this.DISPLAY.setX(X + this.X_SPEED * elapsedTime);
			this.DISPLAY.setY(Y + this.Y_SPEED* elapsedTime);
		}
	}
	
	public void checkStatus() {
		// Multiple bouncers, remove one
		if(this.Y >= SIZE && bouncers.size() > 1){
			root.getChildren().remove(this.DISPLAY);
			this.VALID = false;
		}
		// Lose last bouncer, mult. lives
		else if(this.Y >= SIZE && lives.size() > 1) {				
			NUM_LIVES--;
			bouncers.get(0).reset(SIZE, SIZE);
			root.getChildren().remove(lives.get(NUM_LIVES).DISPLAY);
			lives.remove(NUM_LIVES);
		}
		// Lose last bouncer, last life
		else if(this.Y >= SIZE) {
			CURR_LEVEL = -1;
			updateLevel();
		}
	}

}
