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

public class Bouncer extends Game {
	
	double X_SPEED;
	double Y_SPEED; 
	double X;
	double Y;
	ImageView DISPLAY;
	
	// Constructor
	public Bouncer (int speed){
		this.X_SPEED = speed;
		this.Y_SPEED = speed;
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(BOUNCER_IMAGE));
		this.DISPLAY = new ImageView(image);
	}
	
	// Reset bouncer to center
	public void reset(int width, int height) {
		this.DISPLAY.setX(width / 2 - this.DISPLAY.getBoundsInLocal().getWidth() / 2);
		this.DISPLAY.setY(height / 2 - this.DISPLAY.getBoundsInLocal().getHeight() / 2);
	}
	
	// Bounce as appropriate
	public void bounce(double elapsedTime, Rectangle myPaddle) {
		X = this.DISPLAY.getX();
		Y = this.DISPLAY.getY();
		double pX = myPaddle.getX();
		double pY = myPaddle.getY(); 
		// Bounces against either side
		if(X <= 0 || X >= SIZE) {
			this.X_SPEED = -this.X_SPEED;
		}	
		// Bounces against top
		if(Y <= 0) {
			this.Y_SPEED = -this.Y_SPEED;
		}
		// Bounces against paddle
		if(X >= pX && X <= (pX + PADDLE_WIDTH) && Y >= pY && Y <= (pY + PADDLE_HEIGHT)) {
			System.out.println("HERE");
			this.Y_SPEED = -this.Y_SPEED;
		}
		
		this.DISPLAY.setX(X + this.X_SPEED * elapsedTime);
		this.DISPLAY.setY(Y + this.Y_SPEED* elapsedTime);
	}
	
	public boolean intersect(Block block) {
		double X = block.DISPLAY.getX();
		double Y = block.DISPLAY.getY();
		double bX = this.DISPLAY.getX();
		double bY = this.DISPLAY.getY();
		if(bX >= X && bX <= (X + 50) && bY >= Y && bY <= (Y + 20)) {
			return true;
		}
		return false;
	}
	
	public void bounceBlocks(double elapsedTime) {

		this.X_SPEED = -this.X_SPEED;
		this.Y_SPEED = -this.Y_SPEED;

		this.DISPLAY.setX(X + this.X_SPEED * elapsedTime);
		this.DISPLAY.setY(Y + this.Y_SPEED* elapsedTime);
	}

}
