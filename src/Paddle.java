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

public class Paddle extends Driver {
	
	Rectangle DISPLAY;
	double X; 
	double Y;
	int WIDTH;
	public static final Paint PADDLE_COLOR = Color.PLUM;
	
	// Constructor
	public Paddle(int x, int y, int width, int height) {
		this.DISPLAY = new Rectangle(x, y, width, height);
		this.DISPLAY.setFill(PADDLE_COLOR);
		this.WIDTH = width; 
	}
	
	// Determine sector of paddle
	
	public String paddleDir(double x, double x_SPEED) {
		double ratio = x / this.WIDTH;
		// Send right
		if(ratio > 4/5 && x_SPEED < 0) return "extRight";
		else if(ratio < 1/5 && x_SPEED > 0) return "extLeft";
		else return "normal";
	}
	
	
	// Power up
	public void Grow(int x, int y, int width, int height) {
		this.DISPLAY = new Rectangle(x/2, y, 1.5*width, 1.5*height);
		this.DISPLAY.setFill(PADDLE_COLOR);
	}
	
	
	// Movement
	public void handleKeyInput (KeyCode code) {
		if (code == KeyCode.RIGHT) {
			this.DISPLAY.setX(this.DISPLAY.getX() + MOVER_SPEED/4);
		}
		else if (code == KeyCode.LEFT) {
			this.DISPLAY.setX(this.DISPLAY.getX() - MOVER_SPEED/4);
		}
	}

}
