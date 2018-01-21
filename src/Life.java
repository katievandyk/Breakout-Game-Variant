import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Life extends Driver {

	private int NUM_LIVES;
	private ImageView DISPLAY;
	private double X;
	private double Y;

	// Constructor 
	public Life(int x, int y) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(LIFE_IMG));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
		this.X = x;
		this.Y = y;
	}

	public int getLives() {
		return NUM_LIVES;
	}

	public String toText() {
		return Integer.toString(NUM_LIVES);
	}
	
	public ImageView getDisplay() {
		return this.DISPLAY;
	}

}
