import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Life extends Driver {

	private ImageView DISPLAY;
	
	/**
	 * Constructor for life
	 */
	public Life(int x, int y) {
		Image image = new Image(getClass().getClassLoader().getResourceAsStream(LIFE_IMG));
		this.DISPLAY = new ImageView(image);
		this.DISPLAY.setX(x);
		this.DISPLAY.setY(y);
	}
	
	// Gets display for life
	public ImageView getDisplay() {
		return this.DISPLAY;
	}

}
