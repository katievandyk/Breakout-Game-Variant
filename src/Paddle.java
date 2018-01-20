import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle extends Driver {
	
	int growCount;
	
	Rectangle DISPLAY;
	double X; 
	double Y;
	double WIDTH;
	double HEIGHT;
	public static final Paint PADDLE_COLOR = Color.PLUM;
	
	// Constructor
	public Paddle(int x, int y, int width, int height) {
		this.DISPLAY = new Rectangle(x, y, width, height);
		this.DISPLAY.setFill(PADDLE_COLOR);
		this.WIDTH = width; 
		this.HEIGHT = height;
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
	public void Grow(double d, double e, double width, double height) {
		if(growCount < 1){
			this.DISPLAY = new Rectangle(d/2, e, 1.5*width, height);
			this.WIDTH = 1.5*width;
			this.DISPLAY.setFill(PADDLE_COLOR);
		}
		growCount++;
	}
	
	

}
